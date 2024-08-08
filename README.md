# Feelog Backend

Feelog Backend는 사용자가 작성한 일기를 NAVER CLOVA의 텍스트 감정 인식 API를 이용해 감정을 분석하고, 그에 따른 긍정적인 피드백을 제공하는 서비스의 백엔드 애플리케이션입니다.

## 어플리케이션 목적
Feelog의 백엔드 애플리케이션은 사용자가 작성한 일기를 데이터베이스에 저장하고, 감정을 분석하여 적절한 피드백을 제공하는 역할을 합니다.

## 주요 기능
- **일기 저장 및 관리** : 사용자가 작성한 일기를 데이터베이스에 저장, 수정, 삭제합니다.
- **감정 분석** : NAVER CLOVA 감정 인식 API를 통해 일기의 감정을 분석합니다.
- **피드백 제공** : 감정 분석 결과에 따라 사용자에게 맞춤형 피드백을 제공합니다.
- **사용자 인증 및 관리** : 사용자의 회원가입 및 로그인을 처리하고 JWT를 사용하여 인증합니다.

## 기술 스택
- **프레임워크** : Spring Boot
- **언어** : Java
- **주요 라이브러리** :
  - `Spring Boot Starter Data JPA`
  - `Spring Boot Starter Web`
  - `Spring Security`
  - `JJWT`
  - `MySQL Connector`
  - `Lombok`
  - `NAVER CLOVA Sentiment API`

## 설치 및 실행
1. **리포지토리를 클론합니다.**
   ```bash
   git clone https://github.com/your-repo/feelog-backend.git
   cd feelog-backend
   ```

2. **필수 조건을 충족하는지 확인합니다.**
   - Java 17 이상
   - Maven 3.6 이상

3. **환경 변수를 설정합니다.**
   - `application.properties` 파일에 필요한 환경 변수를 설정합니다.
   ```properties
   # MySQL
   spring.datasource.url=jdbc:mysql://localhost:3306/feelog?useSSL=false&characterEncoding=UTF8&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=${DATABASE_PASSWORD}
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

   spring.jpa.hibernate.ddl-auto=update

   spring.jpa.open-in-view=false

   # Naver CLOVA Sentiment
   clova.api.key-id=${CLOVA_API_KEY_ID}
   clova.api.secret=${CLOVA_API_SECRET}
   clova.api.url=${CLOVA_API_URL}

   # JWT
   app.jwtSecret=${JWT_SECRET_KEY}

   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   ```

4. **애플리케이션을 실행합니다.**
   ```bash
   mvn spring-boot:run
   ```

## API 엔드포인트
- **일기 작성** : `POST /diaries`
- **일기 삭제** : `DELETE /diaries/{diaryId}`
- **일기 수정** : `PUT /diaries/{diaryId}`
- **모든 일기 조회** : `GET /diaries`
- **특정 사용자의 일기 조회** : `GET /diaries/user/{userId}`
- **감정 데이터 조회** : `GET /diaries/{diaryId}/emotions`
- **피드백 조회** : `GET /diaries/{diaryId}/feedback`

## 라이센스
이 프로젝트는 MIT 라이센스에 따라 라이센스가 부여됩니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참고하십시오.

## 연락처

질문이나 문제가 있는 경우 언제든지 연락주세요.

- **Author** : Chae-rim Yoon
- **Email** : cofla226@naver.com
