package etc.function;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {
	  public static String request(String urlStr){  //이게 이제 연결해서 데이터 받는 request 클래스
          StringBuilder output = new StringBuilder();   //StringBuilder ? 먼지 알아보고 변경가능한 string 이다 보통 스트링은 넣으면 변경된다. 근데여기는 추가가능

          try {
              URL url = new URL(urlStr);  //url 객체생성 안의 매개변수는 받은 주소값

              HttpURLConnection conn = (HttpURLConnection)url.openConnection(); //연결한다.
              if(conn != null){  //conn의 값이 null이 아니라면 즉 연결이라면 ?
                  conn.setConnectTimeout(10000);  //연결확인시간을 10초
                  conn.setRequestMethod("GET"); //get 방식
                  conn.setDoInput(true); //받기 허용
                  conn.setDoOutput(true); //쓰기 허용

                  int resCode = conn.getResponseCode(); //http 연결상태 상수를 반환하는 메소드이며 그걸담는 인트 변수
                  if(resCode == HttpURLConnection.HTTP_OK){ //200이다 즉 연결상태가 좋다면 ?
                      BufferedReader reader = new BufferedReader( //버퍼리더 열어서 스트림리더에 연결한 http의 인풋스트림을 담아서 해서
                              new InputStreamReader(conn.getInputStream()));


                  String line =null;  //거기서 날라올 스트링 데이터를 담을 변수를 초기화해준다음
                  while(true) {  //일단 반복 돌린다.
                      line = reader.readLine(); //reader에 담긴 즉 날라온 데이터의 한줄을 넣고
                      if(line ==null){ //한줄이 존재안한다면 즉 빈값 끝이라면 ? 또는 없다면?
                          break; //반복문 끝
                      }
                      output.append(line + "\n"); //아니라면? output
                  }
                  reader.close(); //버퍼리더 닫고
                  conn.disconnect(); //http 연결 해제
                  }
              }
          } catch (Exception ex){
              
              ex.printStackTrace(); //오류가먼지 출력
          }
          return  output.toString(); //output 즉 줄있는 스트링객체를 반환 스트링화해서 즉 줄구분없이 한줄로 줄줄줄 출력 ?
      }
}

