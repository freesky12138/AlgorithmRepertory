
1. 下载reids镜像：docker pull redis

2. 创建配置
#在/usr/local/src目录下创建redis-cluster文件夹，并创建配置文件redis-cluster.conf
cd /usr/local/src && mkdir redis-cluster && cd ./redis-cluster && touch redis-cluster.conf
redis-cluster.conf的内容如下：
 
port ${PORT}
cluster-enabled yes
cluster-config-file nodes.conf
cluster-node-timeout 5000
#对外ip
cluster-announce-ip 192.168.56.103
cluster-announce-port ${PORT}
cluster-announce-bus-port 1${PORT}
appendonly yes


3.节点创建脚本
create_cluster.sh

cd /usr/local/src/redis-cluster && touch create_cluster.sh
 
create_cluster.sh内容如下：（7000-7005是我要创建的6个redis实例，自行修改）
 
#!/bin/bash
#在/home/redis-cluster下生成conf和data目标，并生成配置信息
for port in `seq 7000 7005`; do
  mkdir -p ./${port}/conf && PORT=${port} envsubst < ./redis-cluster.conf > ./${port}/conf/redis.conf && mkdir -p ./${port}/data;
done

4.容器创建脚本
cd /usr/local/src/redis-cluster && touch start_cluster.sh
start_cluster.sh 内容如下：
 
#!/bin/bash
for port in `seq 7000 7005`; do
  docker run -d -ti -p ${port}:${port} -p 1${port}:1${port} -v /usr/local/huppert_service/redis/${port}/conf/redis.conf:/usr/local/etc/redis/redis.conf -v /usr/local/huppert_service/redis/${port}/data:/data  --restart always --name redis-${port} --net redis-net --sysctl net.core.somaxconn=1024 redis redis-server /usr/local/etc/redis/redis.conf;
done

5.运行
chmod 755 create_cluster.sh

chmod 755 start_cluster.sh

docker network create redis-net

./create_cluster.sh && ./start_cluster.sh


集群创建

进入容器：docker exec -it redis-7000 /bin/bash

关闭防火墙
firewall-cmd --zone=public --add-port=7000-7005/tcp --permanent
firewall-cmd --zone=public --add-port=17000-17005/tcp --permanent
firewall-cmd --reload

redis-cli --cluster create 192.168.56.104:7000 192.168.56.104:7001 192.168.56.104:7002  192.168.56.104:7003  192.168.56.104:7004  192.168.56.104:7005   --cluster-replicas 1

redis-cli --cluster create 	192.168.0.72:6379 192.168.0.25:6379 192.168.0.57:6379  192.168.0.130:6379 192.168.0.91:6379 192.168.0.69:6379 --cluster-replicas 1

redis-cli --cluster create 	192.168.0.72:6379 192.168.0.25:6379 192.168.0.57:6379  192.168.0.130:6379 192.168.0.91:6379 192.168.0.69:6379 --cluster-replicas 1
