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

    //登录
    public void testLogin(){

		#if UNITY_ANDROID

		GASDKManager.Instance.loginAndroid("1"," testzoneName ",loginResultAndroid);

		#elif UNITY_IOS || UNITY_IPHONE

		IOSSDKManager.Instance.loginIOS(loginResultIOS);

		#endif
	}
   //支付
    public void testPay()
    {
		#if UNITY_ANDROID

        GASDKManager.Instance.payAndroid("1",10,"1","金币",logPayResult);

		#elif UNITY_IOS || UNITY_IPHONE

		IOSSDKManager.Instance.payIOS ("1","1","金币",1,1,100,"",logPayResult);

		#endif

	}
   //切换账号
    public void testSwitchUser()
    {
		GASDKManager.Instance.switchUser ();

    }
    //用户中心
    public void testUserCenter()
    {

		GASDKManager.Instance.userCenter ();
	}




    //####################### 返回结果 ############################


    public void loginResultAndroid(int ret, string usr, string token,string sign ,string entityjson ,string json){

  		Debug.Log( " 登录成功  In C# Client ret = "+ret + " ,user ="+usr +
              " ,token = "+token+
               " ,sign = " + sign +
                " ,entityjson = " + entityjson +
              ",json = " +json

              );
  		#if UNITY_ANDROID
          GASDKManager.Instance.AndroidToat("登录成功");
  		#endif

  	}
    public void loginResultIOS(int ret, string usr, string token,string sign ,string entityjson ,string json){

      Debug.Log( " 登录成功  In C# Client ret = "+ret + " ,user ="+usr +
              " ,token = "+token+
              ",json = " +json

              );


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

//#######################################################




}


```
