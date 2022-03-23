<%@page import="net.MyPage.db.MemberINFO_Bean"%>
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
   
   MemberINFO_Bean member=(MemberINFO_Bean)request.getAttribute("member");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>마이페이지 : 내정보 관리</title>
    <link rel="stylesheet" href="./css/commen.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/mypage.css?ver=2">
   <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
                                <a href="./MyPageHome.me" class="list_a1">내 정보 홈</a>
                                
                            </li>
                            <li class="list02 list" onmouseover="bgcolor(2)" onmouseout="removecolor(2)">
                                <a href="./MyPageManagement.me" class="list_a2 selected">내 정보 관리</a>
                            </li>
                            <li class="list03 list" onmouseover="bgcolor(3)" onmouseout="removecolor(3)">
                                <a href="./MyPagePW.me" class="list_a3 ">비밀번호 변경</a>
                            </li>
                            <li class="list05 list" onmouseover="bgcolor(5)" onmouseout="removecolor(5)">
                                <a href="./MyPageRemove.me" class="list_a5">회원탈퇴</a>
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
                   <img src="./mypage_img/notification.png" alt="" width="30px" style="    padding-top: 25px;
    padding-bottom: 25px; float: left">
                    <div class="title">내 정보를 <span class="textcolor">최신 정보로 관리</span>해주세요.</div>
                </div>
                <form action="" id="management" enctype="multipart/form-data">
                   <ul>
                       <li class="profile">
                           <span class="text">프로필 사진</span>
                           <img src="<%=member.getProfile() %>" alt="">
                           <input type="file" id="imgchange" accept="image/*" name="imgfile" hidden>
                           <label for="imgchange" class="imgchange">이미지 변경</label>
                       </li>
                       <li>
                           <span class="text">아이디</span>
                           <div><%=member.getEmail_id() %></div>
                       </li>
                       <li>
                           <span class="text">이름</span>
                           <div><%=member.getName() %></div>
                       </li>
                       <li>
                           <span class="text">닉네임/연락처</span>
                           <div><%=member.getNikname() %></div>
                           <div><%=member.getPhone() %></div>
                       </li>
                       <li>
                           <span class="text">성별</span>
                           <div><%=member.getSex() %></div>
                       </li>
                   </ul>
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
    
    <script>

    
     $(document).ready(function(){
      	  $('#favorite').on('click', function(e) {
   		    var bookmarkURL = window.location.href;
   		    var bookmarkTitle = document.title;
   		    var triggerDefault = false;

   		    if (window.sidebar && window.sidebar.addPanel) {
   		        // Firefox version &lt; 23
   		        window.sidebar.addPanel(bookmarkTitle, bookmarkURL, '');
   		    } else if ((window.sidebar && (navigator.userAgent.toLowerCase().indexOf('firefox') < -1)) || (window.opera && window.print)) {
   		        // Firefox version &gt;= 23 and Opera Hotlist
   		        var $this = $(this);
   		        $this.attr('href', bookmarkURL);
   		        $this.attr('title', bookmarkTitle);
   		        $this.attr('rel', 'sidebar');
   		        $this.off(e);
   		        triggerDefault = true;
   		    } else if (window.external && ('AddFavorite' in window.external)) {
   		        // IE Favorite
   		        window.external.AddFavorite(bookmarkURL, bookmarkTitle);
   		    } else {
   		        // WebKit - Safari/Chrome
   		        alert((navigator.userAgent.toLowerCase().indexOf('mac') != -1 ? 'Cmd' : 'Ctrl') + '+D 를 이용해 이 페이지를 즐겨찾기에 추가할 수 있습니다.');
   		    }

   		    return triggerDefault;
   		});
      	  $('#imgchange').on('change', function(e){
      		if($(this).val()!=""){
                var ext=$(this).val().split(".").pop().toLowerCase();
                if($.inArray(ext, ["gif", "jpg", "jpeg", "png"])==-1){
                    swal('이미지 파일만 업로드 해주세요.');
                    $(this).val('');
                    return;
                }
                var fileSize=this.files[0].size;
                var maxSize=(1024*1024)*10;
                if(fileSize>maxSize){
                    swal('파일용량 10MB를 초과했습니다.');
                    $(this).val('');
                    return;
                }
                var formData=new FormData($('#management')[0]);
                $.ajax({
            		type:'POST',
            		url:'./MyPageIMGChange.me',
            		data:formData,
            		async: true,
                    processData : false,
                    contentType : false,
            		success:function(data){
            			console.log('파일업로드 성공');
            			console.log(data);
            			$('.profile img').attr('src', data);
            		},
            		error:function(data){
            			alert('파일 업로드 실패');
            			console.log('파일 업로드 실패');
            		}
            	})
                $(this).val('');
            }
      	  })
      })
  </script>
</body>
</html>