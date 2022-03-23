
<%@page import="java.util.ArrayList"%>
<%@page import="net.search.db.DetailInfo_analysis"%>
<%@page import="net.search.db.DetailInfo_Review"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="net.search.db.DetailInfo_Bean"%>
<%
String ID = null, PW = null;

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
DetailInfo_Bean DetailInfo_bean=(DetailInfo_Bean) request.getAttribute("Info");
ArrayList<DetailInfo_Review> reviews=(ArrayList<DetailInfo_Review>) request.getAttribute("reviews");
DetailInfo_analysis review_analysis=(DetailInfo_analysis) request.getAttribute("review_analysis");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><%=DetailInfo_bean.getName() %></title>
    <link rel="stylesheet" href="./css/commen.css">
    <link rel="stylesheet" href="./css/style.css?ver=2">
    <link rel="stylesheet" href="./css/detail_info.css?ver=2">
    <link rel="stylesheet" href="./jqcloud/jqcloud.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
     
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

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
        
        <section id="img_banner">
            <div class="more_info">
                <div class="img">
                    <ul class="img_collection" id="slides">
                    <%for(int i=0; i<DetailInfo_bean.getImg().size(); i++){ %>
                    	<%if(i==0){ %>
                        <li style="display: block"><img src="<%=DetailInfo_bean.getImg().get(i) %>" alt=""></li>
                        <%}else{ %>
                        <li><img src="<%=DetailInfo_bean.getImg().get(i) %>" alt=""></li>
                        <%} %>
                     <%} %>
                        
                    </ul>
                    <ul class="btn_group">
                        <li class="Previous"><img src="./detail_info_img/left-arrow.png" alt=""></li>
<!--
                        <li class="Stop"><img src="./detail_info_img/pause-button.png" alt=""></li>
                        <li class="Play" style="display: none"><img src="./detail_info_img/play-button.png" alt=""></li>
-->
                        <li class="Next"><img src="./detail_info_img/left-arrow.png" alt=""></li>
                    </ul>
                </div>
                <div class="text">
                    <ul class="text_group">
                        <li class="title">
                            <span><%=DetailInfo_bean.getName() %></span>
                           <span style="display:none;">여행바구니</span>
                        </li>
                        <%if(DetailInfo_bean.getAddr()!=null){ %>
                        <li class="rotate">
                            <div>&#8226; 주소</div>
                            <div><%=DetailInfo_bean.getAddr() %></div>
                        </li>
                        <%} %>
                        <%if(DetailInfo_bean.getMenu()!=null){ %>
                        <li class="menu">
                            <div>&#8226; 대표 메뉴</div>
                            <div><%=DetailInfo_bean.getMenu() %></div>
                        </li>
                        <%}%>
                        <%if(DetailInfo_bean.getOpentime()!=null){ %>
                        <li class="open_time">
                            <div>&#8226; 영업 시간</div>
                            <div><%=DetailInfo_bean.getOpentime() %></div>
                        </li>
                        <%} %>
                        <%if(DetailInfo_bean.getRestday()!=null){ %>
                        <li class="rest">
                            <div>&#8226; 휴무일</div>
                            <div><%=DetailInfo_bean.getRestday() %></div>
                        </li>
                        <%} %>
                        <%if(DetailInfo_bean.getContact_Information()!=null){ %>
                        <li class="phone">
                            <div>&#8226; 문의 및 안내</div>
                            <div><%=DetailInfo_bean.getContact_Information() %></div>
                        </li>
                        <%} %>
                        <%if(DetailInfo_bean.getHomepage()!=null){ %>
                        <li class="homepage">
                            <div>&#8226; 홈페이지</div>
                            <div><%if(DetailInfo_bean.getContentid()==2553428){ %>
                        <%=DetailInfo_bean.getHomepage()+"></a>"%>
                        <%} else{%>
                        <%=DetailInfo_bean.getHomepage()%>
                        <%} %></div>
                        </li>
                        <%} %>
                        
                    </ul>
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
                        <button>상세정보</button>
                    </div>
                    <%if(DetailInfo_bean.getContenttypeid()==12){ %>
                    <div class="listmenu">
                         <button>관광지 정보</button>
                    </div>
                    <%}
                    else if(DetailInfo_bean.getContenttypeid()==39){%>
                    <div class="listmenu">
                         <button>음식점 정보</button>
                    </div>
                    <%} %>
                </div>
            </div>
            <div id="sub-con-body" class="section">
                <ul class="more_info">

                	<%if(DetailInfo_bean.getOverview()!=null){ %>
                    <li class="overview">
                        <span>&#8226; 개 요</span>
                        <span><%=DetailInfo_bean.getOverview() %></span>
                    </li>
                    <%} %>
					<%if(DetailInfo_bean.getHomepage()!=null){ %>
                    <li class="url">
                        <span>&#8226; 홈페이지 주소</span>
                        <span><%if(DetailInfo_bean.getContentid()==2553428){ %>
                        <%=DetailInfo_bean.getHomepage()+"></a>"%>
                        <%} else{%>
                        <%=DetailInfo_bean.getHomepage()%>
                        <%} %>
                        </span>
                    </li>
                    <%} %>
                    <%if(DetailInfo_bean.getMax_peo()!=null){ %>
                    <%if(DetailInfo_bean.getMax_peo().compareTo("")!=0){ %>
                    <li class="people">
                        <span>&#8226; 수용인원</span>
                        <span><%=DetailInfo_bean.getMax_peo() %></span>
                    </li>
                    <%} %>
                    <%} %>
                    <%if(DetailInfo_bean.getStroller()!=null){ %>
                    <li class="stroller">
                        <span>&#8226; 유모차 대여 여부</span>
                        <span><%=DetailInfo_bean.getStroller() %></span>
                    </li>
                    <%} %>
                    <%if(DetailInfo_bean.getCreditcard_availability()!=null){ %>
                    <li class="credit_card">
                        <span>&#8226; 신용카드 가능 여부</span>
                        <span><%=DetailInfo_bean.getCreditcard_availability() %></span>
                    </li>
                    <%} %>
                    <%if(DetailInfo_bean.getPets_allowed()!=null){ %>
                    <li class="animal">
                        <span>&#8226; 애완동물 가능 여부</span>
                        <span><%=DetailInfo_bean.getPets_allowed() %></span>
                    </li>
                    <%} %>
                    <%if(DetailInfo_bean.getAvailable_age()!=null){ %>
                    <li class="available_age">
                        <span>&#8226; 체험가능 연령</span>
                        <span><%=DetailInfo_bean.getAvailable_age()%></span>
                    </li>
                    <%} %>
                    <%if(DetailInfo_bean.getExperience()!=null){ %>
                    <%if(DetailInfo_bean.getExperience().compareTo("")!=0){ %>
                    <li class="experience">
                        <span>&#8226; 체험 안내</span>
                        <span><%=DetailInfo_bean.getExperience() %></span>
                    </li>
                    <%} %>
                    <%} %>
                    <%if(DetailInfo_bean.getContact_Information()!=null){ %>
                    <li class="question">
                        <span>&#8226; 문의 및 안내</span>
                        <span><%=DetailInfo_bean.getContact_Information() %></span>
                    </li>
                    <%} %>
                    <%if(DetailInfo_bean.getOpening_date()!=null){ %>
                    <li class="open">
                        <span>&#8226; 개장일</span>
                        <span><%=DetailInfo_bean.getOpening_date() %></span>
                    </li>
                    <%} %>
                    <%if(DetailInfo_bean.getParking_facilities()!=null){ %>
                    <%if(DetailInfo_bean.getParking_facilities().compareTo("")!=0){ %>
                    <li class="parking">
                        <span>&#8226; 주차시설</span>
                        <span><%=DetailInfo_bean.getParking_facilities() %></span>
                    </li>
                    <%} %>
                    <%} %>
                    <%if(DetailInfo_bean.getRestday()!=null){ %>
                    <li class="rest_day">
                        <span>&#8226; 쉬는날</span>
                        <span><%=DetailInfo_bean.getRestday() %></span>
                    </li>
                    <%} %>
                    <%if(DetailInfo_bean.getWhen_to_use()!=null){ %>
                    <li class="use">
                        <span>&#8226; 이용시기</span>
                        <span><%=DetailInfo_bean.getWhen_to_use() %></span>
                    </li>
                    <%} %>
                    <%if(DetailInfo_bean.getHours_of_use()!=null){ %>
                    <%if(DetailInfo_bean.getHours_of_use().compareTo("")!=0){ %>
                    <li class="use_time">
                        <span>&#8226; 이용시간</span>
                        <span><%=DetailInfo_bean.getHours_of_use() %></span>
                    </li>
                    <%} %>
                    <%} %>
                    <%if(DetailInfo_bean.getDiscount_information()!=null){ %>
                    <%if(DetailInfo_bean.getDiscount_information().compareTo("")!=0){ %>
                    <li class="discount">
                        <span>&#8226; 할인정보</span>
                        <span><%=DetailInfo_bean.getDiscount_information() %></span>
                    </li>
                    <%} %>
                    <%} %>
                    <%if(DetailInfo_bean.getMenu()!=null){ %>
                    <li class="representative_menu">
                        <span>&#8226; 대표메뉴</span>
                        <span><%=DetailInfo_bean.getMenu() %></span>
                    </li>
                    <%} %>
                    <%if(DetailInfo_bean.getPlayroom_availability()!=null){ %>
                    <li class="kid">
                        <span>&#8226; 어린이 놀이방 여부</span>
                        <span><%=DetailInfo_bean.getPlayroom_availability() %></span>
                    </li>
                    <%} %>
                    <%if(DetailInfo_bean.getOpening_Date()!=null){ %>
                    <li class="opening">
                        <span>&#8226; 개업일</span>
                        <span><%=DetailInfo_bean.getOpening_Date() %></span>
                    </li>
                    <%} %>
                    <%if(DetailInfo_bean.getPackable()!=null){ %>
                    <li class="packing">
                        <span>&#8226; 포장 가능</span>
                        <span><%=DetailInfo_bean.getPackable() %></span>
                    </li>
                    <%} %>
                    <%if(DetailInfo_bean.getReservation_Guide()!=null){ %>
                    <li class="reserv">
                        <span>&#8226; 예약 안내</span>
                        <span><%=DetailInfo_bean.getReservation_Guide() %></span>
                    </li>
                    <%} %>
                    <%if(DetailInfo_bean.getScale()!=null){ %>
                    <li class="scale">
                        <span>&#8226; 규모</span>
                        <span><%=DetailInfo_bean.getScale() %></span>
                    </li>
                    <%} %>
                    <%if(DetailInfo_bean.getNumber_seats()!=null){ %>
                    <li class="seats">
                        <span>&#8226; 좌석수</span>
                        <span><%=DetailInfo_bean.getNumber_seats() %></span>
                    </li>
                    <%} %>
                    <%if(DetailInfo_bean.getSmoking_Smoking()!=null){ %>
                    <li class="smoking">
                        <span>&#8226; 금연/흡연 여부</span>
                        <span><%=DetailInfo_bean.getSmoking_Smoking() %></span>
                    </li>
                    <%} %>

                    <%if(DetailInfo_bean.getHandling_menu()!=null){ %>
                    <li class="handling_menu">
                        <span>&#8226; 취급 메뉴</span>
                        <span><%=DetailInfo_bean.getHandling_menu() %></span>
                    </li>
                    <%} %>

                    <li class="location">
                        <div id="wordcloud" style="width: 500px; height: 300px; border: none;"><!--워드클라우드--></div>
                        <div id="map"></div>
                    </li>
                </ul>
                <div class="review1">
                      내일러 후기
                  </div>                  
                    <div class="review2">
                      해당 장소의 리뷰를 적어주세요.
                </div>
                <div class="comment">
                    <form action="" id="comment_add" onsubmit="return false">
                       <div class="imgfile">
<!--
                            <ul class="preview" style="background-image: url('')">
                                <li>삭제</li>
                            </ul>
-->
                           <input type="file" id="img" style="display: none" accept="image/*">
                           <label for="img" class="img_add"><img src="./detail_info_img/image-add-button.png" alt="" width="17px">사진</label>
                       </div>
                       <%if(session.getAttribute("ID")!=null){ %>
                        <textarea name="" id="" cols="50" rows="10" maxlength="200" placeholder="200자 이하만 입력 가능합니다."></textarea>
                        <%} else{%>
                        <textarea name="" id="" cols="50" rows="10" maxlength="200" placeholder="로그인 후 이용가능합니다."></textarea>
                        <%} %>
                        <input type="submit" value="등록" id="submit">
                        <div class="tag">
                           <input type="checkbox" id="like" style="display: none" value="1">
                           <label for="like"><img src="./detail_info_img/like1.png" alt="" class="like_btn"></label>
                            
                            <span class="title">태그</span>
                            <ul class="tag1">
<!--                                <li class="tags">#쩐다.fsdaffsafddfsdfafsdfsadfsadfsadfsafasfsfsfasfsfsfasfas<img src="./detail_info_img/cancel-button.png" alt=""></li>-->
                                <input type="text" class="input_tag" placeholder="#입력 (최대 10개)">
                            </ul>
                        </div>
                    </form>
                </div>
                <div class="review_list">
                   <div class="review_info">
                       <span>REVIEW</span>
                       <span>|</span>
                       <span>악의적인 비방글은 무통보 삭제됩니다.</span>
                   </div>
                    <ul class="analysis">
                        <li class="like_total">
                            <div class="total"><%=review_analysis.getTotal_like() %></div>
                            <div class="text"><%=review_analysis.getTotal_review() %>개 리뷰<br>좋아요</div>
                        </li>
                        <div class="zt-span6 last">
                            <div class="zt-skill-bar"><div data-width="<%=(double)review_analysis.getTotal_like()/(double)review_analysis.getTotal_review()*100.0 %>" style="">좋아요<span><%=(double)review_analysis.getTotal_like()/(double)review_analysis.getTotal_review()*100.0 %>%</span></div></div>
                            <div class="zt-skill-bar"><div data-width="<%=100-(double)review_analysis.getTotal_like()/(double)review_analysis.getTotal_review()*100.0 %>" style="">싫어요<span><%=100-(double)review_analysis.getTotal_like()/(double)review_analysis.getTotal_review()*100.0 %>%</span></div></div>
                        </div>
                        <h3><strong><%=(double)review_analysis.getTotal_like()/(double)review_analysis.getTotal_review()*100.0 %>% 의 내일러들이 이 관광지를 좋아합니다.</strong></h3>
                    </ul>
                    <div class="latest_review">
                        <span>최신순</span> 리뷰 (<%=review_analysis.getTotal_review() %>)
                    </div>
                    <ul class="reviews">
                    	<%
                    	if(reviews.size()>5){
                    	for(int i=0; i<5; i++){ %>
						<li class="review">
                            <div class="date">
                               <img src="<%=reviews.get(i).getProfile_img() %>" alt="">
                               <span class="nikname"><%=reviews.get(i).getNikname() %></span>
                                <span class="datetime"><%=reviews.get(i).getDate().substring(0, 19) %></span>
                                <%if(session.getAttribute("ID")!=null){ %>
                                <%if(session.getAttribute("ID").toString().compareTo(reviews.get(i).getEmail_id())==0){ %>
                                <button class="remove" data-num="<%=reviews.get(i).getReview_num()%>">삭제</button>
                                <%}} %>
                                <%if(reviews.get(i).getLike_yn()==1){ %>
                                <img src="./detail_info_img/like2.png" alt="" class="likeimg">
                                <span class="like"> - 좋아해요</span>
                                <%}
                                else{%>
                                <img src="./detail_info_img/like1.png" alt="" class="likeimg">
                                <span class="like"> - 싫어해요</span>
                                <%} %>
                            </div>
                            <div class="contents">
                                <%=reviews.get(i).getMemo() %>
                            </div>
                            <%if(reviews.get(i).getImgfile().size()!=0){ %>
                            <div class="imgs">
                            	<%for(int a=0; a<reviews.get(i).getImgfile().size(); a++){ %>
                                <img src="<%=reviews.get(i).getImgfile().get(a) %>" alt="" onclick="pop(this)">
                                <%} %>
                            </div>
                            <%} %>
                            <%if(reviews.get(i).getHashtag().size()!=0){ %>
                            <div class="tags">
                            	<%for(int b=0; b<reviews.get(i).getHashtag().size(); b++){ %>
                                <span class="tag">#<%=reviews.get(i).getHashtag().get(b) %></span>
                                <%} %>
                            </div>
                            <%} %>
                        </li>
                   		<%}}
                    	else{
                    		for(int i=0; i<reviews.size(); i++){%>
                    		<li class="review">
                            <div class="date">
                               <img src="<%=reviews.get(i).getProfile_img() %>" alt="">
                               <span class="nikname"><%=reviews.get(i).getNikname() %></span>
                                <span class="datetime"><%=reviews.get(i).getDate().substring(0, 19) %></span>
                                <%if(session.getAttribute("ID")!=null){ %>
                                <%if(session.getAttribute("ID").toString().compareTo(reviews.get(i).getEmail_id())==0){ %>
                                <button class="remove" data-num="<%=reviews.get(i).getReview_num()%>">삭제</button>
                                <%}} %>
                                <%if(reviews.get(i).getLike_yn()==1){ %>
                                <img src="./detail_info_img/like2.png" alt="" class="likeimg">
                                <span class="like"> - 좋아해요</span>
                                <%}
                                else{%>
                                <img src="./detail_info_img/like1.png" alt="" class="likeimg">
                                <span class="like"> - 싫어해요</span>
                                <%} %>
                            </div>
                            <div class="contents">
                                <%=reviews.get(i).getMemo() %>
                            </div>
                            <%if(reviews.get(i).getImgfile().size()!=0){ %>
                            <div class="imgs">
                            	<%for(int a=0; a<reviews.get(i).getImgfile().size(); a++){ %>
                                <img src="<%=reviews.get(i).getImgfile().get(a) %>" alt="" onclick="pop(this)">
                                <%} %>
                            </div>
                            <%} %>
                            <%if(reviews.get(i).getHashtag().size()!=0){ %>
                            <div class="tags">
                            	<%for(int b=0; b<reviews.get(i).getHashtag().size(); b++){ %>
                                <span class="tag">#<%=reviews.get(i).getHashtag().get(b) %></span>
                                <%} %>
                            </div>
                            <%} %>
                        </li>
                    	<%	}
                    		} %>
                    </ul>
                    <div class="page_num_group">
                    <%for(int i=1; i<=review_analysis.getTotal_review()/5; i++){ %>
                    <%if(i==1){ %>
                    <a href="#" class="selected">1</a>
                    <%}
                    else{%>
                    <a href="#"><%=i %></a>
                    <%} %>
                    <%} %>
                    <%if(review_analysis.getTotal_review()%5!=0){ %>
                    <a href="#"><%=review_analysis.getTotal_review()/5+1 %></a>
                    <%} %>
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
    <script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c75ebef98aa832875a335d779a7dc27a"></script>
    <script src="./jqcloud/jqcloud-1.0.4.js"></script>
    <script src="./js/script.js?ver=1"></script>
    <script src="./js/detail_info.js?ver=14"></script>

    
    <script>
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
            mapOption = { 
                center: new daum.maps.LatLng(<%=DetailInfo_bean.getLat()%>, <%=DetailInfo_bean.getLng()%>), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            };

        // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
        var map = new daum.maps.Map(mapContainer, mapOption); 
        
        
                // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
        var mapTypeControl = new daum.maps.MapTypeControl();

        // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
        // daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
        map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);

        // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
        var zoomControl = new daum.maps.ZoomControl();
        map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
        
        
        
        var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커이미지의 주소입니다    
            imageSize = new daum.maps.Size(64, 69), // 마커이미지의 크기입니다
            imageOption = {offset: new daum.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

        // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
        var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imageOption),
            markerPosition = new daum.maps.LatLng(<%=DetailInfo_bean.getLat()%>, <%=DetailInfo_bean.getLng()%>); // 마커가 표시될 위치입니다

        // 마커를 생성합니다
        var marker = new daum.maps.Marker({
            position: markerPosition, 
            image: markerImage // 마커이미지 설정 
        });

        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map); 
        
                //워드클라우드 스크립트 
        
        
                // word frequencies of first two chapters of Oliver Twist
        /*
        var words = [
          {text: '장어탕', size: 50},
          {text: '맛집', size: 47},
          {text: '아프리카', size: 46},
          {text: '유튜브', size: 36},
          {text: '트위치', size: 29, href: 'https://en.wikipedia.org/wiki/Beadle'},
          {text: '대박', size: 29},
          {text: '쩐다', size: 56, href: 'https://en.wikipedia.org/wiki/Mrs.'},
          {text: '맛있어요', size: 27, href: 'http://educationcing.blogspot.nl/2012/06/oliver-twist-mrs-manns-character.html'},
          {text: 'ㅋㅋㅋㅋ', size: 27},
          {text: '우와', size: 26},
        ];
		
        d3.wordcloud()
        .size([500, 300])
        .fill(d3.scale.ordinal().range(["#884400", "#448800", "#888800", "#444400"]))
        .words(words)
        .start();
        */
        var ID='<%=session.getAttribute("ID")%>'
  </script>
</body>
</html>