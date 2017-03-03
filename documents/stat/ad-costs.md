媒体合作广告计费调用接口说明
==================
 
## 接口说明
 本广告计费对接接口使用HTTP协议，GET方法。
 
* 点击接口地址
 `Http://ad.stat.mztgame.com/ad/costs`
 
 * 接口参数
 
 |参数名 | 说明 | 必填 |
 |------|------|------|
 |eventdate|事件时间, 格式 date|2016/5/1|
 |mwid|媒体素材ID|2-123-2412423512|
 |mwinfo|素材信息||
 |zoneid|展示|1312|
 |svrid|点击|23141|
 |adid|广告ID|123115|
 |cost|广告费||
 |other|备注||

 
 * 对接地址范例
 `http://ad.stat.mztgame.com/ad/costs?eventdate=2016/5/1&mwid=2-123-2412423512&mwinfo=&zoneid=1312&svrid=23141&adid=123115&cost=1&other=1`
