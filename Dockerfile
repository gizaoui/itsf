FROM tomcat:10.1
RUN sed -i 's/8080/9090/' /usr/local/tomcat/conf/server.xml
EXPOSE 9090
ADD ./target/itsf.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]