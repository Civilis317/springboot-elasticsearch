FROM docker.elastic.co/elasticsearch/elasticsearch:6.4.0

COPY --chown=elasticsearch:elasticsearch elasticsearch.yml /usr/share/elasticsearch/config/
COPY --chown=elasticsearch:elasticsearch jvm.options /usr/share/elasticsearch/config/
COPY --chown=elasticsearch:elasticsearch log4j2.properties /usr/share/elasticsearch/config/

EXPOSE 9200 9300
