package net.note.action;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.note.db.Note_Step1_Bean;
import net.note.db.Note_Step1_DAO;
import net.note.db.Note_area_Bean;

public class Note_Step1_InsertAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		String array_value=request.getParameter("array_value"); //여행 지역명
		String array_day=request.getParameter("array_day"); //해당 지역의 여행 일차
		HttpSession session=request.getSession();
		
		Note_Step1_DAO note_step1_DAO=new Note_Step1_DAO(); //DB 연결부분
		Note_Step1_Bean note_step1_Bean=new Note_Step1_Bean(); //정보 담아두는 곳
		
		note_step1_Bean.setEmail_id((String)session.getAttribute("id")); //계정 아이디
		note_step1_Bean.setNote_name(request.getParameter("title")); //노트명
		note_step1_Bean.setTravel_start_day(request.getParameter("calendar")); //출발일
		note_step1_Bean.setTravel_day(request.getParameter("travel_day")); //여행 일수
		note_step1_Bean.setTema(request.getParameter("tema_select")); //여행 테마 관광, 체험 , 식사, 휴식
		note_step1_Bean.setTravel_people(request.getParameter("person")); //여행 인원
		
		System.out.println(note_step1_Bean.toString());
		note_step1_DAO.insert_Note(note_step1_Bean); //노트 정보 기록
		
		StringTokenizer area=new StringTokenizer(array_value, ","); //여행 지역 토큰 분리
		StringTokenizer day=new StringTokenizer(array_day, ","); // 여행 일차 토큰 분리
		ArrayList<Note_area_Bean> Note_area=new ArrayList<Note_area_Bean>();
		
		int cnt=note_step1_DAO.select_travel_id(note_step1_Bean); //내 노트 아이디 넘버
		
		if(cnt==0) { //해당 노트가 없음 (내일로노트 정보를 이미 넣었는데 찾지를 못하는것!!)
			System.out.println("해당 노트가 없음!! 큰 문제임..!");
		}
		
		for(int i=0; area.hasMoreTokens(); i++) { //여행 지역과 일차를 토큰 분리해서 하나로 합치는 과정 한 배열 인수마다 (지역,일차)
			Note_area.add(new Note_area_Bean(area.nextToken(), day.nextToken()));
		}
		
		for(int o=0; o<Note_area.size(); o++) { //여행 지역과 일차를 DB에 넣는 작업
			note_step1_DAO.insert_travel_area(cnt, Note_area.get(o));
		}
		
        forward.setRedirect(true); //접속 끊었다가 다시 연결하면서 새로운 정보를 보여준다.
        forward.setPath("./Note_Step2_SelectAction.pl?num="+cnt); //노트2로 이동  
		return forward;
	}

}
