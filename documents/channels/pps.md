
## PPS SDK配置说明

###  一.接入前的准备

  1. 需要与PPS渠道商务联系，获取应用必需的参数<http://open.iqiyi.com/>。

      获取必需的参数：gameid，key，渠道id，支付key。

  2. 同步地址的设置，联系PPS商务

### 二.注意事项
  1.  包名 +   `.pps`   结尾，PPS有浮标，登录后自动出现。
  2. 接入PPS后，游戏启动后会出现一段时间的版本检测中的窗口，这是正常现象。

### 三.常见问题

  1. 如何切换账号以及切换账号的处理

       PPS没有直接的切换账号，若游戏需要，请确保正确调用退出函数。

  2. U3D环境的游戏，登录后，等浮标缩到屏幕边缘时，点击无反应

  需要在游戏的主activity加以下属性：

   ```xml
  <meta-data
  android:name="unityplayer.ForwardNativeEventsToDalvik"
  android:value="true" />
  ```

  3. pps退出游戏时，要求弹出pps退出界面，为何没有弹出pps退出界面？

  1） 在登录情况下退出才会弹出pps退出界面

  2） 还需要在pps后台配置，才会有pps退出界面
