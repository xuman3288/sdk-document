##推广员打包命令文档

---------------------------------------------------------------------------

1. 打包前的准备:  
	首先，需要将母包命名为  `游戏ID.apk` 这样的形式 放到打包工具的 `/opt/source/` 目录下
	  
	然后， 需要检查properties文件中是否含有`config.promoterid=#promoter_id#`这条记录,并且将properties文件放在 `/opt/config/`目录下
	
	最后，需要将keystore文件放置到`/opt/keystore/` 目录下
	  
2. 打单个测试包所需要的脚本（假设上述准备动作完毕）

		命令原型： pakcagetools.py [apk] [from] [to] [properties] [keystore] [alias] [keypass] [storepass]
		
		

	`参数：`  
	【apk】 母包apk  
	【from】 起始推广员id  
	【to】终止推广员id  （倘若起始id = 终止 id  只生成一个推广员id的包）  
	【properties】 配置文件的名称  
	【keystore】 keystore文件的名称  
	【alias】 keystore的别名  
	【keypass】 keystore的条目密钥  
	【storepass】 keystore的仓库密钥
	
	案例
	
	生成一个推广员id为27的推广员包：
	
		pakcagetools.py 5001.apk 27 27 loltd.properties demo.keystore demo.keystore linjinrui linjinrui
		
		
	生成一些推广员id为 1 到 1000 的包：
	
		pakcagetools.py 5001.apk 1 1000 loltd.properties demo.keystore demo.keystore linjinrui linjinrui	
	