## 腾讯游戏币YSDK配置说明

###  一.接入前的准备

  1. 登录：<http://open.qq.com/2012>

  2. 点“移动应用”→“创建应用”→“移动应用”，填写游戏名称，选择游戏分类和合作模式

  ![](http://docs.mztgame.com/files/assets/img/txysdk1.png)

  ![](http://docs.mztgame.com/files/assets/img/txysdk2.png)

  ![](http://docs.mztgame.com/files/assets/img/txysdk3.png)

  3. 进入“移动应用管理”→“移动应用”，选择游戏后完善游戏基本信息，填写完成后提交审核，7个工作日之内审核完毕。

   a)完善游戏基本信息

  ![](http://docs.mztgame.com/files/assets/img/txysdk4.png)

  4. 基本资料通过审核后，进入签署协议和接入YSDK的步骤。选择合作模式然后点击页面同意签署按钮，
      即可完成协议  的签署。YSDK集成的详细步骤，参阅YSDK文档部分。
       为保障游戏用户体验，网游合作（包含独代、联运、开放接入），必须满足以下条件：

    1): 使用腾讯云服务；

    2): 部署专服；

    3): 使用游戏币模式接入腾讯支付；

    ![](http://docs.mztgame.com/files/assets/img/txysdk5.png)

    ![](http://docs.mztgame.com/files/assets/img/txysdk6.png)

  5. 在http://open.qq.com 完成基本资料审核后，流程到达“接入YSDK”，签署合作接入协议以后就会提供手Q和微信的appid和appkey，此时会同步开启测试环境权限，参照下图：

  ![](http://docs.mztgame.com/files/assets/img/txysdk7.png)

  6. 当应用在YSDK相关的测试环境调试成功以后，就可以提交包，申请开通正式环境的权限。具体流程如下： 在http://open.qq.com 完成接入YSDK后，流程将会到达“删档测试”。当游戏在测试环境调通所有YSDK相关功能以后，就可以切换到YSDK的正式环境准备删档测试，参照下图：

  ![](http://docs.mztgame.com/files/assets/img/txysdk8.png)

  `注意：`

  （1）游戏删档测试时提交的安装包必须为正式环境，而且实现所有功能

  （2）建议在测试环境调通所有功能以后再切换到正式环境调试。

  7. `删档测试`

    游戏上线前，需要在应用宝平台进行删档测试。用于删档测试的安装包是接入YSDK，并且联调成功的正式版本。

    ![](http://docs.mztgame.com/files/assets/img/txysdk9.png)

  8. `提交正式包 `

### 二.注意事项

  1.  YSDK后台环境(测试环境/正式环境), 联调阶段游戏需要使用测试环境, 发布时需要切换到正式环境(公司内测, 公测, 正式上线), 切换环境时候需要注意, 后台也要同时切换成对应的环境.

  2.  需要注意登录的处理：在qq登录或微信登录界面中，如果玩家选择取消登录，需要返回到登录界面。请确保玩家取消登录后，可以有按钮来继续进行登录选择或退出。


### 三.常见问题

 1. 出现以下错误提示：

       ![](http://docs.mztgame.com/files/assets/img/tengxunerror04.png)

       ![](http://docs.mztgame.com/files/assets/img/tengxunerror06.png)

       请检查游戏在平台上，填写的资料是否正确, `包名`, `签名`, 是否审核通过。
       确定游戏打包时，`包名和签名`与平台上的保持一致。
       如何检查签名和包名 ：

       下载
        <https://res.wx.qq.com/open/zh_CN/htmledition/res/dev/download/sdk/Gen_Signature_Android2.apk>

     ， 将此apk安装到手机上， 在输入框中输入游戏的包名，点击按钮读取游戏包的签名。 检查上述工具获取到的签名是否和微信后台配置的签名  一致。后台配置如下图：

  ![](http://docs.mztgame.com/files/assets/img/tengxunweixin07.png)

 2. 游戏中，出现点击登录或确认支付时，游戏会重新启动的现象或者微信登陆失败
     建议在游戏中创建的主activity和闪屏Activity不要加属性。

   ```java
        android:launchMode="singleTask"
   ```
   如果游戏是横屏，需要给游戏Activity都加上属性。

   ```java
        android:configChanges="orientation|screenSize|keyboardHidden"
   ```
 3. 错误码

 `100044`：游戏当前安装包签名与平台上传的安装包签名不一致，请检查打包用的keystone。
   游戏下载地址：
    <https://res.wx.qq.com/open/zh_CN/htmledition/res/dev/download/sdk/Gen_Signature_Android2.apk>，
    并将此apk安装到手机上， 在输入框中输入游戏的包名，点击按钮读取游戏包的签名。
    检查上述工具获取到的签名是否和微信后台配置的签名一致。

    `110406`: YSDK管理端后台签名未同步生效导致，在后台同步签名后重试。
