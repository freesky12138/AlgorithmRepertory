#!/usr/bin/env bash
#部署运行ngnix


yum install gcc-c++

yum install -y pcre pcre-devel

yum install -y zlib zlib-devel

yum install -y openssl openssl-devel

tar -zxvf nginx-1.10.1.tar.gzcd nginx-1.10.1

./configure

make
make install

whereis nginx

cd /usr/local/nginx/sbin/
./nginx
./nginx -s stop
./nginx -s quit
./nginx -s reload

/etc/rc.local > /usr/local/nginx/sbin/nginx

chmod 755 rc.loca