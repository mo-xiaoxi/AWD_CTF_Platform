FROM nickistre/ubuntu-lamp
RUN apt-get update && apt-get dist-upgrade -y
ADD apache2.conf /etc/apache2/apache2.conf
EXPOSE 80
EXPOSE 22
CMD ["/tmp/run.sh"]