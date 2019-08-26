FROM golang:1.12-alpine
WORKDIR /go/src/
COPY . .
RUN cp my.cnf /etc/my.cnf; \
    cp goenv.sh /etc/profile.d/goenv.sh; \
    cp -f motd /etc/motd; \
    \
    cd /go/bin/ && \
    go build /go/src/gotsctf2018/vendor/github.com/beego/bee; \
    apk add --no-cache python bash vim openssh-server openssh-sftp-server sudo mysql mysql-client; \
    \
    adduser -D -h /home/ctf ctf; \
    chown ctf:ctf -R /go/; \
    chown root:root /go/src/gotsctf2018/flag;
RUN ln -s /go/src/gotsctf2018/startwebserver.sh /home/ctf/startwebserver.sh; \
    rm -rf /var/cache/apk/* tmp my.cnf run.sh goenv.sh Dockerfile motd docker.sh build_images.sh flag.py;

EXPOSE 8080
EXPOSE 22
CMD ["/tmp/run.sh"]

