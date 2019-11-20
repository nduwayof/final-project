docker-build: 
	docker build -t caiohoffmann/apigw:latest .

docker-push:docker-build 
	docker push caiohoffmann/apigw:latest

k8s-deployment-create: docker-push
	kubectl apply -f deployment.yaml
k8s-deployment-delete:
	kubectl delete -f deployment.yaml

k8s-service-create:
	kubectl apply -f service.yaml
k8s-service-delete:
	kubectl delete -f service.yaml

k8s-expose-create:
	kubectl expose deployment api-gw-deployment --type=LoadBalancer --port 80 --target-port 8085
k8s-expose-delete:
	kubectl delete service api-gw-deployment

k8s-reset : k8s-deployment-delete k8s-service-delete k8s-service-create k8s-deployment-create

########		MongoDB Intallation 				####
helm-mongo-create:
	helm install mongodb-fav bitnami/mongodb -f mongodb.yaml
helm-mongo-delete:
	helm uninstall mongodb-fav


########		Cassandra Intallation 				####
helm-cassandra-create:
	helm install cas-db bitnami/cassandra -f cassandra.yaml
helm-cassandra-delete:
	helm uninstall cas-db
