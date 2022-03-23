<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="net.note.db.Note_Step2_Day_List_Bean"%>
<%@ page import="net.note.db.Note_Step2_Select_Bean"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="net.note.db.Note_Step2_ALL_INFO_Bean" %>
<%@ page import="net.note.db.Note_All_Plans_Bean" %>
<%String ID = null, PW = null;

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
	Note_Step2_Select_Bean note_Step2_Bean=(Note_Step2_Select_Bean)request.getAttribute("note_Step2_Bean");
	ArrayList<Note_Step2_Day_List_Bean> day_list=(ArrayList<Note_Step2_Day_List_Bean>)request.getAttribute("note_Step2_day_list");
	ArrayList<Note_Step2_ALL_INFO_Bean> Info_List=(ArrayList<Note_Step2_ALL_INFO_Bean>)request.getAttribute("Info_List");
	//ArrayList<Note_All_Plans_Bean> NoteInfo2_List=(ArrayList<Note_All_Plans_Bean>)request.getAttribute("NoteInfo2_List");
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

	String to = transFormat.format(note_Step2_Bean.getTravel_Start_Day()); //시작일 저장
	
	Date date=transFormat.parse(to);
	Calendar cal=Calendar.getInstance();
	
	cal.setTime(date);
	cal.add(Calendar.DATE, note_Step2_Bean.getTravel_Day()-1);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>내일로 노트 :: 상세일정 만들기</title>
    <link rel="stylesheet" href="./css/commen.css">
    <link rel="stylesheet" href="./css/Planner_STEP2.css?ver=12">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>

    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"> </script>
    
    <script src="./jqcloud/d3.js"></script>
    <script src="./jqcloud/d3.layout.cloud.js"></script>
    <script src="./jqcloud/d3.wordcloud.js"></script>
    <link rel="stylesheet" href="./css/overlay.css">
</head>
<body>
    <header>
            <img src="./planner_Step2_JPG/LOGO.png" alt="">
                <div class="Note_title"><%=note_Step2_Bean.getNote_Name() %></div>
                <div class="Note_edit_title">
                    <input type="text" value="" class="Note_title_input" maxlength="20">
                    <input type="submit" value="확인" name="Note_title_submit" class="Note_title_submit">
                </div>
            <a href="./Main.me"><input type="button" value="저장 후 닫기"></a>
    </header>
    <section>
        <div class="side_left">
            <div class="edit_day">
<!--전체일정란-->
                <button class="date_change" id="datepicker">
                   <div class="date_info">
                        <h1>
                            <%=to %>&nbsp;~&nbsp;<%=transFormat.format(cal.getTime()).substring(5) %>
                        </h1>
<!--
                        <div>
                            <img src="./planner_Step2_JPG/settings.png" alt="" width="15px">
                            <span>EDIT</span>
                        </div>
-->
                   </div>
                </button>
                <div class="day_arrange">
                <%for(int i=0; i<day_list.size(); i++) {%>
                    <button data-areacode=<%=day_list.get(i).getArea_code() %>
                    data-docode=<%=day_list.get(i).getDo_code() %>
                    data-lat=<%=day_list.get(i).getMapX() %>
                    data-lng=<%=day_list.get(i).getMapY() %>>
                        <ul>
                            <li class="day">
                                <div class="day_num">DAY<%=day_list.get(i).getTravel_Area_Day() %></div>
                                <div class="day_str"><%=day_list.get(i).getDay() %></div>
                            </li>
                            <li class="date">
                                <div class="date_num"><%=day_list.get(i).getDate() %></div>
                                <div class="date_area"><%=day_list.get(i).getTravel_Area_Name() %></div>
                            </li>
                        </ul>
                    </button>  
                    <%} %>  
                </div>
            </div>
            <div class="edit_route">
                 <div class="top">
                   <div class="day_group">
                        <span class="day">DAY<%=day_list.get(0).getTravel_Area_Day() %></span>
                        <span class="wall">|</span>
                        <span class="date"><%=day_list.get(0).getDate()%></span>
                        <span class="week">(<%=day_list.get(0).getDay() %>)</span>
                        <img src="./planner_Step2_JPG/refresh.png" alt="" width="20px" class="refresh">
                   </div>
                 </div>
                 <div class="bottom" id="route_add">
<!--
                     <div class="route">
                        <div class="curcle">1</div>
                         <img src="./jpg/bn01.jpg" alt="" width="80px" height="75px">
                         <ul class="route_info">
                             <li class="title">북촌 한옥 마을</li>
                             <li class="kind">관광지</li>
                             <li class="time">10:00 ~ 11:30</li>
                         </ul>
                        <div class="btn_group">
                             <img src="./jpg/cancel_btn.png" alt="" class="delete_btn">
                         </div>
                     </div>
-->
                     
                 </div>
            </div>
        </div>
        
        <div id="map"></div>
        <div class="route_search">
            <div class="search_area">
                <span class="area_name">
                    <ul>
                        <li class="area"></li>
<!--
                        <li class="area_change">
                            도시변경
                        </li>
-->
                    </ul>
                </span>
                <span class="search_form">
                    <input type="text" maxlength="30" placeholder="장소 검색" class="area_search">
                    <div class="checks">
                        <input type="radio" id="ex_rd1" name="search_type" value="area_search" checked>
                        <label for="ex_rd1">도시내 검색</label>
                        <input type="radio" id="ex_rd2" name="search_type" value="all_search">
                        <label for="ex_rd2">전체 검색</label> 
                    </div>
                </span>
                <span class="kind_select">
                    <div class="all"><img src="./planner_Step2_JPG/ALL2.png" alt="" width="50px"></div>
                    <div class="camera"><img src="./planner_Step2_JPG/camera1.png" alt="" width="50px"></div>
                    <div class="food"><img src="./planner_Step2_JPG/food1.png" alt="" width="50px"></div>
                    <div class="cart"><img src="./planner_Step2_JPG/cart1.png" alt="" width="50px"></div>
                    <div class="tag"><img src="./planner_Step2_JPG/tag1.png" alt="" width="50px"></div>
                </span>
            </div>
            <div class="search_result">
               <div class="all" id="search_data">
               <%for(int i=0; i<Info_List.size(); i++){ %>
                   <div class="search_data" 
                   		data-areacode=<%=Info_List.get(i).getAreacode() %>
                   		data-sigungucode=<%=Info_List.get(i).getSigungucode() %>
                   		data-cat1=<%=Info_List.get(i).getCat1() %>
                   		data-cat2=<%=Info_List.get(i).getCat2() %>
                   		data-cat3=<%=Info_List.get(i).getCat3() %>
                   		data-contentid=<%=Info_List.get(i).getContent_id() %>
                   		data-contenttypeid=<%=Info_List.get(i).getContenttype_id() %>
                   		data-lat=<%=Info_List.get(i).getMapx() %>
                   		data-lng=<%=Info_List.get(i).getMapy() %>
                   		data-addr1="<%=Info_List.get(i).getAddr1() %>"
                   		data-addr2="<%=Info_List.get(i).getAddr2() %>">
                    <div class="img">
                        <img src="<%=Info_List.get(i).getFirstimage() %>" alt="" width="100px" height="100px">
                    </div>
                    <ul class="info_group">
                       <input type="hidden" class="content_id" value="0">
                        <li class="title"><%=Info_List.get(i).getTitle() %></li>
                        <%if(Info_List.get(i).getContenttype_id()==12){ %>
                        	<li class="sub_title">관광지</li>
                        <%} %>
                        <%if(Info_List.get(i).getContenttype_id()==39) {%>
                        	<li class="sub_title">음식점</li>
                        <%} %>
                        <li class="comment_like_group">
                            <div>
                                <img src="./planner_Step2_JPG/comment.png" alt="" width="15px">
                                523
                            </div>
                            <div>|</div>
                            <div>
                            <img src="./planner_Step2_JPG/stamp.png" alt="" width="15px">
                                24
                            </div>
                        </li>
                        <div class="day_marker">
                            <!-- DAY1 -->
                        </div>
                    </ul>
                    <div class="add_btn">
                        <img src="./map_image/add.png" alt="" class="route_add_btn">
                    </div>
                </div>
                  <%} %>
               </div>
        
               <div class="hash_add">
                   <input type="text" placeholder="#해시태그" class="hash" maxlength="6">
                   
                   <div class="radio_group">
                        <label for="food"><img src="./planner_Step2_JPG/dish.png" alt="" width="50px"></label><input type="radio" name="kind" id="food" value="음식점">
                       <label for="tour"><img src="./planner_Step2_JPG/camera.png" alt="" width="50px"></label><input type="radio" name="kind" id="tour" value="관광지">
                       <label for="move"><img src="./planner_Step2_JPG/train.png" alt="" width="50px"></label><input type="radio" name="kind" id="move" value="이동">  
                   </div>
                    <textarea name="" id="" cols="35" rows="3" placeholder="메모" style="resize:none" maxlength="20"></textarea>
                    
                    <button class="hash_add_btn">추가하기</button>
               </div>
            </div>
        </div>
        <!-- 추가 정보 -->
        <ul class="More_Info" data-contentid=0 data-contenttypeid=0>
            <li class="top">
                <img src="./planner_Step2_JPG/right-arrow.png" alt="" width="30px" class="right">
                <img src="./planner_Step2_JPG/add.png" alt="" width="25px" class="route_add">
                <img src="./planner_Step2_JPG/more_Info_Stamp.png" alt="" width="25px" class="stamp">
            </li>
            <div class="info_scrolling">
                    <img src="http://tong.visitkorea.or.kr/cms/resource/96/1023896_image2_1.jpg" alt="" class="thumbnail" width="100%">

                <li class="Main_Title">
                    <h1>
                        강태분 할머니묵집
                    </h1>
                </li>
                <li class="info">
                    교태전은 왕비의 침전으로 광화문을 비롯하여 여섯 대문을 지나야 볼 수 있는 가장 깊은 곳에 위치하고 있다. 
세종 이후에 마련된 전각으로 경복궁 전각 중 가장 화려한 건축미가 돋보인다.
경복궁 내 임금의 침전인 강녕전과 함께 왕비의 침전인 교태전에는 용마루가 없는데 
이는 용은 곧 임금을 상징하는데 성스러운 공간인 침전에 용마루가 누르고 있으면 좋지 않다는 설에서 전해져오고 있고
또한, 교태전이 강녕전과 함께 용머리가 없다는 건 왕과 왕비를 동등하게 대우했다는 뜻한다
                </li>
                <li class="description">
                    <div class="tel">전화번호</div>
                    <div class="phone">010-1234-5678</div>
                    <div class="addr_desc">주소</div>
                    <div class="addr">대구대구대구대구</div>
                    <div class="open">영업시간</div>
                    <div class="open_time">21:00~24:00</div>
                    <div class="menu">대표메뉴</div>
                    <div class="menu_list">장어탕</div>
                    <div id="wordcloud">
<!--                     워드클라우드-->
                    </div>
                </li>
                
<!--
                <li class="btn_group">
                    <button>상세정보</button>
                    <button>리뷰</button>
                    <button>리뷰 작성</button>
                </li>
-->
            </div>
        </ul>
        
        
        
    </section>
    
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c75ebef98aa832875a335d779a7dc27a"></script>
    <script src="./js/Planner_STEP2_Daum_map.js?ver=71"></script>
    <script src="./jqcloud/example.words.js"></script>
    <script src="./js/Planner_STEP2.js?ver=97"></script>
    
    <script>
    	
		NoteID=<%=note_Step2_Bean.getTravel_ID()%>
		
        //워드클라우드 스크립트 
        d3.wordcloud()
        .size([320, 450])
        .fill(d3.scale.ordinal().range(["#884400", "#448800", "#888800", "#444400"]))
        .words(words)
        .start();
        
    </script>
</body>
</html>