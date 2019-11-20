#!/bin/bash

#=============
#EDIT HERE
#=============

echo "Hey, let's create a new service!"

echo "serviceName?"
read name

echo "Your docker hub user (or other repo) ?"
read hub


read -p "Use docker image [latest]: " tag
tag=${tag:-latest}
echo $tag

read -p "service port[8080]: " srvPort
srvPort=${srvPort:-latest}
echo $srvPort

export serviceName=$name
export dockerUser=$hub
export serviceVersion=$tag
export servicePort=$srvPort
export serviceDBPort=$((servicePort+1)) 

#=============
# NO MORE EDITS
#============

export k8sDeployment=k8s-deployment.yaml
export k8sService=k8s-service.yaml
export k8sConfigMap=k8s-config-map.yaml
export k8sSecrets=k8s-secrets.yaml
export k8sRole=k8s-role.yaml
export k8sPersistenceVol=k8s-persistence-volumn.yaml
export k8sPersistenceDB=k8s-persistence-database.yaml

export serviceImage=$dockerUser/$serviceName:$serviceVersion

pushd ./templates && ./process.sh && popd
mv makefile.template makefile
mv bootstrap.yaml src/main/resources
echo "DONE!"