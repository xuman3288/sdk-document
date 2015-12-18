#游戏激活码插件

## IOS 客户端

### 配置
* deployment target 6.0以上
* ztactivegameres以蓝色资源文件夹方式添加进项目
* 链接SystemConfiguration.framework以及libZTActive.a库文件
* 添加ZTActive.h头文件

####初始化

~~~objectc
    [[ZTActive instance] initWithGameId:@"5012" isLandscape:YES userId:@"1629306" platformId:@"23" account:@"1234569999" block:^(NSDictionary *dictionary) {    
	    NSLog(@"%@", dictionary);
	    if([[ZTActive instance] isNeedActive]){
	    	//需要激活
	    }    
    }];
~~~

初始化信息dictionary格式:

* 初始化成功：`{"code":0,"info":"success"}`
* 初始化失败：`{"code":大于零的状态码,"info":"状态码说明"}`

#### 查询是否已经激活

> 注: 此接口必须在初始化完成后才可调用

~~~objectc
[[ZTActive instance] isNeedActive]
~~~

### 激活

~~~objectc
// 显⽰激活用户界⾯
[[ZTActive instance] showEntry:^(NSDictionary *ret) {
// 激活信息json
NSLog(@"%@", ret);
}];
~~~

激活信息dictionary格式:

* 激活成功：{"code":0,"info":"success"}
* 其他状态：{"code":大于零的状态码,"info":"状态码说明"}

------------------------------------------------------

## Android 客户端

...

