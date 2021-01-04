# onedrive

How to start the onedrive application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/onedrive-1.0-SNAPSHOT.jar server app.yml`
1. To check that your application is running enter url `http://localhost:8080`
direct run
 java -jar target/app.jar server app.yml


docker running
- Build the image: docker build ../ or  docker build ../ -t onedriveapp
- List the images: docker images 
- list containers docker ps -a
- Create and start a container from the image: docker run "imageid" 
- remove containes docker container rm $(docker container ls –aq)
- stop containeres docker container stop $(docker container ls –aq)

running tag
- build ../ -t onedriveapp
- docker run -p 8080:8080 -d onedriveapp or docker run -p 8080:8080 -p 5005:5005 9bd2c58d1eb5

debugging
- if we need to debug: docker run -it 70bc802669a2 sh
- stop everything docker kill $(docker ps -q)
- docker images; docker image rm xxx
- docker ps -a ;  docker container prune    
  Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`


Maven tips
- pull dependencies for offline: mvn dependency:go-offline


curl 'http://localhost:8080/OneDriveManagement/sayHello' \
-H 'Connection: keep-alive' \
-H 'DNT: 1' \
-H 'Upgrade-Insecure-Requests: 1' \
-H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36' \
-H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9' \
-H 'Sec-Fetch-Site: none' \
-H 'Sec-Fetch-Mode: navigate' \
-H 'Sec-Fetch-User: ?1' \
-H 'Sec-Fetch-Dest: document' \
-H 'Accept-Language: en-US,en;q=0.9' \
--compressed


https://login.microsoftonline.com/8d2ae17d-e352-493c-acaa-41720ca2ea9b/oauth2/v2.0/token
https://login.microsoftonline.com/8d2ae17d-e352-493c-acaa-41720ca2ea9b/oauth2/v2.0/token?client_id=88a3f5b1-e2c9-4721-b686-a92295c77b14&redirect_uri=http://localhost:8080/shcarr/login&client_secret=aR-e.5G7uxSOx1O68-wEP6_2yRQo91h2xH&grant_type=client_credentials&scope=https%3A%2F%2Fbrandyleeloo-my.sharepoint.com%2F.default