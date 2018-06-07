FROM java:8
WORKDIR /
ADD target/graphql-api.jar master-api-0.0.1-SNAPSHOT.jar
EXPOSE 8090
CMD java -jar master-api-0.0.1-SNAPSHOT.jar