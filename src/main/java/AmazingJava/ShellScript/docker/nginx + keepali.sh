1：
docker pull nginx

2：
docker run  --name nginx -d -p 80:80 --net host  -v /usr/local/nginx_data/html:/usr/share/nginx/html -v /usr/local/nginx_data/conf/nginx.conf:/etc/nginx/nginx.conf -v /usr/local/nginx_data/conf.d/default.conf:/etc/nginx/conf.d/default.conf  -v /usr/local/nginx_data/logs:/var/log/nginx nginx