package etc.function;

import java.util.ArrayList;

public class JSONPassing {

	
	public static void arrayPassing(ArrayList<EasyMap> emList, String json, String parents, ArrayList<String> parentsFriend, String keyName) {
        boolean stringStartOn=false,stringEndOn=false,bigStartOn=false,bigEndOn=false,centerStartOn=false,centerEndOn=false,comma=false,separator=false;
        boolean keyOn=false,attributeOn=true,attributeInt=false,attributeString=false,attributeObject=false,attributeArray=false;
        String tempKey = keyName;
        StringBuffer tempAttribute= new StringBuffer("");
        int objectStartCount=0;
        int arrayStartCount=0;
        EasyMap tempEm = new EasyMap();
        tempEm.setParents(parents);
        tempEm.setParentsFriend(parentsFriend);

        //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        //System.out.println("받은제이슨:"+json);
        //, 구분자이다. 일단 키값은 item 으로 받아서 고정이된다.
        // 중간은 , 로 치고 마지막은 ? 0~ size()-1  i+1==size() 같다면 마지막이다.  객체라면 이미 담앗고 아닌애들이 문제 문자나 숫자나 문자도 문제는 x 숫자가 문제임
        //if(attributeInt)
        // 	if(i+1 == json.length())
        //    put 하고
        // 키는 지우고 속성값 온하는 기준을 : 에서 , 로 바꾼다.
        //애초에 배열이라는게 속성의 집단이라.. 배열이 들어오면 키는 고정 속성은 처음 온 그리고 여기서는 ,다음이 속성이온이다.

        //여기서는 특이하게 "{ 로 받고 }" 형식으로 주더라 아마 jsonArray를 잘못쓴거겟지 ?
        /*
        StringBuffer tempString = new StringBuffer("");
        boolean start=false,end=false,nomal=false;

        for(int o=0; o<json.length(); o++) {


            nomal=true;
            if(json.charAt(o)=='"')
                if(o+1<json.length())
                    if(json.charAt(o+1)=='{') {
                        o++;
                        start=true;
                    }

            if(json.charAt(o)=='}')
                if(o+1<json.length())
                    if(json.charAt(o+1)=='"')
                        end=true;

            if(start) {

                tempString.append(json.charAt(o));
                start=false;
                nomal=false;
            }
            if(end) {

                tempString.append(json.charAt(o));
                o++;
                end=false;
                nomal=false;
            }
            if(!start && !end && nomal)
            {
                tempString.append(json.charAt(o));
            }
        }
        json=tempString.toString();
        */
        for(int i=0; i<json.length(); i++) {
            //System.out.println("받은문자"+json.charAt(i));
            //---------------------------------------------> 닫는거는 위로
            if(json.charAt(i)=='}' && centerStartOn) {
                objectStartCount--;
                if(objectStartCount==0) {
                    //System.out.println("객체닫기");
                    centerEndOn=true;
                }
            }

            if(json.charAt(i)==']' && bigStartOn) {
                arrayStartCount--;
                if(arrayStartCount==0) {
                    //System.out.println("배열닫기");
                    bigEndOn=true;
                }
            }


            if(stringStartOn && !attributeObject && !attributeArray)
                if(json.charAt(i)=='"') {
                    stringEndOn=true;
                    //System.out.println("문끝");
                }


            if(json.charAt(i)==','  && !bigStartOn && !centerStartOn)
                comma=true;

            if(json.charAt(i)==':'  && !bigStartOn && !centerStartOn) {
                separator=true;
                //System.out.println("구분자온");
            }

            //--------------------------------------------->




            //---------------------------------------------> 키 처리부분은 1.키끄기 2.키담기 3. key 키기
            //--------------------------------------------->



            //---------------------------------------------> 속성 처리부분은 1.속성끄기(3개) 2.속성구분(3개) 3.속성담기(3개?1개)  4.속성키기

            if(stringStartOn && stringEndOn && attributeString && !attributeOn && !keyOn) { //문자값 끄기

                attributeString=false;
                tempEm.put(tempKey,tempAttribute.toString(),"String");
                //System.out.println("속성이문자네 키값:"+tempKey.toString()+"   속성값:"+tempAttribute.toString());
                tempAttribute=new StringBuffer("");
            }
            if(!stringStartOn && !stringEndOn && attributeInt && !attributeOn && !keyOn) { //보통 정수 뒤에 ,점이라면 계속 진행이고 아니라면 다른놈이니 일단 저장
                if(json.charAt(i)=='0' || json.charAt(i)=='1' || json.charAt(i)=='2' || json.charAt(i)=='3' || json.charAt(i)=='4' || json.charAt(i)=='5' ||
                        json.charAt(i)=='6' || json.charAt(i)=='7' || json.charAt(i)=='8' || json.charAt(i)=='9' || json.charAt(i)=='.') //여기서는 더블도 그냥 인트로 친다 문자열은 아니고 객체와배열은 아니니까
                    System.out.print("");
                else {
                    attributeInt =false;
                    tempEm.put(tempKey,tempAttribute.toString(),"Int");
                    //System.out.println("속성이인트네 키값:"+tempKey.toString()+"   속성값:"+tempAttribute.toString());
                    tempAttribute=new StringBuffer("");
                }
            }
            if(attributeObject && centerStartOn && centerEndOn && !attributeOn) {
                attributeObject=false;
                centerStartOn=false;
                centerEndOn=false;
                tempEm.put(tempKey,tempAttribute.toString(),"Object");
               // System.out.println("속성이객체네 키값:"+tempKey.toString()+"   속성값:"+tempAttribute.toString());
                tempAttribute=new StringBuffer("");
            }
            if(attributeArray && bigStartOn && bigEndOn && !attributeOn) {
                attributeArray=false;
                bigStartOn=false;
                bigEndOn=false;
                //tempAttribute.append(json.charAt(i));
                tempEm.put(tempKey,tempAttribute.toString(),"Array");
                //System.out.println("속성이배열이네 키값:"+tempKey.toString()+"   속성값:"+tempAttribute.toString());
                tempAttribute=new StringBuffer("");
            }

            if(attributeOn && stringStartOn && !bigStartOn && !centerStartOn &&!attributeString && !attributeInt && !attributeObject && !attributeArray) {//문자속성값이라면
                attributeString=true;
                attributeOn=false;
               // System.out.println("문자속성온");
            }

            if(attributeOn && !stringStartOn && bigStartOn && !centerStartOn &&!attributeString && !attributeInt && !attributeObject && !attributeArray) {
                attributeArray=true;
                attributeOn=false;
               // System.out.println("배열속성온");
            }

            if(attributeOn && !stringStartOn && !bigStartOn && centerStartOn &&!attributeString && !attributeInt && !attributeObject && !attributeArray) {
                attributeObject=true;
                attributeOn=false;
                //System.out.println("객체속성온");
            }


            if(attributeOn && !stringStartOn && !bigStartOn && !centerStartOn &&!attributeString && !attributeInt && !attributeObject && !attributeArray  && json.charAt(i)!='{' && json.charAt(i)!='[' && json.charAt(i)!='"') { //인트가 마지막이다.
                attributeInt=true;
                attributeOn=false;
                //System.out.println("정수속성온");
            }

            //스트링은 stringStartOn이 객체들은 bigStartOn 객체는 centerStartOn 온된다. 하지만 인트는 구분자뒤에 아무것도없다.



            if((attributeInt || attributeString || attributeObject || attributeArray) && !attributeOn)  //정수값 넣기
                tempAttribute.append(json.charAt(i));

            //if(i<50)
            //System.out.println("속성조건문:"+separator+"문자시작:"+stringStartOn+"속성시작:"+attributeOn+"키시작:"+keyOn);
            if(comma && !stringStartOn && !attributeOn && !keyOn) {//4.키는곳
                attributeOn=true;
                //System.out.println("속성온");
            }


            //--------------------------------------------->




            //---------------------------------------------> 여는거는 맨밑에

            if(json.charAt(i)=='{' && !stringStartOn && !bigStartOn) {
               // System.out.println("객체시작");
                objectStartCount++;
                centerStartOn=true;
            }

            if(json.charAt(i)=='['  && !stringStartOn && !centerStartOn) {
               // System.out.println("배열시작");
                bigStartOn=true;
                arrayStartCount++;
            }

            if(!stringStartOn  && !attributeObject && !attributeArray)
                if(json.charAt(i)=='"') {
                    stringStartOn=true;
                   // System.out.println("문자열시작");
                }
            if(stringStartOn && stringEndOn) {
                stringStartOn=false;
                stringEndOn =false;
            }

            if(comma==true)
                comma=false;
            if(separator==true)
                separator=false;
            //--------------------------------------------->

        }
        //System.out.println("우선맵 사이즈:"+tempEm.size());

        for(int count=0; count<tempEm.size(); count++) {
            if(tempEm.getType(count).equals("Object") ) // 객체라면 쪼개주고 아니면 패스하고
            {
                //System.out.println("반복들어감");
                repeatPassing(emList,tempEm.getAttribute(count),tempEm.getParents()+"."+tempEm.getKey(count),tempEm.getAttributeList());
                //System.out.println("객체 분해");
            } else if(tempEm.getType(count).equals("Array")) { //배열이면 다르게처리

                arrayPassing(emList,tempEm.getAttribute(count),tempEm.getParents(),tempEm.getParentsFriendList() ,tempEm.getKey(count));
                tempEm.setKey(count, tempEm.getKey(count)+"Parents");
                //System.out.println("배열 분해");
            }

            //System.out.println("분해할게없다.");
        }
        //System.out.println("반복나옴");
        emList.add(tempEm);
    }




    public static void repeatPassing(ArrayList<EasyMap> emList,String json,String parents,ArrayList<String> parentsFriend) {
        boolean stringStartOn=false,stringEndOn=false,bigStartOn=false,bigEndOn=false,centerStartOn=false,centerEndOn=false,comma=false,separator=false;
        boolean keyOn=false,attributeOn=false,attributeInt=false,attributeString=false,attributeObject=false,attributeArray=false;
        StringBuffer tempKey =new StringBuffer("");
        StringBuffer tempAttribute= new StringBuffer("");
        int objectStartCount=0;
        int arrayStartCount=0;
        EasyMap tempEm = new EasyMap();
        tempEm.setParents(parents);
        tempEm.setParentsFriend(parentsFriend);
        boolean first=true;
        //.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        //System.out.println("받은제이슨:"+json);
        for(int i=0; i<json.length(); i++) {
            
        	
            //---------------------------------------------> 닫는거는 위로
            if(json.charAt(i)=='}' && centerStartOn) {
                objectStartCount--;
                if(objectStartCount==0) {
                    //System.out.println("객체닫기");
                    centerEndOn=true;
                }
            }

            if(json.charAt(i)==']' && bigStartOn) {
                arrayStartCount--;
                if(arrayStartCount==0) {
                    //System.out.println("배열닫기");
                    bigEndOn=true;
                }
            }


            if(stringStartOn && !attributeObject && !attributeArray)
                if(json.charAt(i)=='"') {
                    stringEndOn=true;
                    //System.out.println("문끝");
                }


            if(json.charAt(i)==','  && !bigStartOn && !centerStartOn)
                comma=true;

            if(json.charAt(i)==':'  && !bigStartOn && !centerStartOn) {
                separator=true;
                //System.out.println("구분자온");
            }

            //--------------------------------------------->




            //---------------------------------------------> 키 처리부분은 1.키끄기 2.키담기 3. key 키기


            if(stringStartOn && stringEndOn&& keyOn && !attributeOn) {
                keyOn=false;
                //System.out.println("키끄기");
                //System.out.println(tempKey);
            }


            if(stringStartOn && keyOn && !attributeOn ) {
                tempKey.append(json.charAt(i));
              
            }


            if(!bigStartOn&&!centerStartOn && !attributeOn && comma && !separator&& !attributeString && !attributeObject && !attributeArray) //반복키 받기
                if(i+1 <json.length())
                    if(json.charAt(i+1)=='"') {
                        keyOn=true;
                        // System.out.println("반복키값온");
                    }

            if(first)
                if(!stringStartOn && !stringEndOn) { //맨처음 키 받기
                    for(int temp=0; temp<json.length(); temp++) {
                        //System.out.println("temp는 증가하나 ?"+temp);
                        if(json.charAt(temp)=='"')
                            if(temp+1<json.length())
                                if(json.charAt(temp+1)==':') {
                                    keyOn=true;
                                    //System.out.println("그래서 찾은 구분자?:"+json.charAt(temp)+"그리고 지금 위치: " +temp);
                                    //System.out.println("키값온");
                                    first=false;
                                    break;
                                }

                        //System.out.println("받은제이슨값:"+json.length()+"\n 현재 제이슨 위치값:"+temp);
                        //System.out.println("mapList 크기:"+emList.size());
                    }

                }



            //--------------------------------------------->



            //---------------------------------------------> 속성 처리부분은 1.속성끄기(3개) 2.속성구분(3개) 3.속성담기(3개?1개)  4.속성키기

            if(stringStartOn && stringEndOn && attributeString && !attributeOn && !keyOn) { //문자값 끄기

                attributeString=false;
                tempEm.put(tempKey.toString(),tempAttribute.toString(),"String");
                //System.out.println("속성이문자네 키값:"+tempKey.toString()+"   속성값:"+tempAttribute.toString());
                tempKey = new StringBuffer("");
                tempAttribute=new StringBuffer("");
            }
            if(!stringStartOn && !stringEndOn && attributeInt && !attributeOn && !keyOn) { //보통 정수 뒤에 ,점이라면 계속 진행이고 아니라면 다른놈이니 일단 저장
                //System.out.println("인트안에 들어온값:"+json.charAt(i));
                //System.out.println("문자열 시작?"+stringStartOn +"문자열끝?"+ stringEndOn+"인트속성?"+attributeInt +"속성온?"+ attributeOn +"키는?"+keyOn);
                if(json.charAt(i)=='0' || json.charAt(i)=='1' || json.charAt(i)=='2' || json.charAt(i)=='3' || json.charAt(i)=='4' || json.charAt(i)=='5' ||
                        json.charAt(i)=='6' || json.charAt(i)=='7' || json.charAt(i)=='8' || json.charAt(i)=='9' || json.charAt(i)=='.') //여기서는 더블도 그냥 인트로 친다 문자열은 아니고 객체와배열은 아니니까
                    System.out.print("");
                else {
                    //System.out.println("일단 인트아니니 좀맞자");
                    attributeInt =false;
                    tempEm.put(tempKey.toString(),tempAttribute.toString(),"Int");
                   //System.out.println("속성이인트네 키값:"+tempKey.toString()+"   속성값:"+tempAttribute.toString());
                    tempKey = new StringBuffer("");
                    tempAttribute=new StringBuffer("");
                }
            }
            if(attributeInt)
                if(json.charAt(i)==',' || i==json.length()-1)
                {
                	if(i==json.length()-1)
                		tempAttribute.append(json.charAt(i));
                			
                    //System.out.println("어떻게 살아남았냐 일단 좀맞자");
                    attributeInt =false;
                    tempEm.put(tempKey.toString(),tempAttribute.toString(),"Int");
                    //System.out.println("속성이인트네 키값:"+tempKey.toString()+"   속성값:"+tempAttribute.toString());
                    tempKey = new StringBuffer("");
                    tempAttribute=new StringBuffer("");
                }
            if(attributeObject && centerStartOn && centerEndOn && !attributeOn) {
                attributeObject=false;
                centerStartOn=false;
                centerEndOn=false;
                tempEm.put(tempKey.toString(),tempAttribute.toString(),"Object");
                //System.out.println("속성이객체네 키값:"+tempKey.toString()+"   속성값:"+tempAttribute.toString());
                tempKey = new StringBuffer("");
                tempAttribute=new StringBuffer("");
            }
            if(attributeArray && bigStartOn && bigEndOn && !attributeOn) {
                attributeArray=false;
                bigStartOn=false;
                bigEndOn=false;
                //tempAttribute.append(json.charAt(i));
                tempEm.put(tempKey.toString(),tempAttribute.toString(),"Array");
                //System.out.println("속성이배열이네 키값:"+tempKey.toString()+"   속성값:"+tempAttribute.toString());
                tempKey = new StringBuffer("");
                tempAttribute=new StringBuffer("");
            }

            if(attributeOn && stringStartOn && !bigStartOn && !centerStartOn &&!attributeString && !attributeInt && !attributeObject && !attributeArray) {//문자속성값이라면
                attributeString=true;
                attributeOn=false;
                //System.out.println("문자속성온");
            }

            if(attributeOn && !stringStartOn && bigStartOn && !centerStartOn &&!attributeString && !attributeInt && !attributeObject && !attributeArray) {
                attributeArray=true;
                attributeOn=false;
                //System.out.println("배열속성온");
            }

            if(attributeOn && !stringStartOn && !bigStartOn && centerStartOn &&!attributeString && !attributeInt && !attributeObject && !attributeArray) {
                attributeObject=true;
                attributeOn=false;
                //System.out.println("객체속성온");
            }


            if(attributeOn && !stringStartOn && !bigStartOn && !centerStartOn &&!attributeString && !attributeInt && !attributeObject && !attributeArray  && json.charAt(i)!='{' && json.charAt(i)!='[' && json.charAt(i)!='"') { //인트가 마지막이다.
                attributeInt=true;
                attributeOn=false;
                //System.out.println("정수속성온");
            }

            //스트링은 stringStartOn이 객체들은 bigStartOn 객체는 centerStartOn 온된다. 하지만 인트는 구분자뒤에 아무것도없다.



            if((attributeInt || attributeString || attributeObject || attributeArray) && !attributeOn)  //정수값 넣기
            {
            	  tempAttribute.append(json.charAt(i));
            		  
            	
            }
              

            //if(i<50)
            //System.out.println("속성조건문:"+separator+"문자시작:"+stringStartOn+"속성시작:"+attributeOn+"키시작:"+keyOn);
            if(separator && !stringStartOn && !attributeOn && !keyOn) {//4.키는곳
                attributeOn=true;
                //System.out.println("속성온");
            }


            //--------------------------------------------->




            //---------------------------------------------> 여는거는 맨밑에

            if(json.charAt(i)=='{' && !stringStartOn && !bigStartOn) {
                //System.out.println("객체시작");
                objectStartCount++;
                centerStartOn=true;
            }

            if(json.charAt(i)=='['  && !stringStartOn && !centerStartOn) {
                //System.out.println("배열시작");
                bigStartOn=true;
                arrayStartCount++;
            }

            if(!stringStartOn  && !attributeObject && !attributeArray)
                if(json.charAt(i)=='"') {
                    stringStartOn=true;
                    //System.out.println("문자열시작");
                }
            if(stringStartOn && stringEndOn) {
                stringStartOn=false;
                stringEndOn =false;
            }

            if(comma==true)
                comma=false;
            if(separator==true)
                separator=false;
            //--------------------------------------------->

        }
        //System.out.println("우선맵 사이즈:"+tempEm.size());

        for(int count=0; count<tempEm.size(); count++) {
            if(tempEm.getType(count).equals("Object") ) // 객체라면 쪼개주고 아니면 패스하고
            {
                //System.out.println("반복들어감");
                repeatPassing(emList,tempEm.getAttribute(count),tempEm.getParents()+"."+tempEm.getKey(count),tempEm.getAttributeList());
            } else if(tempEm.getType(count).equals("Array")) { //배열이면 다르게처리

                arrayPassing(emList,tempEm.getAttribute(count),tempEm.getParents(),tempEm.getParentsFriendList(),tempEm.getKey(count));
                tempEm.setKey(count, tempEm.getKey(count)+"Parents");
            }


        }
        //System.out.println("반복나옴");
        emList.add(tempEm);
    }


    public static ArrayList<EasyMap> passing(String json) { //최초로 파싱하는놈이므로 부모가없고 해서 그냥 json만 받음
        boolean firstOn =true;
        ArrayList<EasyMap> emList = new ArrayList<EasyMap>();
        boolean stringStartOn=false,stringEndOn=false,bigStartOn=false,bigEndOn=false,centerStartOn=false,centerEndOn=false,comma=false,separator=false;
        boolean keyOn=true,attributeOn=true,attributeInt=false,attributeString=false,attributeObject=false,attributeArray=false;
        String tempKey ="json";
        StringBuffer tempAttribute= new StringBuffer("");
        int objectStartCount=0;
        int arrayStartCount=0;
        EasyMap tempEm = new EasyMap();
        System.out.println("우선파싱에 들어옴");
        for(int i=0; i<json.length(); i++) {

            //---------------------------------------------> 닫는거는 위로
            if(json.charAt(i)=='}' && centerStartOn) {
                objectStartCount--;
                if(objectStartCount==0) {

                    centerEndOn=true;
                }
            }

            if(json.charAt(i)==']' && bigStartOn) {
                arrayStartCount--;
                if(arrayStartCount==0) {

                    bigEndOn=true;
                }
            }


            if(stringStartOn && !bigStartOn && !centerStartOn)
                if(json.charAt(i)=='"')
                    stringEndOn=true;

            if(json.charAt(i)==','  && !bigStartOn && !centerStartOn)
                comma=true;

            if(json.charAt(i)==':'  && !bigStartOn && !centerStartOn)
                separator=true;
            //--------------------------------------------->




            //---------------------------------------------> 키 처리부분은 1.키끄기 2.키담기 3. key 키기
            //처음은 키는 이미 json임
            //--------------------------------------------->



            //---------------------------------------------> 속성 처리부분은 1.속성끄기(3개) 2.속성구분(3개) 3.속성담기(3개?1개)  4.속성키기

            if(stringStartOn && stringEndOn && attributeString && !attributeOn && !keyOn) { //문자값 끄기
                stringStartOn=false;
                stringEndOn =false;
                attributeString=false;
                tempEm.put(tempKey.toString(),tempAttribute.toString(),"String");
            }
            if(!stringStartOn && !stringEndOn && attributeInt && !attributeOn && !keyOn) { //보통 정수 뒤에 ,점이라면 계속 진행이고 아니라면 다른놈이니 일단 저장
                if(json.charAt(i)=='0' || json.charAt(i)=='1' || json.charAt(i)=='2' || json.charAt(i)=='3' || json.charAt(i)=='4' || json.charAt(i)=='5' ||
                        json.charAt(i)=='6' || json.charAt(i)=='7' || json.charAt(i)=='8' || json.charAt(i)=='9' || json.charAt(i)=='.') //여기서는 더블도 그냥 인트로 친다 문자열은 아니고 객체와배열은 아니니까
                    System.out.print("");
                else {
                    attributeInt =false;
                    tempEm.put(tempKey.toString(),tempAttribute.toString(),"Int");
                }
            }
            if(attributeObject && centerStartOn && centerEndOn && !attributeOn) {
                attributeObject=false;
                centerStartOn=false;
                centerEndOn=false;
                String tempResult = tempAttribute.toString();
                StringBuffer result=new StringBuffer("");
                for(int n =1; n<tempResult.length(); n++) {
                    result.append(tempResult.charAt(n));
                }
                tempEm.put(tempKey.toString(),result.toString(),"Object");

            }
            if(attributeArray && bigStartOn && bigEndOn && !attributeOn) {
                attributeArray=false;
                bigStartOn=false;
                bigEndOn=false;
                tempAttribute.append(json.charAt(i));
                tempEm.put(tempKey.toString(),tempAttribute.toString(),"Array");
            }

            if(attributeOn && stringStartOn && !bigStartOn && !centerStartOn &&!attributeString && !attributeInt && !attributeObject && !attributeArray) {//문자속성값이라면
                attributeString=true;
                attributeOn=false;
            }

            if(attributeOn && !stringStartOn && bigStartOn && !centerStartOn &&!attributeString && !attributeInt && !attributeObject && !attributeArray) {
                attributeArray=true;
                attributeOn=false;
            }

            if(attributeOn && !stringStartOn && !bigStartOn && centerStartOn &&!attributeString && !attributeInt && !attributeObject && !attributeArray) {
                attributeObject=true;
                attributeOn=false;

            }


            if(attributeOn && !stringStartOn && !bigStartOn && !centerStartOn &&!attributeString && !attributeInt && !attributeObject && !attributeArray) { //인트가 마지막이다.
                attributeInt=true;
                attributeOn=false;
            }

            //스트링은 stringStartOn이 객체들은 bigStartOn 객체는 centerStartOn 온된다. 하지만 인트는 구분자뒤에 아무것도없다.



            if((attributeInt || attributeString || attributeObject || attributeArray) && !attributeOn)  //정수값 넣기
                tempAttribute.append(json.charAt(i));

            if(separator && !stringStartOn && !attributeOn && !keyOn) //4.키는곳
                attributeOn=true;

            if(firstOn) {
                firstOn=false;
                attributeObject=true;
            }
            //--------------------------------------------->




            //---------------------------------------------> 여는거는 맨밑에

            if(json.charAt(i)=='{' && !bigStartOn && !stringStartOn) {

                objectStartCount++;
                centerStartOn=true;
            }

            if(json.charAt(i)=='[' && !centerStartOn && !stringStartOn) {

                bigStartOn=true;
                arrayStartCount++;
            }

            if(!stringStartOn  && !bigStartOn && !centerStartOn)
                if(json.charAt(i)=='"') {
                    stringStartOn=true;

                }


            if(comma==true)
                comma=false;
            if(separator==true)
                separator=false;
            //--------------------------------------------->

        }


        for(int count=0; count<tempEm.size(); count++) {
            if(tempEm.getType(count).equals("Object") || tempEm.getType(count).equals("Array")) // 객체라면 쪼개주고 아니면 패스하고
            {

                repeatPassing(emList,tempEm.getAttribute(count),tempEm.getKey(count),tempEm.getParentsFriendList());
            }
        }

        emList.add(tempEm);
        //쪼갤꺼 다쪼갰으면 이제 그 리스트를 반환
        return emList;
    }
    public static String changeString(String json) {
    	StringBuffer result= new StringBuffer("");
        
    	for(int t =0; t<json.length(); t++){
       
        	  if(json.charAt(t)=='\\') {
        		  if(json.charAt(t+1)=='"') {
  					result.append("'");
  					t=t+1;
  				} else {
  			
  					result.append(json.charAt(t));
  				}
        		    
        		  
        	  } else {		  
        		  result.append(json.charAt(t));
		  
        		  
              }
            	
            } 
          
            
            
        		return result.toString();
        }

       
    
    public static String removeBackslash(String json) {
        StringBuffer result= new StringBuffer("");
        for(int t =0; t<json.length(); t++){
            if(json.charAt(t)!='\\'){
                result.append(json.charAt(t));
            } else {
            
            	if(t+1<json.length())
            			if(json.charAt(t+1)=='"')
            				{
            				System.out.println("문자열표시 들어옴");
            					result.append("'");
            					t=t+1;
            				}
            }
            
            
        }

        return result.toString();
    }
    public static String tagProcessing(String json) {
    	StringBuffer result = new StringBuffer("");
    	boolean putRock=false;
    	for(int i=0; i<json.length(); i++) {
    	
    	
    		
    	if(i+3<json.length())
    		if(json.charAt(i)=='<')
    			if(json.charAt(i+1)=='b')
    				if(json.charAt(i+2)=='r')
    					if(json.charAt(i+3)=='>')
    					{
    						System.out.println("<br> 엔 들어옴");
    						putRock=true;
    						result.append("\n");
    						i=i+3;
    					}
    	
    	if(i+1<json.length())
    		if(json.charAt(i)=='\\')
    			if(json.charAt(i+1)=='n')
    				{
    				System.out.println("역슬래쉬 엔 들어옴");
    					putRock=true;
    					result.append("\n");
    					i=i+1;
    				}
    		
    	if(i+5<json.length())
    		if(json.charAt(i)=='&')
    			if(json.charAt(i+1)=='n')
    				if(json.charAt(i+2)=='b')
    					if(json.charAt(i+3)=='s')
    						if(json.charAt(i+4)=='p')
    							if(json.charAt(i+5)==';')
    					{
    								System.out.println("공백 엔 들어옴");
    						putRock=true;
    						result.append(" ");
    						i=i+5;
    					}
    		
    		if(!putRock) {
    			result.append(json.charAt(i));
    		} else {
    			putRock=false;
    		}
    		
    	}
    	return result.toString();
    }
    public static String tagProcessingVer2(String json) {
    	StringBuffer result = new StringBuffer("");
    	boolean putRock=false;
    	for(int i=0; i<json.length(); i++) {
    	
    		if(i+1<json.length())
        		if(json.charAt(i)=='\\')
        			if(json.charAt(i+1)=='"')
        				{
        				System.out.println("문자열표시 들어옴");
        					putRock=true;
        					result.append("'");
        					i=i+1;
        				}
    		
    		
    	if(i+8<json.length())
    		if(json.charAt(i)=='<')
    			if(json.charAt(i+1)=='b')
    				if(json.charAt(i+2)=='r')
    					if(json.charAt(i+3)==' ')
    						if(json.charAt(i+4)=='\\')
    							if(json.charAt(i+5)=='/')
    								if(json.charAt(i+6)=='>')
    									if(json.charAt(i+8)==')')
    					{
    						System.out.println("br태그 들어옴");
    						putRock=true;
    						result.append("\n");
    						i=i+8;
    					}
    	
    	if(i+1<json.length())
    		if(json.charAt(i)=='\\')
    			if(json.charAt(i+1)=='n')
    				{
    				System.out.println("역슬래쉬 엔 들어옴");
    					putRock=true;
    					result.append("\n");
    					i=i+1;
    				}
    		
    	if(i+5<json.length())
    		if(json.charAt(i)=='&')
    			if(json.charAt(i+1)=='n')
    				if(json.charAt(i+2)=='b')
    					if(json.charAt(i+3)=='s')
    						if(json.charAt(i+4)=='p')
    							if(json.charAt(i+5)==';')
    					{
    								System.out.println("공백 엔 들어옴");
    						putRock=true;
    						result.append(" ");
    						i=i+5;
    					}
    		
    		if(!putRock) {
    			result.append(json.charAt(i));
    		} else {
    			putRock=false;
    		}
    		
    	}
    	return result.toString();
    }
}
