package net.notedata.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import etc.function.EasyMap;
import etc.function.JSONPassing;
import etc.function.MobileCheck;
import etc.function.Request;
import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.notedata.db.NoteDataDAO;

public class NoteDataAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		request.setCharacterEncoding("UTF-8");
		String result="들어왔네";
		String areaCode=request.getParameter("areaCode"); //해당 뷰 폼에서 검색어를 가져온다.
		String sigunguCode=request.getParameter("sigunguCode"); //해당 뷰 폼에서 검색어를 가져온다.
		String contentTypeId=request.getParameter("contentTypeId"); //해당 뷰 폼에서 검색어를 가져온다.
		
		System.out.println("일단 노트 액션에 들어옴");
		
		result =NoteDataDAO.getNoteData(areaCode, sigunguCode, contentTypeId);
	
		
	
		System.out.println(result);
		if(MobileCheck.isMobile(request)) { //모바일이라면
			System.out.println("모바일이네.");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			
			out.print(result);
			out.close();
			return null;
		} //웹이라면 
			return forward;	
	}
	
}
