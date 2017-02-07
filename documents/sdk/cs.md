```csharp

public class TestLogin : MonoBehaviour {


    void Start()
    {
		//初始化
		GASDKManager.Instance.InitGASDK("5163","testGameName",false);

    // 默认关闭日志 ，放开注释（打开平台日志）
    //GASDKManager.Instance.EnableDebugGA();

	}


    public void testLogin(){
	     //登录接口 自动登录true，  不自动登录 false			
         GASDKManager.Instance.Login (true,loginResult);
	}

    public void testPay()
    {
		    //支付（区id，商品id，商品名字，商品价格，商品数量，兑换比例，拓展信息）		
       GASDKManager.Instance.Pay (new GAPayInfo("zonId","productId","productName",100,1,1,""),
		  	logPayResult);
	}

    public void testSwitchUser()
    {
		    //切换用户		
		      GASDKManager.Instance.SwitchUser ();

    }
    public void testUserCenter()
    {
		      //用户中心		
          GASDKManager.Instance.UserCenter ();

	}




    //####################### 返回结果 ############################


	public void loginResult(int ret,string json){

		Debug.Log( " 登录成功  In C# Client ret = "+ret +
            ",json = " +json
            );
	}

    public void logPayResult(int ret)
    {
        Debug.Log("  支付  In C# Client ret = " + ret  );
    }

}


```
