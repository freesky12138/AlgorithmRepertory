docker pull bladex/sentinel-dashboard:1.7.2


docker run --name sentinel -d  -p 8719:8719 -p 8087:8080  bladex/sentinel-dashboard:1.7.2

账号密码都是：sentinel