#!/bin/sh
cp run.sh tmp/
cp flag.py tmp/
docker run  -v /var/lib/mysql  -v `pwd`/tmp:/tmp -p {web_out_port}:8080 -p {other_port1}:8088  -p {ssh_port}:22  -d --name {team_name}  -ti moxiaoxi/gotsctf2018
