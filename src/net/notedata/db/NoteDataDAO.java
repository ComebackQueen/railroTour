package net.notedata.db;

import java.util.ArrayList;

import etc.function.EasyMap;
import etc.function.JSONPassing;
import etc.function.Request;

public class NoteDataDAO {
	
	
	public static String getNoteData(String areaCode,String sigunguCode,String contentTypeId) {
		String result="";
		
		String url =NoteDataDAO.createUrl(1, areaCode, sigunguCode, contentTypeId);
		System.out.println("우선 그지역 url:"+url);
		result = NoteDataDAO.getNoteDataJson(url, contentTypeId);
		System.out.println("우선 그지역 데이터:"+result);
		return result;
	}
	
	
	public static String createUrl(int page,String areaCode,String sigunguCode,String contentTypeId) {
		String Api_Key="HEuGpjSxUXFPlzluyZKNAaLDDJ7KyJrIN91zx%2FKcWgRx2dRttiJSDE9mzirIowcPjXogH%2FUu5ih9LAOrmC8ZEw%3D%3D";
		String url;

		url="http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey="+Api_Key
				+ "&pageNo="+page+"&startPage="+page
				+ "&contentTypeId="+contentTypeId
				+ "&areaCode="+areaCode
				+ "&sigunguCode="+sigunguCode
				+ "&numOfRows=30&pageSize=10&MobileApp=AppTest&MobileOS=ETC&arrange=A&listYN=Y"	
				+ "&overviewYN=Y&_type=json";
		

		return url;
	}
	public static String createUrl2(String contentId,String contentTypeId) { //음식점 39와 15축제를 위해 자세한내요 가져오기위한 url
		String Api_Key="HEuGpjSxUXFPlzluyZKNAaLDDJ7KyJrIN91zx%2FKcWgRx2dRttiJSDE9mzirIowcPjXogH%2FUu5ih9LAOrmC8ZEw%3D%3D";
		String url;

		url="http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey="+Api_Key
				+ "&pageNo=1&startPage=1"
				+ "&contentTypeId="+contentTypeId
				+ "&contentId=" +contentId
				+ "&numOfRows=10&pageSize=10&MobileApp=AppTest&MobileOS=ETC"	
				+ "&introYN=Y&_type=json";
				

		return url;
	}
	public static String getNoteDataJson(String url,String contentTypeId) { //39및 12 일때 쓸 제이슨
		StringBuffer result = new StringBuffer();
		NoteDataBeen noteDataBeen = new NoteDataBeen();

				String responseContext =Request.request(url);
				ArrayList<EasyMap> emList = new ArrayList<EasyMap>();
				emList =JSONPassing.passing(JSONPassing.removeBackslash(JSONPassing.changeString(responseContext)));
				//System.out.println(emList.toString());
				result.append("{\"notedata\":[");
				for(int index=0; index<emList.size(); index++) {
					if(emList.get(index).getParents().equals("json.response.body.items.item")) {
						noteDataBeen=new NoteDataBeen();
						noteDataBeen.setTitle(emList.get(index).get("title"));
						noteDataBeen.setAddress(emList.get(index).get("addr1"));
						noteDataBeen.setTel(emList.get(index).get("tel"));
						noteDataBeen.setFirstimage(emList.get(index).get("firstimage"));
						noteDataBeen.setContentId(emList.get(index).get("contentid"));
						noteDataBeen.setContentTypeId(emList.get(index).get("contenttypeid"));
						
						String url2 =NoteDataDAO.createUrl2(noteDataBeen.getContentId(), noteDataBeen.getContentTypeId());
						String responseContext2 =Request.request(url2);
						System.out.println(responseContext2);
						ArrayList<EasyMap> emList2 = new ArrayList<EasyMap>();
						emList2 =JSONPassing.passing(JSONPassing.removeBackslash(JSONPassing.changeString(responseContext2)));
						//System.out.println(emList2.toString());
						
						for(int index2=0; index2<emList2.size(); index2++ ) {
							if(emList2.get(index2).getParents().equals("json.response.body.items.item")) {
								if(contentTypeId.equals("15")) {
									noteDataBeen.setContext(emList2.get(index2).get("eventplace"));
									
								} else if(contentTypeId.equals("39")) {
									noteDataBeen.setContext(emList2.get(index2).get("treatmenu"));
									
								}
								
							}
						}
						
						//{"키값" : [{},{}]}
						result.append("{");
						result.append("\"title\":");
						result.append("\""+noteDataBeen.getTitle()+"\"");
						result.append(",\"address\":");
						result.append("\""+noteDataBeen.getAddress()+"\"");
						result.append(",\"tel\":");
						result.append("\""+noteDataBeen.getTel()+"\"");
						result.append(",\"image\":");
						result.append("\""+noteDataBeen.getFirstimage()+"\"");
						result.append(",\"context\":");
						result.append("\""+noteDataBeen.getContext()+"\"");
						result.append(",\"contentId\":");
						result.append("\""+noteDataBeen.getContentId()+"\"");
						result.append(",\"contentTypeId\":");
						result.append("\""+noteDataBeen.getContentTypeId()+"\"");
						result.append("}");
						
						System.out.println("현재인덱스:"+index+" 최종사이즈:"+(emList.size()-1));
							System.out.println("점추가");
							result.append(",");
						
						
					}
					
					if(index==(emList.size()-1)) {
						System.out.println("결과가됬다");
						String tempResult;
						tempResult=result.toString();
						tempResult=tempResult.substring(0, tempResult.length()-1);
						result= new StringBuffer("");
						result.append(tempResult);
						result.append("]}");
					}
				}
				
		return result.toString();
	}
}
