GiantMobile SDK4.0 server guide
==========================

## Login guide

### Login guide
![Login chart](http://i.imgur.com/SbDLwTI.png)


1.	When a user login, get a token.
2.	Game client gets `token` and `openid` from GiantMobileSDK, and submit to the game server. 
3.	Game server to [verify the token](#__2). If is valid , then todo enter the game.


### Token verification API

#### Protocol

* Protocol: `HTTP`
* Request method: `GET`
* Content-Type: `application/x-www-form-urlencoded`
* Access url: `http://passport.mobileztgame.com/service/check-token`

#### Parameters

| Parameter |  Required/Optional | Description |
| --------- | ------------------ | ----------- |
| game_id   | Required           | Game ID  |
| openid    | Required           | Account openid. Get from GiantMobile SDK client `accid` |
| time      | Required           | Game server current timestamp. (For example: `2015-01-01 00:00:00 = 1420041600`) |
| token     | Required           | Get from GiantMobile SDK client `token` |
| sign      | Required           | Signature |

> **Generation method:**
> Md5(game_id+openid+time+token+key) <br>
> 
> **PS:** The key get from GiantMobile Server programmer

#### Response

**Content-Type:** application/json

| Parameter | Type  | Description |
| --------- | ----- | --- |
| code      | int   | 0: success <br>  gt 0: failed |
| error     | string | Error message, when `code > 0`. |
| errors    | object | Empty or invalid format param(s) <br> (For example : `{"time":"error msg","token":"error msg"}` ) |
| entity    | object | Account info entity. <br> For example: `{"openid":"1-1234","account":"test","nickname":"昵称"}` <br><br>`openid` is unique identify.<br>`account` may be null.<br> `nickname` may be null/undefined. |

> `entity.openid` note:
> 
> Example: 1-1234 , 26-5678; <br>
> Before “-“ is vendor’s NO.; <br>
> After “-“ is vendor’s UID.; <br>

#### Request example

When key = 123456
Generate signature:

```php
//Md5(game_id+openid+time+token+key)
md5("50121-1234142121287408897c5d66eb86b8c6d50c623e63ea27123456");
//Result: 8da532dffb888fc0dbb88465032e20fa
```

**Request url:**
```
http://passport.mobileztgame.com/service/check-token?game_id=5012&openid=1-1234&time=1421212874&token=08897c5d66eb86b8c6d50c623e63ea27&sign=8da532dffb888fc0dbb88465032e20fa
```
**Response:**
```json
{"code":0,"entity":{"openid":"1-1234","account":"test"}}
```

## Payment guide

**Version:** `3.0`

### Payment guide

Request from GiantMobile payment server. Server IPs [see payment server IP list](#__14)
Tell our developer your feedback URL and set it by us when you complated your API.

![Payment chart](http://i.imgur.com/bsUs9zZ.png)

#### Protocol

* Protocol: `HTTP`
* Request method: `POST`
* Content-Type: `application/x-www-form-urlencoded`

#### Parameters

| Parameter  | Required/Optional | Description  |
| ---------- | ----------------- | ------------ |
| account    | Optional | Account name. May be empty string |
| amount     | Required | RMB, decimal(15,2) |
| channel    | Required | Vendor NO. |
| extra      | Optional | Game extra data. Set from GiantMobile Client. |
| game_id    | Required | Game ID |
| order_id   | Required | GiantMobile order id |
| product_id | Optional | Product ID. IOS set it product ID |
| time       | Required | GiantMobile server timestamp, (Unit: second) |
| transaction_id | Required | Vendor transaction id |
| openid     | Required | openID|
| zone_id    | Required | Game zone ID. Set from GiantMobile SDK client |
| version    | Required | Request version 3.0 |
| sign       | Required | Rsa private sign. <br> Algo: `SHA1` <br> Data format: <br> `data = account+amount+channel+extra+game_id+openid+order_id+product_id+time+transaction_id+version+zone_id;` <br> **PS:** Public key get from GiantMobile server programmer. |

#### Response

**Content-Type:** application/json

If the connection or protocol is broken. Request will try again.


| Parameter | Type | Description |
| --------- | --------- | --------- |
| code      | int  | 0. Success; <br> 1. Failed(Request will try again every minutes, when the code is equal to 0 stop it); <br> 2. Failed(Never request again); <br><br> Game server return success (`code=0`) condition: <br> 1. First request and game server execute successfully. <br> 2. The same order request and the game server has been executed. |
| msg | string | Error message, when the code is greater than 0. |



### RSA verify example

**Request params:**

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

**A public key example:**

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

**A private key example:**

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

**Generate result:**

```
m2S0nl5i/bNqUqPMH8+qL3f4PgG/bINqDafdxo1pzVP/oXiItT4hKNkuXVXbBOlCrsG3wDkIMfWgBeo3JHrDqlS4wF6MPNVscc8JnaHn2IzeYnbew8r+dyLB0XDBkugUcEQHlO4Aat82PRWoRWFRDbdBK9mClbBsgEObW7+kcAMFoJlpxedjzk7LzDcPf8qXfLmr0HfU70RRj6/fhlKA3ZA+PL7bzluKg53v3HARRV5MPm90U92iWuL8zecyZ1M775k3pEswW8IllQNc4EuihY5zUJozUxeRmoqLxgZJI3aECy/dKlDWcHwWg083TGX3/nynYF6JlCj5tZ6WE3zuUQ==
```

**PHP example:**

```php
$params = $_POST;
$sign = base64_decode($params['sign']);
unset($params['sign']);
ksort($params);
$signData = implode($params);
$keySource = openssl_pkey_get_public($publicKey);
if (openssl_verify($signData, $sign, $keySource) === 1) {
    echo "Todo success";
} else {
    echo "Todo failed";
}
```

#### Request example

**Request HTTP stream :**
```
POST /GameServerAction HTTP/1.1
Host: GameServerHost
User-Agent: Mztgame payment server
Connection: close
Content-Length: 565
Content-Type: application/x-www-form-urlencoded

account=abcd&amount=6.00&channel=1&extra=123&game_id=GMG001&openid=1-1234&order_id=1399633295037630&product_id=HWDPID0006&time=1404975144&transaction_id=1000000110081354&version=3.0&zone_id=1&sign=m2S0nl5i%2FbNqUqPMH8%2BqL3f4PgG%2FbINqDafdxo1pzVP%2FoXiItT4hKNkuXVXbBOlCrsG3wDkIMfWgBeo3JHrDqlS4wF6MPNVscc8JnaHn2IzeYnbew8r%2BdyLB0XDBkugUcEQHlO4Aat82PRWoRWFRDbdBK9mClbBsgEObW7%2BkcAMFoJlpxedjzk7LzDcPf8qXfLmr0HfU70RRj6%2FfhlKA3ZA%2BPL7bzluKg53v3HARRV5MPm90U92iWuL8zecyZ1M775k3pEswW8IllQNc4EuihY5zUJozUxeRmoqLxgZJI3aECy%2FdKlDWcHwWg083TGX3%2FnynYF6JlCj5tZ6WE3zuUQ%3D%3D
```

**Game server response HTTP stream:**
```
HTTP/1.1 200 OK
Content-Length: 10
Content-Type: application/json
Connection: close

{"code":0}
``` 

### GiantMobile payment server IP list.

```
118.194.50.69
118.194.48.217
118.194.50.55
222.73.56.208
222.73.56.213
```
