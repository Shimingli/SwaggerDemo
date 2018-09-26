## SwaggerDemo 

* 推荐一个好玩的网站（http://www.network-science.de/ascii/）

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
```  
INSERT user VALUES (0,'仕明','25','仕明是个好同学');
```