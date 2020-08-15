package com.yourcard.share;


import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.yourcard.language.Language;
import com.yourcard.share.TypefaceUtil;

import java.util.Locale;


public class App extends MultiDexApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(Language.updateResources(base,Language.getLanguage(base)));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.setDefaultFont(this, "DEFAULT", "fonts/Hacen_Saudi_Arabia.ttf");
        TypefaceUtil.setDefaultFont(this, "MONOSPACE", "fonts/Hacen_Saudi_Arabia.ttf");
        TypefaceUtil.setDefaultFont(this, "SERIF", "fonts/Hacen_Saudi_Arabia.ttf");
        TypefaceUtil.setDefaultFont(this, "SANS_SERIF", "fonts/Hacen_Saudi_Arabia.ttf");

    }
}

