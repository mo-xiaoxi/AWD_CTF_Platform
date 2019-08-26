#!/bin/sh
chmod 777 /tmp/

if [ -d /app/mysql ]; then
  echo "[i] MySQL directory already present, skipping creation"
else
  if [ "$CTF_USER_PASSWORD" = "" ]; then
    CTF_USER_PASSWORD=moxiaoxi666
    echo "ctf:$CTF_USER_PASSWORD" | chpasswd
    echo "[i] ctf user Password: $CTF_USER_PASSWORD"
  fi

  ssh-keygen -A

  echo "[i] MySQL data directory not found, creating initial DBs"

  mysql_install_db --user=root > /dev/null

  if [ "$MYSQL_ROOT_PASSWORD" = "" ]; then
    MYSQL_ROOT_PASSWORD=111111
    echo "[i] MySQL root Password: $MYSQL_ROOT_PASSWORD"
  fi

  MYSQL_DATABASE=""
  MYSQL_USER=""
  MYSQL_PASSWORD=""

  if [ ! -d "/run/mysqld" ]; then
    mkdir -p /run/mysqld
  fi

  tfile=`mktemp`
  if [ ! -f "$tfile" ]; then
      return 1
  fi

  cat << EOF > $tfile
USE mysql;
FLUSH PRIVILEGES;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY "$MYSQL_ROOT_PASSWORD" WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' WITH GRANT OPTION;
UPDATE user SET password=PASSWORD("") WHERE user='root' AND host='localhost';
EOF
  cat /go/src/gotsctf2018/gotsctf.sql >> $tfile

  if [ "$MYSQL_DATABASE" != "" ]; then
    echo "[i] Creating database: $MYSQL_DATABASE"
    echo "CREATE DATABASE IF NOT EXISTS \`$MYSQL_DATABASE\` CHARACTER SET utf8 COLLATE utf8_general_ci;" >> $tfile

    if [ "$MYSQL_USER" != "" ]; then
      echo "[i] Creating user: $MYSQL_USER with password $MYSQL_PASSWORD"
      echo "GRANT ALL ON \`$MYSQL_DATABASE\`.* to '$MYSQL_USER'@'%' IDENTIFIED BY '$MYSQL_PASSWORD';" >> $tfile
    fi
  fi

  /usr/bin/mysqld --user=root --bootstrap --verbose=0 < $tfile
  rm -f $tfile

fi

/usr/bin/mysqld --user=root &
/usr/sbin/sshd

bash -c 'cd /go/src/gotsctf2018 && sleep 5 && sudo -u ctf GOPATH="/go" bee run ' &
rm -rf /go/src/gotsctf2018/static/uploads
ln -s  ../../ /go/src/gotsctf2018/static/uploads
chmod 777 /go/src/gotsctf2018/static/uploads
chmod 755 /go/src/gotsctf2018
ln -s  /go/src/gotsctf2018/flag /flag
python /tmp/flag.py  &  2>&1 1>/dev/null
sleep 2
rm -rf /tmp
exec /bin/bash