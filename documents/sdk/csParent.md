```csharp

using UnityEngine;
using System.Collections;

public class TestGaParentPackage : MonoBehaviour {


    void Start()
    {

      GASDKManager.Instance.InitGASDK("5151","testGameName",false);
    		GASDKManager.Instance.EnableDebugGA ();
    }


    public void testLogin(){

	     GASDKManager.Instance.Login (true,LoginResult);
	}

    public void testPay()
    {
      GASDKManager.Instance.Pay (new GAPayInfo("zonId","productId","productName",100,1,1,""),
  			LogPayResult);

	}

    public void testSwitchUser()
    {

		GASDKManager.Instance.SwitchUser ();

    }
    public void testUserCenter()
    {
		GASDKManager.Instance.UserCenter ();
	}

    public void testHasUserCenter()
    {
      GASDKManager.Instance.HasUserCenter(logHasUserCenter);

    }

    public void testHasSwitchUser()
  	{

  		GASDKManager.Instance.HasSwitchUser(logHasSwitchUserCenter);

  	}
  	public void testHasQuitDialog()
  	{

  		GASDKManager.Instance.HasQuitDialog(logHasQuitDialog);

  	}
  	public void testQuitDialog()
  	{

  		GASDKManager.Instance.QuitGameDialog(logQuitDialog);

  	}

  	public void testGetPlatformId()
  	{

  		GASDKManager.Instance.GetPlatformId(logGetPlatFormId);
  	}

  	public void testSetZoneId()
  	{

  		GASDKManager.Instance.SetZoneId("11");
  	}
  	public void testSetActivity()
  	{

  		GASDKManager.Instance.SetActivity();
  	}


  	public void testIsLogined()
  	{

  		GASDKManager.Instance.IsLogined(logisLogined);

  	}
  	public void testloginOkZTGame()
  	{

  		GASDKManager.Instance.LoginOkZTGame("1","角色名","角色等级","11","区名");

  	}
  	public void testcreateRoleZTGame()
  	{

  		GASDKManager.Instance.CreateRoleZTGame("1", "角色名", "角色等级", "11", "区名");

  	}
  	public void testroleLevelUpZTGame()
  	{

  		GASDKManager.Instance.RoleLevelUpZTGame("1", "角色名",  "11", "区名",11);
  	}



      //####################### 返回结果 ############################


  	public void LoginResult(int ret,string json){

  		Debug.Log( " 登录成功  In C# Client ret = "+ret +
              ",json = " +json
              );
  	}

      public void LogPayResult(int ret)
      {
          Debug.Log("  支付  In C# Client ret = " + ret  );
      }

  	public void logHasUserCenter(bool ishasUserCenter)
  	{
  		Debug.Log("  用户中心  In C# Client ret = " + ishasUserCenter);
  		GASDKManager.Instance.ToastAnd("用户中心是否需要 ： "+ishasUserCenter);

  	}
  	public void logHasSwitchUserCenter(bool ishasUserCenter)
  	{
  		Debug.Log("  切换账号 In C# Client ret = " + ishasUserCenter);
  		GASDKManager.Instance.ToastAnd("切换账号是否需要 ： " + ishasUserCenter);

  	}
  	public void logHasQuitDialog(bool ishasUserCenter)
  	{
  		Debug.Log("  第三方退出弹出框 In C# Client ret = " + ishasUserCenter);
  		GASDKManager.Instance.ToastAnd(" 第三方退出弹出框 是否需要 ： " + ishasUserCenter);

  	}
  	public void logQuitDialog(int retcode)
  	{
  		Debug.Log("  第三方弹出框  确认按钮In C# Client ret = " + retcode);
  		GASDKManager.Instance.ToastAnd("第三方弹出框  确认按钮 ： " + retcode);

  	}
  	public void logGetPlatFormId(int  retcode)
  	{
  		Debug.Log("  渠道ID In C# Client ret = " + retcode);
  		GASDKManager.Instance.ToastAnd(" 渠道ID ： " + retcode);

  	}

  	public void logisLogined(bool retcode)
  	{
  		GASDKManager.Instance.ToastAnd(" 是否登录 ： " + retcode);
  	}
    //#######################################################

}

```
