<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
 
    request.setCharacterEncoding("UTF-8");
 
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Railro Tour : 회원가입</title>
    <link rel="stylesheet" href="./css/commen.css">
    <link rel="stylesheet" href="./css/sign_up_from.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
</head>
<body>
    <div id="wrap">
        <section id="content">
            <a href="./Main.me">
                <img src="./jpg/RailroTour LOGO.png" alt="" width="250px">
            </a>
            <div class="box">
               <form action="" name="agree" id="group_from" >
                   <ul>
                    <li class="All_agree">
                       <ul>
                           <li class="discrution">이용약관, 개인정보 수집 및 이용,</li>
                           <li class="discrution">위치정보 이용약관(선택), 프로모션 안내
                               <input type="checkbox" value="all" name="all-agree" class="all-agree">
                           </li>
                           <li class="discrution">메일 수신(선택)에 모두 동의합니다.</li>
                       </ul>
                        
                    </li>
                    <li class="each_agree">
                        <div class="agree1">
                           <span>
                               내일로 투어 이용약관 동의(필수)
                               <input type="checkbox" name="agree1" class="agree5">
                           </span> 
                            <textarea name="" id="" cols="60" rows="6" readonly>테스트
                            </textarea>
                        </div>
                        <div class="agree1">
                           <span>
                               내일로 투어 이용약관 동의(필수)
                               <input type="checkbox" name="agree2" class="agree2">
                           </span> 
                            <textarea name="" id="" cols="60" rows="6" readonly>테스트
                            </textarea>
                        </div>
                        <div class="agree1">
                           <span>
                               내일로 투어 이용약관 동의(필수)
                               <input type="checkbox" name="agree3" class="agree3">
                           </span> 
                            <textarea name="" id="" cols="60" rows="6" readonly>테스트
                            </textarea>
                        </div>
                        <div class="agree1">
                           <span>
                               내일로 투어 이용약관 동의(필수)
                               <input type="checkbox" name="agree4" class="agree4">
                           </span> 
                        </div>
                    </li>
                </ul>
               </form>
            </div>
            <div class="btn_box">
               <a href="../index.jsp">
                   <input type="button" value="비동의" class="no">
               </a>
				<a href="./MemberJoin2.me" onclick = "return click_check();">
                   <input type="button" value="동의" class="yes">
				</a>
            </div>
        </section>
    </div>
    <script type="text/javascript">
    $('.all-agree').click(function(){
        if($('.all-agree').is(":checked")==true){
            $('.agree5').attr("checked", true);
            $('.agree2').attr("checked", true);
            $('.agree3').attr("checked", true);
            $('.agree4').attr("checked", true);
        }
        else{
            $('.agree5').attr("checked", false);
            $('.agree2').attr("checked", false);
            $('.agree3').attr("checked", false);
            $('.agree4').attr("checked", false);
        }
    })
    
    function click_check(){
        if($('.agree5').is(":checked")==false){
            alert("모든 약관에 동의해주세요");
            return false;
        }
        else if($('.agree2').is(":checked")==false){
        	alert("모든 약관에 동의해주세요");
            return false;
        }
        else if($('.agree3').is(":checked")==false){
        	alert("모든 약관에 동의해주세요");
            return false;
        }
        else if($('.agree4').is(":checked")==false){
        	alert("모든 약관에 동의해주세요");
            return false;
        }
        else{
            return true;
        }

}
    </script>
</body>
</html>