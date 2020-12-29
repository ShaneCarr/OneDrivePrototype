FROM openjdk:8
COPY target/app.jar /data/
COPY app.yml /data/
WORKDIR /data
RUN java -jar app.jar db migrate app.yml
CMD java -jar app.jar server app.yml
EXPOSE 8080