package com.dashingqi.base.application;

import android.util.Log;

import com.dashingqi.base.providers.application.IApplicationProvider;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhangqi
 * @time : 2020/5/20
 * desc :
 */
class ApplicationManager {

    public static List<IApplicationProvider> apps = new ArrayList<>();

    static {
        Log.w("ApplicationManager", apps.toString());
    }

    public static void register(IApplicationProvider provider) {
        Log.w("ApplicationManager", provider.toString());
        apps.add(provider);
    }
}
