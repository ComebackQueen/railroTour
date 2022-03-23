<%@page import="net.member.db.MemberBean"%>
<%@page import="net.note.db.Note_Plans_List_Bean"%>
<%@page import="net.search.db.Tour_Food_Bean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String ID = null, PW = null;

Cookie cookies[] = request.getCookies();

if(cookies!=null) {
      for(int i = 0; i<cookies.length; i++){
         String name = cookies[i].getName();
         if(name.equals("ID")){
            ID = cookies[i].getValue();
         } 
         else if (name.equals("PW")){
            PW = cookies[i].getValue();
         }
      }
 }
   if(ID!=null&&PW!=null){
      session.setAttribute("ID", ID);
      session.setAttribute("PW", PW);
   }
   
   ArrayList<Tour_Food_Bean> Tour=(ArrayList<Tour_Food_Bean>) request.getAttribute("Tour");
   String keyword=(String) request.getAttribute("keyword");
   ArrayList<Tour_Food_Bean> Food=(ArrayList<Tour_Food_Bean>) request.getAttribute("Food");
   ArrayList<Note_Plans_List_Bean> Railro_Note=(ArrayList<Note_Plans_List_Bean>) request.getAttribute("Note");
   ArrayList<MemberBean> Member=(ArrayList<MemberBean>) request.getAttribute("Member");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><%=keyword %> : 통합검색</title>
    <link rel="stylesheet" href="./css/commen.css">
    <link rel="stylesheet" href="./css/style.css?ver=1">
    <link rel="stylesheet" href="./bxslide/dist/jquery.bxslider.css">
    <link rel="stylesheet" href="./css/Search_from.css?ver=3">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
    <script src="./bxslide/dist/jquery.bxslider.min.js">
    </script>

</head>
<body>
    <div id="wrap"> <!-- 전체를 감싸는 부분 -->
        <header><!--상단 메뉴 -->
            <section id="head-top"> <!-- 상단메뉴의 검은색 메뉴 -->
                <div class="section">
                    <div class="topnavi"> <!-- 메뉴명 -->
                        <ul>
                            <li>
                                <a href="./Main.me">홈으로</a>
                                <span></span>
                            </li>
                            <li>
                                <a href="#" id="favorite">즐겨찾기</a>
                                <span></span>
                            </li>
                            <li>
                                <%if (ID == null) {
                                   if(session.getAttribute("id")==null){%>
                                   		<a href="./MemberLogin.me">로그인</a>
                                   <%} else { %>
                                   		<a href="./MemberLogoutAction.me">로그아웃</a>
                        		<%} 
                                }else {%>
                                   		<a href="./MemberLogoutAction.me">로그아웃</a>
                                  <%}%>
                                <span></span>
                                </li>
                            <li>
								<%if(ID == null) {
                                   if(session.getAttribute("id")==null){%>
                                   		<a href="./MemberJoin1.me">회원가입</a>
                        			<%}
                                else{%>
                                   <a href="./MyPageHome.me">마이페이지</a>
                                <%}
                                }else {%>
                                	<a href="./MyPageHome.me">마이페이지</a>
                                   <%}%>
                                </li>
                        </ul>
                    </div>
                    
                    <div class="snsicon"> <!-- 상단아이콘 -->
                       <form action="./All_Search.se" method="get" id="search_form">
                           <input type="text" id="search_input" placeholder="통합검색" name="search_word">
                           <input type="submit" id="search_btn" value="검색">
                       </form>
                         <!-- <a href="#" class="sns1">안드로이드</a> -->
                        <a href="http://www.letskorail.com/" class="sns2" target="_blank">카페</a>
                        <a href="https://cafe.naver.com/hkct" class="sns3" target="_blank">코레일</a>
                    </div>
                </div>
            </section>
            <section id="head-bot">
                       <div class="section">
                    <div id="logo"> <!-- 로고 -->
                        <h1><a href="./Main.me">
                            <img src="./jpg/RailroTour%20LOGO.png" alt="">
                            </a>
                        </h1>
                    </div>
                    <nav>
                        <ul class="m-menu"><!--메인메뉴-->
                            <li class="list01 list" onmouseover="bgcolor(1)" onmouseout="removecolor(1)">
                                <a href="#" class="list_a1">내일로 소개</a>
                            </li>
                            <li class="list02 list" onmouseover="bgcolor(2)" onmouseout="removecolor(2)">
                                <a href="#" class="list_a2">TOP 100</a>
                            </li>
                            <li class="list03 list" onmouseover="bgcolor(3)" onmouseout="removecolor(3)">
                                <a href="#" class="list_a3">플래너</a>
                            </li>
                            <li class="list05 list" onmouseover="bgcolor(5)" onmouseout="removecolor(5)">
                                <a href="#" class="list_a5">고객센터</a>
                            </li>
                        </ul>
                    </nav>
                    <div class="s-menu">
                        <div class="section">
                           <div class="float">
                            <dl class="hoverbg1 hoverbg" onmouseover="bgcolor(1)" onmouseout="removecolor(1)">
<!--                                            <dt>지우지 말것</dt>-->
                                <dd><a href="./IntroRailro.me">내일로 소개</a></dd>
                                <!-- <dd><a href="#">발권지 혜택</a></dd> -->
                            </dl>
                            <dl class="hoverbg2 hoverbg" onmouseover="bgcolor(2)" onmouseout="removecolor(2)">
                                <dd><a href="./Top100_Search.se?contenttypeid=12&page_num=1">관광지</a></dd>
                                <dd><a href="./Top100_Search.se?contenttypeid=39&page_num=1">맛집</a></dd>
                                <dd><a href="./Top100_Note.se?page_num=1">내일로 노트</a></dd>
                                <dd><a href="./Top100_Member.se?page_num=1">내일러</a></dd>
                            </dl>
                            <dl class="hoverbg3 hoverbg" onmouseover="bgcolor(3)" onmouseout="removecolor(3)">

                                <dd><a href="./Railro_Note_Step1.pl">새 플래너 작성</a></dd>
                                <dd><a href="./Note_Plans_List.pl">내 플래너 목록</a></dd>
                            </dl>
                            <dl class="hoverbg5 hoverbg" onmouseover="bgcolor(5)" onmouseout="removecolor(5)">
                                <dd><a href="./NoticeList.no">공지사항</a></dd>
                            </dl>
                            </div>
                        </div>
                    </div>
                </div>
			</section>
        </header>

        <section id="content">
             <div class="result_bar">
                <div>
                    <%=keyword %>의 검색 결과입니다.
                </div>
            </div>
            <ul class="itemlist">
                <li class="itembox selected"><a href="./All_Search.se?search_word=<%=keyword%>">전체</a></li>
                <li class="itembox"><a href="./Tour_Search.se?search_word=<%=keyword%>&page_num=1">관광지</a></li>
                <li class="itembox"><a href="./Food_Search.se?search_word=<%=keyword%>&page_num=1">음식점</a></li>
                <li class="itembox"><a href="./RailroNote_Search.se?search_word=<%=keyword%>&page_num=1">내일로 노트</a></li>
                <li class="itembox"><a href="./Member_Search.se?search_word=<%=keyword%>&page_num=1">내일러</a></li>
            </ul>
            <div class="Contents">
                <div class="Tourist">
                   <div class="Tourist_Title">
                        <h1>관광지</h1>
                        <%if(Tour.size()!=0){ %>
                        <a href="./Tour_Search.se?search_word=<%=keyword%>&page_num=1"><h2>더보기<img src="./jpg/plus.png" alt=""></h2></a>
                        <%} %>
                   </div>
                   <%for(int i=0; i<Tour.size(); i++){ %>
                    <a href="./Detail_Info.se?contentid=<%=Tour.get(i).getContentid()%>&contenttypeid=<%=Tour.get(i).getContenttypeid()%>" target="_blank">
                    <ul class="Tour_group">
                        <li class="TourImage"><img src="<%=Tour.get(i).getImg() %>" alt="" width="148px"></li>
                        <div class="TourInfo">
                            <li class="TourTitle"><%=Tour.get(i).getTitle() %></li>
                            <li class="shopping_add"><img src="./jpg/plus.png" alt=""></li>
                            <li class="TourContent"><%=Tour.get(i).getContent() %></li>
                        </div>
                        <div class="like_review">
                           <span><%=Tour.get(i).getReview_cnt() %></span>
                            <img src="./mynote_jpg/review.png" alt="">
                            <span><%=Tour.get(i).getLike_cnt() %></span>
                            <img src="./mynote_jpg/footprint.png" alt="">
                        </div>
                    </ul>
                    </a>
                    <%} %>
                    <%if(Tour.size()==0){ %>
                   	 <h1>관광지 정보가 없습니다.</h1>
                    <%} %>
                </div>
                <div class="Tourist Restaurant">
                    <div class="Tourist_Title">
                        <h1>음식점</h1>
                        <%if(Food.size()!=0){ %>
                        <a href="./Food_Search.se?search_word=<%=keyword%>&page_num=1"><h2>더보기<img src="./jpg/plus.png" alt=""></h2></a>
                        <%} %>
                   </div>
                   <%for(int i=0; i<Food.size(); i++){ %>
                    <a href="./Detail_Info.se?contentid=<%=Food.get(i).getContentid()%>&contenttypeid=<%=Food.get(i).getContenttypeid()%>" target="_blank">
                    <ul class="Tour_group">
                        <li class="TourImage"><img src="<%=Food.get(i).getImg() %>" alt="" width="148px"></li>
                        <div class="TourInfo">
                            <li class="TourTitle"><%=Food.get(i).getTitle() %></li>
                            <li class="shopping_add"><img src="./jpg/plus.png" alt=""></li>
                            <li class="TourContent"><%=Food.get(i).getContent() %></li>
                        </div>
                        <div class="like_review">
                           <span><%=Food.get(i).getReview_cnt() %></span>
                            <img src="./mynote_jpg/review.png" alt="">
                            <span><%=Food.get(i).getLike_cnt() %></span>
                            <img src="./mynote_jpg/footprint.png" alt="">
                        </div>
                    </ul>
                    </a>
                    <%} %>
                    <%if(Food.size()==0){ %>
                    	<h1>음식점 정보가 없습니다.</h1>
                    <%} %>
                </div>
                <div class="Travel_Course Tourist">
                    <div class="Tourist_Title">
                        <h1>내일로 노트</h1>
                        <%if(Railro_Note.size()!=0){ %>
                        <a href="./RailroNote_Search.se?search_word=<%=keyword%>&page_num=1"><h2>더보기<img src="./jpg/plus.png" alt=""></h2></a>
                        <%} %>
                   </div>
                   
                    <div class="plans_list_rows">
                    <%for(int i=0; i<Railro_Note.size(); i++) {%>
                       <a href="./NoteDetail.pl?num=<%=Railro_Note.get(i).getNote_ID()%>" target="_blank"><ul class="list_item">
                            <li>
                                <img src="<%=Railro_Note.get(i).getImg() %>" alt="" width="346px" height="200px">
                                <div class="note_info">
                                    <h1><%=Railro_Note.get(i).getTravel_Day() %> (<%=Railro_Note.get(i).getDay() %>일)</h1>
                                    <h1><%=Railro_Note.get(i).getNote_Name() %></h1>
                                </div>
                            </li>
                            <li>
                                <div class="like">
                                 <span class="tema">
                                     	<%=Railro_Note.get(i).getTema_Name() %>여행
                                 </span>
                                  <span>
                                      <%=Railro_Note.get(i).getView() %>
                                  </span>
                                   <img src="./note_plans_list_jpg/eye.png" alt="" width="20px">
                                    <span>
                                        <%=Railro_Note.get(i).getLike() %>
                                    </span>
                                    <img src="./mynote_jpg/footprint.png" alt="" width="20px">
                                </div>
                                <div class="route">
                                    	<%=Railro_Note.get(i).getArea() %>
                                </div>
                                <div class="person">
                                    <img src="<%=Railro_Note.get(i).getProfileimg() %>" alt="" width="20px">
                                    <span><%=Railro_Note.get(i).getName() %></span>
                                </div>
                            </li>                    
                        </ul></a>
                        <%} %>
                        <%if(Railro_Note.size()==0){ %>
                        	<h1>내일로 노트 정보가 없습니다.</h1>
                        <%} %>
                    </div>
                    </div>
                <div class="Tourist">
                   <div class="Tourist_Title">
                        <h1>내일러</h1>
                        <%if(Member.size()!=0){ %>
                        <a href="./Member_Search.se?search_word=<%=keyword%>&page_num=1"><h2>더보기<img src="./jpg/plus.png" alt=""></h2></a>
                        <%} %>
                   </div>
                   <%for(int i=0; i<Member.size(); i++){ %>
                   <a href="#" onclick="return false;">
                    <ul class="user" onclick="return false;">
                        <li class="user_img">
                            <img src="<%=Member.get(i).getIMG_NAME() %>" alt="">
                        </li>
                        <li class="name"><%=Member.get(i).getMEMBER_NIKNAME() %></li>
                        <li class="note_num">노트 <%=Member.get(i).getNote_Count()%>개</li>
                        <%if(Member.get(i).getFollowing_YN()==0){%>
                        	<%if(session.getAttribute("ID")!=null){ %>
                             <%if(Member.get(i).getMEMBER_ID().compareTo(session.getAttribute("ID").toString())==0) {%>
                        	<%} 
                        	else{%>
                        	<li class="follow" style="border: 1px solid #00baff; background-color: white; color: #00baff;">팔로워</li>
                        	<%} %>
                        	<%} %>
                        <%} 
                        else{%>
                        	<%if(session.getAttribute("ID")!=null){ %>
                        	<%if(Member.get(i).getMEMBER_ID().compareTo(session.getAttribute("ID").toString())==0) {%>
                        	<%} 
                        	else{%>
                        	<li class="follow" style="border: 1px solid #00baff; background-color: #00baff; color: white;">팔로잉</li>
                        	<%} %>
                        	<%} %>
                        <%} %>
                    </ul>
                    </a>
					<%} %>
					<%if(Member.size()==0){ %>
					<h1>내일러 정보가 없습니다.</h1>
					<%} %>
                </div>
            </div>
        </section>
        <footer>
            <div class="section">
                <div id="foot_top">
                    <ul>
                        <li>
                            <a href="./IntroRailro.me">내일로소개</a>
                            <span></span>
                        </li>
                        <li>
                            <a href="./Top100_Search.se?contenttypeid=12&page_num=1">TOP 100</a>
                            <span></span>
                        </li>
                        <li>
                            <a href="./Note_Plans_List.pl">내일로노트</a>
                            <span></span>
                        </li>
                        <li>
                            <a href="./NoticeList.no">고객센터</a>
                        </li>
                    </ul>
                </div>
                <div id="foot_bot">
                    <div id="f_logo">
                        <h2>
                            <a href="./Main.me">
                            <img src="./jpg/RailroTour%20LOGO.png" alt="">
                            </a>
                        </h2>
                    </div>
                    <address>내일로 통합 시스템<br>
주소 : 대구광역시 북구 복현동 영진전문대학 컴퓨터정보계열<br>
대표번호 : 000-0000-0000 팩스번호 : 00-0000-0000<br>
Copyright ⓒ RAILRO COMBINATION SYSTEM. All rights reserved.
</address>
                    <select name="" id="">
                        <option value="">family site</option>
                        <option value="">family site</option>
                    </select>
                </div>
            </div>
        </footer>
    </div>
    
    <script src="./js/script.js"></script>
    <script>
     $(function() {
      var count = $('#rank-list li').length;
      var height = $('#rank-list li').height();

      function step(index) {
          $('#rank-list ol').delay(2000).animate({
              top: -height * index,
          }, 500, function() {
              step((index + 1) % count);
          });
      }

      step(1);
  });
		

  </script>
</body>
</html>