package net.Ajax.Note.Action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import net.Ajax.Note.db.Note_Step2_Ajax_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.note.db.Note_Step2_ALL_INFO_Bean;
import net.note.db.Note_Step2_Day_List_Bean;
import net.note.db.Note_Step2_Select_Bean;
import net.note.db.Note_Step2_Select_DAO;

public class Note_Plans_Info2_Import_Action implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		HttpSession session=request.getSession();
		int NoteID=Integer.parseInt(request.getParameter("NoteID"));
		
		JSONObject json=new JSONObject();
		System.out.println("노트 ID : "+NoteID);
		
		Note_Step2_Ajax_DAO Import_Data=new Note_Step2_Ajax_DAO();
		
		json=Import_Data.NoteInfo2_Select(NoteID);
		
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
		return null;
	}
	
}