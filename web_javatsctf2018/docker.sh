#!/bin/sh
cp run.sh tmp/
cp flag.py tmp/
docker run -p {web_out_port}:8080  -p {ssh_port}:22  -v /var/lib/mysql   -v `pwd`/tmp:/tmp  -v `pwd`/javatsctf2018:/opt/source/ -d --name {team_name}   -ti wulasite/jmmt:latest /tmp/run.sh