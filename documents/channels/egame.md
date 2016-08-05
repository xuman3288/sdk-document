## 电信爱游戏 SDK配置说明

###  一.接入前的准备

  1.  开发者网站：<http://open.play.cn/dev/> 注册您的开发者账号。

  ![](http://docs.mztgame.com/files/assets/img/dxayxhelper0.jpg)

  2.  登录商务合作管理平台,创建游戏，并获取参数appkey、渠道号和Client ID、Client Secret

  ![](http://docs.mztgame.com/files/assets/img/dxayxhelper1.jpg)

  ![](http://docs.mztgame.com/files/assets/img/dxayxhelper2.jpg)

  3. 获取配置文件，审核通过后，点击左侧 普通单产品-产品管理（厂商），下载系统生成的配置文件，即“feeInfo.dat”

    ![](http://docs.mztgame.com/files/assets/img/dxayxhelper5.jpg)

  4. 在爱游戏后台填写

    短信回调地址

    计费回调地址

### 二.注意事项

  1.  包名 +  `.egame `   结尾 ，填写包名要与申请时游戏包名一致 。

  2.  短信支付开启时只支持电信卡支付，1-30元支持短信支付


### 三.常见问题

   1. 调用计费接口后支付界面为什么显示不出来？

   1)检查包名或者游戏名称是不是和爱游戏计费后台填写一致。

   2. 各支付方法计费限额  

|支付方式	 |支付限额 |
|----------|----------|
|短信支付	|1-30元
|移动充值卡	|1-500元
|电信充值卡|	1-100元
|联通充值卡|	1-500元
|支付宝	|1-10000元（具体视银行卡是否支持）
|掌中付	|1-10000元（具体视银行卡是否支持）
