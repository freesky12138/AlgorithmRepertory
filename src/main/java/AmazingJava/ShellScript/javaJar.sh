#!/usr/bin/env bash
#运行java的jar包
port=9090
#根据端口号查询对应的pid
pid=$(netstat -nlp | grep :$port | awk '{print $7}' | awk -F"/" '{ print $1 }');

#杀掉对应的进程，如果pid不存在，则不执行
if [  -n  "$pid"  ];  then
    kill  -9  $pid;
fi
nohup java -Xms256M -Xmx256M -jar insurance_service.jar --spring.config.location="/opt/insurance/config/" >/dev/null 2>&1 &
echo "启动完成，请进入logs/文件夹查看相关日志"
exit
