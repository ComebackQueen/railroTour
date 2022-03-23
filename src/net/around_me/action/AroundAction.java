package net.around_me.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import etc.function.EasyMap;
import etc.function.JSONPassing;
import etc.function.MobileCheck;
import etc.function.Request;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class AroundAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		request.setCharacterEncoding("UTF-8");
		String mapX=request.getParameter("MAPX"); //해당 뷰 폼에서 검색어를 가져온다.
		String mapY=request.getParameter("MAPY"); //해당 뷰 폼에서 검색어를 가져온다.
		System.out.println("일단 액션에 들어옴");
		int totalCount=0,realCount=0;
		boolean firstObject=true;
		int pageNo=1,startPage=1;
		String url;

	
		ArrayList<EasyMap> emList = new ArrayList<EasyMap>();
		String result="";
		int testCount=0;
		String responseContext = "";
			url = createUrl(pageNo,startPage,mapX,mapY);
			
			responseContext =Request.request(url);
			emList=JSONPassing.passing(JSONPassing.removeBackslash(responseContext));
			System.out.println(response);
			System.out.println(emList.toString());
			
			for(int i=0; i<emList.size(); i++) {
				if(emList.get(i).getParents().equals("json.response.body")) {  //여기서는 토탈카운터를 받는곳 why? 한페이지당10개씩받는데 데이터의갯수가 10개가 넘는다면 그넘는만큼의 데이터가 손실되므로 페이지를 넘겨서 받아야함
					for(int o=0; o<emList.get(i).size(); o++)
						if(emList.get(i).getKey(o).equals("totalCount"))
							totalCount=Integer.parseInt(emList.get(i).get("totalCount"));
				}
			
				if(emList.get(i).getParents().equals("json.response.body.items.item")) { //여기서는 받은 제이슨에서 내가 쓸 item정보들만 다시 제이슨 객체화 해서 결과에 담는작업
					testCount++;
					if(firstObject) {
						result=result+emList.get(i).JSONObject();
						firstObject=false;
					} else {
						result=result+","+emList.get(i).JSONObject();
					}
						
					
				}
				
			}
			
			System.out.println(result);
			
			realCount=totalCount/10;  //총갯수의 한페이지당 10개니 10을 나눈것의 몫이 진짜 카운터에다가
			if(totalCount%10!=0)   //나머지가 0이 아니면 한페이지 더 요청해야하므로 또 +1 해준다
				realCount++;
			
			for(int k=pageNo; k<realCount; ++k) {  //즉 페이지의 끝이아니라면 ?
				pageNo++;
				startPage++;
				
				String tempResult="";
				
				url = createUrl(pageNo,startPage,mapX,mapY);
				responseContext =Request.request(url);
				emList=JSONPassing.passing(JSONPassing.removeBackslash(responseContext));
				
				for(int i=0; i<emList.size(); i++) { //다시 제이슨 받았으니 쪼개서 item만 분류해서 더한다.
					if(emList.get(i).getParents().equals("json.response.body.items.item")) { //여기서는 받은 제이슨에서 내가 쓸 item정보들만 다시 제이슨 객체화 해서 결과에 담는작업
						
						if(firstObject) {
							tempResult=tempResult+emList.get(i).JSONObject();
							firstObject=false;
						} else {
							tempResult=tempResult+","+emList.get(i).JSONObject();
						}
							
						testCount++;
					}
					
					
				}
				result= result+tempResult; //아까 분류해놓은 결과물에 더한다.
				System.out.println(pageNo+"번째 결과물 더함");
				
			}
			result="{\"items\":["+result+"]}";
			
			System.out.println("최종결과물:"+result);
			System.out.println("최종갯수:"+testCount);
		
				//어차피 안드만 쓸 클래스라 웹은 신경안쓴다.
	
		
		if(MobileCheck.isMobile(request)) { //모바일이라면
			System.out.println("모바일이네.");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			
			out.print(result);
			out.close();
			return null;
		} //웹이라면 
			return forward;	
	}
	public static String createUrl(int pageNo,int startPage,String mapX,String mapY) {
		String Api_Key="HEuGpjSxUXFPlzluyZKNAaLDDJ7KyJrIN91zx%2FKcWgRx2dRttiJSDE9mzirIowcPjXogH%2FUu5ih9LAOrmC8ZEw%3D%3D";
		String url;
		
		url="http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="+Api_Key
				+ "&numOfRoews=10"
				+ "&pageNo="+ pageNo
				+ "&startPage=" + startPage
				+ "&MobileOS=ETC&MobileApp=AppTest&arrange=A&contenTypeId=12"
				+ "&mapX="+mapX
				+ "&mapY="+mapY
				+ "&radius=3000&listYN=Y&_type=json";
		
		return url;
	}
}
