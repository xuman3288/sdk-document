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
		
		GASDKManager.Instance.login (true,loginResult);
	}

    public void testPay()
    {
		
		GASDKManager.Instance.pay (new GAPayInfo("zonId","productId","productName",100,1,1,""),
			logPayResult);
	}

    public void testSwitchUser()
    {
		
		GASDKManager.Instance.switchUser (); 
     
    }
    public void testUserCenter()
    {
		
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
