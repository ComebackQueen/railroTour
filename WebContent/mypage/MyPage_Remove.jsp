<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("id"); 
%>    
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

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>마이페이지 : 회원탈퇴</title>
    <link rel="stylesheet" href="./css/commen.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/mypage.css">
       <link rel="stylesheet" href="./css/jquery-labelauty.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
 
    <script src="./js/jquery-labelauty.js"></script>
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
                                <a href="./MyPageHome.me" class="list_a1">내 정보 홈</a>
                                
                            </li>
                            <li class="list02 list" onmouseover="bgcolor(2)" onmouseout="removecolor(2)">
                                <a href="./MyPageManagement.me" class="list_a2">내 정보 관리</a>
                            </li>
                            <li class="list03 list" onmouseover="bgcolor(3)" onmouseout="removecolor(3)">
                                <a href="./MyPagePW.me" class="list_a3">비밀번호 변경</a>
                            </li>
                            <li class="list05 list" onmouseover="bgcolor(5)" onmouseout="removecolor(5)">
                                <a href="./MyPageRemove.me" class="list_a5 selected">회원탈퇴</a>
                            </li>
                        </ul>
                    </nav>
                    
                </div>
            </section>
        </header>
        
        <section id="sub-imgbanner">
            <div class="section">
                <div class="sub-img-text">
                    <h3>My Page</h3>
                    <h2>
                        마이페이지
                    </h2>
                </div>
            </div>
        </section>
        <section id="sub-content">
            
            <div id="sub-con-body" class="section">
                <div class="notify">
                    <span class="removeimg"></span>
                    <div class="title">회원탈퇴에 앞서 <span class="textcolor">유의사항 및 안내</span>를 반드시 읽고 진행해 주세요.</div>
                </div>
                <form action="./MyPageRemoveAction.me" id="passwordchange">
                    <div class="explanation">
                        <ul>
                            <li>아이디 복구 불가 안내</li>
                            <li>회원탈퇴 진행 시 해당 아이디는 복구가 불가능합니다.</li>
                            <li>신중히 선택하신 후 결정해주세요.</li>
                        </ul>
                        <ul>
                            <li>
                                <input type="checkbox" name="agree" class="agree">
                            </li>
                        </ul>
                    </div>
                    <div class="explanation">
                        <ul>
                            <li>내정보 기록 삭제 안내</li>
                            <li>내 개인정보는 모두 삭제되며, 삭제된 데이터는 복구되지 않습니다.</li>
                            <li>필요한 데이터는 미리 백업을 해주세요.</li>
                        </ul>
                        <ul>
                            <li>
                                <input type="checkbox" name="agree" class="agree">
                            </li>
                        </ul>
                    </div>
                    <div class="explanation">
                        <ul>
                            <li>내일로 노트(여행플래너) 삭제 안내</li>
                            <li>내일로 노트(여행플래너)는 모두 삭제되며, 삭제된 데이터는 복구되지 않습니다.</li>
                            <li>신중히 선택하신 후 결정해주세요.</li>
                        </ul>
                        <ul>
                            <li>
                                <input type="checkbox" name="agree" class="agree">
                            </li>
                        </ul>
                    </div>
                    <div class="pwcheck">
                        <span>비밀번호 확인</span>
                        <input type="password" name="pw" class="pw">
                    </div>
                    <div class="btngroup">
                        <a href="./MyPageHome.me" class="previous">이전으로</a>
                        <input type="submit" value="탈퇴" class="submit" maxlength="32">
                    </div>
                </form>
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
    
<!--    <script src="./js/script.js"></script>-->
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
        
    $(document).ready(function(){
        $(":checkbox").labelauty();
        $(":radio").labelauty();
        $('#passwordchange').submit(function(){
            if($('input:checkbox[name=agree]:checked').length!=3){
                alert('모두 동의해주셔야 탈퇴가 진행됩니다.');
                return false;
            }
            if($('.pw').val().length==0){
                alert('비밀번호를 입력해주세요.');
                return false;
            }
            return true;
        })
    });
  </script>
</body>
</html>