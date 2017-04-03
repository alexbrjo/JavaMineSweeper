# use java 8
FROM java:8 

# install maven
RUN apt-get update
RUN apt-get install -y maven

# install dependenices
ADD pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

# test and run
RUN ["mvn", "compile"]
RUN ["mvn", "test"]
