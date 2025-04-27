# BUSICARD
명함 관리 파일럿 프로젝트(CLOVA OCR API)

## Spec
- Spring Boot 2.7.18
- Java 21
- Maven Build
- React 18 + Vite
- CLOVA OCR
- H2 Database
- JDBC MyBatis
- Docker


## Docker 환경 구성
아래 Container Registry 를 참고하여 테스트

### BUSICARD API 서비스
- https://github.com/users/Hotsse/packages/container/package/busicard-api
``` bash
docker pull ghcr.io/hotsse/busicard-api:latest
docker run --platform=linux/amd64 -p 8080:8080 ghcr.io/hotsse/busicard-api:latest
```

### BUSICARD Frontend 서비스
- https://github.com/users/Hotsse/packages/container/package/busicard-fe
``` bash
docker pull ghcr.io/hotsse/busicard-fe:latest
docker run --platform=linux/amd64 -p 80:80 ghcr.io/hotsse/busicard-fe:latest
```


## 기타
1. OCR 서비스 Credentials 관련
OCR 서비스 Credentials 는 아래와 같은 구조로 이루어져 있습니다.

```json
{"baseUrl":"YOUR_BASE_URL","uri":"YOUR_URI","secretKey":"YOUR_SECRET_KEY"}
```

public repository 인 관계로 악의적인 API 호출이 발생할 수 있어 credentials 을 기본적으로 제공하지 않으니, docker 환경에서 OCR 테스트 해보고자 하신다면 개인 연락처로 연락 바랍니다.



## 서비스 미리보기

<img width="250" alt="image" src="https://github.com/user-attachments/assets/ea6dfc0d-1d8b-454c-b00c-05b8527b1da5" />
<img width="250" alt="image" src="https://github.com/user-attachments/assets/827959c7-24c6-4880-8952-b54a0458d988" />
<img width="250" alt="image" src="https://github.com/user-attachments/assets/45598993-3632-48f9-96b2-a937227e68b6" />

- 부서, 사원정보 데이터 관리
- Java GUI 기반으로 사원정보 데이터로 명함 이미지 생성 기능
- Naver CLOVA OCR API 기반으로 명함 내 정보 자동 분석 기능
