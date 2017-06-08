##在/etc/hosts文件中添加对peer1和peer2的转换

127.0.0.1 peer1
127.0.0.1 peer2


##通过spring.profiles.active属性来分别启动peer1和peer2

java -jar eureka-server-1.0.0.jar --spring.profiles.active=peer1
java -jar eureka-server-1.0.0.jar --spring.profiles.active=peer2

##服务注册与发现

spring.application.name=compute-service
server.port=2222
eureka.client.serviceUrl.defaultZone=http://peer1:1111/eureka/,http://peer2:1112/eureka/

##若有多个节点 则两两互相注册