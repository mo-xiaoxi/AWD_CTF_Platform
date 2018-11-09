FROM python:3
RUN apt-get update && apt-get upgrade -y && apt-get install -y \
libsqlite3-dev
ENV PYTHONUNBUFFERED 1
ADD webapps /webapps
WORKDIR /webapps
RUN pip install -r requirements.txt
CMD ["/webapps/run.sh"]