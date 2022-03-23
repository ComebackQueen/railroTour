package net.note.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.note.db.Note_Plans_List_DAO;

public class Note_Remove_Action implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		HttpSession session=request.getSession();
		String ID=(String) session.getAttribute("ID");
		int NoteID=Integer.parseInt(request.getParameter("NoteID"));
		
		Note_Plans_List_DAO remove=new Note_Plans_List_DAO();
		
		remove.remove_note_travel_area(NoteID); //노트 지역정보 삭제
		remove.remove_noteinfo1(NoteID); //노트1 정보삭제
		remove.remove_noteinfo2(NoteID); //노트2 정보삭제
		remove.remove_notelikecnt(NoteID); //노트 좋아요 정보 삭제

//        forward.setRedirect(false); // true시 접속 끊었다가 다시 연결하면서 새로운 정보를 보여준다.
//        forward.setPath("./planner_writer/Railro_Note_Plans_List.jsp"); //노트 목록으로 이동
		return null;
	}
}
