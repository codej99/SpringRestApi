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
    
### 3. DDL
create table user (
       msrl bigint not null auto_increment,
        name varchar(100) not null,
        password varchar(100),
        provider varchar(100),
        uid varchar(50) not null,
        primary key (msrl)
    ) engine=InnoDB;
    
create table user_roles (
       user_msrl bigint not null,
        roles varchar(255)
    ) engine=InnoDB;
    
    
alter table user 
add constraint UK_a7hlm8sj8kmijx6ucp7wfyt31 unique (uid);

alter table user_roles 
       add constraint FKel3d4qj41g0sy1mtp4sh055g7 
       foreign key (user_msrl) 
       references user (msrl);
       
### 4. 목차
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
- SpringBoot2로 Rest api 만들기(10) – Social Login kakao
    - Document
        - https://daddyprogrammer.org/post/1012/springboot2-rest-api-social-login-kakao/
    - Git
        - https://github.com/codej99/SpringRestApi/tree/feature/social-kakao
- SpringBoot2로 Rest api 만들기(11) – profile을 이용한 환경별 설정 분리
    - Document
        - https://daddyprogrammer.org/post/2421/springboot2-seperate-environment-by-profile/
    - Git
        - https://github.com/codej99/SpringRestApi/tree/feature/seperate-profile
 - SpringBoot2로 Rest api 만들기(12) – Deploy & Nginx 연동 & 무중단 배포 하기
    - Document
        - https://daddyprogrammer.org/post/2445/springboot2-blue-green-deploy-nginx/
    - Git
        - https://github.com/codej99/SpringRestApi/tree/feature/gracefullyshutdown
  - SpringBoot2로 Rest api 만들기(13) – Jenkins 배포(Deploy) + Git Tag Rollback
    - Document
        - https://daddyprogrammer.org/post/2697/springboot2-jenkins-deploy-gittag-rollback/
  - SpringBoot2로 Rest api 만들기(14) – 간단한 JPA 게시판(board) 만들기
    - Document
        - https://daddyprogrammer.org/post/2695/springboot2-simple-jpa-board/
    - Git
        - https://github.com/codej99/SpringRestApi/tree/feature/board
  - SpringBoot2로 Rest api 만들기(15) – Redis로 api 결과 캐싱(Caching)처리
    - Document
        - https://daddyprogrammer.org/post/3870/spring-rest-api-redis-caching/
    - Git
        - https://github.com/codej99/SpringRestApi/tree/cache-data-redis
