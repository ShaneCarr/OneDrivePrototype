#!/bin/sh

# set up some logs
#echo "dw" > $HOME/logs/app.log.type
#echo "nginx" > $HOME/logs/nginx-access.log.type
#echo "nginx" > $HOME/logs/nginx-error.log.type

#monit -d 5 -c /etc/nginx.monit
JAVA_OPTS="$PRE_JAVA_OPTS $JAVA_OPTS $ADD_JAVA_OPTS"

# java -cp app.jar:own_libs/*:. server $HOME/app.yml
exec java $JAVA_OPTS $JAVA_ENTRY_CLASS_NAME server /data/app.yml
