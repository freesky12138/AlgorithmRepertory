docker pull rabbitmq

2:启动
docker run -d --hostname rabbit1 --name myrabbit1 -p 15672:15672 -p 5672:5672 -e RABBITMQ_ERLANG_COOKIE='rabbitcookie' 镜像ID/名称
docker run -d --hostname rabbit2 --name myrabbit2 -p 5673:5672 --link myrabbit1:rabbit1 -e RABBITMQ_ERLANG_COOKIE='rabbitcookie' 镜像ID/名称
docker run -d --hostname rabbit3 --name myrabbit3 -p 5674:5672 --link myrabbit1:rabbit1 --link myrabbit2:rabbit2 -e RABBITMQ_ERLANG_COOKIE='rabbitcookie' 镜像ID/名称

3：配置
docker exec -it myrabbit1 bash
rabbitmqctl stop_app
rabbitmqctl reset
rabbitmqctl start_app
exit

docker exec -it myrabbit2 bash
rabbitmqctl stop_app
rabbitmqctl reset
rabbitmqctl join_cluster rabbit@rabbit1	
rabbitmqctl start_app
exit

docker exec -it myrabbit3 bash
rabbitmqctl stop_app
rabbitmqctl reset
rabbitmqctl join_cluster --ram rabbit@rabbit1
rabbitmqctl start_app
exit





通过docker logs 容器ID，查询RABBITMQ_ERLANG_COOKIE（home dir），然后拷贝到其他两台
docker cp 容器ID:/var/lib/rabbitmq/. 主机目录
docker cp 主机目录/.erlang.cookie 容器ID:/var/lib/rabbitmq/





# 先添加个用户
rabbitmqctl add_user 新用户名 新密码
# 然后可以选择把原来那个删了
rabbitmqctl delete_user 原来的用户名，即guest
# 查看用户组，发现新增用户没有权限，TAG=[]
rabbitmqctl  list_users
# 授权
rabbitmqctl set_user_tags 新用户名