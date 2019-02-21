本项目包含组建：
spring-cloud-config-client
spring-cloud-starter-netflix-eureka-server
spring-cloud-starter-openfeign
spring-boot-starter-web
spring-boot-starter-security
spring-boot-starter-actuator
spring-boot-starter-thymeleaf
lombok

1. 刷新配置
POST http://localhost:18001/actuator/refresh

2. 优雅停机[停机不会等待现有线程处理完成,直接会shutdown,存在一定风险]
POST http://localhost:18001/actuator/shutdown