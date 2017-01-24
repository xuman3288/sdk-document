

## Unity插件下载

- [巨人官方插件_2.0.4(Android和ios)](http://docs.mztgame.com/files/unity3d/GASDKUnity_2.0.4.zip)

  ***GASDK2.0.4 说明:***

    >android， ios有界面,适用于巨人官方渠道出包 和公司游戏测试使用.


- [渠道母包插件_1.0.1(Android)](http://docs.mztgame.com/files/unity3d/GASDKUnity_Android_母包_1.0.1.zip)

 ***GASDK_Android_1.0.1说明:***
  >纯android母包接口。

  >无界面，需要打包工具，出各渠道包。

  >避免和官方插件在一个分支，避免资源重复等问题。

- [合体插件_1.0.0（包括IOS官方,Android渠道）](http://docs.mztgame.com/files/unity3d/GASDKAll_1.0.0_20161229.zip)

 ***GASDKAll_1.0.0说明:***
 
 >ios 含巨人官方渠道资源，有UI界面，导出工程可以直接使用

 >android 不含巨人官方资源，导出无UI界面，需要用平台打包工具，出各渠道包。
  
 >适合上国内渠道的游戏
  
------------------------------------------------------------------

## 开发前准备


#### [可参考testLogin.cs](/docs/sdk/cs)



## 巨人官方插件接口

------------------------------------------------------------------


###### `初始化接口`  (必接)

```
 //初始化 【游戏ID[需要向巨人平台申请]，游戏名字,true横屏 false 竖屏) 】
  public void InitGASDK(string gameid,string appName,bool isLandScape);
```
调用例子:

```
  GASDKManager.Instance.InitGASDK("5014","testGameName",false);
```

------------------------------------------------------------------

###### `登录接口`(必接)

```
  //登录【是否自动登录（true自动登录，false不自动登录），登录回调】
  public void Login(bool,LoginStatusCallback req);

  //登录回调  retCode 0 成功，1 失败  -1 错误。
  public delegate void GALoginCallback(int retCode,string json)
```    
 调用举例：
```
  GASDKManager.Instance.Login(true,LogResult);
  pulic void LogResult(int code,string json){
    json 数据格式如下
  }

  //登录返回json格式
  {
    "account" : "4f*****sm",
    "pt_id" : 2,
    "sign" : "a\/wE7oX8hx6TEjCfsTm4ziqQjkV51WmRoNOLmud0W5XDohsiL\/WHpcOoMKE72yTrD+mADgUTD31Bfmnthb83AZmCcNcnGU4zg5eWbH3lNEMYwTsIDUxYrm9L6GBf18BaRdWNDSVSo20Qd2ckoer0dUzsSDbCKAXw48UyjX5kgEY\/dBW5PwwWWs+nM7vCJqiFMHKtx3RdJAvhDsIeJpL7xdQo5gzdXRi4qRqzAUyDnAvB1QdpcEwoZ3R\/XbKmiWIBbFBhZTqztLc65s8sD5ZBSVMrkSfuuxXNO2TiyV8LvomCZNwqcE2ks5wCLGUAmIX87ZW5vmgn5deUYsxcoGHSxg==",
    "entity" : {
      "time" : 1482983893,
      "account" : "_q_4ff8apgsm",
      "openid" : "1-1990027331"
    },
    "accid" : "1-1990027331",
    "mac" : "",
    "token" : "uc18c22521fde898a92e7fcb20oixhp1",
    "device_type" : "iPhone4,1",
    "imei" : "F320902B-B071-4224-AAB5-F51B1E027AB5",
    "ip" : "192.168.240.239"
  }

```

###### ` 注：在调用该接口之前，请务必先调用init接口。`

------------------------------------------------------------------

###### `支付接口`(必接)

```
//支付（参考支付参数，支付回调）
GASDKManager.Instance.Pay(GAPayInfo,callback);

//支付回调 retCode 0 成功 1 失败
public delegate void PayCallback(int retCode);

```

参数说明：  
支付类参数：
```
public class GAPayInfo{

 public string zoneId;//区ID （必填）
 public string productId;//商品id （必填）
 public string productName;// 商品名字 （必填）
 public int productPrice;// 商品价格 （必填）
 public int productCount; // 商品数量 （必填）
 public int exchangeRate = 1; // 商品兑换比例 （默认是1）
 public string extra ;// 拓展信息 （可为空）

 }
```
##### 开通微信支付须知

###### （1）需提供签名md5值和包名给我们平台，申请微信支付相关参数
###### （2）将 WXEntryActivity.java放置在游戏应用包名+.wxapi下面.

调用实例：可参考demo

```
  GASDKManager.Instance.Pay(new GAPayInfo("zoneId","productId","productName",100,1,1,""),LogPayResult);
  pulic void LogPayResult(int code){

  }
```

------------------------------------------------------------------

###### `切换账号`(必接)

```

//切换账号
 public void SwitchUser();

```

调用实例：可参考demo

```
GASDKManager.Instance.SwitchUser();
```
###### `用户中心`(必接)
```
  /**
  *用户中心
  **/
  public void UserCenter();

```
调用实例：可参考demo

```
  GASDKManager.Instance.UserCenter();
```
###### ` 开启日志输出接口`

```
   //开启日志输出接口   游戏上线前需要注释该行代码以关闭日志输出
   public void EnableDebugGA();
```
调用实例：可参考demo

```
  GASDKManager.Instance.EnableDebugGA();
```

##  渠道母包通用接口
![](http://docs.mztgame.com/files/assets/unityimg/unity_gasdk_android_parent.png)

###### `初始化接口(必接)`
###### `登录接口(必接)`
###### `支付接口(必接)`
###### `切换账号(必接)`
###### `用户中心(必接)`
以上接口说明可参考官方sdk(Android)

#### [可参考TestGaParentPackage.cs](/docs/sdk/csParent)

###### `是否需要用户中心按钮接口(必接)`

```
   /**
    * 是否需要用户中心按钮接口
    *
    * 以上为某些渠道判断是否存在用户中心按钮倘若返回false不作处理，倘若返回true需要显示*用户中心按钮，
    HasUserCenter()
    */

    public void HasUserCenter(GAIsHasUserCenterCallback  req);
```
调用实例：可参考demo

```
  GASDKManager.Instance.HasUserCenter(logHasUserCenter);
```
###### `是否需要切换账号按钮接口(必接)`

```
  /**
    * 是否需要切换账号按钮接口
    * 以上接口返回true则游戏需要添加一个切换账号按钮以方便用户切换账号.
    * 点击此按钮后调用SwitchAccount()
    */

    public void HasSwitchUser(GAIsHasSwitchUserCallback req)
```
调用实例：可参考demo

```
   GASDKManager.Instance.HasSwitchUser(logHasSwitchUserCenter);
```

###### `是否有第三方渠道的退出确认弹出框（必接）`

```
  /**
   * 是否有第三方渠道的退出确认弹出框（必接）
   *  
   * 如果此函数返回true，请游戏不要弹出游戏自身的退出确认弹出框
   而是直接调用quitZTGameAndroid来弹出第三方的退出弹出框，
   在回调返回状态码:4 ,处理游戏的退出操作
   * （销毁代码，而不是再次弹出退出确认对话框。返回false则按照游戏自己的退出流程处理即可。)
   */
    public void HasQuitDialog(GAIsHasQuitDialogCallback req)
```
调用实例：可参考demo

```
  GASDKManager.Instance.HasQuitDialog(logHasQuitDialog);
```

###### `弹出第三方退出弹出 确认框接口（必接）`

```
  /**
   * 弹出第三方退出弹出 确认框接口（必接）
   */
   public void QuitGameDialog(GAQuitGameZTCallback req);

```
调用实例：可参考demo

```
   GASDKManager.Instance.QuitGameDialog(logQuitDialog);
```
###### ` 开启日志输出接口`

```
  /**
   * 开启日志输出接口
   * 游戏上线前需要注释该行代码以关闭日志输出
   *
   */
   public void EnableDebugGA();
```
调用实例：可参考demo

```
  GASDKManager.Instance.EnableDebugGA();
```
###### `获取渠道id`

```
  /**
    * 获取渠道id
    *
    * 渠道id以及游戏id可以统一从此wiki链接查询.
    * @see http://wiki.mztgame.com/index.php/%E6%B8%A0%E9%81%93%E4%BF%A1%E6%81%AF%E5%88%97%E8%A1%A8
    */
   public void GetPlatFormId(GAGetPlatFormIdCallback req)
```
调用实例：可参考demo

```
   GASDKManager.Instance.GetPlatFormId(logGetPlatFormId);
```
###### `更新服务器id`

```
  /**
   * 更新服务器id
   * 玩家切换服务器后需要调用此方法更新当前服务器id
   */
   public void SetZoneId(string zoneId)
```
调用实例：可参考demo

```
  GASDKManager.Instance.SetZoneId("11");
```
###### `更新当前活动Activity`

```
    /**
    * 更新当前活动Activity
    * 如果游戏当前Activity变更，需要更新activity到SDK
    */
    public void SetActivity();
```
调用实例：可参考demo

```
   GASDKManager.Instance.SetActivity();
```
###### `是否已经登录`

```
    /**
     * 是否已经登录
     */
    public void IsLogined(GAIsLoginedCallback req)
```
调用实例：可参考demo

```
   GASDKManager.Instance.IsLogined(logisLogined);
```
###### `登录完成数据统计接口（必接）`

```
  /**
    *登录完成数据统计接口（必接）
    角色进入游戏后调用
    **/
    public void LoginOkZTGame(string roleId,
      string roleName, string roleLevel,
      string zoneId, string zoneName)
```
调用实例：可参考demo

```
 GASDKManager.Instance.LoginOkZTGame("1","角色名","角色等级","11","区名");
```
###### `创建角色数据统计接口（必接）`

```
  /***
    创建角色数据统计接口（必接）
    **/
    public void CreateRoleZTGame(string roleId,
       string roleName, string roleLevel,
       string zoneId, string zoneName)
```
调用实例：可参考demo

```
  GASDKManager.Instance.CreateRoleZTGame("1", "角色名", "角色等级", "11", "区名");
```
###### `角色等级升级信息接口（必接）`

```
  /**
   角色等级升级信息接口（必接）
   **
   */
   public void RoleLevelUpZTGame(string roleId,
     string roleName, string zoneId,
     string zoneName, int level)
```
调用实例：可参考demo

```
   GASDKManager.Instance.RoleLevelUpZTGame("1", "角色名",  "11", "区名",11);
```
##### 1.注意
 [ AndroidMinfest ](/docs/sdk/csAndroidMinfest)
##### 2.接入生命周期
[ UnityActivity ](/docs/sdk/csAndroidLife)

## Unity3d 官方SDK(IOS)


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
  * CoreImage.framework

* ##### Libraries
  * libstdc++.tbd
  * libsqlite3.tbd
  * libz.tbd

####必须包含自定义配置文件和资源文件
检查Build Phases->Copy Bundle Resources

  * ztsdk_config.plist
  * GASDKResource.bundle
