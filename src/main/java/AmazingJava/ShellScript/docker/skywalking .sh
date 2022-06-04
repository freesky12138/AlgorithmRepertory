

1:docker-compose.yml：

version: '3.3'
services:
  skywalking-oap:
    image: apache/skywalking-oap-server:latest
    container_name: skywalking-oap
    restart: always
    ports:
      - 11800:11800
      - 12800:12800
    healthcheck:
      test: ["CMD-SHELL", "/skywalking/bin/swctl"]
      interval: 30s
      timeout: 10s
      retries: 3
      start_period: 40s
    environment:
      JAVA_OPTS: "-Xms512m -Xmx512m"
  skywalking-ui:
    image: apache/skywalking-ui:latest
    container_name: skywalking-ui
    depends_on:
      - skywalking-oap
    links:
      - skywalking-oap
    restart: always
    ports:
      - 3380:8080
    environment:
      SW_OAP_ADDRESS: skywalking-oap:12800
	  
2docker-compose up -d



3下载agent 的jar包 
http://skywalking.apache.org/downloads/

4：在jar运行时加参数(skywalking-agent需要整个文件夹 而不是只有一个jar包)
-javaagent:C:\\Users\\Administrator\\Desktop\\启动\\skywalking-agent.jar -DSW_AGENT_COLLECTOR_BACKEND_SERVICES=192.168.56.104:11800 -DSW_AGENT_NAME=Management


5:http://localhost:3380