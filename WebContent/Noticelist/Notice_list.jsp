
<%@page import="net.notice.db.NoticeBean" %>
<%@page import="net.notice.db.NoticeDAO" %>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.naming.Context" %>
<%@page import="javax.naming.InitialContext" %>
<%@page import="javax.sql.DataSource" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
 
    request.setCharacterEncoding("UTF-8");
 
%>
<%
String id=null;
if(session.getAttribute("id")!=null){
	id=(String)session.getAttribute("id");
}
List noticelist = (List)request.getAttribute("noticelist");
int listcount = ((Integer)request.getAttribute("listcount")).intValue();
int nowpage = ((Integer)request.getAttribute("page")).intValue();
int maxpage = ((Integer)request.getAttribute("maxpage")).intValue();
int startpage = ((Integer)request.getAttribute("startpage")).intValue();
int endpage = ((Integer)request.getAttribute("endpage")).intValue();
int limit = ((Integer)request.getAttribute("limit")).intValue();

String ID = null, PW = null;

Cookie cookies[] = request.getCookies();

if(cookies!=null) {
      for(int i = 0; i<cookies.length; i++){
         String name = cookies[i].getName();
         if(name.equals("ID")){
            ID = cookies[i].getValue();
         } else if (name.equals("PW")){
            PW = cookies[i].getValue();
         }
      }
   }
   if(ID!=null&&PW!=null){
      session.setAttribute("ID", ID);
      session.setAttribute("PW", PW);
   }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>공지사항</title>
    <link rel="stylesheet" href="./css/commen.css?ver=2">
    <link rel="stylesheet" href="./css/style.css?ver=17">

   
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
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
                            <!-- 
                            <dl class="hoverbg4 hoverbg" onmouseover="bgcolor(4)" onmouseout="removecolor(4)">

                                <dd><a href="#">지도</a></dd>
                                <dd><a href="#">타임라인</a></dd>
                            </dl>
                             -->
                            <dl class="hoverbg5 hoverbg" onmouseover="bgcolor(5)" onmouseout="removecolor(5)">
                                <dd><a href="./NoticeList.no">공지사항</a></dd>
                            </dl>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </header>
        
        <section id="sub-imgbanner1">
            <div class="section">
                <div class="sub-img-text">
                    <h3>자유여행패스 소개</h3>
                    <h2>
                        <b>무제한 자유기차여행</b>
                    </h2>
                </div>
            </div>
        </section>
        <section id="sub-content">
            <div id="sub-con-navi">
                <div class="section">
                    <div class="homebtn">
                        <a href="./Main.me">
                            <img src="./jpg/home.jpg" alt="">
                        </a>
                    </div>
                    <div class="listmenu">
                        <button>고객센터</button>
                        <ul class="listbox">
                            <li><a href="./IntroRailro.me">내일로 소개</a></li>
                            <li><a href="./Top100_Search.se?contenttypeid=12&page_num=1">TOP 100</a></li>
                            <li><a href="./Note_Plans_List.pl">플래너</a></li>
                        </ul>
                    </div>
                    <div class="listmenu">
                         <button>공지사항</button>
                        <ul class="listbox">
                        </ul>
                    </div>
                </div>
            </div>
            <div id="sub-con-body" class="section">
                <div class="s-c-b-title">
                    <h3>Community</h3>
                    <h2>공지사항</h2>
                </div>

                <div class="s-c-b-cover">
                    <div class="cover-search">
                    	<p>· Total : <b><%out.print(listcount); %></b>건 <b><%out.print("[" + nowpage + "/" + maxpage + "]"); %></b> 페이지</p>
                    	<div class="search-right">
                    		<form action = "./NoticeSearchAction.no" method="get" id="notice_search_form">
                    		<select name="opt">
                    			<option value="0">제목</option>
                    			<option value="1">내용</option>
                    		</select>
                    		<input type="text" name="condition"/>
                    		<input type="submit" value="검색" class="search_btn">
                    	  </form>
                    	</div>
                    </div>
                    <div class="cover-table">
                    	<table>
                    		<thead>
                    			<tr>
                    				<th>번호</th>
                    				<th>제목</th>
                    				<th>날짜</th>
                    				<th>조회</th>
                    			</tr>
                    		</thead>
                
                    		<tbody>
                    			<tr class="tdbgcolor">
                    			<%
                    				for(int i=0; i<noticelist.size(); i++){
                    					NoticeBean nl = (NoticeBean)noticelist.get(i);
                    			%>
                    				<td><%=nl.getNum()%></td>
                    				<td>
                    					<a href="./NoticeDetailAction.no?num=<%=nl.getNum()%>">
                    						<%=nl.getTitle() %>
                    					</a>
                    				</td>
                    				<td><%=nl.getDates() %></td>
                    				<td><%=nl.getReadcnt() %></td>
                    			</tr>
                    			<%} %>
                    		</tbody>
                    	</table>
                    </div>
                    <div class="cover-btn">
                        <div class="bt_group">
                        	<%if(nowpage<=1){ %><button><a href="#">&lt;&lt;</a></button>
                           <%}else { %>
                           		<%if(maxpage <= 10){ %>
                               <button><a href="./NoticeList.no?page=<%=1%>">&lt;&lt;</a></button>
                            <%}else { %>
                            	<button><a href="./NoticeList.no?page=<%=nowpage-10%>">&lt;&lt;</a></button>
                            <%	}
                           	}%>
                            <%if(nowpage<=1){ %><button class="Pre"><a href="#">&lt;</a></button>
                            <%}else { %>
                                 <button><a href="./NoticeList.no?page=<%=nowpage-1%>">&lt;</a></button>
                             <%} %>
                             
                             <%for(int a=startpage; a<=endpage; a++){ 
                                if(a==nowpage){%>
                                 <button><a href="#"><%=a %></a></button>
                              <%}else{ %>
                                 <button><a href="./NoticeList.no?page=<%=a%>"><%=a %></a></button>
                              <%} %>
                           <%} %>   
                           
                           <%if(nowpage >= maxpage){%> <button class="Next"><a href="#">&gt;</a></button>
                           <%}else { %>
                              <button><a href="./NoticeList.no?page=<%=nowpage+1%>">&gt;</a></button>
                           <%} %>
                           <%if(nowpage >= maxpage) {%> <button class="Next"><a href="#">&gt;&gt;</a></button>
                           <%}else { %>
                           		<%if(maxpage <= 10) {%>
                              <button><a href="./NoticeList.no?page=<%=maxpage%>">&gt;&gt;</a></button>
                              <%} else {%>
                              <button><a href="./NoticeList.no?page=<%=nowpage+10%>">&gt;&gt;</a></button>
                              <%}
                           		}%>
                        </div>
                    	<button class="post"><a href="./NoticeWrite.no">글 쓰기</a></button>
                    </div>
                    
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
    <script src="./js/script.js?ver=1"></script>
    <script>
     $(function() {
         var cnt=Number($('.cover-btn>.bt_group button').length);
         
         if(cnt==5){
             $('.cover-btn>.bt_group').css('width', '170px');
         }
         else if(cnt==6){
             $('.cover-btn>.bt_group').css('width', '190px');
         }
         else if(cnt==7){
             $('.cover-btn>.bt_group').css('width', '220px');
         }
         else if(cnt==8){
             $('.cover-btn>.bt_group').css('width', '250px');
         }
         else if(cnt==9){
             $('.cover-btn>.bt_group').css('width', '275px');
         }
         else if(cnt==10){
             $('.cover-btn>.bt_group').css('width', '300px');
         }
         else if(cnt==11){
             $('.cover-btn>.bt_group').css('width', '330px');
         }
         else if(cnt==12){
             $('.cover-btn>.bt_group').css('width', '360px');
         }
         else if(cnt==13){
             $('.cover-btn>.bt_group').css('width', '385px');
         }
         else if(cnt==14){
             $('.cover-btn>.bt_group').css('width', '410px');
         }
         
  });
  </script>
</body>
</html>