##ZTPromoteCode二维码分享插件v1.0 Doc  


---------------------------------------------------------

####二维码分享插件SDK下载链接：

- [Download SDK V1.0](http://222.73.243.55:3000/downloadsdk/ztpromotecode_v1.0.zip)

------------------------------------------------------------------
####`注：该插件依赖base,务必在base的初始化接口完成后保证gameid,openid不为空的情况下，再调用该插件接口`
1. 接入步骤：  

   - 添加libs/目录中jar放入工程的libs中（若libs中的base.jar无需复制）  
   - 在AndroidManifest.xml中添加如下：
   		
   			<activity
	            android:name="com.mztgame.promotercode.ZTPromoteCodeActivity"
	            android:configChanges="orientation|screenSize|keyboardHidden"
	            android:screenOrientation="behind"
	            android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen" />

        
   - 添加权限：
   	
   			<uses-permission android:name="android.permission.INTERNET" />
        
2. 调用初始化接口：  
	函数原型：  
	
			public void init(Context context,String gameid,String channelId,String openid)
	参数说明：
	
	`context:`context  
	`gameid:`游戏的id（另：若已接入base包，该值可用接口IZTLibBase.getUserInfo().get(ZTConsts.User.GAMEID)获取)  
	`channelId:`渠道id（另：若已接入base包，该值可用接口IZTLibBase.getInstance().getPlatform()获取）  
	`openid：`用户的uid（唯一标识)（另：若已接入base包，该值可由IZTLibBase.getUserInfo().get(ZTConsts.User.ACCID)接口获取）  
		
	调用例子：

		String gameid = IZTLibBase.getUserInfo().get(ZTConsts.User.GAMEID);//获取gameid
		int channelid = IZTLibBase.getInstance().getPlatform();//获取渠道id
		String openid = IZTLibBase.getUserInfo().get(ZTConsts.User.ACCID);//获取openid

		ZTPromoteCode.getInstance().init(MainActivity.this,gameid,channelid+"",openid);

3. 生成分享二维码接口:  
	函数原型：  

		public void generatePromoteCode(int width, String logoName,int logowidth,boolean  isShow,IPromoteCodeListener listener)
	参数说明：
	
	`width:`生成的二维码长宽  
	`logoName:`logo图片对应的资源名称  
	`logowidth:`logo对应长宽  
	`isShow：`是否展示二维码图片,true展示二维码图片，false不展示图片  
	`listener:`生成结果回调接口  
	调用实例：
	
		ZTPromoteCode.getInstance().generatePromoteCode(width,"ic_launcher",30,false,listener);
	#####`注：在调用该接口之前，请务必先调用init接口。`
4. 其他接口  
	IPromoteCodeListener接口：  
	该接口必须实现，并实现接口方法onResult  
	原型：  

		public void onResult(int code,String errmsg,String shortUrl,Bitmap codeBitmap);

	参数说明：  
	`code:`返回结果码，0表示成功，其他失败  
	`errmsg:`结果消息，当code为0是，该值为"success"；  
	`shortUrl:`短链接地址  
	`codeBitmap:`二维码图片的bitmap  

	#####`注：当code值不为0时，shortUrl和codebitmap均为null。`
	