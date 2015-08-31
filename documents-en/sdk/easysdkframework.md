# GiantMobile Android SDK API

**SDK Version：**	 Ver 4.4.5

## Classes reference.

#### Class `com.ztgame.mobileappsdk.common.IZTLibBase` description

~~~java
package com.ztgame.mobileappsdk.common;

public abstract class IZTLibBase {
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
	public void initZTGame(String gameId, String appName, boolean isLandScape, IZTListener listener) {}

	/**
	 * 
	 * @param zoneId   Game zone ID
	 * @param zoneName Game zone name
	 * @param isAutoLogin Player auto login. Always set true.
	 */
	public void loginZTGame(String zoneId, String zoneName, boolean isAutoLogin) {}

	/**
     * Call payment information.
     * 
     * @param payInfo Init product payment information.
     */
	final public boolean payZTGame(ZTPayInfo payInfo) {}


	/**
     * Is has switch account button
     * If it is true then the game must be has a switch account button. 
     * And call `switchAccountZTGame()` when player touched.
     */
    public boolean isHasSwitchAccountZTGame() {}

	/**
     * Call switch account.
     * Send a `ZTGAME_LOGOUT` signal when calling this method.
     */
	public void switchAccountZTGame(){}

    /**
     * Is has account center button in game UI
     * Some vendor has account center button.
     * If return true must be show account center button. And goto the vendor's account center UI when player touched.
     */
	public boolean isHasCenterZTGame(){}


	/**
     * Goto the vendor's account center UI.
     */
	public void enterCenterZTGame(){}


	/**
     * Is has vendor's exit dialog.
	 *  
	 * Notes: 
	 * 1. If return ture, mean vendor has exit dialog. 
	 * 2. Don't popup yourself exit dialog if return true.
	 * 3. At method `IZTListener.onfinished()` todo exit program.
     */
	public void isHasQuitDialog(){}

    /**
     * Popup vendor's exit dialog
     */
	public void quitZTGame(){}


	/**
     * Enable debug mode.
     * Don't enable on production.
     */
	public void enableDebugMode(){}

	/**
     * Get vendor ID
     * 
     * Vendors ID list.
     * @see http://wiki.mztgame.com/index.php/%E6%B8%A0%E9%81%93%E4%BF%A1%E6%81%AF%E5%88%97%E8%A1%A8
     */
    public int getPlatform(){}


	/**
     * Set game zone ID
     * Set the zone ID when player switched the zone.
     */
	public void setZoneId(final String zoneId){}

	/**
     * Set current Activity.
     * If the game Activity switched, set it.
     */
	public void setActivity(Activity activity){}

	/**
     * Player is logined.
     */
	public boolean isLogined(){}


	/**
     * Is has join Tencent QQGroup interface
     * Note: 
     * Send a `ZTGAME_QQGROUP` signal when calling this method.
     * If `errcode` is equal to 0 call `joinQQGroup()`.
     */
	public void isHasJoinQQGroup(){}

	/**
     * Join Tencent QQGroup interface
     * 
     * Notes: 
     * 1. The QQGroup key get from Tencent QQ. 
     * 2. Call it after `isHasJoinQQGroup()`.
     * 3. Call it in `IZTListener.onFinished()` get a `ZTGAME_QQGROUP` signal and errcode is equal to 0.
     * 
     * @example:
     * <code>
     *     IZTLibBase.getInstance().joinQQGroup();
     * </code>
     */
	public void joinQQGroup(){}


	/**
	 * Character enter the game world
	 * Optional call it.
	 * 
	 * When character enter the game world call it.
	 * 
	 * @param roleId     Character ID
	 * @param roleName   Character name
	 * @param roleLevel  Character level
	 * @param zoneId     Game zone ID 
	 * @param zoneName   Game zone name
	 */
	public void loginOkZTGame(String roleId,String roleName,String roleLevel,String zoneId,String zoneName) {}

	/**
	 * Player create a character.
	 * Optional call it.
	 * 
	 * When player create a character call it.
	 * 
	 * @param roleId     Character ID
	 * @param roleName   Character name
	 * @param roleLevel  Character level
	 * @param zoneId     Game zone ID 
	 * @param zoneName   Game zone name
	 */
	public void createRoleZTGame(String roleId,String roleName,String roleLevel,String zoneId,String zoneName) {}

	/**
     * Character level up.
     * Optional call it.
     * 
	 * @param roleId     Character ID
	 * @param roleName   Character name
	 * @param zoneId     Game zone ID 
	 * @param zoneName   Game zone name
     * @param level      Character level
     */
    public void roleLevelUpZTGame(String roleId,String roleName,String zoneId,String zoneName,int level){}
}
~~~


#### Interface `com.ztgame.mobileappsdk.common.IZTListener` description

~~~java
/**
 * When something ztgameframework event was finished to call it.
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

##### Parameter `what`

Values:

| Consts                   | Descriptions |
|--------------------------|--------------|
| ZTConsts.ZTGAME_INIT     | SDK initialized. |
| ZTConsts.ZTGAME_LOGIN    | When account was logined. Todo submit `accid` and `token` to game server, and game server todo [CheckToken](http://docs.mztgame.com/docs/sdk/server_guide#__2 "CheckToken") |
| ZTConsts.ZTGAME_PAY      | Payment finished event. If the `errorcode` is equal to 0 mean success, else mean failed. Player add gold/coin/point to see [Server payment guide](http://docs.mztgame.com/docs/sdk/server_guide#__7) |
| ZTConsts.ZTGAME_QUIT     | Provider opened exit dialog. Call it when players touch confirm button. |
| ZTConsts.ZTGAME_LOGOUT   | When palyer trigger logout. (Player may be to do switch account) |
| ZTConsts.ZTGAME_QQGROUP  | If your app use Tencent QQGroup library. Call it when players click "Join QQGroup" button.|
| ZTConsts.ZTGAME_TENCENT_QUERY  | When the provider is Tencent, and call `queryTencentGamePoint` finished.|
| ZTConsts.ZTGAME_CREATE_ORDER  | Call it when players touch some product and ZTgameframework call create order finished. |


###### Case `ZTConsts.ZTGAME_LOGIN` the `json_obj` values:

~~~json
{
	"mobile_type":"xxxxxx",
	"token":"c814684cbf4f17e2dd0c169db997db7f",
	"accid":"1-123456",
	"imei":"xxxxxx",
	"action":"login",
	"account":"xxxxxx",
	"mac":"xxxxxx",
	"channel":1,
	"ip":"xxxxxx"
}
~~~

> **Note:**
> 
> `accid` is unique identify. GiantMobile server mean `openid`.
> `account` may be empty string.


###### Case `ZTConsts.ZTGAME_TENCENT_QUERY` the `json_obj` values:

~~~json
{
	"code": 0,
	"error":"Error message.",
	"balance":"0",
	"auto_consumed":false
}
~~~

| Parameters |  Type         | Description                            |
|-----------|---------------|----------------------------------|
| code      | int           | Status code. if is equal to 0 success then failed. |
| error     | string / null | Error message. When code returned not equal to zero.        |
| balance   | string        | Tencent account balance.               |
| auto_consumed | boolean   | Auto consumed. |


###### Case `ZTConsts.ZTGAME_LOGOUT`:

> **Note:**
> 
> Ensure player is logined before call `IZTLibBase.getInstance().loginZTGame()`.

#### Class `com.ztgame.mobileappsdk.common.ZTPayInfo`'s methods description

| Method | Required / Optional / Deprecated | Description |
|--------|----------------------------------|----------------------------|
| setAmount | Required  | Set product amount (RMB). |
| setExchangeRatio | Deprecated  | ... |
| setExtra | Required | Set developer payload.  |
| setMoneyName | Deprecated | Set game coin name.  |
| setMonthCard | Deprecated | ...  |
| setProductName | Optional  | Set product name. |
| setProductId | Optional  | Set product id. |


## Getting started with ZTgameFramework 

### Step 1. Environment


* Import the libs(`base.jar`, `ztgameframework.jar`) and res files of Giant SDK to your game project.
* Add the following permissions in the Androidmenifest.xml file of your game project:
~~~XML
<uses-permission android:name="android.permission.WRITE_SETTINGS" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.CALL_PHONE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
<uses-permission android:name="android.permission.GET_TASKS" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.SEND_SMS" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.RECEIVE_SMS" />
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
~~~


* Add following attribute in the application tag(the tag of <application/>)
~~~
android:name="com.ztgame.ztgameframework.ZTApplication"
~~~

> Note：if your game needs a customized application, it is required to extend this class:
com.ztgame.ztgameframework.ZTApplication

Remember to call the super.onCreate() in the onCreate method of your application.


* Copy the files in the asserts folder from demo project to your game project. Among them, the file of ztsdk_config.properties is like this:

~~~
config.sdk.class=com.ztgame.mobileappsdk.ga.ZTLibGA
config.domain.passport.legacy=http://passport.mztgame.com
config.domain.passport=http://passport.mobileztgame.com
config.domain.pay=http://pay.mobileztgame.com
config.ui.transparentbg=1
config.ui.hidemibaolingpwd=0
config.ui.hidechangepwd=0
config.ui.hidefindpwd=0
config.ui.hideregpwd=0
config.ui.hideloginpwd=0
#config.ui.css=
#config.ui.css=.close{display:none;} .header{display:none !important;}
config.ui.css=#login_close{display:none;}
config.pay.version=1.0.4
config.appid=5016 ###5016
config.paykey=p0emh5SFwBvpDbT ###your own payKey
config.paypid=10 ###your own paypid
config.channel_id=1
#config.ad_id=10000000000
~~~

> **Note 1**：
> 
> In `AndroidManifest.xml` file, all `android:name` properties needs to be set JAVA package full name. 

> **Note 2**：
> 
> Please sure the file `assets/ztsdk_config.properties` has this config.
> ~~~
> config.sdk.class=com.ztgame.ztgameframework.ZTgameFramework
> ~~~

> **Note 3**：
> 
> If you want to extends `com.ztgame.ztgameframework.ZTApplication`. Call `super.onCreate()` at your `onCreate`'s first line.


### Step 2. Init ZTgameFramework at `MainActivity`

#### Example:
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

	private IZTListener mListener = new IZTListener() {
		@Override
		public void onFinished(int what, int errcode, JSONObject json_obj) {

			switch (what) {
			case ZTConsts.ZTGAME_LOGIN:
				if (errcode == 0) {
					//Todo login success
					IZTLibBase.getInstance().loginOkZTGame("roleId", "roleName", "roleLevel", "zoneId", "zoneName");
				} else {
					//Todo login failed
				}
				break;
			case ZTConsts.ZTGAME_INIT:
				if (errcode == 0) {
					//Todo initialized.
				} else {
					//Todo failed
				}
				break;
			case ZTConsts.ZTGAME_PAY:
				if(errcode == 0){
					//Todo success
				}else{
					//Todo failed
				}
				break;
			case ZTConsts.ZTGAME_QUIT:
				if(errcode == 0){
				} else {
				}
				break;
			case ZTConsts.ZTGAME_LOGOUT:
				if(!IZTLibBase.getInstance().isLogined()){
					IZTLibBase.getInstance().loginZTGame("1", "Game area", true);
				}
				break;
			case ZTConsts.ZTGAME_QQGROUP: //Tencent QQGroup library
				if(errcode == 0){
					joinQQButton.setVisibility(View.VISIBLE);
				}
				break;
			}
		}
	};
}
~~~


### Step 3. Login

Call login event when the game UI has been ready.

**For example：**

~~~java
package com.example;

import android.app.Activity;
import android.view.View;

import com.ztgame.mobileappsdk.common.IZTLibBase;
import com.ztgame.mobileappsdk.common.ZTConsts;

public class MainActivity extends Activity {

	//...

	@Override
	public void onClick(View v) {
		//Player touch a enter the game button
		switch (v.getId()) {
			case R.id.enterGameWorldButton:
            	IZTLibBase.getInstance().loginZTGame("1", "ZoneName", true);
				break;
			case R.id.exitButton:
				//exit ui of channels'
				if(IZTLibBase.getInstance().isHasQuitDialog()){
					IZTLibBase.getInstance().quitZTGame();
				}else{
					//The exit ui of game's.
				}
				
				break;
			case R.id.accountCenterButton:
				IZTLibBase.getInstance().enterCenterZTGame();
				break;
			case R.id.switchAccountButton:
				IZTLibBase.getInstance().switchAccountZTGame();
				break;
			//...
		}
	}

	//...
}
~~~

Or call it at `Runnable`

~~~java
	YourActivity.runOnUiThread(new Runnable() {
			public void run() {			
					 IZTLibBase.getInstance().loginZTGame("1", "zoneName"", true);
			}
	});
~~~


### Step 4. Payment

When player touch a buy product button to call `IZTLibBase.getInstance().payZTGame(ZTPayInfo payInfo)`

#### For example: 

~~~java
package com.example;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.ztgame.mobileappsdk.common.IZTLibBase;
import com.ztgame.mobileappsdk.common.ZTConsts;
import com.ztgame.mobileappsdk.common.ZTPayInfo;

public class MainActivity extends Activity {
	@Override
	public void onClick(View v) {
		//Player touch a buy product button
		switch (v.getId()) {
			case R.id.buyButton:
				if(IZTLibBase.getInstance().isLogined()){
					ZTPayInfo payInfo = new ZTPayInfo();
					payInfo.setAmount(100);  //Set RMB
					payInfo.setProductName("test item"); // Set product name.
					payInfo.setProductId("1001"); // Set product id.
					payInfo.setExtra("1"); // Set developer payload.
					IZTLibBase.getInstance().payZTGame(payInfo);
				} else {
					Toast.makeText(MainActivity.this, "Please login.", Toast.LENGTH_SHORT).show();
				}
				break;
			//...
		}
	}
}
~~~

### Step 5. Bind life-cycle methods

~~~java
package com.example;

import android.app.Activity;
import com.ztgame.mobileappsdk.common.IZTLibBase;

public class MainActivity extends Activity {
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
}
~~~


## Changelogs

### 4.4.0

* Add `payNotify()` method

~~~java
	public void payNotify(String amount, String order, String description) {
		Log.d("giant", "Pay Notify called" +  amount + order + description);
    }
~~~

### 4.4.5

* Add Tencent join QQGroup feature.

~~~java
	public void isHasJoinQQGroup();
	public void joinQQGroup();
~~~

* Add `getSDKVersion()` method

~~~java
	/**
     * Get SDK version
     */
	public String getSDKVersion() {
		return mSDKVersionName;
	}

	public long getSDKVersionCode(){
		return mSDKVersionCode;
	}
	
	/**
     * Get vendor's SDK version.
     */
    public String getChannelSDKVersion(){
    	return CHANNEL_SDKVERSION;
    }

    /**
     * Get current framework version
     */
    public String getFrameworkVersion(){
    	return FRAMEWORK_VERSION;
    }
~~~

### 4.4.6

* Add set Tencent login type.

~~~java
	public void setTencentLoginType(int tencentLoginType)
~~~

Login types:

~~~java
	ZTConsts.TencentLoginType.QQ;
	ZTConsts.TencentLoginType.WECHAT;
~~~

* Add query Tencent balance interface.

~~~java
	public void queryTencentGamePoint()

	public boolean createOrderZTGame(ZTPayInfo payInfo)
~~~
