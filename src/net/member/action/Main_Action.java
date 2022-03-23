package net.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.member.db.MainDAO;
import net.note.db.Note_Plans_List_Bean;

public class Main_Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(); // 회원 인증 성공시 아이디를 세션에 등록할 세션 객체 생성
		MainDAO maindao=new MainDAO();
		ArrayList<String> keyword=new ArrayList<String>();
		
		ArrayList<Note_Plans_List_Bean> RealTime_Railro_Note=new ArrayList<Note_Plans_List_Bean>();
		ArrayList<Note_Plans_List_Bean> Recommend_Railro_Note=new ArrayList<Note_Plans_List_Bean>();
		
		keyword=maindao.getPopular_keyword();
		RealTime_Railro_Note=maindao.getRealTime_Note();
		Recommend_Railro_Note=maindao.getPopular_Note();
		
		System.out.println("RealTime_Railro_Note : "+RealTime_Railro_Note.size());
		System.out.println("Recommend_Railro_Note : "+Recommend_Railro_Note.size());
		request.setAttribute("RealTime_Railro_Note", RealTime_Railro_Note);
		request.setAttribute("Recommend_Railro_Note", Recommend_Railro_Note);
		
		request.setAttribute("keyword", keyword);
		forward.setRedirect(false); // 접속 끊었다가 다시 연결하면서 새로운 정보를 보여준다.
		forward.setPath("./index.jsp"); // 메인으로
		return forward;
	}

}