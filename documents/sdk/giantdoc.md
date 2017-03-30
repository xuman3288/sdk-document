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
    

### 1.初始化

- newInstance(Activity ac)
<table border=”1″>
<tr>
<td>参数</td>
<td>参数说明</td>
</tr>
<tr>
<td>ac</td>
<td>Activity类的一个实例</td>
</tr>
</table>

- initZTGame(String gameId, String appName, boolean isLandScape, IZTListener listener)

<table border=”1″>
<tr>
<td>参数</td>
<td>参数说明</td>
</tr>
<tr>
<td>gameId</td>
<td>游戏ID</td>
</tr>
<tr>
<td>appName</td>
<td>游戏名称</td>
</tr>
<tr>
<td>isLandScape</td>
<td>游戏横竖屏（true  : 横版，false : 竖版）</td>
</tr>
<tr>
<td>listener</td>
<td>用于响应回调事件的监听接口</td>
</tr>
</table>

#####IZTListener 说明

	new IZTListener() {
        @Override
        public void onFinished(int what, int errcode, JSONObject json_obj){
		}
	}

##### 参数 `what` 说明
<table border=”1″>
<tr>
<td>出现值  </td>
<td>描述</td>
</tr>
<tr>
<td>ZTConsts.ZTGAME_INIT</td>
<td>是SDK初始化完成的通知，所有接口都需要在INIT成功完成之后才能开始调用</td>
</tr>
<tr>
<td>ZTConsts.ZTGAME_LOGIN</td>
<td>需要处理登录返回的json_obj参数，交给游戏服务器<a href="http://docs.mztgame.com/docs/sdk/server_guide#__2" >进行效验</a></td>
</tr>
<tr>
<td>ZTConsts.ZTGAME_PAY</td>
<td>只需要判断errcode为0成功还是-1失败，但是是否到账要以<a href="http://docs.mztgame.com/docs/sdk/server_guide#__7" >服务器通知为准</a></td>
</tr>
<tr>
<td>ZTConsts.ZTGAME_QUIT</td>
<td>是游戏进行销毁操作的地方，在第三方退出框点击确认后会收到此回调</td>
</tr>
<tr>
<td>ZTConsts.ZTGAME_LOGOUT</td>
<td>是游戏进行登出操作/切换账号操作的地方，需要返回到游戏登录界面等待用户再次登录</td>
</tr>
</table>



#####调用用例:

注意：只需调用一次  

    IZTLibBase.newInstance(MainActivity.this);
    IZTLibBase.getInstance().initZTGame("5012","Game Name", false, mListener);
	 //事件监听
    private IZTListener mListener = new IZTListener() {
        @Override
        public void onFinished(int what, int errcode, JSONObject json_obj) {

            switch (what) {
            case ZTConsts.ZTGAME_LOGIN:
                if (errcode == 0) {
                    //游戏完成事件, 设置角色信息
                    IZTLibBase.getInstance().loginOkZTGame("roleId", "roleName", "roleLevel", "zoneId", "zoneName");
                    try {
                        String openid = json_obj.getString("accid");
                        String sign = json_obj.getString("sign");
                        JSONObject entity = json_obj.getJSONObject("entity");
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else if（errcode == -2）{
                     //用户取消登录，关闭登录框
                }else {
                    //登录失败
                }
                break;
            case ZTConsts.ZTGAME_INIT:
                if (errcode == 0) {
                    //初始化完成
                } else {
                    //初始化失败
                }
                break;
            case ZTConsts.ZTGAME_PAY:
                if(errcode == 0){
                    //支付完成
                }else{
                    //支付失败
                }
                break;
            case ZTConsts.ZTGAME_QUIT:
                if(errcode == 0){
                    //游戏退出
                } else {
                }
                break;
            case ZTConsts.ZTGAME_LOGOUT:
              
                break;
            }
        }
    };
}
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
<td>参数类型</td>
<td>参数说明</td>
</tr>
<tr>
<td>accid</td>
<td>String</td>
<td>账号ID</td>
</tr>
<tr>
<td>sign</td>

<td>String</td>
<td>验签需要参数由entity生成，规则见<a href="http://docs.mztgame.com/docs/sdk/server_guide#__2" >服务端文档1.2</a></td>
</tr>
<tr>
<td>entity</td>

<td>String</td>
<td>验签需要参数</td>
</tr>
</table>

例：
	  	
	GiantUserInfo mGiantUserInfo = IZTLibBase.getInstance().getLoginUserInfo();
	String accid  = mGiantUserInfo.getAccid();
	String sign   = mGiantUserInfo.getSign();
	String entity = mGiantUserInfo.getEntity();




### 5.登录接口
loginZTGame(String zoneId, String zoneName, boolean isAutoLogin)
<table border=”1″>
<tr>
<td>参数</td>
<td>参数说明</td>
</tr>
<tr>
<td>zoneId</td>
<td>游戏区ID（Int, 大于0）</td>
</tr>
<tr>
<td>zoneName</td>
<td>游戏区名称</td>
</tr>
<tr>
<td>isAutoLogin</td>
<td>是否自动登录，传true即可</td>
</tr>

</table>

#####回调函数返回值详细说明
ZTConsts.ZTGAME_LOGIN,errcode为0成功时， json_obj 出现值:

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

<table border=”1″>
<tr>
<td>参数</td>
<td>参数说明</td>
</tr>
<tr>
<td>entity</td>
<td>服务端验签需要参数</td>
</tr>
<tr>
<td>accid</td>
<td>账号ID</td>
</tr>
<tr>
<td>token</td>
<td>服务端验签需要参数</td>
</tr>
<tr>
<td>sign</td>
<td>服务端验签需要参数</td>
</tr>

</table>
   
#####调用用例:

     YourActivity.runOnUiThread(new Runnable() {
            public void run() {            
                     IZTLibBase.getInstance().loginZTGame("1", "zoneName"", true);
            }
    });
	 //事件监听
    private IZTListener mListener = new IZTListener() {
        @Override
        public void onFinished(int what, int errcode, JSONObject json_obj) {

            switch (what) {
            case ZTConsts.ZTGAME_LOGIN:
                if (errcode == 0) {
                    //游戏完成事件, 设置角色信息
                    IZTLibBase.getInstance().loginOkZTGame("roleId", "roleName", "roleLevel", "zoneId", "zoneName");
                    try {
                        String openid = json_obj.getString("accid");
                        String sign = json_obj.getString("sign");
                        JSONObject entity = json_obj.getJSONObject("entity");
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else if（errcode == -2）{
                     //用户取消登录，关闭登录框
                }else {
                    //登录失败
                }
                break;
			}}}

### 6.支付接口：
    
    
payZTGame(ZTPayInfo payInfo)

#####ZTPayInfo说明
<table border=”1″>
<tr>
<td>Method</td>
<td>Required / Optional / Deprecated</td>
<td>Description</td>
</tr>
<tr>
<td>setAmount</td>
<td>必填</td>
<td>设置商品金额(RMB). 单位(分)</td>
</tr>

<tr>
<td>setExtra</td>
<td>可选设置</td>
<td>设置游戏订单扩展信息(游戏订单号等等)</td>
</tr>

<tr>
<td>setMoneyName</td>
<td>必填</td>
<td>设置货币单位名称</td>
</tr>

<tr>
<td>setProductName</td>
<td>必填</td>
<td>设置商品名称</td>
</tr>

<tr>
<td>setProductId</td>
<td>必填</td>
<td>设置商品ID</td>
</tr>

<tr>
<td>setExchangeRatio</td>
<td>可选设置</td>
<td>设置价格比率</td>
</tr>


</table>

#####注意事项:
调用支付接口之前设置一下区服号码
IZTLibBase.getInstance().setZoneId("1");

#####调用用例:

	IZTLibBase.getInstance().setZoneId("1");    //支付需要提供区服号码 不可写死
    ZTPayInfo payInfo = new ZTPayInfo();
    payInfo.setAmount(100);  //设置金额, 单位(分) *必传参数
    payInfo.setProductName("test item"); //设置商品名称 *必传参数
    payInfo.setProductId("1001"); // 设置商品ID *必传参数
    payInfo.setExtra("1"); //设置游戏订单扩展信息
    IZTLibBase.getInstance().payZTGame(payInfo);
	     //事件监听
	private IZTListener mListener = new IZTListener() {
    @Override
    public void onFinished(int what, int errcode, JSONObject json_obj) {

        switch (what) {
        case ZTConsts.ZTGAME_PAY:
            if (errcode == 0) {
                //支付成功
            }else {
				//支付失败
            }
            break;
        }}}



###7 用户中心
- 是否需要用户中心按钮接口

	isHasCenterZTGame（）

	以上为某些渠道判断是否存在用户中心按钮倘若返回false不作处理，倘若返回true需要显示用户中心按钮，点击此按钮后调用enterCenterZTGame()
- 用户中心操作, 调用此接口弹去渠道方的用户中心界面

    enterCenterZTGame()
#####调用实例
	
	 //玩家点击退出
    if(IZTLibBase.getInstance().isHasCenterZTGame()){ //渠道是否有退出框确认
    	IZTLibBase.getInstance().enterCenterZTGame();//
    }

###8 切换账号
- 是否需要切换账号按钮接口

	isHasSwitchAccountZTGame（）


    以上接口返回true则游戏需要添加一个切换账号按钮以方便用户切换账号.点击此按钮后调用switchAccountZTGame()

- 切换账号

    switchAccountZTGame()
	以上为切换账号接口功能，调用此接口执行切换账号操作，调用此接口后，会发送ZTGAME_LOGOUT消息。
#####调用实例
	
	 //玩家点击退出
    if(IZTLibBase.getInstance().isHasSwitchAccountZTGame()){ //渠道是否有退出框确认
    	IZTLibBase.getInstance().switchAccountZTGame();//
    }

		 //事件监听
    private IZTListener mListener = new IZTListener() {
        @Override
        public void onFinished(int what, int errcode, JSONObject json_obj) {

            switch (what) {
            case ZTConsts.ZTGAME_LOGOUT:
        	//切换账号处理
       
			}}}

### 开通微信支付须知
#### （1）需提供签名md5值和包名给我们平台，申请微信支付相关参数
#### （2）将NewGiantDemo\src\com\ztgame\j3\wxapi\WXPayEntryActivity.java 放置在游戏应用包名+.wxapi下面.

## 接入建议	

新版sdk的登录流程优化：已登录的账号，再次登录不再弹出欢迎界面。建议游戏做如下处理

1. 已登录账户在游戏登录界面会自动登录，建议在游戏登录界面加一个调用用户中心的地方，方便用户注销、修改密码等操作（没有登陆不要调用）。
2. 建议在游戏设置内增加中户中心，方便用户在游戏内注销、修改密码等操作。
