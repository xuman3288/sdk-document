## 联想SDK配置说明

###  一.接入前的准备

  1. 在开发者网站：<http://open.lenovo.com/developer/>，
  注册您的开发者账号。

  2. 登陆账号后选择“管理－联运合作”，点击“创建应用”,如图所示：

  ![](http://docs.mztgame.com/files/assets/img/lenovo-ztgame1.jpg)

  1)    填写应用名称，包名，类别和游戏简介，注意包名在联想开放平台全局唯一，不可重复，保存后不可修改

  2)    获取应用Open AppID，并按屏幕引导，完成游戏应用内计费点配置

      ![](http://docs.mztgame.com/files/assets/img/lenovo-ztgame2.jpg)

  3. 点击应用内计费点管理，在移动应用内计费系统完善计费数据配置后，进入应用详情查看支付密钥Appkey。

  ![](http://docs.mztgame.com/files/assets/img/lenovo-ztgame3.jpg)




### 二.注意事项

  1.  包名的要求：游戏的安装包名必须以   `.lenovo`  为后缀，不可重复，保存后不可修改，请确保包名的正确性。

  2.  联想的悬浮窗，在游戏上线之后会自动出现。

### 三.常见问题

   1. 切换账号的操作：
           联想登陆一次以后会自动登录，若需切换账号，请登录时点击“切换账号”进行账号的切换。

   2. 渠道登录错误: 渠道方登录错误

    检查appid，appkey，在Manifest文件中要配置appid，搜索字符串“.app.ln”替换成＃app_id＃

   3. 充值崩溃

     检查waresid值是否为空

   4. 黑屏、不兼容、没有悬浮框 
     将版本改成
```xml
     <uses-sdk android:minSdkVersion="4"  android:targetSdkVersion="8"  />
```     
