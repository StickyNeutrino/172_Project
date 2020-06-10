FROM maven

ADD https://downloads.apache.org/lucene/java/8.5.2/lucene-8.5.2.tgz ./
RUN tar -xzf lucene-8.5.2.tgz
RUN rm lucene-8.5.2.tgz

COPY ./TweetLucene /usr/app/TweetLucene
WORKDIR /usr/app/TweetLucene
RUN mvn clean install

ENTRYPOINT java -cp target/TweetLucene-1.0-SNAPSHOT.jar cs172.project.TweetLucene.App
