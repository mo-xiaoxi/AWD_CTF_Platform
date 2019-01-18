FROM python:3
RUN apt-get update && apt-get upgrade -y && apt-get install -y libsqlite3-dev,sqlite3
ENV PYTHONUNBUFFERED 1
ADD webapps /webapps
WORKDIR /webapps
RUN pip install -r requirements.txt
EXPOSE 8000
CMD ["/webapps/run.sh"]