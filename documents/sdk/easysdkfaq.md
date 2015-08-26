###GiantAndroidEasyTools FAQ

---------------------
Version:4.0.0
 	
 1. **Q:为什么某些渠道按下back按键  home 按键还有退出游戏，在进入游戏会黑屏？**  
 	A:在onStop中调用IZTLibBase的onStop方法。
 	 
 2. **Q:游戏中需要实现切换账号的逻辑吗？**  
 	A:在游戏的mListener的回调中处理ZTGAME_LOGOUT消息，做切换用户的逻辑。切换逻辑是账号登出，重新调用登录接口。 
 	
 3. **Q:游戏有多个Activity，初始化应该传哪个? **  
 	A:倘若游戏中存在多个Activity，初始化的应该传游戏的Activity，如果Activity有变更需要调用IZTLibBase的setActivity方法更新游戏的Activity
 
    