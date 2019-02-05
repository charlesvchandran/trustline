FROM java:8
EXPOSE 8080
ADD /target/trustline-0.0.1-SNAPSHOT.jar trustline.jar
ENTRYPOINT ["java","-jar","trustline.jar"]