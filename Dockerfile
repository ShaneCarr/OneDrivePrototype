FROM openjdk:8

#going to wokr in a data diectory.
COPY target/app.jar /data/
COPY app.yml /data/
WORKDIR /data
#RUN java -jar app.jar db migrate app.yml

# i 'll not need own libs and libs since i am using sharde for convenience
ARG APP_FILENAME=app.jar
ARG OWN_LIBS_NAME=own_libs
ARG LIBS_NAME=libs

# example -cp (class path) app.jar:libs/*:own_libs/*:.
# app jar, ownlibs, current path and libs. note data is '.' the last item.
# ned to add data to class path hence . so when we get to the  the CMD [...] below everything needed is in the classpath.
ENV ADD_JAVA_OPTS="-cp ${APP_FILENAME}:${LIBS_NAME}/*:${OWN_LIBS_NAME}/*:."
ENV JAVA_ENTRY_CLASS_NAME=onedrive.OnedriveApplication


# this will run by default if a command isn't specified when the container starts (exec).
#i tend to like the script approach. its easier to edit and test rather than stuffing everying in a docker file. It's also more
#reuesble.
CMD ["/usr/bin/start.sh"]
COPY ./docker/start.sh /usr/bin/start.sh
RUN chmod +x /usr/bin/start.sh
COPY ./docker/app.yml  ./app.yml
#CMD java -jar app.jar server app.yml
#for debugging
EXPOSE 8080
EXPOSE 9090
EXPOSE 8081
EXPOSE 5005
