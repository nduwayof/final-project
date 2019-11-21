docker-login:
	docker login

#===================  account  ===========================
docker-build-acc: 
	docker build -t caiohoffmann/account-service2:latest account-service/.

docker-push-acc: docker-login docker-build-acc
	docker push caiohoffmann/account-service2:latest

k8s-deployment-create-acc: docker-push-acc
	kubectl apply -f account-service/k8s-deployment.yaml
k8s-deployment-delete-acc:
	kubectl delete -f account-service/k8s-deployment.yaml


k8s-service-create-acc:
	kubectl apply -f account-service/k8s-service.yaml
k8s-service-delete-acc:
	kubectl delete -f account-service/k8s-service.yaml

#===================  API-GW  ===========================

docker-build-gw: 
	docker build -t caiohoffmann/apigw:latest api-gw/.

docker-push-gw:docker-build-gw
	docker push caiohoffmann/apigw:latest

k8s-deployment-create-gw: docker-push-gw
	kubectl apply -f api-gw/deployment.yaml
k8s-deployment-delete-gw:
	kubectl delete -f api-gw/deployment.yaml

k8s-service-create-gw:
	kubectl apply -f api-gw/service.yaml
k8s-service-delete-gw:
	kubectl delete -f api-gw/service.yaml

k8s-expose-create-gw:
	kubectl expose deployment api-gw-deployment --type=LoadBalancer --port 80 --target-port 8085
k8s-expose-delete-gw:
	kubectl delete service api-gw-deployment


#===================  DEALS  ===========================

gradle-build-deal:
	gradle clean bootJar -p deals-service 

docker-build-deal: gradle-build-deal
	docker build -t caiohoffmann/deals:latest deals-service/.

docker-push-deal:docker-build-deal
	docker push caiohoffmann/deals:latest

k8s-deployment-create-deal: docker-push-deal
	kubectl apply -f deals-service/k8s-deployment.yaml
k8s-deployment-delete-deal:
	kubectl delete -f deals-service/k8s-deployment.yaml

k8s-service-create-deal:
	kubectl apply -f deals-service/k8s-service.yaml
k8s-service-delete-deal:
	kubectl delete -f deals-service/k8s-service.yaml

#===================  Favorites  ===========================

docker-build-fav: 
	docker build -t caiohoffmann/favorite:latest Favorites/.

docker-push-fav:docker-build-fav 
	docker push caiohoffmann/favorite:latest

k8s-deployment-create-fav: docker-push-fav
	kubectl apply -f Favorites/deployment.yaml
k8s-deployment-delete-fav:
	kubectl delete -f Favorites/deployment.yaml

k8s-service-create-fav:
	kubectl apply -f Favorites/service.yaml
k8s-service-delete-fav:
	kubectl delete -f Favorites/service.yaml

#===================  Location  ===========================
docker-build-loc: 
	docker build -t caiohoffmann/location-service:latest location-service/.

docker-push-loc: docker-login docker-build-loc
	docker push caiohoffmann/location-service:latest

k8s-deployment-create-loc: docker-push-loc
	kubectl apply -f location-service/k8s-deployment.yaml
k8s-deployment-delete-loc:
	kubectl delete -f location-service/k8s-deployment.yaml


k8s-service-create-loc:
	kubectl apply -f location-service/k8s-service.yaml
k8s-service-delete-loc:
	kubectl delete -f location-service/k8s-service.yaml

#===================  Notification  ===========================

build-not:
	mvn clean install -f notification-service/
docker-build-not: build-not
	docker build -t caiohoffmann/notification-service:latest notification-service/.

docker-push-not: docker-login docker-build-not
	docker push caiohoffmann/notification-service:latest

k8s-deployment-create-not: docker-push-not
	kubectl apply -f notification-service/k8s-deployment.yaml
k8s-deployment-delete-not:
	kubectl delete -f notification-service/k8s-deployment.yaml


k8s-service-create-not:
	kubectl apply -f notification-service/k8s-service.yaml
	kubectl apply -f notification-service/k8s-config-map.yaml
	kubectl apply -f notification-service/k8s-secrets.yaml
k8s-service-delete-not:
	kubectl delete -f notification-service/k8s-service.yaml
	kubectl delete -f notification-service/k8s-config-map.yaml
	kubectl delete -f notification-service/k8s-secrets.yaml
#===================  Order  ===========================

docker-build-ord: 
	docker build -t caiohoffmann/orders:latest Order/.

docker-push-ord:docker-build-ord 
	docker push caiohoffmann/orders:latest

k8s-deployment-create-ord: docker-push-ord
	kubectl apply -f Order/deployment.yaml
k8s-deployment-delete-ord:
	kubectl delete -f Order/deployment.yaml

k8s-service-create-ord:
	kubectl apply -f Order/service.yaml
k8s-service-delete-ord:
	kubectl delete -f Order/service.yaml

#===================  Order Status  ===========================

build-ord-st:
	mvn clean install -f OrderStatus/
docker-build-ord-st: build-ord-st
	docker build -t caiohoffmann/order-status:latest OrderStatus/.

docker-push-ord-st: docker-login docker-build-ord-st
	docker push caiohoffmann/order-status:latest

k8s-deployment-create-ord-st: docker-push-ord-st
	kubectl apply -f OrderStatus/k8s-deployment.yaml
k8s-deployment-delete-ord-st:
	kubectl delete -f OrderStatus/k8s-deployment.yaml


k8s-service-create-ord-st:
	kubectl apply -f OrderStatus/k8s-service.yaml
k8s-service-delete-ord-st:
	kubectl delete -f OrderStatus/k8s-service.yaml

#===================  Ranking  ===========================

build-rk:
	mvn clean install -f RankingService/
docker-build-rk: build-rk
	docker build -t caiohoffmann/ranking-service:latest RankingService/.

docker-push-rk: docker-login docker-build-rk
	docker push caiohoffmann/ranking-service:latest

k8s-deployment-create-rk: docker-push-rk
	kubectl apply -f RankingService/k8s-deployment.yaml
k8s-deployment-delete-rk:
	kubectl delete -f RankingService/k8s-deployment.yaml


k8s-service-create-rk:
	kubectl apply -f RankingService/k8s-service.yaml
k8s-service-delete-rk:
	kubectl delete -f RankingService/k8s-service.yaml

#===================  Payment  ===========================

build-pay:
	mvn clean install -f payment-service/
docker-build-pay: build-pay
	docker build -t caiohoffmann/pay-service:latest payment-service/.

docker-push-pay: docker-login docker-build-pay
	docker push caiohoffmann/pay-service:latest

k8s-deployment-create-pay: docker-push-pay
	kubectl apply -f payment-service/k8s-deployment.yaml
k8s-deployment-delete-pay:
	kubectl delete -f payment-service/k8s-deployment.yaml


k8s-service-create-pay:
	kubectl apply -f payment-service/k8s-service.yaml
	kubectl apply -f payment-service/k8s-config-map.yaml
	kubectl apply -f payment-service/k8s-secrets.yaml
k8s-service-delete-pay:
	kubectl delete -f payment-service/k8s-service.yaml
	kubectl delete -f payment-service/k8s-config-map.yaml
	kubectl delete -f payment-service/k8s-secrets.yaml


#===================  Restaurants  ===========================
	
build-rest:
	gradle clean bootJar -p restaurants-service/ 
docker-build-rest: build-rest
	docker build -t caiohoffmann/restaurants-service:latest restaurants-service/.

docker-push-rest: docker-login docker-build-rest
	docker push caiohoffmann/restaurants-service:latest

k8s-deployment-create-rest: docker-push-rest
	kubectl apply -f restaurants-service/k8s-deployment.yaml
k8s-deployment-delete-rest:
	kubectl delete -f restaurants-service/k8s-deployment.yaml


k8s-service-create-rest:
	kubectl apply -f restaurants-service/k8s-service.yaml
k8s-service-delete-rest:
	kubectl delete -f restaurants-service/k8s-service.yaml
k8s-expose-create-rest:
	kubectl expose deployment restaurants-service-deployment --type=LoadBalancer --port 80 --target-port 8091
k8s-expose-delete-rest:
	kubectl delete service restaurants-service-deployment

#===========================================				Final				===================================


k8s-deployment-create : k8s-deployment-create-pay k8s-deployment-create-rest k8s-deployment-create-rk k8s-deployment-create-ord-st k8s-deployment-create-ord k8s-deployment-create-not k8s-deployment-create-loc k8s-deployment-create-fav k8s-deployment-create-deal k8s-deployment-create-gw k8s-deployment-create-acc 

k8s-deployment-delete : k8s-deployment-delete-pay k8s-deployment-delete-rest k8s-deployment-delete-rk k8s-deployment-delete-ord-st k8s-deployment-delete-ord k8s-deployment-delete-not k8s-deployment-delete-loc k8s-deployment-delete-fav k8s-deployment-delete-deal k8s-deployment-delete-gw k8s-deployment-delete-acc

k8s-service-create : k8s-service-create-rest k8s-service-create-pay k8s-service-create-rk k8s-service-create-ord-st k8s-service-create-ord k8s-service-create-not k8s-service-create-loc k8s-service-create-fav k8s-service-create-deal k8s-service-create-gw k8s-service-create-acc
	kubectl apply -f k8s-secrets.yaml
k8s-service-delete : k8s-service-delete-pay k8s-service-delete-rest k8s-service-delete-rk k8s-service-delete-ord-st k8s-service-delete-ord k8s-service-delete-not k8s-service-delete-loc k8s-service-delete-fav k8s-service-delete-deal k8s-service-delete-gw k8s-service-delete-acc
	kubectl delete -f k8s-secrets.yaml

k8s-reset : k8s-deployment-delete k8s-service-delete  k8s-service-create  k8s-deployment-create 

helm-create: helm-mongo-create helm-cassandra-create helm-kafka-create helm-redis-create
helm-delete: helm-mongo-delete helm-cassandra-delete helm-kafka-delete helm-redis-delete

server-up: helm-create k8s-service-create k8s-deployment-create
server-down: helm-delete k8s-service-delete k8s-deployment-delete

########		MongoDB Intallation 				####
helm-mongo-create:
	helm install mongodb bitnami/mongodb -f mongodb.yaml
helm-mongo-delete:
	helm uninstall mongodb


########		Cassandra Intallation 				####
helm-cassandra-create:
	helm install cas-db incubator/cassandra -f cassandra.yaml
helm-cassandra-delete:
	helm uninstall cas-db


##########       Kafka 							######
helm-kafka-create:
	helm install my-kafka incubator/kafka -f kafka.yaml
helm-kafka-delete:
	helm uninstall my-kafka


##########       REDIS 							######
helm-redis-create:
	helm install redis bitnami/redis -f redis.yaml
helm-redis-delete:
	helm uninstall redis