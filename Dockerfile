FROM java:8
WORKDIR /
ADD /var/lib/jenkins/.m2/repository/com/abcplusd/masterapi/master-api/0.0.1-SNAPSHOT/master-api-0.0.1-SNAPSHOT.jar master-api-0.0.1-SNAPSHOT.jar
EXPOSE 8090
CMD java - jar master-api-0.0.1-SNAPSHOT.jar