# Spring Rest Api 만들기 프로젝트

### 0. 개요
- SpringBoot2 framework 기반에서 RESTful api 서비스를 Step by Step으로 만들어 나가는 프로젝트
- daddyprogrammer.org에서 연재 및 소스 Github 등록
    - https://daddyprogrammer.org/post/series/springboot2%EB%A1%9C-rest-api-%EB%A7%8C%EB%93%A4%EA%B8%B0/

### 1. 개발환경
- Java 8~11
- SpringBoot 2.x
- SpringSecurity 5.x
- JPA, H2
- Intellij Community

### 2. 프로젝트 실행
- H2 database 설치
    - https://www.h2database.com/html/download.html
- intellij lombok 플러그인 설치
    - Preferences -> Plugins -> Browse repositories... -> search lombok -> Install "IntelliJ Lombok plugin"
- Enable annotation processing
    - Preferences - Annotation Procesors - Enable annotation processing 체크
- build.gradle에 lombok 추가(Git을 받은경우 이미 추가되어있음)
    - compileOnly 'org.projectlombok:lombok:1.16.16'
- 실행
    - Run -> SpringBootApiApplication
- Swagger
    - http://localhost:8080/swagger-ui.html

### 3. 목차
- SpringBoot2로 Rest api 만들기(1) – Intellij Community에서 프로젝트생성
    - Document
        - https://daddyprogrammer.org/post/19/spring-boot1-start-intellij/
- SpringBoot2로 Rest api 만들기(2) – HelloWorld
    - Document
        - https://daddyprogrammer.org/post/41/spring-boot2-helloworld/
- SpringBoot2로 Rest api 만들기(3) – H2 Database 연동
    - Document
        - https://daddyprogrammer.org/post/152/spring-boot2-h2-database-intergrate/
    - Git
        - https://github.com/codej99/SpringRestApi/tree/feature/h2
- SpringBoot2로 Rest api 만들기(4) – Swagger API 문서 자동화
    - Document
        - https://daddyprogrammer.org/post/313/swagger-api-doc/
    - Git
        - https://github.com/codej99/SpringRestApi/tree/feature/swagger
- SpringBoot2로 Rest api 만들기(5) – API 인터페이스 및 결과 데이터 구조 설계
    - Document
        - https://daddyprogrammer.org/post/404/spring-boot2-5-design-api-interface-and-data-structure/
    - Git
        - https://github.com/codej99/SpringRestApi/tree/feature/api-structure
- SpringBoot2로 Rest api 만들기(6) – ControllerAdvice를 이용한 Exception처리
    - Document 
        - https://daddyprogrammer.org/post/446/spring-boot2-5-exception-handling/
    - Git
        - https://github.com/codej99/SpringRestApi/tree/feature/controller-advice
- SpringBoot2로 Rest api 만들기(7) – MessageSource를 이용한 Exception 처리
    - Document
        - https://daddyprogrammer.org/post/499/springboot2-message-exception-handling-with-controlleradvice/
    - Git
        - https://github.com/codej99/SpringRestApi/tree/feature/messagesource
- SpringBoot2로 Rest api 만들기(8) – SpringSecurity를 이용한 인증 및 권한부여
    - Document
        - https://daddyprogrammer.org/post/636/springboot2-springsecurity-authentication-authorization/
    - Git
        - https://github.com/codej99/SpringRestApi/tree/feature/security
- SpringBoot2로 Rest api 만들기(9) – Unit Test
    - Document
        - https://daddyprogrammer.org/post/938/springboot2-restapi-unit-test/
    - Git
        - https://github.com/codej99/SpringRestApi/tree/feature/junit-test