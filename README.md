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
docker run -p 8080:8080 ghcr.io/hotsse/busicard-api:latest
```

### BUSICARD Frontend 서비스
- https://github.com/users/Hotsse/packages/container/package/busicard-fe
``` bash
docker pull ghcr.io/hotsse/busicard-fe:latest
docker run -p 80:80 ghcr.io/hotsse/busicard-fe:latest
```


## 기타
1. 인증파일 위치 및 샘플
- c:/storages/BUSICARD/credentials/OcrCredentials.json
```json
{"baseUrl":"YOUR_BASE_URL","uri":"YOUR_URI","secretKey":"YOUR_SECRET_KEY"}
```

