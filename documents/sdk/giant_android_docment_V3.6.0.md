##Giant Android 自动打包工具文档
================================

*版本：* v2.2 
*SDK版本： Ver 3.6.0 
时间：2015-03-26

烦请cp方仔细阅读文档，每次更新的修改在文档最下方
-----------------------------------------------
###接入ZTgameFramework  

  
 
 - 请将`base.jar`, `ztgameframework.jar` 添加到工程的库依赖中。  
 - 需要在`AndroidManifest.xml`中添加Application的节点属性。
 
		android:name="com.ztgame.ztgameframework.ZTApplication"
	注：如果项目有自己Application需继承ZTApplication，然后修改AndroidManifest.xml中Application的节点熟悉android:name为自己的Application。

 - 在`assets`目录下添加`ztsdk_config.properties`文件确保文件中有以下内容:
 
 		config.sdk.class=com.ztgame.ztgameframework.ZTgameFramework
 		
 - 确保代码中存在如下引用：
 
 		import com.ztgame.mobileappsdk.common.IZTLibBase;
		import com.ztgame.mobileappsdk.common.IZTListener;
		import com.ztgame.mobileappsdk.common.ZTPayInfo;
		import com.ztgame.mobileappsdk.common.ZTConsts;
		
 - 初始化Android登录SDK：
 
 		IZTLibBase.newInstance(LoginActivity.this);
	    IZTLibBase.getInstance().initZTGame("GMG000","乱炖英雄", false, mListener);
	    
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
   	
 - IZTListener SDK事件回调接口
   该接口必须实现onFinished方法  
   *`函数原型`*： 
   
   		public void onFinished(int what, int errcode, JSONObject json_obj);
   	
   	**`参数`**:  
   	`what`:返回事件的类型。 e.g. ZTConsts.ZTGAME_LOGIN, ZTConsts.ZTGAME_PAY etc.  
   	`errcode`:返回事件的错误码. e.g. ""  
   	`json_obj`:返回的Json数据对象。e.g. 
   	
   		{"mobile_type":"MI+4LTE","token":"ccdad172c729ee04","accid":"2056362","imei":"866333025673573","action":"login","account":"默认账号","mac":"7c:1d:d9:76:87:e2","channel":20,"account_3rd":"snoopyflex","ip":"192.168.197.33"}
   		
   		
   		该Json可以作为测试登录进入游戏的测试参数。接收到ZTGAME_LOGIN消息用于登录成功。其中accid和account 分别为：用户标示和账号。
   		
   	what参数枚举：
   	  
   		    public static final int ZTGAME_LOGIN           = 0x01; //登录    
    		public static final int ZTGAME_PAY             = 0x03; //支付   
    		public static final int ZTGAME_QUIT            = 0x04; //退出
    		public static final int ZTGAME_ANTIADDICTION   = 0x06; //防沉迷   
    		public static final int ZTGAME_LOGOUT          = 0x07; //登出
    		public static final int ZTGAME_INIT            = 0x08; //初始化
    		public static final int ZTGAME_SWITCH          = 0x09; //切换用户
    		
 - ZTPayInfo支付信息说明：  
  		
  			ZTPayInfo payInfo = new ZTPayInfo();
	        payInfo.setAmount(100);
	        payInfo.setProductName("钻石*10");
	        payInfo.setMoneyName("钻石");
	        payInfo.setExchangeRatio(10);
	        payInfo.setExtra("1");         // 扩展信息，需要回传游戏服务器的请设置这个字段
	        IZTLibBase.getInstance().payZTGame(payInfo);
	        
   **`说明`**  
   			`setAmount(int amount):`设置支付的数量   
   			`setProductName(String)`:设置商品名称，如果是钻石就设置为`“钻石*数量”`，如果是月卡就设置为  `月卡`  
   			`payInfo.setMoneyName(String)`:设置游戏货币名称，如果是钻石就设置为`钻石`
   			`payInfo.setExchangeRatio(int)`:设置游戏兑换比率，1：10就设置为10  
   			`setExtra(String)`:设置额外参数  
   			`payZTGame（ZTPayInfo payInfo）`:支付。
   			
==============================
###测试说明
1. 在初始化时收到 init finished 的Toast 表示初始化成功。
2. 在登录时收到 should pass json to client 后加一个json格式的Toast表示登录测试成功。
3. 支付目前在接入framework时候无法测试，需要使用打包工具后，实际测试。

=======================================
###使用自动打包工具  
![Screenshot](./easysdk.jpg)
ScreenShot
  
  

 - 将接入好ZTgameFramework的填入到源apk文件中
 - 将签名文件填入Kestore文件栏并填入 密码 别名（Alias）alias密码。
 - 勾选渠道
 - 保存位置填写好后 就可以点击生成了。
 
 
###`特别注意`
~~在选择豌豆荚的渠道时候，AndroidManifest.xml 中的application 的name字段需要保留。工具会改写该字段。（此处可以不写name字段）~~

###添加官方支付SDK需要的补充步骤
~~将Demo中的src目录下的两个ResouceMap.java 和ResourceSDKMap.java 添加到你的工程目录.~~   
~~将Demo中的res目录与你的工程目录合并。~~

~~做了以上两步操作之后，此工程只能用于巨人官方SDK的接入。如果要接入其他渠道需要撤销以上两步。~~


###ZTPayInfo支付信息接口：

	public void setProductName(String productName)	//设置商品名称
	public void setExtra(String extra)	//设置额外信息
	public void setAmount(int amount)	//设置商品价值
	public void setProductName(String productName)	//设置商品名称
	public void setMoneyName(String moneyName)	//设置货币名称
	public void setExchangeRatio(int exchangeRatio)	//设置汇率比例

------------------------------------  
`特别注意`  
**`所有文档中的接口均为必须接入，请游戏方自行核查`**

##Base.jar 3.1.0 版本 新增接口：


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

 
 
以上为渠道需要在主Activity中调用的生命周期函数。
 
 			public void createRoleZTGame(String roleId,String roleName,String roleLevel,String zoneId,String zoneName)
 			
以上接口为创建角色信息接口分别传入：角色ID。角色名称，角色等级, 区ID， 区名称。

			public void roleLevelUpZTGame(String roleId,String roleName,String zoneId,String zoneName,int level)

以上接口为角色等级升级信息接口：角色ID。角色名称, 区ID， 区名称， 等级。

			public boolean isHasSwitchAccountZTGame()
以上接口返回true则游戏需要添加一个切换账号按钮以方便用户切换账号，点击此按钮后调用switchAccountZTGame()  

			public void switchAccountZTGame()	
以上为切换账号接口功能，调用此接口执行切换账号操作

			public boolean isHasCenterZTGame()
			
以上为某些渠道判断是否存在用户中心按钮倘若返回false不作处理，倘若返回true需要显示用户中心按钮，点击此按钮后调用enterCenterZTGame()

			public void enterCenterZTGame()
以上为用户中心接口功能，调用此接口弹去渠道方的用户中心界面

###IZTLibBase 3.2.0 新增接口

		@Override
		public void onActivityResultZTGame(int requestCode, int resultCode, android.content.Intent data)			
	
		@Override
		public void onNewIntentZTGame(Intent intent)

以上为渠道需要在主Activity中调用的生命周期函数。

###~~IZTLibBase 3.3.0 新增接口~~

			public void callMethodByName(String methodName, JSONObject param_json)
			
`说明`  
~~之后的渠道新增接口，将通过此方法调用。~~

`参数`  			
			
String methodName：渠道模块的方法名  
JSONObject param_json： 为需要传入的参数 json  格式  key为函数名称  value 为值

###IZTLibBase 3.4.0 新增接口

			public void isHasQuitDialog()
			
`说明`  
是否有第三方渠道的退出确认弹出框，如果此函数返回true，请游戏不要弹出游戏自身的退出确认弹出框而是直接调用quitZTGame来弹出第三方的退出弹出框，在ZTGame_Quit回调内处理游戏的退出操作（销毁代码，而不是再次弹出退出确认对话框。返回false则按照游戏自己的退出流程处理即可。
			

###IZTLibBase 3.5.0 新增接口
			
			@Override
			public void onConfigurationChangedZTGame(Configuration newConfig)
			
以上为渠道需要在主Activity中调用的生命周期函数。	   	


###IZTLibBase 3.5.1 接口修正

在需要接入需要计费点信息的渠道，需要在ZTPayInfo中传入productID，为渠道的计费点信息


###base.jar 3.6.0版本新增接口

- 新增ZTBaseApplication类，该类继承自Application；
- 在ztgameframework.jar中新增类ZTApplication类，该类继承自ZTBaseApplication类；
- 新增推送消息SDK所需要的数据接口；
