巨人官方SDK4.X接入文档（5.0不适用）
=================

version： 4.1.0

## 接入前准备工作  

- 将工程依赖sdk中的giant工程
- 在游戏工程的Androidmenifest.xml中添加如下权限：

~~~
<uses-permission android:name="android.permission.WRITE_SETTINGS" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.CALL_PHONE" />    
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
<uses-permission android:name="android.permission.GET_TASKS" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.SEND_SMS" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.RECEIVE_SMS" />
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
~~~

- Application节点中添加如下字段：

~~~
android:name="com.ztgame.ztgameframework.ZTApplication"
~~~

> 注意：倘若游戏需要有自己的Application节点需要继 `com.ztgame.ztgameframework.ZTApplication`			
> 并且在自己的Application的onCreate方法中调用 `super.onCreate()`

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
        android:name="com.ztgame.mobileappsdk.ga.LoginActivity"
        android:configChanges="orientation|screenSize|keyboardHidden"
        android:windowSoftInputMode="adjustResize"
        android:theme="@android:style/Theme.Translucent.NoTitleBar"
        android:screenOrientation="landscape">
    </activity>
    <activity
        android:name="com.ztgame.websdk.payment.ui.WebPayActivity"
        android:configChanges="orientation|keyboardHidden"
        android:screenOrientation="landscape"
        android:theme="@android:style/Theme.NoTitleBar" >
    </activity>
   <activity
        android:name="com.alipay.android.app.sdk.WapPayActivity"
        android:screenOrientation="landscape" >
    </activity>
    <!-- 全民付 -->
    <activity
        android:name="com.hxcr.umspay.activity.Initialize"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.swipe.SwipeCardActivity"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_activity_mydialog22" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.swipe.SelectDriverActivity"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_activity_mydialog" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.UmsShuaPayActivity"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.UmsPayResultYouActivity"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.UmsPayResultWuActivity"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.ModifyPhone"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.user.adpater.UmsUser"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.user.adpater.UmsChangePs"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.banklist.adpater.UmsBankListActivity"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.order.adpater.UmsOrderListActivity"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.UmsInputPayActivity"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.UmsRegActivity"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.MainActivity"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_Myanimation"
        android:windowSoftInputMode="adjustPan" >
    </activity>
    
    
    
    <activity
        android:name="com.hxcr.umspay.activity.UmsUserMode"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.more.adpater.UmsMore"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.SustainBankList"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.Other"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_activity_mydialog" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.UmsForGetPs"
        android:screenOrientation="landscape"
        android:theme="@style/umspay_Myanimation" >
    </activity>
    <activity
        android:name="com.hxcr.umspay.activity.WebViewActivity"
        android:screenOrientation="landscape"
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
	
### 绑定生命周期  

~~~java
	@Override
    protected void onPause() {
        super.onPause();
        IZTLibBase.getInstance().onPauseZTGame();
    }

    @Override
    protected void onStop() {
        super.onStop();
        IZTLibBase.getInstance().onStopZTGame();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IZTLibBase.getInstance().onResumeZTGame();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IZTLibBase.getInstance().destroyZTGame();
    }
~~~


### 登录接口

~~~java
/**
 *
 * @param zoneId       登录的区服号码
 * @param zoneName     登录的区服名称  
 * @param isAutoLogin  是否自动登录
 */
public void loginZTGame(final String zoneId, final String zoneName, final boolean isAutoLogin)
~~~

- 登录接口返回：

~~~json
{
    "code":0,
    "entity": {
        "openid":"1-123456",
        "account":"XXXXXXXX"
    },
    "token":"bbe7e46de2c7d3ace036cea155b23978"
}
~~~

游戏客户端拿到此登录返回数据需要拿entity中的openid和token去巨人平台服务端登录验证。具体参见服务端文档。

### 事件回调监听器：

~~~java
		private IZTListener mListener = new IZTListener() {
        @Override
        public void onFinished(int what, int errcode, JSONObject json_obj) {
        switch (what) {
			case ZTConsts.ZTGAME_LOGIN:
				// Do your Login logic here
				break;
			case ZTConsts.ZTGAME_INIT:
				// Do your init logic here
				break;
			case ZTConsts.ZTGAME_PAY:
				// handle the post payment event here.
				break;
			case ZTConsts.ZTGAME_QUIT:
				// quit your game here				
				System.exit(0);
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

~~~java
	if (IZTLibBase.getInstance().isLogined()) {
        // 订单信息
    	IZTLibBase.getInstance().setZoneId("1");	//支付需要提供区服号码 不可写死
        ZTPayInfo payInfo = new ZTPayInfo();
        payInfo.setAmount(100);		//金额 单位为分
        payInfo.setExtra("");                          // 扩展信息，需要回传游戏服务器的请设置这个字段
        IZTLibBase.getInstance().payZTGame(payInfo);	//调用支付接口
    }
~~~

### 退出接口：

~~~
	IZTLibBase.getInstance().quitZTGame();
~~~
	
### 用户中心接口：

~~~
	IZTLibBase.getInstance().enterCenterZTGame();
~~~

### 切换账号接口

~~~
	IZTLibBase.getInstance().switchAccountZTGame();
~~~

## 接入建议	

新版sdk的登录流程优化：已登录的账号，再次登录不再弹出欢迎界面。建议游戏做如下处理

1. 已登录账户在游戏登录界面会自动登录，建议在游戏登录界面加一个调用用户中心的地方，方便用户注销、修改密码等操作（没有登陆不要调用）。
2. 建议在游戏设置内增加中户中心，方便用户在游戏内注销、修改密码等操作。
