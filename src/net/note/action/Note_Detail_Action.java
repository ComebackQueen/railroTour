package net.note.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.note.db.Note_Basic_Info_Bean;
import net.note.db.Note_Detail_DAO;
import net.note.db.Note_Detail_Info_Bean;
import net.note.db.Note_Travel_Area_Bean;


public class Note_Detail_Action implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		HttpSession session=request.getSession();
		
		int Note_ID=Integer.parseInt(request.getParameter("num"));
		
		Note_Detail_DAO note_detail=new Note_Detail_DAO();
		
		note_detail.NoteViewIncrease(Note_ID); // Note Views 1 Increase
		
		Note_Basic_Info_Bean Basic_Info=new Note_Basic_Info_Bean();
		
		//Note Basic Information Import
		Basic_Info.setEmail_ID(note_detail.Note_getID(Note_ID)); // Note Email_ID Import
		Basic_Info.setNikname(note_detail.Note_getNikname(Basic_Info.getEmail_ID())); // Note Nikname Import
		Basic_Info.setProfileImg(note_detail.ProFile_getImg(Basic_Info.getEmail_ID())); // Note Profile Image Path import
		Basic_Info.setBGImg(note_detail.BackGround_getImg(Note_ID)); //Note Background Img Import
		Basic_Info.setFllow_YN(note_detail.Follow_getYN((String)session.getAttribute("ID"), Basic_Info.getEmail_ID())); //Note Follow Yes or No?
		Basic_Info.setLike_YN(note_detail.Like_getYN((String)session.getAttribute("ID"), Note_ID)); // Note Like Yes or No?
		Basic_Info.setDate(note_detail.Note_getDate(Note_ID)); // Note Date Import
		Basic_Info.setTema(note_detail.Note_getTema(Note_ID)); // Note Tema Import
		Basic_Info.setLike(note_detail.Note_LikeCnt(Note_ID)); //Note Like Import
		Basic_Info.setView(note_detail.Note_View(Note_ID)); //Note Views Import
		Basic_Info.setTravel_day(note_detail.Note_TravelDay(Note_ID)); //Note Travel Day Import
		Basic_Info.setNote_name(note_detail.Note_getName(Note_ID));
		Basic_Info.setDays(note_detail.Note_getDays(Note_ID));
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date to = transFormat.parse(Basic_Info.getDate());

		Calendar cal=Calendar.getInstance(); //캘릭터 설정
		cal.setTime(to); //시작일 지정
		cal.add(Calendar.DATE, Basic_Info.getDays()-1); //시작일에서 해당 날짜의 일차만큼 더해준다.
		Basic_Info.setEnd_Date(transFormat.format(cal.getTime())); //저장
		
//		System.out.println("---------------------기본 정보------------------------");
//		System.out.println("아이디 : "+Basic_Info.getEmail_ID());
//		System.out.println("닉네임 : "+Basic_Info.getNikname());
//		System.out.println("프사 : "+Basic_Info.getProfileImg());
//		System.out.println("커버사진 : "+Basic_Info.getBGImg());
//		System.out.println("팔로워 유무 Y=1 N=0 : "+Basic_Info.getFllow_YN());
//		System.out.println("좋아요 유무 Y=1 N=0 : "+Basic_Info.getLike_YN());
//		System.out.println("노트 여행 날짜 : "+Basic_Info.getDate());
//		System.out.println("노트 테마명 : "+Basic_Info.getTema());
//		System.out.println("노트 좋아요 수 : "+Basic_Info.getLike());
//		System.out.println("노트 조회수 : "+Basic_Info.getView());
//		System.out.println("노트 여행 일 수 : "+Basic_Info.getTravel_day());
		
		System.out.println("발송 전 Free Memory " + Runtime.getRuntime().freeMemory()/1024/1024 + "MB");
		
		ArrayList<Note_Detail_Info_Bean> Detail_Info=new ArrayList<Note_Detail_Info_Bean>(); //상세정보
		
		Detail_Info=note_detail.Note_getDetailInfo(Note_ID);
		
		ArrayList<Note_Travel_Area_Bean> Travel_Area=new ArrayList<Note_Travel_Area_Bean>();
		
		Travel_Area=note_detail.Note_getArea(Note_ID);//네비게이션 정보 등록
				
		request.setAttribute("Basic_Info", Basic_Info); //기본 정보 등록
		request.setAttribute("Detail_Info", Detail_Info); //상세 정보 등록
		request.setAttribute("Navi_Info", Travel_Area);//네비 정보 등록
        forward.setRedirect(false); //disconnect
        forward.setPath("./planner_writer/Railro_Note_Detail.jsp"); //page forwarding  
		return forward;
	}

}