Giant SDK  For  iOS
============================

> In order to unify the version, please try to use XCode 6 to publish the game, if you have to use XCode 5, please let us know in advance.


**General Library Files：**

* Security.framework
* CoreTelephony.framework
* AVFoundation.framework
* Foundation.framework
* UIKit.framework
* CoreGraphics.framework
* CoreLocation.framework
* SystemConfiguration.framework
* libsqlite3.0.dylib
* libsqlite3.dylib
* libz.dylib
* libstdc++.dylib


## General Setting:

*  **Code Sign Resource Rules Path** please set to `$(SDKROOT)/ResourceRules.plist`
*  **Valid Architectures** please set to armv7 armv7s
*  **Other Linker Flags** please set to -ObjC
*  **iOS Deployment Target** please set to 6.0 or later
*  please set Enable Mobules and Link Frameworks Automatically to YES for the dependency libraries to be imported automatically


&nbsp;&nbsp;&nbsp;&nbsp;After configuring the settings above, the general library will usually be imported automatically, if you encounter any problem, try to resolve according to the following process.


1. Please import the `StoreKit.framework` library if the error information like **OBJC_CLASS$SKPayment** or **OBJC_CLASS$_SKProductsRequest**.
2. Please import the `AdSupport.framework` library if the error information like OBJC_CLASS$_ASIdentifierManger.
3. Please import the `MessageUI.framework` library if the error information like **OBJC_CLASS$_MFMailComposeViewController**.
4. If the project is cocos2d please import the `GameController.framework` library if the error information like **OBJC_CLASS$_GCControllerConnectionEventHandler**.

## Differences among channels

* XY channel requires import `XYFramework` library to the **Xcode6 General Embeded Framework**.

* HaiMa  channel requires import `AiBeiFramework.framework` library to the **Xcode6 General Embeded Framework** .
* PP channel requires import `libMobileGestalt.dylib` library.
* I4 Channel
	1. Requires import `libMobileGestalt.dylib` library.
	2. Add a item to URL Types ，in the item  identifier set to AsQQPay and URL Schemes set to Bundle identifier for example "com.mztgame.i4"
	3. Add a item to URL Types ，in the item  identifier set to Alixpay
 and URL Schemes set to As channel appid for example "As328" (you can check the appid in the setting file what the name is `ztsdk_config.plist`)


* HaiMaWan Channel 
	1. This channel will be check Bundle identifier，you need to set up the value that business given to log on. For example "com.mztgame.hm2.hm"
	2. Add a item to URL Types, in the item URL Schemes set to `ZHPAY-Bundle identifier`. For example "ZHPAY-com.mztgame.hm2.hm"
	3. Requires import `IOKit.framework` library.


* TongBuTui Channel
	1. **Other Linker Flags** please set to `-ObjC -lz`
	2. Add a item to URL Types ，in the item **URL Schemes** set to `Bundle identifier-tb`. For example "com.mztgame.hm2.hm-tb"


* 49you Channel
	1. **Architectures** please set to `$(ARCHS_STANDARD_32_BIT)`
	2. Add a item to URL Types ，in the item **URL Schemes** set to "com.iOS49AppSdk.gameName"

* KuaiYong Channel
	1. This channel will be check **Bundle identifier**，you need to set up the value that business given to log on. For example "com.mztgame.sg.ky"
	2. Add a item to URL Types, in the item  identifier set to "com.ky.xSDK.alipa" and **URL Schemes** set to **Bundle identifier** for example "com.mztgame.dzz.ky"


* YunDian Channel
	1. Add a item to URL Types, in the item **URL Schemes** set to `CloudPoint.Bundle identifier`. For example "CloudPoint.com.mztgame.hm2.hm"

## Necessary interfaces for the game

### The UIApplicationDelegate class

You need to ensure the code is exactly the same with the sample, especially for

~~~objectivec
- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    [ZTLibBase instance];

      ***game and window initialization 、makeKeyAndVisible***


    [[ZTLibBase instance] initZTGameWithID:@"5010" gameName:@"MLGB" delegate:gameListenerDelegate];
    [[ZTLibBase getInstance] viewSetZTViewControl:[self.window rootViewController]];
    return YES;
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
    [[ZTLibBase instance] applicationDidEnterBackgroundZTGame];
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
    [[ZTLibBase instance] applicationWillEnterForegroundZTGame];
}
- (void)applicationDidBecomeActive:(UIApplication *)application
{
    [[ZTLibBase instance] applicationDidBecomeActiveZTGame];
}
- (BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation {
    [[ZTLibBase getInstance] applicationZTGame:application openURL:url sourceApplication:sourceApplication annotation:annotation];
    return YES;
}
~~~

#### The SDK Interface Functional Description

* The game will show the user center button based on the return value of the function `isHasUserCenter`
* The game will show the user center button based on the return value of the function `isHasSwitchAccount`
* The game need based on requires set a different zoneid


initialization

~~~objectivec
 [[ZTLibBase instance] initZTGameWithID:@"5010" gameName:@"MLGB" delegate:gameListenerDelegate];
~~~

login function，the parameters is YES

~~~objectivec
[[ZTLibBase instance] loginZTGame:YES];
~~~

**The game will show the user center button based on the return value of the function “isHasUserCenter **  
Whether have User Center button

~~~ 
[[ZTLibBase instance]isHasUserCenter]
~~~

show User Center view

~~~
[[ZTLibBase instance]userCenterZTGame]
~~~


** The game will show the user center button based on the return value of the function “isHasSwitchAccount **  
Whether have switch account button 

~~~
[[ZTLibBase instance]isHasSwitchAccount]
~~~

Show Switch Account View

~~~
[[ZTLibBase instance]switchAccountZTGame]
~~~

** print debug log, please delete the code that adjust this function when the game publish**  
Enable Logging print debug mode 

~~~
[[ZTLibBase instance]enableDebugMode]
~~~

get ZTPayInfo Singleton

~~~
[[ZTLibBase instance] getPayInfo]
	ZTPayInfo *payInfo = [ZTPayInfo getInstance];
        payInfo.productName = @"diamond";
        payInfo.productId = @"DZZ198";
        payInfo.productPrice = 100;
        payInfo.count = 1;
        payInfo.exchangeRate = 1;
        payInfo.extra = @"123";
~~~

pay function the parameters set in the ZTPayInfo Singleton like the above code

~~~
[[ZTLibBase instance]payZTGame]
~~~

** The game need based on requires set a different zoneid **  
setting zoneid

~~~
[[ZTLibBase instance]setZoneID:@"1"]
~~~

Whether logined

~~~
[[ZTLibBase instance]isLogined]
~~~

get channel id

~~~
[[ZTLibBase instance] getPlatformID]
~~~

#### ZTListenerDelegate Callback interface 1

* Please note that all other features interface must be after received ZTGAME_INIT 
* After receiving ZTGAME_LOGOUT the game need logout to be performed to switch to the login view, SDK will open the login UI for the user to log on by default

* After receiving  ZTGAME_CLOSE need to determine whether the user is logon state, if the game does not have login button it will call the login Interface to force users to log on, if the game has a login button, you can ignore deal with this notification, wait to the user to click the login button and decide to enter the game or popup the login view based on their login status.

~~~
- (void)onFinished:(int)what errCode:(int)errCode data:(NSDictionary *)json {
    NSLog(@"onfinished what:%d errorCode:%d json:%@", what, errCode, json);
    switch (what) {
        case ZTGAME_INIT:
            if (errCode == 0) {
                //           call login after init finished
                [[ZTLibBase instance] loginZTGame:YES];
            }
            break;
        case ZTGAME_LOGIN:
            if (errCode == 0) {
                //                game logic after user logged in
                NSLog(@"login json %@", json);
            }
            break;
        case ZTGAME_LOGOUT:
            if (errCode == 0) {
                //                game logic after user logged out
                UIButton *loginBtn = (UIButton *) [self.view viewWithTag:1];
                [loginBtn setEnabled:YES];
            }
            break;
        case ZTGAME_CLOSE:
            if (errCode == 0) {
                //                when user close sdk window,check user is logged in or not,if user isn't logged in,then reopen the login window
                if (![[ZTLibBase instance] isLogined]) {
                    [[ZTLibBase instance] loginZTGame:NO];
                }
            }
        case ZTGAME_PAY:
            if (errCode == 0) {
                //                pay finished success
            }
            else {
                //                pay finished fail
            }
        default:break;
    }
}
~~~


#### ZTListenerDelegate Callback interface 2

* Note that in this function process triggering a page drawn and stop

~~~
- (void)switchAccountView:(int)what
{
  switch (what) {
        case ZTGAME_VIEWSHOW:
            break;
        case ZTGAME_VIEWCLOSE:
            break;
        default:break;
    }
}
~~~

#### The Login function returns Json format 


device_type：Equipment type
pt_id: platform id
token：login verifications token
accid：user id（**Now a digital format, the game needs to support a string format**）
imei：user’s phone imei
action：Operating identity
account：username
mac：mac Address
pt_id：channel id
ip：user ip Address
example :

~~~
{
accid = 461902;  
account = "默认账户"";  
action = login;  
"device_type" = "x86_64";  
imei = "87835508-6849-4805-ADB9-B4780A2E6E1D";  
ip = "192.168.1.109"; 
mac = ""; 
"pt_id" = 2;  
token = d9e3057af0f7fcfe;
}
~~~


#### ZTPayInfo Parameters information

~~~
NSString *appOrderId; // don’t require game to set up, reserved parameters
NSString *productId; //product id , only appstore use
NSString *productName; // product name
int productPrice; // product price，Please note that the currency is RMB cents
int count; // product amount，must set to 1
int exchangeRate; // Virtual Currency，How many units of one Yuan medal correspondence
NSString extra; // Extended information, this parameters can be set by game，SDK will pass back to the game server
~~~

## Push Function

* If you want to test, configure pushDebug in the configuration file(ztsdk_config.plist，State is 1 for debug and 0 for the release version, the default is for the release .

~~~objectivec
- (void)application:(UIApplication )application didRegisterForRemoteNotificationsWithDeviceToken:(NSData )deviceToken {
	[ZTServicePush registerDeviceToken:deviceToken];
}
~~~

Quick Add QQ Group Function
Please inform us the qqGroup number，We will configuration in server.

~~~objectivec
 if([[ZTLibBase getInstance] isHasQQGroup])
{
     [[ZTLibBase getInstance] joinQQGroup];
}
~~~

## Remark

The channel id and game id can be referenced from this wiki link  
[http://wiki.mztgame.com/index.php/%E6%B8%A0%E9%81%93%E4%BF%A1%E6%81%AF%E5%88%97%E8%A1%A8](http://wiki.mztgame.com/index.php/%E6%B8%A0%E9%81%93%E4%BF%A1%E6%81%AF%E5%88%97%E8%A1%A8)

