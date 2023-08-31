package com.dashingqi.base.application;

import android.app.Application;
import android.util.Log;

import com.dashingqi.base.providers.application.IApplicationProvider;
import com.orhanobut.logger.Logger;

/**
 * @author : zhangqi
 * @time : 2020/5/20
 * desc :
 */
public class ApplicationController {

    public static boolean mIsDebug;

    public static void init(Application application, boolean isDebug) {
        mIsDebug = isDebug;
        for (IApplicationProvider provider : ApplicationManager.apps) {
            provider.init(application);
        }
    }

    public static void transformOnCreate() {
        Logger.d("transformOnCreate");
        for (IApplicationProvider provider : ApplicationManager.apps) {
            provider.onCreate();
        }
    }

    public static void transformOnLowMemory() {
        for (IApplicationProvider provider : ApplicationManager.apps) {
            provider.onLowMemory();
        }
    }
}
