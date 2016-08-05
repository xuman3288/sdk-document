## 快用SDK 配置说明

###  一.接入前的准备

 1. 请联系要玩商务人员申请参数，快用商务

 2. 支付结果地址配置

### 二.注意事项

 1.  包名 +   `.KY `   结尾。


### 三.常见问题

  1. 游戏中，锁屏解锁后，游戏重启，初始化界面启动，怎么处理？

   在AndroidManifest.xml文件中设置游戏Activity的属性

   ```xml
   android:configChanges="orientation|keyboardHidden|screenSize"。
   ```
  2. 如何切换账号以及切换账号的处理？        

        浮标上点击“退出当前账号”，切换账号后，会收到switchAccountZTGame的回调，游戏方可以在这个回调中调用登录接口，以及切换账号的处理。
