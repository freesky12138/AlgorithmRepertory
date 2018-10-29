#!/usr/bin/env bash
#运行和部署redis
redisDir="/home/huppert/redis"

if [ ! -d "$redisDir" ];then
    mkdir "$redisDir"
fi

cd "$redisDir"

wget http://download.redis.io/releases/redis-4.0.6.tar.gz

tar zxvf redis-4.0.6.tar.gz

rm -f redis-4.0.6.tar.gz

mv redis-4.0.6 redis

cd redis

make install

