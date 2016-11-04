```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="你的包名"
    android:versionCode="1"
    android:versionName="1.0" >

    <uses-sdk
        android:minSdkVersion="8"
        android:targetSdkVersion="24" />

        <!-- GASDK  需要的权限 start -->
		<uses-permission android:name="android.permission.WRITE_SETTINGS" />
		<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
		<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
		<uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
		<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
		<uses-permission android:name="android.permission.INTERNET" />
		<uses-permission android:name="android.permission.SEND_SMS" />
		<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
		<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
		<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
		<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
		<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

		 <!-- GASDK  需要的权限 end -->

	 <!--  如果工程不存在Application   必须这样写android:name="com.ztgame.ztgameframework.ZTApplication"   -->

	 <!--  如果工程 存在Application   必须继承ZTApplication 并且在 onCreate() 函数中添加 super.onCreate(),

	     android:name="游戏包名.游戏的application"  

		 -->	 	

	 <!--  android:launchMode 设置成 standard 或者 singleTop	 -->
     <application
	    android:name="参考以上写法"
        android:allowBackup="true"
        android:icon="@drawable/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/AppTheme" >
    </application>
    <!--  
	 所有的activity service等标签 android:name 字段写上对应的java包名的全称(全路径)
	 activity 中包含 android:configChanges="orientation|keyboardHidden|screenSize"
	-->
	<!-- 例如 <activity android:name="你的包名.xxxxxActivity"
            android:configChanges="orientation|keyboardHidden|screenSize"/>   
		<-->
</manifest>

```
