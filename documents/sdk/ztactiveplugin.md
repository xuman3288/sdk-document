##ZTActivePlugin登录激活插件V2.1Doc  


---------------------------------------------------------

###激活插件SDK下载链接：

- [Download V2.1 SDK](http://222.73.243.55:3000/downloadsdk/ztactiveplugin.zip)

------------------------------------------------------------------

1. 接入步骤：  

   - 添加ztactiveplugin.jar 入工程libs中  
   - 将Demo中的assets下面的ztgameactiveres加入到工程assets中  
   - 替换激活界面的背景图片:路径为ztgameactiveres文件夹ztgameactiveres\images\bg2.jpg
   - 在AndroidManifest.xml中添加如下：
   		
   			<activity
            android:name="com.mztgame.ztactiveplugin.ZTActiveView"
            android:configChanges="orientation|keyboardHidden|screenSize">
        	</activity>
        
   - 添加权限：
   	
   			<uses-permission android:name="android.permission.INTERNET" />
        
2. 调用接口：  
	函数原型：  
	
	`public void isActived() `

		
	以上为判断是否激活的接口，会在IMZTActiveTListener中返回是否需要显示激活页面的结果：
	1. 返回ZTGAME_NEED_ACTIVE则表示需要显示激活
	2. 返回ZTGAME_NONEED_ACTIVE则表示不需要显示激活页面
			
    `	public void init (String gameId, String channelId, String uid, Activity ctx, IMZTActiveTListener listener)`
			
		
	`	public void init (String gameId, String channelId, String uid, Activity ctx, IMZTActiveTListener listener,boolean isDisplayClose)`
	
	以上为初始化接口，必须在其他插件接口调用之前调用。isDisplayClose在激活界面是否显示关闭按钮：true不显示，false显示，不设置默认显示
	
	`	public void showActiveWindow()`
	
	以上为显示激活窗口
	

	`	public void closeActiveWindow()`
	

	以上为关闭激活窗口

	
	实现回调接口接受插件的消息：
	
		private IMZTActiveTListener mImztListener = new IMZTActiveTListener() {
		
		@Override
		public void onFinished(int what, String response) {

			switch (what) {
				case ZTGAME_ACTIVE_SUCCESS:
				//Do active successfully allow user to enter game
				break;
				case ZTGAME_ACTIVE_FAILED:
				//active failed for some reason check response for more details
				Toast.makeText(mContext, response, 3000).show();
				break;
				case ZTGAME_QUERY_CODEURL:
				//reserved
				break;
				case ZTGAME_NONEED_ACTIVE:
				//there is no need to active allow user to enter game
				break;
				case ZTGAME_NEED_ACTIVE:
				//call showActiveWindow();
				showActiveWindow();
				break;
				case ZTGAME_ACTIVE_INIT:
				//initialization finished call isActived()
				isActived();
				case ZTGAME_ACTIVE_CLOSED:
				//There are two reason we receive the message 1. user close the window by click X button 2.user finished the active procedure. we should check whether ZTGAME_ACTIVE_SUCCESS is received if not we recall showActiveWindow() or exit the game base on demand.
				//用户点击关闭激活界面的按钮 需要调用closeActiveWindow()方法或退出游戏，或者提示不能关闭的提示
			}
		};
		
		
		
	what消息种类：  
			
		public static final int ZTGAME_ACTIVE_SUCCESS = 0; // 激活成功
		public static final int ZTGAME_ACTIVE_FAILED  = -1; //激活失败
		public static final int ZTGAME_QUERY_CODEURL  = 2;  //获取激活码连接正确
		public static final int ZTGAME_NONEED_ACTIVE = 3; //无需激活
		public static final int ZTGAME_NEED_ACTIVE = 4;   //需要激活
		public static final int ZTGAME_ACTIVE_INIT = 5;   //初始化成功回调，收到这个回调之后开始其他操作。
		public static final int ZTGAME_ACTIVE_CLOSED = 6;   //表示手动关闭激活页面，发送此消息.
	
	
	
	当what为ZTGAME_ACTIVE_FAILED时， response为失败原因。
	