package com.diabin.latte.app;

import android.content.Context;

/**
 * Created by 傅令杰 on 2017/3/29
 */

public final class Latte {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return Configurator.getInstance().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return Configurator.getInstance()
                .getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }
}