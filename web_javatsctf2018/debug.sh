#!/bin/sh
cp run.sh tmp/
cp flag.py tmp/
docker run -p 8080:8080  -p 2201:22  -v /var/lib/mysql   -v `pwd`/tmp:/tmp  -v `pwd`/javatsctf2018:/opt/source/ -ti wulasite/jmmt:latest /tmp/run.sh