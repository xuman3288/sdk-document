###GiantAndroidEasyTools FAQ

---------------------
Version:3.3.0

  1. **Q: 为什么使用使用自动打包工具接入的官方渠道不能支付，其他渠道的支付都是可以的？**  
 	A: 请参考接入文档。其中需要向你的工程中添加官方支付所需要的资源，还有Framework中的src两个java文件拷贝到你的工程目录中
		
 2. **Q:接入其他渠道需要把官方渠道的res 和src中的文件添加到工程么？**  
 	A:不需要，如果将这些文件接入到工程将会导致最终编译apk包出错。  
 	
 3. **Q:为什么接入`vivo`渠道没有支付回调？**    
 	A:请参考Demo代码添加onActivityResult方法。
 	
 		@Override
    	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        	super.onActivityResult(requestCode, resultCode, data);
        	IZTLibBase.getInstance().onActivityResultZTGame(requestCode, resultCode, data);
    }  
 
 4. **Q:为什么使用自动打包工具生成的`豌豆荚`渠道的apk包无法进行登录？**  
 	A:请参考文档，注意事项条目。
 	
 5. **Q:为什么某些渠道按下back按键  home 按键还有退出游戏，在进入游戏会黑屏？**  
 	A:在onStop中调用IZTLibBase的onStop方法。
 	 
 6. **Q:游戏中需要实现切换账号的逻辑吗？**  
 	A:在游戏的mListener的回调中处理ZTGAME_LOGOUT消息，做切换用户的逻辑。切换逻辑是账号登出，重新调用登录接口。 
 	
 7. **Q:在Activity中需要实现哪些生命周期**  
 	A:  
 	
 			@Override
   			 protected void onPause() {
        		super.onPause();
        		IZTLibBase.getInstance().onPauseZTGame();
  		  	}

  			@Override
		    protected void onStop() {
        		super.onStop();
        		IZTLibBase.getInstance().onStopZTGame();
    		}

    		@Override
    		protected void onResume() {
        		super.onResume();
        		IZTLibBase.getInstance().onResumeZTGame();
    		}

    		@Override
		    protected void onDestroy() {
        		super.onDestroy();
        		IZTLibBase.getInstance().destroyZTGame();
        		IZTLibBase.delInstance();
    		}
    
    		@Override
    		protected void onStart() {
    			super.onStart();
    			IZTLibBase.getInstance().onStartZTGame();
    		}
    
    		@Override
    		protected void onRestart() {
		    	super.onRestart();
    			IZTLibBase.getInstance().onRestartZTGame();
    		}
 	
 8. 倘若游戏中存在多个Activity，初始化的并非游戏主Activity，需要在进入游戏之后在游戏的主Activity中调用 IZTLibBase的setActivity方法重置为主要的Activity
 9. **Q:游戏有自己Application，如何接入？**
 	
	A:游戏的Application需继承jar包中的ZTApplication类，然后在实现游戏自己的代码，详情参考接入文档。
 
 
 
    