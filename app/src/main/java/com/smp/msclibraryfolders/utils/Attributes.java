package com.smp.msclibraryfolders.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;

import com.smp.msclibraryfolders.R;

/**
 * Created by steve on 1/8/18.
 */

public class Attributes {
    public static int fetchAccentColor(Context context) {
        TypedValue typedValue = new TypedValue();

        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorAccent});
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }

    public static int getPrimaryColor(Context activity)
    {
        TypedValue typedValue = new TypedValue();
        activity.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }
    public static int getPrimaryTextColor(Context activity)
    {
        TypedValue typedValue = new TypedValue();
        activity.getTheme().resolveAttribute(android.R.attr.textColorPrimary, typedValue, true);
        return typedValue.data;
    }
}
