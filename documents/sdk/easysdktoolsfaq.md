##Giant Android 自动打包工具文档
================================

*SDK版本： Ver 4.1.0  
时间：2015-2-9

烦请cp方仔细阅读文档，每次更新的修改在文档最下方  
`特别注意`  
**`所有文档中的接口均为必须接入，请游戏方自行核查`**
-----------------------------------------------
### 接入ZTgameFramework  

  
 
 - 请将`base.jar`, `ztgameframework.jar` 添加到工程的库依赖中。  
 - 在`assets`目录下添加`ztsdk_config.properties`文件确保文件中有以下内容:
 
 		config.sdk.class=com.ztgame.ztgameframework.ZTgameFramework
 						
 - ###1.1 初始化Android SDK：
 
 		IZTLibBase.newInstance(LoginActivity.this);
	    IZTLibBase.getInstance().initZTGame("5000","乱炖英雄", false, mListener);
	    
   *`函数原型`*
   
   		synchronized static public boolean newInstance(Activity ac);
   		
   		public void initZTGame(String gameId,String appName, boolean isLandScape, IZTListener listener)
   	**`说明:`**  
    首先调用IZTLibBase 的newInstance方法，传入你的Activity。然后调用getInstance()方法的initZTGame初始化游戏信息。  
   	**`参数:`**  
   	`Activiy`:传入的Activity 。e.g. LoginActivity.this;  
   	`gameId`:传入的游戏ID。 e.g. 5010;  
   	`appName`:传入的游戏名称。 e.g. 乱炖英雄。  
   	`isLandScape`:是否为横版。 e.g. true;  
   	`listener`:用于响应回调事件的监听接口。 e.g. mListener.  
   	
 - #####IZTListener SDK事件回调接口
   该接口必须实现onFinished方法  
   *`函数原型`*： 
   
   		public void onFinished(int what, int errcode, JSONObject json_obj);
   	
   	**`参数`**:  
   	`what`:返回事件的类型。 e.g. ZTConsts.ZTGAME_LOGIN, ZTConsts.ZTGAME_PAY etc.  
   	`errcode`:返回事件的错误码. e.g. ""  
   	`json_obj`:返回的Json数据对象。e.g. 
   	
   		{"mobile_type":"MI+4LTE","token":"ccdad172c729ee04","accid":"2056362","imei":"866333025673573","action":"login","account":"默认账号","mac":"7c:1d:d9:76:87:e2","channel":20,"account_3rd":"snoopyflex","ip":"192.168.197.33"}
   		
   		
   		该Json可以作为测试登录进入游戏的测试参数。接收到ZTGAME_LOGIN消息用于登录成功。其中accid和account 分别为：用户标示和账号。
   		
   	**what参数枚举**：
   	  
  *	#####ZTGAME_INIT  
  	**ZTGAME_INIT是SDK初始化完成的通知，所有接口都需要在INIT完成之后才能开始调用**    
  	
  * #####ZTGAME_LOGIN  
    ZTGAME_LOGIN需要处理登录返回的json_obj参数，交给游戏服务器进行效验
    
  * #####ZTGAME_PAY   
    ZTGAME_PAY只需要判断errcode为0成功还是-1失败，但是是否到账要以服务器通知为准
    
  * #####ZTGAME_QUIT
  	ZTGAME_QUIT是游戏进行销毁操作的地方，玩家在第三方退出框点击确认后会收到此回调
  		
  *	#####ZTGAME_LOGOUT
  	ZTGAME_LOGOUT是游戏进行登出操作/切换账号操作的地方，需要返回到游戏登录界面等待用户再次登录
  				
    		
 - ###1.2 登录操作
		public void loginZTGame(final String zoneId, final String zoneName, final boolean isAutoLogin)		
	zoneId : 服务器分区ID
	zoneName : 服务器分区名称
	isAutoLogin : 是否自动登录，传true即可
 - ###1.3 支付操作
 		final synchronized public boolean payZTGame(ZTPayInfo payInfo)
** 	payInfo详细描述参见如下	**
#####ZTPayInfo支付信息说明：  
  		
  			ZTPayInfo payInfo = new ZTPayInfo();
	        payInfo.setAmount(100);
	        payInfo.setProductName("钻石*10");
	        payInfo.setMoneyName("钻石");
	        payInfo.setExchangeRatio(10);
	        payInfo.setProductId("123");
	        payInfo.setExtra("1");         // 扩展信息，需要回传游戏服务器的请设置这个字段
	        IZTLibBase.getInstance().payZTGame(payInfo);
	        
   **`说明`**  
   			`setAmount(int amount):`设置支付的价格，单位为分  
   			`setProductName(String)`:设置商品名称，如果是钻石就设置为`“钻石*数量”`，如果是月卡就设置为`月卡`  
   			`payInfo.setMoneyName(String)`:设置游戏货币名称，如果是钻石就设置为`钻石`
   			`payInfo.setExchangeRatio(int)`:设置游戏兑换比率，1元对应多少游戏币，1：10就设置为10  
   			`setExtra(String)`:设置额外参数  
   			`payZTGame（ZTPayInfo payInfo）`:支付。   
   			`setProductId(String)` :部分运营商需要提供计费点通过此接口传入计费点。			
#####ZTPayInfo支付信息接口：

		public void setProductName(String productName)	//设置商品名称
		public void setExtra(String extra)	//设置额外信息
		public void setAmount(int amount)	//设置商品价格，单位为分
		public void setProductName(String productName)	//设置商品名称
		public void setMoneyName(String moneyName)	//设置货币名称
		public void setExchangeRatio(int exchangeRatio)	//设置汇率比例


- ###1.4 登录完成数据统计接口
		public void loginOkZTGame(String roleId,String roleName,String roleLevel,String zoneId,String zoneName)
	以上接口为登录流程结束后调用，分别传入：角色ID，角色名称，角色等级，服务器ID，服务器名称

- ###1.5 创建角色数据统计接口


 			public void createRoleZTGame(String roleId,String roleName,String roleLevel,String zoneId,String zoneName)
 			
	以上接口为创建角色数据统计接口，分别传入：角色ID。角色名称，角色等级, 区ID， 区名称。 

- ###1.6 角色等级升级信息接口

		public void roleLevelUpZTGame(String roleId,String roleName,String zoneId,String zoneName,int level)

	以上接口为角色等级升级信息接口：角色ID。角色名称, 区ID， 区名称， 等级。

- ###1.7 是否需要切换账号按钮接口
			public boolean isHasSwitchAccountZTGame()
以上接口返回true则游戏需要添加一个切换账号按钮以方便用户切换账号，点击此按钮后调用switchAccountZTGame()  

- ###1.8 切换账号操作
			public void switchAccountZTGame()	
	以上为切换账号接口功能，调用此接口执行切换账号操作

- ###1.9 是否需要用户中心按钮接口
			public boolean isHasCenterZTGame()
			
	以上为某些渠道判断是否存在用户中心按钮倘若返回false不作处理，倘若返回true需要显示用户中心按钮，点击此按钮后调用enterCenterZTGame()

- ###1.10 用户中心操作
			public void enterCenterZTGame()
以上为用户中心接口功能，调用此接口弹去渠道方的用户中心界面

- ###1.11 是否需要调用第三方推出框接口

			public void isHasQuitDialog()
			
	以上为是否有第三方渠道的退出确认弹出框，如果此函数返回true，请游戏不要弹出游戏自身的退出确认弹出框而是直接调用quitZTGame来弹出第三方的退出弹出框，在ZTGame_Quit回调内处理游戏的退出操作（销毁代码，而不是再次弹出退出确认对话框。返回false则按照游戏自己的退出流程处理即可。  
	
- ###1.12 弹出第三方退出弹出确认框接口
					
			public void quitZTGame()  
						
	以上为弹出第三方渠道的退出框的函数			

- ###1.13 开启日志输出接口
		public void enableDebugMode()
	游戏上线前需要注释该行代码以关闭日志输出

- ###1.14 获取渠道id
		public int getPlatform();
	渠道id以及游戏id可以统一从此wiki链接查询
[http://wiki.mztgame.com/index.php/%E6%B8%A0%E9%81%93%E4%BF%A1%E6%81%AF%E5%88%97%E8%A1%A8](http://wiki.mztgame.com/index.php/%E6%B8%A0%E9%81%93%E4%BF%A1%E6%81%AF%E5%88%97%E8%A1%A8)

- ###1.15 更新服务器id
		public void setZoneId(final String zoneId)
	玩家切换服务器后需要调用此方法更新当前服务器id
	
- ###1.16 更新当前活动Activity
		public void setActivity(Activity activity)
	如果游戏当前Activity变更，需要更新activity到SDK
	
- ###1.17 是否已经登录
		public boolean isLogined()
		
##需要调用的Android生命周期接口


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
 
####***以上为渠道需要在游戏主Activity中调用的生命周期函数。***####

------------------------------------------------------------
###Base 4.1.0 更新注意事项###

需要在AndroidManifest.xml中添加Application的节点属性。

	android:name="com.ztgame.ztgameframework.ZTApplication"
	
***如果你的工程里存在Application需要继承改Application 并且在onCreate函数中添加super.onCreate***



