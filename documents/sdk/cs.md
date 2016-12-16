```csharp

public class TestLogin : MonoBehaviour {


    void Start()
    {
		//初始化
		GASDKManager.Instance.initGASDK("5163","testGameName",false);
		 #if UNITY_ANDROID
            // 默认关闭日志 ，放开注释（打开平台日志）
         	 //GASDKManager.Instance.enableDebugModeAndroid();
       		 #endif
	}

   
    public void testLogin(){
	//登录接口 自动登录true，  不自动登录 false			
         GASDKManager.Instance.login (true,loginResult);
	}

    public void testPay()
    {
		//支付（区id，商品id，商品名字，商品价格，商品数量，兑换比例，拓展信息）		
                GASDKManager.Instance.pay (new GAPayInfo("zonId","productId","productName",100,1,1,""),
			logPayResult);
	}

    public void testSwitchUser()
    {
		//切换用户		
		GASDKManager.Instance.switchUser (); 
     
    }
    public void testUserCenter()
    {
		//用户中心		
             GASDKManager.Instance.userCenter ();	

	}
 



    //####################### 返回结果 ############################


	public void loginResult(int ret,string json){

		Debug.Log( " 登录成功  In C# Client ret = "+ret + 
            ",json = " +json
            
            );
		#if UNITY_ANDROID
        GASDKManager.Instance.AndroidToat("登录成功");
		#endif

	}
	 
    public void logPayResult(int ret)
    {
        Debug.Log("  支付  In C# Client ret = " + ret  );
        
		#if UNITY_ANDROID
		if(ret == 0)
        {
            GASDKManager.Instance.AndroidToat("支付成功");
        }
        else
        {
            GASDKManager.Instance.AndroidToat("支付失败");
        }
		#endif
    }




}


```
