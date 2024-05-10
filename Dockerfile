FROM openjdk:oraclelinux8
VOLUME /tmp
WORKDIR /doctors
COPY target/*.jar doctors.jar
EXPOSE ----
ENTRYPOINT ["java","-jar","doctors.jar"]