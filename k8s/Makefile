create-all: create-cluster create-namespace pull-image-dockerhub load-image-kind create-aws-secret install-istio enable-sidecar install-prometheus-operator install-metallb wait-metallb install-helm-package-app

create-cluster:
	kind create cluster --config cluster.yaml

create-namespace:
	kubectl apply -f extra-manifests/namespace/

pull-image-dockerhub:
	docker image pull reysonbarros/financeiro-api:3.0.0

load-image-kind:
	kind load docker-image reysonbarros/financeiro-api:3.0.0 -n financeiro

create-aws-secret:
	@kubectl create secret generic financeiro-api-aws-credentials \
        --from-literal=key_id=${AWS_ACCESS_KEY_ID} \
        --from-literal=secret=${AWS_SECRET_ACCESS_KEY} \
	-n financeiro

install-istio:
	istioctl install --set profile=demo --skip-confirmation

enable-sidecar:
	kubectl label ns financeiro istio-injection=enabled

install-prometheus-operator:
	helm repo add prometheus-community https://prometheus-community.github.io/helm-charts && \
	helm install kube-prometheus-stack prometheus-community/kube-prometheus-stack --version 68.4.4 -n monitoring --values extra-manifests/prometheus/values.yaml

install-metallb:
	kubectl apply -f extra-manifests/metallb/metallb-native.yaml

wait-metallb:
	kubectl wait --namespace metallb-system \
                --for=condition=ready pod \
                --selector=app=metallb \
                --timeout=200s


install-helm-package-app:
	helm install financeiro-api chart/

delete-all:
	helm uninstall financeiro-api
	helm uninstall kube-prometheus-stack -n monitoring
	kind delete clusters financeiro
