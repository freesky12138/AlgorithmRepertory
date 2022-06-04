1：docker-compose：
version: '2'
services:
  mysql-master:
    image: mysql:5.7.17
    environment:
      - "MYSQL_ROOT_PASSWORD=root"
      - "MYSQL_DATABASE=replicas_db"
    volumes:
      - "./master_my.cnf:/etc/mysql/my.cnf"
    links:
      - mysql-slave
    ports:
      - "33065:3306"
    restart: always
    hostname: mysql-master
  mysql-slave:
    image: mysql:5.7.17
    environment:
      - "MYSQL_ROOT_PASSWORD=root"
      - "MYSQL_DATABASE=replicas_db"
    volumes:
      - "./slave_my.cnf:/etc/mysql/my.cnf"
    ports:
      - "33066:3306"
    restart: always
    hostname: mysql-slave

2：master_my.cnf（主服务器配置）：
[mysqld]
## 设置server_id，一般设置为IP，注意要唯一
server_id=100
## 复制过滤：也就是指定哪个数据库不用同步（mysql库一般不同步）
binlog-ignore-db=mysql
## 开启二进制日志功能，可以随便取，最好有含义（关键就是这里了）
log-bin=mysql-bin
## 为每个session分配的内存，在事务过程中用来存储二进制日志的缓存
binlog_cache_size=1M
## 主从复制的格式（mixed,statement,row，默认格式是statement）
binlog_format=mixed
## 二进制日志自动删除/过期的天数。默认值为0，表示不自动删除。
expire_logs_days=7
## 跳过主从复制中遇到的所有错误或指定类型的错误，避免slave端复制中断。
## 如：1062错误是指一些主键重复，1032错误是因为主从数据库数据不一致
slave_skip_errors=1062

3：slave_my.cnf(从服务器配置)
[mysqld]
## 设置server_id，一般设置为IP，注意要唯一
server_id=101
## 复制过滤：也就是指定哪个数据库不用同步（mysql库一般不同步）
binlog-ignore-db=mysql
## 开启二进制日志功能，以备Slave作为其它Slave的Master时使用
log-bin=mysql-slave1-bin
## 为每个session 分配的内存，在事务过程中用来存储二进制日志的缓存
binlog_cache_size=1M
## 主从复制的格式（mixed,statement,row，默认格式是statement）
binlog_format=mixed
## 二进制日志自动删除/过期的天数。默认值为0，表示不自动删除。
expire_logs_days=7
## 跳过主从复制中遇到的所有错误或指定类型的错误，避免slave端复制中断。
## 如：1062错误是指一些主键重复，1032错误是因为主从数据库数据不一致
slave_skip_errors=1062
## relay_log配置中继日志
relay_log=replicas-mysql-relay-bin
## log_slave_updates表示slave将复制事件写进自己的二进制日志
log_slave_updates=1
## 防止改变数据(除了特殊的线程)
read_only=1

4：配置mysql服务

在主mysql执行：
 show master status;

在从服务器执行（参数来自主服务器）：
CHANGE MASTER TO
	MASTER_HOST='mysql_mysql-master_1',
	MASTER_USER='root',
	MASTER_PASSWORD='root',
	MASTER_LOG_FILE='mysql-bin.000001',
	MASTER_LOG_POS=154;
	
start slave;

show slave status\G;