##激活插件  


---------------------------------------------------------

###激活插件SDK下载链接：

- [Download V1.0.7 SDK](http://docs.mztgame.com/files/Android/plugin/ActivePluginSDKv1.0.7.zip)

------------------------------------------------------------------

###1. 接入步骤：  
####工程目录：

- ZTActiveNPlugin
	- libs          //将此目录下的jar包拷贝到所在工程
	- res           //将此目录下的所有资源文件拷贝到所在工程

- ZTActiveNPluginTest
	- assets //将此目录下的所有资源文件拷贝到所在工程
	- res
	- src    //目录下MainActivity.java文件需要仔细看下，里面有注释说明
	- AndroidManifest.xml 

####资源接入
   - 添加ZTActiveNPlugin/libs目录下的文件到工程libs中  
   - 添加ZTActiveNPlugin/res目录下的文件到工程res中  
   - 添加ZTActiveNPluginTest/assets目录下的文件到工程assets中 
   - 如果需要替换背景图片：替换 ZTActiveNPlugin/res/drawable/ztgame_act_img_bg.jpg 文件
   - 在AndroidManifest.xml中添加如下：
	
         <activity
            android:name="com.mztgame.ztactiveplugin.ZTActiveView"
            android:configChanges="orientation|keyboardHidden|screenSize"
            android:screenOrientation="landscape"
            android:theme="@style/Ztgame_Act_Theme" >
        </activity>
        

   - 添加权限：
   - 
   			<uses-permission android:name="android.permission.INTERNET" />

        
###2. 调用接口： 
####2.1 初始化接口：			
-     public void init (String gameId, String channelId, String uid, Activity ctx, IMZTActiveTListener listener)
- 	public void init (String gameId, String channelId, String uid, Activity ctx, IMZTActiveTListener listener,boolean isDisplayClose)
	
	以上为初始化接口，必须在其他插件接口调用之前调用。isDisplayClose在激活界面是否显示关闭按钮：true不显示，false显示，不设置默认显示 
####2.2 是否开启激活功能：  
	
public void isActived() 

	以上为判断是否激活的接口，会在IMZTActiveTListener中返回是否需要显示激活页面的结果：
	1. 返回ZTGAME_NEED_ACTIVE则表示需要显示激活
	2. 返回ZTGAME_NONEED_ACTIVE则表示不需要显示激活页面

####2.3 显示激活窗口：	
public void showActiveWindow()
	
####2.4 关闭激活窗口：
public void closeActiveWindow()
	

	
####2.5 实现回调接口接受插件的消息：

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
	public static final int ZTGAME_ACTIVE_FAILED = -1; //激活失败
	public static final int ZTGAME_QUERY_CODEURL = 2; //获取激活码连接正确
	public static final int ZTGAME_NONEED_ACTIVE = 3; //无需激活
	public static final int ZTGAME_NEED_ACTIVE = 4; //需要激活
	public static final int ZTGAME_ACTIVE_INIT = 5; //初始化成功回调， 收到这个回调之后开始其他操作。
	public static final int ZTGAME_ACTIVE_CLOSED = 6; //表示手动关闭激活页面， 发送此消息.

	当what为ZTGAME_ACTIVE_FAILED时， response为失败原因。
