#!/bin/sh
service ssh start
a2enmod rewrite
service apache2 start
rm /var/www/html/index.html /var/www/html/phpinfo.php
chown www-data:www-data /var/www/html/* -R
python /tmp/flag.py  &  2>&1 1>/dev/null
cd /var/www/html
useradd ctf
echo ctf:moxiaoxi666 | chpasswd
sleep 2
rm -rf /tmp
if [ -x "extra.sh" ]; then
./extra.sh
fi
/bin/bash