package net.notice.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.notice.db.NoticeBean;
import net.notice.db.NoticeDAO;

public class NoticeAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		NoticeDAO noticedao = new NoticeDAO();
		NoticeBean noticedata = new NoticeBean();
		ActionForward forward = new ActionForward();
		
		//파일 업로드 부분 경로 및 파일 크기 지정
		Calendar cal = Calendar.getInstance();
		String yStr = "" + cal.get(Calendar.YEAR);
		String mStr = "" + (cal.get(Calendar.MONTH) + 1);
		String dStr = "" + cal.get(Calendar.DATE);
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
		

		int fileSize = 5*1024*1024;
		
		boolean result = false;
		
		//파일 업로드 객체
		MultipartRequest multi;


		try{
			multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			Enumeration fileNames = multi.getFileNames();
			
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
			
			
			noticedata.setEmailid(multi.getParameter("id"));
			noticedata.setTitle(multi.getParameter("title"));
			noticedata.setContent(multi.getParameter("content"));
			noticedata.setFileName_1(filename1);
			noticedata.setFileName_2(filename2);
			noticedata.setFileName_3(filename3);
			noticedata.setFileName_4(filename4);
			noticedata.setFileName_5(filename5);
			noticedata.setFilePath(saveFo);
			result=noticedao.noticeInsert(noticedata);
			
			
			if(result==false){
				System.out.println("게시판 등록 실패");
				return null;
			}
			System.out.println(file1);
			System.out.println(file2);
			System.out.println(file3);
			System.out.println(file4);
			System.out.println(file5);
			System.out.println("게시판 등록 완료");
			forward.setRedirect(true);
			forward.setPath("./NoticeList.no");
			return forward;
		} catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
}
