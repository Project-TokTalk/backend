# TokTalk
스마트인재개발원에서 주관하는 실전프로젝트에서 사용한 서버코드입니다.
Spring Boot(gradle)를 활용하여 외국어 챗봇 서비스의 대시보드를 구현하였습니다.

## 📟 프로젝트 소개
"LLM와 번역 API를 이용한 공공기관 외국어 챗봇 서비스"를 주제로 한 프로젝트입니다. 외국인들의 민원처리를 수월하게 하기 위한 챗봇을 제작해보았습니다.

## 🕒 개발 기간
- 2024.02.15(목) ~ 2024.04.15(월)
- 계획 수립
- 요구사항 분석
- 설계 (DB, UI/UX, Web, 데이터 모델링)
- 기획 발표
- 구현
- 서비스 테스트
- 최종 발표

## 👯‍♀️ 개발자 소개
- 손승아 : 팀장, Server 개발자, AWS 배포
- 이주명 : 데이터 분석, Backend
- 방찬혁 : Frontend, UI/UX Designer
- 엄다은 : Backend
- 임해솔 : 데이터 분석, 챗봇 제작

---
손승아 : <https://github.com/sonseungah>

이주명 : <https://github.com/audwndl>

방찬혁 : <https://github.com/hyuk3001/portfolio>

엄다은 : <https://github.com/hummingbr/portfolio>

임해솔 : <https://github.com/loseahmil>

## 🖥 개발환경
- Version : Java 17
- IDE : VSCODE
- Framework : SpringBoot 3.2.4
- ORM : JPA

## 🛠 기술 스택
- Server : AWS EC2
- DataBase : MySQL
- WS/WAS : Tomcat
- 아이디어 회의 : Notion

## 📜 프로젝트 아키텍쳐

### 시스템 아키텍처
![시스템 아키텍처](https://github.com/Project-TokTalk/backend/blob/main/KakaoTalk_20240413_105255582.png)

### AWS 설계
![AWS 아키텍쳐](https://github.com/Project-TokTalk/backend/blob/main/AWS.png)

### 챗봇 설계
![챗봇 설계](https://github.com/Project-TokTalk/backend/blob/main/%EC%B1%97%EB%B4%87%20%EC%84%A4%EA%B3%84.png)

## 📌 주요 기능
- 외국어 챗봇
  - 휴대폰 화면 기준으로 제작하였다.
  - 질문이 들어오면 Python에서 해당 질문과 DB에 저장된 질문의 유사도 분석을 통해 가장 비슷한 질문에 대한 답변을 React로 보낸다.
  - 로그인 후 챗봇 좌측 상단 버튼 클릭 시 해당 휴대폰 번호로 질문한 모든 내역을 확인 할 수 있다.
  - 현재 창업 관련 질문에 한정하여 진행하였다.
- 대시보드 기능
  - PC 화면 기준으로 제작하였다.
  - 회원가입 시 입력한 정보들을 바탕으로 그래프를 제작하여 대시보드 메인에서 확인할 수 있고 필요 시 csv∙png 등의 파일로 다운로드 가능하다.
  - 사용자들의 챗봇 이력을 확인할 수 있다.
  - 챗봇이 응답 불가능한 질문들을 모아 확인 가능하고 그에 맞는 답변을 관리자가 작성해 추가할 수 있다.
  - 회원정보 관리가 가능하다.
  - 시나리오 관리가 가능하다.

 ![로그인화면](https://github.com/Project-TokTalk/backend/blob/main/%EB%A1%9C%EA%B7%B8%EC%9D%B8%ED%99%94%EB%A9%B4.jpg)
 ![챗봇 화면](https://github.com/Project-TokTalk/backend/blob/main/%EC%B1%97%EB%B4%87%ED%99%94%EB%A9%B4.jpg)
 ![대시보드 화면](https://github.com/Project-TokTalk/backend/blob/main/%EB%8C%80%EC%8B%9C%EB%B3%B4%EB%93%9C1.png)
 
## ⚠ 트러블 슈팅
- NoneType 에러
  - SpringBoot의 Entity 생성 후 챗봇에서 에러 확인
    ![에러 사진](https://github.com/Project-TokTalk/backend/blob/main/nonetype%20%ED%8A%B8%EB%9F%AC%EB%B8%94%EC%8A%88%ED%8C%85.png)
  - Entity 생성하는 과정에서 쿼리문의 auto_increment를 삭제하는 것을 확인
  - 파이썬에서 오늘 날짜 받아서 sql문 안에 변수로 넣고 execute 하는 것으로 해결함

- CORS 에러
  - AWS 배포 이후 CORS에러가 뜨는 것을 확인
    
    ![CORS 에러](https://github.com/Project-TokTalk/backend/blob/main/CORS%EC%97%90%EB%9F%AC.png)
  - 서버에 빌드하는 과정에서 주소가 localhost -> AWS로 설정한 탄력적ip를 가져오는 것을 확인
  - React Proxy에 주소값 변경으로 해결

 ## ⚙ API
 - API 명세서 : [https://www.notion.so/API-88c11926138346b181864fcf70d73ce1?pvs=4](https://adorable-society-1a9.notion.site/API-88c11926138346b181864fcf70d73ce1?pvs=4)

 - 프론트 git 주소 : https://github.com/Project-TokTalk/React.git
