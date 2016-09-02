## vivo（步步高）SDK配置说明

###  一.接入前的准备

  1. 登陆vivo开发者平台：<http://developer.vivo.com.cn/>

  2. 在“应用管理”中的“创建新应用”创建一个新的应用名及包名，注意网页上提示的包名格式，**包名一旦新建则不可修改且一定要与接入sdk的应用包名保持一致；一旦在草稿箱中删除这个应用名，包名将永不生效，请注意！**

  ![](http://docs.mztgame.com/files/assets/img/bbg-online1.jpg)

  3. 创建后进入完善应用信息界面，生成新的appid，一个包名对应一个appid，请记录这个参数
     鼠标移入框中全选即可复制，接入sdk会用到
     若不马上完善信息，则可在草稿箱中找到此应用，进入详情则可重新查看appid参数

  ![](http://docs.mztgame.com/files/assets/img/bbg-online2.jpg)

  4. 进入“个人管理”中的“个人信息”，页面抬头显示cpid和cpkey
    这两者是接入sdk的另外两个重要参数，鼠标移入框中全选即可复制

  ![](http://docs.mztgame.com/files/assets/img/bbg-online3.jpg)

  5. 拥有以上3个参数后，可对接vivosdk，具体用途在下方文档中描述

###  二.注意事项

  1.  包名格式：按 com.公司名.游戏名   ` .vivo `  ，要与vivo平台创建包名一致。

  2.  无闪屏、角标。。

###  三.常见问题

   1. 支付界面无法弹出？

           检查参数有没有配置正确，无误后再检查支付接口里面的参数有没有填写正确，特别是价格

           （price）的单位。

   2.  支付参数不全，不能进行支付  payment_params参数需要核对 { 确保微信wxapi存在，配置文件 }
   
    ```xml
    
        config.cpid=#cp_id#
        config.appid=#app_id#

        <data android:scheme="qwallet#package#" />
     ```
