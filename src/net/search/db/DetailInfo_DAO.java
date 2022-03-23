package net.search.db;

import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import etc.function.DB_Connection;

public class DetailInfo_DAO extends DB_Connection {
	public DetailInfo_Bean getInfo(int contentid, int contenttypeid) {
		DetailInfo_Bean DetailInfo_bean = new DetailInfo_Bean();
		try {
			URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?serviceKey="
					+ Key + "&numOfRows=1&pageSize=1&pageNo=1&startPage=1&MobileOS=ETC&MobileApp=AppTest&contentId="
					+ contentid
					+ "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&_type=json");
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			JSONObject items = (JSONObject) JSONValue.parseWithException(isr);
			JSONObject obj = (JSONObject) items.get("response");
			obj = (JSONObject) obj.get("body");
			obj = (JSONObject) obj.get("items");
			obj = (JSONObject) obj.get("item");
			System.out.println("공통정보 : " + obj);

			if (obj.get("title") != null) {
				DetailInfo_bean.setName(obj.get("title").toString());
			} else {
				DetailInfo_bean.setName(null);
			}

			if (obj.get("addr1") != null) {
				DetailInfo_bean.setAddr(obj.get("addr1").toString());
			} else {
				DetailInfo_bean.setAddr(null);
			}

			if (obj.get("homepage") != null) {
				DetailInfo_bean.setHomepage(obj.get("homepage").toString());
			} else {
				DetailInfo_bean.setHomepage(null);
			}

			if (obj.get("mapy") != null) {
				DetailInfo_bean.setLat(Double.parseDouble(obj.get("mapy").toString()));
			} else {
				DetailInfo_bean.setLat(0);
			}

			if (obj.get("mapx") != null) {
				DetailInfo_bean.setLng(Double.parseDouble(obj.get("mapx").toString()));
			} else {
				DetailInfo_bean.setLng(0);
			}

			if (obj.get("overview") != null) {
				DetailInfo_bean.setOverview(obj.get("overview").toString());
			} else {
				DetailInfo_bean.setOverview(null);
			}

			if (obj.get("telname") != null) {
				DetailInfo_bean.setTelname(obj.get("telname").toString());
			} else {
				DetailInfo_bean.setTelname(null);
			}

			url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?serviceKey=" + Key
					+ "&numOfRows=1&pageSize=1&pageNo=1&startPage=1&MobileOS=ETC&MobileApp=AppTest&contentId="
					+ contentid + "&contentTypeId=" + contenttypeid + "&introYN=Y&_type=json");
			isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			items = (JSONObject) JSONValue.parseWithException(isr);
			obj = (JSONObject) items.get("response");
			obj = (JSONObject) obj.get("body");
			obj = (JSONObject) obj.get("items");
			obj = (JSONObject) obj.get("item");
			System.out.println("소개정보 : " + obj);
			
			if (contenttypeid == 12) {
				if (obj.get("accomcount") != null) // 수용인원
					DetailInfo_bean.setMax_peo(obj.get("accomcount").toString());
				else
					DetailInfo_bean.setMax_peo("");

				if (obj.get("chkbabycarriage") != null) // 유모차 대여 여부
					DetailInfo_bean.setStroller(obj.get("chkbabycarriage").toString());
				else
					DetailInfo_bean.setStroller(null);

				if (obj.get("chkcreditcard") != null) // 신용카드 가능 여부
					DetailInfo_bean.setCreditcard_availability(obj.get("chkcreditcard").toString());
				else
					DetailInfo_bean.setCreditcard_availability(null);

				if (obj.get("chkpet") != null)// 애완동물 가능여부
					DetailInfo_bean.setPets_allowed(obj.get("chkpet").toString());
				else
					DetailInfo_bean.setPets_allowed(null);

				if (obj.get("expagerange") != null) // 체험 가능 연령
					DetailInfo_bean.setAvailable_age(obj.get("expagerange").toString());
				else
					DetailInfo_bean.setAvailable_age(null);

				if (obj.get("expguide") != null) // 체험 안내
					DetailInfo_bean.setExperience(obj.get("expguide").toString());
				else
					DetailInfo_bean.setExperience(null);

				if (obj.get("infocenter") != null) // 문의 및 안내
					DetailInfo_bean.setContact_Information(obj.get("infocenter").toString());
				else
					DetailInfo_bean.setContact_Information(null);

				if (obj.get("opendate") != null)// 개장일
					DetailInfo_bean.setOpening_date(obj.get("opendate").toString());
				else
					DetailInfo_bean.setOpening_date(null);

				if (obj.get("parking") != null)// 주차시설
					DetailInfo_bean.setParking_facilities(obj.get("parking").toString());
				else
					DetailInfo_bean.setParking_facilities(null);

				if (obj.get("restdate") != null)// 쉬는날
					DetailInfo_bean.setRestday(obj.get("restdate").toString());
				else
					DetailInfo_bean.setRestday(null);

				if (obj.get("useseason") != null)// 이용시기
					DetailInfo_bean.setWhen_to_use(obj.get("useseason").toString());
				else
					DetailInfo_bean.setWhen_to_use(null);

				if (obj.get("usetime") != null)// 이용시간
					DetailInfo_bean.setHours_of_use(obj.get("usetime").toString());
				else
					DetailInfo_bean.setHours_of_use(null);

			} else if (contenttypeid == 39) {
			
				if (obj.get("chkcreditcardfood") != null)// 신용카드 할인 여부
					DetailInfo_bean.setCreditcard_availability(obj.get("chkcreditcardfood").toString());
				else
					DetailInfo_bean.setCreditcard_availability(null);
			
				if (obj.get("discountinfofood") != null)// 할인정보
					DetailInfo_bean.setDiscount_information(obj.get("discountinfofood").toString());
				else
					DetailInfo_bean.setDiscount_information(null);
			
				if (obj.get("firstmenu") != null)// 대표메뉴
					DetailInfo_bean.setMenu(obj.get("firstmenu").toString());
				else
					DetailInfo_bean.setMenu(null);
			
				if(obj.get("infocenterfood")!=null)//문의 및 안내
					DetailInfo_bean.setContact_Information(obj.get("infocenterfood").toString());
				else
					DetailInfo_bean.setContact_Information(null);
				
				if (obj.get("kidsfacility") != null)// 어린이 놀이방 여부
					DetailInfo_bean.setPlayroom_availability(obj.get("kidsfacility").toString());
				else
					DetailInfo_bean.setPlayroom_availability(null);
		
				if (obj.get("opendatefood") != null)// 개업일
					DetailInfo_bean.setOpening_Date(obj.get("opendatefood").toString());
				else
					DetailInfo_bean.setOpening_Date(null);
				
				if (obj.get("opentimefood") != null)// 영업시간
					DetailInfo_bean.setOpentime(obj.get("opentimefood").toString());
				else
					DetailInfo_bean.setOpentime(null);
			
				if (obj.get("packing") != null)// 포장여부
					DetailInfo_bean.setPackable(obj.get("packing").toString());
				else
					DetailInfo_bean.setPackable(null);
		
				if (obj.get("parkingfood") != null)// 주차시설
					DetailInfo_bean.setParking_facilities(obj.get("parkingfood").toString());
				else
					DetailInfo_bean.setParking_facilities(null);
			
				if (obj.get("reservationfood") != null)// 예약안내
					DetailInfo_bean.setReservation_Guide(obj.get("reservationfood").toString());
				else
					DetailInfo_bean.setReservation_Guide(null);
		
				if (obj.get("restdatefood") != null)// 쉬는날
					DetailInfo_bean.setRestday(obj.get("restdatefood").toString());
				else
					DetailInfo_bean.setRestday(null);

				if (obj.get("scalefood") != null)// 규모
					DetailInfo_bean.setScale(obj.get("scalefood").toString());
				else
					DetailInfo_bean.setScale(null);

				if (obj.get("seat") != null)// 좌석 수 Number_seats
					DetailInfo_bean.setNumber_seats(obj.get("seat").toString());
				else
					DetailInfo_bean.setNumber_seats(null);

				if (obj.get("smoking") != null)// 금연/흡연
					DetailInfo_bean.setSmoking_Smoking(obj.get("smoking").toString());
				else
					DetailInfo_bean.setSmoking_Smoking(null);

				if (obj.get("treatmenu") != null)// 취급 메뉴
					DetailInfo_bean.setHandling_menu(obj.get("treatmenu").toString());
				else
					DetailInfo_bean.setHandling_menu(null);

			}
			

			url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?serviceKey=" + Key
					+ "&numOfRows=1&pageSize=1&pageNo=1&startPage=1&MobileOS=ETC&MobileApp=AppTest&contentId="
					+ contentid + "&imageYN=Y&sublmageYN=Y&_type=json");
			isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			items = (JSONObject) JSONValue.parseWithException(isr);
			obj = (JSONObject) items.get("response");
			obj = (JSONObject) obj.get("body");
			System.out.println("이미지 정보 : " + obj);

			int totalcount = Integer.parseInt(obj.get("totalCount").toString());

			for (int i = 1; i <= totalcount; i++) {
				url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?serviceKey="
						+ Key + "&numOfRows=1&pageSize=1&pageNo=" + i
						+ "&startPage=1&MobileOS=ETC&MobileApp=AppTest&contentId=" + contentid
						+ "&imageYN=Y&sublmageYN=Y&_type=json");
				isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
				items = (JSONObject) JSONValue.parseWithException(isr);
				obj = (JSONObject) items.get("response");
				obj = (JSONObject) obj.get("body");
				obj = (JSONObject) obj.get("items");
				obj = (JSONObject) obj.get("item");
				if(obj.get("originimgurl")!=null) {
					DetailInfo_bean.getImg().add(obj.get("originimgurl").toString());
				}
				else {
					DetailInfo_bean.getImg().add("./jpg/no_image.gif");
				}
			}
		} catch (Exception e) {
			System.out.println("getInfo ERROR : " + e);
		}

		return DetailInfo_bean;
	}
	public ArrayList<DetailInfo_Review> getReviews(int contentid, int contenttypeid) {
		ArrayList<DetailInfo_Review> review=new ArrayList<DetailInfo_Review>();
		try {
			String sql="select * from review where content_id=? and content_type_id=? order by date_time desc LIMIT 5";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contentid);
			pstmt.setInt(2, contenttypeid);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				review.add(new DetailInfo_Review());
				review.get(review.size()-1).setContentid(rs.getInt("content_id"));
				review.get(review.size()-1).setContenttypeid(rs.getInt("content_type_id"));
				review.get(review.size()-1).setDate(rs.getString("date_time"));
				review.get(review.size()-1).setEmail_id(rs.getString("email_id"));
				review.get(review.size()-1).setLike_yn(rs.getInt("like_yn"));
				review.get(review.size()-1).setMemo(rs.getString("memo"));
				review.get(review.size()-1).setReview_num(rs.getInt("review_num"));
			}
			
			for(int i=0; i<review.size(); i++) {
				sql="select * from member where email_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, review.get(i).getEmail_id());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					review.get(i).setNikname(rs.getString("nikname"));
					review.get(i).setProfile_img(rs.getString("imgfile"));
				}
				
				sql="select * from review_hashtag where content_id=? and content_type_id=? and review_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, review.get(i).getContentid());
				pstmt.setInt(2, review.get(i).getContenttypeid());
				pstmt.setInt(3, review.get(i).getReview_num());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					review.get(i).getHashtag().add(rs.getString("hashtag"));
				}
				
				sql="select * from review_imgfile where content_id=? and content_type_id=? and review_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, review.get(i).getContentid());
				pstmt.setInt(2, review.get(i).getContenttypeid());
				pstmt.setInt(3, review.get(i).getReview_num());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					review.get(i).getImgfile().add(rs.getString("file_path"));
				}
			}
			
		}catch(Exception ex) {
			System.out.println("getReviews ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return review;
	}
	
	public DetailInfo_analysis getAnalysis_Result(int contentid, int contenttypeid) {
		DetailInfo_analysis analysis=new DetailInfo_analysis();
		try {
			String sql="select sum(like_yn) as cnt from review where content_id=? and content_type_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contentid);
			pstmt.setInt(2, contenttypeid);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				analysis.setTotal_like(rs.getInt("cnt"));
			}
			
			sql="select count(*) as cnt from review where content_id=? and content_type_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contentid);
			pstmt.setInt(2, contenttypeid);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				analysis.setTotal_review(rs.getInt("cnt"));
			}
			
			//analysis.setLike_percent((analysis.getTotal_like()/analysis.getTotal_review())*100);
		}catch(Exception ex) {
			System.out.println("getAnalysis_Result ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return analysis;
	}
}
