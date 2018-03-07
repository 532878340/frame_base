package com.smart.frame.bus;

import com.orhanobut.logger.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * RxBus 事件总线
 * 1、事件总线，解耦
 * 2、异常处理
 * 3、允许发送粘性事件
 *
 * @author Gjm
 * @date 2018/3/7
 */
public class RxBus {
    private final Subject<Object> mBus;
    private final Map<Class<?>,Object> mStickyEventMap;

    private RxBus(){
        mBus = PublishSubject.create().toSerialized();
        mStickyEventMap = new ConcurrentHashMap<>();
    }

    private static final class Holder{
        private static final RxBus INSTANCE = new RxBus();
    }

    public static RxBus getInstance(){
        return Holder.INSTANCE;
    }

    /**
     * 发送事件
     */
    public void post(Object o){
        mBus.onNext(o);
    }

    /**
     * 观察者监听 处理异常
     */
    public <T> Observable<T> toObservable(Class<T> eventType){
        return mBus.ofType(eventType)
                .flatMap(t -> Observable.just(t)
                .doOnError(throwable -> Logger.e("RxBus处理异常: " + throwable.getMessage()))
                .onErrorResumeNext(Observable.never()));
    }

    /**
     * 发送粘性事件
     */
    public void postSticky(Object o){
        synchronized (mStickyEventMap){
            mStickyEventMap.put(o.getClass(),o);
        }
        post(o);
    }

    /**
     * 粘性事件观察者监听
     */
    public <T> Observable<T> toObservableSticky(Class<T> eventType){
        synchronized (mStickyEventMap){
            Observable<T> observable = toObservable(eventType);
            final Object event = mStickyEventMap.get(eventType);
            if(event != null){
                return observable.mergeWith(Observable.create(e -> e.onNext(eventType.cast(event))));
            }else{
                return observable;
            }
        }
    }

    /**
     * 获取粘性事件
     */
    public <T> T getStickyEvent(Class<T> eventType){
        synchronized (mStickyEventMap){
            return eventType.cast(mStickyEventMap.get(eventType));
        }
    }

    /**
     * 移除粘性事件
     */
    public <T> T removeStickyEvent(Class<T> eventType){
        synchronized (mStickyEventMap){
            return eventType.cast(mStickyEventMap.remove(eventType));
        }
    }

    /**
     * 移除所有粘性事件
     */
    public void removeAllStickyEvent(){
        synchronized (mStickyEventMap){
            mStickyEventMap.clear();
        }
    }
}
