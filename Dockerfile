FROM openjdk:oraclelinux8
VOLUME /tmp
WORKDIR /doctors
COPY target/*.jar doctors.jar
EXPOSE 8448
ENTRYPOINT ["java","-jar","doctors.jar"]