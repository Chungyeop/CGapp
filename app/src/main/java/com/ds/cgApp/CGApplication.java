package com.ds.cgApp;

/* Build MiCom Test - General Application -> Error
import com.dseltec.micom.mode.MicomModeConsts;
import com.dseltec.micom.mode.MicomModeListener;
import com.dseltec.micom.mode.MicomModeManager;
*/

import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class CGApplication extends Application {

    private static final String TAG = "CGApplication";
    private static final boolean LOGD = true;

/* Build MiCom Test - General Application -> Error
    private MicomModeManager mModeManager = null;
    public boolean bluetoothPlayingState = false;
*/

    @Override
    public void onCreate() {
        super.onCreate();
    /* Build MiCom Test - General Application -> Error
        InitialMiComModeManager();
    */
    }

    /* MiCom Listener */
    /* Build MiCom Test - General Application -> Error
    private MicomModeListener mMiComModesListener = new MicomModeListener() {
        @Override
        public void onVideoFormat(int format) {
        }
        @Override
        public void onMiComMode(int miComMode) {
            Log.v(TAG, "MiCom Connect Check");
            Log.v(TAG, "MiCom Mode = " + miComMode);

            if (LOGD) {
                Log.v(TAG, "CGApp Bluetooth Check = " + bluetoothPlayingState);
            }
        }
    };
    */

    /* MiCom Initial */
    /* Build MiCom Test - General Application -> Error
    public void InitialMiComModeManager() {
        mModeManager = new MicomModeManager(this);
        mModeManager.addListener(mMiComModesListener);
        mModeManager.getMicomMode();
        mModeManager.getAuxStatus();
    }
    */

    /* Static Method */
    public void moveToBackGround() {
        if (LOGD) {
            Log.d(TAG, "moveToBackGround()");
        }
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void moveToHome() {
        if (LOGD) {
            Log.d(TAG, "moveToHome()");
        }
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void moveToClockMain() {
        if (LOGD) {
            Log.d(TAG, "moveToClockMain()");
        }
        Intent intent = new Intent(getApplicationContext(), ClockMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
