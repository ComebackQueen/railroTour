package net.Ajax.Note.Action;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.Ajax.Note.db.NoteImg_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.note.db.Note_Plans_List_DAO;

public class NoteImg_Change_Action implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		HttpSession session=request.getSession();
		
		int max=1024*1024*10;
		MultipartRequest multi;
		
		//File dir = new File("C:/Users/손준호/eclipse-workspace/Railro_Tour_WEB1234/WebContent/NoteCoverImg/"+session.getAttribute("ID").toString());
		File dir = new File("/cartoonpoet/tomcat/webapps/ROOT/NoteCoverImg/"+session.getAttribute("ID").toString());
		// 디렉토리들이 있는지 확인
		if(dir.isDirectory()){//디렉토리가 있으면
			//multi=new MultipartRequest(request, "C:/Users/손준호/eclipse-workspace/Railro_Tour_WEB1234/WebContent/NoteCoverImg/"+session.getAttribute("ID").toString(), max, "UTF-8");
			multi=new MultipartRequest(request, "/cartoonpoet/tomcat/webapps/ROOT/NoteCoverImg/"+session.getAttribute("ID").toString(), max, "UTF-8");
		}
		else{//디렉토리가 없으면
			dir.mkdirs();
			//multi=new MultipartRequest(request, "C:/Users/손준호/eclipse-workspace/Railro_Tour_WEB1234/WebContent/NoteCoverImg/"+session.getAttribute("ID").toString(), max, "UTF-8");
			multi=new MultipartRequest(request, "/cartoonpoet/tomcat/webapps/ROOT/NoteCoverImg/"+session.getAttribute("ID").toString(), max, "UTF-8");
		}
		String sysName=multi.getFilesystemName("save"); //이미지 이름
		
		NoteImg_DAO img_change=new NoteImg_DAO();
		
		int Note_ID=Integer.parseInt(multi.getParameter("note_id"));

		img_change.img_change(Note_ID, "./NoteCoverImg/"+session.getAttribute("ID").toString()+"/"+sysName);
		
		
		PrintWriter out=response.getWriter();
	 	System.out.println(request.getSession().getServletContext().getRealPath("/"));
	 	out.write(request.getSession().getServletContext().getRealPath("/"));
	 	out.close();
		return null;
	}
}