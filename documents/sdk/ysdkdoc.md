

YSDK接入文档
=================

version： 1.2.4
##一、目录结构
可以在游戏项目中直接引入ysdk library项目，或者将ysdk项目下的文件拷贝到游戏项目中。

###项目结构图如下：

----------

- **ysdk** //library依赖文件	
	- **libs** //将此目录下的jar包拷贝到所在工程
	- **res** //将此目录下的所有资源文件拷贝到所在工程
- **ysdkTest** //测试demo的主工程	
	- **assets** //将此目录下的所有资源文件拷贝到所在工程
	- **res** 
	- **src** //目录下MainActivity.java文件需要仔细看下，里面的注释已经很明确了
	- **AndroidManifest.xml** //将此目录下的所有资源文件拷贝到所在工程

----------




## 二、接入前准备工作

### 2.1 YSDK模块介绍
<table class="t">
<tr>
<th width="50"> <b>模块名称</b>
</th><th width="150"> <b>模块功能</b>
</th><th width="100"> <b>接入条件</b>
</th></tr>
<tr>
<td> 数据分析模块
</td><td> 提供数据上报, 异常上报
</td><td> 需要手Q appId（应用注册时会自动分配，无需单独申请）
</td></tr>
<tr>
<td> 手Q
</td><td> 提供手Q登录，拉取个人信息的能力
</td><td> 需要手Q appId和appKey（应用注册时会自动分配，无需单独申请）
</td></tr>
<tr>
<td> 微信
</td><td> 提供微信登录，拉取个人信息的能力
</td><td> 需要微信 appId和appKey（应用注册时会自动分配，无需单独申请）
</td></tr>
<tr>
<td> 支付
</td><td> 提供米大师（midas）相关的支付能力
</td><td> 需要midas的offerid（无需单独申请，支付接入时自动分配）
</td></tr></table>

### 2.2 <a href="http://wiki.open.qq.com/wiki/YSDK%E4%BB%8B%E7%BB%8D" >【腾讯Ysdk环境权限】</a></td>
	
- 手Q、微信 Appid及测试环境权限

	在http://open.qq.com 完成基本资料审核后，流程到达“接入YSDK”，签署合作接入协议以后就会提供手Q和微信的appid和appkey，此时会同步开启测试环境权限
	
	点击接入米大师支付能力后面的“立即接入”前往midas相关配置管理地址
	
	至此，应用所有的测试环境权限都已经OK，可以开始SDK的接入了。

- 正式环境权限申请
	
	当应用在YSDK相关的测试环境调试成功以后，就可以自助开通正式环境的权限。具体流程如下：在http://open.qq.com 完成接入YSDK后，在流程“接入YSDK”中，合作协议的下方有应用的appid以及接入SDK的指引流程，点击流程第三步测试环境调试后方的调试通过按钮，并在弹框中选择确认以后，即可开通YSDK正式环境权限。

>注意事项：

	（1）游戏删档测试时提交的安装包必须为正式环境，而且实现所有功能
	（2）建议在测试环境调通所有功能以后再切换到正式环境调试。

- 游戏接入米大师流程

		（1）米大师注册，这步目前由YSDK自动完成，游戏无需单独注册。
		（2）应用相关信息配置，主要是应用名称以及虚拟货币的货币类型、兑换比例配置。
		（3）沙箱环境配置。配置游戏在沙箱的大区id，名词解释中已经有说明。
		（4）沙箱联调，开发者开始在沙箱环境联调相关功能。midas提供了充值沙箱Q币等功能，详细参考midas联调配置界面（名词解释中已经有说明）。
		（5）正式环境配置。当开发者在沙箱测试所有功能都已经OK以后，就可以提交正式环境权限开通的申请。按照米大师环境介绍中提供的指引提交审核。
		（6）正式环境验证。当正式环境审核通过以后，游戏开通正式环境权限，就可以在正式环境验证功能是否OK。

### 2.3 ysdk资源接入
#####2.3.1推荐游戏使用Android Library方式接入YSDK，既可以隔绝SDK与游戏代码的耦合，而且方便后续的SDK版本更新

-   (1) 复制ysdk目录到游戏工程相应目录
-   (2) 在Eclipse中引入ysdk项目
-   (3) 右击游戏项目→属性→Android→添加(库)→选择ysdk，完成对ysdk的引用
#####2.3.2 配置文件修改  
ysdkTest/assets/ysdkconf.ini中有游戏相关的各种配置，游戏需要根据自身情况调整为对应的配置。具体配置内容如下：

-  游戏相关配置，游戏需要把下面的三个配置项改为游戏自己的配置
  
		;**************游戏相关配置, 游戏需要根据各自情况修改 START **********
		;游戏的QQAPPID
		QQ_APP_ID=1104936***
		
		;游戏的微信APPID
		WX_APP_ID=wxfcefed3f366f***
		
		;游戏的OFFER_ID
		OFFER_ID=110493***
		;***************游戏配置项, 游戏需要根据各自情况修改 END **************

- YSDK相关配置项，游戏需要根据各自情况修改
 
	YSDK域名：ysdktest.qq.com为测试环境域名, ysdk.qq.com为正式环境域名；联调阶段游戏需要使用测试环境，发布时需要切换到正式环境

		;************* YSDK相关配置项,游戏需要根据各自情况修改 START **********
		;联调环境
		YSDK_URL=https://ysdktest.qq.com
		;正式环境
		;YSDK_URL=https://ysdk.qq.com
		;************** YSDK相关配置项,游戏需要根据各自情况修改 END **************

>备注：
>
>（1）为了防止游戏用非正式环境上线, SDK内检测到游戏使用非正式环境时, 会Toast出类似: “You are using http://ysdk.qq.com” 这样的提示, 游戏切换成正式环境域名以后此提示自动消失。
>
>（2）游戏修改域名的时候只需要将计划使用的域名前的分号删除，注释不需要使用的域名即可。

#####2.3.3 AndroidMainfest修改
######2.3.3.1  以下权限是通用的权限声明，需要在application标签外添加（建议参考下载包的Demo修改）

		<!-- TODO SDK接入必须权限模块 START -->
		<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
		<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
		<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
		<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
		<uses-permission android:name="android.permission.GET_TASKS" />
		<uses-permission android:name="android.permission.INTERNET" />
		<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
		<uses-permission android:name="android.permission.READ_PHONE_STATE" />
		<uses-permission android:name="android.permission.RESTART_PACKAGES" />
		<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
		<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />



######2.3.3.2 Application节点中添加如下字段：
 
    	android:name="com.ztgame.ztgameframework.ZTApplication"


> 注意：倘若游戏需要有自己的Application节点需要继承 `com.ztgame.ztgameframework.ZTApplication`        	
> 并且在自己的Application的onCreate方法中调用 `super.onCreate()`

	

	
######2.3.3.3 手Q接入AndroidMainfest修改
 游戏复制下面的事例填写到AndroidMainfest。

	<!-- TODO SDK接入 QQ接入配置 START -->
	<activity
	    android:name="com.tencent.tauth.AuthActivity"
	    android:launchMode="singleTask"
	    android:noHistory="true" >
	    <intent-filter>
	        <action android:name="android.intent.action.VIEW" />
	        <category android:name="android.intent.category.DEFAULT" />
	        <category android:name="android.intent.category.BROWSABLE" />
	        <data android:scheme="tencent游戏的手Q appid" />
	    </intent-filter>
	</activity>
	<activity
	    android:name="com.tencent.connect.common.AssistActivity"
	    android:configChanges="orientation|screenSize|keyboardHidden"
	    android:screenOrientation="portrait"
	    android:theme="@android:style/Theme.Translucent.NoTitleBar" />
	<!-- TODO SDK接入 QQ接入配置 END -->
> 注意事项：
	
- （1）com.tencent.tauth.AuthActivity的intent-filter中的<data android:scheme="tencent游戏的手Q appid" /> 中tencent后填写游戏的手Q appid。例如：<data android:scheme="tencent100703379" /> 
	
- （2）游戏的Activity的launchMode需要设置为singleTop, 设置为singleTop以后在平台拉起游戏的场景下, 有可能会出现游戏Activity被拉起两个的情况, 所以游戏Activity的onCreate里面需要检测当前Activity是否是重复的游戏Activity, 如果是则要finish掉当前游戏Activity。


######2.3.3.4 微信接入AndroidMainfest修改	
 游戏复制下面的事例填写到AndroidMainfest。

    <!-- TODO SDK接入 微信接入配置 START -->
    <activity
        <!-- 注意：此处应改为 游戏包名.wxapi.WXEntryActivity -->
        android:name="com.tencent.tmgp.yybtestsdk.wxapi.WXEntryActivity"
        android:excludeFromRecents="true"
        android:exported="true"
        android:label="WXEntryActivity"
        android:launchMode="singleTop"
        <!-- 注意：此处应改为 游戏包名.diff -->
        android:taskAffinity="com.tencent.tmgp.yybtestsdk.diff">
        <intent-filter>
            <action android:name="android.intent.action.VIEW" />
            <category android:name="android.intent.category.DEFAULT" />
            <!-- 注意：此处应改为 游戏的微信appid -->
            <data android:scheme="wxfcefed3f366fa606" />
        </intent-filter>
    </activity>
    <!-- TODO SDK接入 微信接入配置 END -->

> 注意事项：
 
（1）将WXEntryActivity.java放置在应用包名+.wxapi下面.

（2）微信接入的Activity中有三处需要游戏自行修改，上面的注释有具体说明：

- 	  WXEntryActivity的android:name需要修改
-   	  WXEntryActivity的android:taskAffinity需要修改
-   	  WXEntryActivity的data中android:scheme需要修改

######2.3.3.5 实名制接入AndroidMainfest修改
 游戏复制下面的事例填写到AndroidMainfest。

	 <!-- TODO GAME SDK接入YSDK实名制接入配置 START -->
	<activity
	    android:name="com.tencent.ysdk.module.realName.impl.RegisterRealNameActivity"
	    android:configChanges="orientation|screenSize|keyboardHidden"
	    android:screenOrientation="sensor"
	    android:theme="@android:style/Theme.Translucent.NoTitleBar">
	</activity>
	<!-- TODO GAME SDK接入YSDK实名制接入配置 END -->
######2.3.3.6 Midas接入AndroidMainfest修改
游戏按照下面的事例填写配置信息。 

	 <!-- TODO GAME SDK Midas接入配置 设置屏幕兼容声明 START -->
	<uses-permission android:name="android.permission.INTERNET" />
	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
	<uses-permission android:name="android.permission.READ_PHONE_STATE" />
	<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
	<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
	<uses-permission android:name="android.permission.RESTART_PACKAGES" />
	<uses-permission android:name="android.permission.GET_TASKS" />
	<uses-permission android:name="android.permission.READ_SMS" />
	<uses-permission android:name="android.permission.SEND_SMS"/>
	<uses-permission android:name="android.permission.WRITE_SETTINGS"/>
	<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
	<supports-screens
	    android:anyDensity="true"
	    android:largeScreens="true"
	    android:normalScreens="true" />
	<!-- TODO GAME SDK Midas接入配置 设置屏幕兼容声明 END -->
	
	<!-- TODO GAME SDK Midas接入配置 START -->
	<activity
	    android:name="com.tencent.midas.proxyactivity.APMidasPayProxyActivity"
	    android:theme="@android:style/Theme.Translucent.NoTitleBar"
	    android:screenOrientation="portrait"
	    android:configChanges="orientation|keyboardHidden|screenSize" />
	<activity
	    android:name="com.tencent.midas.wx.APMidasWXPayActivity"
	    android:theme="@android:style/Theme.Translucent.NoTitleBar"
	    android:exported="true" />
	
	<activity
	    android:name="com.tencent.midas.qq.APMidasQQWalletActivity"
	    android:launchMode="singleTop"
	    android:theme="@android:style/Theme.Translucent.NoTitleBar"
	    android:configChanges="orientation|keyboardHidden"
	    android:exported="true" >
	    <intent-filter>
	        <action android:name="android.intent.action.VIEW" />
	        <category android:name="android.intent.category.BROWSABLE"/>
	        <category android:name="android.intent.category.DEFAULT"/>
	        <data android:scheme="qwallet100703379"/>
			<!-- TODO GAME 这里为米大师的默认配置，游戏不要修改 -->
	    </intent-filter>
	</activity>
	<activity android:name="com.tencent.midas.jsbridge.APWebJSBridgeActivity"
	    android:theme="@android:style/Theme.Translucent.NoTitleBar"
	    android:windowSoftInputMode="stateAlwaysHidden"/>
	<!-- TODO GAME SDK Midas接入配置 END -->
	  
##二、代码接入  
###1.SDK初始化接口（必选接口）

在主Activity的onCreate方法中对EasySDK进行初始化。

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

####IZTListener 说明

	new IZTListener() {
        @Override
        public void onFinished(int what, int errcode, JSONObject json_obj){
		}
	}

#### 参数 `what` 说明
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
<tr>
<td>ZTConsts.ZTGAME_QQGROUP</td>
<td>是游戏中一键增加QQ群的消息，游戏收到该消息后，当errcode为0表示有一键加QQ功能，其他表示没有该功能</td>
</tr>
<tr>
<td>ZTConsts.ZTGAME_TENCENT_QUERY</td>
<td>是腾讯游戏点查询接口的消息，游戏收到该消息后，当errcode为0表示有查询成功，需要游戏端根据查询金额判断是否补</td>
</tr>
<tr>
<td>ZTConsts.ZTGAME_CREATE_ORDER</td>
<td>是创建订单成功的消息，返回的order_id参数</td>
</tr>

</table>



####调用用例:

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
            case ZTConsts.ZTGAME_QQGROUP: //腾讯QQ一键加群
                if(errcode == 0){
                    joinQQButton.setVisibility(View.VISIBLE);
                }
                break;
            }
        }
    };
}

###2.Activity生命周期相关方法（必选接口）

    @Override
    protected void onDestroy() {
    	IZTLibBase.getInstance().destroyZTGame();
		IZTLibBase.delInstance();
    	super.onDestroy();
    }
    
    @Override
    protected void onPause() {
    	IZTLibBase.getInstance().onPauseZTGame();
    	super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
        IZTLibBase.getInstance().onStopZTGame();
    }
    @Override
    protected void onResume() {
    	IZTLibBase.getInstance().onResumeZTGame();
    	super.onResume();
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

###3.登录模块（必选接口）
- 设置腾讯登录类型
 
	void setTencentLoginType(int tencentLoginType)
	
	tencentLoginType参数说明
	<table border=”1″>
	<tr>
	<td>参数</td>
	<td>参数说明</td>
	</tr>
	<tr>
	<td> ZTConsts.TencentLoginType.QQ</td>
	<td>QQ登录</td>
	</tr>
	<tr>
	<td>ZTConsts.TencentLoginType.WECHAT</td>
	<td>微信登录</td>
	</tr>
	
	</table>	
- 登录接口

	void loginZTGame(String zoneId, String zoneName, boolean isAutoLogin)

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

>回调函数返回值详细说明

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

ZTConsts.ZTGAME_LOGIN,errcode为-1失败时， json_obj 出现值:

    {
    "entity": {
    "openid":"1-123456",
    "account":"XXXXXXXX"
    ......
    },
    "accid":"1-123456",
    "token":"bbe7e46de2c7d3ace036cea155b23978",
    "sign":"xxxxxx"
	"msg":"QQ登录异常，请重试",
    "msgcode":"1001"
    }
>msgcode msg 说明

见http://wiki.open.qq.com/wiki/YSDK_Android_%E5%B8%B8%E9%87%8F%E6%9F%A5%E8%AF%A2 的eFLag

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
   
####调用用例:

     YourActivity.runOnUiThread(new Runnable() {
            public void run() {
					    //QQ登录
					    IZTLibBase.getInstance().setTencentLoginType(ZTConsts.TencentLoginType.QQ);
					    IZTLibBase.getInstance().loginZTGame("30001", "ZoneName", true);
					    
					    //微信登录
					    IZTLibBase.getInstance().setTencentLoginType(ZTConsts.TencentLoginType.WECHAT);
					    IZTLibBase.getInstance().loginZTGame("30001", "ZoneName", true);
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




###4.支付接入（必选接口）
payZTGame(ZTPayInfo payInfo)

####ZTPayInfo说明
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

####注意事项:
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


###5.统计接口（必选接口）
####登录完成数据统
loginOkZTGame(String roleId,String roleName,String roleLevel,String zoneId,String zoneName)
####创建角色数据统计接口接口
createRoleZTGame(String roleId,String roleName,String roleLevel,String zoneId,String zoneName)
####角色等级升级信息接口
roleLevelUpZTGame(String roleId,String roleName,String zoneId,String zoneName,int level)

<table border=”1″>
<tr>
<td>参数</td>
<td>参数说明</td>
</tr>
<tr>
<td>roleId</td>
<td>角色ID</td>
</tr>
<tr>
<td>roleName</td>
<td>角色名称</td>
</tr>
<tr>
<td>roleLevel</td>
<td>角色等级</td>
</tr>
<tr>
<td>zoneId</td>
<td>游戏区ID</td>
</tr>
<tr>
<td>zoneName</td>
<td>游戏区名称</td>
</tr>
<tr>
<td>level</td>
<td>角色等级</td>
</tr>

</table>

###6.退出界面（必选接口）
- 是否有第三方渠道的退出确认弹出框

	isHasQuitDialog（）

	如果此函数返回true，请游戏不要弹出游戏自身的退出确认弹出框而是直接调用quitZTGame来弹出第三方的退出弹出框，在ZTGame_Quit回调内处理游戏的退出操作（销毁代码，而不是再次弹出退出确认对话框。返回false则按照游戏自己的退出流程处理即可。)
- 弹出第三方退出弹出确认框接口

    quitZTGame()

####调用实例
	
	 //玩家点击退出
    if(IZTLibBase.getInstance().isHasQuitDialog()){ //渠道是否有退出框确认
    	IZTLibBase.getInstance().quitZTGame();//
    }else{
    	//游戏自己的退出框代码
    }

		 //事件监听
    private IZTListener mListener = new IZTListener() {
        @Override
        public void onFinished(int what, int errcode, JSONObject json_obj) {

            switch (what) {
            case ZTConsts.ZTGAME_QUIT:
                if (errcode == 0) {
    				//游戏退出
                }else {
              
                }
                break;
			}}}








##<a href="http://wiki.open.qq.com/wiki/YSDK%E6%8E%A5%E5%85%A5%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98%E4%B9%8BAndroid%E6%8E%A5%E5%85%A5" >【腾讯YSDK常见问题】</a>

###登陆接入注意事项
#####1. 通用
因为微信和手Q各自的bug，会导致游戏在多个场景下收不到回调。游戏在调用login后可以开始一个倒计时, 倒计时完毕如果没有收到回调则算作超时, 让用户回到登录界面。倒计时推荐时间为30s，游戏也可以自己设置其中收不到回调的场景包括但不限于：

- 在微信未登录的情况下, 游戏拉起微信输入用户名密码以后登录, 可能会没有登录回调, 这是微信客户端已知BUG
- 微信授权过程中, 点击左上角的 返回 按钮, 可能会导致没有授权回调
- openSDK 2.7 （MSDK 2.5）以下版本通过web授权点击取消授权以后没有回调
- 游戏接入过程中如果想体验实名制相关功能，可以在下面的界面查看当前账号是否已经有实名注册：http://jkyx.qq.com/web2010/faq.htm
#####2. 手Q相关
- 游戏在正式上线前，手Q只能使用协作者账号登陆，否则会在登陆时，在手Q的界面报110406或者110404错误。可以点击查看如何添加协作者账号。
- 没有安装手Q的时，精品游戏可以拉起Web页面授权,请确保AndroidMenifest.xml中AuthActivity的声明中要在intent-filter中配置, 详见本节手Q相关AndeoidMainfest配置处。 海纳游戏现在不支持拉起页面授权。可以通过WGIsPlatformInstalled接口判断是否安装手Q，未安装手Q则提示用户不能授权。
- 偶尔收不到OnLoginNotify回调：请确保com.tencent.tauth.AuthActivity和com.tencent.connect.common.AssistActivity在AndroidManifest.xml与手Q接入权限申明一致。
- 如果游戏的Activity为Launch Activity, 则需要在游戏Activity声明中添加android:configChanges="orientation|screenSize|keyboardHidden", 否则可能造成没有登录没有回调。
- 部分游戏在未装手Q时使用手Q登陆，跳转到提示下载手Q的界面返回游戏时可能会引起Crash。出现此问题时，使用Unity直接打出Apk包的游戏把YSDK的jar包中的assets中的内容解压放入Android/assets中。如果使用其他方式打包，需要注意打包脚本中适当处理YSDK的jar包中的so文件和资源文件，如果还有问题可尝试把YSDK的jar包中的assets中的内容解压放入游戏工程中的assets目录。
#####3. 微信相关
- 微信授权需要保证微信版本高于4.0
- 拉起微信时候, 微信会检查应用程序的签名和微信后台配置的签名是否匹配(此签名在申请微信appId时提交过), 如果不匹配则无法唤起已经授权过的微信客户端.
- WXEntryActivity.java 位置不正确（必须在包名/wxapi 目录下）则不能收到回调.

###手Q登录异常检查步骤
#####第一步：手Q界面弹框报错
- 首先确认游戏是否上线，如果游戏没有上线，检查登陆的账号是否为协作者账号。协作者账号添加方法如下：

	1.进入游戏在open平台的注册界面，点击下图中的QQ登录。
	 
	2.在跳转后的界面选择应用调试者，并输入调试者QQ好并确定。	

- 如果添加调试者账号以后仍然报错，然后按照下面的报错错误码及具体解决方案进一步确认：
 
	100044：游戏当前安装包签名与平台上传的安装包签名不一致，请检查打包用的keystone

	游戏也可下载https://res.wx.qq.com/open/zh_CN/htmledition/res/dev/download/sdk/Gen_Signature_Android2.apk， 并将此apk安装到手机上， 在输入框中输入游戏的包名，点击按钮读取游戏包的签名。检查上述工具获取到的签名是否和微信后台配置的签名一致。
	
 	**如果通过签名检查工具确认签名一致，并且确认使用了调试者QQ,请咨询企业QQ(800013811)解决。**

    110406：未正式上线的游戏，需要使用调试者账号测试，非调试者账号登陆则报错110406。添加的调试者账号必须与开发者账号是好友。
#####第二步：检查签名、包名、appid

- 检查游戏在ysdkconf.ini文件中配置的appid是否正确；
- 检查游戏在AndroidManfest中关于手Q的配置是否正确，配置方法参照上面接入配置的说明，重点检查com.tencent.tauth.AuthActivity和com.tencent.connect.common.AssistActivity的配置；
- 检查com.tencent.tauth.AuthActivity的intent-filter中的 中tencent后填写的是否为游戏的手Q appid；
- 检查游戏的Activity的launchMode是否为singleTop。
- 检查无法登陆的情形是否属于YSDK登陆模块注意事项中提到的集中情形，点击查看对应内容。

#####第三步：检查onActivityResult
游戏最后一次调用了initZTGame的Activity的onActivityResult是否调用了
	
	IZTLibBase.getInstance().onActivityResultZTGame(requestCode, resultCode, data);。
部分游戏有多个activity，确保要最后一个初始化的Activity里面调用onActivityResult。

#####第四步：检查声明周期的设置
按照接入说明里面的调用说明检查是否在对应的声明周期调用了SDK的声明周期函数（第二步Activity生命周期相关方法）

###微信登录不了检查步骤
#####第一步：检查Log
检查Log中是否有
	
	lauchWXPlatForm wx SendReqRet: true

有这一句表示已经成功发送请求到微信
如果微信客户端被不能被拉起来，请查看第二步， 

如果微信客户端被拉起了，但是没有回调，请查看第三步

#####第二步：检查签名、包名、appid

-  检查游戏在ysdkconf.ini文件中配置的appid是否正确
-  检查游戏在AndroidManfest中关于WXEntryActivity的配置是否正确，配置方法参照上面接入配置的说明，另外要重点检查下面三项：
	-    	WXEntryActivity的android:name需要修改
	-   		WXEntryActivity的android:taskAffinity需要修改
	-   		WXEntryActivity的data中android:scheme需要修改
-  下载https://res.wx.qq.com/open/zh_CN/htmledition/res/dev/download/sdk/Gen_Signature_Android2.apk ， 将此apk安装到手机上， 在输入框中输入游戏的包名，点击按钮读取游戏包的签名。检查上述工具获取到的签名是否和微信后台配置的签名一致。

#####第三步：检查WXEntryActivity.java放置的位置
检查WXEntryActivity.java放置的位置（ysdkTest）
此文件一定要放在 游戏+.wxapi 下面，例如游戏的包名为：com.tencent.ysdkdemo， 则WXEntryActivity.java 应该放在com.tencent.ysdkdemo.wxapi下。同时查看WXEntryActivity里面的内容是否和下面的一致

		/**
		 * !!此文件的代码逻辑部分使用者不要修改
		 */
		public class WXEntryActivity extends com.tencent.ysdk.module.user.impl.wx.YSDKWXEntryActivity { }

此步骤没问题请查看第四步
#####第四步：检查YSDK声明周期的设置
按照接入说明里面的调用说明检查是否在对应的声明周期调用了YSDK的声明周期函数。 点击查看YSDK声明周期设定
#####第五步：检查onResume
游戏的Launch Activity的onResume里面是否调用了onResume。

    @Override
    protected void onResume() {
    	IZTLibBase.getInstance().onResumeZTGame();
    	super.onResume();
    }

###其他登录相关问题
####1.YSDK登录失败，提示client request's app is not existed

游戏登录的时候能成功授权，但是授权回来不能收到成功回调，log日志显示{"ret":-2,"msg":"client request's app is not existed"}。该问题为游戏没有YSDK对应环境权限，权限开通请点击参考。

####2.YSDK登录拉起手Q以后弹框100044、110404或者110406，尤其MSDK切换游戏比较容易遇到

游戏在拉起手Q授权的界面弹框，多是因为流程或者权限原因引起。该问题可以参照YSDK wiki中关于手Q登录异常检查步骤的第一步中给出的方法去定位解决。
####3.YSDK登录微信拉不起来微信授权

游戏在登录时拉不起来微信一般是因为签名问题引起，可以参照YSDK wiki中关于微信登录不了检查步骤的顺序逐步检查。
####4.手Q登录异常检查步骤（手Q界面报错、没有回调等）

游戏可以按照此检查步骤检查关于YSDK的手Q登录相关的内容是否正确，生命周期重点检查onCreate和onActivityResult的调用。
####5. 微信登录不了检查步骤（拉不起微信、微信无回调等）

游戏可以按照此检查步骤检查关于YSDK的微信登录相关的内容是否正确，生命周期重点检查handleIntent的调用。
####6. YSDK自动登录

为了保证提供给游戏的票据的有效性，YSDK会在游戏启动或者运行期间定时触发自动登录，具体关于自动登录相关的说明可以参考YSDK Wiki中登录模块关于登陆模块概述中的内容。
###其它支付相关问题

####1 点击支付无法成功唤起支付界面

目前YSDK集成米大师的插件版，支付需要同时依赖腾讯充值的APP，游戏可以参照YSDK的wiki中关于支付时无法唤起支付界面的方法定位。
####2 点击支付报错1001-1007-0

出现该错误一般是因为游戏沙箱环境或者大区id传递错误，具体可以参考YSDK的wiki中关于支付相关错误码的说明。
其中游戏可以参照YSDK的wiki中关于支付时参数错误定位的方法来验证对应参数是否正确。
####3 点击支付时报错1016

出现该错误一般是因为游戏支付相关配置没有同步或者权限没有开通，具体可以参考YSDK的wiki中关于支付相关错误码的说明。
其中游戏可以参照YSDK的wiki中关于支付时参数错误定位的方法来验证对应参数是否正确。
####4 充值时或者游戏启动时提示腾讯充值已停止运行

目前YSDK集成米大师的插件版，支付需要同时依赖腾讯充值的APP，目前腾讯充值存在部分兼容性问题，游戏可以根据wiki看是否已知问题。


http://wiki.open.qq.com/wiki/YSDK%E6%8E%A5%E5%85%A5%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98%E4%B9%8BAndroid%E6%8E%A5%E5%85%A5#1.1._YSDK.E7.99.BB.E5.BD.95.E5.A4.B1.E8.B4.A5.EF.BC.8C.E6.8F.90.E7.A4.BAclient_request.27s_app_is_not_existed



























