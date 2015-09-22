##mgcafe-android接入文档
================================

*SDK版本： Ver 1.1.0  
时间：2015-08-21

烦请cp方仔细阅读文档，每次更新的修改在文档最下方  

####`文档中的所有接口均为必须接入，请游戏方自行核查`
-----------------------------------------------
### 1、环境搭建  
  
- 请将SDK中`mgcafe`作为外部依赖library添加到工程的库依赖中。  
- 将SDK中在`mgcafeDemo/assets`目录下内容到复制到工程目录`ssets`中,并确保文件`ztsdk_config.properties`中有以下内容:

~~~xml
config.sdk.class=com.mztgame.mgcafe.ZTLibMgcafe

config.domain.passport.legacy=http://passport.mztgame.com
config.domain.passport=http://other.passport.mobileztgame.com
config.domain.pay=http://pay.mobileztgame.com

config.appid=10210
config.appkey=be2750459900795c4d4f06144733d126

#config.promoterid=0
~~~

> `说明：``config.appid`为登录使用的appid，`config.appkey`为登录使用的appkey，由手游咖啡渠道提供,`config.promoterid`为推广员id，出母包时请务必将该行使用`#`注释，其他配置请务修改。

- 配置工程项目的AndroidManifest.xml文件，

所需权限

~~~xml
<uses-permission android:name="android.permission.WRITE_SETTINGS" />
<uses-permission android:name="android.permission.CALL_PHONE" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
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

Activity注册

~~~xml
<application
android:allowBackup="true"
android:label="@string/app_name"
android:icon="@drawable/ic_launcher"
android:theme="@style/AppTheme" >
       
<activity
    android:name="com.mgcafe.loginappsdk.LoginActivity"
    android:configChanges="orientation|screenSize|keyboardHidden"
    android:windowSoftInputMode="adjustResize"
    android:theme="@android:style/Theme.Translucent.NoTitleBar"
    android:screenOrientation="landscape">
</activity>

<activity
    android:name="com.ztgame.websdk.payment.ui.WebPayActivity"
    android:configChanges="orientation|keyboardHidden|screenSize"
    android:screenOrientation="portrait"
    android:theme="@android:style/Theme.NoTitleBar" >
</activity>
<activity
    android:name="com.alipay.android.app.sdk.WapPayActivity"
    android:screenOrientation="landscape" >
</activity>
<activity
    android:name="com.ztgame.websdk.payment.wxapi.WXPayEntryActivity"
    android:exported="true"
    android:launchMode="singleTop" />
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
</application>
~~~

> `注意：`LoginActivity的属性`android:screenOrientation`需要跟工程主Activity的方向一直，需游戏自行修改。

###2、IZTListener SDK事件回调接口（必接）
IZTListener接口实现onFinish()方法

~~~java
/**
*初始化、登录、支付回调接口中需要实现的方法
*
*@param what 返回事件的类型。 e.g. ZTConsts.ZTGAME_LOGIN, ZTConsts.ZTGAME_PAY etc.
*@param errcode 返回事件的错误码. e.g. ""
*@param json_obj 返回的Json数据对象。e.g.
*    {"mobile_type":"xxxx","token":"c814684cbf4f17e2dd0c169db997db7f","accid":"86-xxxx",
*	"imei":"xxxx","action":"login","account":"默认账号","mac":"xxxx","channel":86,"ip":"xxxx"}
*/
public void onFinished(int what, int errcode, JSONObject json_obj);
~~~

> 该Json可以作为测试登录进入游戏的测试参数。接收到ZTGAME_LOGIN消息用于登录成功。其中accid和account 分别为：用户标示和账号,其中account统一处理为`默认帐号`。
   		
   **what参数枚举**：
   	  
#####ZTGAME_INIT  
  ZTGAME_INIT是SDK初始化完成的通知，所有接口都需要在INIT完成之后才能开始调用**    
  	
#####ZTGAME_LOGIN  
  ZTGAME_LOGIN需要处理登录返回的json_obj参数，交给游戏服务器进行效验
    
#####ZTGAME_PAY   
  ZTGAME_PAY只需要判断errcode为0成功还是-1失败，但是是否到账要以服务器通知为准
    
#####ZTGAME_QUIT
  ZTGAME_QUIT是游戏进行销毁操作的地方，玩家在第三方退出框点击确认后会收到此回调
  		
#####ZTGAME_LOGOUT
  ZTGAME_LOGOUT是游戏进行登出操作/切换账号操作的地方，需要返回到游戏登录界面等待用户再次登录

#####ZTGAME_QQGROUP
  ZTGAME_QQGROUP是游戏中一键增加QQ群的消息，游戏收到该消息后，当errcode为0表示有一键加QQ功能，其他表示没有该功能

> `注：在游戏接收ZTGAME_LOGOUT后，调用登录接口之前前，建议调用isLogin接口做一次用户登录状态判断，如果true则不调用登录接口，否则调用。增加判断可防止多次弹出登录界面。`**
###3、SDK 接入

- 1.1 初始化SDK接口（必接）
首先调用IZTLibBase 的newInstance方法，传入你的Activity。然后调用getInstance()方法的initZTGame初始化游戏信息。  

~~~java
/**
*实例化方法
*
*@param ac 传入的Activity对象
*/
synchronized static public boolean newInstance(Activity ac);

/**
*初始化游戏信息
*
*@param gameId 传入的游戏ID。 e.g. 5010;
*@param appName 传入的游戏名称。 e.g. 乱炖英雄。
*@param isLandScape 是否为横版。 e.g. true;  
*@param listener 用于响应回调事件的监听接口。 e.g. mListener. 
*/
public void initZTGame(String gameId,String appName, boolean isLandScape, IZTListener listener)
~~~

> `注：`initZTGame方法中的设置横竖屏的参数对当前渠道设置无效。
调用实例：

~~~java
IZTLibBase.newInstance(LoginActivity.this);
IZTLibBase.getInstance().initZTGame("5000","乱炖英雄", false, mListener);
~~~

- 1.2 登录操作（必接）

~~~java
/*
*SDK登录接口
*@param zoneId 服务器分区ID 
*@param zoneName 服务器分区名称  
*@param isAutoLogin 是否自动登录，传true即可
*
*/
public void loginZTGame(final String zoneId, final String zoneName, final boolean isAutoLogin)	
~~~

调用实例：

~~~java
IZTLibBase.getInstance().loginZTGame("服务器id","服务器名",true）
~~~

- 1.3 支付操作（必接）

~~~java
/**
*SDK支付方法
*@param payInfo 支付对象
*/
final synchronized public boolean payZTGame(ZTPayInfo payInfo)
~~~

#####ZTPayInfo支付信息接口：

~~~java
public void setProductName(String productName)	//设置商品名称
public void setExtra(String extra)	//设置额外信息
public void setAmount(int amount)	//设置商品价格，单位为分
public void setProductName(String productName)	//设置商品名称
public void setMoneyName(String moneyName)	//设置货币名称
public void setExchangeRatio(int exchangeRatio)	//设置汇率比例
~~~		
	        
**`说明`**  
`setAmount(int amount):`设置支付的价格，单位为分  
`setProductName(String)`:设置商品名称，如果是钻石就设置为`“钻石*数量”`，如果是月卡就设置为`月卡`  
`setExtra(String)`:设置额外参数  
`payZTGame（ZTPayInfo payInfo）`:支付。   
`setProductId(String)` :部分运营商需要提供计费点通过此接口传入计费点。			`payInfo.setMonthCard(true);`:部分渠道需要得知是否道具是月卡

调用实例：

~~~java
ZTPayInfo payInfo = new ZTPayInfo();
payInfo.setAmount(100);//必传
payInfo.setProductName("钻石*10");//必传
payInfo.setProductId("123");
payInfo.setExtra("1");         // 扩展信息，需要回传游戏服务器的请设置这个字段
payInfo.setMonthCard(true);    //Base4.4.1以上版本需要设置此参数用来表示是否为月卡
IZTLibBase.getInstance().payZTGame(payInfo);
~~~

- 1.4 登录完成数据统计接口（必接）
~~~java
/**
*
*@param roleId 角色ID
*@param roleName 角色名称
*@param roleLevel 角色等级
*@param zoneId 服务器ID
*@param zoneName 服务器名称
*/
public void loginOkZTGame(String roleId,String roleName,String roleLevel,String zoneId,String zoneName)
~~~

- 1.5 创建角色数据统计接口（必接）

~~~java
/**
*
*@param roleId 角色ID
*@param roleName 角色名称
*@param roleLevel 角色等级
*@param zoneId 服务器ID
*@param zoneName 服务器名称
*/
public void createRoleZTGame(String roleId,String roleName,String roleLevel,String zoneId,String zoneName)
~~~


- 1.6 角色等级升级信息接口

~~~java
/**
*角色升级
*@param roleId 角色ID
*@param roleName 角色名称
*@param level 角色等级
*@param zoneId 服务器ID
*@param zoneName 服务器名称
*/
public void roleLevelUpZTGame(String roleId,String roleName,String zoneId,String zoneName,int level)
~~~


- 1.7 是否需要切换账号按钮接口

~~~java
/**
*判断是否有切换帐号接口
*@return boolean 返回true则游戏需要添加一个切换账号按钮以方便用户切换账号，点击此按钮后调用switchAccountZTGame()
*/
public boolean isHasSwitchAccountZTGame()
~~~


- 1.8 切换账号操作

~~~java
/**
*切换账号接口功能，调用此接口执行切换账号操作
*/
public void switchAccountZTGame()	
~~~
		

- 1.9 开启日志输出接口

~~~java
/**
*游戏上线前需要注释该行代码以关闭日志输出
*/
public void enableDebugMode()
~~~

- 1.10 更新服务器id

~~~java
/**
*玩家切换服务器后需要调用此方法更新当前服务器id
*@param zoneId 区id
*/
public void setZoneId(final String zoneId)
~~~

	
- 1.11 更新当前活动Activity

~~~java
/**
*如果游戏当前Activity变更，需要更新activity到SDK
*@param activity
*/
public void setActivity(Activity activity)
~~~

- 1.12 是否已经登录

~~~java
/**
*判断是否登录
*/
public boolean isLogined()
~~~

##需要调用的Android生命周期接口

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
	IZTLibBase.delInstance();
}

@Override
protected void onStart() {
    super.onStart();
    IZTLibBase.getInstance().onStartZTGame();
}

@Override
protected void onRestart() {
    super.onRestart();
    IZTLibBase.getInstance().onRestartZTGame();
}
@Override
public void onConfigurationChanged(Configuration newConfig) {
	super.onConfigurationChanged(newConfig);
	IZTLibBase.getInstance().onConfigurationChangedZTGame(newConfig);
}

@Override
protected void onNewIntent(Intent intent) {
	super.onNewIntent(intent);
	IZTLibBase.getInstance().onNewIntentZTGame(intent);
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	IZTLibBase.getInstance().onActivityResultZTGame(requestCode, resultCode, data);
}

@Override
public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    IZTLibBase.getInstance().onSaveInstanceState(outState);
}
~~~
 
####***以上为渠道需要在游戏主Activity中调用的生命周期函数。***####

------------------------------------------------------------



