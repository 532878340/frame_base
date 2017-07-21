package com.frame.manager.rxbus;


//import rx.Observable;

/**
 * Created by Zijin on 2017/7/13.
 */

public class RxBus {
//    private final Subject bus;
//
//    public static RxBus getDefault() {
//        return RxBusInstance.INSTANCE;
//    }
//
//    private final static class RxBusInstance{
//        private static final RxBus INSTANCE = new RxBus();
//    }
//
//    public RxBus() {
//        //把线程非安全的PublishSubject包装成线程安全的Subject(SerializedSubject)
//        bus = new SerializedSubject<>(PublishSubject.create());
//    }
//
//    // 根据传递的 eventType 类型返回特定类型(eventType)的 Observable
//    public <T> Observable<T> register(Class<T> eventType){
//        return bus.ofType(eventType);
//    }
//
//    // 提供了一个新的事件用于发送,这时候的Subject是一个观察者
//    public void post(Object obj){
//        bus.onNext(obj);
//    }
}
