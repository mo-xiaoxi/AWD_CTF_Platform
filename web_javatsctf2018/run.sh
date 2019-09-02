#!/bin/bash
/etc/tomcat-9.0.24/bin/startup.sh && mv /etc/tomcat-9.0.24/webapps/ROOT /etc/tomcat-9.0.24/webapps/ROOT.bak && mkdir -p /var/run/mysqld && chown -R mysql:mysql /var/lib/mysql /var/run/mysqld && cd /opt/ &&  service mysql start  && mysql -uroot -pJmtserver@Hello123 -e "CREATE DATABASE chapter2 CHARACTER SET utf8 COLLATE utf8_general_ci;"  && mysql -uroot -pJmtserver@Hello123 chapter2 < /opt/source/chapter2.sql && mysql -uroot -pJmtserver@Hello123 -e "GRANT ALL PRIVILEGES ON chapter2.* TO 'tomcat'@'%' IDENTIFIED BY 'tomcat';" && cd source && mvn package && cp /opt/source/target/charpter2-1.0-SNAPSHOT.war /etc/tomcat-9.0.24/webapps/ROOT.war
python /tmp/flag.py  &  2>&1 1>/dev/null
chmod -R 777 /etc/tomcat-9.0.24/webapps/ /tmp /opt/source/
useradd ctf
echo ctf:moxiaoxi666 | chpasswd
sleep 2
apt install ssh -y
service ssh start
rm -rf /tmp/run.sh /tmp/flag.py
if [ -x "extra.sh" ]; then
./extra.sh
fi
/bin/bash