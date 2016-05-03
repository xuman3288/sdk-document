巨人移动SDK 4.0服务端接口文档
==========================

## 登录接口

### 流程说明
![渠道登录流程图](http://i.imgur.com/dm1aUBd.jpg)

1.	用户通过巨人移动SDK登录后, 获得token
2.	游戏客户端从SDK获得 token和openid 交付游戏服务端
3.	游戏服务端使用token和openid在巨人移动服务端进行认证, 验证成功则登录进入游戏

### 服务端Token验证接口说明

#### 协议说明

* 协议说明: `HTTP`
* 请求方式: `GET`
* 内容类型(Content-Type): `application/x-www-form-urlencoded`
* 接口地址: `http://passport.mobileztgame.com/service/check-token`

#### 参数说明

| 参数     |  必填 |	说明 |
| -------- | ---- | --- |
| game_id  | 是   | 游戏应用ID  |
| openid   | 是   | 由SDK获得唯一ID, (数据库建议设置长度 120位) |
| time     | 是   | 时间戳,以秒为单位, 如(1421212874) |
| token    | 是   | 由SDK获得 |
| sign     | 是   | 签名, 生成规则: <br>Md5(game_id+openid+time+token+key) <br>其中key 由, 巨人移动服务端对接人员提供 |

#### 响应说明

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


#### 请求举例

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

## 充值回调接口

### 支付回调(V3.0)接口说明

适用于 2015-1-14以后新游戏的接入, 2015-1-14之前接入的游戏, 使用(2.x/1.x)的版本回调, 如需更新回调版本请联系服务器开发人员配合调试.

接口均由巨人移动充值系统通知游戏服务器, 如需IP验证查看充值系统IP列表

### 支付流程说明

![支付流程图](http://i.imgur.com/cHg5fye.jpg)


#### 协议说明

* 协议说明: `HTTP`
* 请求方式: `POST`
* 内容类型(Content-Type): `application/x-www-form-urlencoded`
* 通知回调版本:  Ver 3.0

#### 参数说明

| 参数           | 必填 | 类型、最大长度(Mysql Types) | 说明                                                                        |
| -------------- | ---- | --------------------------- | --------------------------------------------------------------------------- |
| account        | 是   | varchar(100)                | 账号名                                                                      |
| amount         | 是   | decimal(15,2)               | 总金额(单位人民币), 浮点数两位 decimal(15,2)                                |
| channel        | 是   | int                         | 渠道ID, 详情查看 [渠道信息列表](/docs/channel)                              |
| extra          | 是   | varchar(255)                | 游戏扩展数据, 创建订单传入的值, 原数据返回                                  |
| game_id        | 是   | int                         | 游戏ID                                                                      |
| order_id       | 是   | bigint                      | 巨人移动订单号                                                              |
| product_id     | 否   | varchar(45)                 | 苹果商品编号或安卓渠道或游戏自定义商品编号                                  |
| time           | 是   | int                         | 巨人移动充值服发起请求,秒为单位的时间戳                                     |
| transaction_id | 是   | varchar(100)                | 第三方交易单号                                                              |
| openid         | 是   | varchar(128)                | 账号ID                                                                      |
| zone_id        | 是   | int                         | 游戏区ID                                                                    |
| version        | 是   | varchar(5)                  | 回调接口版本3.0                                                             |
| sign           | 是   | text                        | Base64加密的签名 <br> 接收到的参数,除sign以外,按字母排序连接后,进行RSA校验: |

##### 签名事例

```javascript
data = account+amount+channel+extra+game_id+order_id+product_id+time+transaction_id+openid+zone_id;
sign = base64_decode(sign);

openssl_verify(
data,
sign,
public_key,
OPENSSL_ALGO_SHA1
);

//其中公钥(public_key)由巨人移动服务端对接人员提供
```

[RSA 范例下载(PHP, JAVA, C++, C#)](http://docs.mztgame.com/files/rsa_examples.zip)


#### 响应说明
响应内容为JSON格式
如果出现网络异常, 或者返回格式错误, 充值系统无法读取, 将加入充值系统队列处理. 

| 参数  |  类型   |	说明 |
| ----- | ------ | --- |
| code  | int    | 0. 成功; <br> 1. 失败(加入充值系统队列, 每5分钟会进行重复回调，一星期后不在通知); <br> 2. 失败(订单验证异常, 不加入充值系统队列);  <br><br> 游戏服务器有两种情况需要返回成功  <br> 1. 订单第一次请求充值成功返回 0, <br> 2. 如果后续有重复订单请求, 在游戏服务器中验证此订单已经充值过了, 也需要返回 0 |
| msg   | string | 失败响应消息,记录日志用 |

##### 失败订单补单规则 
每5分钟处理一次失败订单


#### 签名示例

* 参数:

```
account = abcd
amount = 6.00
channel = 1
extra = 123
game_id = GMG001
openid = 1-1234
order_id = 1399633295037630
product_id = HWDPID0006
time = 1404975144
transaction_id = 1000000110081354
version = 3.0
zone_id = 1
```

* 示例公钥:

```
-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA/VweA5KME/PR7QIwe+Bo
Wf+yM5tRVpaXWOZC7S4SeLT5zyd1gNzKjLHCxAIhsxYUnXHRCdsC+cnKTVfCBuew
v7N2kZCKF+/gMiqSdfiJo3XE7lmrxpIKO6YnWt7Itq/VvMJoTO7g0KkjF/irzI+O
KUj5DSZORHwulA6OXNjUJbGeMdIGX7VHgbk7dv8oqx+FE0bAQ4APtOiSs5agBRdg
9De92tURcj15jztYtPvaRVn5O8ozTEui4Kh2Cmf1fPFKbv5yQyNhHaqMdI2tGPRE
s78wGIiHT1yOmCMeKHCVgElwwgmFnmxKYsBD9XZ9GM6wzt/95M53jh/aNp/+9Y1m
0QIDAQAB
-----END PUBLIC KEY-----
```

* 示例私钥, 实际不提供游戏开发人员私钥, 由巨人移动保存维护:

```
-----BEGIN PRIVATE KEY-----
MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQD9XB4DkowT89Ht
AjB74GhZ/7Izm1FWlpdY5kLtLhJ4tPnPJ3WA3MqMscLEAiGzFhSdcdEJ2wL5ycpN
V8IG57C/s3aRkIoX7+AyKpJ1+ImjdcTuWavGkgo7pida3si2r9W8wmhM7uDQqSMX
+KvMj44pSPkNJk5EfC6UDo5c2NQlsZ4x0gZftUeBuTt2/yirH4UTRsBDgA+06JKz
lqAFF2D0N73a1RFyPXmPO1i0+9pFWfk7yjNMS6LgqHYKZ/V88Upu/nJDI2Edqox0
ja0Y9ESzvzAYiIdPXI6YIx4ocJWASXDCCYWebEpiwEP1dn0YzrDO3/3kzneOH9o2
n/71jWbRAgMBAAECggEBAOqhKFp+mKLRpZMr9+KAi6LtHQaVy9m4VCjL9SbZB9op
Hz8sd7ECx8FLzebfYn4K8MRt5vyNik/xBBxkLRdf8VtWw3NCu+A3ERTkQAZoSG7M
Xbet61pVXIyo7sFEl/Q3GmVYVGcKwb/7IrjMXa1vNGQfZCU9WDYsGqaaZFSvqKfN
4dIkT6kySXkVrZuoW9i03EOC5YoxtlvPI/tpSao+C/tIytVf0wLzdy3eOCdxylRd
hExNTQX+wh62QzsnXwZZjIyg96YOWZanud5SKB9EydPIEgvvPW0bPnOU7Xa/6C/h
/3+7mhY8Rm/ZsYXhaOLhsf4BFysMGXjOndM4s9u6cMECgYEA/sF3gvcqL3QFwlG4
4Ui0uA6AXlwzXGKDU/eUlgSEt/RrqHKBndu4pB5WrsrV5zW2fSyiMo7X5WL8chnj
9+1a0gzfkXgW95HAV4lO+Iubjc1oXWBgh7HcKvWFi117gCSpbvFGUeBG6EQy9Txe
OJAGreJ3tOGPDTtY7GpNvcYHVv0CgYEA/pjnsPTk15aFMtnUyFD8QDyKaiY8ULte
AgBEW9EhPJcrkhVA6FBr/TPtstmOBptSu2f7ImtwGGiHlJPtctkqGwVZ/yZvo3J/
/qqkQ86R9O+cXLL86xjq/SRukDrHmc35iG4zG18pxGYOJ1y91q3STLkjEi2IP/h6
M1H4olD8+WUCgYEAzfaqYfbkY1MhKNaba4A1o60Nubw/RgzbcnJy7cqfZn404A4b
nv26TO2+VTjmMTTq4k7In6l/ARTDVph6B8MdLz97LQfzQZrT77GyaB2u2LleDEhU
IsD4aL7xhhhBNfvuBCULHjz99uDUdhKldoNy8vhqoZHO7vqxTQow6iIFYx0CgYEA
nSyyZHlbSYjgJDt8dEoi4Fk/rjZ6p/B0IdIeYXyjAy/kMyQx1PnJISDMzswmNQfw
tEeGL4KaSkdF5EDKp0tQBixwmecVppcKU6LvE/lT/B1sr4HESDMy5yJ1q07OaLJT
KZm2rWA7fcbvV/eWLxFSMhFdiFxZr/xrHIGveC19b00CgYBjY+yTxn7UGrudGAHE
4DXuuzfKmyxJcJaJXfJeXQ/zAnryFvfEr30G7VxTprF0Mizf3blUMNFq/uwGin7f
sZq0R7AmBW2sC+LpOHX9+Bi3Ui9ZgSmzT0F8diXnNGa92P77mQwD0KDASOiqcTF4
4ToHQ2dR0DqgN+LILINh2Chz0Q==
-----END PRIVATE KEY-----
```

* 私钥生成签名:

```
m2S0nl5i/bNqUqPMH8+qL3f4PgG/bINqDafdxo1pzVP/oXiItT4hKNkuXVXbBOlCrsG3wDkIMfWgBeo3JHrDqlS4wF6MPNVscc8JnaHn2IzeYnbew8r+dyLB0XDBkugUcEQHlO4Aat82PRWoRWFRDbdBK9mClbBsgEObW7+kcAMFoJlpxedjzk7LzDcPf8qXfLmr0HfU70RRj6/fhlKA3ZA+PL7bzluKg53v3HARRV5MPm90U92iWuL8zecyZ1M775k3pEswW8IllQNc4EuihY5zUJozUxeRmoqLxgZJI3aECy/dKlDWcHwWg083TGX3/nynYF6JlCj5tZ6WE3zuUQ==
```

* PHP 代码示例:

```php
$params = $_POST;
$sign = base64_decode($params['sign']);
unset($params['sign']);
ksort($params); //可忽略, 参数传递时已经排序
$signData = implode($params);
$keySource = openssl_pkey_get_public($publicKey);
if (openssl_verify($signData, $sign, $keySource) === 1) {
    echo "成功";
} else {
    echo "失败";
}
```

#### 请求举例

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

### 充值系统IP列表

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
