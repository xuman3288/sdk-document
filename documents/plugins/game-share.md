游戏分享链接插件
==========================

## 流程说明

1. 分享者A 通过客户端生成分享链接或二维码
2. 被邀请人B 通过 点击 > 下载 > 安装 > 启动 > 注册 步骤后, A B 形成邀请人与被邀请人关系。
 （下图2.1. ~ 2.5. 为被被邀请人B 通过点击后显示分享页面的流程）
3. 游戏可通过以下 **查询分享者带来人数接口** 和 **通过被邀请人查询邀请人接口** 处理游戏业务.

**流程图：**

![游戏分享流程](http://i.imgur.com/7ssz2ut.png)


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
 * @param notify_url 游戏活动回调通知地址(如果设置以当前通知为准，未设置以后台配置通知地址为准)
 */

public void init(Context context,String gameid,String channelId,String openid, String notify_url)
~~~

调用例子：

~~~java
String gameid = IZTLibBase.getUserInfo().get(ZTConsts.User.GAMEID);//获取gameid
int channelid = IZTLibBase.getInstance().getPlatform();//获取渠道id
String openid = IZTLibBase.getUserInfo().get(ZTConsts.User.ACCID);//获取openid
String notify_url = "http://127.0.0.1";

ZTPromoteCode.getInstance().init(MainActivity.this,gameid,channelid+"",openid, notify_url);
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

采用 Restful 接口设计，部分Restful 成功失败说明，请参考 Restful 相关文档。

> 提示：请求时，请携带 `Accept: application/json` 的请求头， 已保证所有的响应内容类型都为 `application/json`。

### 签名请求头说明

请求头：

| 头名称   | 说明          |
|----------|---------------|
| X-Uri-Sign      | HTTP 协议中 URI 的 `HMAC-MD5` 的值，其中key 为游戏分配的密钥 |
| X-Content-Sign  | HTTP 协议中 body `HMAC-MD5` 的值，其中key 为游戏分配的密钥   |


### 设置IP(身份标识者)的id

请求地址: `http://share.plugin.mobileztgame.com/games/{game_id}/channels/{channel_id}/identifies/{client_ip}` 

请求方式: `PATCH`

#### 链接路径(Path)参数说明

| 参数名     | 说明          |
|------------|---------------|
| game_id    | 游戏ID        |
| channel_id | 渠道ID        |
| client_ip  | 客户端IP      |

#### 响应说明

HTTP 状态 200 响应内容:

响应头内容类型：`Content-Type: application/json`

响应内容说明：

~~~javascript
{
    "channel_id":"渠道ID",
    "invitee_id":"被邀请人ID",
    "inviter_id":"邀请人ID",
    "click_time":"触发链接时间",
    "os_type" :"被邀请人系统类型(android/ios)"
},
~~~

#### HTTP流举例说明

~~~
PATCH /games/5012/channels/1/identifies/1.1.1.1 HTTP/1.1
Host: share.plugin.mobileztgame.com
X-Uri-Sign: 9e37b5a3ee0ed1754af3139ccdf15dc4
X-Content-Sign: 6ccf4b59c895de5b585a95e5032bdac1
Accept: application/json
Content-Length: 14

invitee_id=123

HTTP/1.1 200 OK
Date: Fri, 29 Apr 2016 07:25:29 GMT
Content-Length: 107
Connection: close
Content-Type: application/json;charset=utf-8

{"channel_id":"1","inviter_id":"111","invitee_id":"123","click_time":"2016-04-29 10:14:28","os_type":"web"}
~~~

### 分享者带来人数服务端查询接口

请求地址: `http://share.plugin.mobileztgame.com/games/{game_id}/inviters/{inviter_id}/invitees` 

请求方式: GET

#### 链接路径(Path)参数说明

| 参数名     | 说明          |
|------------|---------------|
| game_id    | 游戏ID        |
| inviter_id | 邀请人ID      |

#### 查询部分(Query)参数说明:

| 参数名     | 说明          |
|------------|---------------|
| channel_id | 渠道ID        |

#### 响应说明

HTTP 状态 200 响应内容:

响应头内容类型：`Content-Type: application/json`

响应内容说明：

~~~javascript
[
    {
        "channel_id":"渠道ID",
        "invitee_id":"分享者ID"
    },
    {
        "channel_id":"渠道ID",
        "invitee_id":"分享者ID"
    },
    //...
]
~~~

#### 请求举例

~~~
GET /games/5012/inviters/123/invitees HTTP/1.1
Host: share.plugin.mobileztgame.com
X-Uri-Sign: 9e37b5a3ee0ed1754af3139ccdf15dc4
Accept: application/json

HTTP/1.1 200 OK
Date: Fri, 29 Apr 2016 07:25:29 GMT
Content-Length: 107
Connection: close
Content-Type: application/json;charset=utf-8

[
    {
        "channel_id":"渠道ID",
        "invitee_id":"分享者ID"
    },
    {
        "channel_id":"渠道ID",
        "invitee_id":"分享者ID"
    }
]
~~~

### 通过被邀请人查询邀请人服务端接口

请求地址: `http://share.plugin.mobileztgame.com/games/{game_id}/invitees/{invitee_id}/inviters` 

请求方式: GET

#### 链接路径(Path)参数说明

| 参数名     | 说明          |
|------------|---------------|
| game_id    | 游戏ID        |
| invitee_id | 被邀请人ID    |

#### 参数说明:

| 参数名     | 说明          |
|------------|---------------|
| channel_id | 渠道ID        |

#### 响应说明

HTTP 状态 200 响应内容:

响应头内容类型：`Content-Type: application/json`

响应内容说明：

~~~javascript
[
    {
        "channel_id":"渠道ID",
        "inviter_id":"分享者ID"
    }
]
~~~

### 流程图(2.3)用户点击回调接口协议说明

若已配置点击回调链接, 用户触发链接会发生以下请求行为.

* 协议说明: `HTTP`
* 请求方式: `POST`
* 内容类型(Content-Type): `application/x-www-form-urlencoded`

#### 参数说明:

| 参数名     | 说明             |
|------------|------------------|
| game_id    | 游戏ID           |
| inviter    | 邀请人(openid)   |
| ip         | 被邀请人客户端IP |
| user_agent | 被邀请人浏览器UA |
| sign       | 签名, 计算方式 `md5(game_id+"&"+inviter+"&"+ip+"&"+user_agent+"&"+key)`, 其中 key 为接登录时分配的密钥 |


#### 响应说明

发送请求后HTTP连接关闭, 不接收响应内容, 可随意定响应.
