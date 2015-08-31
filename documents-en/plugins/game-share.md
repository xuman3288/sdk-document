### 查询接口地址

请求地址: `http://share.plugin.mobileztgame.com/query.php` 

请求方式: GET

### 参数说明:


| 参数名  | 说明          |
|---------|---------------|
| game_id | 游戏应用ID    |
| openid  | 分享者账号ID  |
| channel | 渠道ID        |
| sign    | 签名 md5(game_id+"&"+openid+"&"+channel+"&"+key), 其中 key 为接登录时分配的密钥 |


### 响应说明

响应内容格式为: `application/json`


| 参数名  | 类型   | 说明          |
|---------|--------|---------------|
| code    | int    | 响应码, 0: 正常返回; 大于0: 错误, error 字段为错误信息  |
| error   | string | 错误消息           |
| count   | int    | 分享者带来的账号数 |
| inviters | array | 分享者带来的账号 |
