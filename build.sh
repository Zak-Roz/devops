#!/bin/bash

#docker build -t base-jenkins ./base_image

#docker build -t nazariikozynets/master-jenkins ./master_image 
docker run --name jenkins --rm -dp 8080:8080 --env JENKINS_ADMIN_ID=admin --env JENKINS_ADMIN_PASSWORD=password nazariikozynets/master-jenkins