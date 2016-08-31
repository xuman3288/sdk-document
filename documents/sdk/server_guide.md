巨人移动SDK 4.0服务端接口文档
==========================

## 1. 登录接口

### 1.1. 流程说明
![渠道登录流程图](http://i.imgur.com/dm1aUBd.jpg)

1. 用户通过巨人移动SDK登录后, 获得token
2. 游戏客户端从SDK获得 token和openid 交付游戏服务端
3. 游戏服务端使用token和openid在巨人移动服务端进行认证, 验证成功则登录进入游戏

### 1.2. 服务端登录验证接口说明

系统提供两种验证方式：

* 签名方式验证
* 接口方式验证

二者选一种，或者当“接口方式验证”因网络原因故障，可切为签名方式离线验证。

#### 1.2.1. 签名方式验证(离线验证，需客户端 Android&IOS 5.2 以上支持)

##### 1.2.1.1. 验签需要参数(JSON类型)

| 参数    | 类型   | 说明 |
| --------|--------| --- |
| entity  | object | 由客户端中取得, 参与签名需要 encode 后的字符串  |
| entity.openid  | string | 用户账号唯一标识(账号唯一性标识) |
| entity.time  | int | 生成签名的服务器时间，单位秒。  |
| entity.account  | string|null | 用户账号，可能为null。  |
| sign    | string | 由Rsa\Sha1 生成的签名, <br> 其中 PublicKey 由, 巨人移动服务端对接人员提供 |

##### 1.2.1.2. RSA 签名简例子

我方服务器生成规则示例： `Rsa.sign(Json.encode(entity), privateKey)` 
游戏需按该规则执行验证.

RSA验证签名执行顺序：

1. 从客户端取得 `entity` 和 `sign` 两个值.
2. `JSON.Encode` `entity` 得到一个String: `'{"openid":"1-1234","account":"test"}'`.
3. 是该String 进行RSA-SHA1 验证.
4. 对 `entity.time` 进行有效时间验证 (验证 `entity.time` 是否超过建议时间 7*86400s(一周)).

简例：`Rsa.verify(Json.encode(entity), sign, publicKey) && entity.time > (time() - 7*86400)`

#### 1.2.2. 接口方式验证

##### 1.2.2.1. 协议说明

* 协议说明: `HTTP`
* 请求方式: `GET`
* 内容类型(Content-Type): `application/x-www-form-urlencoded`
* 接口地址: `http://passport.mobileztgame.com/service/check-token`

#####  1.2.2.2.  参数说明

| 参数     |  必填 |	说明 |
| -------- | ---- | --- |
| game_id  | 是   | 游戏应用ID  |
| openid   | 是   | 由SDK获得唯一ID, (数据库建议设置长度 120位) |
| time     | 是   | 游戏服务器时间戳,以秒为单位, 如(1421212874) |
| token    | 是   | 由SDK客户端中获得 |
| sign     | 是   | MD5 签名,结果为32位字符十六进制数字形式返回的散列值, 生成规则: <br>Md5(game_id+openid+time+token+key) <br>其中key 由, 巨人移动服务端对接人员提供 |

#####  1.2.2.3. 响应说明

响应内容为JSON格式:

| 参数     |  类型 |	说明 |
| -------- | ---- | --- |
| code | int | 错误代码: <br> 0 成功 <br> 大于 0 失败 |
| error | string | 当code > 0 时的错误说明 |
| errors | object | 参数缺少或参数格式的错误提示, 如: `{"time":"msg","token":"msg"}` |
| entity | object | 登录账号的用户信息, 如: `{"openid":"1-1234","account":"test","nickname":"昵称"}` <br> account, nickname 可能为空, <br> 渠道接口有提供就返回, 没有可能为null 或 键不存在 |


> `entity.openid`范例 : 1-1234 , 26-5678
>
> * "-" 前表示渠道编号,
> * "-" 后表示各渠道的用户ID.
>
> 比如上面的
>
> * "-" 前 1表示巨人移动渠道, 26表示 UC渠道.
> * "-" 后就是各渠道相应的用户ID


#####  1.2.2.4. 请求举例

Key = 123456为例:
参与md5的字符串和加密后的值为:

```
md5("50121-1234142121287408897c5d66eb86b8c6d50c623e63ea27123456");
8da532dffb888fc0dbb88465032e20fa
```

**请求链接为:**
```
http://passport.mobileztgame.com/service/check-token?game_id=5012&openid=1-1234&time=1421212874&token=08897c5d66eb86b8c6d50c623e63ea27&sign=8da532dffb888fc0dbb88465032e20fa
```
响应: 
```
{"code":0,"entity":{"openid":"1-1234","account":"test"}}
```

## 2. 充值回调接口

### 2.1. 支付回调(V3.0)接口说明

适用于 2015-1-14以后新游戏的接入, 2015-1-14之前接入的游戏, 使用(2.x/1.x)的版本回调, 如需更新回调版本请联系服务器开发人员配合调试.

接口均由巨人移动充值系统通知游戏服务器, 如需IP验证查看充值系统IP列表

### 2.2. 支付流程说明

![支付流程图](http://i.imgur.com/cHg5fye.jpg)


#### 2.2.1. 协议说明

* 协议说明: `HTTP`
* 请求方式: `POST`
* 内容类型(Content-Type): `application/x-www-form-urlencoded`
* 通知回调版本:  Ver 3.0

#### 2.2.2. 参数说明

| 参数           | 必传 | 类型、最大长度(Mysql Types) | 说明                                                                        |
| -------------- | ---- | --------------------------- | --------------------------------------------------------------------------- |
| account        | 否   | varchar(100)                | 渠道方平台账号名，部分渠道无法取得账号名则为空可能                          |
| amount         | 是   | decimal(15,2)               | 总金额(单位人民币), 浮点数两位 decimal(15,2)                                |
| channel        | 是   | int                         | 渠道ID, 详情查看 [渠道信息列表](/docs/channels)                              |
| extra          | 否   | varchar(255)                | 游戏扩展数据, 创建订单传入的值, 原数据返回                                  |
| game_id        | 是   | int                         | 游戏ID                                                                      |
| order_id       | 是   | bigint                      | 巨人移动订单号                                                              |
| product_id     | 否   | varchar(45)                 | 苹果商品编号或安卓渠道或游戏自定义商品编号(**苹果渠道请验证商品ID与金额后发货**) |
| time           | 是   | int                         | 巨人移动充值服发起请求,秒为单位的时间戳                                     |
| transaction_id | 是   | varchar(100)                | 第三方交易单号                                                              |
| openid         | 是   | varchar(128)                | 账号ID                                                                      |
| zone_id        | 是   | int                         | 游戏区ID                                                                    |
| version        | 是   | varchar(5)                  | 回调接口版本3.0                                                             |
| sign           | 是   | text                        | Base64加密的签名 <br> 接收到的参数,除sign以外,按字母排序连接后,进行RSA校验: |

> 注意：苹果渠道请验证 `product_id` 与 `amount` 匹配后再发货，如不验证请提供给我方服务器开发人员苹果商品ID对应金额，我方系统给于配置和验证。


#### 2.2.3. 响应说明
响应内容为JSON格式
如果出现网络异常, 或者返回格式错误, 充值系统无法读取, 将加入充值系统队列处理. 

| 参数  |  类型   |	说明 |
| ----- | ------ | --- |
| code  | int    | 0. 成功; <br> 1. 失败(加入充值系统队列, 每5分钟会进行重复回调，一星期后不在通知); <br> 2. 失败(订单验证异常, 不加入充值系统队列);  <br><br> 游戏服务器有两种情况需要返回成功  <br> 1. 订单第一次请求充值成功返回 0, <br> 2. 如果后续有重复订单请求, 在游戏服务器中验证此订单已经充值过了, 也需要返回 0 |
| msg   | string | 失败响应消息,记录日志用 |

** 失败订单补单规则: ** 每5分钟处理一次失败订单

#### 2.2.4. 签名示例

##### 2.2.4.1. 简例

```javascript
data = account + amount + channel + extra + game_id + openid + order_id + product_id + time + transaction_id + version + zone_id;
sign = base64_decode(sign);

openssl_verify(
data,
sign,
public_key,
OPENSSL_ALGO_SHA1
);

//其中公钥(public_key)由巨人移动服务端对接人员提供
```

##### 2.2.4.2. 范例下载

[RSA 范例下载(PHP, JAVA, C++, C#, Python3)](http://docs.mztgame.com/files/rsa_examples.zip)

####  2.2.5. 请求示例

**HTTP请求数据流举例:**

```
POST /GameServerAction HTTP/1.1
Host: GameServerHost
User-Agent: Mztgame payment server
Connection: close
Content-Length: 565
Content-Type: application/x-www-form-urlencoded

account=abcd&amount=6.00&channel=1&extra=123&game_id=GMG001&openid=1-1234&order_id=1399633295037630&product_id=HWDPID0006&time=1404975144&transaction_id=1000000110081354&version=3.0&zone_id=1&sign=m2S0nl5i%2FbNqUqPMH8%2BqL3f4PgG%2FbINqDafdxo1pzVP%2FoXiItT4hKNkuXVXbBOlCrsG3wDkIMfWgBeo3JHrDqlS4wF6MPNVscc8JnaHn2IzeYnbew8r%2BdyLB0XDBkugUcEQHlO4Aat82PRWoRWFRDbdBK9mClbBsgEObW7%2BkcAMFoJlpxedjzk7LzDcPf8qXfLmr0HfU70RRj6%2FfhlKA3ZA%2BPL7bzluKg53v3HARRV5MPm90U92iWuL8zecyZ1M775k3pEswW8IllQNc4EuihY5zUJozUxeRmoqLxgZJI3aECy%2FdKlDWcHwWg083TGX3%2FnynYF6JlCj5tZ6WE3zuUQ%3D%3D
```

**响应:**

```
HTTP/1.1 200 OK
Content-Length: 10
Content-Type: application/json
Connection: close

{"code":0}
```

### 2.3. 充值系统IP列表

```
118.194.50.69
118.194.48.217
118.194.50.55

222.73.56.222
222.73.56.213
222.73.56.225
222.73.56.224
222.73.56.226
```
