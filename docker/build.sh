mvn clean; mvn package
docker stop $(docker ps -a)
docker kill $(docker ps )
docker rm $(docker ps -a )
#docker rmi $(docker images)
docker image rm od
cd docker
docker build ../ -t od
docker run -p 8080:8080 -p 5005:5005 od
