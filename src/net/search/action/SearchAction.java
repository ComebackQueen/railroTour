package net.search.action;

import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import etc.function.MobileCheck;
import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.search.db.*;


public class SearchAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		String SearchWord=request.getParameter("SEARCH_WORD"); //해당 뷰 폼에서 검색어를 가져온다.
		ArrayList<TouristBean> Tour=new ArrayList<TouristBean>();
		ArrayList<RestaurantBean> Food=new ArrayList<RestaurantBean>();
		
		JSONArray tourArray = new JSONArray();
	      JSONArray restArray = new JSONArray();
	      JSONObject resultJson = new JSONObject();
		
		
		System.out.println(SearchWord+"검색됨");
		TouristDAO touristdao1=new TouristDAO(SearchWord);
		TouristDAO touristdao2=new TouristDAO(SearchWord);
		
		touristdao1.setUrl(new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?ServiceKey="+touristdao1.getApi_Key()
								+ "&keyword="+URLEncoder.encode(SearchWord, "UTF-8")
								+ "&MobileOS=ETC"
								+ "&MobileApp=AppTest&_type=json&contentTypeId=12&areaCode=38&areaCode=37&areaCode=5&cat1=A01"));
		//대분류 1 셋팅
		touristdao2.setUrl(new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?ServiceKey="+touristdao2.getApi_Key()
								+ "&keyword="+URLEncoder.encode(SearchWord, "UTF-8")
								+ "&MobileOS=ETC"
								+ "&MobileApp=AppTest&_type=json&contentTypeId=12&areaCode=38&areaCode=37&areaCode=5&cat1=A02"));
		//대분류 2 셋팅
		
		
		if(touristdao1.getTouristList()!=null && touristdao2.getTouristList()!=null) {
			Tour=touristdao1.getTouristList();
			Tour.addAll(touristdao2.getTouristList());
		}
		else if(touristdao1.getTouristList()==null && touristdao2.getTouristList()!=null){
			Tour=touristdao2.getTouristList();
		}
		else if(touristdao1.getTouristList()!=null && touristdao2.getTouristList()==null) {
			Tour=touristdao1.getTouristList();
		}
		else if(touristdao1.getTouristList()==null && touristdao2.getTouristList()==null) {
			Tour=null;
		}


		RestaurantDAO restaurantdao=new RestaurantDAO("DrVFgkqXkuQbgQfBPCyTkqsUDXXukiY3pGLkMGgXwCA%2BMZ2XCGAmvQdQtDm8QlqKejostGbcJSHKR0Ru8n8Weg%3D%3D");
		restaurantdao.setUrl(new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?ServiceKey=DrVFgkqXkuQbgQfBPCyTkqsUDXXukiY3pGLkMGgXwCA%2BMZ2XCGAmvQdQtDm8QlqKejostGbcJSHKR0Ru8n8Weg%3D%3D"
								+ "&keyword="+URLEncoder.encode(SearchWord, "UTF-8")
								+ "&MobileOS=ETC"
								+ "&MobileApp=AppTest&_type=json&contentTypeId=39&areaCode=38&areaCode=37&areaCode=5&cat1=A05"));
		

		if(restaurantdao.getRestaurantList()!=null) {
			Food=restaurantdao.getRestaurantList(); // 음식점 키워드 정보 검색 실행
		}
		else {
			Food=null;
		}
		System.out.println("--------------------최종 합친 데이터 결과 ------------------------------------");
		if(Tour!=null) {
	         for(TouristBean bean:Tour) {
	            if(MobileCheck.isMobile(request)) { //안드로이드일때
	               tourArray.add(bean.getJson());
	            }
	            System.out.println(bean.getTitle());
	         }
	      }
	      if(Food!=null) {
	         for(RestaurantBean rest:Food) {
	            if(MobileCheck.isMobile(request)) { //안드로이드일때
	               restArray.add(rest.getJson());
	            }
	            System.out.println(rest.getTitle());
	         }
	      }
		
		request.setAttribute("KeyWord", SearchWord);
		request.setAttribute("Tour", Tour);
		request.setAttribute("Food", Food);
		System.out.println("request 객체 준비완료");
		if(MobileCheck.isMobile(request)) { //모바일이라면
	         System.out.println("모바일이네.");
	         response.setContentType("text/html;charset=UTF-8");
	         PrintWriter out=response.getWriter();
	         resultJson.put("tourItem", tourArray);
	         resultJson.put("restItem", restArray);
	         out.print(resultJson.toString());
	         out.close();
	         return null;
	      } //웹이라면
	      
	         forward.setRedirect(false); //접속 끊었다가 다시 연결하면서 새로운 정보를 보여준다.
	         forward.setPath("./Search/Search_Form.jsp"); 
	         return forward;   
	   }

}
