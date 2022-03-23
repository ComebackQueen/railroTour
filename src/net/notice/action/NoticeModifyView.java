package net.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.notice.db.NoticeBean;
import net.notice.db.NoticeDAO;

public class NoticeModifyView implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		NoticeDAO noticedao = new NoticeDAO();
		NoticeBean noticedata = new NoticeBean();
		
		int num = Integer.parseInt(request.getParameter("num"));
		noticedata = noticedao.getDetail(num);
		
		if(noticedata == null){
			System.out.println("수정_상세보기 실패");
			return null;
		}
		System.out.println("수정_상세보기 성공");
		request.setAttribute("noticedata", noticedata);
		forward.setRedirect(false);
		forward.setPath("./Noticelist/Notice_modify.jsp");
		return forward;
	}

}
