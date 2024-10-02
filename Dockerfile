FROM openjdk:21-jdk-alpine
WORKDIR /opt/app
COPY target/karyawan-0.0.1-SNAPSHOT.jar karyawan.jar
EXPOSE 3000
ENTRYPOINT exec java $JAVA_OPTS -jar karyawan.jar
# ENTRYPOINT ["java","-jar","/app/karyawan.jar"]
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar karyawan.jar
