分布式事务，配合nacos

1：已有nacos
2：更改seata配置中心和注册中心为nacos
3：创建seata数据库
4：在业务数据库创建seata所用的undo表
5：将seata的配置（修改数据库源）通过 官方脚本导入到nacos
6：将nacos的（service.vgroupMapping.%s-seata-service-group）配置名改为已有的服务如
service.vgroupMapping.ManagementServer-seata-service-group
每注册一个服务需要添加一个此配置
7：在项目引用包：
<dependency>
            <groupId>io.seata</groupId>
            <artifactId>seata-spring-boot-starter</artifactId>
            <version>1.4.0</version>
        </dependency>
8：项目添加配置：
seata:
  config:
    type: nacos
    nacos:
      server-addr: 127.0.0.1:8848
      group : "SEATA_GROUP"
      namespace: ""
  registry:
    type: nacos
    nacos:
      application: seata-server
      server-addr: 127.0.0.1:8848
      group: "SEATA_GROUP"
      namespace: ""