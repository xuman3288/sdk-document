```java
package com.example;

import android.app.Activity;
import com.ztgame.mobileappsdk.common.IZTLibBase;

public class UnityActivity extends Activity {
    @Override
    protected void onCreate() {
        super.onCreate();

        IZTLibBase.newInstance(this);
    }
   IZTLibBase.newInstance(this);
    @Override
    protected void onPause() {
        super.onPause();

        IZTLibBase.getInstance().onPauseZTGame();//必接
    }

    @Override
    protected void onStop() {
        super.onStop();
        IZTLibBase.getInstance().onStopZTGame();//必接
    }

    @Override
    protected void onResume() {
        super.onResume();
        IZTLibBase.getInstance().onResumeZTGame();//必接
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IZTLibBase.getInstance().destroyZTGame();//必接
        IZTLibBase.delInstance();//必接
    }

    @Override
    protected void onStart() {
        super.onStart();
        IZTLibBase.getInstance().onStartZTGame();//必接
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        IZTLibBase.getInstance().onRestartZTGame();//必接
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        IZTLibBase.getInstance().onConfigurationChangedZTGame(newConfig);//必接
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        IZTLibBase.getInstance().onNewIntentZTGame(intent);//必接
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IZTLibBase.getInstance().onActivityResultZTGame(requestCode, resultCode, data);//必接
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        IZTLibBase.getInstance().onSaveInstanceState(outState);//必接
    }
}
```
