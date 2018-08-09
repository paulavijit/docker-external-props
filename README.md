# **In Springboot Application Externalizing Environment Specific Properties while creating it's Docker Image**

### **Get source code**
```
	git clone git@github.com:paulavijit/docker-external-props.git
```

### **Setup development dependencies**

 - Java JDK1.8.0
 - Docker
 - Minikube (optional)

### **Build Project**
```
	cd docker-external-props
	./gradlew clean build
```

### **Steps to externalize properties**

1. In **src/main/resources**, create **application.properties**. This will contain all properties required by the appliaction and its value should be for local developer workstation. For e.g

	```
			my.message=Hello World
			log.level=info
	```

2. In **src/main/resources**, create **application-container.properties**. This will contain all properties in **application.properties**. But the value of properties which are environment specific should be parametrized so that they can be changed during deployment. For e.g.

	```
			my.message=${MY_MESSAGE}
			log.level=${LOG_LEVEL}
	```

3. When application is started within the an IDE, it will use application.properties by default

4. In Dockerfile, change the **Spring Active Profile** to **container** in **ENTRYPOINT**. The resulting docker image will then only use **application-container.properties**

	```
		ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar","docker-external-props.jar"]
	```

### **Pass values to a Docker Container while running it**

- Refer **docker/docker-compose.yml** for passing values as environment variables

```
	environment:
	  MY_MESSAGE: Hi
	  LOG_LEVEL: warn
```

- In **docker run** command

```
	docker run -e MY_MESSAGE=Hi -e LOG_LEVEL=warn -p 8080:8080 -d docker-external-props
```

### **Pass values to a Pod in Kubernetes**

- Refer **kube/docker-external-props.yml** for passing values as environment variables

```
	env:
		- name: MY_MESSAGE
		  value: Hi
		- name: LOG_LEVEL
		  value: warn
```

### **Example Requests**

- ###### **Get Message**
```
	http://localhost:8080//message/Paul
```
