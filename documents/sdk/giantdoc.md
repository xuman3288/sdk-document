巨人官方SDK5.X接入文档
=================

version： 5.2.3

## 接入前准备工作  

- 将工程依赖sdk中的NewGiant工程
	
	或者拷贝资源：
 
	- NewGiant/libs目录下的jar包： base.jar、acra.jar、ztgameframework.jar、libammsdk.jar、newgiant.jar、alipaySdk-20160809.jar、giantpaysdklib.jar
	- NewGiant/res目录下的所有资源文件
- 在游戏工程的Androidmenifest.xml中添加如下权限：

    	<uses-permission android:name="android.permission.WRITE_SETTINGS" />
    	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    	<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    	<uses-permission android:name="android.permission.INTERNET" />
    	<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    	<uses-permission android:name="android.permission.READ_PHONE_STATE" />
    	<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
    	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    	<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    	<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    	<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />


- Application节点中添加如下字段：

		android:name="com.ztgame.ztgameframework.ZTApplication"


 	注意：倘若游戏需要有自己的Application节点需要继 `com.ztgame.ztgameframework.ZTApplication`			

 	并且在自己的Application的onCreate方法中调用 `super.onCreate()`

- 将demo中的assets下的内容拷贝到游戏工程的assets下面其中 `ztsdk_config.properties` 为
    	
		config.sdk.class=com.ztgame.giant.ZTLibGiant
    	config.domain.passport.ztgame = http://passport-api.sdk.mztgame.com
    	config.domain.passport.legacy=http://passport.mztgame.com
    	config.domain.passport=http://passport.mobileztgame.com
    	config.domain.pay=http://pay.mobileztgame.com    
    	config.forumurl=http://ztkd.bbs.mztgame.com/
    	config.gifts_url=http://www.baidu.com
    	config.help_url=http://www.baidu.com
    	config.showmobilelogin=#showmblogin# 
    	config.isFreeAutoLogin=1
    	config.sdk_version = 5.0
    	config.channel_id=1
    	#config.ad_id=22222
	
- AndroidManifest.xml中添加一下Activity：（参考demo）

    	<activity
    		android:name="com.example.giantdemo.MainActivity"
    		android:configChanges="orientation|screenSize|keyboardHidden"
    		android:launchMode="singleTask">
    		<intent-filter>
    			<action android:name="android.intent.action.MAIN" />
    			<category android:name="android.intent.category.LAUNCHER" />
    		</intent-filter>
    	</activity>
    	<activity
    		android:name="com.ztgame.giant.ZTGameMainActivity"
    		android:configChanges="orientation|keyboardHidden|navigation"
    		android:theme="@style/giant_main_dialog"
    		android:windowSoftInputMode="adjustResize" >
    	</activity>
    	<activity
    		android:name="com.ztgame.giant.ZTGameForgetPwActivity"
    		android:configChanges="orientation|keyboardHidden|navigation"
    		android:screenOrientation="behind"
    		android:windowSoftInputMode="adjustResize" >
    	</activity>
    	<activity
    		android:name="com.mztgame.plugin.GiantFloatWindowActivity"
    		android:configChanges="orientation|screenSize|keyboardHidden"
    		android:screenOrientation="portrait" />
    	<activity
    		android:name="com.ztgame.websdk.payment.ui.WebPayActivity"
    		android:configChanges="orientation|keyboardHidden|screenSize"
    		android:hardwareAccelerated="false"
    		android:windowSoftInputMode="adjustResize"
    		android:launchMode="singleTop"
    		android:screenOrientation="portrait" >
    	</activity>
   	    <activity
    		android:name=".wxapi.WXPayEntryActivity"
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
    	<activity
    		android:name="com.alipay.android.app.sdk.WapPayActivity"
    		android:screenOrientation="portrait" >
    	</activity>
    

- 在活动Activity中的`onCreate()`的方法中添加如下代码


    	IZTLibBase.newInstance(MainActivity.this);
    	IZTLibBase.getInstance().enableDebugMode();
    	IZTLibBase.getInstance().initZTGame("5010","乱炖英雄", true, mListener);


### 1.初始化

#####其中参数原型为：`IZTLibBase.newInstance(Activity act);`

#####`Activity act`:为游戏的活跃Activity

#####`IZTLibBase.getInstance().enableDebugMode();`
##### 此方法为激活SDK的调试模式 注意上线之后必须注释掉此行代码。


    
    /**
     *
     * @param gameId   为巨人平台申请到GameID;
     * @param appName  为游戏名称  
     * @param isLandScape  sdk横竖版
     * @param listener 事件回调监听器
     */
    public void initZTGame(String gameId,String appName, boolean isLandScape, IZTListener listener)
    
    	
### 2.绑定生命周期  

    
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
### 3.是否已经登录
   
    public boolean isLogined();

### 4.返回已登录用户信息

	public GiantUserInfo getLoginUserInfo();

	  	
<table border=”1″>
<tr>
<td>参数</td>
<td>参数说明</td>
</tr>
<tr>
<td>accid</td>
<td>账号ID</td>
</tr>
<tr>
<td>sign</td>
<td>验签需要参数由entity生成，规则见<a href="http://docs.mztgame.com/docs/sdk/server_guide#__2" >服务端文档1.2</a></td>
</tr>
<tr>
<td>entity</td>
<td>验签需要参数</td>
</tr>
</table>

例：
	  	
	GiantUserInfo mGiantUserInfo = IZTLibBase.getInstance().getLoginUserInfo();
	String accid  = mGiantUserInfo.getAccid();
	String sign   = mGiantUserInfo.getSign();
	String entity = mGiantUserInfo.getEntity();




### 5.登录接口

    /**
     *
     * @param zoneId   登录的区服号码
     * @param zoneName 登录的区服名称  
     * @param isAutoLogin  是否自动登录
     */
    public void loginZTGame(final String zoneId, final String zoneName, final boolean isAutoLogin)
    
    
    - 登录接口返回：
    {
    	"entity": {
    		"openid":"1-123456",
    		"account":"XXXXXXXX"
			......
    	},
    	"accid":"1-123456",
    	"token":"bbe7e46de2c7d3ace036cea155b23978",
    	"sign":"xxxxxx"
    }


游戏客户端拿到此登录返回数据需要拿entity中的openid和token去巨人平台服务端登录验证。具体参见服务端文档。

### 6.事件回调监听器：


	private IZTListener mListener = new IZTListener() {
	@Override
	public void onFinished(int what, int errcode, JSONObject json_obj) {
    switch (what) {
    	case ZTConsts.ZTGAME_LOGIN:
    		// Do your Login logic here
    		if (0 == errcode) {
    			// 客户端登录成功，登录流程下一步，把返回的信息发送到服务器进行验证
    			Toast.makeText(MainActivity.this, json_obj.toString(), Toast.LENGTH_SHORT).show();
    			Log.d("login_json", json_obj.toString());
    			try {
    				String openid = json_obj.getString("accid");
    				String sign = json_obj.getString("sign");
    				JSONObject entity = json_obj.getJSONObject("entity");
    			} catch (JSONException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    
    		}else if(2 == errcode){
    			//平台服务器连接异常，返回上次账号信息
    			try {
    				String openid = json_obj.getString("accid");
    				String sign = json_obj.getString("sign");
    				JSONObject entity = json_obj.getJSONObject("entity");
    			} catch (JSONException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}else {
    			Toast.makeText(MainActivity.this, json_obj.toString(), Toast.LENGTH_SHORT).show();
    		}
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

其中 what为消息的类型：


    public static final int ZTGAME_LOGIN = 0x01;	//登录消息 errocode非零为失败
    public static final int ZTGAME_PAY = 0x03;  //支付消息 errocode非零为失败
    public static final int ZTGAME_QUIT = 0x04;  //退出消息 errocode非零为失败
    public static final int ZTGAME_LOGOUT = 0x07;  //切换账号消息 errocode非零为失败
    public static final int ZTGAME_INIT = 0x08;  //初始化消息 errocode非零为失败


### 7.支付接口：
    
    
    if (IZTLibBase.getInstance().isLogined()) {
    	// 订单信息
    	IZTLibBase.getInstance().setZoneId("1");	//支付需要提供区服号码 不可写死
    	ZTPayInfo payInfo = new ZTPayInfo();
    	payInfo.setAmount(100);		//金额 单位为分
    	payInfo.setExtra("");  // 扩展信息，需要回传游戏服务器的请设置这个字段
    	IZTLibBase.getInstance().payZTGame(payInfo);	//调用支付接口
    }


### 8.退出接口：

	IZTLibBase.getInstance().quitZTGame();

	
### 9.用户中心接口：


	IZTLibBase.getInstance().enterCenterZTGame();


### 10.切换账号接口


	IZTLibBase.getInstance().switchAccountZTGame();

### 开通微信支付须知
#### （1）需提供签名md5值和包名给我们平台，申请微信支付相关参数
#### （2）将NewGiantDemo\src\com\ztgame\j3\wxapi\WXPayEntryActivity.java 放置在游戏应用包名+.wxapi下面.

## 接入建议	

新版sdk的登录流程优化：已登录的账号，再次登录不再弹出欢迎界面。建议游戏做如下处理

1. 已登录账户在游戏登录界面会自动登录，建议在游戏登录界面加一个调用用户中心的地方，方便用户注销、修改密码等操作（没有登陆不要调用）。
2. 建议在游戏设置内增加中户中心，方便用户在游戏内注销、修改密码等操作。
