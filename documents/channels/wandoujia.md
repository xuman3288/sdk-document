
 ## 豌豆荚SDK配置说明

 ###  一.接入前的准备

  1. 在开发者网站：http://developer.wandoujia.com/，
  （豌豆荚开发者中心的默认浏览器为 Chrome。建议您使用 Chrome，避免注册过程发生未知错误。）点击网页右上方的“登陆”按钮。若您已经注册过豌豆荚的账户，直接登陆即可。若您还未注册，请注册一个豌豆荚的账户。注册完毕后页面会自动跳转。

   ![](http://docs.mztgame.com/files/assets/img/wdj01.png)

  2. 登陆账号后，选择右上角的“管理应用”，按要求完善开发者信息。

  ![](http://docs.mztgame.com/files/assets/img/wdj02.png)

  3. 如果需要修改账号信息，可点击 右上角头像，下拉列表里的“修改注册信息”

  ![](http://docs.mztgame.com/files/assets/img/wdj03.png)

  4. 豌豆荚不直接审核账号，在开发者中心首页，点击[应用](http://open.wandoujia.com/home/myapp)就可以直接提交您的应用/游戏，并完善信息。

        ![](http://docs.mztgame.com/files/assets/img/wandoujiahelper1.png)

        「+添加新应用」 – 提交应用，或非联运游戏。不开计费游戏

        「+联运游戏」 – 提交联运游戏评审。开计费游戏

        「+接入移动广告」 – 接入网盟广告SDK

        上传应用，选择“+添加新应用”

        注意事项：豌豆荚要求平台上的游戏包名必须以 **.wdj** 结尾

  5. 回调地址的设置：

    ![](http://docs.mztgame.com/files/assets/img/wandoujiahelper2.png)

###  二.注意事项

    1.  包名 + ** `.wdj` **  。
    2.  豌豆荚有悬浮窗,请确保onPause、onResume已经调用。
    3.  切换账号的逻辑：在登出回调onLogout里，调用登录接口login

### 三.常见问题解决

   1. 支付订单后没有收到豌豆荚服务器回调通知的原因：。

           1. 配置的URL是否正确，可在开发者后台http://open.wandoujia.com/home/myapp
             里编辑，会立刻生效。

           2. 检查客户端appkey是否正确

           3. 查看游戏服务器的access log

           4. 在开发者后台http://open.wandoujia.com/home/myapp里查询订单详情中的回调时间

           5. 确认以上没有问题后，可以在开发者后台里查询失败订单发起手动回调
