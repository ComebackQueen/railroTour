package net.around_me.db;

import java.util.ArrayList;

import etc.function.EasyMap;
import etc.function.JSONPassing;
import etc.function.Request;

public class ImageAroundMeDAO {

	public static String imageJson(int contentId) {
		String url="";
		String result = "";
		ArrayList<EasyMap> emList = new ArrayList<EasyMap>();
		String responseContext = "";
		String tempResult="";
		
		url = createUrl(contentId);
		responseContext =Request.request(url);
		responseContext = JSONPassing.removeBackslash(responseContext);
		System.out.println(responseContext);
		emList =JSONPassing.passing(responseContext);
		result=result+"{\"items\":[";
		for(int i=0; i<emList.size(); i++) {
			
			if(emList.get(i).getParents().equals("json.response.body.items.item")) {
				if(!emList.get(i).get("originimgurl").equals("null")) {
					result=result+"{\"originimgurl\":\""+emList.get(i).get("originimgurl")+"\"},";
				}
			}
			
			
		}
		if(result.length()!=9)
			result =result.substring(0, result.length()-1);
			else
				result= result+"{\"result\":\"null\"}";
		
		result= result+"]}";
		
		
		
		return result;
	}
	
	
	
	
	
	public static String createUrl(int contentid) {
		String Api_Key="HEuGpjSxUXFPlzluyZKNAaLDDJ7KyJrIN91zx%2FKcWgRx2dRttiJSDE9mzirIowcPjXogH%2FUu5ih9LAOrmC8ZEw%3D%3D";
		String url;

		url="http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?ServiceKey="+Api_Key
				+ "&contentId="+contentid
				+ "&MobileOS=ETC&MobileApp=AppTest&numOfRows=10&pageSize=10&pageNo=1&startPage=1"	
				+ "&_type=json&imageYN=Y&sublmageYN=Y";
			
		
		return url;
	}
	
}
