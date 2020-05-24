package com.dashingqi.base.base.viewmodel;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

/**
 * @author : zhangqi
 * @time : 2020/5/24
 * desc :
 */
public class ParamViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    Object[] param;
    Class[] paramClass;

    /**
     *
     * @param param
     */
    public ParamViewModelFactory(Object... param) {
        this.param = param;
        paramClass = new Class[param.length];
        for (int i = 0; i < param.length; i++) {
            paramClass[i] = param[i].getClass();
        }
    }
    /**
     *
     * @param param
     */
    public ParamViewModelFactory(Class[] paramClass, Object[] param) {
        this.param = param;
        this.paramClass = paramClass;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(paramClass).newInstance(param);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return super.create(modelClass);
    }
}
