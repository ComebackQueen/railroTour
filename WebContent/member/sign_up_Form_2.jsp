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
    <link rel="stylesheet" href="./css/sign_up.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
</head>
<body>
    <div id="wrap">
        <section id="content">
            <a href="./Main.me">
                <img src="./jpg/RailroTour%20LOGO.png" alt="" width="250px">
            </a>
            <form action="./MemberJoinAction.me" name="sign_up_form" method="post" onsubmit="return check_sign_up();">
                <div class="form1">
                    <input type="text" placeholder="이메일ID" name="MEMBER_ID">
                    <input type="password" placeholder="비밀번호" name="MEMBER_PW">
                    <input type="password" placeholder="비밀번호 재확인" name="pw_check">
                </div>
                <div class="form2">
                    <input type="text" placeholder="이름" name="MEMBER_NAME">
                    <input type="text" placeholder="닉네임" name="MEMBER_NIKNAME">
                    <div class="sex">
                        <input type="radio" id="1" class="rad" name="MEMBER_GENDER" value="man" />
                        <label for="1" class="sex_radio">남자</label>
                        <input type="radio" id="2" class="rad" name="MEMBER_GENDER" value="woman" />
                        <label for="2" class="sex_radio">여자</label>
                    </div>
                    <input type="text" name="MEMBER_PHONE" id="cellPhone" placeholder="전화번호" maxlength="13" class="number"/>
                </div>

                <input type="submit" value="가입하기" class="sign_up">
            </form>
        </section>
    </div>
    <script>
    function autoHypenPhone(str){
            str = str.replace(/[^0-9]/g, '');
            var tmp = '';
            if( str.length < 4){
                return str;
            }else if(str.length < 7){
                tmp += str.substr(0, 3);
                tmp += '-';
                tmp += str.substr(3);
                return tmp;
            }else if(str.length < 11){
                tmp += str.substr(0, 3);
                tmp += '-';
                tmp += str.substr(3, 3);
                tmp += '-';
                tmp += str.substr(6);
                return tmp;
            }else{              
                tmp += str.substr(0, 3);
                tmp += '-';
                tmp += str.substr(3, 4);
                tmp += '-';
                tmp += str.substr(7);
                return tmp;
            }
            return str;
        }

        var cellPhone = document.getElementById('cellPhone');
        cellPhone.onkeyup = function(event){
            event = event || window.event;
            var _val = this.value.trim();
            this.value = autoHypenPhone(_val) ;
        }
            //아래는 회원가입 유효성 체크
        function check_sign_up(){
            theForm=document.sign_up_form;
            
            if(theForm.MEMBER_ID.value==""){
                alert("아이디가 입력되지 않았습니다. 확인해주세요");
                return false;
            }
            else if(theForm.MEMBER_PW.value==""){
                alert("비밀번호가 입력되지 않았습니다. 확인해주세요");
                return false;
            }
            else if(theForm.pw_check.value==""){
                alert("비밀번호 확인이 입력되지 않았습니다. 확인해주세요");
                return false;
            }
            else if(theForm.MEMBER_NAME.value==""){
                alert("이름이 입력되지 않았습니다. 확인해주세요");
                return false;
            }
            else if(theForm.MEMBER_NIKNAME.value==""){
                alert("닉네임이 입력되지 않았습니다. 확인해주세요");
                return false;
            }
            else if(theForm.MEMBER_GENDER.value==""){
                alert("성별이 선택되지 않았습니다. 확인해주세요");
                return false;
            }
            else if(theForm.MEMBER_PHONE.value==""){
                alert("전화번호가 입력되지 않았습니다. 확인해주세요");
                return false;
            }
        }
    </script>
    <script src="./js/script.js">
    </script>
</body>
</html>