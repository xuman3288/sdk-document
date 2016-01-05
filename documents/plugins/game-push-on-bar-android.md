## 消息推送SDK接入文档 ##

推送SDK 

版本：V1.0.1

版本支持依赖base 4.0.0以上版本。

#### 通知栏推送SDK下载链接:
- [Download GiantPush V1.0.1 SDK](http://docs.mztgame.com/files/Android/plugin/push_on_barSDK_v1.0.1.zip)

---------------------------------------------------------------------------------------------------------------

### 1.接入环境搭建

导入资源工程

将push资源工程拷贝到工作空间，用eclipse导入工程，并在Properties->Android中选中此工程为library工程。


添加资源工程为工程项目的library,在Properties->Android中添加资源工程为项目工程的library。

### 2.配置心跳时间文件
将assets目录中的giant_push_message.cfg文件复制到接入项目的assets目录中，该文件内容如下：

~~~
#推送时间，秒为单位
giant.push_time=20
~~~

单位为秒，这里需要接入方按需求配置，为防止浪费网络资源和影响游戏体验，建议时间至少设置在1分钟以上,最高可12个小时。

### 3.配置AndroidMenifest.xml文件

注册service和Receiver

~~~xml
<!-- 游戏接入推送消息需配置以下内容       begin --> 
<service
    android:name="com.mztgame.push.ZTGaintPushService">
</service>
<receiver android:name="com.mztgame.push.ZTGaintPushReceiver">
    <intent-filter >
        <action android:name="android.intent.action.BOOT_COMPLETED"/>
        <action android:name="android.intent.action.USER_PRESENT"/> 
    </intent-filter>
</receiver>
<!-- 游戏接入推送消息需配置以上     end -->
~~~

在工程项目首次启动Activity中配置下信息

~~~xml	
<!-- 游戏接入推送消息需配置以下内容       begin -->
<intent-filter >
   <action android:name="android.intent.action.VIEW"/>     
   <category android:name="android.intent.category.DEFAULT"/>  
   <!-- 值说明  "PUSH."+#package# + ".ZTGaintPush"的形式 -->  
   <data android:scheme="PUSH.#package#.ZTGaintPush"/>
</intent-filter>
<!-- 游戏接入推送消息需配置以下内容       begin -->
~~~

> 注：配置<data>标签scheme的值必须以：PUSH.+包名+.ZTGaintPush命名，其中包名即为AndroidMenifest.xml中配置的packageName。若是接入母包中，则直接使用#package#，即：PUSH.#package#.ZTGaintPush；若直接接入游戏（非母包）则需要将#package#替换成游戏的包名，例如，游戏包名为：com.example,则为：PUSH.com.example.ZTGaintPush。

添加必须权限

~~~xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.VIBRATE" />
<uses-permission android:name ="android.permission.GET_TASKS" />
<!-- 开机启动push的service -->
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
~~~
    
在Application节点中增加属性

~~~xml
android:persistent="true"
~~~

详情请参考Demo工程中的AndroidManifest.xml文件。
### 4.启动推送服务接口

初始化完毕后调用该方法启动推送服务

~~~java
void startPushService(Context context);
~~~

在工程首次启动的Activity中的onCreate()生命周期方法中启动该service。

~~~java
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //游戏初始化工作
    .....
    //开启debug模式
    ZTGaintMsgPush.getInstance().setDebugMode(true);
    //开启service
    ZTGaintMsgPush.getInstance().startPushService(this);
｝
~~~

> 注：如需进行调试需要开启调试模式，即ZTGaintMsgPush.getInstance().setDebugMode(true)，默认情况下不开启调试模式.

> 建议startPushService()接口在依赖base包中的init接口初始化完成后调用。

### 5.上报用户信息接口
登录成功后调用接口（*使用前请联系游戏产品开启此功能*）

~~~java
void reportUserInfo(Context context,String userId);
~~~

使用例子：

~~~java
//设置用户的accid
String userid = IZTLibBase.getUserInfo().get(ZTConsts.User.ACCID);
ZTGaintMsgPush.getInstance().reportUserInfo(MainActivity.this, userid);
~~~

> 注：userId获取可以在登录成功后调用IZTLibBase.getUserInfo().get(ZTConsts.User.ACCID)获取；

### 6.调试模式接口
调试模式接口，默认情况下是false（即不开启调试模式），如需开启调试模式，请将该接口值设为true.	

~~~java
void setDebugMode(boolean flag)
~~~

