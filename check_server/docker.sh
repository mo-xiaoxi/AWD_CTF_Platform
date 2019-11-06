#!/bin/sh
docker run -d  -v `pwd`/webapps:/webapps  --name check_server -ti moxiaoxi/check_server