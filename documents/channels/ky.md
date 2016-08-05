## 快用SDK 配置说明

###  一.接入前的准备

 1. 注册成为开发者：登录华为开发者网站：http://developer.huawei.com，注册成为开发者。

 2. 登录账号，点击创建移动应用。如图：

   ![](http://docs.mztgame.com/files/assets/img/huawei_ztgame1.jpg)

 3. 创建完app后，点击创建的应用，获取游戏所需的参数：AppID，支付私钥，支付公钥，支付 ID，CPID，浮标密钥
 4.  配置回调地址：

 ![](http://docs.mztgame.com/files/assets/img/huawei_ztgame3)

### 二.注意事项

 1.  包名 + ** `.KY ` ** 结尾。


### 三.常见问题

  1. SDK是否支持横屏？

   在AndroidManifest.xml文件中设置游戏Activity的属性
   ```java
   android:configChanges="orientation|keyboardHidden|screenSize"。
   ```
  2. 如何切换账号以及切换账号的处理？        

        浮标上点击“退出当前账号”，切换账号后，会收到switchAccountZTGame的回调，游戏方可以在这个回调中调用登录接口，以及切换账号的处理。
