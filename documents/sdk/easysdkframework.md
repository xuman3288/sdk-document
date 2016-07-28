巨人移动Android母包接入文档
================================

**SDK版本：** Ver 4.4.6   
**时间：** 2015/8/31

## 相关类说明

### 类 `com.ztgame.mobileappsdk.common.IZTLibBase` 说明


~~~java
package com.ztgame.mobileappsdk.common;

public abstract class IZTLibBase {

    /**
     * 初始化IZTLibBase实例(必接)
     * 
     * @param ac Activity类的一个实例
     */
    @Override
    static public boolean newInstance(Activity ac){}

    /**
     * 初始化Android SDK（必接） 
     *
     * 首先调用IZTLibBase 的newInstance方法，传入你的Activity。然后调用getInstance()方法的initZTGame初始化游戏信息。
     * 
     * @param gameId   游戏ID
     * @param appName  游戏名称
     * @param isLandScape 游戏横竖屏.  
     *     - true  : 横版
     *     - false : 竖版
     * @param listener  用于响应回调事件的监听接口
     */
    @Override
    public void initZTGame(String gameId, String appName, boolean isLandScape, IZTListener listener) {}

    /**
     * 登录接口（必接）
     * 
     * @param zoneId   游戏区ID（Int, 大于0）
     * @param zoneName 游戏区名称
     * @param isAutoLogin 是否自动登录，传true即可
     */
    public void loginZTGame(String zoneId, String zoneName, boolean isAutoLogin) {}

    /**
     * 支付接口（必接）
     * 
     * @param payInfo 实例化 ZTPayInfo 信息 
     */
    final public boolean payZTGame(ZTPayInfo payInfo) {}


    /**
     * 是否需要切换账号按钮接口
     * 以上接口返回true则游戏需要添加一个切换账号按钮以方便用户切换账号.
     * 点击此按钮后调用switchAccountZTGame()
     */
    public boolean isHasSwitchAccountZTGame() {}

    /**
     * 切换账号操作
     * 
     * 以上为切换账号接口功能，调用此接口执行切换账号操作，调用此接口后，会发送ZTGAME_LOGOUT消息。
     */
    public void switchAccountZTGame(){}

    /**
     * 是否需要用户中心按钮接口
     * 
     * 以上为某些渠道判断是否存在用户中心按钮倘若返回false不作处理，倘若返回true需要显示用户中心按钮，点击此按钮后调用enterCenterZTGame()
     */
    public boolean isHasCenterZTGame(){}


    /**
     * 用户中心操作, 调用此接口弹去渠道方的用户中心界面
     */
    public void enterCenterZTGame(){}


    /**
     * 是否有第三方渠道的退出确认弹出框
     *  
     * 如果此函数返回true，请游戏不要弹出游戏自身的退出确认弹出框而是直接调用quitZTGame来弹出第三方的退出弹出框，在ZTGame_Quit回调内处理游戏的退出操作
     * （销毁代码，而不是再次弹出退出确认对话框。返回false则按照游戏自己的退出流程处理即可。)
     */
    public void isHasQuitDialog(){}

    /**
     * 弹出第三方退出弹出确认框接口
     */
    public void quitZTGame(){}


    /**
     * 开启日志输出接口
     * 游戏上线前需要注释该行代码以关闭日志输出
     */
    public void enableDebugMode(){}

    /**
     * 获取渠道id
     * 
     * 渠道id以及游戏id可以统一从此wiki链接查询.
     * @see http://wiki.mztgame.com/index.php/%E6%B8%A0%E9%81%93%E4%BF%A1%E6%81%AF%E5%88%97%E8%A1%A8
     */
    public int getPlatform(){}


    /**
     * 更新服务器id
     * 玩家切换服务器后需要调用此方法更新当前服务器id
     */
    public void setZoneId(final String zoneId){}

    /**
     * 更新当前活动Activity
     * 如果游戏当前Activity变更，需要更新activity到SDK
     */
    public void setActivity(Activity activity){}

    /**
     * 是否已经登录
     */
    public boolean isLogined(){}


    /**
     * 是否有一键加QQ群功能接口
     * 
     * 注: 接入此接口时，游戏会收到ZTGAME_QQGROUP消息，当errcode为0表示有一键加QQ功能，-1表示没有该功能
     */
    public void isHasJoinQQGroup(){}

    /**
     * 一键加QQ群接口
     * 
     * 注：接入此接口时，需提供QQ群对应的key，key由QQ官方申请。
     * 接入次接口前，请务必调用 `isHasJoinQQGroup()` 接口，并且在收到ZTGAME_QQGROUP消息后，errcode为0的情况下调用。
     * 
     * @example:
     * <code>
     * if(IZTLibBase.getInstance().isHasJoinQQGroup()){
     *     IZTLibBase.getInstance().joinQQGroup();
     * }
     * </code>
     */
    public void joinQQGroup(){}


    /**
     * 登录完成数据统计接口
     * 
     * 角色进入游戏后调用
     * 
     * @param roleId     角色ID
     * @param roleName   角色名称
     * @param roleLevel  角色等级
     * @param zoneId     游戏区ID 
     * @param zoneName   游戏区名称
     */
    public void loginOkZTGame(String roleId,String roleName,String roleLevel,String zoneId,String zoneName) {}

    /**
     * 创建角色数据统计接口（建议接入）
     * 
     * 玩家创建角色后调用.
     * 
     * @param roleId     角色ID
     * @param roleName   角色名称
     * @param roleLevel  角色等级
     * @param zoneId     游戏区ID 
     * @param zoneName   游戏区名称
     */
    public void createRoleZTGame(String roleId,String roleName,String roleLevel,String zoneId,String zoneName) {}

    /**
     * 角色等级升级信息接口
     * 
     * @param roleId     角色ID
     * @param roleName   角色名称
     * @param zoneId     游戏区ID 
     * @param zoneName   游戏区名称
     * @param level      角色等级
     */
    public void roleLevelUpZTGame(String roleId,String roleName,String zoneId,String zoneName,int level){}
}
~~~

### 接口 `com.ztgame.mobileappsdk.common.IZTListener` 说明

~~~java
/**
 * 完成事件回调接口
 */
public interface IZTListener {

    /**
     * 当某事件执行后触发
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


##### 参数 `what` 说明

| 出现值                   | 描述 |
|--------------------------|--------------|
| ZTConsts.ZTGAME_INIT     | 是SDK初始化完成的通知，所有接口都需要在INIT成功完成之后才能开始调用 |
| ZTConsts.ZTGAME_LOGIN    | 需要处理登录返回的json_obj参数，交给游戏服务器[进行效验](http://docs.mztgame.com/docs/sdk/server_guide#__2) |
| ZTConsts.ZTGAME_PAY      | 只需要判断errcode为0成功还是-1失败，但是是否到账要以[服务器通知为准](http://docs.mztgame.com/docs/sdk/server_guide#__7) |
| ZTConsts.ZTGAME_QUIT     | 是游戏进行销毁操作的地方，玩家在第三方退出框点击确认后会收到此回调 |
| ZTConsts.ZTGAME_LOGOUT   | 是游戏进行登出操作/切换账号操作的地方，需要返回到游戏登录界面等待用户再次登录 |
| ZTConsts.ZTGAME_QQGROUP  | 是游戏中一键增加QQ群的消息，游戏收到该消息后，当errcode为0表示有一键加QQ功能，其他表示没有该功能 |
| ZTConsts.ZTGAME_TENCENT_QUERY  | 是腾讯游戏点查询接口的消息，游戏收到该消息后，当errcode为0表示有查询成功，需要游戏端根据查询金额判断是否补单|
| ZTConsts.ZTGAME_CREATE_ORDER  | 是创建订单成功的消息，返回的order_id参数 |

###### 当 `what` 值为 `ZTConsts.ZTGAME_LOGIN`, `json_obj` 出现值:

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

> **注:**
> 
> `accid` 为账号唯一身份标识, 即服务端的 `openid`;
> `account` 可能为空;

###### 当 `what` 值为 `ZTConsts.ZTGAME_TENCENT_QUERY`, `json_obj` 出现值:

~~~json
{
    "code": 0,
    "error":"Error message.",
    "balance":"0",
    "auto_consumed":false
}
~~~

| 参数     |  类型         |  说明                            |
|----------|---------------|----------------------------------|
| code     | int           | 返回结果编号; 0 正确; 其它为错误 |
| error    | string / null | 错误消息                         |
| balance  | string        | 腾讯账号余额, 单位元. (无法被消耗的余额, 需要游戏创建订单处理)  |
| auto_consumed | boolean   | 是否自动查询消费       |


###### 当 `what` 值为 `ZTConsts.ZTGAME_LOGOUT`, `json_obj` 出现值:

> **注:**
> 
> 在游戏接收ZTGAME_LOGOUT后，调用登录接口之前前，建议调用isLogin接口做一次用户登录状态判断，如果true则不调用登录接口，否则调用。增加判断可防止多次弹出登录界面。

#### 类 `com.ztgame.mobileappsdk.common.ZTPayInfo`'s 方法说明:

| Method | Required / Optional / Deprecated | Description |
|--------|----------------------------------|----------------------------|
| setAmount | 必填  | 设置商品金额(RMB). 单位(分) |
| setExtra | 必填 | 设置游戏订单扩展信息(游戏订单号等等)  |
| setMoneyName | 可选设置 | 设置货币单位名称  |
| setMonthCard | 可选设置 | 设置是否为月卡  |
| setExchangeRatio | 可选设置  | 设置价格比率 |
| setProductName | 可选设置  | 设置商品名称 |
| setProductId | 可选设置  | 设置商品ID |


## 开始接入 ZTgameFramework 

### Step 1. 环境说明


* 导入库(`base.jar`, `ztgameframework.jar`) 和资源文件到你的项目
* 项目中给 `Androidmenifest.xml` 添加权限设定 :


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


* 添加属性至你的 `<application />` 标签中

~~~
android:name="com.ztgame.ztgameframework.ZTApplication"
~~~

* 在demo中, 复制 asserts 文件夹到你的游戏项目. ztsdk_config.properties 文件范例:

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

> **注 1**：
> 
> 在 `androidManifest.xml` 中需要将所有的activity service等标签 `android:name` 字段写上对应的java包名的全称

> **注 2**：
> 
> 在assets目录下添加 ztsdk_config.properties 文件确保文件中有以下内容:
> ~~~
> config.sdk.class=com.ztgame.ztgameframework.ZTgameFramework
> ~~~

> **注 3**：
> 
> 如果你的工程里存在 Application 需要继承改Application 并且在 `onCreate()` 函数中添加 `super.onCreate()`


### Step 2. 初始化 `MainActivity`

#### 代码范例:

~~~java
package com.example;

import android.app.Activity;
import android.os.Bundle;
import com.ztgame.mobileappsdk.common.IZTLibBase;
import com.ztgame.mobileappsdk.common.IZTListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //游戏其它事项初始化代码...

        //IZTLibBase 初始化
        IZTLibBase.newInstance(MainActivity.this);
        IZTLibBase.getInstance().initZTGame("5012","Game Name", false, mListener);
    }

    //事件监听
    private IZTListener mListener = new IZTListener() {
        @Override
        public void onFinished(int what, int errcode, JSONObject json_obj) {

            switch (what) {
            case ZTConsts.ZTGAME_LOGIN:
                if (errcode == 0) {
                    //游戏完成事件, 设置角色信息
                    IZTLibBase.getInstance().loginOkZTGame("roleId", "roleName", "roleLevel", "zoneId", "zoneName");
                } else {
                    //登录失败
                }
                break;
            case ZTConsts.ZTGAME_INIT:
                if (errcode == 0) {
                    //初始化完成
                } else {
                    //初始化失败
                }
                break;
            case ZTConsts.ZTGAME_PAY:
                if(errcode == 0){
                    //支付完成
                }else{
                    //支付失败
                }
                break;
            case ZTConsts.ZTGAME_QUIT:
                if(errcode == 0){
                    //游戏退出
                } else {
                }
                break;
            case ZTConsts.ZTGAME_LOGOUT:
                //玩家退出处理
                if(!IZTLibBase.getInstance().isLogined()){
                    IZTLibBase.getInstance().loginZTGame("1", "Game area", true);
                }
                break;
            case ZTConsts.ZTGAME_QQGROUP: //腾讯QQ一键加群
                if(errcode == 0){
                    joinQQButton.setVisibility(View.VISIBLE);
                }
                break;
            }
        }
    };
}
~~~


### Step 3. 登录接入

UI在"进入游戏"界面.

**代码范例：**

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
        
        switch (v.getId()) {
            case R.id.enterGameWorldButton: //玩家点击"进入游戏" 
                IZTLibBase.getInstance().loginZTGame("1", "ZoneName", true);
                break;
            case R.id.exitButton:
                //玩家点击退出
                if(IZTLibBase.getInstance().isHasQuitDialog()){ //渠道是否有退出框确认
                    IZTLibBase.getInstance().quitZTGame();
                }else{
                    //游戏自己的退出框代码
                }
                
                break;
            case R.id.accountCenterButton: //进入用户中心
                IZTLibBase.getInstance().enterCenterZTGame();
                break;
            case R.id.switchAccountButton: //切换账号
                IZTLibBase.getInstance().switchAccountZTGame();
                break;
            //...
        }
    }

    //...
}
~~~

或者通过主线程调用方式:

~~~java
    YourActivity.runOnUiThread(new Runnable() {
            public void run() {            
                     IZTLibBase.getInstance().loginZTGame("1", "zoneName"", true);
            }
    });
~~~


### Step 4. 充值接入

当用户点击充值商品时调用 `IZTLibBase.getInstance().payZTGame(ZTPayInfo payInfo)`

#### 代码范例: 

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
        //玩家点击购买商品按键
        switch (v.getId()) {
            case R.id.buyButton:
                if(IZTLibBase.getInstance().isLogined()){
                    ZTPayInfo payInfo = new ZTPayInfo();
                    payInfo.setAmount(100);  //设置金额, 单位(分)
                    payInfo.setProductName("test item"); //设置商品名称
                    payInfo.setProductId("1001"); // 设置商品ID
                    payInfo.setExtra("1"); //设置游戏订单扩展信息
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

### Step 5. 需要调用的Android生命周期接口

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


## 更新日志

### 4.4.0

* 增加 `payNotify()` 方法

~~~java
    public void payNotify(String amount, String order, String description) {
        Log.d("giant", "Pay Notify called" +  amount + order + description);
    }
~~~

### 4.4.5

* 增加腾讯一键加群功能

~~~java
    public void isHasJoinQQGroup();
    public void joinQQGroup();
~~~

* 增加 `getSDKVersion()` 方法

~~~java
    /**
     * 获取SDK 版本号
     */
    public String getSDKVersion() {
        return mSDKVersionName;
    }

    public long getSDKVersionCode(){
        return mSDKVersionCode;
    }
    
    /**
     * 获取第三方SDK版本号
     */
    public String getChannelSDKVersion(){
        return CHANNEL_SDKVERSION;
    }

    /**
     * 获取当前框架版本号
     */
    public String getFrameworkVersion(){
        return FRAMEWORK_VERSION;
    }
~~~

### 4.4.6

* 设置腾讯登录类型

~~~java
    public void setTencentLoginType(int tencentLoginType)
~~~

Login types:

~~~java
    ZTConsts.TencentLoginType.QQ;
    ZTConsts.TencentLoginType.WECHAT;
~~~

* 腾讯游戏点 接入功能

~~~java
    /**
     * 腾讯游戏点查询接口
     */
    public void queryTencentGamePoint()

    /**
     * 腾讯补单接口
     */
    public boolean createOrderZTGame(ZTPayInfo payInfo)
~~~
### 4.4.7

* 提交玩家最后登录游戏区

~~~java
    public void saveZoneId(String openId, String gameId, String zoneId)
~~~

例子:

~~~java
    IZTLibBase.getInstance().saveZoneId(IZTLibBase.getUserInfo().get(ZTConsts.User.ACCID),           IZTLibBase.getUserInfo().get(ZTConsts.User.GAMEID),"1");
~~~

* 获取玩家最后登录游戏区

~~~java
    public void getZoneIdforServices(String openId, String gameId, final ZTNetCallBack iCallBack)
~~~
例子:

~~~java
      	IZTLibBase.getInstance().getZoneIdforServices(IZTLibBase.getUserInfo().get(ZTConsts.User.ACCID),    IZTLibBase.getUserInfo().get(ZTConsts.User.GAMEID),new ZTNetCallBack() {
				
				@Override
				public void callBack(Bundle bundle) {
					// TODO Auto-generated method stub
					Log.e("giant", bundle.toString());
				}
			});
~~~

### 4.4.8

~~~java
    /**
     * 游戏端下单获取参数信息接口
     */
    public ZTPayInfo convertPayOrder();

    /**
     * 游戏端下单，调起sdk支付
     */
    public void payOrderZTGame(ZTPayInfo mZTPayInfo);
~~~


**使用范例:**

~~~java

    
    mZTPayInfo 订单信息(渠道支付需要的信息)：订单号、金额、商品id...;
    例子：
    jsonObject 服务器下单接口的返回的json
    mZTPayInfo.setJsonExtra(jsonObject.getJSONObject("extra"));（支付扩展参数）
    mZTPayInfo.setAmount(100);  //设置金额, 单位(分)
    mZTPayInfo.setProductName("test item"); //设置商品名称
    mZTPayInfo.setProductId("1001"); // 设置商品ID
    mZTPayInfo.setExtra("1"); //设置游戏订单扩展信息
    mZTPayInfo.setOrderId(order_id);//订单号
~~~

###注意事项
#### '1.如果游戏是u3d框架，以上sdk接口必须放在runOnUiThread()方法中调用'
