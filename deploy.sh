#!/bin/bash
REPOSITORY=/home/jenkins-ssh

cd $REPOSITORY/deploy/

echo "### check running application... ###"

CURRENT_PID=$(pgrep -f vue-backboard)

echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
    echo "### Not found running application ###"
else
    echo "## Found application!! try to kill process $CURRENT_PID ###"
    kill -9 $CURRENT_PID
    sleep 3
fi
echo "### Ready to deploy ###"

JAR_NAME=$(ls $REPOSITORY/deploy | grep 'vue-backboard' | tail -n 1)

echo "### Source file Name: $JAR_NAME ###"

nohup java -jar $REPOSITORY/deploy/$JAR_NAME >> $REPOSITORY/deploy/nohup.out 2>&1 &
