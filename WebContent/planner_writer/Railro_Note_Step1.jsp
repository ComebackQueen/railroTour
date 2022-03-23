<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
   
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>내일로 노트 만들기 : 도시 선택</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
    <link rel="stylesheet" href="./css/commen.css?ver=1">
    <link rel="stylesheet" href="./css/Planner_STEP1.css?ver=1">
    <link rel="stylesheet" href="./datepicker/jquery-ui.css?ver=1">
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=im6orZEZ1htrK1ka94EI&submodules=geocoder"></script>
    <script type="text/javascript" src="./js/Planner_STEP1.js?ver=7"></script>
    <script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <script src="./datepicker/jquery-ui.js?ver=1"></script>
	
    
    <script type="text/javascript">
    	
    </script>
    
</head>
<body>
        <div id="create_event">
               <form id="editor-form" action="./Note1InsertAction.pl" onsubmit="return submit_check()" name="form1" method="post">
                    <div class="title_box">
                        <h1>출발일 선택</h1>
                        <img src="./jpg/close_btn.gif" alt="">
                    </div>
                    <div class="input_title">
                        <h1>노트 제목</h1>
                        <input type="text" maxlength="20" class="pn_title" placeholder="수기수기한 여행!" name="title">
                    </div>
                    <div class="input_start_day">
                       <h1>출발일</h1>
                        <input type="text" id="datepicker" class="date" placeholder="날짜" name="calendar" readonly>
                    </div>
                    <div class="input_travel_day">
                        <h1>여행일수/인원</h1>
                        <input type="radio" name="travel_day" value="5" class="five">
                        <label for="travel_day" class="five_day">5일</label>
                        <input type="radio" name="travel_day" value="7" class="seven">
                        <label for="travel_day" class="seven_day">7일</label>
                        <select name="person" id="person_cnt">
                            <option value="1">1명</option>
                            <option value="2">2명</option>
                            <option value="3">3명</option>
                            <option value="4">4명</option>
                            <option value="5">5명</option>
                            <option value="6">6명</option>
                            <option value="7">7명</option>
                            <option value="8">8명</option>
                            <option value="9">9명</option>
                            <option value="10">10명</option>
                        </select>
                    </div>
                    <div class="input_travel_tema">
                        <h1>여행테마</h1>
                       
                           <div>
                                <input type="radio" id="tour" name="tema_select" value="관광">
                                <label for="tour" value="관광">
                                    <img src="./travel_tema/관광.png" alt="">
                                    <h1>관광</h1>
                                </label>
                                <input type="radio" id="experience" name="tema_select" value="체험">
                                <label for="experience" value="체험">
                                    <img src="./travel_tema/체험.png" alt="">
                                    <h1>체험</h1>
                                </label>
                           </div>
                           <div>
                               <input type="radio" id="food" name="tema_select" value="식사">
                                <label for="food" value="식사">
                                    <img src="./travel_tema/식사.png" alt="">
                                    <h1>음식</h1>
                                </label>
                                <input type="radio" id="rest" name="tema_select" value="휴식">
                               <label for="rest" value="휴식">
                                    <img src="./travel_tema/휴식.png" alt="">
                                    <h1>휴식</h1>
                                </label>
                           </div>
                        </div>
                        <div class="save">
                            <input type="submit" value="저장">
                        </div>
                        <input type="hidden" class="array_day" name="array_day">
                        <input type="hidden" class="array_value" name="array_value">
               </form>
        </div>
        <header>
           <a href="#"><img src="./jpg/RailroTour%20LOGO.png" alt=""></a>
            
                <ul class="title">
                    <li>1. 도시 선택</li>
                    <img src="./jpg/arrow.png" alt="">
                    <li>2. 상세 일정 만들기</li>
                </ul>
                <a href="javascript:history.back()"><input type="button" value="닫기" class="close"></a>
        </header>

        <section>
            <div id="left">
                <span class="left_sub1">
                    <input type="button" value="모두 보기" id="ALL_VIEW">
                    <input type="button" value="전라북도" id="BUK_DO_VIEW">
                    <input type="button" value="전라남도" id="NAM_DO_VIEW">
                    <input type="button" value="광주광역시" id="Gwangju_VIEW">
                </span>
                <span class="left_sub2 all">
                   <h3><span>모두 보기</span></h3>
                    <div class="item_list">
                      <div class="item_box bukdo" data-no="1" data-ci_name="전라북도" data-lat="35.7175" data-lng="127.153">
                             <div class="img_box">
                               <img src="./jpg/buk_do.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title" id="test1">전라북도</div>
                               <div class="info_sub_title">Jeollabuk-do</div>
                           </div>
                           <div class="clear"></div>
                      </div>
                      <div class="item_box namdo" data-no="2" data-ci_name="전라남도" data-lat="34.8679" data-lng="126.991">
                             <div class="img_box">
                               <img src="./jpg/nam_do.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box" >
                               <div class="info_title">전라남도</div>
                               <div class="info_sub_title">Jeollanam-do</div>
                           </div>
                           <div class="clear"></div>
                      </div>
                      <div class="item_box gwangjudo" data-no="3" data-ci_name="광주" data-lat="35.153946" data-lng="126.834872">
                             <div class="img_box">
                               <img src="./map_image/%EA%B4%91%EC%A3%BC.jpg" alt="광주" width="62px" height="55px">
                           </div>
                           <div class="info_box" data-lat="35.153946" data-lng="126.834872">
                               <div class="info_title">광주</div>
                               <div class="info_sub_title">Gwangju</div>
                           </div>
                           <div class="clear">
                           </div>
                      </div>
                        
                   </div>
                   
                </span>
                <span class="left_sub2 buk_do">
                   <h3><span>전라북도</span></h3>
                    <div class="item_list">
                      <div class="item_box buk_do1" data-no="1" data-ci_name="고창군" data-lat="35.434392" data-lng="126.6965415" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라북도/고창군.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">고창군</div>
                               <div class="info_sub_title">Gochang-gun</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="고창군">
                           </div>
                      </div>
                      <div class="item_box buk_do2" data-no="2" data-ci_name="군산시" data-lat="35.999346" data-lng="126.758714" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라북도/군산.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">군산시</div>
                               <div class="info_sub_title">Gunsan-si</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="군산시">
                           </div>
                      </div>
                      <div class="item_box buk_do3" data-no="3" data-ci_name="김제시" data-lat="35.792445" data-lng="126.900767" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라북도/김제.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">김제시</div>
                               <div class="info_sub_title">Gimje-si</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="김제시">
                           </div>
                      </div>
                      <div class="item_box buk_do4" data-no="4" data-ci_name="남원시" data-lat="35.411288" data-lng="127.359174" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라북도/남원.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">남원시</div>
                               <div class="info_sub_title">Namwon-si</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="남원시">
                           </div>
                      </div> 
                       <div class="item_box buk_do5" data-no="5" data-ci_name="무주군" data-lat="36.0047077" data-lng="127.6620594" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라북도/무주.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">무주군</div>
                               <div class="info_sub_title">Muju-gun</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="무주군">
                           </div>
                      </div> 
                       <div class="item_box buk_do6" data-no="6" data-ci_name="부안군" data-lat="35.7115502" data-lng="126.7223125" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라북도/부안.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">부안군</div>
                               <div class="info_sub_title">Buan-gun</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="부안군">
                           </div>
                      </div>  
                       <div class="item_box buk_do7" data-no="7" data-ci_name="순창군" data-lat="35.376789" data-lng="127.139057" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라북도/순창.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">순창군</div>
                               <div class="info_sub_title">Sunchang-gun</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="순창군">
                           </div>
                      </div>   
                       <div class="item_box buk_do8" data-no="8" data-ci_name="완주군" data-lat="35.874408" data-lng="126.9033637" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라북도/완주.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">완주군</div>
                               <div class="info_sub_title">Wanju-gun</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="완주군">
                           </div>
                      </div> 
                       <div class="item_box buk_do9" data-no="9" data-ci_name="익산시" data-lat="35.940257" data-lng="126.944102" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라북도/익산.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">익산시</div>
                               <div class="info_sub_title">Iksan-si</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="익산시">
                           </div>
                      </div>
                       <div class="item_box buk_do10" data-no="10" data-ci_name="임실군" data-lat="35.632905" data-lng="127.287716" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라북도/임실.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">임실군</div>
                               <div class="info_sub_title">Imsil-gun</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="임실군">
                           </div>
                      </div>        
                       <div class="item_box buk_do11" data-no="11" data-ci_name="장수군" data-lat="35.6545488" data-lng="127.2561564" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라북도/장수.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">장수군</div>
                               <div class="info_sub_title">Jangsu-gun</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="장수군">
                           </div>
                      </div>             
                       <div class="item_box buk_do12" data-no="12" data-ci_name="전주시" data-lat="35.84977" data-lng="127.159604" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라북도/전주.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">전주시</div>
                               <div class="info_sub_title">Jeonju-si</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="전주시">
                           </div>
                      </div>                       
                       <div class="item_box buk_do13" data-no="13" data-ci_name="정읍시" data-lat="35.575578" data-lng="126.8409779" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라북도/정읍.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">정읍시</div>
                               <div class="info_sub_title">Jeongeup-si</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="정읍시">
                           </div>
                      </div>
                       <div class="item_box buk_do14" data-no="14" data-ci_name="진안군" data-lat="35.764138" data-lng="127.4286664" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라북도/진안.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">진안군</div>
                               <div class="info_sub_title">Jinan-gun</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="진안군">
                           </div>
                      </div>                
                                                
                                                                  
                                        
                   </div>
                </span>
                <span class="left_sub2 nam_do">
                   <h3><span>전라남도</span></h3>
                    <div class="item_list nam_do">
                      <div class="item_box nam_do1" data-no="1" data-ci_name="강진군" data-lat="34.6274176" data-lng="126.6989386" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라남도/강진.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">강진군</div>
                               <div class="info_sub_title">Gangjin-gun</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="강진군">
                           </div>
                      </div>
                      <div class="item_box nam_do2" data-no="2" data-ci_name="고흥군" data-lat="34.6076643" data-lng="127.2803746" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라남도/고흥.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">고흥군</div>
                               <div class="info_sub_title">Goheung-gun</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="고흥군">
                           </div>
                      </div>
                      <div class="item_box nam_do3" data-no="3" data-ci_name="곡성군" data-lat="35.283603" data-lng="127.301563" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라남도/곡성.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">곡성군</div>
                               <div class="info_sub_title">Gokseong-gun</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="곡성군">
                           </div>
                      </div>
                      <div class="item_box nam_do4" data-no="4" data-ci_name="광양시" data-lat="34.95732" data-lng="127.588418" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라남도/광양.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">광양시</div>
                               <div class="info_sub_title">Gwangyang-si</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="광양시">
                           </div>
                      </div>
                      <div class="item_box nam_do5" data-no="5" data-ci_name="구례군" data-lat="35.1634829" data-lng="127.417548" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라남도/구례.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">구례군</div>
                               <div class="info_sub_title">Gurye-gun</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="구례군">
                           </div>
                      </div>
                      <div class="item_box nam_do6" data-no="6" data-ci_name="나주시" data-lat="35.014269" data-lng="126.7148" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라남도/나주.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">나주시</div>
                               <div class="info_sub_title">Naju-si</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="나주시">
                           </div>
                      </div>
                      <div class="item_box nam_do7" data-no="7" data-ci_name="담양군" data-lat="35.3010227" data-lng="126.9305201" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라남도/담양.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">담양군</div>
                               <div class="info_sub_title">Damyang-gun</div>
                           </div>
                           <div class="clear"><img src="./map_image/add.png" alt="" data-ci_name="담양군"></div>
                      </div>
                      <div class="item_box nam_do8" data-no="8" data-ci_name="목포시" data-lat="34.791143" data-lng="126.384398" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라남도/목포.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">목포시</div>
                               <div class="info_sub_title">Mokpo-si</div>
                           </div>
                           <div class="clear"><img src="./map_image/add.png" alt="" data-ci_name="목포시"></div>
                      </div>
                          <div class="item_box nam_do9" data-no="9" data-ci_name="무안군" data-lat="34.962685" data-lng="126.514795" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라남도/무안.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">무안군</div>
                               <div class="info_sub_title">Muan-gun</div>
                           </div>
                           <div class="clear"><img src="./map_image/add.png" alt="" data-ci_name="무안군"></div>
                      </div> 
                        <div class="item_box nam_do10" data-no="10" data-ci_name="보성군" data-lat="34.766993" data-lng="127.079473" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라남도/보성.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">보성군</div>
                               <div class="info_sub_title">Boseong-gun</div>
                           </div>
                           <div class="clear"><img src="./map_image/add.png" alt="" data-ci_name="보성군"></div>
                      </div>
                        <div class="item_box nam_do11" data-no="11" data-ci_name="순천시" data-lat="34.945789" data-lng="127.501066" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라남도/순천.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">순천시</div>
                               <div class="info_sub_title">Suncheon-si</div>
                           </div>
                           <div class="clear"><img src="./map_image/add.png" alt="" data-ci_name="순천시"></div>
                      </div>     
                       <div class="item_box nam_do12" data-no="12" data-ci_name="신안군" data-lat="34.8835781" data-lng="126.3129983" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라남도/신안.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">신안군</div>
                               <div class="info_sub_title">Sinan-gun</div>
                           </div>
                           <div class="clear"><img src="./map_image/add.png" alt="" data-ci_name="신안군"></div>
                      </div>   
                        <div class="item_box nam_do13" data-no="13" data-ci_name="여수시" data-lat="34.7525795" data-lng="127.743613" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라남도/여수.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">여수시</div>
                               <div class="info_sub_title">Yeosu-si</div>
                           </div>
                           <div class="clear"><img src="./map_image/add.png" alt="" data-ci_name="여수시"></div>
                      </div>   
                         <div class="item_box nam_do14" data-no="14" data-ci_name="영광군" data-lat="35.2968477" data-lng="126.3500052" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라남도/영광.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">영광군</div>
                               <div class="info_sub_title">Yeonggwang</div>
                           </div>
                           <div class="clear"><img src="./map_image/add.png" alt="" data-ci_name="영광군"></div>
                      </div>   
                          <div class="item_box nam_do15" data-no="15" data-ci_name="영암군" data-lat="34.7991006" data-lng="126.6863274" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라남도/영암.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">영암군</div>
                               <div class="info_sub_title">Yeongam-gun</div>
                           </div>
                           <div class="clear"><img src="./map_image/add.png" alt="" data-ci_name="영암군"></div>
                      </div> 
                          <div class="item_box nam_do16" data-no="16" data-ci_name="완도군" data-lat="34.3174975" data-lng="126.7439985" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라남도/완도.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">완도군</div>
                               <div class="info_sub_title">Wando-gun</div>
                           </div>
                           <div class="clear"><img src="./map_image/add.png" alt="" data-ci_name="완도군"></div>
                      </div>
                          <div class="item_box nam_do17" data-no="17" data-ci_name="장성군" data-lat="35.299989" data-lng="126.778001" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라남도/장성.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">장성군</div>
                               <div class="info_sub_title">Jangseong-gun</div>
                           </div>
                           <div class="clear"><img src="./map_image/add.png" alt="" data-ci_name="장성군"></div>
                      </div>
                          <div class="item_box nam_do18" data-no="18" data-ci_name="장흥군" data-lat="34.6771745" data-lng="126.8398232" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라남도/장흥.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">장흥군</div>
                               <div class="info_sub_title">Jangheung-gun</div>
                           </div>
                           <div class="clear"><img src="./map_image/add.png" alt="" data-ci_name="장흥군"></div>
                      </div>
                          <div class="item_box nam_do19" data-no="19" data-ci_name="진도군" data-lat="34.4787235" data-lng="126.261389" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라남도/진도.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">진도군</div>
                               <div class="info_sub_title">Jindo-gun</div>
                           </div>
                           <div class="clear"><img src="./map_image/add.png" alt="" data-ci_name="진도군"></div>
                      </div>
                          <div class="item_box nam_do20" data-no="20" data-ci_name="함평군" data-lat="35.023912" data-lng="126.536898" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라남도/함평.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">함평군</div>
                               <div class="info_sub_title">Hampyeong-gun</div>
                           </div>
                           <div class="clear"><img src="./map_image/add.png" alt="" data-ci_name="함평군"></div>
                      </div>
                          <div class="item_box nam_do21" data-no="21" data-ci_name="해남군" data-lat="34.5705739" data-lng="126.6076056" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라남도/해남.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">해남군</div>
                               <div class="info_sub_title">Haenam-gun</div>
                           </div>
                           <div class="clear"><img src="./map_image/add.png" alt="" data-ci_name="해남군"></div>
                      </div>
                          <div class="item_box nam_do22" data-no="22" data-ci_name="화순군" data-lat="35.051548" data-lng="126.959535" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라남도/화순.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">화순군</div>
                               <div class="info_sub_title">Hwasun-gun</div>
                           </div>
                           <div class="clear"><img src="./map_image/add.png" alt="" data-no="22" data-ci_name="화순군"></div>
                      </div>
                      
                      
                   </div>
                </span>
            	<span class="left_sub2 Gwangju_do">
                   <h3><span>광주</span></h3>
                    <div class="item_list Gwangju">
                      <div class="item_box Gwangju1" data-no="1" data-ci_name="광주 광산구" data-lat="35.1394638" data-lng="126.79371830000002" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라남도/강진.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">광주 광산구</div>
                               <div class="info_sub_title">Gwangju</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="광주 광산구">
                           </div>
                      </div>
                      <div class="item_box Gwangju2" data-no="2" data-ci_name="광주 남구" data-lat="35.1221068" data-lng="126.90795789999993" data-station="버스">
                             <div class="img_box">
                               <img src="./map_image/전라남도/고흥.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">광주 남구</div>
                               <div class="info_sub_title">Gwangju</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="광주 남구">
                           </div>
                      </div>
                      <div class="item_box Gwangju3" data-no="3" data-ci_name="광주 동구" data-lat="35.1461042" data-lng="126.92312279999999" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라남도/곡성.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">광주 동구</div>
                               <div class="info_sub_title">Gwangju</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="광주 동구">
                           </div>
                      </div>
                      <div class="item_box Gwangju4" data-no="4" data-ci_name="광주 북구" data-lat="35.1740167" data-lng="126.9119842" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라남도/광양.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">광주 북구</div>
                               <div class="info_sub_title">Gwangju</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="광주 북구">
                           </div>
                      </div>
                      <div class="item_box Gwangju5" data-no="5" data-ci_name="광주 서구" data-lat="35.1528248" data-lng="126.89109770000005" data-station="기차">
                             <div class="img_box">
                               <img src="./map_image/전라남도/구례.jpg" alt="" width="62px" height="55px">
                           </div>
                           <div class="info_box">
                               <div class="info_title">광주 서구</div>
                               <div class="info_sub_title">Gwangju</div>
                           </div>
                           <div class="clear">
                               <img src="./map_image/add.png" alt="" data-ci_name="광주 서구">
                           </div>
                      </div>
                   </div>
                </span>
            </div>
            <div id="map">
            </div>
            <div id="right">
                <h3>여행 지역</h3>
                <form action="">
                    <div class="area_list" id="area_list_box">
    <!--
                        <ul class="area_item_box" draggable="true">
                            <li class="cancel_btn">
                                <img src="./jpg/cancel_btn.png" alt="" onclick="delete()">
                            </li>
                            <li class="area_title">
                                여수
                            </li>
                            <div class="day_custom">
                                <li class="minus" onclick="change(-1)"><img src="./jpg/minus.png" alt=""></li>
                                <li>
                                    <input type="text" value="1" class="day" readonly>
                                </li>
                                <li class="plus" onclick="change(1)"><img src="./jpg/plus.png" alt=""></li>
                            </div>
                        </ul>
    -->
                    
                    </div>
                <input type="button" value="상세 일정 만들기" class="create">
                </form>
            </div>

        </section>
<script src="./js/naver_map.js?ver=5"></script>
</body>
</html>