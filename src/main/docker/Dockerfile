#FROM openjdk:8-jdk-alpine

#FROM microsoft/windowsservercore:latest

#ENV JAVA_PKG=server-jre-8u181-windows-x64.tar.gz \
#    JAVA_HOME=C:\\jdk1.8.0_181

#ADD $JAVA_PKG /

#RUN setx /M PATH %PATH%;%JAVA_HOME%\bin

#VOLUME /tmp
#ARG DEPENDENCY=target/dependency
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","app:app/lib/*","com.ripple.track.alicetrustline.AlicetrustlineApplication"]

#FROM microsoft/nanoserver:latest

#ENV JAVA_PKG=server-jre-8u181-windows-x64.tar.gz \
#    JAVA_HOME=C:\\jdk1.8.0_181

#ADD $JAVA_PKG /

#RUN setx /M PATH %PATH%;%JAVA_HOME%\bin



FROM oraclelinux:7-slim

MAINTAINER Charles V Chandran <charlesvc@gmail.com>

ENV JAVA_PKG=server-jre-8u*-linux-x64.tar.gz \
    JAVA_HOME=/usr/java/default

ADD $JAVA_PKG /usr/java/

RUN export JAVA_DIR=$(ls -1 -d /usr/java/*) && \
    ln -s $JAVA_DIR /usr/java/latest && \
    ln -s $JAVA_DIR /usr/java/default && \
    alternatives --install /usr/bin/java java $JAVA_DIR/bin/java 20000 && \
    alternatives --install /usr/bin/javac javac $JAVA_DIR/bin/javac 20000 && \
    alternatives --install /usr/bin/jar jar $JAVA_DIR/bin/jar 20000