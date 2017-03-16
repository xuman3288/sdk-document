## Unity3D语音文档

##### 1.下载SDK
 1.[下载Unity3D SDK包 版本：1.0.0](http://cdn.mztgame.ztgame.com.cn/gavoice_rtchat/resource_zip/GARTCSDK_Voice_1.0.0.zip)

2.双击GARTCSDK_1.0.0_20170208.unitypackage导入
或则直接复制 Unity3D/ 下内容到 Assets/下


##### 2.系统配置  
   1.在iOS ／android, Player setting 设置宏（RTCHAT_ENABLE）开关，否则无法调用语音功能。
   ![](http://cdn.mztgame.ztgame.com.cn/gavoice_rtchat/resource_img/gavoice_hong.png)


##### 3.基本API

###### 3.1初始化接口


  *1.接口说明  *

  初始化SDK,在其他接口之前调用。

 *2.函数原型 *

   ```
   //初始化sdk(appid ,appkey,userid 可以联系我们，serverurl,xif)
   public void InitGASDK (string appId, string appKey, string  userId, string serverUrl, string xfid)

   ```
| 参数        | 类型           | 意义   |
| ------------- |:-------------:| -----:|
| appId      | string | 应用标识,可以联系我们申请 |
| appKey     | string      |   应用标识,可以联系我们申请|
| userId     | string      |   用户标识,可以联系我们申请|
| serverUrl | string     |  录音上传地址 |
| xfid | string     |    录音ID(Android 和iOS 会不一样) |

*3.示例代码*
```
GAVoiceManager.Instance.InitGASDK (appid, appKey, userId,uploadserver,"58536195");
```

###### 3.2加入房间接口


  *1.接口说明  *

   实时语音，加入房间

 *2.函数原型 *

   ```
   //进入房间（roomid:房间id，cb加入房间回调）
 public void JoinRoom (string roomId,GAJoinRoomCallback cb)；
 //回调
 public delegate void GAJoinRoomCallback (int retCode);

   ```
| 参数        | 类型           | 意义   |
| ------------- |:-------------:| -----:|
| roomId      | string | 房间号 |
| cb     | GAJoinRoomCallback      |   加入房间回调|
| retCode      | int |加入房间状态：1成功，0失败 |



*3.示例代码*
```
GAVoiceManager.Instance.JoinRoom (roomidValue,ResultJoinRoom);

private void ResultJoinRoom(int code ){
		if(code ==1){
			AppLogPrint ("加入房间成功"");
		}else if(code ==0){
			AppLogPrint ("加入房间失败);
		}
	}
```
###### 3.3退出房间接口


  *1.接口说明  *

   实时语音，退出房间

 *2.函数原型 *

   ```
  //退出房间（roomid:房间id，cb加入房间回调）
 public void QuitRoom (GAQuitRoomCallback cb)；
 //回调
 public delegate void GAQuitRoomCallback (int retCode);

   ```

| 参数        | 类型           | 意义   |
| ------------- |:-------------:| -----:|
| cb     | GAQuitRoomCallback      |   退出房间回调|
| retCode      | int |退出房间状态：1成功，0失败 |



*3.示例代码*
```
GAVoiceManager.Instance.QuitRoom (QuitroomResult);

private void QuitroomResult(int ret){
		if(ret ==1){
			AppLogPrint ("退出房间成功");
		}else if(ret ==0){
			AppLogPrint ("退出房间失败");
		}  

	}
```

###### 3.4关闭扬声器


  *1.接口说明  *

   实时语音，关闭扬声器

 *2.函数原型 *

   ```
   //关闭扬声器外放功能
   public void CloseLoudSpeaker ()

   ```

*3.示例代码*
```
GAVoiceManager.Instance.CloseLoudSpeaker ();

```
###### 3.5打开扬声器


  *1.接口说明  *

   实时语音，打开扬声器

 *2.函数原型 *

   ```
   //打开扬声器外放功能
   public void OpenLoudSpeaker ()

   ```

*3.示例代码*
```
GAVoiceManager.Instance.OpenLoudSpeaker ();

```
###### 3.6打开麦克风


  *1.接口说明  *

   实时语音，打开麦克风

 *2.函数原型 *

   ```
   //打开麦克风
   public void OpenMic ()

   ```

*3.示例代码*
```
GAVoiceManager.Instance.OpenMic ();

```
###### 3.6关闭麦克风


  *1.接口说明  *

   实时语音，关闭麦克风

 *2.函数原型 *

   ```
   //关闭麦克风
   public void CloseMIc ()

   ```

*3.示例代码*
```
GAVoiceManager.Instance.CloseMIc ();

```
###### 3.7 设置语音外放音量调节


  *1.接口说明  *

   实时语音，设置语音外放音量调节

 *2.函数原型 *

   ```
   //设置语音外放音量调节（取值范围为0-10的整数，最大值请根据回音情况做调节）
   public void SpeakerVolum (float volume)；

   ```
   | 参数        | 类型           | 意义   |
   | ------------- |:-------------:| -----:|
   | volume     | float      |   音量调节，范围（0，10）|


*3.示例代码*
```
GAVoiceManager.Instance.SpeakerVolum (4.0);

```
---
###### 3.8 开始录制语音消息


  *1.接口说明  *

   语音消息，开始录制语音消息

 *2.函数原型 *

   ```
   //开始录制语音消息（true 需要， false 不需要）
   public void StartRecord (bool needConvertWord)；

   ```
   | 参数        | 类型           | 意义   |
   | ------------- |:-------------:| -----:|
   | needConvertWord     | bool      |   是否需要翻译为文字 true 需要， false 不需要|


*3.示例代码*
```
GAVoiceManager.Instance.StartRecord (true);

```
###### 3.9 停止录制语音消息


  *1.接口说明  *

   语音消息，停止录制语音消息

 *2.函数原型 *

   ```
   //停止录制语音消息（录制回调）
  public void StopRecord (GAVoiceRecordCallback  callback)
  //回调
  public delegate void GAVoiceRecordCallback (int retCode, string url, string text,float duration);
   ```
   | 参数        | 类型           | 意义   |
   | ------------- |:-------------:| -----:|
   | callback     | GAVoiceRecordCallback      |   是否需要翻译为文字 true 需要， false 不需要|
   | retCode     | int      |   停止录制状态 1 成功， 0 失败|
   | url     | string      |   语音上传地址|
   | text     | string      |   翻译的文字|
   | duration     | float      |   语音时间长度|

*3.示例代码*
```
GAVoiceManager.Instance.StopRecord (VoiceRecordResult);
//回调数据
private void VoiceRecordResult(int retCode,string url,string text,float duration){

		if (retCode == 1) {
			ValueChangeCheck(" 停止录制回调: 成功，url = " + url + ",文本="+text+ ",时间：" + duration + "秒");
		} else {
			ValueChangeCheck(" 停止录制回调: 失败，url = " + url + ",文本="+text+ ",时间：" + duration + "秒");
		}

	}

```
###### 3.10 播放录制语音消息


  *1.接口说明  *

   语音消息，播放录制语音消息

 *2.函数原型 *

   ```
   //播放录制语音消息
  public void StartPlayLocal (string voiceUrl, GAStartPlayFinishCallback callback)；
  //回调
  public delegate void GAStartPlayFinishCallback (int retCode);
   ```
   | 参数        | 类型           | 意义   |
   | ------------- |:-------------:| -----:|
   | callback     | GAStartPlayFinishCallback      |   播放完成回调|
   | retCode     | int      |   停止录制状态 1 成功， 0 失败|
   | voiceUrl     | string      |   语音上传地址 （停止录音接口 取得）|


*3.示例代码*
```
GAVoiceManager.Instance.StartPlayLocal (startplayFinishResult);
//回调数据
public void startplayFinishResult(int retcode){
		if (retcode == 1) {
			ValueChangeCheck(" 播放: 成功");
		} else {
			ValueChangeCheck(" 播放: 失败");
		}
	}


```
###### 3.11 取消录制语音消息


  *1.接口说明  *

   语音消息，取消录制语音消息

 *2.函数原型 *

   ```
   //取消录制语音消息
  public void CancelVoiceRecord ()；
  ```

*3.示例代码*
```
GAVoiceManager.Instance.CancelVoiceRecord ();

```
###### 3.11 停止播放语音消息


  *1.接口说明  *

   语音消息，停止播放语音消息

 *3.函数原型 *

   ```
   //停止播放语音消息
  public void StopPlayLocal ()；
  ```

*3.示例代码*
```
GAVoiceManager.Instance.StopPlayLocal ();

```
###### 3.12 设置用户消息


  *1.接口说明  *

   语音消息，设置用户消息

 *2.函数原型 *

   ```
   //停止播放语音消息
  public void SetUserInfo (string username, string userkey)；
  ```
  | 参数        | 类型           | 意义   |
  | ------------- |:-------------:| -----:|
  | username     | string      |   用户名|
  | userkey     | string      |   可为空|


*3.示例代码*
```
GAVoiceManager.Instance.SetUserInfo ("username","");

```
