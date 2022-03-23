package net.search.db;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class RestaurantDAO {
	String Api_Key="HEuGpjSxUXFPlzluyZKNAaLDDJ7KyJrIN91zx%2FKcWgRx2dRttiJSDE9mzirIowcPjXogH%2FUu5ih9LAOrmC8ZEw%3D%3D";
	URL url;
	String Word;

	public RestaurantDAO(String key){
		Api_Key=key;
	}
	public String getApi_Key() {
		return Api_Key;
	}
	public void setApi_Key(String api_Key) {
		Api_Key = api_Key;
	}
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	public ArrayList<RestaurantBean> getRestaurantList() throws UnsupportedEncodingException, IOException, ParseException {
		InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
		ArrayList<RestaurantBean> food_array=new ArrayList<RestaurantBean>();
		JSONObject items = (JSONObject) JSONValue.parseWithException(isr); 

//		System.out.println("음식점 : "+items);
		
		JSONObject obj=(JSONObject) items.get("response");  //잡다한 것들 걸러내는 작업
		obj=(JSONObject) obj.get("body");  //잡다한 것들 걸러내는 작업
//		System.out.println("response와 body 걸러낸 후 : "+obj); 
		if(obj.get("items").equals("")) { //검색했는데 데이터가 비었으면
			System.out.println("데이터가 없음");
			return null;
		}
		
		
		obj=(JSONObject)obj.get("items"); //잡다한 것들 걸러내는 작업
//		System.out.println("obj 사이즈 : "+obj);
		
		if(obj.get("item").equals("")) {
			System.out.println("item : 데이터가 없음");
			return null;
		}
		else {
			try {
				JSONArray arr=(JSONArray) obj.get("item"); //잡다한 것들 걸러내는 작업
				for(int i=0; i<arr.size(); i++) { //가변 배열에 넣는 작업, 제목, 주소, 이미지
					JSONObject tempObj=(JSONObject) arr.get(i);
					RestaurantBean restaurantbean=new RestaurantBean();
					restaurantbean.setTitle(tempObj.get("title").toString());
					restaurantbean.setAddress1(tempObj.get("addr1").toString());
				
					if(tempObj.get("firstimage")!=null) {
						restaurantbean.setImg(tempObj.get("firstimage").toString());
					}
					else if(tempObj.get("firstimage2")!=null) {
						restaurantbean.setImg(tempObj.get("firstimage2").toString());
					}
					else {
						restaurantbean.setImg("./jpg/no_image.gif");
					}

					food_array.add(restaurantbean);

				}
			}catch(Exception e) {
				obj=(JSONObject) obj.get("item");
				RestaurantBean restaurantbean=new RestaurantBean();
				restaurantbean.setTitle(obj.get("title").toString());
				restaurantbean.setAddress1(obj.get("addr1").toString());
			
				if(obj.get("firstimage")!=null) {
					restaurantbean.setImg(obj.get("firstimage").toString());
				}
				else if(obj.get("firstimage2")!=null) {
					restaurantbean.setImg(obj.get("firstimage2").toString());
				}
				else {
					restaurantbean.setImg("./jpg/no_image.gif");
				}

				food_array.add(restaurantbean);
			}
		}


//		for(int i=0; i<tour_array.size(); i++) {
//			System.out.println("마지막 for : "+((TouristBean) ((List) tour_array).get(i)).getTitle()+":"+((TouristBean) ((List) tour_array).get(i)).getImg()+":"+((TouristBean) ((List) tour_array).get(i)).getAddress1());
//		}
		
		return food_array;
	}
}
