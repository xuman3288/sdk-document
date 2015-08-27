# GiantMobile Android SDK API

**SDK Version：**	 Ver 4.4.5

## Environment

### Notes

> **Note 1**：
> 
> It is strongly recommended that in the generated apk package when using the SDK build-tools 19.1


> **Note 2**：
> 
> In `AndroidManifest.xml` File, all `android:name` properties needs to be set JAVA package full name. 


> **Note 3**：
> 
> Pleash sure the file `assets/ztsdk_config.properties` has this config.
> ~~~
> config.sdk.class=com.ztgame.ztgameframework.ZTgameFramework
> ~~~

### (Eclipse) IDE Libraries

* base.jar
* ztgameframework.jar

Add theirs to IDE libraries.


## Getting started with ZTgameFramework 


### Step 1: Init ZTgameFramework at `MainActivity`

~~~java
package com.example;

import android.app.Activity;
import android.os.Bundle;
import com.ztgame.mobileappsdk.common.IZTLibBase;
import com.ztgame.mobileappsdk.common.IZTListener;

public class MainActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {

		//Game init ...

		//Init
		IZTLibBase.newInstance(MainActivity.this);
		IZTLibBase.getInstance().initZTGame("5012","Game Name", false, mListener);
	}
}
~~~

#### com.ztgame.ztgameframework.initZTGame description

~~~java
/**
 * Init game info.
 *
 * Call it after IZTLibBase.newInstance(SomeActivity.this);
 * 
 * @param gameId   Set game id 
 * @param appName  Set app name
 * @param isLandScape Screen type.  
 *     - true  : Horizontal
 *     - false : Vertical
 * @param listener  When something event was finished call it.
 */
@Override
public void initZTGame(String gameId,String appName, boolean isLandScape, IZTListener listener) {}
~~~

#### com.ztgame.mobileappsdk.common.IZTListener description

~~~java
/**
 * When something ztgameframework event was finished call it.
 */
public interface IZTListener {

	/**
	 * Finished callback.
	 * 
	 * @param what Finished events type
     * @param errcode Error code.
     * If equal to 0 Success, then error.
     * 
	 * @param json_obj Finished callback info.
	 */
	public void onFinished(int what, int errcode, JSONObject json_obj);
}
~~~

**Parameter `What` values:**

| Consts                   | Descriptions |
|--------------------------|--------------|
| ZTConsts.ZTGAME_INIT     |Game was initialized. |
| ZTConsts.ZTGAME_LOGIN    |Account was logined.|
| ZTConsts.ZTGAME_PAY      | |
| ZTConsts.ZTGAME_QUIT     |Account was quit|
| ZTConsts.ZTGAME_LOGOUT   | |
| ZTConsts.ZTGAME_QQGROUP  |Tencent QQGroup|


**Parameter `json_obj` example:**

When `what == ZTConsts.ZTGAME_LOGIN` and `errcode == 0`, the `json_obj` returns:
~~~json
{
"mobile_type":"xxxxxx",
"token":"c814684cbf4f17e2dd0c169db997db7f",
"accid":"1-123456",
"imei":"xxxxxx",
"action":"login",
"account":"默认账号",
"mac":"xxxxxx",
"channel":1,
"ip":"xxxxxx"
}
~~~

接收到ZTGAME_LOGIN消息用于登录成功。其中accid和account 分别为：用户标示和账号,其中帐号统一处理为“默认帐号”。
	
   	  
  *	#####ZTGAME_INIT  
	ZTGAME_INIT是SDK初始化完成的通知，所有接口都需要在INIT成功完成之后才能开始调用  
  	
  * #####ZTGAME_LOGIN  
    ZTGAME_LOGIN需要处理登录返回的json_obj参数，交给游戏服务器进行效验
    
  * #####ZTGAME_PAY   
    ZTGAME_PAY只需要判断errcode为0成功还是-1失败，但是是否到账要以服务器通知为准
    
  * #####ZTGAME_QUIT
  	ZTGAME_QUIT是游戏进行销毁操作的地方，玩家在第三方退出框点击确认后会收到此回调
  		
  *	#####ZTGAME_LOGOUT
  	ZTGAME_LOGOUT是游戏进行登出操作/切换账号操作的地方，需要返回到游戏登录界面等待用户再次登录
		
  *	#####ZTGAME_QQGROUP
  	ZTGAME_QQGROUP是游戏中一键增加QQ群的消息，游戏收到该消息后，当errcode为0表示有一键加QQ功能，其他表示没有该功能

  *	#####ZTGAME_TENCENT_QUERY
  	ZTGAME_TENCENT_QUERY是腾讯游戏点查询接口的消息，游戏收到该消息后，当errcode为0表示有查询成功，需要游戏端根据查询金额判断是否补单

  *	#####ZTGAME_CREATE_ORDER
  	ZTGAME_CREATE_ORDER是创建订单成功的消息，返回的order_id参数

  	**`注：在游戏接收ZTGAME_LOGOUT后，调用登录接口之前前，建议调用isLogin接口做一次用户登录状态判断，如果true则不调用登录接口，否则调用。增加判断可防止多次弹出登录界面。`**
    		
 - ###1.2 登录接口（必接）
		public void loginZTGame(final String zoneId, final String zoneName, final boolean isAutoLogin)		

	**`参数:`**  
	`zoneId` : 服务器分区ID（建议大于0）  
	`zoneName` : 服务器分区名称  
	`isAutoLogin` : 是否自动登录，传true即可
	
	**调用方式：**	

		YourActivity.runOnUiThread(new Runnable() {
				public void run() {			
						 IZTLibBase.getInstance().loginZTGame("1", "zoneName"", true);
				}
		});
 - ###1.3 支付接口（必接）
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
	        payInfo.setMonthCard(true);    //Base4.4.1以上版本需要设置此参数用来表示是否为月卡
	        IZTLibBase.getInstance().payZTGame(payInfo);
	        
	        
   	**`说明`**  
   			`setAmount(int amount):`设置支付的价格，单位为分（若游戏中价格为元，请转换成分后在传入）；  
   			`setProductName(String)`:设置商品名称，如果是钻石就设置为`“钻石*数量”`，如果是月卡就设置为`月卡`,（特殊：华为渠道，支付时不能带*及其他特殊符号）；  
   			`payInfo.setMoneyName(String)`:设置游戏货币名称，如果是钻石就设置为`钻石`;  
   			`payInfo.setExchangeRatio(int)`:设置游戏兑换比率，1元对应多少游戏币，1：10就设置为10  
   			`setExtra(String)`:设置额外参数  
   			`payZTGame（ZTPayInfo payInfo）`:支付。   
   			`setProductId(String)` :部分运营商需要提供计费点通过此接口传入计费点。  
			`payInfo.setMonthCard(true);`:部分渠道需要得知是否道具是月卡
	#####ZTPayInfo支付信息接口：

		public void setProductName(String productName)	//设置商品名称
		public void setExtra(String extra)	//设置额外信息
		public void setAmount(int amount)	//设置商品价格，单位为分
		public void setProductName(String productName)	//设置商品名称
		public void setMoneyName(String moneyName)	//设置货币名称
		public void setExchangeRatio(int exchangeRatio)	//设置汇率比例


- ###1.4 登录完成数据统计接口（建议接入）
	登录流程结束后调用

		public void loginOkZTGame(String roleId,String roleName,String roleLevel,String zoneId,String zoneName)
	**`参数说明：`**  
	`roleId`:角色ID;  
	`roleName`:角色名称;  
	`roleLevel`:角色等级;   
	`zoneId`:服务器ID;  
	`zoneName`:服务器名称
	
- ###1.5 创建角色数据统计接口（建议接入）
	创建角色后调用数据统计接口

 			public void createRoleZTGame(String roleId,String roleName,String roleLevel,String zoneId,String zoneName)
 	**`参数说明：`**  
	`roleId`:角色ID;  
	`roleName`:角色名称;  
	`roleLevel`:角色等级;   
	`zoneId`:服务器ID;  
	`zoneName`:服务器名称	

- ###1.6 角色等级升级信息接口

		public void roleLevelUpZTGame(String roleId,String roleName,String zoneId,String zoneName,int level)

	**`参数说明：`**  
	`roleId`:角色ID;  
	`roleName`:角色名称;  
	`level`:角色等级;   
	`zoneId`:服务器ID;  
	`zoneName`:服务器名称	

- ###1.7 是否需要切换账号按钮接口
			public boolean isHasSwitchAccountZTGame()
以上接口返回true则游戏需要添加一个切换账号按钮以方便用户切换账号，点击此按钮后调用switchAccountZTGame()  

- ###1.8 切换账号操作
			public void switchAccountZTGame()	
	以上为切换账号接口功能，调用此接口执行切换账号操作，调用此接口后，会发送ZTGAME_LOGOUT消息。

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
- ###1.18 是否有一键加QQ群功能接口
		public void isHasJoinQQGroup()	
	调用实例：  
	IZTLibBase.getInstance().isHasJoinQQGroup();  
	`注：`接入此接口时，游戏会收到ZTGAME_QQGROUP消息，当errcode为0表示有一键加QQ功能，-1表示没有该功能。
- ###1.19 一键加QQ群接口
		public void joinQQGroup()	

	调用实例：  

		IZTLibBase.getInstance().joinQQGroup();  

	`注：`接入此接口时，需提供QQ群对应的key，key由QQ官方申请。`接入次接口前，请务必调用isHasJoinQQGroup()接口，并且在收到ZTGAME_QQGROUP消息后，errcode为0的情况下调用。`
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
			
			
			@Override
			public void onSaveInstanceState(Bundle outState) {
    			super.onSaveInstanceState(outState);
    			IZTLibBase.getInstance().onSaveInstanceState(outState);
    		}
 
####***以上为渠道需要在游戏主Activity中调用的生命周期函数。***####

------------------------------------------------------------
###Base 4.1.0 更新注意事项###

需要在AndroidManifest.xml中添加Application的节点属性。

	android:name="com.ztgame.ztgameframework.ZTApplication"
	
***如果你的工程里存在Application需要继承改Application 并且在onCreate函数中添加super.onCreate***

------------------------------------------------------------------
###Base 4.4.0 新增接口###
- 部分渠道需要支付回调结果接口


		public void payNotify(String amount, String order, String description) {
    		//Log.d("giant", "Pay Notify called" +  amount + order + description);
	    }


###Base 4.4.5 新增接口###
- 是否有一键加qq群功能接口

		public void isHasJoinQQGroup()

- 一键加QQ群接口

		public void joinQQGroup()

- 增加版本标识接口

		//获取SDK Version
		public String getSDKVersion() {
			return mSDKVersionName;
		}
	
		/**
		 * 
		 * @return
		 */
		public long getSDKVersionCode(){
			return mSDKVersionCode;
		}
		
		//	获取渠道sdk版本号
	    public String getChannelSDKVersion(){
	    	return CHANNEL_SDKVERSION;
	    }
		//	获取当前框架版本号
	    public String getFrameworkVersion(){
	    	return FRAMEWORK_VERSION;
	    }


###Base 4.4.6 新增接口###
- 设置腾讯登录类型

    	public void setTencentLoginType(int tencentLoginType)
		
	tencentLoginType：

		ZTConsts.TencentLoginType.QQ  //qq登录
		ZTConsts.TencentLoginType.WECHAT //微信登录

- 腾讯游戏点查询接口

    	public void queryTencentGamePoint()

   	查询成功会收到ZTGAME_TENCENT_QUERY消息

	返回的Json数据说明：	
	
	
> code	int	返回结果编号; 0 正确; 其它为错误	0
> 
> error	string / null	错误消息	
> 
> balance	string	腾讯账号余额, 单位元. (无法被消耗的余额, 需要游戏创建订单处理)	
> 
> auto_consumed	boolean	是否自动查询消费

-  补单接口

		public boolean createOrderZTGame(ZTPayInfo payInfo)

 	payInfo详细描述参见1.3