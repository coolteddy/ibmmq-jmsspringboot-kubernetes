# IBMMQ-jmsspringboot-kubernetes
.
<img src="/Picture1.png"  width="700">
### package JMS springboot app
```
$ ./mvnw package
```

### build & push container
```
# build
$ ./mvnw com.google.cloud.tools:jib-maven-plugin:dockerBuild -Dimage=hmanikkothu/mqspringboot

# push
$ docker push hmanikkothu/mqspringboot
```

### setup IBM MQ on localhost (outside kubernetes cluster)
```
$ docker run   \
     --env LICENSE=accept   \
     --env MQ_QMGR_NAME=QM1 \
     --env MQ_APP_PASSWORD=passw0rd  \
     --publish 1414:1414   \
     --publish 9443:9443   \
     --detach   \
     ibmcom/mq
```

### build/test kustomize
```
# cd into the kubernetes folder
$ kustomize build .
```

### deploy in to kubernetes cluster
```
# ideally the 'kubectl apply -k .' should do the deployment, but for some bug in the kubectl the parsing of spec.ports[0].port fails, so use the kustomize as follows.

$ kustomize build . | kubectl apply -f - 
```

### Test
```
$ curl http://localhost:8080/send

# This should display the output as below 

Msg sent: Email{to=hari@manikkothu.com, body=Hello Msg Body}

```
