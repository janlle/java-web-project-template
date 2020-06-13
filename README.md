# 后台开发脚手架及开发规范

## 脚手架项目结构说明

此脚手架适用于传统的JavaWeb中小型项目，具有快速搭建和快速开发的优势，避免了繁琐初始搭建及配置，可直接上手开发，提高了项目开发效率和项目的统一性可维护性。

## 项目开发规范


### 1.Java编程规范

#### 1.1.命名风格

* 代码中的命名均不能以下划线或美元符号开始，也不能以下划线或美元符号结束。

* 类名使用 UpperCamelCase 风格，必须遵从驼峰形式，但以下情形例外：DO / BO / DTO / VO / AO

* 接口类中的方法和属性不要加任何修饰符号，保持代码的简洁性，并加上有效的 Javadoc 注释。尽量不要在接口里定义变量，如果一定要定义变量，肯定是与接口方法相关，并且是整个应用的基础常量。

* Service/DAO 层方法命名规约

    * 1） 获取单个对象的方法用 get 做前缀。  
    * 2） 获取多个对象的方法 list 命名。  
    * 3） 获取统计值的方法用 count 做前缀。  
    * 3） 分页的方法用 page 做后缀。  
    * 4） 插入的方法用 save 做前缀。  
    * 5） 删除的方法用 delete 做前缀。  
    * 6） 修改的方法用 update 做前缀。  

* 领域模型命名规约

    * 1） 数据传输对象：xxxDTO，xxx 为业务领域相关的名称。  
    * 2） 展示对象：xxxVO，xxx 一般为实体名称。  
    * 3） POJO 是 DO/DTO/BO/VO 的统称，禁止命名成 xxxPOJO  


* 分层领域模型规约

    * DO(Data Object):与数据库表结构一一对应，通过 DAO 层向上传输数据源对象。
    * DTO(Data Transfer Object):数据传输对象，Service 或 Manager 向外传输的对象。
    * BO(Business Object):业务对象。由 Service 层输出的封装业务逻辑的对象。
    * AO(ApplicationObject):应用对象。在Web层与Service层之间抽象的复用对象模型，极为贴近展示层，复用度不高。
    * VO(View Object):显示层对象，通常是 Web 向模板渲染引擎层传输的对象。
    * Query:数据查询对象，各层接收上层的查询请求。注意超过 2 个参数的查询封装，禁止使用 Map 类来传输。

#### 1.2.代码格式化规范

每次编辑完一个类后使用idea的格式化功能，格式化代码和去掉无用导入的包，win快捷键为 Ctrl+Alt+l 和 Ctrl+Alt+o，Mac为ctrl+option+o 和 option+command+l。

项目的代码格式统一为UTF-8。


#### 1.3.API命名规范

GET（SELECT）：从服务器取出资源（一项或多项）。

POST（CREATE）：在服务器新建一个资源。

PUT（UPDATE）：在服务器更新完整的资源（客户端提供改变后的完整资源）。

DELETE（DELETE）：从服务器删除资源。

**基本规范**

* 使用'/'表示层级关系
* url 不能以'/'结尾
* url 中不能包含空格
* url 中不能以文件后缀结尾
* url 中字母小写，单词间加下划线
* 不要再url中添加CRUD

|  说明   | ActionName  | HttpMapping | HttpRequestBody | HttpResponseBody |
|  ----  | ----  |----|----|----|
| 查询所有  | list | GET /v1/user/list?xx=xx | N/A | Resource* list |
| 获取单个资源 | query | GET /v1/user/1 | N/A | Resource* |
| 创建单个资源 | create | POST /v1/user/create | Resource | Resource* |
| 更新单个资源 | update | PUT /v1/user/update | Resource | Resource* |
| 删除单个资源 | delete | DELETE /v1/user/delete | N/ | Empty |
| 分页条件查询 | page   | GET /v1/user/page?page=0&size=10    | N/A | Resource |
| 批量添加	 | batchCreate|	POST /batch_create	|Resource* list | Resource IDS|
| 批量删除	| batchDelete| 	POST /batch_delete	| Resource IDS	| Empty|
| 更新用户的年龄| 	updateAge| 	POST /v1/user/1/age?value=20| N/A	| {"key":"age","value":"20"}|


#### 1.4.异常处理规范

* 异常不要用来做流程控制，条件控制，因为异常的处理效率比条件分支低。

* 对大段代码进行 try-catch，这是不负责任的表现。catch 时请分清稳定代码和非稳 定代码，稳定代码指的是无论如何不会出错的代码。对于非稳定代码的 catch 尽可能进行区分 异常类型，再做对应的异常处理。

* 捕获异常是为了处理它，不要捕获了却什么都不处理而抛弃之，如果不想处理它，请 将该异常抛给它的调用者。最外层的业务使用者，必须处理异常，将其转化为用户可以理解的 内容。

* 有 try 块放到了事务代码中，catch 异常后，如果需要回滚事务，一定要注意手动回 滚事务。

* finally 块必须对资源对象、流对象进行关闭，有异常也要做 try-catch。

* 不能在 finally 块中使用 return，finally 块中的 return 返回后方法结束执行，不 会再执行 try 块中的 return 语句。

* 方法的返回值可以为 null，不强制返回空集合，或者空对象等，必须添加注释充分 说明什么情况下会返回 null 值。调用方需要进行 null 判断防止 NPE 问题。


#### 1.5.模块化开发规范

模块化开发是指公司共有的基础模块的开发如：短信、权限、支付、公共工具、邮件等。

模块化开发的pom模版

```xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.domain.module</groupId>
    <artifactId>domain-module</artifactId>
    <version>1.0.0.RELEASE</version>

    <dependencies>
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>1.4.0</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.7.0</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <distributionManagement>
        <repository>
            <id>releases</id>
            <name>Nexus release Repository</name>
            <url>http://192.168.0.110:8081/repository/maven-releases/</url>
        </repository>

        <snapshotRepository>
            <id>snapshots</id>
            <name>Nexus snapshots Repository</name>
            <url>http://192.168.0.110:8081/repository/maven-snapshots/</url>
        </snapshotRepository>
    </distributionManagement>

</project>

```

* 模块化定义 GAV 遵从以下规则:
    * GroupID格式:com.{域名}.业务线.[子业务线]，最多4级。
    * ArtifactID格式:产品线名-模块名。语义不重复不遗漏，先到中央仓库去查证一下。

* 模块化命名方式:主版本号.次版本号.修订号
    * 主版本号:产品方向改变，或者大规模API不兼容，或者架构不兼容升级。
    * 次版本号:保持相对兼容性，增加主要功能特性，影响范围极小的API不兼容修改。 3) 修订号:保持完全兼容性，修复BUG、新增次要功能特性等。

### 2.前后端对接规范

* 后端返回格式统一为json格式数据。

```json
{"code":20000,"message":"success","data":null}
```

* 后端返回的code字段，以4开头则为前端请求有误，以5开头则是后端接口问题。2开头则表示成功。

* 后端返回的message字段表示请求的信息，成功是success，错误则会提示相应的异常信息或返回error。

* 后端返回的data字段则是本次请求的数据，无数据则返回null。

* 后端返回的时间格式统一为时间戳


### 3.数据库设计规范

#### 3.1.建表规约

* 表名、字段名必须使用小写字母或数字，禁止出现数字开头，禁止两个下划线中间只出现数字。数据库字段名的修改代价很大，因为无法进行预发布，所以字段名称需要慎重考虑。

* 表达是与否概念的字段，必须使用 is_xxx 的方式命名，数据类型是 tinyint (1表示是，0表示否)。

* 表名不使用复数名词。

* 禁用保留字，如 desc、range、match、delayed 等，请参考 MySQL 官方保留字。

* 主键索引名为 pk_字段名;唯一索引名为 uk_字段名;普通索引名则为 idx_字段名。

* 小数类型为 decimal，禁止使用 float 和 double。

* 如果存储的字符串长度几乎相等，使用 char 定长字符串类型。

* varchar 是可变长字符串，不预先分配存储空间，长度不要超过 5000，如果存储长 度大于此值，定义字段类型为 text，独立出来一张表，用主键来对应，避免影响其它字段索 引效率。

* 表必备三字段:id, gmt_create, gmt_modified。

* 表的命名最好是加上"业务名称_表的作用"。

* 库名与应用名称尽量一致。

* 单表行数超过 500 万行或者单表容量超过 2GB，才推荐进行分库分表。


#### 3.2.索引规约

* 业务上具有唯一特性的字段，即使是多个字段的组合，也必须建成唯一索引。

* 超过三个表禁止 join。需要 join 的字段，数据类型必须绝对一致;多表关联查询时，保证被关联的字段需要有索引。

* 在 varchar 字段上建立索引时，必须指定索引长度，没必要对全字段建立索引，根据实际文本区分度决定索引长度即可。

* 如果有 order by 的场景，请注意利用索引的有序性。order by 最后的字段是组合索引的一部分，并且放在索引组合顺序的最后，避免出现 file_sort 的情况，影响查询性能。

* 利用覆盖索引来进行查询操作，避免回表。

* SQL 性能优化的目标:至少要达到 range 级别，要求是 ref 级别，如果可以是 consts 最好。

* 建组合索引的时候，区分度最高的在最左边。


#### 3.3.SQL 语句

* 不要使用 count(列名)或 count(常量)来替代 count(*)，count(*)是 SQL92 定义的 标准统计行数的语法，跟数据库无关，跟 NULL 和非 NULL 无关。

* count(distinct col) 计算该列除 NULL 之外的不重复行数，注意 count(distinct col1, col2) 如果其中一列全为NULL，那么即使另一列有不同的值，也返回为0。

* 当某一列的值全是 NULL 时，count(col)的返回结果为 0，但 sum(col)的返回结果为 NULL，因此使用 sum()时需注意 NPE 问题。

* 使用 ISNULL()来判断是否为 NULL 值。

* 在代码中写分页查询逻辑时，若 count 为 0 应直接返回，避免执行后面的分页语句。

* 不得使用外键与级联，一切外键概念必须在应用层解决。

* 禁止使用存储过程，存储过程难以调试和扩展，更没有移植性。

* 数据订正时，删除和修改记录时，要先 select，避免出现误删除，确认无误才能执行更新语句。

* in 操作能避免则避免，若实在避免不了，需要仔细评估 in 后边的集合元素数量，控制在 1000 个之内

* 如果有全球化需要，所有的字符存储与表示，均以 utf-8 编码，注意字符统计函数的区别。

* TRUNCATE TABLE 比 DELETE 速度快，且使用的系统和事务日志资源少，但 TRUNCATE 无事务且不触发 trigger，有可能造成事故，故不建议在开发代码中使用此语句。

#### 3.4.ORM 映射

* 在表查询中，一律不要使用 * 作为查询的字段列表，需要哪些字段必须明确写明。

* POJO 类的布尔属性不能加 is，而数据库字段必须加 is_，要求在 resultMap 中进行 字段与属性之间的映射。

* 不要用 resultClass 当返回参数，即使所有类属性名与数据库字段一一对应，也需 要定义;反过来，每一个表也必然有一个与之对应。

* sql.xml 配置参数使用:#{}，#param# 不要使用${} 此种方式容易出现 SQL 注入。

* 不允许直接拿 HashMap 与 Hashtable 作为查询结果集的输出。

* 更新数据表记录时，必须同时更新记录对应的 gmt_modified 字段值为当前时间。

* 不要写一个大而全的数据更新接口。传入为 POJO 类，不管是不是自己的目标更新字 段，都进行 update table set c1=value1,c2=value2,c3=value3; 这是不对的。执行 SQL 时，不要更新无改动的字段，一是易出错;二是效率低;三是增加 binlog 存储。

* @Transactional 事务不要滥用。事务会影响数据库的 QPS，另外使用事务的地方需 要考虑各方面的回滚方案，包括缓存回滚、搜索引擎回滚、消息补偿、统计修正等。


### 4.服务部署规范

* 在线上生产环境，JVM的Xms和Xmx设置一样大小的内存容量，避免在GC 后调整堆 大小带来的压力。

* 给 JVM 设置-XX:+HeapDumpOnOutOfMemoryError 参数，让 JVM 碰到 OOM 场景时输出 dump 信息。



