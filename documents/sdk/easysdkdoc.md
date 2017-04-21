巨人移动Android母包接入文档
================================

**SDK版本：** Ver 5.0.0   
**时间：** 2017/4/20
#接入流程
##一. EasySDK_Framework(Java)开发包
####资源包括 libs 目录下的 SDK 主架包 base.jar、acra.jar、ztgameframework.jar

####配置AndroidManifest.xml添加框架需要的权限
    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

####添加下面属性至项目工程AndroidManifest.xml文件的 `<application />` 标签中

	android:name="com.ztgame.ztgameframework.ZTApplication"

####配置下面组件至项目工程AndroidManifest.xml文件中

	<service android:name="com.ztgame.mobileappsdk.sdk.logserver.LogHTTPService"></service>

如果你的工程里存在 Application 需要继承ZTApplication 并且在 onCreate() 函数中添加 super.onCreate()
####复制 asserts 文件夹到你的游戏项目. ztsdk_config.properties 文件范例:

####注意

- 在 androidManifest.xml 中需要将所有的activity service等标签 android:name 字段写上对应的java包名的全称
- 在assets目录下添加 ztsdk_config.properties 文件确保文件中有以下内容:

  	 ` config.sdk.class=com.ztgame.ztgameframework.ZTgameFramework`


##二. 客户端接入代码
###2.1 *SDK初始化接口（必选接口）*

在主Activity的onCreate方法中对EasySDK进行初始化。

- newInstance(Activity ac)
<table border=”1″>
<tr>
<td>参数</td>
<td>参数说明</td>
</tr>
<tr>
<td>ac</td>
<td>Activity类的一个实例</td>
</tr>
</table>

- initZTGame(String gameId, String appName, boolean isLandScape, IZTListener listener)

<table border=”1″>
<tr>
<td>参数</td>
<td>参数说明</td>
</tr>
<tr>
<td>gameId</td>
<td>游戏ID</td>
</tr>
<tr>
<td>appName</td>
<td>游戏名称</td>
</tr>
<tr>
<td>isLandScape</td>
<td>游戏横竖屏（true  : 横版，false : 竖版）</td>
</tr>
<tr>
<td>listener</td>
<td>用于响应回调事件的监听接口</td>
</tr>
</table>

#####IZTListener 说明

	new IZTListener() {
        @Override
        public void onFinished(int what, int errcode, JSONObject json_obj){
		}
	}

##### 参数 `what` 说明
<table border=”1″>
<tr>
<td>出现值  </td>
<td>描述</td>
</tr>
<tr>
<td>ZTConsts.ZTGAME_INIT</td>
<td>是SDK初始化完成的通知，所有接口都需要在INIT成功完成之后才能开始调用</td>
</tr>
<tr>
<td>ZTConsts.ZTGAME_LOGIN</td>
<td>需要处理登录返回的json_obj参数，交给游戏服务器<a href="http://docs.mztgame.com/docs/sdk/server_guide#__2" >进行效验</a></td>
</tr>
<tr>
<td>ZTConsts.ZTGAME_PAY</td>
<td>只需要判断errcode为0成功还是-1失败，但是是否到账要以<a href="http://docs.mztgame.com/docs/sdk/server_guide#__7" >服务器通知为准</a></td>
</tr>
<tr>
<td>ZTConsts.ZTGAME_QUIT</td>
<td>是游戏进行销毁操作的地方，在第三方退出框点击确认后会收到此回调</td>
</tr>
<tr>
<td>ZTConsts.ZTGAME_LOGOUT</td>
<td>是游戏进行登出操作/切换账号操作的地方，需要返回到游戏登录界面等待用户再次登录</td>
</tr>

</table>



#####调用用例:

注意：只需调用一次  

    IZTLibBase.newInstance(MainActivity.this);
    IZTLibBase.getInstance().initZTGame("5012","Game Name", false, mListener);
	 //事件监听
    private IZTListener mListener = new IZTListener() {
        @Override
        public void onFinished(int what, int errcode, JSONObject json_obj) {

            switch (what) {
            case ZTConsts.ZTGAME_LOGIN:
                if (errcode == 0) {
                    //游戏完成事件, 设置角色信息
                    IZTLibBase.getInstance().loginOkZTGame("roleId", "roleName", "roleLevel", "zoneId", "zoneName");
                    try {
                        String openid = json_obj.getString("accid");
                        String sign = json_obj.getString("sign");
                        JSONObject entity = json_obj.getJSONObject("entity");
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else if（errcode == -2）{
                     //用户取消登录，关闭登录框
                }else {
                    //登录失败
                }
                break;
            case ZTConsts.ZTGAME_INIT:
                if (errcode == 0) {
                    //初始化完成
                } else {
                    //初始化失败
                }
                break;
            case ZTConsts.ZTGAME_PAY:
                if(errcode == 0){
                    //支付完成
                }else{
                    //支付失败
                }
                break;
            case ZTConsts.ZTGAME_QUIT:
                if(errcode == 0){
                    //游戏退出
                } else {
                }
                break;
            case ZTConsts.ZTGAME_LOGOUT:
              
                break;

            }
        }
    };
}

###2.2 *Activity生命周期相关方法（必选接口）*
	


    @Override
    protected void onDestroy() {
    	IZTLibBase.getInstance().destroyZTGame();
		IZTLibBase.delInstance();
    	super.onDestroy();
    }
    
    @Override
    protected void onPause() {
    	IZTLibBase.getInstance().onPauseZTGame();
    	super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
        IZTLibBase.getInstance().onStopZTGame();
    }
    @Override
    protected void onResume() {
    	IZTLibBase.getInstance().onResumeZTGame();
    	super.onResume();
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        IZTLibBase.getInstance().onConfigurationChangedZTGame(newConfig);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        IZTLibBase.getInstance().onNewIntentZTGame(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IZTLibBase.getInstance().onActivityResultZTGame(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        IZTLibBase.getInstance().onSaveInstanceState(outState);
    }

###2.3 *登录接入（必选接口）*
- loginZTGame(String zoneId, String zoneName, boolean isAutoLogin)
<table border=”1″>
<tr>
<td>参数</td>
<td>参数说明</td>
</tr>
<tr>
<td>zoneId</td>
<td>游戏区ID（Int, 大于0）</td>
</tr>
<tr>
<td>zoneName</td>
<td>游戏区名称</td>
</tr>
<tr>
<td>isAutoLogin</td>
<td>是否自动登录</td>
</tr>

</table>

####IZTListener回调接口类型(what)为:

<table border=”1″>
<tr>
<td>类型 </td>
<td>描述</td>
</tr>
<tr>
<td>ZTConsts.ZTGAME_LOGIN</td>
<td>需要处理登录返回的json_obj参数，交给游戏服务器<a href="http://docs.mztgame.com/docs/sdk/server_guide#__2" >进行效验</a></td>
</tr>
</table>

####IZTListener回调接口状态吗(errcode)为:
<table border=”1″>
<tr>
<td>状态码 </td>
<td>描述</td>
</tr>
<tr>
<td>0</td>
<td>登录成功</td>
</tr>
<tr>
<td>其它</td>
<td>登录失败</td>
</tr>
</table>


####IZTListener回调接口数据(json_obj)为:
ZTConsts.ZTGAME_LOGIN,errcode为0成功时，json_obj出现值:

    {
    "entity": {
    "openid":"1-123456",
    "account":"XXXXXXXX"
    ......
    },
    "accid":"1-123456",
    "token":"bbe7e46de2c7d3ace036cea155b23978",
    "sign":"xxxxxx"
    }

<table border=”1″>
<tr>
<td>参数</td>
<td>参数说明</td>
</tr>
<tr>
<td>entity</td>
<td>服务端验签需要参数</td>
</tr>
<tr>
<td>accid</td>
<td>账号ID</td>
</tr>
<tr>
<td>token</td>
<td>服务端验签需要参数</td>
</tr>
<tr>
<td>sign</td>
<td>服务端验签需要参数</td>
</tr>

</table>
  
#####调用用例:

     YourActivity.runOnUiThread(new Runnable() {
            public void run() {            
                     IZTLibBase.getInstance().loginZTGame("1", "zoneName"", true);
            }
    });
	 //事件监听
    private IZTListener mListener = new IZTListener() {
        @Override
        public void onFinished(int what, int errcode, JSONObject json_obj) {

            switch (what) {
            case ZTConsts.ZTGAME_LOGIN:
                if (errcode == 0) {
                    //游戏完成事件, 设置角色信息
                    IZTLibBase.getInstance().loginOkZTGame("roleId", "roleName", "roleLevel", "zoneId", "zoneName");
                    try {
                        String openid = json_obj.getString("accid");
                        String sign = json_obj.getString("sign");
                        JSONObject entity = json_obj.getJSONObject("entity");
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else {
                    //登录失败
                }
                break;
			}}}

###2.4 *支付接入（必选接口）*
- payZTGame(ZTPayInfo payInfo)

依赖登录接口。
####ZTPayInfo：支付订单信息。有以下属性：
<table border=”1″>
<tr>
<td>Method</td>
<td>Required / Optional / Deprecated</td>
<td>Description</td>
</tr>
<tr>
<td>setAmount</td>
<td>必填</td>
<td>设置商品金额(RMB). 单位(分)</td>
</tr>

<tr>
<td>setExtra</td>
<td>可选设置</td>
<td>设置游戏订单扩展信息(游戏订单号等等)</td>
</tr>

<tr>
<td>setMoneyName</td>
<td>必填</td>
<td>设置货币单位名称</td>
</tr>

<tr>
<td>setProductName</td>
<td>必填</td>
<td>设置商品名称</td>
</tr>

<tr>
<td>setProductId</td>
<td>必填</td>
<td>设置商品ID</td>
</tr>
<tr>
<td>setZoneId</td>
<td>必填</td>
<td>设置区服</td>
</tr>


<tr>
<td>setExchangeRatio</td>
<td>可选设置</td>
<td>设置价格比率</td>
</tr>
</table>



####IZTListener回调接口类型(what)为:

<table border=”1″>
<tr>
<td>类型 </td>
<td>描述</td>
</tr>
<tr>
<td>ZTConsts.ZTGAME_PAY</td>
<td>只需要判断errcode为0成功还是-1失败，但是是否到账要以<a href="http://docs.mztgame.com/docs/sdk/server_guide#__7" >服务器通知为准</a></td>
</tr>
</table>
####IZTListener回调接口状态吗(errcode)为:
<table border=”1″>
<tr>
<td>状态码 </td>
<td>描述</td>
</tr>
<tr>
<td>0</td>
<td>支付成功</td>
</tr>
<tr>
<td>其它</td>
<td>支付失败</td>
</tr>
</table>

调用用例:

	
    ZTPayInfo payInfo = new ZTPayInfo();
    payInfo.setAmount(100);  //设置金额, 单位(分) *必传参数
    payInfo.setProductName("test item"); //设置商品名称 *必传参数
    payInfo.setProductId("1001"); // 设置商品ID *必传参数
    payInfo.setExtra("1"); //设置游戏订单扩展信息
	payInfo.setZoneId("1");//设置区服
    IZTLibBase.getInstance().payZTGame(payInfo);
	     //事件监听
	private IZTListener mListener = new IZTListener() {
    @Override
    public void onFinished(int what, int errcode, JSONObject json_obj) {

        switch (what) {
        case ZTConsts.ZTGAME_PAY:
            if (errcode == 0) {
                //支付成功
            }else {
				//支付失败
            }
            break;
        }}}


###2.5 *统计接口（必选接口）*
#####登录完成数据统
loginOkZTGame(String roleId,String roleName,String roleLevel,String zoneId,String zoneName,Map<String, Object> map)
#####创建角色数据统计接口接口
createRoleZTGame(String roleId,String roleName,String roleLevel,String zoneId,String zoneName,Map<String, Object> map)
#####角色等级升级信息接口
roleLevelUpZTGame(String roleId,String roleName,String zoneId,String zoneName,int level,Map<String, Object> map)

<table border=”1″>
<tr>
<td>参数</td>
<td>参数说明</td>
</tr>
<tr>
<td>roleId</td>
<td>角色ID</td>
</tr>
<tr>
<td>roleName</td>
<td>角色名称</td>
</tr>
<tr>
<td>roleLevel</td>
<td>角色等级</td>
</tr>
<tr>
<td>zoneId</td>
<td>游戏区ID</td>
</tr>
<tr>
<td>zoneName</td>
<td>游戏区名称</td>
</tr>
<tr>
<td>level</td>
<td>角色等级</td>
</tr>

<tr>
<td>map</td>
<td>扩展参数</td>
</tr>
</table>

注意： 角色创建时间(单位：秒)，获取服务器存储的角色创建时间，不可用本地手机时间，同一角色创建时间不可变，长度10(UC渠道)
	
	map.put("login_ok_create_roletime","角色创建时间");

###2.6 *退出界面（必选接口）*
- 是否有第三方渠道的退出确认弹出框

	isHasQuitDialog（）

	如果此函数返回true，请游戏不要弹出游戏自身的退出确认弹出框而是直接调用quitZTGame来弹出第三方的退出弹出框，在ZTGame_Quit回调内处理游戏的退出操作（销毁代码，而不是再次弹出退出确认对话框。返回false则按照游戏自己的退出流程处理即可。)
- 弹出第三方退出弹出确认框接口

    quitZTGame()

#####调用实例
	
	 //玩家点击退出
    if(IZTLibBase.getInstance().isHasQuitDialog()){ //渠道是否有退出框确认
    	IZTLibBase.getInstance().quitZTGame();//
    }else{
    	//游戏自己的退出框代码
    }

		 //事件监听
    private IZTListener mListener = new IZTListener() {
        @Override
        public void onFinished(int what, int errcode, JSONObject json_obj) {

            switch (what) {
            case ZTConsts.ZTGAME_QUIT:
                if (errcode == 0) {
    				//游戏退出
                }else {
              
                }
                break;
			}}}

###2.7 *用户中心*
- 是否需要用户中心按钮接口

	isHasCenterZTGame（）

	以上为某些渠道判断是否存在用户中心按钮倘若返回false不作处理，倘若返回true需要显示用户中心按钮，点击此按钮后调用enterCenterZTGame()
- 用户中心操作, 调用此接口弹去渠道方的用户中心界面

    enterCenterZTGame()
#####调用实例
	
	 //玩家点击退出
    if(IZTLibBase.getInstance().isHasCenterZTGame()){ //渠道是否有退出框确认
    	IZTLibBase.getInstance().enterCenterZTGame();//
    }

###2.8 *切换账号*
- 是否需要切换账号按钮接口

	isHasSwitchAccountZTGame（）


    以上接口返回true则游戏需要添加一个切换账号按钮以方便用户切换账号.点击此按钮后调用switchAccountZTGame()

- 切换账号

    switchAccountZTGame()
	以上为切换账号接口功能，调用此接口执行切换账号操作，调用此接口后，会发送ZTGAME_LOGOUT消息。
#####调用实例
	
	 //玩家点击退出
    if(IZTLibBase.getInstance().isHasSwitchAccountZTGame()){ //渠道是否有退出框确认
    	IZTLibBase.getInstance().switchAccountZTGame();//
    }

		 //事件监听
    private IZTListener mListener = new IZTListener() {
        @Override
        public void onFinished(int what, int errcode, JSONObject json_obj) {

            switch (what) {
            case ZTConsts.ZTGAME_LOGOUT:
        	//切换账号处理
       
			}}}

####注意事项
#### 1.如果游戏是u3d框架，以上sdk接口必须放在runOnUiThread()方法中调用
