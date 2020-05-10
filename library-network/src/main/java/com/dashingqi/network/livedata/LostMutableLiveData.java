package com.dashingqi.network.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Method;

/**
 * @author : zhangqi
 * @time : 2020/5/10
 * desc :
 */
public class LostMutableLiveData<T> extends MutableLiveData<T> {

    public LostMutableLiveData() {
        super();
    }

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, new WrapperObserver<>(observer, this));
    }


    @Override
    public void observeForever(@NonNull Observer<? super T> observer) {
        super.observeForever(new WrapperObserver<>(observer, this));
    }

    private int reflectGetVersion() {
        try {
            Method method = LiveData.class.getDeclaredMethod("getVersion");
            method.setAccessible(true);
            return (int) method.invoke(this);
        } catch (Exception e) {
            throw new RuntimeException("LiveData has changed ,can't find method getVersion");
        }
    }

    static class WrapperObserver<T> implements Observer<T> {
        Observer<? super T> real;
        int version = 0;
        LostMutableLiveData liveData;

        WrapperObserver(Observer<? super T> real, LostMutableLiveData liveData) {
            this.real = real;
            version = liveData.reflectGetVersion();
            this.liveData = liveData;
        }

        @Override
        public void onChanged(T o) {
            if (version >= liveData.reflectGetVersion()) {
                return;
            }
            version++;
            real.onChanged(o);
        }
    }
}
