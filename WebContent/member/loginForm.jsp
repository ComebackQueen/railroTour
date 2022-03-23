<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
 
    request.setCharacterEncoding("UTF-8");
 
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Railro Tour : 로그인</title>
    <link rel="stylesheet" href="./css/commen.css">
    <link rel="stylesheet" href="./css/loginform.css">

</head>
<body>
    <div id="wrap">
       <section>
           <span class="logo">
                <a href="./Main.me">
                    <img src="./jpg/RailroTour%20LOGO.png" alt="" width="250px">
                </a>
           </span>

        
            <form action="./MemberLoginAction.me" name="login_form" onsubmit="return loginCheck();" method="post">
                <input type="text" class="id" id="e_mail" placeholder="아이디" name="ID">
                <input type="password" class="pw" id="password"  placeholder="비밀번호" name="PW">
                <div class="discrution">
                   <br>
                    아이디 또는 비밀번호를 입력해주세요.
                </div>
                <input type="submit" value="로그인" class="login_bt">
                <div id="auto_login">  
                    <ul>
                        <li><input type="checkbox" value="auto_Login" name="auto_login">&nbsp;&nbsp;자동 로그인</li>
                        <li class="sign_up">
                            <a href="">비밀번호 찾기</a>
                        </li>
                    </ul>
                </div>
            </form>
       </section>
    </div>
        <script>
            function loginCheck(){
                theForm=document.login_form;
                
                if(login_form.ID.value==""){
                    alert("아이디를 입력하세요.");
                    return false;
                }
                else if(login_form.PW.value==""){
                    alert("비밀번호를 입력하세요.");
                    return false;
                }
            }
    </script>
</body>
</html>