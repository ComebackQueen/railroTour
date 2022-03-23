<%@page import="net.note.db.Note_Travel_Area_Bean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.note.db.Note_Detail_Info_Bean"%>
<%@page import="net.note.db.Note_Basic_Info_Bean"%>
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
   
   
   Note_Basic_Info_Bean Basic_Info=(Note_Basic_Info_Bean) request.getAttribute("Basic_Info");
   ArrayList<Note_Detail_Info_Bean> Detail_Info=(ArrayList<Note_Detail_Info_Bean>) request.getAttribute("Detail_Info");
   ArrayList<Note_Travel_Area_Bean> Navi_Info=(ArrayList<Note_Travel_Area_Bean>) request.getAttribute("Navi_Info");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>내 노트</title>
    <link rel="stylesheet" href="./css/commen.css">
    <link rel="stylesheet" href="./css/style.css?ver=2">
    <link rel="stylesheet" href="./css/mynote.css?ver=2">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
    
      <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css">
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
    
    <link href="https://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
    <link href="./src/select-mania.css" rel="stylesheet" type="text/css">
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
                            <img src="jpg/RailroTour%20LOGO.png" alt="">
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
        
        <section id="sub-content">
           <section id="note_img" style="background:url('<%=Basic_Info.getBGImg() %>') no-repeat;background-size:cover;background-position:center center;">
                <div class="note_info">
                    <ul class="note_info_center">
                        <div class="top">
                           <li class="cover_img">
								<form id="ajaxform" enctype="multipart/form-data">
                               <input type="file" id="img" accept="image/*" name="save">
								</form>
                               <%if(session.getAttribute("ID")!=null){ %>
                               <%if(Basic_Info.getEmail_ID().compareTo(session.getAttribute("ID").toString())==0){ %>
                               	<label for="img" class="img_change">커버사진 바꾸기</label>
                               <%} 
                               }%>
                           </li>
                           <%if(session.getAttribute("ID")!=null) {%>
                           		<%if(session.getAttribute("ID").toString().compareTo(Basic_Info.getEmail_ID())!=0){ %>
                            	<li class="add_mynote"><img src="./mynote_jpg/add.png" alt=""></li>
                            	<%} %>
                            	<%if(Basic_Info.getLike_YN()==0) {%>
                            	<li class="like"><img src="./mynote_jpg/footprint.png" alt=""></li>
                            	<%} 
                            	else{%>
                            	<li class="like"><img src="./mynote_jpg/footprint2.png" alt=""></li>
                            	<%} %>
                            <%} %>
                        </div>
                        <li class="user">
                            <div class="user_img">
                                <img src="<%=Basic_Info.getProfileImg() %>" alt="">
                            </div>
                            <div class="user_name"><%=Basic_Info.getNikname() %></div>
                            <%if(session.getAttribute("ID")!=null) {
                            	if(session.getAttribute("ID").toString().compareTo(Basic_Info.getEmail_ID())!=0){%>
                            <%if(Basic_Info.getFllow_YN()==0){ %>
                            <div class="follow"  style="background-color: white; border-color:#0076ff; color:#0076ff;">팔로우+</div>
                            <%}
                            else{%>
                            <div class="follow" style="background-color: #0076ff; border-color:white; color:white;">팔로워</div>
                          <%}}} %>
                        </li>
                        <li class="title">
                            <div><%=Basic_Info.getNote_name() %></div>
                        </li>
                        <li class="note_date">
                            <div class="date">
                                <%=Basic_Info.getDate()%>~<%=Basic_Info.getEnd_Date() %> (<%=Basic_Info.getDays()%>일)
                            </div>
                            <div class="tema" style="background-color: #696969"><%=Basic_Info.getTema() %>여행</div>
                        </li>
                        <li class="add_info">
                            <div class="rectangle">
                                <img src="./mynote_jpg/foot.png" alt="">
                                <span><%=Basic_Info.getLike() %></span>
                                <img src="./mynote_jpg/view.png" alt="" class="view">
                                <span><%=Basic_Info.getView() %></span>
                            </div>
                        </li>
                    </ul>
                </div>
            </section>
            <div id="sub-con-navi">
                <div class="section">
                    <div class="homebtn">
                        <a href="../index.html">
                            <img src="../jpg/home.jpg" alt="">
                        </a>
                    </div>
                    <div class="listmenu">
                        <button>플래너</button>
                        <ul class="listbox">
                            <li><a href="./IntroRailro.me">내일로 소개</a></li>
                            <li><a href="./Top100_Search.se?contenttypeid=12&page_num=1">TOP 100</a></li>
                            <li><a href="./NoticeList.no">고객센터</a></li>
                        </ul>
                    </div>
                    <div class="listmenu">
                         <button>여행 플래너</button>
                        <ul class="listbox">
                            <li><a href="./Railro_Note_Step1.pl">새 플래너 작성</a></li>
                            <li><a href="./Note_Plans_List.pl">플래너 목록</a></li>
                        </ul>
                    </div>
                    
                </div>
            </div>

            <div id="sub-con-body" class="section">
                <div class="plan_nav">
                    <div class="top_arrow" onclick="arrow_Move(-1)"></div>
                    <ul class="nav_route">
                    <%for(int i=0; i<Navi_Info.size(); i++) {%>
                    	<%if(i==0){ %>
                        <li style="color: #0093ff" onclick="fnMove(0)">DAY1 <%=Navi_Info.get(i).getTravel_area_name()%></li>
                        <%} 
                        else{%>
                        <li onclick="fnMove(<%=i%>)"><hr>DAY<%=Navi_Info.get(i).getTravel_area_day()%> <%=Navi_Info.get(i).getTravel_area_name() %></li>
                        <%} %>
                       <%} %>
                    </ul>
                    <div class="bottom_arrow" onclick="arrow_Move(1)"></div>
                </div>
                <div class="plan_route">
                    <ul class="select">
                        <li style="color: #006cff" class="view1">순서대로 보기</li>
                        <span>&nbsp;|&nbsp;</span>
                        <li class="view2">표로 보기</li>
                    </ul>
					<%
                	String route_name;
                	int day;
                	
					for(int i=0; i<Navi_Info.size(); i++) {
                        route_name=Navi_Info.get(i).getTravel_area_name();
                        day=Navi_Info.get(i).getTravel_area_day();
					%>
                    <div class="plan_info">
                        <ul class="day_info">
                            <li class="day">DAY<%=Navi_Info.get(i).getTravel_area_day() %></li>
                            <li class="date_info">
                                <!-- <span class="date"><%=Navi_Info.get(i).getDate() %></span> -->
                                <span class="area"><%=Navi_Info.get(i).getTravel_area_name() %></span>
                            </li>
                        </ul>
                        <ul class="day_route">
                        <%
                        	for(int o=0; o<Detail_Info.size(); o++){
                        	if(route_name.compareTo(Detail_Info.get(o).getArea_name())==0){ //지역명이 같으면
                        		if(Detail_Info.get(o).getDays().compareTo("DAY"+day)==0){//DAY가 같으면
                        %>
									<%if(Detail_Info.get(o).getOrders()==0){//해당 일차의 첫번째 일정(장소)이라면 %>
                            <li class="route">
                                <div class="number">
                                    <span><%=Detail_Info.get(o).getOrders()+1 %></span>
                                </div>
                                <div class="info">
                                    <a href="#">
                                    	<%if(Detail_Info.get(o).getKinds_1().compareTo("일반")==0){ %>
                                        <img src="<%=Detail_Info.get(o).getImg() %>" alt="">
                                        <%}
                                    	else if(Detail_Info.get(o).getKinds_1().compareTo("해시")==0){%>
                                    	<img src="./mynote_jpg/main_hash.png" alt="">
                                    	<%} %>
                                    </a>
                                    <div class="title"><%=Detail_Info.get(o).getRoute_name() %></div>
                                    <div class="kinds">
                                    <%if(Detail_Info.get(o).getKinds_1().compareTo("일반")==0){ %>
                                    	<%if(Detail_Info.get(o).getKinds_2().compareTo("음식점")==0){ %>
                                        <img src="./mynote_jpg/spoon.png" alt="" class="food_tour_hash">
                                        <%}
                                    	else if(Detail_Info.get(o).getKinds_2().compareTo("관광지")==0){%>
                                        <img src="./mynote_jpg/photo-camera.png" alt="" class="food_tour_hash">
                                        <%} %>
                                        <h1>|</h1>
                                        <img src="./mynote_jpg/footprint.png" alt="" class="like">
                                        <h2><%=Detail_Info.get(o).getLike_cnt() %></h2>
                                        <h2>개 좋아요</h2>
                                        <a href="./Detail_Info.se?contentid=<%=Detail_Info.get(o).getContent_ID()%>&contenttypeid=<%=Detail_Info.get(o).getContent_Type_ID()%>" target="_blank"><img src="./mynote_jpg/info.png" alt="" class="info"></a>
                                        
                                    <%}else if(Detail_Info.get(o).getKinds_1().compareTo("해시")==0){%>
                                    	<%if(Detail_Info.get(o).getKinds_2().compareTo("음식점")==0){ %>
                                        <img src="./mynote_jpg/spoon.png" alt="" class="food_tour_hash">
                                        <a href="#"><img src="./mynote_jpg/info.png" alt="" class="info"></a>
                                        <%}
                                    	else if(Detail_Info.get(o).getKinds_2().compareTo("관광지")==0){%>
                                        <img src="./mynote_jpg/photo-camera.png" alt="" class="food_tour_hash">
                                        <a href="#"><img src="./mynote_jpg/info.png" alt="" class="info"></a>
                                        <%} %>
                                        <%if(Detail_Info.get(o).getKinds_2().compareTo("이동")==0){ %>
                                        <img src="./mynote_jpg/hash.png" alt="" class="food_tour_hash">
                                        <select name="start" id="start_station">
                                            <option value="">출발역 선택</option>
                                            <%for(int b=0; b<Detail_Info.get(o).getStart_Station().size(); b++) {%>
                                            <option value="<%=Detail_Info.get(o).getStart_Station().get(b).getNodeId()%>"><%=Detail_Info.get(o).getStart_Station().get(b).getNodeName() %></option>
                                            <%} %>

                                        </select>
                                        <select name="end" id="end_station">
                                            <option value="">도착역 선택</option>
                                             <%for(int b=0; b<Detail_Info.get(o).getEnd_Station().size(); b++) {%>
                                            <option value="<%=Detail_Info.get(o).getEnd_Station().get(b).getNodeId()%>"><%=Detail_Info.get(o).getEnd_Station().get(b).getNodeName() %></option>
                                            <%} %>
                                        </select>
                                        <button>조회</button>
                                        <a href="#"><img src="./mynote_jpg/info.png" alt="" class="info"></a>
                                        <%}} %>
                                    </div>
                                    <div class="arrow"></div>
                                    <%if(session.getAttribute("ID")!=null) {%>
                                    <%if(session.getAttribute("ID").toString().compareTo(Basic_Info.getEmail_ID())==0){ %>
                                    <div contenteditable="true" class="post" data-orders="<%=Detail_Info.get(o).getOrders()%>" data-day_orders="<%=Detail_Info.get(o).getDay_orders()%>"><%=Detail_Info.get(o).getMemo() %></div>
                                    <%}
                                    else{%>
                                    <div class="post"><%=Detail_Info.get(o).getMemo() %></div>
                                    <%} %>
                                    <%}
                                    else{%>
                                    <div class="post"><%=Detail_Info.get(o).getMemo() %></div>
									<%} %>
                                </div>
                            </li>
                            <%} 
                            else{%>
                            <hr class="line">
                            <li class="route">
                                <div class="number">
                                    <span><%=Detail_Info.get(o).getOrders()+1 %></span>
                                </div>
                                <div class="info">
                                    <a href="">
                                        <%if(Detail_Info.get(o).getKinds_1().compareTo("일반")==0){ %>
                                        <img src="<%=Detail_Info.get(o).getImg() %>" alt="">
                                        <%}
                                    	else if(Detail_Info.get(o).getKinds_1().compareTo("해시")==0){%>
                                    	<img src="./mynote_jpg/main_hash.png" alt="">
                                    	<%} %>
                                    </a>
                                    <div class="title"><%=Detail_Info.get(o).getRoute_name() %></div>
									<div class="kinds">
                                    <%if(Detail_Info.get(o).getKinds_1().compareTo("일반")==0){ %>
                                    	<%if(Detail_Info.get(o).getKinds_2().compareTo("음식점")==0){ %>
                                        <img src="./mynote_jpg/spoon.png" alt="" class="food_tour_hash">
                                        <%}
                                    	else if(Detail_Info.get(o).getKinds_2().compareTo("관광지")==0){%>
                                        <img src="./mynote_jpg/photo-camera.png" alt="" class="food_tour_hash">
                                        <%} %>
                                        <h1>|</h1>
                                        <img src="./mynote_jpg/footprint.png" alt="" class="like">
                                        <h2><%=Detail_Info.get(o).getLike_cnt() %></h2>
                                        <h2>개 좋아요</h2>
                                        <a href="./Detail_Info.se?contentid=<%=Detail_Info.get(o).getContent_ID()%>&contenttypeid=<%=Detail_Info.get(o).getContent_Type_ID()%>" target="_blank"><img src="./mynote_jpg/info.png" alt="" class="info"></a>
                                        
                                    <%}else if(Detail_Info.get(o).getKinds_1().compareTo("해시")==0){%>
                                    	<%if(Detail_Info.get(o).getKinds_2().compareTo("음식점")==0){ %>
                                        <img src="./mynote_jpg/spoon.png" alt="" class="food_tour_hash">
                                        <a href="#"><img src="./mynote_jpg/info.png" alt="" class="info"></a>
                                        <%}
                                    	else if(Detail_Info.get(o).getKinds_2().compareTo("관광지")==0){%>
                                        <img src="./mynote_jpg/photo-camera.png" alt="" class="food_tour_hash">
                                        <a href="#"><img src="./mynote_jpg/info.png" alt="" class="info"></a>
                                        <%} %>
                                        <%if(Detail_Info.get(o).getKinds_2().compareTo("이동")==0){ %>
                                        <img src="./mynote_jpg/hash.png" alt="" class="food_tour_hash">
                                        <select name="start" id="start_station">
                                            <option value="">출발역 선택</option>
                                            <%for(int b=0; b<Detail_Info.get(o).getStart_Station().size(); b++) {%>
                                            <option value="<%=Detail_Info.get(o).getStart_Station().get(b).getNodeId()%>"><%=Detail_Info.get(o).getStart_Station().get(b).getNodeName() %></option>
                                            <%} %>

                                        </select>
                                        <select name="end" id="end_station">
                                            <option value="">도착역 선택</option>
                                             <%for(int b=0; b<Detail_Info.get(o).getEnd_Station().size(); b++) {%>
                                            <option value="<%=Detail_Info.get(o).getEnd_Station().get(b).getNodeId()%>"><%=Detail_Info.get(o).getEnd_Station().get(b).getNodeName() %></option>
                                            <%} %>
                                        </select>
                                        <button>조회</button>
                                        <a href="#"><img src="./mynote_jpg/info.png" alt="" class="info"></a>
                                        <%}} %>
                                    </div>
                                    <div class="arrow"></div>
                                    <%if(session.getAttribute("ID")!=null) {%>
                                    <%if(session.getAttribute("ID").toString().compareTo(Basic_Info.getEmail_ID())==0){ %>
                                    <div contenteditable="true" class="post" data-orders="<%=Detail_Info.get(o).getOrders()%>" data-day_orders="<%=Detail_Info.get(o).getDay_orders()%>"><%=Detail_Info.get(o).getMemo() %></div>
                                    <%}
                                    else{%>
                                    <div class="post"><%=Detail_Info.get(o).getMemo() %></div>
                                    <%} %>
                                    <%}
                                    else{%>
                                    <div class="post"><%=Detail_Info.get(o).getMemo() %></div>
									<%} %>
                                </div>
                            </li>
                           <%		}}
                           		}
                            } 
                           %>
                        </ul>
                    </div>
                    <%} %>
                    <table>
                        <tr>
                            <th>날짜</th>
                            <th>지역</th>
                            <th>음식점</th>
                            <th>관광지</th>
                        </tr>
                        <%for(int i=0; i<Basic_Info.getDays(); i++){ %>
                        <tr>
                            <td>DAY<%=i+1 %></td>
							
                            <td>
                            	<%for(int a=0; a<Navi_Info.size(); a++){ %>
                            		<%if(Navi_Info.get(a).getTravel_area_day()==(i+1)){ %>
                                		<div class="areaname"><%=Navi_Info.get(a).getTravel_area_name() %></div>
                                	<%} %>
                               	<%} %>
                            </td>
                            <td>
                            	<%int foodcnt=1; %>
                            	<%for(int b=0; b<Detail_Info.size(); b++){ %>
                            		<%if(Detail_Info.get(b).getContent_Type_ID()==39 && Detail_Info.get(b).getDays().compareTo("DAY"+(i+1))==0){ %>
                                	<div class="food"><%=foodcnt %>. <%=Detail_Info.get(b).getRoute_name() %></div>
                                	<%foodcnt++; %>
                                	<%} %>
                                <%} %>
                            </td>
                            <td>
                            	<%int tourcnt=1; %>
                            	<%for(int b=0; b<Detail_Info.size(); b++){ %>
                            		<%if(Detail_Info.get(b).getContent_Type_ID()==12 && Detail_Info.get(b).getDays().compareTo("DAY"+(i+1))==0){ %>
                                	<div class="tour"><%=tourcnt %>. <%=Detail_Info.get(b).getRoute_name() %></div>
                                	<%tourcnt++; %>
                                	<%} %>
                                <%} %>
                            </td>
                        </tr>
                        <%} %>
                    </table>
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
    
    
<div id="dialog" title="알림">
    해당 노트를 내 노트에 담으시겠어요?
</div>

    <script src="./js/mynote.js?ver=21"></script>
    <script src="./js/script.js?ver=7"></script>
    <script>
    $(function () {

      var $gnb = $(".plan_nav").first();
      var gnbTop = $gnb.offset().top;

      function fixGNB() {
        var winTop = $(window).scrollTop();
        $gnb.css({
          'position': (winTop > gnbTop-100) ? 'fixed' : 'absolute',
          'top': (winTop > gnbTop-100) ? '100px' : ''
        });
      }

      fixGNB();

      $(window).scroll(function () {
        fixGNB();
      });  


        $("#dialog").dialog({
            autoOpen:false, //자동으로 열리지않게
            position:['center', 'center'], //x,y  값을 지정
            //"center", "left", "right", "top", "bottom"
            modal:true, //모달대화상자
            resizable:false, //크기 조절 못하게

            buttons:{
                "확인":function(){
                    $.ajax({
                		type:'POST',
                		url:'./NoteAdd.pl',
                		data: {
                			Note_ID: getParam('num')
                		},
                		async: true,
                		success:function(data){
                        	
                		},
                		error:function(data){
                			alert('노트 담기 실패');
                		}
                	})
                	$(this).dialog("close");
                },"취소":function(){
                    $(this).dialog("close");
                }
            }
        });

    });
        

  </script>


</body>
</html>