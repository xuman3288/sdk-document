```csharp

using UnityEngine;
using System.Collections;

public class TestGaParentPackage : MonoBehaviour {


    void Start()
    {

		GASDKManager.Instance.initGASDK("5151","testGameName",false);

        #if UNITY_ANDROID
               // GASDKManager.Instance.enableDebugModeAndroid();
        #endif
    }


    public void testLogin(){

		#if UNITY_ANDROID

		GASDKManager.Instance.loginAndroid("1","zoneName",loginResult);

		#elif UNITY_IOS || UNITY_IPHONE

		#endif
	}

    public void testPay()
    {
		#if UNITY_ANDROID

        GASDKManager.Instance.payAndroid("1",10,"1","金币",logPayResult);

		#elif UNITY_IOS || UNITY_IPHONE

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

    public void testHasUserCenter()
    {
        #if UNITY_ANDROID

                GASDKManager.Instance.hasUserCenterAndroid(logHasUserCenter);
        #endif

    }

    public void testHasSwitchUser()
    {
        #if UNITY_ANDROID

                GASDKManager.Instance.hasSwitchUserAndroid(logHasSwitchUserCenter);
        #endif

    }
    public void testHasQuitDialog()
    {
        #if UNITY_ANDROID

                GASDKManager.Instance.hasQuitDialogAndroid(logHasQuitDialog);
        #endif

    }
    public void testQuitDialog()
    {
        #if UNITY_ANDROID

                GASDKManager.Instance.quitZTGameAndroid(logQuitDialog);
        #endif

    }

    public void testGetPlatformId()
    {
        #if UNITY_ANDROID

                GASDKManager.Instance.getPlatFormIdAndroid(logGetPlatFormId);
        #endif
    }

    public void testSetZoneId()
    {
        #if UNITY_ANDROID

                GASDKManager.Instance.setZoneIdAndroid("11");
        #endif
    }
    public void testSetActivity()
    {
        #if UNITY_ANDROID

        GASDKManager.Instance.setActivityAndroid();
        #endif
    }


    public void testIsLogined()
    {
        #if UNITY_ANDROID

                GASDKManager.Instance.getIsLoginedAndroid(logisLogined);
        #endif
    }
    public void testloginOkZTGame()
    {
        #if UNITY_ANDROID

                GASDKManager.Instance.loginOkZTGameAndroid("1","角色名","角色等级","11","区名");
        #endif
    }
    public void testcreateRoleZTGame()
    {
        #if UNITY_ANDROID

                GASDKManager.Instance.createRoleZTGameAndroid("1", "角色名", "角色等级", "11", "区名");
        #endif
    }
    public void testroleLevelUpZTGame()
    {
        #if UNITY_ANDROID

                GASDKManager.Instance.roleLevelUpZTGameAndroid("1", "角色名",  "11", "区名",11);
        #endif
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

    public void logHasUserCenter(bool ishasUserCenter)
    {
        #if UNITY_ANDROID
               Debug.Log("  用户中心  In C# Client ret = " + ishasUserCenter);
               GASDKManager.Instance.AndroidToat("用户中心是否需要 ： "+ishasUserCenter);
        #endif

    }
    public void logHasSwitchUserCenter(bool ishasUserCenter)
    {
    #if UNITY_ANDROID
            Debug.Log("  切换账号 In C# Client ret = " + ishasUserCenter);
            GASDKManager.Instance.AndroidToat("切换账号是否需要 ： " + ishasUserCenter);
    #endif

    }
    public void logHasQuitDialog(bool ishasUserCenter)
    {
#if UNITY_ANDROID
        Debug.Log("  第三方退出弹出框 In C# Client ret = " + ishasUserCenter);
        GASDKManager.Instance.AndroidToat(" 第三方退出弹出框 是否需要 ： " + ishasUserCenter);
#endif

    }
    public void logQuitDialog(int retcode)
    {
#if UNITY_ANDROID
        Debug.Log("  第三方弹出框  确认按钮In C# Client ret = " + retcode);
        GASDKManager.Instance.AndroidToat("第三方弹出框  确认按钮 ： " + retcode);
#endif

    }
    public void logGetPlatFormId(int  retcode)
    {
    #if UNITY_ANDROID
            Debug.Log("  渠道ID In C# Client ret = " + retcode);
            GASDKManager.Instance.AndroidToat(" 渠道ID ： " + retcode);
    #endif

    }
    public void logisLogined(bool retcode)
    {
        #if UNITY_ANDROID
                Debug.Log("   是否登录In C# Client ret = " + retcode);
                GASDKManager.Instance.AndroidToat(" 是否登录 ： " + retcode);
        #endif

    }
    //#######################################################

}

```
