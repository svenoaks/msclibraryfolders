package com.smp.msclibraryfolders.library.image;

import android.content.Context;

/**
 * Created by steve on 1/8/18.
 */

public class AudioCover {
    final String path;
    final Context context;
    public AudioCover(Context context, String path) {
        this.path = path;
        this.context = context.getApplicationContext();
    }
}