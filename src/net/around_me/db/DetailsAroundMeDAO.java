package net.around_me.db;

import java.util.ArrayList;

import etc.function.*;

public class DetailsAroundMeDAO {
	
	
	
	public static String detailsJson(int contentid,int contenttypeid) {
		
		String url,result="";
		ArrayList<EasyMap> emList = new ArrayList<EasyMap>();
		String responseContext = "";
		String tempResult="";
		
		url = createUrl(contentid);
		responseContext =Request.request(url);
		emList =JSONPassing.passing(JSONPassing.changeString(responseContext));
		
		for(int i=0; i<emList.size(); i++) {
			if(emList.get(i).getParents().equals("json.response.body.items.item")) {
				
				result=emList.get(i).get("overview");
				
			}
			
		}
		
		result="{\"body\":{\"overview\":\""+result+"\",";
		
		
		//--------------------------------------------->
		
		url = createUrl2(contentid,contenttypeid);
		responseContext =Request.request(url);
		emList =JSONPassing.passing(JSONPassing.changeString(responseContext));
	
		tempResult="\"items\":[";
		for(int o=0; o<emList.size(); o++) {
			
			if(emList.get(o).getParents().equals("json.response.body.items.item")) {
				
				if(!emList.get(o).get("infoname").equals("null")) {
					tempResult=tempResult+"{\"infoname\":\""+emList.get(o).get("infoname")+"\",";
					tempResult=tempResult+"\"infotext\":\""+emList.get(o).get("infotext")+"\"},";
				}
				
					
			}
		}
		if(tempResult.length()!=9)
		tempResult =tempResult.substring(0, tempResult.length()-1);
		else
			tempResult= tempResult+"{\"result\":\"null\"}";
			
		tempResult= tempResult+"]";
		result= result+tempResult+"}}";

		
		
		
		
		
		return result;
	}
	
	
	public static String createUrl(int contentid) {
		String Api_Key="HEuGpjSxUXFPlzluyZKNAaLDDJ7KyJrIN91zx%2FKcWgRx2dRttiJSDE9mzirIowcPjXogH%2FUu5ih9LAOrmC8ZEw%3D%3D";
		String url;

		url="http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey="+Api_Key
				+ "&contentId="+contentid
				+ "&MobileOS=ETC&MobileApp=AppTest&defaultYN=N"	
				+ "&overviewYN=Y&_type=json";
		
		return url;
	}
	public static String createUrl2(int contentid,int contenttypeid) {
		String Api_Key="HEuGpjSxUXFPlzluyZKNAaLDDJ7KyJrIN91zx%2FKcWgRx2dRttiJSDE9mzirIowcPjXogH%2FUu5ih9LAOrmC8ZEw%3D%3D";
		String url;

		url="http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailInfo?ServiceKey="+Api_Key
				+ "&contentId="+contentid
				+ "&contentTypeId="+contenttypeid
				+ "&MobileOS=ETC&MobileApp=AppTest&numOfRows=10"	
				+ "&detailYN=Y&_type=json";
		
		return url;
	}
}
