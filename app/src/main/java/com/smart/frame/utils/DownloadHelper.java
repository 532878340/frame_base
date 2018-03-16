package com.smart.frame.utils;

import android.text.TextUtils;

import com.smart.frame.manager.constants.Configs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 下载管理
 *
 * @author Gjm
 * @date 2018/3/16
 */
public class DownloadHelper {
    /**
     * 文件保存目录
     */
    public static final String DESTININATION_DIR = SystemUtil.getSDCardPath() + Configs.APP_DOWNLOAD_DIR;

    private OkHttpClient mOkHttpClient;

    /**
     * 保存网络请求
     */
    private Map<String, Call> mCallMap;

    private static final class Holder{
        private static final AtomicReference<DownloadHelper> INSTANCE = new AtomicReference<>();
    }

    public static DownloadHelper getInstance() {
        return Holder.INSTANCE.get();
    }

    private DownloadHelper(){
        mCallMap = new HashMap<>();
        mOkHttpClient = new OkHttpClient.Builder().build();
    }

    /**
     * 下载Url
     * @param downloadUrl
     */
    public void download(String downloadUrl, ResourceSubscriber subscriber){
        Flowable.just(downloadUrl)
                .filter(url -> !TextUtils.isEmpty(url) && !mCallMap.containsKey(downloadUrl))
                .flatMap(s -> Flowable.just(createDownloadInfo(downloadUrl)))
                .map(this::getRealFileName)
                .flatMap(downloadInfo -> Flowable.create(new DownloadSubscriber(downloadInfo), BackpressureStrategy.BUFFER))
                .compose(TransformUtils.flowableIOToMain())
                .subscribeWith(subscriber);
    }

    /**
     * 创建DownloadInfo
     * @param url
     * @return
     */
    private DownloadInfo createDownloadInfo(String url){
        DownloadInfo downloadInfo = new DownloadInfo();
        long contentLength = getContentLength(url);//获得文件大小
        downloadInfo.setTotalSize(contentLength);
        String fileName = url.substring(url.lastIndexOf("/"));
        downloadInfo.setFileName(fileName);
        return downloadInfo;
    }

    private DownloadInfo getRealFileName(DownloadInfo downloadInfo){
        String fileName = downloadInfo.getFileName();
        long downloadLength = 0, contentLength = downloadInfo.getTotalSize();
        File fileDir = new File(DESTININATION_DIR);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        File file = new File(DESTININATION_DIR, fileName);
        if (file.exists()) {
            //找到了文件,代表已经下载过,则获取其长度
            downloadLength = file.length();
        }
        //之前下载过,需要重新来一个文件
        int i = 1;
        while (downloadLength >= contentLength) {
            int dotIndex = fileName.lastIndexOf(".");
            String fileNameOther;
            if (dotIndex == -1) {
                fileNameOther = fileName + "(" + i + ")";
            } else {
                fileNameOther = fileName.substring(0, dotIndex)
                        + "(" + i + ")" + fileName.substring(dotIndex);
            }
            File newFile = new File(DESTININATION_DIR, fileNameOther);
            file = newFile;
            downloadLength = newFile.length();
            i++;
        }
        //设置改变过的文件名/大小
        downloadInfo.setProgressSize(downloadLength);
        downloadInfo.setFileName(file.getName());
        return downloadInfo;
    }

    /**
     * 取消下载请求
     * @param url
     */
    public void cancel(String url){
        Call call = mCallMap.get(url);
        if(call != null){
            call.cancel();
        }
    }

    private static final class DownloadInfo{
        /**
         * 获取进度失败
         */
        private static final long TOTAL_ERROR = -1;
        /**
         * 下载Url
         */
        private String url;
        /**
         * 文件总大小
         */
        private long totalSize;
        /**
         * 已下载大小
         */
        private long progressSize;
        /**
         * 文件名
          */
        private String fileName;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getTotalSize() {
            return totalSize;
        }

        public void setTotalSize(long totalSize) {
            this.totalSize = totalSize;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public long getProgressSize() {
            return progressSize;
        }

        public void setProgressSize(long progressSize) {
            this.progressSize = progressSize;
        }
    }

    private class DownloadSubscriber implements FlowableOnSubscribe<DownloadInfo> {
        private DownloadInfo mDownloadInfo;

        public DownloadSubscriber(DownloadInfo downloadInfo) {
            this.mDownloadInfo = downloadInfo;
        }

        @Override
        public void subscribe(FlowableEmitter<DownloadInfo> e) throws Exception {
            String url = mDownloadInfo.getUrl();
            long downloadLength = mDownloadInfo.getProgressSize();
            long contentLength = mDownloadInfo.getTotalSize();

            e.onNext(mDownloadInfo);

            Request request = new Request.Builder()
                    //确定下载的范围,添加此头,则服务器就可以跳过已经下载好的部分
                    .addHeader("RANGE", "bytes=" + downloadLength + "-" + contentLength)
                    .url(url)
                    .build();

            Call call = mOkHttpClient.newCall(request);
            mCallMap.put(url, call);//把这个添加到call里,方便取消
            Response response = call.execute();

            File fileDir = new File(DESTININATION_DIR);
            if(!fileDir.exists()){
                fileDir.mkdirs();
            }

            File file = new File(DESTININATION_DIR, mDownloadInfo.getFileName());
            InputStream is = null;
            FileOutputStream fileOutputStream = null;
            try {
                is = response.body().byteStream();
                fileOutputStream = new FileOutputStream(file, true);
                byte[] buffer = new byte[2048];//缓冲数组2kB
                int len;
                while ((len = is.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                    downloadLength += len;
                    mDownloadInfo.setProgressSize(downloadLength);
                    e.onNext(mDownloadInfo);
                }
                fileOutputStream.flush();
                mCallMap.remove(url);
            } finally {
                //关闭IO流
                CloseUtils.close(is,fileOutputStream);
            }
            e.onComplete();//完成
        }
    }

    /**
     * 获取下载长度
     *
     * @param downloadUrl
     * @return
     */
    private long getContentLength(String downloadUrl) {
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                long contentLength = response.body().contentLength();
                response.close();
                return contentLength == 0 ? DownloadInfo.TOTAL_ERROR : contentLength;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DownloadInfo.TOTAL_ERROR;
    }
}
