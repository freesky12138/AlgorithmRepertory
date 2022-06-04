ELK

  获取ES镜像：docker pull elasticsearch:latest

  获取kibana镜像：docker pull kibana:latest

  获取logstash镜像：docker pull logstash:latest


创建网络 docker network create elk
  
1：启动es

2：
mkdir -p /data/elk/{elasticsearch/data,logstash}     #新建目录


创建docker-compose.yml
version: '2.2'
services:
  es01:
    image: elasticsearch:7.4.0
    container_name: es01
    environment:
      - node.name=es01
      - discovery.seed_hosts=es02
      - cluster.initial_master_nodes=es01,es02
      - cluster.name=docker-cluster
      - bootstrap.memory_lock=true
      - "ES_JAVA_OPTS=-Xms512m -Xmx512m"
    ulimits:
      memlock:
        soft: -1
        hard: -1
    volumes:
      - esdata01:/usr/share/elasticsearch/data
    ports:
      - 9200:9200
    networks:
      - elk
  es02:
    image: elasticsearch:7.4.0
    container_name: es02
    environment:
      - node.name=es02
      - discovery.seed_hosts=es01
      - cluster.initial_master_nodes=es01,es02
      - cluster.name=docker-cluster
      - bootstrap.memory_lock=true
      - "ES_JAVA_OPTS=-Xms512m -Xmx512m"
    ulimits:
      memlock:
        soft: -1
        hard: -1
    volumes:
      - esdata02:/usr/share/elasticsearch/data
    networks:
      - elk
  kibana:
    image: kibana:7.4.0
    container_name: kibana
    restart: always
    environment:
      - ELASTICSEARCH_HOSTS=http://es:9200 #设置访问elasticsearch的地址
      - I18N_LOCALE=zh-CN
    ports:
      - 5601:5601
    links:
      - es01:es #配置elasticsearch域名为es
    depends_on:
      - es01
    networks:
      - elk
  logstash:
    image: logstash:7.4.0
    container_name: logstash
    restart: always
    volumes:
      - /usr/local/huppert_server/elk/logstash.conf:/usr/share/logstash/pipeline/logstash.conf #挂载logstash的配置文件
    ports:
    ports:
      - 4560:4560
    links:
      - es01:es #配置elasticsearch域名为es
    depends_on:
      - es01
    networks:
      - elk
networks:
  elk:
    external: true
volumes:
  esdata01:
    driver: local
  esdata02:
    driver: local



	  
3:创建logstash.conf
input {
  tcp {
    mode => "server"
    host => "0.0.0.0"
    port => 4560
    codec => json_lines
  }
}
output {
  elasticsearch {
    hosts => "es:9200"
    index => "springboot-logstash-%{+YYYY.MM.dd}"
  }
}



#安装，运行ELK
docker-compose up -d


如果出现找不到  http:es/9200 的错误 就进容器改 config下面的.yml配置文件(改成ip或者同一个网络就用es)