#FROM sbtscala/scala-sbt:graalvm-ce-21.2.0-java8_1.6.2_2.12.15
#
#WORKDIR /usr/app
#
#ADD ./ ./
#
#RUN sbt compile
#
#CMD  sbt "runMain test.App"

# sudo docker build .
# sudo docker run -p 50051:50051 37a023246bb0
# sudo docker create -p 50051:50051 37a023246bb0
# sudo docker start daa3fed9412d

FROM openjdk:11

ENV SBT_VERSION 1.6.2

RUN curl -L -o sbt-$SBT_VERSION.zip https://github.com/sbt/sbt/releases/download/v$SBT_VERSION/sbt-$SBT_VERSION.zip
RUN unzip sbt-$SBT_VERSION.zip -d ops

WORKDIR /usr/hello
COPY ./ ./

RUN  /ops/sbt/bin/sbt compile

CMD /ops/sbt/bin/sbt "runMain test.App"
