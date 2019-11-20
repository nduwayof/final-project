docker-build: 
	docker build -t caiohoffmann/orders:latest .

docker-push:docker-build 
	docker push caiohoffmann/orders:latest

k8s-deployment-create: docker-push
	kubectl apply -f deployment.yaml
k8s-deployment-delete:
	kubectl delete -f deployment.yaml

k8s-service-create:
	kubectl apply -f service.yaml
k8s-service-delete:
	kubectl delete -f service.yaml

k8s-expose-create:
	kubectl expose deployment orders-deployment --type=LoadBalancer --port 80 --target-port 8082
k8s-expose-delete:
	kubectl delete service orders-deployment

k8s-reset : k8s-deployment-delete k8s-service-delete k8s-deployment-create k8s-service-create