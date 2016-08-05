 ## 华为SDK配置说明

 ###  一.接入前的准备

  1. 注册成为开发者：登录华为开发者网站：http://developer.huawei.com，注册成为开发者。

  2. 登录账号，点击创建移动应用。如图：

    ![](http://docs.mztgame.com/files/assets/img/huawei_ztgame1.jpg)

  3. 创建完app后，点击创建的应用，获取游戏所需的参数：AppID，支付私钥，支付公钥，支付 ID，CPID，浮标密钥
  4.  配置回调地址：

  ![](http://docs.mztgame.com/files/assets/img/huawei_ztgame3)

### 二.注意事项

  1.  包名 + ** `.huawei` ** 结尾。


### 三.常见问题

   1. SDK是否支持横屏？

          帐号和游戏中心浮标支持横屏设置，支付界面也支持。

   2. 点击“立刻登录”出现“网络异常”的提示，怎么办？        
          查看日志中是否存在error=1202这个关键字，如果存在说明包名和appId不是对应的，
          请从巨人平台获取当前应用包名对应的appId

   3. 浮标界面“我的”，“礼包”内容无法显示或显示出错了的问题。
          请检查CPId和浮标密钥这两个参数是否与申请的一致，要保证这两个参数的正确性。

   4. 出现短信暂未送达，请重试，如下图情况，怎么办？        

      ![](http://docs.mztgame.com/files/assets/img/huawei-ztgame5)

      a.检查手机的系统时间是否正确；

      b.清空应用数据再重试。

   5. 如何切换账号？    
            浮标上点击我的个人中心，里面有切换账号功能。
            切换账号后，会收到”onlogout”的通知，然后调用登录接口。
