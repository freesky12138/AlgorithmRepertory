version: '3.1'
services:
  jenkins:
    image: jenkins/jenkins:lts
    volumes:
      - /data/jenkins/:/var/jenkins_home
      - /var/run/docker.sock:/var/run/docker.sock
      - /usr/bin/docker:/usr/bin/docker
      - /usr/lib/x86_64-linux-gnu/libltdl.so.7:/usr/lib/x86_64-linux-gnu/libltdl.so.7
    ports:
      - "8080:8080"
    expose:
      - "8080"
      - "50000"
    privileged: true
    user: root
    restart: always
    container_name: jenkins
    environment:
      JAVA_OPTS: '-Djava.util.logging.config.file=/var/jenkins_home/log.properties'
	  
2：查看密码：
cat /data/jenkins/secrets/initialAdminPassword