package net.around_me.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import etc.function.MobileCheck;
import net.around_me.db.DetailsAroundMeDAO;
import net.around_me.db.ImageAroundMeDAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class ImageAroundAction  implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		request.setCharacterEncoding("UTF-8");
		String contentId=request.getParameter("contentId"); //해당 뷰 폼에서 검색어를 가져온다.
		System.out.println("일단 ImageAround 들어옴");
		String result = ImageAroundMeDAO.imageJson(Integer.parseInt(contentId));
		
		
		
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
