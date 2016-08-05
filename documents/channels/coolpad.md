## 酷派 SDK配置说明

### 一.接入前的准备

1. 获取开发者账号后登陆开发者平台: http://appdev.coolyun.com/ylra/

  ![](http://docs.mztgame.com/files/assets/img/coolpadhelper1.jpg)

   点击新增按钮创建应用

   ![](http://docs.mztgame.com/files/assets/img/coolpadhelper2.jpg)

   填写应用相应的信息。

    在资源管理标签中可以看到您所有的应用，同时也可以看到系统自动分配的appid

  ![](http://docs.mztgame.com/files/assets/img/coolpadhelper3.jpg)

2. 获取KEY。

  ![](http://docs.mztgame.com/files/assets/img/coolpadhelper4.jpg)

  点击密钥按钮获得appkey

  ![](http://docs.mztgame.com/files/assets/img/coolpadhelper5.jpg)

3. 创建计费点

   在首页下点击支付系统，进入创建计费点的界面

   ![](http://docs.mztgame.com/files/assets/img/coolpadhelper6.jpg)

    跳转到计费点添加页面后点修改应用信息

  ![](http://docs.mztgame.com/files/assets/img/coolpadhelper7.jpg)

  跳转至下图页面后即可对计费点、应用信息、支付方式进行编辑。

  ![](http://docs.mztgame.com/files/assets/img/coolpadhelper8.jpg)

4. 配置支付回调地址

    酷派两种配置地址方式：

    方式一，渠道后台配置。

    进入【应用管理】->【应用信息】，填写支付回调地址：

   ![](http://docs.mztgame.com/files/assets/img/coolpadhelper9.jpg)

5. 查看统计信息

    在首页下点击统计系统，进入统计信息的界面

    ![](http://docs.mztgame.com/files/assets/img/coolpadhelpr9a.jpg)

    跳转到下图页面中查看相应统计信息:

    ![](http://docs.mztgame.com/files/assets/img/coolpadhelper9b.jpg)

### 二.注意事项

 1.  包名的要求：包名必须以  `.coolpad ` 结尾。

### 三.常见问题

 1. 登陆和支付界面是否支持横竖屏切换

     登陆界面和支付界面都支持。

 2. 如何切换账号

     调用logout接口登出，然后调用login接口登陆，完成账号切换的处理。
