## 百度移动SDK配置说明

###  一.接入前的准备

 1. 开发者网站：http://app.baidu.com/，注册您的开发者账号。

 2. 登陆账号后选择“管理中心”，选择“联运游戏”，点击“创建游戏”,如下图：

   ![](http://docs.mztgame.com/files/assets/img/baidugame2.jpg)

 3. 支付结果地址和服务器IP地址

 ![](http://docs.mztgame.com/files/assets/img/baidugame3.jpg)
        3.1、支付结果地址配置

        3.2、服务器IP地址配置：222.73.31.33

 注意事项：

  完成创建后，游戏名称更改需通知百度平台，由百度平台操作更改，游戏类别一旦建立，游戏就无法更改，其他信息可由CP端编辑更改。
 4.  创建完游戏后， 获取游戏所需的参数：APP ID、APP KEY和SECRET KEY:

 ![](http://docs.mztgame.com/files/assets/img/baidugame4.jpg)

### 二.注意事项

 1.  包名 + ** `.baidu` ** 结尾。


### 三.常见问题

  1. 如何切换账号以及切换账号的处理

         浮标上点击“个人中心”->“切换账号”，输入新的账号和密码，登录成功后会收到   loginsuccess的回调，失败后会收到loginfailed的回调。

  2. 充值时，充值卡充值中选项移动/电信/联通不全？        
         如果定额充值， 会根据金额来显示充值卡，没有显示的运营商是因为没有对应金额的卡。

  3. 手机一键注册失败，为什么？

        确定手机中是否有SIM卡且有效，能够发送短信。
        百度移动游戏SDK一键注册是通过手机发送短信来获取手机号码为账号，并注册账号

  4. 百度移动游戏接入sdk论坛：       

     http://bbs.mgame.baidu.com/forum.php?mod=viewthread&tid=530&extra=page%3D1

  5. 使用多酷账号登陆，修改密码成功后，再点击个人中心，浮标消失或游戏停止运行    

           需要在onlogout回调里，调用login接口。。
