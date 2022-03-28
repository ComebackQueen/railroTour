# 내일로 투어(Railro Tour)

------

**요약 : 코레일 여행 상품 '내일로'의 편의 제공 시스템**

**프로젝트 기간 : 2018/03 ~ 2018/10**

**Skils: Java(JSP, Android), Cubrid(DB), Javascript, Tomcat 7.0, Exerd**

**※ 화면설계도 :** https://docs.google.com/presentation/d/1OqoI5HWrImnFQ3qwQEKGmsC0WarIX-CH/edit?usp=sharing&ouid=107148269951797787950&rtpof=true&sd=true

**DB설계도 :** https://docs.google.com/presentation/d/1h6BRSW4Zaiv1woa_E1JpDcHd3g2A10-P/edit?usp=sharing&ouid=107148269951797787950&rtpof=true&sd=true

**프로젝트 일정**

![프로젝트 일정](https://user-images.githubusercontent.com/87848564/160341350-4d375d7c-1823-48fe-b9d7-6a8fb3723cbd.png)

---

## Description



### Summary

![title](https://user-images.githubusercontent.com/87848564/160341975-e837a3bb-cfe5-43e4-bc71-39ccde874c5d.JPG)

- 내일로 여행 시 계획부터 여행까지에 있어 불편한 점들을 개선해주는 시스템으로 구성
- 사용자의 내일로 노트(여행 계획)를 웹, 앱에서 언제 어디서든 볼 수 있음
- 앱에서 사용자의 주변 내에 음식점과 관광지를 한 눈에 파악 가능



### Jaein's Role

- 시스템 구성도, 기능 및 DB 설계도 설계
- 웹 백 앤드 : 공지사항, 로그인/회원가입, 통합검색, 마이페이지, 내일로 노트 STEP1(여행 계획) 구현



---

## About Project



### Web

- **로그인/회원가입/자동로그인** 구현
- 전라도 음식점, 관광지 공공데이터와 Json API를 활용한 **'통합검색'** 구현
- 사용자 선호도를 파악하기 위한 **'Top 100'**과 **'실시간 검색어'** 구현
- 사용자 편의성을 고려하여 Step1, Step2로 나누어서 여행 계획을 세울 수 있는 **'내일로노트'** 구현
- CRUD를 활용한 **'공지사항'** 게시판 구현



### Application

- **로그인/회원가입** 구현
- 사용자 선호도를 파악하기 위한 **'Top 100'** 구현
- 사용자들의 선호도가 높은 **'추천코스'** 구현
- 전라도 음식점, 관광지 공공데이터와 Json API를 활용한 **'통합검색'** 구현
- 사용자 편의성을 고려하여 Step1, Step2로 나누어서 여행 계획을 세울 수 있는 **'내일로노트'** 구현
- 사용자 위치 좌표의 3km 반경 내 음식점, 관광지를 마크로 보여주는 **'내 주변'** 구현



### 내일로 노트

- 로그인 후 이용 가능하도록 구현
- 'Step 1'은 사용자가 여행하고 싶은 루트를 네이버 지도를 보여주어 쉽게 정하여 갈 수 있도록 제작
  - 'Step 1'에서 'Step 2'로 넘어갈 때 노트제목, 출발일, 여행일수/인원, 여행테마를 지정하여 넘어가도록 구현

- 'Step 2'는 상세 일정 만들기 파트
  - 'Step 1'에서 지정하였던 도시마다 음식점, 관광지를 쉽게 검색 및 지도를 통해 루트를 보여줌으로써 직관적으로 구현(지도 클러스터링 활용)
  - 정해놨던 루트를 드래그 앤 드롭을 통해 바꿀 수도 있음
  - 일정이 완성되면 DB에 업로드  




### 내 주변

- 사용자 주변의 음식점, 관광지를 직관적으로 보이도록 구현
- 해당 데이터를 클릭하면 데이터에 대한 이름, 주소, 전화번호 등 간략한 정보를 보여줌
- 간략한 정보를 한번더 클릭하게 되면 상세정보 보기 화면으로 이동

 

---

## Results

### 시스템 구성도

![시스템구성도](https://user-images.githubusercontent.com/87848564/160342081-e4138982-84b3-4532-96ec-85b6b0f2a5e1.png)





### 통합검색

- **검색화면**

![search](https://user-images.githubusercontent.com/87848564/160345008-9507d89b-6886-4cf9-b582-6ddffd7abf8f.gif)


- **상세정보**

![detail](https://user-images.githubusercontent.com/87848564/160346907-bbc63645-3286-41b2-9d22-4ebe906442d1.gif)





### 내일로 노트 Step 1

![Step1](https://user-images.githubusercontent.com/87848564/160348912-4370dacd-1d66-4c41-a9ec-a9d8f3a96167.gif)



### 내일로 노트 Step 2

![Step2](https://user-images.githubusercontent.com/87848564/160349114-a0893e2b-8092-4495-b017-e48247e55e9b.gif)



### 내 주변

![map](https://user-images.githubusercontent.com/87848564/160349614-cc11df13-a32b-4e94-bf43-487cad13b9ae.JPG)


![detail](https://user-images.githubusercontent.com/87848564/160349885-d8c22438-0dd4-434a-85b0-b65dd1a4616e.JPG)
