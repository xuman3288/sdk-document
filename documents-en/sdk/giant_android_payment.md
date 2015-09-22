GiantMobile SDK Only Payment Document
=====================================

version： 4.1.0

## Getting start

1. Add some permission to `Androidmenifest.xml`：

~~~xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.WRITE_SETTINGS" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.CALL_PHONE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
~~~

2. Copy `DemoProject/assets/*` to `GameProject/assets/*`. 
3. Modify `GameProject/assets/ztsdk_config.properties` file:

~~~ini
config.sdk.class=com.ztgame.mobileappsdk.ga.ZTLibGA

config.domain.passport.legacy=http://passport.mztgame.com
config.domain.passport=http://passport.mobileztgame.com
config.domain.pay=http://pay.mobileztgame.com

config.ui.transparentbg=1
config.ui.hidemibaolingpwd=0
config.ui.hidechangepwd=0
config.ui.hidefindpwd=0
config.ui.hideregpwd=0
config.ui.hideloginpwd=0
#config.ui.css=
#config.ui.css=.close{display:none;} .header{display:none !important;}
config.ui.css=#login_close{display:none;} 

config.pay.version=1.0.4

config.appid=5016                   ###5016 Set it GameId (Get from Giant developer)
config.paykey=p0emh5SFwBvpDbT       ###Set payment key    (Get from Giant developer)
config.paypid=10                    ###Set payment product id  (Get from Giant developer)

config.channel_id=1
#config.ad_id=10000000000
~~~

4. Add some activity tag in `AndroidManifest.xml` (Reference demo):

~~~xml
    <activity
        android:name="com.ztgame.websdk.payment.ui.WebPayActivity"
        android:configChanges="orientation|keyboardHidden|screenSize"
        android:screenOrientation="portrait"
        android:theme="@android:style/Theme.NoTitleBar" >
    </activity>
    <activity
        android:name="com.alipay.android.app.sdk.WapPayActivity"
        android:screenOrientation="portrait" >
    </activity>
    <!-- alipay sdk begin -->
    <activity
        android:name="com.alipay.sdk.app.H5PayActivity"
        android:configChanges="orientation|keyboardHidden|navigation"
        android:exported="false"
        android:screenOrientation="behind"
        android:windowSoftInputMode="adjustResize|stateHidden" >
    </activity>
    <!-- 全民付 -->
    <activity
        android:name="com.hxcr.umspay.activity.Initialize"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.swipe.SwipeCardActivity"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_activity_mydialog22" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.swipe.SelectDriverActivity"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_activity_mydialog" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.UmsShuaPayActivity"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.UmsPayResultYouActivity"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.UmsPayResultWuActivity"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.ModifyPhone"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.user.adpater.UmsUser"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.user.adpater.UmsChangePs"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.banklist.adpater.UmsBankListActivity"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.order.adpater.UmsOrderListActivity"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.UmsInputPayActivity"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.UmsRegActivity"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.MainActivity"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation"
        android:windowSoftInputMode="adjustPan" >
    </activity>

    <activity
        android:name="com.hxcr.umspay.activity.UmsUserMode"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.more.adpater.UmsMore"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.SustainBankList"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.Other"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_activity_mydialog" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.UmsForGetPs"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.WebViewActivity"
        android:screenOrientation="portrait"
        android:theme="@style/umspay_Myanimation" >
    </activity>
~~~

5. Add some code to `MainActivity.onCreate()`, and new A `IZTListener`.

~~~java
package com.example;

import android.app.Activity;
import android.os.Bundle;
import com.ztgame.mobileappsdk.common.IZTLibBase;
import com.ztgame.mobileappsdk.common.IZTListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Game init ...

        //Init GiantMobile SDK
        IZTLibBase.newInstance(MainActivity.this);
        IZTLibBase.getInstance().enableDebugMode(); // If you are debugging. Please make sure that the production environment is removed.
        IZTLibBase.getInstance().initZTGame("5010","Game Name", true, mListener);
    }

    private IZTListener mListener = new IZTListener() {
        @Override
        public void onFinished(int what, int errcode, JSONObject json_obj) {
            switch (what) {
            case ZTConsts.ZTGAME_INIT:
                // Do your init logic here
                break;
            case ZTConsts.ZTGAME_PAY:
                // handle the post payment event here.
                break;
            }
        }
    };
}
~~~

Enum `what` values:

~~~java
public static final int ZTGAME_LOGIN  = 0x01;   //Login event
public static final int ZTGAME_PAY    = 0x03;   //Payment event 
public static final int ZTGAME_QUIT   = 0x04;   //Exit event 
public static final int ZTGAME_LOGOUT = 0x07;   //Switch account event
public static final int ZTGAME_INIT   = 0x08;   //Init SDK event
~~~

6. Call payment method

Call `IZTLibBase.getInstance().payZTGame(ZTPayInfo payInfo)` when player clicked some product.

**For example:**

~~~java
package com.example;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.ztgame.mobileappsdk.common.IZTLibBase;
import com.ztgame.mobileappsdk.common.ZTConsts;
import com.ztgame.mobileappsdk.common.ZTPayInfo;

public class MainActivity extends Activity {
    @Override
    public void onClick(View v) {
        //Player touch a buy product button
        switch (v.getId()) {
            case R.id.buyButton:
                //Note:
                //Please make sure set a `user_id` and a `zone_id` before call payment method.
                IZTLibBase.getUserInfo().set(ZTConsts.User.ACCID, userid);
                //Set player zone id.
                IZTLibBase.getInstance().setZoneId(zoneId);

                if(IZTLibBase.getInstance().isLogined()){
                    ZTPayInfo payInfo = new ZTPayInfo();
                    payInfo.setAmount(100);  //Set RMB
                    payInfo.setProductName("test item"); // Set product name.
                    payInfo.setProductId("1001"); // Set product id.
                    payInfo.setExtra("1"); // Set developer payload.
                    IZTLibBase.getInstance().payZTGame(payInfo);
                } else {
                    Toast.makeText(MainActivity.this, "Please login.", Toast.LENGTH_SHORT).show();
                }
                break;
            //...
        }
    }
}
~~~

**Class `com.ztgame.mobileappsdk.common.ZTPayInfo`'s methods description**:

| Method           | Required / Optional | Description                         |
|------------------|---------------------|-------------------------------------|
| setAmount        | Required            | Set product amount (RMB).           |
| setExchangeRatio | Optional            | Set RMB to game coin exchange ratio |
| setExtra         | Required            | Set developer payload.              |
| setMoneyName     | Optional            | Set game coin name.                 |
| setMonthCard     | Optional            | Set product type is card.           |
| setProductName   | Optional            | Set product name.                   |
| setProductId     | Optional            | Set product id.                     |