FROM java

RUN wget https://downloads.apache.org/lucene/java/8.5.2/lucene-8.5.2.tgz
RUN tar -xzf lucene-8.5.2.tgz
RUN rm lucene-8.5.2.tgz

COPY ./cs172 ./cs172
ENV CLASSPATH ./
ENTRYPOINT java cs172.TweetLucene
