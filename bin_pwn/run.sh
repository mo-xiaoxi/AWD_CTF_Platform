#!/bin/sh
service ssh start
chmod 777 /pwn
chmod 744 /flag
chmod 700 /tmp/*
python /tmp/flag.py & 2>&1 1>/dev/null
sleep 2
# rm -rf /tmp
useradd ctf
echo ctf:moxiaoxi666 | chpasswd
runuser -u ctf socat TCP-LISTEN:$1,reuseaddr,fork EXEC:$2 & 2>&1 1>/dev/null
if [ -x "extra.sh" ]; then
./extra.sh
fi
/bin/bash
