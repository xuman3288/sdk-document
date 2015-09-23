巨人官方SDK支付接入文档
=================

version： 4.1.0

## 接入前准备工作  

- 将工程依赖sdk中的giant工程
- 在游戏工程的Androidmenifest.xml中添加如下权限：

~~~
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

- 将demo中的assets下的内容拷贝到游戏工程的assets下面其中 `ztsdk_config.properties` 为

~~~
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

config.appid=5016   ###5016 为游戏的gameid
config.paykey=p0emh5SFwBvpDbT		###此处为平台申请的payKey
config.paypid=10					###此处为平台申请的paypid

config.channel_id=1
#config.ad_id=10000000000
~~~
	
- AndroidManifest.xml中添加一下Activity：（参考demo）

~~~
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

- 在活动Activity中的`onCreate()`的方法中添加如下代码

~~~
	IZTLibBase.newInstance(MainActivity.this);
    IZTLibBase.getInstance().enableDebugMode();
    IZTLibBase.getInstance().initZTGame("5010","乱炖英雄", true, mListener);
~~~

用来初始化sdk的信息

其中参数原型为：`IZTLibBase.newInstance(Activity act);`

`Activity act`:为游戏的活跃Activity

> `IZTLibBase.getInstance().enableDebugMode();`
> 此方法为激活SDK的调试模式 注意上线之后必须注释掉此行代码。

~~~java
/**
 *
 * @param gameId       为巨人平台申请到GameID;
 * @param appName      为游戏名称  
 * @param isLandScape  sdk横竖版
 * @param listener     事件回调监听器
 */
public void initZTGame(String gameId,String appName, boolean isLandScape, IZTListener listener)
~~~


### 事件回调监听器：

~~~java
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
~~~

其中 what为消息的类型：

~~~java
public static final int ZTGAME_LOGIN = 0x01;	//登录消息 errocode非零为失败
public static final int ZTGAME_PAY = 0x03;  //支付消息 errocode非零为失败
public static final int ZTGAME_QUIT = 0x04;  //退出消息 errocode非零为失败
public static final int ZTGAME_LOGOUT = 0x07;  //切换账号消息 errocode非零为失败
public static final int ZTGAME_INIT = 0x08;  //初始化消息 errocode非零为失败
~~~

### 支付接口：

当用户点击充值商品时调用 `IZTLibBase.getInstance().payZTGame(ZTPayInfo payInfo)`

#### 代码范例: 

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
        //玩家点击购买商品按键
        switch (v.getId()) {
            case R.id.buyButton:
				//支付前请务必调用该行代码设置登录用户的userid.
				IZTLibBase.getUserInfo().set(ZTConsts.User.ACCID, userid);
				//设置区后请调用该方法设置zoneid.
				IZTLibBase.getInstance().setZoneId(zoneId);
                if(IZTLibBase.getInstance().isLogined()){
                    ZTPayInfo payInfo = new ZTPayInfo();
                    payInfo.setAmount(100);  //设置金额, 单位(分)
                    payInfo.setProductName("test item"); //设置商品名称
                    payInfo.setProductId("1001"); // 设置商品ID
                    payInfo.setExtra("1"); //设置游戏订单扩展信息
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

> `注：`由于调用支付接口中判断了登录状态，所以在调用支付接口时，请务必使用IZTLibBase.getUserInfo().set(ZTConsts.User.ACCID, userid)，设置登录的用户的userid。游戏在设置选区后，请调用IZTLibBase.getInstance().setZoneId(zoneId)设置zoneid信息。

#### 类 `com.ztgame.mobileappsdk.common.ZTPayInfo`'s 方法说明:

| Method | Required / Optional / Deprecated | Description |
|--------|----------------------------------|----------------------------|
| setAmount | 必填  | 设置商品金额(RMB). 单位(分) |
| setExtra | 必填 | 设置游戏订单扩展信息(游戏订单号等等)  |
| setMoneyName | 可选设置 | 设置货币单位名称  |
| setMonthCard | 可选设置 | 设置是否为月卡  |
| setExchangeRatio | 可选设置  | 设置价格比率 |
| setProductName | 可选设置  | 设置商品名称 |
| setProductId | 可选设置  | 设置商品ID |


