# Musinsa Product API

## 1. 구현 범위에 대한 설명

이 프로젝트는 Musinsa의 상품 정보를 관리하고 조회하는 API를 구현합니다. 주요 기능은 다음과 같습니다:

- Jakarta EE, Spring Data JPA, Spring MVC, Lombok, 그리고 Java SDK version 17를 사용하여 Spring Boot 애플리케이션을 설정함.
- 템플릿 메서드  패턴을 사용하여 애플리케이션의 CRUD 로직을 간결하고 유연하게 구현함. 어노테이션 기반의 검증 전략을 사용하여, 필드별로 적합한 검증 전략을 선택할 수 있게 함.
- @RestControllerAdvice를 사용하여 전역 예외 처리를 구현함. 이 패턴은 예외 처리 로직을 중앙 집중화하여 코드 재사용성과 유지 관리성을 높임.
- 등록 삭제 수정은 CommonResponseModel 객체 내에 적절한 HTTP 상태 코드와 함께 반환됨.

## 2. 코드 빌드, 테스트, 실행 방법

### 요구사항

- Java 17 이상
- Gradle 6.8 이상
- h2 database

### H2 콘솔 접속
- JDBC URL: jdbc:h2:tcp://localhost/~/test
- User Name: sa
- Password: 1234

### Build

-  프로젝트를 빌드하려면 다음 명령어를 사용하십시오:

```bash
./gradlew build
```
- 통합테스트를 위한 intg 페키지, 단위테스트를 위한 unit 페키지로 구성되어 있습니다.

### Test

단위 테스트를 실행하려면 다음 명령어를 사용하십시오:

```bash
./gradlew test
```

### Run

애플리케이션을 실행하려면 다음 명령어를 사용하십시오:



```bash
./gradlew bootRun
```



## 3. API 문서
API 문서를 보려면 브라우저에서 Swagger UI에 접속할 수 있습니다
- http://localhost:8080/swagger-ui.html


## 4. 기타 추가 정보

### 데이터베이스 초기화
애플리케이션이 시작될 때 H2 인메모리 데이터베이스가 자동으로 초기화됩니다. 초기 데이터는 data.sql 파일에 정의되어 있습니다.

### Thymeleaf를 이용한 화면 표시
애플리케이션은 Thymeleaf를 사용하여 간단한 웹 UI를 제공합니다. 브라우저에서 /products 경로로 접근하면, JSON 응답과 표 형식으로 상품 정보를 확인할 수 있습니다.
- http://localhost:8080/products