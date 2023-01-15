#!/bin/bash
sudo su
yum -y install docker
usermod -a -G docker ec2-user
id ec2-user
newgrp docker
systemctl enable docker.service
systemctl start docker.service

docker pull nazariikozynets/master-jenkins:latest
docker run --name jenkins --rm -dp 80:8080 --env JENKINS_ADMIN_ID=admin --env JENKINS_ADMIN_PASSWORD=password nazariikozynets/master-jenkins:latest