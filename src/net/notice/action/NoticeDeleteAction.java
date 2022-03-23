package net.notice.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.notice.db.NoticeDAO;

public class NoticeDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		System.out.println(id);
		boolean result=false;
		int num=Integer.parseInt(request.getParameter("num"));
		
		NoticeDAO noticedao = new NoticeDAO();
		boolean usercheck=noticedao.isNoticeWriter(num, id);
		if(!(id.equals("ing4114@naver.com"))){
			if(usercheck==false){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제할 권한이 없습니다.');");
				out.println("location.href='./NoticeList.no'");
				out.println("</script>");
				out.close();
				return null;
			}
		}
		result = noticedao.noticeDelete(num);
		//result = noticedao.arrayNoticeNum(num);
		
		if(result==false){
			System.out.println("게시판 삭제 실패");
			return null;
		}
		System.out.println("게시판 삭제 성공");
		forward.setRedirect(true);
		forward.setPath("./NoticeList.no");
		return forward;
	}
}