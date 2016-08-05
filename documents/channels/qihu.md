
## 奇虎360SDK配置说明

###  一.接入前的准备

  1. 商务在开发者网站：http://dev.360.cn/ ，注册您的开发者账号。

    ![](http://docs.mztgame.com/files/assets/img/360helper.jpg)

  2. 登陆账号，点击管理中心，选择我的应用，如图

      ![](http://docs.mztgame.com/files/assets/img/360helper2.jpg)

###  二.注意事项

  1.  包名 +   `.qh360 `  。


###  三.常见问题

  1. 如何切换账号以及切换账号的处理浮标上点击切换账号，输入新的账号和密码，
      登录成功后会收到onLoginSuccess的回调，失败后会收到onLoginFailed的回调

  2. 登陆和支付界面是否支持横竖屏切换？登陆界面和支付界面都支持，
      支付界面的横竖屏可在工具上配置。
