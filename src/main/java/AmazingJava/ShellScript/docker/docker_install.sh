###
# step 1: 安装必要的一些系统工具
sudo yum install -y yum-utils device-mapper-persistent-data lvm2

# Step 2: 添加软件源信息
sudo yum-config-manager --add-repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

# Step 3: 更新并安装Docker-CE
sudo yum makecache fast

sudo yum -y install docker-ce

# Step 4: 开启Docker服务
sudo service docker start



#step 5：使用阿里云镜像加速
sudo mkdir -p /etc/docker

sudo tee /etc/docker/daemon.json <<-'EOF'
{"registry-mirrors": ["https://cmleuiw2.mirror.aliyuncs.com"]}
EOF

sudo systemctl daemon-reload

sudo systemctl restart docker


#安装docker-compose
sudo curl -L https://get.daocloud.io/docker/compose/releases/download/1.24.1/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose

sudo chmod +x /usr/local/bin/docker-compose

