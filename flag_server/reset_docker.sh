#!/bin/sh
docker stop flag_server
docker rm flag_server
docker run -p 9090:8000  -v `pwd`/webapps:/webapps -d  --name flag_server -ti moxiaoxi/flag_server