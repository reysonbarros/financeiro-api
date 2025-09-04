build-with-apko:
	docker run --rm --workdir /work -v ${PWD}:/work cgr.dev/chainguard/apko \
	build jre21-apko.yaml reysonbarros/jre-apko:21 jre21-apko.tar -k https://apk.bell-sw.com/info@bell-sw.com-5fea454e.rsa.pub \
	--arch host

load-image-docker:
	docker image load < jre21-apko.tar

push-to-registry-app:
	docker image push reysonbarros/financeiro-api:3.0.6

push-to-registry-jre:
	docker image push reysonbarros/jre-apko:21-amd64

pull-from-registry-jre:
	docker image pull reysonbarros/jre-apko:21-amd64

build-with-docker-compose:
	docker-compose build --no-cache

load-image-kind:
	kind load docker-image reysonbarros/financeiro-api:3.0.6 -n financeiro

debug-container:
	cdebug exec -it financeiro-api

debug-pod:
	cdebug exec --namespace=financeiro -it pod/financeiro-api-7c766485b7-c2qtt

show-envs-from-container:
	docker inspect financeiro-api