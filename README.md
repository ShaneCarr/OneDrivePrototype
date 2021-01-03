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
