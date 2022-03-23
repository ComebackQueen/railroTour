package net.Ajax.Note.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import net.Ajax.Note.db.Note_Step2_Ajax_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class Note_plans_Save_Action implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		
		int NoteID=Integer.parseInt(request.getParameter("NoteID")); // travel_id
		int Content_ID=Integer.parseInt(request.getParameter("Content_ID")); //content_id
		int Content_Type_ID=Integer.parseInt(request.getParameter("Content_Type_ID")); //content_type_id
		String Title=request.getParameter("Title");//route_name
		String Kind1=request.getParameter("Kind1");//kinds_1
		String Kind2=request.getParameter("Kind2");//kinds_2
		int sigungucode=Integer.parseInt(request.getParameter("sigungucode"));//sigungucode
		int areacode=Integer.parseInt(request.getParameter("areacode"));//areacode
		String areaname=request.getParameter("areaname");
		String date=request.getParameter("date");//date
		String week=request.getParameter("week");//week
		String day=request.getParameter("day");//day
		int order=Integer.parseInt(request.getParameter("order"));//order
		int day_orders=Integer.parseInt(request.getParameter("day_orders"));
		String memo=request.getParameter("memo");
		System.out.println("노트 아이디 : "+NoteID);
		System.out.println("콘텐츠 아이디 : "+Content_ID);
		System.out.println("콘텐츠 타입 : "+Content_Type_ID);
		System.out.println("제목 : "+Title);
		System.out.println("종류1 : "+Kind1);
		System.out.println("종류2 : "+Kind2);
		System.out.println("시군구 코드 : "+sigungucode);
		System.out.println("지역 코드 : "+areacode);
		System.out.println("날짜 : "+date);
		System.out.println("요일 : "+week);
		System.out.println("일차 : "+day);
		System.out.println("순번 : "+order);
		System.out.println("day_orders : "+day_orders);
		System.out.println("메모 : "+memo);

		Note_Step2_Ajax_DAO note_plan_save=new Note_Step2_Ajax_DAO();
		if(Content_Type_ID!=0) {//일반 일정 추가 일시
			note_plan_save.Plans_Save_Action(NoteID, Content_ID, Content_Type_ID, Title, Kind1, Kind2, sigungucode, areacode, date, week, day, order, areaname, day_orders);
		}
		else {//해시태그 일정 추가일시
			note_plan_save.Plans_HashTag_Save_Action(NoteID, Content_ID, Content_Type_ID, Title, Kind1, Kind2, sigungucode, areacode, date, week, day, order, areaname, day_orders, memo);		
		}
		return null;
	}
}
