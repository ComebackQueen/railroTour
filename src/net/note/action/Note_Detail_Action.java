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

		Calendar cal=Calendar.getInstance(); //????????? ??????
		cal.setTime(to); //????????? ??????
		cal.add(Calendar.DATE, Basic_Info.getDays()-1); //??????????????? ?????? ????????? ???????????? ????????????.
		Basic_Info.setEnd_Date(transFormat.format(cal.getTime())); //??????
		
//		System.out.println("---------------------?????? ??????------------------------");
//		System.out.println("????????? : "+Basic_Info.getEmail_ID());
//		System.out.println("????????? : "+Basic_Info.getNikname());
//		System.out.println("?????? : "+Basic_Info.getProfileImg());
//		System.out.println("???????????? : "+Basic_Info.getBGImg());
//		System.out.println("????????? ?????? Y=1 N=0 : "+Basic_Info.getFllow_YN());
//		System.out.println("????????? ?????? Y=1 N=0 : "+Basic_Info.getLike_YN());
//		System.out.println("?????? ?????? ?????? : "+Basic_Info.getDate());
//		System.out.println("?????? ????????? : "+Basic_Info.getTema());
//		System.out.println("?????? ????????? ??? : "+Basic_Info.getLike());
//		System.out.println("?????? ????????? : "+Basic_Info.getView());
//		System.out.println("?????? ?????? ??? ??? : "+Basic_Info.getTravel_day());
		
		System.out.println("?????? ??? Free Memory " + Runtime.getRuntime().freeMemory()/1024/1024 + "MB");
		
		ArrayList<Note_Detail_Info_Bean> Detail_Info=new ArrayList<Note_Detail_Info_Bean>(); //????????????
		
		Detail_Info=note_detail.Note_getDetailInfo(Note_ID);
		
		ArrayList<Note_Travel_Area_Bean> Travel_Area=new ArrayList<Note_Travel_Area_Bean>();
		
		Travel_Area=note_detail.Note_getArea(Note_ID);//??????????????? ?????? ??????
				
		request.setAttribute("Basic_Info", Basic_Info); //?????? ?????? ??????
		request.setAttribute("Detail_Info", Detail_Info); //?????? ?????? ??????
		request.setAttribute("Navi_Info", Travel_Area);//?????? ?????? ??????
        forward.setRedirect(false); //disconnect
        forward.setPath("./planner_writer/Railro_Note_Detail.jsp"); //page forwarding  
		return forward;
	}

}