媒体合作广告点击调用接口说明
==================
 
## 接口说明

 本广告点击对接接口使用HTTP协议，GET方法。
 
### 点击接口地址

`http://ad.stat.mztgame.com/ad/hits`
 
### 接口参数
 
|参数名 | 说明 | 必填 |
|------|------|------|
| ad_id | 对接广告ID(巨人提供) | 是 |
| game_id | 巨人游戏应用 id(巨人提供) | 是|
| idfa | IOS IDFA | 否 |
| imei | 设备IMEI(Android)  | 否 |
| mac | IOS 或Android Mac | 否 |
| ua | 用户若是通过网页点击的， 为浏览器的 User-Agent 信息 | 否 |
| ip | 用户IP | 否 |
| redirect_url | 跳转URL（客户端点击 302 跳转方式，S2S 无需传此参数）（限制域名：`*.[m]ztgame.com`, `itunes.apple.com`) |  否 |
 
### 对接地址范例

* Server to server方式:

```
http://ad.stat.mztgame.com/ad/hits?ad_id=2000001&game_id=5012&idfa=(idfa)&ip=1.1.1.1&ua=(ua)
```

* 客户端跳转方式

```
http://ad.stat.mztgame.com/ad/hits?ad_id=2000001&game_id=5012&idfa=(idfa)&ua=(ua)&redirect_url=https://itunes.apple.com
```
 
### 响应说明
 
* Server to server方式:

HTTP 响应状态码为 204，无响应内容

* 客户端跳转方式

HTTP 响应状态码为 302，跳转地址是 `redirect_url` 参数传入值。
