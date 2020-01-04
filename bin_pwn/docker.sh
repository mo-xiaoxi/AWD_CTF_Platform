#!/bin/sh
cp run.sh tmp/
cp flag.py tmp/
docker run -p {out_port}:8888  -p {ssh_port}:22 -v `pwd`/tmp:/tmp -d  --name {team_name} -ti wangtsiao/pwn

