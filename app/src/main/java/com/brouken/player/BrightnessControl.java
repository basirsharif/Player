package com.brouken.player;

import android.app.Activity;
import android.util.Log;
import android.view.WindowManager;

class BrightnessControl {

    private Activity activity;

    public int currentBrightnessLevel = 15;

    public BrightnessControl(Activity activity) {
        this.activity = activity;
    }

    public float getScreenBrightness() {
        return activity.getWindow().getAttributes().screenBrightness;
    }

    public void setScreenBrightness(final float brightness) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.screenBrightness = brightness;
        activity.getWindow().setAttributes(lp);
    }

    public void changeBrightness(final boolean increase) {
        final int newBrightnessLevel = (increase ? currentBrightnessLevel + 1 : currentBrightnessLevel - 1);

        if (newBrightnessLevel < 0 || newBrightnessLevel > 30)
            return;

        currentBrightnessLevel = newBrightnessLevel;
        setScreenBrightness(levelToBrightness(newBrightnessLevel));
    }

    float levelToBrightness(final int level) {
        final double d = 0.064 + 0.936 / (double) 30 * (double) level;
        return (float) (d * d);
    }
}
