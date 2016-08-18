## Unity3d 官方SDK插件(目前支持android)


---------------------------------------------------------

#### 插件 下载链接：

- [Download  ](http://222.73.243.55:3000/downloadsdk/ztpromotecode_v1.0.zip)

------------------------------------------------------------------
####  导入 loginPaySdk.unitypackage 全部（\Assets\import package\custom package）

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

###### `登录接口`

```
  /// <summary>
  /// 登录
  /// </summary>
  /// <param name="zoneId">区id 游戏自传</param>
  /// <param name="zoneName">区name 游戏自传</param>
  /// <param name="req">回调方法</param>
  public void login(string zoneId,string zoneName,LoginStatusCallback req);

```    
参数说明：  

`zoneId` :可为空

`zoneName` :可为空

调用举例：可参考demo
```
  GASDKManager.Instance.login("1","败走麦城区",logResult);
```

###### ` 注：在调用该接口之前，请务必先调用init接口。`

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
public void pay(string zoneId,int money,string productId,string productName,PayStatusCallback req);

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
  GASDKManager.Instance.pay("1",100,"1","金币",logPayResult);
```
###### `切换账号`

```
/// <summary>
/// 切换账号
/// </summary>
/// <param name="req">回调方法</param>
public void switchAccount(LogoutCallback req)

```

调用实例：可参考demo

```
  GASDKManager.Instance.switchAccount(logSwitchUser);
```
