

## Unity插件下载

- [插件](http://docs.mztgame.com/files/unity3d/GASDKUnity_20160901.zip)
 

## Unity3d 官方SDK插件(Android)

---------------------------------------------------------
------------------------------------------------------------------
####  导入 GASDKUnity.unitypackage 全部（\Assets\import package\custom package）
![](http://docs.mztgame.com/files/assets/unityimg/giantsdk_unity_import.png)

####  接口说明

###### `初始化接口`  

```
  /// <summary>
  ///
  /// </summary>
  /// <param name="gameid">游戏id   游戏自传</param>
  /// <param name="appName">游戏名字  游戏自传</param>
  /// <param name="isLandScape">是否横屏  游戏自传</param>
  public void initGASDK(string gameid,string appName,bool isLandScape);
```
调用例子:

```
  GASDKManager.Instance.initGASDK("5014","testGameName",false);
```

------------------------------------------------------------------

###### `登录接口`

```
  /// <summary>
  /// 登录
  /// </summary>
  /// <param name="zoneId">区id 游戏自传</param>
  /// <param name="zoneName">区name 游戏自传</param>
  /// <param name="req">回调方法</param>
  public void loginAndroid(string zoneId,string zoneName,LoginStatusCallback req);

```    
参数说明：  

`zoneId` :可为空

`zoneName` :可为空

调用举例：可参考demo
```
  GASDKManager.Instance.loginAndroid("1","败走麦城区",logResult);
```

###### ` 注：在调用该接口之前，请务必先调用init接口。`

------------------------------------------------------------------

###### `支付接口`

```
/// <summary>
/// 支付
/// </summary>
/// <param name="zoneId"> 区id 游戏自传</param>
/// <param name="money">单位 分  游戏自传</param>
/// <param name="productId">商品id  游戏自传</param>
/// <param name="productName">商品名称  游戏自传</param>
/// <param name="req">回调方法</param>
public void payAndroid(string zoneId,int money,string productId,string productName,PayStatusCallback req);

```

参数说明：  

`zoneId` :不能为空

`productId` :不能为空

`productName`:不能为空

##### 开通微信支付须知

###### （1）需提供签名md5值和包名给我们平台，申请微信支付相关参数
###### （2）将 WXEntryActivity.java放置在游戏应用包名+.wxapi下面.

调用实例：可参考demo

```
  GASDKManager.Instance.payAndroid("1",100,"1","金币",logPayResult);
```

------------------------------------------------------------------

###### `切换账号`

```
/// <summary>
/// 切换账号
/// </summary>
/// <param name="req">回调方法</param>
public void switchAccountAndroid(LogoutCallback req)

```

调用实例：可参考demo

```
  GASDKManager.Instance.switchAccountAndroid(logSwitchUser);
```

## Unity3d 官方SDK插件(IOS)

 
 
------------------------------------------------------------------

#### 导入SDK文件

#### 添加资源及配置文件

在与Assets同级目录创建SDKFile目录，包含SDK的bundle和plist文件(也可在导出xcode工程后添加)

* #### SDK的bundle文件
  * GASDKResource.bundle

* #### 需要项目部分自定义的配置文件
  * ztsdk_config.plist

#### 添加静态库
在Assets/Plugins/iOS目录中，添加.a和.h文件
* #### 静态库
  * libZTLibGA.a

* #### Unity用的静态库头文件
  * IOSConnector.h

* #### 其它头文件
  * ZTConsts.h
  * ZTLibBase.h
  * ZTPayInfo.h
  * ZTSDKInfo.h
  * ZTServicePush.h

---
#### Unity部分

####设置libZTLibGA的Platform settings

![](http://docs.mztgame.com/files//assets/unityimg/11521753AC9B9074318B4E550BB29823.png)

##### 添加使用OC callback的MonoPInvokeCallbackAttribute.cs文件

```

using System;

// This attribute is used on static functions and it allows Mono's Ahead of Time Compiler
// to generate the code necessary to support native iOS code calling back into C# code.
public sealed class MonoPInvokeCallbackAttribute : Attribute
{

	public MonoPInvokeCallbackAttribute(Type type) {

	}
}

```
##### 创建IOSConnector.cs并导入System.Runtime.InteropServices;用来解析.a库
```

using UnityEngine;
using System.Collections;
using System;
using System.Runtime.InteropServices;

public class IOSConnector
{

	protected static IOSConnector _connector = null;

	public static IOSConnector Instance {

		get{
			if(_connector == null){

				_connector = new IOSConnector();

			}
			return _connector;
		}
	}
```

##### DllImport
```

  /// <summary>
	/// 注册初始化回调
	/// </summary>
	/// <param name="sdkInitCallback"> 初始化回调方法</param>
	[DllImport("__Internal")]
	static extern void registSdkInitCallback(SdkInitCallback sdkInitCallback);


	/// <summary>
	/// 注册显示和关闭SDK窗口的回调
	/// </summary>
	/// <param name="sdkViewDisplayCallback"> 显示和关闭SDK窗口的回调方法</param>
	[DllImport("__Internal")]
	static extern void registSdkViewDisplayCallback(SdkViewDisplayCallback sdkViewDisplayCallback);

	/// <summary>
	/// 注册登陆回调
	/// </summary>
	/// <param name="loginCallback"> 登陆回调方法</param>
	[DllImport("__Internal")]
	static extern void registLoginCallback(LoginCallback loginCallback);

	/// <summary>
	/// 注册支付回调
	/// </summary>
	/// <param name="payStatusCallback"> 支付回调方法</param>
	[DllImport("__Internal")]
	static extern void registPayStatusCallback(PayStatusCallback payStatusCallback);

	/// <summary>
	/// 注册登出回调
	/// </summary>
	/// <param name="logoutCallback"> 登出回调方法</param>
	[DllImport("__Internal")]
	static extern void registLogoutCallback(LogoutCallback logoutCallback);

	/// <summary>
	/// 注册生成新账号回调
	/// </summary>
	/// <param name="newRegistCallback"> 生成新账号回调方法</param>
	[DllImport("__Internal")]
	static extern void registNewRegistCallback(NewRegistCallback newRegistCallback);


	/// <summary>
	/// 注册获取账号绑定信息回调
	/// </summary>
	/// <param name="accountBindInfoCallback"> 获取账号绑定信息回调方法</param>
	[DllImport("__Internal")]
	static extern void registAccountBindInfoCallback(AccountBindInfoCallback accountBindInfoCallback);


	/// <summary>
	/// 注册更新LastGameZone操作回调
	/// </summary>
	/// <param name="updateLastGameZoneCallback"> 更新LastGameZone操作回调方法</param>
	[DllImport("__Internal")]
	static extern void registUpdateLastGameZoneCallback(UpdateLastGameZoneCallback updateLastGameZoneCallback);


	/// <summary>
	/// 注册获取LastGameZone操作回调
	/// </summary>
	/// <param name="updateLastGameZoneCallback"> 获取LastGameZone操作回调方法</param>
	[DllImport("__Internal")]
	static extern void registLoadLastGameZoneCallback(LoadLastGameZoneCallback loadLastGameZoneCallback);

	//打开调试模式
	[DllImport("__Internal")]
	public static extern void enableDebugMode();

	/// <summary>
	/// 初始化sdk
	/// </summary>
	/// <param name="gameid">游戏id</param>
	/// <param name="appName">游戏名字</param>
	[DllImport("__Internal")]
	public static extern void initWithGameId(string gameId, string gameName); // const char* 对应 string

	/// <summary>
	/// 登陆
	/// </summary>
	/// <param name="autoLogin">是否自动登陆</param>
	[DllImport("__Internal")]
	public static extern void login(bool autoLogin);

	/// <summary>
	/// 支付
	/// </summary>
	/// <param name="productId"> 商品id</param>
	/// <param name="zoneId"> 游戏区id</param>
	/// <param name="productName">商品名称</param>
	/// <param name="productPrice">商品价格(单位 分)</param>
	/// <param name="productCount">数量(1)</param>
	/// <param name="exchangeRate">兑换比例(1)</param>
	/// <param name="extra">扩展信息</param>
	[DllImport("__Internal")]
	public static extern void payZtgameWithProductId(string productId, string zoneId, string productName, int productPrice, int productCount, int exchangeRate, string extra)

	/// <summary>
	/// 设置游戏区ID
	/// </summary>
	/// <param name="zoneId">当前游戏区id</param>
	[DllImport("__Internal")]
	public static extern void setZoneID(string zoneId);

	/// <summary>
	/// 切换账号
	/// </summary>
	[DllImport("__Internal")]
	public static extern void switchAccount();

	/// <summary>
	/// 单独获取用户绑定信息接口 如果没有绑定跳出绑定界面
	/// </summary>
	[DllImport("__Internal")]
	public static extern void getAccountBindingInfo();


	/// <summary>
	/// 是否已登陆
	/// </summary>
	[DllImport("__Internal")]
	public static extern bool isLogined();

	/// <summary>
	/// 是否游客账号
	/// </summary>
	[DllImport("__Internal")]
	public static extern bool isTrialAccount();

	/// <summary>
	/// 账号是否已绑定手机
	/// </summary>
	[DllImport("__Internal")]
	public static extern bool isUserMobileBinded();

	//AppDelegate
	[DllImport("__Internal")]
	public static extern void applicationWillEnterForegroundZTGame();

	//AppDelegate
	[DllImport("__Internal")]
	public static extern void applicationDidBecomeActiveZTGame();

	//AppDelegate
	[DllImport("__Internal")]
	public static extern void applicationDidEnterBackgroundZTGame();

	/// <summary>
	/// 显示登陆界面
	/// </summary>
	[DllImport("__Internal")]
	public static extern void loginWithUI();

	/// <summary>
	/// 上传最后登陆的游戏区号
	/// </summary>
	[DllImport("__Internal")]
	public static extern void updateLastGameZone(string zoneId);
```

##### 初始化回调

```

// delegate
  /// <summary>
	/// 初始化回调
	/// </summary>
	/// <param name="status"> 初始化完成状态</param>
	[DllImport("__Internal")]
	delegate void SdkInitCallback(bool status);

	//SDK初始化回调
	[MonoPInvokeCallback(typeof(SdkInitCallback))]
	void SdkInitCallbackFunc(bool status)
	{
		Debug.Log ("#CallbackFunc# SdkInitCallback status:" + status);
		//Init成功后才可以调用登陆
	}
```



#####  SDK View变化回调
```

  /// <summary>
	/// SDK View变化回调
	/// </summary>
	/// <param name="isShow">sdk view显示或隐藏</param>
  delegate void SdkViewDisplayCallback(bool isShow);

	[MonoPInvokeCallback(typeof(SdkViewDisplayCallback))]
	void SdkViewDisplayCallbackFunc(bool isShow)
	{
		Debug.Log ("#CallbackFunc# SdkViewDisplayCallback");
	}
```

##### 登陆回调
```

  /// <summary>
	/// 登陆回调
	/// </summary>
	/// <param name="retCode">返回状态码,0为成功,其余为失败(当retCode=2时，可利用json数据在游戏服务端做离线验证，服务端需做相应开发)</param>
	/// <param name="accid">账号id</param>
	/// <param name="json">离线验证数据</param>
  delegate void LoginCallback(int retCode, string accid, string token, string json);

  [MonoPInvokeCallback(typeof(LoginCallback))]
	void LoginCallbackFunc(int retCode, string accid, string token, string json)
	{
		Debug.Log ("#CallbackFunc# LoginCallback retCode:" + retCode + " accid:" + accid + " token:" + token + " json:" + json);
	}
```

##### 支付回调

```

  /// <summary>
	/// 支付回调
	/// </summary>
	/// <param name="retCode">返回状态码,0为成功,其余为失败</param>
	delegate void PayStatusCallback(int retCode);

  [MonoPInvokeCallback(typeof(PayStatusCallback))]
	void PayStatusCallbackFunc(int retCode)
	{
		Debug.Log ("#CallbackFunc# PayStatusCallback retCode:" + retCode);
	}
```

##### 登出回调
```

  /// <summary>
	/// 登出回调
	/// </summary>
	/// <param name="retCode">返回状态码,0为成功,其余为失败</param>
	delegate void LogoutCallback(bool status);

  [MonoPInvokeCallback(typeof(LogoutCallback))]
	void LogoutCallbackFunc(bool status)
	{
		Debug.Log ("#CallbackFunc# LogoutCallback status:" + status);
	}
```

##### 产生新账号回调
```

  /// <summary>
	/// 产生新账号回调
	/// </summary>
	/// <param name="account">新账号名</param>
	delegate void NewRegistCallback(string account);

  [MonoPInvokeCallback(typeof(NewRegistCallback))]
	void NewRegistCallbackFunc(string account)
	{
		Debug.Log ("#CallbackFunc# NewRegistCallback account:" + account);
	}
```

##### 获取账号绑定信息回调
```

  /// <summary>
	/// 获取账号绑定信息回调
	/// </summary>
	/// <param name="retCode">返回状态码,0为成功,其余为失败</param>
	/// <param name="mobileNum">手机号</param>
	delegate void AccountBindInfoCallback(int retCode, string mobileNum);

  [MonoPInvokeCallback(typeof(AccountBindInfoCallback))]
	void AccountBindInfoCallbackFunc(int retCode, string mobileNum)
	{
		Debug.Log ("#CallbackFunc# AccountBindInfoCallback retCode:" + retCode + " mobileNum:" + mobileNum);
	}
```

##### 更新最后一次登陆的游戏区信息的回调
```

  /// <summary>
	/// 更新最后一次登陆的游戏区信息的回调
	/// </summary>
	/// <param name="status">是否成功</param>
	delegate void UpdateLastGameZoneCallback(bool status);

	[MonoPInvokeCallback(typeof(UpdateLastGameZoneCallback))]
	void UpdateLastGameZoneCallbackFunc(bool status)
	{
		Debug.Log ("#CallbackFunc# UpdateLastGameZoneCallback status:" + status);
	}
```

##### 获取最后一次登陆的游戏区信息的回调
```

  /// <summary>
	/// 获取账号绑定信息回调
	/// </summary>
	/// <param name="status">是否成功</param>
	/// <param name="zoneId">区id</param>
  delegate void LoadLastGameZoneCallback(bool status, string zoneId);

  [MonoPInvokeCallback(typeof(LoadLastGameZoneCallback))]
	void LoadLastGameZoneCallbackFunc(bool status, string zoneId)
	{
		Debug.Log ("#CallbackFunc# LoadLastGameZoneCallback status:" + status + " zoneId:" + zoneId);
	}
```

---
##### 调用实例
##### 注册所有回调
```

	public void RegistAllCallback()
	{
		Debug.Log ("Regist all callbacks");
		registSdkInitCallback (SdkInitCallbackFunc);
		registSdkViewDisplayCallback (SdkViewDisplayCallbackFunc);
		registLoginCallback (LoginCallbackFunc);
		registPayStatusCallback (PayStatusCallbackFunc);
		registLogoutCallback (LogoutCallbackFunc);
		registNewRegistCallback (NewRegistCallbackFunc);
		registAccountBindInfoCallback (AccountBindInfoCallbackFunc);
		registUpdateLastGameZoneCallback (UpdateLastGameZoneCallbackFunc);
		registLoadLastGameZoneCallback (LoadLastGameZoneCallbackFunc);
	}
```

##### Demo
```

public class DemoApp : MonoBehaviour {

	// Use this for initialization
	void  Awake() {
		Debug.Log ("在执行初始化操作前先注册所有回调");
		IOSManager.Instance.RegistAllCallback ();

		Debug.Log("SDK初始化");
		IOSManager.initWithGameId ("5016", "gameName");


	}

	public void loginGA(){

		Debug.Log("登陆，未登陆过显示登陆界面，已登陆过则用上一次登陆的账号自动登陆");

		IOSManager.login();

	}

	public void switchUser(){
		Debug.Log("切换账号");
		IOSManager.switchAccount();

	}
}
```
---
####  导出Xcode后检查工程设置
####  Build Settings设置

在Links->Other Linker Flags中需要包含**-ObjC**，否则会出现“_unrecognized selector sent to class_”描述的错误。

##### 需要包含以下Frameworks和Libraries
* #### Frameworks
  * AudioToolbox.framework
  * CoreLocation.framework
  * AVFoundation.framework
  * MessageUI.framework
  * EventKitUI.framework
  * AdSupport.framework
  * StoreKit.framework
  * CoreTelephony.framework
  * SystemConfiguration.framework
  * EventKit.framework
  * MediaPlayer.framework

* ##### Libraries
  * libstdc++.tbd
  * libsqlite3.tbd
  * libz.tbd

####必须包含自定义配置文件和资源文件
检查Build Phases->Copy Bundle Resources
  
  * ztsdk_config.plist
  * GASDKResource.bundle
