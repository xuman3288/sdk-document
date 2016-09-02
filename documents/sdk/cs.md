```csharp

public class TestLogin : MonoBehaviour {


    void Start()
    {

		GASDKManager.Instance.initGASDK("5163","testGameName",false);

	}


    public void testLogin(){

		#if UNITY_ANDROID

		GASDKManager.Instance.loginAndroid("1"," testzoneName ",loginResult);

		#elif UNITY_IOS || UNITY_IPHONE

		IOSSDKManager.Instance.loginIOS(loginResult);

		#endif
	}

    public void testPay()
    {
		#if UNITY_ANDROID

        GASDKManager.Instance.payAndroid("1",10,"1","金币",logPayResult);

		#elif UNITY_IOS || UNITY_IPHONE

		IOSSDKManager.Instance.payIOS ("1","1","金币",1,1,100,"",logPayResult);

		#endif

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


	public void loginResult(int ret, string usr, string token,string json){

		Debug.Log( " 登录成功  In C# Client ret = "+ret + " ,user ="+usr + " token = "+token+",json = "+json);
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

//#######################################################




}


```
