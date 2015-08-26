###运营商SDK对接文档

------------------------------------------------------
运营商对接需要提供运营商提供的计费点信息，游戏方需要维护改计费点信息。

所有的计费点信息需要通过

	ZTPayInfo payInfo = new ZTPayInfo();
	payInfo.setProductId("30000881602001");
	
###移动MM接入	
移动MM可以使用自动打包工具支持。

###移动基地接入

移动基地依赖lib工程之后  需要将lib工程的res下的values/g_strings.xml 写入Activity的入口地址

	<string name="g_class_name">com.mztgame.ydjdtest.MainActivity</string>
	

###沃商店接入
需要依赖沃商店libs工程 需要修改androidmanifest.xml中的 字段：

	<!-- 请务必将action name 按照应用包名+".unicom.wostore.unipay.securityserviceframework"进行组装 -->	
	<action android:name="com.example.wostoretest.unicom.wostore.unipay.securityserviceframework" />
	
	
	
Application的name字段必须填写`com.mztgame.ZTGameWoStoreApplication` 倘若游戏存在自己的Application需要继承该类。

	<application
        android:name="com.mztgame.ZTGameWoStoreApplication"
    >