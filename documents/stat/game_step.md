游戏步骤点统计接口
====================

## 接口说明

应用协议: `HTTP`

请求方式: `GET` / `POST`

参数传递方式(Content-Type): `application/x-www-form-urlencoded`

接口地址: `http://stat.mztgame.com/game/step`

响应内容类型: `JSON`

## 参数说明

| 名称        | 类型        | 必填 | 说明 |
| ----------- | ----------- | ---- | ---- |
| game_id     | int         | 是 | 游戏ID |
| channel_id  | int         | 是 | 渠道ID |
| device_udid | string(100) | 是 | 设备ID |
| device_type | string(100) | 否 | 设备型号 |
| step        | string(255) | 是 | 步骤点说明 |
| sign        | string      | 是 | `md5(game_id+"&"+channel_id+"&"+device_udid+"&"+device_type+"&"+step+"&"+key)`  , 其中 key为登录使用的 login_key |
## 响应说明

| 名称 | 类型 | 说明 |
| ---- | ---- | ---- |
| code | int | 执行结果,  0: 成功; 其它失败 |
| msg  | string | 错误消息 |

## 举例说明

~~~
http://stat.mztgame.com/game/step?game_id=5012&channel_id=1&device_udid=abc&device_type=Iphone&step=123
~~~