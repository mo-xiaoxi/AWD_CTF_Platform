#!/bin/sh
kill -9 `ps -a | grep bee | awk '{print $1}' | paste -sd ' '` 
kill -9 `ps -a | grep gotsctf2018 | awk '{print $1}' | paste -sd ' '` 

cd /go/src/gotsctf2018
bee run
