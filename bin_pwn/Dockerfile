FROM ubuntu:16.04
MAINTAINER WangTsiAo (wang.qi.ao@qq.com)
RUN sed -i "s/http:\/\/archive.ubuntu.com/http:\/\/mirrors.tuna.tsinghua.edu.cn/g" /etc/apt/sources.list && \
    apt-get update && apt-get dist-upgrade -y && \
    apt-get install socat -y && \
    apt-get install python -y && \
    apt-get install openssh-server -y
EXPOSE 8888
EXPOSE 22
COPY flag /flag
COPY pwn /pwn
CMD ["/tmp/run.sh", "8888", "/pwn"]

