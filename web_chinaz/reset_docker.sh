#!/bin/sh
rm -rf tmp/*
rm -rf chinaz/*
cp -R ../web_chinaz/ chinaz/
cp run.sh tmp/run.sh
cp flag.py tmp/run.sh
docker stop {team_name}
docker rm {team_name}
docker run -p {web_out_port}:80  -p {ssh_port}:22 -v `pwd`/chinaz:/var/www/html -v `pwd`/tmp:/tmp -d  --name {team_name} -ti moxiaoxi/chinaz