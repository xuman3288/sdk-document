###巨人网络FacebookSDK接入文档

--------------------------------------------------------------------------------

Base ver 3.x  
Facebook SDK ver:3.23.0  
Author: Jerry

- 接入前准备：

	Androidmanifest.xml中添加如下内容
	
			<activity
            android:name="com.facebook.LoginActivity"
            android:label="@string/app_name"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />
            
    需要将游戏工程依赖FacebookSDK（可在Facebook官网下载中找到）
    工程需要依赖ZTFacebook工程
	需要在AndroidManifest.xml中添加如下meta-data
	
		<meta-data
            android:name="com.facebook.sdk.ApplicationId"
            android:value="@string/app_id" />
	
	请在工程的assets目录下面 添加文件 ztsdk_config.properties
	
		config.sdk.class=com.mztgame.ztfacebook.ZTFacebook


		config.gen_order_id.url=http://pay.mztgame.com/service/create
		config.convert_url=http://passport.ina.dev.mobileztgame.com/api/account/login
		config.ga.active.url=http://stat.mztgame.com/active

	*`其中config.convert_url可以设置为登陆验证的域名`*
	
	样板可以参考demo
	
	请在工程的res/string.xml中添加如下内容
	
	
			<string name="com_facebook_loginview_log_in_button">Connect</string>
		    <string name="com_facebook_loginview_log_out_button">Your Logout</string>
		 	<string name="app_id">736107976457282</string>
	`参数:`
	
	app_id：为在facebook开发平台申请到得appid  
	com_facebook_loginview_log_out_button：为登出账号时按钮上显示的文字  
	com_facebook_loginview_log_in_button：为登陆按钮显示的文字 
	
	需要在游戏适当位置添加Facebook的LoginButton 
	
- API接入
	将UI中的LoginButton做为参数传入到初始化接口：
	
		public void initZTGame(String gameId, String appName, boolean isLandScape,
			IZTListener listener, LoginButton fbLoginButton,
			Bundle savedInstanceState)
	
		Sample：
	
		((ZTFacebook)IZTLibBase.getInstance()).initZTGame("5001", "3wsfs", true, new IZTListener() {
			
			@Override
			public void onFinished(int what, int errcode, JSONObject json_obj) {
				Toast.makeText(MainActivity.this, json_obj.toString(), 3000).show();
			}
		}, fbLoginButton, savedInstanceState);

	
	`参数：`
	
	- gameId ：游戏的GameID
	- appName： 游戏名称
	- isLandScape ： 横竖屏设置
	- listener： SDK事件通知回调
	- fbLoginButton：Facebook LoginButton
	- savedInstanceState：主要Activity 创建onCreate的参数需要传入
	
	
	需要绑定的生命周期：
	
	
		@Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) 	{
	        IZTLibBase.getInstance().onActivityResultZTGame(requestCode, resultCode, data);
	        super.onActivityResult(requestCode, resultCode, data);
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
	    
	    @Override
	    protected void onPause() {
	    	super.onPause();
	    	IZTLibBase.getInstance().onPauseZTGame();
	    }	

	
	
	
- 回调接口：

		public void onFinished(int what, int errcode, JSONObject json_obj) {
				Toast.makeText(MainActivity.this, json_obj.toString(), 3000).show();
			}
			
			
返回的json_obj请参考：    

[Giant SDK Framework](http://222.73.243.55:3000/doc/easysdktoolsfaq.html#iztlistener_sdk______)
		