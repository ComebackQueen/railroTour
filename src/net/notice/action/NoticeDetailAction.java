package net.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.notice.db.NoticeBean;
import net.notice.db.NoticeDAO;

//글 내용 확인
public class NoticeDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		NoticeDAO noticedao = new NoticeDAO();
		NoticeBean noticedata = new NoticeBean();
		
		int num = Integer.parseInt(request.getParameter("num"));
		noticedao.setReadCountUpdate(num);
		noticedata = noticedao.getDetail(num);
		
		
		if(noticedata == null){
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
		
		request.setAttribute("noticedata", noticedata);
		ActionForward forward= new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./Noticelist/Notice_view.jsp");
		return forward;
	}

}
