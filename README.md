# BUSICARD
명함 관리 파일럿 프로젝트(Spring Boot + Thymeleaf)

- Spring Boot 2.6.6
- Java 11
- Maven Build
- Thymeleaf
- CLOVAR OCR
- H2 Database
- JDBC MyBatis


1. 인증파일 위치 및 샘플
- c:/storages/BUSICARD/credentials/OcrCredentials.json
```json
{"baseUrl":"YOUR_BASE_URL","uri":"YOUR_URI","secretKey":"YOUR_SECRET_KEY"}
```
- c:/storages/BUSICARD/credentials/RomanizerCredentials.json
```json
{"clientId":"YOUR_CLIENTID","clientSecret":"YOUR_CLIENTSECRET"}
```


2. 명함 이미지 파일 위치
- c:/storages/BUSICARD/sample/card/card_ko.png
- c:/storages/BUSICARD/sample/card/card_en.png
