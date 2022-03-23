package net.notice.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.notice.db.NoticeBean;
import net.notice.db.NoticeDAO;

//수정 처리
public class NoticeModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		boolean result=false;
		
		NoticeDAO noticedao = new NoticeDAO();
		NoticeBean noticedata = new NoticeBean();


		//파일 업로드 부분 경로 및 파일 크기 지정
		String saveFolder = "C:/Users/손준호/eclipse-workspace/Railro_Tour_WEB1234/WebContent/file";
		String saveFo ="./file/";
		String formName = "";
		String fileName = "";
		String fileRealName = "";
				
		String filename1 = "";
		String origfilename1 = "";
		String filename2 = "";
		String origfilename2 = "";
		String filename3 = "";
		String origfilename3 = "";
		String filename4 = "";
		String origfilename4 = "";
		String filename5 = "";
		String origfilename5 = "";
				
		File file = null;
		
		int fileSize = 5*1024*1024;
		MultipartRequest multi;
		

		try{
			multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			int num = Integer.parseInt(multi.getParameter("num"));
			String id = multi.getParameter("id");
			//String existFilepath = multi.getParameter("filepath");
			boolean usercheck = noticedao.isNoticeWriter(num, id);
			if(!(id.compareTo("admin")==0)){
				if(usercheck==false){
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('수정할 권한이 없습니다.');");
					out.println("location.href='./NoticeList.bo';");
					out.println("</script>");
					out.close();
					return null;
				}
			}
			System.out.println(id);
			System.out.println(id.equals("admin"));
			Enumeration fileNames = multi.getFileNames();
			String existFile1 = multi.getParameter("file1");
			String existFile2 = multi.getParameter("file2");
			String existFile3 = multi.getParameter("file3");
			String existFile4 = multi.getParameter("file4");
			String existFile5 = multi.getParameter("file5");
			
			String file1 = (String)fileNames.nextElement();
			filename1 = multi.getFilesystemName(file1);
			origfilename1 = multi.getOriginalFileName(file1);
			String file2 = (String)fileNames.nextElement();
			filename2 = multi.getFilesystemName(file2);
			origfilename2 = multi.getOriginalFileName(file2);
			String file3 = (String)fileNames.nextElement();
			filename3 = multi.getFilesystemName(file3);
			origfilename3 = multi.getOriginalFileName(file3);
			String file4 = (String)fileNames.nextElement();
			filename4 = multi.getFilesystemName(file4);
			origfilename4 = multi.getOriginalFileName(file4);
			String file5 = (String)fileNames.nextElement();
			filename5 = multi.getFilesystemName(file5);
			origfilename5 = multi.getOriginalFileName(file5);
			
			System.out.println("파일==="+file1);
			System.out.println("또다른 파일=="+origfilename1);
			noticedata.setNum(Integer.parseInt(multi.getParameter("num")));
			noticedata.setTitle(multi.getParameter("title"));
			noticedata.setContent(multi.getParameter("content"));
			if(filename1 == null){
				noticedata.setFileName_1(existFile5);
			} else {
				noticedata.setFileName_1(filename1);
			}
			if(filename2 == null){
				noticedata.setFileName_2(existFile4);
			} else {
				noticedata.setFileName_2(filename2);
			}
			if(filename3 == null){
				noticedata.setFileName_3(existFile3);
			} else {
				noticedata.setFileName_3(filename3);
			}
			if(filename4 == null){
				noticedata.setFileName_4(existFile2);
			} else {
				noticedata.setFileName_4(filename4);
			}
			if(filename5 == null){
				noticedata.setFileName_5(existFile1);
			} else {
				noticedata.setFileName_5(filename5);
			}
			noticedata.setFilePath(saveFo);
			System.out.println(existFile1);
			System.out.println(existFile2);
			System.out.println(existFile3);
			System.out.println(existFile4);
			System.out.println(existFile5);
			
			System.out.println(filename1+ filename2 +filename3 +filename4+ filename5);
			
			result = noticedao.noticeModify(noticedata);
			if(result == false){
				System.out.println("게시판 수정 실패");
				return null;
			}
	
			System.out.println("게시판 수정 완료");
			forward.setRedirect(true);
			forward.setPath("./NoticeDetailAction.no?num="+noticedata.getNum());
			return forward;
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
