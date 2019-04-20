package com.mission.mystore;

import android.content.Context;
import android.util.DisplayMetrics;

public class Utility {


    public static int getNumOfColumns(Context context, float columnWidthDp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        columnWidthDp = 10 + (columnWidthDp / displayMetrics.density);
        return (int)(screenWidthDp / columnWidthDp+ 0.5); // +0.5 for correct rounding to int.
    }
}
