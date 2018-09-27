## Spring Boot整合Swagger2、Mybatis构建RESTful API

* 首先推荐一个好玩的网站 [ASCll](http://www.network-science.de/ascii/) ,可以把你想要的字母生成文本的图形，然后`copy`到`banner.txt`,即可替换`Spring Boot`的开启图标
```
                                                            
88    88                                                    
88    88                                                    
88    88                                                    
88    88  ,adPPYba,  8b       d8  ,adPPYba,    88       88  
88    88 a8"     "8a `8b     d8' a8P_____88    88       88  
88    88 8b       d8  `8b   d8'  8PP"""""""    88       88  
88    88 "8a,   ,a8"   `8b,d8'   "8b,   ,aa    "8a,   ,a88  
88    88  `"YbbdP"'      "8"      `"Ybbd8"'     `"YbbdP'Y8  
                                                            
                                                            

          88          88                    88                          
          88          ""                    ""                          
          88                                                            
,adPPYba, 88,dPPYba,  88 88,dPYba,,adPYba,  88 8b,dPPYba,   ,adPPYb,d8  
I8[    "" 88P'    "8a 88 88P'   "88"    "8a 88 88P'   `"8a a8"    `Y88  
 `"Y8ba,  88       88 88 88      88      88 88 88       88 8b       88  
aa    ]8I 88       88 88 88      88      88 88 88       88 "8a,   ,d88  
`"YbbdP"' 88       88 88 88      88      88 88 88       88  `"YbbdP"Y8  
                                                            aa,    ,88  
                                                             "Y8bbdP"   

```
![spring boot](https://upload-images.jianshu.io/upload_images/5363507-ee790b50c43f80fc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


#### Swagger2
 `Swagger`是一款可以快速生成符合`RESTful`风格`API`并进行在线调试的插件。`REST`实际上为`Representational State Transfer`的缩写，翻译为“表现层状态转化” 。如果一个架构符合`REST `原则，就称它为`RESTful`架构。 实际上，“表现层状态转化”省略了主语，完整的说应该是“资源表现层状态转化”。什么是资源`Resource`？资源指的是网络中信息的表现形式，比如一段文本，一首歌，一个视频文件等等；什么是表现层`Reresentational`？表现层即资源的展现在你面前的形式，比如文本可以是`JSON`格式的，也可以是`XML`形式的，甚至为二进制形式的。图片可以是`gif`，也可以是`PNG`；什么是状态转换`State Transfer`？用户可使用`URL`通过`HTTP`协议来获取各种资源，`HTTP`协议包含了一些操作资源的方法，比如：`GET` 用来获取资源， `POST` 用来新建资源 , `PUT `用来更新资源，` DELETE` 用来删除资源，` PATCH` 用来更新资源的部分属性。通过这些HTTP协议的方法来操作资源的过程即为状态转换。

* 传统`URL`请求和`RESTful`风格请求的区别
 ```  
描述	传统请求	                 方法	          RESTful请求	         方法
查询	/user/query?name=mrbird      	GET       	/user?name=mrbird	GET
详情	/user/getInfo?id=1	            GET	        /user/1	            GET
创建	/user/create?name=mrbird	    POST	    /user            	POST
修改	/user/update?name=mrbird&id=1	POST	    /user/1	            PUT
删除	/user/delete?id=1	            GET	        /user/1	           DELETE
 ```
#### 大致可以总结下传统请求和`RESTful`请求的几个区别：
   * 传统请求通过URL来描述行为，如`create`，`delete`等；`RESTful`请求通过`URL`来描述资源。
   * `RESTful`请求通过`HTTP`请求的方法来描述行为，比如`DELETE`，`POST`，`PUT`等，并且使用`HTTP`状态码来表示不同的结果。
   * `RESTful`请求通过`JSON`来交换数据。



####  一、引入Swagger、MySQL 依赖
```
 <!-- MySQL 连接驱动依赖 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql-connector}</version>
        </dependency>
        <!-- Spring Boot Mybatis 依赖 -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>${mybatis-spring-boot}</version>
        </dependency>

        <!--使用的Swagger版本为2.6.1：-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.6.1</version>
        </dependency>
        <!--ui的界面-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.6.1</version>
        </dependency>
```

#### 二、配置 SwaggerConfig

```
/**
 * author： Created by shiming on 2018/9/26 18:10
 * mailbox：lamshiming@sina.com
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // 在配置类中添加@EnableSwagger2注解来启用Swagger2，apis()定义了扫描的包路径
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.swagger.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("RESTful API 文档")
                .contact(new Contact("shiming", "https://www.shiming.site/", null))
                .version("1.0")
                .build();
    }
}
```
#### 三、配置数据库，创建表


```
## 数据源配置
spring.datasource.url= jdbc:mysql://localhost:3306/shiming?useUnicode=true&characterEncoding=utf-8&useSSL=true
spring.datasource.username=root
spring.datasource.password=App123
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```
*  
    ![Navicat创建数据库一.png](https://upload-images.jianshu.io/upload_images/5363507-e76bc4cc7ff7386d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 创建数据库
``` 
DROP TABLE IF EXISTS  `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(25) DEFAULT NULL COMMENT '用户姓名',
  `user_age` varchar(25) DEFAULT NULL COMMENT '用户年级',
  `description` varchar(25) DEFAULT NULL COMMENT '用户描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```
*  
   ![Navicat创建数据库二.png](https://upload-images.jianshu.io/upload_images/5363507-9321ddc1db668556.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


```  
INSERT user VALUES (0,'仕明','25','仕明是个好同学');
```
*  
  ![Navicat创建数据库三.png](https://upload-images.jianshu.io/upload_images/5363507-2eb01d0624f57fe3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
*  
     ![Navicat创建数据库四.png](https://upload-images.jianshu.io/upload_images/5363507-2da826977ef3b129.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 最终的创建的表
![image.png](https://upload-images.jianshu.io/upload_images/5363507-48126ed7e744b006.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


* 访问本地地址 ：http://localhost:8080/swagger-ui.html#/

   ![image.png](https://upload-images.jianshu.io/upload_images/5363507-2dcc6dd5f8aa0159.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 四、测试
* 使用 `swagger` 新增用户
![image.png](https://upload-images.jianshu.io/upload_images/5363507-03bb7fdb5279dbb1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 使用 `swagger` 获取用户列表，也可以访问：http://localhost:8080/user/list 

```
{
  "code": "0",
  "message": "success",
  "result": [
    {
      "id": 1,
      "name": "仕明",
      "age": "25",
      "description": "仕明是个好同学"
    },
    {
      "id": 2,
      "name": "仕明",
      "age": "25",
      "description": "仕明是个好同学"
    },
    {
      "id": 3,
      "name": "仕明",
      "age": "25",
      "description": "仕明是个好同学"
    },
    {
      "id": 4,
      "name": "仕明",
      "age": "25",
      "description": "仕明是个好同学"
    },
    {
      "id": 5,
      "name": "仕明",
      "age": "25",
      "description": "仕明是个好同学"
    },
    {
      "id": 6,
      "name": "仕明",
      "age": "25",
      "description": "仕明是个好同学"
    },
    {
      "id": 7,
      "name": "仕明",
      "age": "25",
      "description": "仕明是个好同学"
    }
  ]
}
```
![数据库](https://upload-images.jianshu.io/upload_images/5363507-ffa46be50ef69449.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 使用 `swagger` 删除`id=1`的用户！
![image.png](https://upload-images.jianshu.io/upload_images/5363507-06bb29290d598e23.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


* 使用 `swagger` 获取`id=2`的用户！
![image.png](https://upload-images.jianshu.io/upload_images/5363507-a6f8522e9f66822c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```
{
  "code": "0",
  "message": "success",
  "result": {
    "id": 2,
    "name": "仕明",
    "age": "25",
    "description": "仕明是个好同学"
  }
}
```

* 使用 `swagger` 更新用户！

![image.png](https://upload-images.jianshu.io/upload_images/5363507-27eea8ac65c14a66.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
    {
      "id": 7,
      "name": "仕明",
      "age": "25",
      "description": "我更新了-仕明是个好同学"
    }
```

* 更新后的数据库
![image.png](https://upload-images.jianshu.io/upload_images/5363507-e4421258772876de.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* UserController的代码如下
```
/**
 * author： Created by shiming on 2018/9/26 16:42
 * mailbox：lamshiming@sina.com
 */

@Api(value = "用户Controller")
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService cityService;

    //{"id":1,"name":"仕明","age":"25","description":"仕明是个好同学"}
    // http://localhost:8080/user/1
    @ApiOperation(value = "获取用户信息", notes = "根据用户id获取用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/{id}")
    @ResponseBody
    public ResultBody getUserById(@PathVariable(value = "id") Long id) throws GlobalErrorInfoException {
        System.out.println("id="+id);
        User userById = cityService.getUserById(id);
        if(userById!=null){
            ResultBody resultBody = new ResultBody(userById);
            return resultBody;
        }
//        User user = new User();
//        user.setDescription("没有找到这个人");
        throw new GlobalErrorInfoException(GlobalErrorInfoEnum.NOT_FOUND);
       // return user;
    }
    // http://localhost:8080/user/list
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @GetMapping("/list")
    @ResponseBody
    public ResultBody getUserList() throws GlobalErrorInfoException {
        List<User> userList = cityService.getUserList();
        if (userList==null||userList.size()==0){
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.NOT_FOUND);
        }
        ResultBody resultBody = new ResultBody(userList);
        return resultBody;
    }


    @ApiOperation(value = "新增用户", notes = "根据用户实体创建用户")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    @PostMapping("/add")
    @ResponseBody
    public ResultBody addUser(@RequestBody User user) {
        Long aLong = cityService.addUser(user);
        System.out.println("Long=="+aLong);
        Map<String, Object> map = new HashMap<>();
        map.put("result", "新增用户成功");
        ResultBody resultBody = new ResultBody(map);
        return resultBody;
    }

    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResultBody deleteUser(@PathVariable(value = "id") Long id) {
        Long aLong = cityService.deleteUser(id);
        System.out.println("along="+aLong);
        System.out.println("删除掉的id="+id);
        Map<String, Object> map = new HashMap<>();
        map.put("result", "删除成功");
        ResultBody resultBody = new ResultBody(map);
        return resultBody;
    }
    @ApiOperation(value = "更新用户", notes = "根据用户id更新用户")
    @ApiImplicitParams(@ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User"))
    @PutMapping("/{id}")
    @ResponseBody
    public  ResultBody updateUser(@RequestBody User user) {
        System.out.println(user.toString());
        Long aLong = cityService.updateUser(user);
        System.out.println("aLong="+aLong);
        Map<String, Object> map = new HashMap<>();
        map.put("result", "更新成功");
        ResultBody resultBody = new ResultBody(map);
        return resultBody;
    }
}

```
#### Swagger常用注解
* @Api：修饰整个类，描述Controller的作用；
* @ApiOperation：描述一个类的一个方法，或者说一个接口；
* @ApiParam：单个参数描述；
* @ApiModel：用对象来接收参数；
* @ApiProperty：用对象接收参数时，描述对象的一个字段；
* @ApiResponse：HTTP响应其中1个描述；
* @ApiResponses：HTTP响应整体描述；
* @ApiIgnore：使用该注解忽略这个API；
* @ApiError ：发生错误返回的信息；
* @ApiImplicitParam：一个请求参数；
* @ApiImplicitParams：多个请求参数。

* 编写RESTful API接口
  * Spring Boot中包含了一些注解，对应于HTTP协议中的方法：
  * @GetMapping对应HTTP中的GET方法；
  * @PostMapping对应HTTP中的POST方法；
  * @PutMapping对应HTTP中的PUT方法；
  * @DeleteMapping对应HTTP中的DELETE方法；
  * @PatchMapping对应HTTP中的PATCH方法。


*  本文git地址[Swagger2Demo](https://github.com/Shimingli/SwaggerDemo)

* 最后说明几点
   *  感谢给与我学习的帮助[SpringAll](https://github.com/wuyouzhuguli/SpringAll)
   *  `RESTful`只是一种风格，并不是一种强制性的标准。   
   *  访问 [http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/) 就是`Swagger UI`
   *  `spring.profiles.active=dev` 使用的是开发的环境
   *   为了使APi返回更加的规整，我使用了`ResultBody`,来返回给客户端，如果不写`getXXX`的方法的话，`IllegalArgumentException `： 不合法的参数异常。`No converter found for return value of type`： 找不到类型返回值的转换器 .这个类一定得提供。
   *  仅限于学习


