package net.notice.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.notice.db.NoticeDAO;

public class NoticeListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
//		if(id==null){
//			response.setContentType("text/html;charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>");
//			out.println("alert('로그인 후 이용해주세요.');");
//			out.println("location.href='./MemberLogin.me'");
//			out.println("</script>");
//			out.close();
//			return null;
//		}
		NoticeDAO noticedao = new NoticeDAO();
		List noticelist = new ArrayList();
		
		int page =1;
		int limit = 10;
		
		if(request.getParameter("page") != null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount = noticedao.getListCount(); //총 리스트 수
		noticelist = noticedao.getNoticeList(page, limit); // 리스트
		
		//총 페이지 수
		int maxpage=(int)((double)listcount/limit+0.95); // 0.95를 더해 올림처리
		//현재 페이지에 보여줄 시작 페이지 수(1,11,21....)
		int startpage = (((int)((double)page/limit + 0.9)) - 1) * limit + 1;
		//현재 페이지에 보여줄 마지막 페이지 수(10,20,30...)
		int endpage = maxpage;
		
		if(endpage < startpage+limit-1) endpage=maxpage;
		
		request.setAttribute("page", page); // 현재 페이지 수
		request.setAttribute("maxpage", maxpage); // 최대 페이지 수
		request.setAttribute("startpage", startpage); // 현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("endpage", endpage); // 현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("listcount", listcount); //글 수
		request.setAttribute("limit", limit);
		request.setAttribute("noticelist", noticelist);
		
		forward.setRedirect(false);
		forward.setPath("./Noticelist/Notice_list.jsp");
		return forward;
	}
}
