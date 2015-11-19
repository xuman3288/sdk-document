游戏分享链接插件
==========================

## 流程说明

1. 分享者A 通过客户端生成分享链接或二维码
2. 被邀请人B 通过 点击 > 下载 > 安装 > 启动 > 注册 步骤后, A B 形成邀请人与被邀请人关系
3. 游戏可通过以下 **查询分享者带来人数接口** 和 **通过被邀请人查询邀请人接口** 处理游戏业务.

### 邀请人与被邀请人关联标识技术说明

过程是通过IP方式进行关联. 如:
B 点击 A 的连接下载, 我方服务端记录 A 与 IP(1.1.1.1) 的关系.
B 在IP(1.1.1.1) 下注册后, 与A形成关系.

* 问题1: 其中若B的下载与注册的IP不一致会导致不能确立关系.
* 问题2: 若B用户通过(IP: 1.1.1.1)下载,不参数注册. C用户其它途径下载安装在(IP: 1.1.1.1)进行注册, 会导致C与A形成关系.

以上问题, 请不要过分信任此插件带来用户关系的完整性. 


## Android 客户端接入说明

### 二维码分享插件SDK下载链接：

- [Download SDK V1.0.3](http://docs.mztgame.com/files/Android/plugin/ztpromotecode_v1.0.3.zip)

---------------------------------------------------------------------------------------------------------

> 注：该插件依赖base,务必在base的初始化接口完成后保证gameid,openid不为空的情况下，再调用该插件接口


#### 接入步骤：

##### 1. 准备工作

- 添加libs/目录中jar放入工程的libs中（若libs中的base.jar无需复制）  
- 在AndroidManifest.xml中添加如下：

~~~xml
<activity
android:name="com.mztgame.promotercode.ZTPromoteCodeActivity"
android:configChanges="orientation|screenSize|keyboardHidden"
android:screenOrientation="behind"
android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen" />
~~~

- 添加权限：

~~~xml
<uses-permission android:name="android.permission.INTERNET" />
~~~

##### 2. 调用初始化接口：

函数原型：  


~~~java

/**
 * 
 * @param context    
 * @param gameid     游戏的id（另：若已接入base包，该值可用接口IZTLibBase.getUserInfo().get(ZTConsts.User.GAMEID)获取)
 * @param channelId  渠道id（另：若已接入base包，该值可用接口IZTLibBase.getInstance().getPlatform()获取）
 * @param openid     用户的uid（唯一标识)（另：若已接入base包，该值可由IZTLibBase.getUserInfo().get(ZTConsts.User.ACCID)接口获取）
 * @param notify_url 通知地址(如果设置以当前通知为准，未设置以后台配置通知地址为准)
 */

public void init(Context context,String gameid,String channelId,String openid, String notify_url)
~~~
	
调用例子：

~~~java
String gameid = IZTLibBase.getUserInfo().get(ZTConsts.User.GAMEID);//获取gameid
int channelid = IZTLibBase.getInstance().getPlatform();//获取渠道id
String openid = IZTLibBase.getUserInfo().get(ZTConsts.User.ACCID);//获取openid

ZTPromoteCode.getInstance().init(MainActivity.this,gameid,channelid+"",openid,notify_url);
~~~

##### 3. 生成分享二维码接口:

函数原型：

~~~java
/**
 * 
 * @param width       生成的二维码长宽
 * @param logoName    logo图片对应的资源名称
 * @param isShow      是否展示二维码图片,true展示二维码图片，false不展示图片
 * @param listener    生成结果回调接口
 */
public void generatePromoteCode(int width, String logoName,boolean  isShow,IPromoteCodeListener listener)
~~~

调用实例：

~~~java
ZTPromoteCode.getInstance().generatePromoteCode(width,"ic_launcher",false,listener);
~~~

> 注：在调用该接口之前，请务必先调用init接口。


##### 4. 其他接口  

IPromoteCodeListener接口:
该接口必须实现，并实现接口方法onResult, 原型： 

~~~java
/**
 * 
 * @param code        返回结果码，0表示成功，其他失败
 * @param errmsg      结果消息，当code为0是，该值为"success"；
 * @param shortUrl    短链接地址
 * @param codeBitmap  二维码图片的bitmap
 */
public void onResult(int code,String errmsg,String shortUrl,Bitmap codeBitmap);
~~~

> 注：当code值不为0时，shortUrl和codebitmap均为null。

## 服务端查询接口

### 分享者带来人数服务端查询接口

请求地址: `http://share.plugin.mobileztgame.com/query_slaves.php` 

请求方式: GET

#### 参数说明:


| 参数名   | 说明          |
|---------|---------------|
| game_id | 游戏应用ID    |
| openid  | 分享者账号ID  |
| channel | 渠道ID        |
| sign    | 签名 md5(game_id+"&"+openid+"&"+channel+"&"+key), 其中 key 为接登录时分配的密钥 |


#### 响应说明

响应内容格式为: `application/json`

| 参数名  | 类型   | 说明          |
|---------|--------|---------------|
| code    | int    | 响应码, 0: 正常返回; 大于0: 错误, error 字段为错误信息  |
| error   | string | 错误消息           |
| slaves  | array  | 分享者带来的账号 |

### 通过被邀请人查询邀请人服务端接口

请求地址: `http://share.plugin.mobileztgame.com/query_inviter.php` 

请求方式: GET

#### 参数说明:

| 参数名   | 说明          |
|---------|---------------|
| game_id | 游戏应用ID    |
| openid  | 被邀请人账号ID  |
| channel | 渠道ID        |
| sign    | 签名 md5(game_id+"&"+openid+"&"+channel+"&"+key), 其中 key 为接登录时分配的密钥 |


#### 响应说明

响应内容格式为: `application/json`

| 参数名   | 类型   | 说明            |
|---------|--------|---------------|
| code    | int    | 响应码, 0: 正常返回; 大于0: 错误, error 字段为错误信息  |
| error   | string | 错误消息       |
| inviter | string | 分享者的账号ID |


### 用户点击回调说明

若已配置点击回调链接, 用户触发链接会发生以下请求行为.

* 协议说明: `HTTP`
* 请求方式: `POST`
* 内容类型(Content-Type): `application/x-www-form-urlencoded`

#### 参数说明:

| 参数名     | 说明           |
|------------|----------------|
| game_id    | 游戏ID         |
| inviter    | 邀请人(openid) |
| ip         | 客户端IP       |
| user_agent | 分享者账号ID   |
| sign       | 签名, 计算方式 `md5(game_id+"&"+inviter+"&"+ip+"&"+user_agent+"&"+key)`, 其中 key 为接登录时分配的密钥 |


#### 响应说明

发送请求后HTTP连接关闭, 不接收响应内容, 可随意定响应.
