HA proxy

docker pull haproxy:1.5.19

docker run -d -it --name haproxy  -p 80:80 -v  /usr/local/yaml/haproxy/haproxy.cfg:/usr/local/etc/haproxy/haproxy.cfg:ro haproxy:1.5.19


haproxy.cfg：
#---------------------------------------------------------------------
# Example configuration for a possible web application.  See the
# full configuration options online.
#
#   http://haproxy.1wt.eu/download/1.4/doc/configuration.txt
#
#---------------------------------------------------------------------
 
#---------------------------------------------------------------------
# Global settings    
#---------------------------------------------------------------------
global    #全局配置文件
    # to have these messages end up in /var/log/haproxy.log you will
    # need to:     #配置日志
    #
    # 1) configure syslog to accept network log events.  This is done
    #    by adding the '-r' option to the SYSLOGD_OPTIONS in
    #    /etc/sysconfig/syslog    #修改syslog配置文件
    #
    # 2) configure local2 events to go to the /var/log/haproxy.log
    #   file. A line like the following can be added to
    #   /etc/sysconfig/syslog    #定义日志设备
    #
    #    local2.*                       /var/log/haproxy.log
    #
    #log         127.0.0.1 local2        #日志配置，所有的日志都记录本地，通过local2输出
 
    #chroot      /var/lib/haproxy        #改变haproxy的工作目录
    #pidfile     /var/run/haproxy.pid    #指定pid文件的路径
    maxconn     4000                    #最大连接数的设定
    #user        haproxy                 #指定运行服务的用户
    #group       haproxy                 #指定运行服务的用户组
    daemon
 
    # turn on stats unix socket
    #stats socket /var/lib/haproxy/stats
 
#---------------------------------------------------------------------
# common defaults that all the 'listen' and 'backend' sections will
# use if not designated in their block
#---------------------------------------------------------------------
defaults
     
    mode                    http                  #默认使用协议,可以为{http|tcp|health} http:是七层协议 tcp:是四层 health：只返回OK
    log                     global                #全局日志记录
    option                  httplog               #详细记录http日志
    option                  dontlognull           #不记录空日志
    option http-server-close                      #启用http-server-close
    option forwardfor       except 127.0.0.0/8    #来自这些信息的都不forwardfor
    option                  redispatch            #重新分发，ServerID对应的服务器宕机后，强制定向到其他运行正常的服务器
    retries                 3                      #3次连接失败则认为服务不可用
    timeout http-request    10s                    #默认http请求超时时间
    timeout queue           1m                     #默认队列超时时间
    timeout connect         10s                    #默认连接超时时间
    timeout client          1m                     #默认客户端超时时间
    timeout server          1m                     #默认服务器超时时间
    timeout http-keep-alive 10s                    #默认持久连接超时时间
    timeout check           10s                    #默认检查时间间隔
    maxconn                 3000                   #最大连接数
 
#---------------------------------------------------------------------
# main frontend which proxys to the backends
#---------------------------------------------------------------------
frontend  main *:80
    #定义ACL规则以如".html"结尾的文件；-i：忽略大小写
    acl url_static       path_beg       -i /static /images /javascript /stylesheets
    acl url_static       path_end       -i .jpg .gif .png .css .js
 
    use_backend static          if url_static    #调用后端服务器并检查ACL规则是否被匹配
    default_backend             app              #客户端访问时默认调用后端服务器地址池
 
#---------------------------------------------------------------------
# static backend for serving up images, stylesheets and such
#---------------------------------------------------------------------
backend static                    #定义后端服务器
    balance     roundrobin        #定义算法;基于权重进行轮询
    server      static 127.0.0.1:4331 check    check #启动对后端server的健康状态检测
 
#---------------------------------------------------------------------
# round robin balancing between the various backends
#---------------------------------------------------------------------
backend app
    balance     roundrobin
    server  app1 172.17.0.2:8080 check
    server  app2 172.17.0.3:8080 check
