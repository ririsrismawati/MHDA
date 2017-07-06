package com.sttb.mobileprogramming.mengenalhurufdanangka;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * @author Riris Rismawati <riris@urbanindo.com>
 * @since 2017.07.05
 */
public class Applications extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/KidZone.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        //....
    }
}
