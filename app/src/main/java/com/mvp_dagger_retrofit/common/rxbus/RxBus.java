package com.mvp_dagger_retrofit.common.rxbus;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * rxbus可以代替eventbus，是单例的
 * courtesy: https://gist.github.com/benjchristensen/04eef9ca0851f3a5d7bf
 */
@Singleton
public class RxBus {

    //private final PublishSubject<Object> mRXBus = PublishSubject.create();

    // If multiple threads are going to emit events to this
    // then it must be made thread-safe like this instead
    private final Subject mRXBus = new SerializedSubject(PublishSubject.create());

    @Inject
    public RxBus(){

    }

    public void send(Object event) {
            mRXBus.onNext(event);
    }

    public <T> Observable<T> toObserverable(Class<T> tClass) {

       return mRXBus.ofType(tClass);

    }

    public boolean hasObservers() {
        return mRXBus.hasObservers();
    }
}