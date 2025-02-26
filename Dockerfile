FROM tomcat:latest
COPY ems.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]