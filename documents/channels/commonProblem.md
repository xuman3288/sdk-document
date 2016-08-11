### Java 环境接入常见问题
1. 修改退出框

  ```java  
    isHasQuitDialog() 返回true表示调用游戏退出框
    quitZTGame() 方法里面执行退出逻辑，一般发送退出消息 ZTGAME_QUIT -1
  ```
2. 充值不到账

       检查充值通知地址有没有配置，客户端还是商务配置，，核对appid之类参数。

3. 登陆成功后悬浮框不显示，按home键之后再次回到前台又出现。

       如果在onRsume里面登陆成功之后再显示悬浮框，可能就会出现改问题。
       解决方法登陆成功后，调用悬浮框重新显示。       

4. 按Home键登录框消失

       将Activity的启动模式设为默认或者singleTop。

5. 闪屏调用

 ```java

	base里面ZTGameSplashActivity

 ```
6. 包名不能为空？（渠道包名有没有配置，重启） 
