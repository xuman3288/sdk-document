游戏 GM 工具接入指南
========================================== 

## 流程说明

![GM工具接口流程说明](http://i.imgur.com/n2e4FjS.png)

用户操作Web增删改查, 调用GM 调用工具服务 将数据用JSONRPC 方式转发游戏接口


## 协议说明

**应用层协议:** HTTP , JSON-RPC 1.0

各语言服务模块参考:

| 语言  |           链接                             |
|-------|--------------------------------------------|
| PHP    | https://github.com/zendframework/zend-json |
| C++    | https://github.com/cinemast/libjson-rpc-cpp |
| JAVA    | https://github.com/briandilley/jsonrpc4j |
| Python    | https://github.com/orokusaki/django-jsonrpc-2-0 |

更多参考Wiki:
https://en.wikipedia.org/wiki/JSON-RPC#Implementations


## 接口说明

### SelectInterface

~~~javascript
/**
 * 查询接口说明
 * @type {{select: Function, fetchCount: Function, meta: Function}}
 */
var selectInterface = {
    /**
     * 查询数据
     *
     * @param {object[]} where            Where 条件
     * @param {object}   sort             排序
     * @param {number}   offset           起始坐标
     * @param {number}   itemCountPerPage 显示条数,0为不限制条数
     * @return {object}  响应数据格式, 请看下面范例
     *
     *
     * 请求参数 where 范例
     * <code>
     * [
     *    {
     *       "field":"id",
     *       "operator":"eq",
     *       "value":"1"
     *    },
     *    {
     *       "field":"name",
     *       "operator":"eq",
     *       "value":"test2"
     *    },
     *    //...
     * ]
     * </code>
     *
     *
     * 请求参数 order 范例
     * <code>
     * {
     *     "id":"desc",
     *     "name":"asc",
     *     //...
     * }
     * </code>
     *
     *
     * 响应数据范例
     * <code>
     * {
     *    "rows" : [
     *        {"id":1, "name":"test1"},
     *        {"id":2, "name":"test2"},
     *        //...
     *    ]
     * }
     * </code>
     */
    select: function (where, sort, offset, itemCountPerPage) {},

    /**
     * 查询总条数
     *
     * @param {object} where
     * @return {number}
     */
    fetchCount: function (where) {},

    /**
     * 响应 select 字段信息
     * @return {object}
     *
     * 响应结构说明
     * <code>
     * {
     *    
     *     //字段       类型               允许编辑该字段        允许为null        主键
     *     "id"     : {"type" : "number", "editable": false,  "nullable" : false, "primary" : true},
     *     "name"   : {                                       "nullable" : false},
     *     "age"    : {"type" : "number", "editable": true,   "nullable" : true},
     *     "date"   : {"type" : "date",   "editable": false,  "nullable" : false},
     *     //...
     * 
     * }
     * </code>
     *
     * fieldName.primary  类型说明:
     * 可设的值: true/false
     * 默认为 false
     *
     * fieldName.type  类型说明:
     * 可设的值: ["string", "number", "date", "boolean"]
     * 默认为 "string"
     *
     * fieldName.editable  允许编辑该字段说明:
     * 当接入 UpdateInterface 设置字段是否允许编辑
     * 默认为 true
     *
     * fieldName.nullable  允许为null 说明:
     * 当接入 UpdateInterface, InsertInterface 设置字段是否允许为空
     * 默认为 false
     */
    meta: function () {}
};

~~~


### UpdateInterface 
~~~javascript

/**
 * 更新接口说明
 * @type {{update: Function}}
 */
var updateInterface = {
    /**
     * 更新接口
     *
     * @param {object} data  更新后的新数据, 包含主键, 可根据主键进行更新
     * @param {object} where  根据 meta 分析主键，传递where更新时主键条件。
     * @return array
     *
     * 请求参数 data 范例
     * <code>
     * {
     *     "name":"test",
     *     "age":"1",
     *     //...
     * }
     * </code>
     *
     * 请求参数 where 范例
     * <code>
     * {
     *     "id":"1"
     * }
     * </code>
     *
     *
     * 响应数据范例
     * <code>
     * {
     *    "code" : 0,             //[Must] 0: 成功; 大于0表示失败
     *    "msg" : "成功或失败消息", //
     *    "row" : {               //[Must]
     *        "id":"1",
     *        "name":"test",
     *        "age":"1",
     *        //...
     *    }
     * }
     * </code>
     */
    update : function (data, where) {}
};

~~~


### InsertInterface 

~~~javascript

/**
 * 插入接口说明
 * @type {{insert: Function}}
 */
var insertInterface = {
    /**
     * 更新接口
     *
     * @param {object} data  新增数据
     * @return {object}
     *
     *
     * 请求参数 data 范例
     * <code>
     * {
     *     "name":"test2",
     *     "age":"1",
     *     //...
     * }
     * </code>
     *
     *
     * 响应数据范例
     * <code>
     * {
     *    "code" : 0,             //[Must] 0: 成功; 大于0表示失败
     *    "msg" : "成功或失败消息", //
     *    "row" : {               //[Must]
     *        "id":"2",
     *        "name":"test2",
     *        "age":"1",
     *        //...
     *    }
     * }
     * </code>
     */
    insert : function (data) {}
};
~~~

### DeleteInterface 

~~~javascript
/**
 * 删除接口说明
 * @type {{delete: Function}}
 */
var deleteInterface = {

    /**
     *
     * @param {object} row 要删除的行数据
     * @return {object}
     *
     * 响应数据范例
     * <code>
     * {
     *    "code" : 0,             //[Must] 0: 成功; 大于0表示失败
     *    "msg" : "成功或失败消息", //
     * }
     * </code>
     */
    delete : function (row) {}
};

~~~

### CallableInterface

~~~javascript
/**
 * 其它调用接口说明
 * @type {{CALL_SUCCESS: number, CALL_FAILURE: number}}
 */
var callableInterface = {
    CALL_SUCCESS : 0,
    CALL_FAILURE : 1,

    /**
     * 其它方法调用范例
     *
     * @param {object} row 要删除的行数据
     *
     * 任何方法响应结果说明
     * <code>
     * {
     *    "code" : 0,             //[Must] 0: 成功; 大于0表示失败
     *    "msg" : "成功或失败消息", //
     * }
     * </code>
     */
    example: function (row) {}
};
~~~

## 数据流传输范例

### 查询范例

**获取查询总数(fetchCount)**

~~~
POST /gm-demo/rpc/character HTTP/1.1
Host: gm-demo-server
Connection: close
Accept-Encoding: gzip, deflate
Content-Type: application/json
Content-Length: 46
Accept: application/json

{"method":"fetchCount","id":"1","params":[[]]}
~~~

**响应:**

~~~
HTTP/1.1 200 OK
Content-Length: 24
Connection: close
Content-Type: application/json

{"result":1000,"id":"1"}
~~~

<hr>

**查询数据(select)**

~~~javascript
POST /gm-demo/rpc/character HTTP/1.1
Host: gm.mztgame.com
Connection: close
Content-Type: application/json
Content-Length: 150
Accept: application/json

{
    "method":"select",
    "id":"2",   //此ID为请求ID，与数据内容无关，响应时原值返回
    "params": [
        [
            {"field":"玩家账号","operator":"eq","value":"test1"},
            {"field":"服务器","operator":"eq","value":"1"}
        ],
        {"玩家账号":"asc"},
        0,
        15
    ]
}
~~~

**响应**

~~~javascript
HTTP/1.1 200 OK
Content-Length: 2101
Connection: close
Content-Type: application/json

{
    "result": {
        "meta": {
            "ID": {
                "primary": true,
                "type": "number",
                "editable": false,
                "nullable": true
            },
            "玩家账号": {"type": "string"},
            "服务器名": {"type": "string"},
            "注册平台": {"type": "string"},
            "角色名": {"type": "string"},
            "公会名称": {"type": "string"},
            "VIP等级": {"type": "string"},
            "玩家等级": {"type": "string"},
            "充值货币": {"type": "string"},
            "注册时间": {"type": "string"},
            "注册IP": {"type": "string"},
            "注册设备码": {"type": "string"},
            "体力": {"type": "string"},
            "精力": {"type": "string"}
        },
        "rows": [
            [ 1, "test1", "1区", 22, "Test", 1, "1", "10", "10", "2015-01-01 00:00:00", "127.0.0.1", "123", "5", "5"],
            [ 2, "test2", "1区", 21, "Test", 1, "1", "10", "10", "2015-01-01 00:00:00", "127.0.0.1", "123", "5", "5"],
            [ 3, "test3", "1区", 20, "Test", 1, "1", "10", "10", "2015-01-01 00:00:00", "127.0.0.1", "123", "5", "5"],
            [ 4, "test4", "1区", 20, "Test", 1, "1", "10", "10", "2015-01-01 00:00:00", "127.0.0.1", "123", "5", "5"],
            [ 5, "test5", "1区", 22, "Test", 1, "1", "10", "10", "2015-01-01 00:00:00", "127.0.0.1", "123", "5", "5"],
            [ 6, "test6", "1区", 21, "Test", 1, "1", "10", "10", "2015-01-01 00:00:00", "127.0.0.1", "123", "5", "5"],
            [ 7, "test7", "1区", 1, "Test", 1, "1", "10", "10", "2015-01-01 00:00:00", "127.0.0.1", "123", "5", "5"],
            [ 8, "test8", "1区", 22, "Test", 1, "1", "10", "10", "2015-01-01 00:00:00", "127.0.0.1", "123", "5", "5"],
            [ 9, "test9", "1区", 21, "Test", 1, "1", "10", "10", "2015-01-01 00:00:00", "127.0.0.1", "123", "5", "5"],
            [ 10, "test10", "1区", 22, "Test", 1, "1", "10", "10", "2015-01-01 00:00:00", "127.0.0.1", "123", "5", "5"],
            [ 11, "test11", "1区", 22, "Test", 1, "1", "10", "10", "2015-01-01 00:00:00", "127.0.0.1", "123", "5", "5"],
            [ 12, "test12", "1区", 21, "Test", 1, "1", "10", "10", "2015-01-01 00:00:00", "127.0.0.1", "123", "5", "5"],
            [ 13, "test13", "1区", 20, "Test", 1, "1", "10", "10", "2015-01-01 00:00:00", "127.0.0.1", "123", "5", "5"],
            [ 14, "test14", "1区", 1, "Test", 1, "1", "10", "10", "2015-01-01 00:00:00", "127.0.0.1", "123", "5", "5"],
            [ 15, "test15", "1区", 20, "Test", 1, "1", "10", "10", "2015-01-01 00:00:00", "127.0.0.1", "123", "5", "5"]
        ]
    },
    "id": "2"  //JSON-RPC 请求ID
}
~~~

**获得数据META(meta)**

~~~javascript
POST /gm-demo/rpc/character HTTP/1.1
Host: gm.mztgame.com
Connection: close
Content-Type: application/json
Content-Length: 26
Accept: application/json

{
    "method":"meta",
    "id":"2"   //此ID为请求ID，与数据内容无关，响应时原值返回
}
~~~

**响应**

~~~javascript
HTTP/1.1 200 OK
Content-Length: 2101
Connection: close
Content-Type: application/json

{
    "result": {
        "ID": {
            "primary": true,
            "type": "number",
            "editable": false,
            "nullable": true
        },
        "玩家账号": {"type": "string"},
        "服务器名": {"type": "string"},
        "注册平台": {"type": "string"},
        "角色名": {"type": "string"},
        "公会名称": {"type": "string"},
        "VIP等级": {"type": "string"},
        "玩家等级": {"type": "string"},
        "充值货币": {"type": "string"},
        "注册时间": {"type": "string"},
        "注册IP": {"type": "string"},
        "注册设备码": {"type": "string"},
        "体力": {"type": "string"},
        "精力": {"type": "string"}
    },
    "id": "2"  //JSON-RPC 请求ID
}
~~~

### 新增(Insert)范例 

~~~javascript
POST /gm-demo/rpc/character HTTP/1.1
Host: gm.mztgame.com
Connection: close
Accept-Encoding: gzip, deflate
Content-Type: application/json
Content-Length: 150
Accept: application/json

{
    "method":"insert",
    "id":"123",
    "params": [
        {
            "name":"test2",
            "age":"1",
            "level":"10"
        },
    ]
}
~~~

**响应**

~~~javascript
HTTP/1.1 200 OK
Content-Length: 2101
Connection: close
Content-Type: application/json

{
    "result": {
      "code" : 0,
      "msg" : "成功或失败消息",
      "row" : {
          "id":"2",
          "name":"test2",
          "age":"1",
          "level":"10"
      }
    },
    "id": "123"
}
~~~


### 修改(Update)范例 

~~~javascript
POST /gm-demo/rpc/character HTTP/1.1
Host: gm.mztgame.com
Connection: close
Accept-Encoding: gzip, deflate
Content-Type: application/json
Content-Length: 150
Accept: application/json

{
    "method":"update",
    "id":"123",
    "params": [
        {
            "name":"test2",
            "age":"100",
            "level":"11"
        },
        {
            "id":"2"
        }
    ]
}
~~~

**响应**

~~~javascript
HTTP/1.1 200 OK
Date: Tue, 14 Jul 2015 12:47:35 GMT
Content-Length: 2101
Connection: close
Content-Type: application/json

{
    "result": {
      "code" : 0,
      "msg" : "成功或失败消息",
      "row" : {
            "id":"2",
            "name":"test2",
            "age":"100",
            "level":"11"
      }
    },
    "id": "123"
}
~~~


### 删除(Delete)范例 

~~~
POST /gm-demo/rpc/character HTTP/1.1
Host: gm.mztgame.com
Connection: close
Accept-Encoding: gzip, deflate
Content-Type: application/json
Content-Length: 150
Accept: application/json

{
    "method":"delete",
    "id":"123",
    "params": [
        {
            "id":"2",
            "name":"test2",
            "age":"100",
            "level":"11"
        },
    ]
}
~~~

**响应**

~~~javascript
HTTP/1.1 200 OK
Date: Tue, 14 Jul 2015 12:47:35 GMT
Content-Length: 2101
Connection: close
Content-Type: application/json

{
    "result": {
      "code" : 0,
      "msg" : "删除成功"
    },
    "id": "123"
}
~~~


### 其它接口调用(Callable)范例 

~~~javascript
POST /gm-demo/rpc/character HTTP/1.1
Host: gm.mztgame.com
Connection: close
Accept-Encoding: gzip, deflate
Content-Type: application/json
Content-Length: 150
Accept: application/json

{
    "method":"example",
    "id":"123",
    "params": [
        {
            "id":"2",
            "name":"test2",
            "age":"100",
            "level":"11"
        },
    ]
}
~~~

**响应**

~~~javascript
HTTP/1.1 200 OK
Date: Tue, 14 Jul 2015 12:47:35 GMT
Content-Length: 2101
Connection: close
Content-Type: application/json

{
    "result": {
      "code" : 0,
      "msg" : "执行结果"
    },
    "id": "123"
}
~~~
