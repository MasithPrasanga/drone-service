FROM amazoncorretto:21

RUN yum -y update

RUN mkdir -p /opt/workspace/drone-service/

WORKDIR /opt/workspace/drone-service/
COPY /var/jenkins_home/workspace/drone-service/build/libs/drone-service-1.0.0.jar .

RUN yum install -y curl

EXPOSE 8000

