package com.gw.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gw.app.dao.Board_comm_dao;
import com.gw.app.dao.Board_dao;
import com.gw.app.dao.Data_dao;
import com.gw.app.dao.Email_dao;
import com.gw.app.dao.Notice_dao;
import com.gw.app.dao.Position_dao;
import com.gw.app.dao.Team_dao;
import com.gw.app.dao.User_dao;
import com.gw.app.dao.Work_dao;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	User_dao udao;
	
	@Autowired
	Work_dao wdao;
	
	@Autowired
	Email_dao edao;
	
	@Autowired
	Board_dao bdao;
	
	@Autowired
	Notice_dao ndao;
	
	@Autowired
	Data_dao ddao;
	
	@Autowired
	Position_dao pdao;
	
	@Autowired
	Team_dao tdao;
	
	@Autowired
	Board_comm_dao bcdao;
	
	@Autowired
	private ServletContext context;
	
	private String data_uppath = "resources/dataroom/";
	//	@Autowired
	//	private String root_path = context.getRealPath("/");
	//	//context.getRealPath는 웹 컨텐츠 경로(서버 경로 WAR폴더 구조 경로)를 
	//	//디스크 파일 시스템 경로로 변환하기 위해 쓴다
		
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Main(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "Main";
	}
	
	@RequestMapping({"/","/Main"})
	public String Main() {
		System.out.println("메인");
		return "Main";
	}
	
	
	@RequestMapping("Main_Top")
	public String Main_Top() {
		
		
		return "Main_Top";
	}
	@RequestMapping("Gw_home")
	public String Gw_home() {
		
		
		return "Gw_home";
	}
	
	@RequestMapping("Gw_user")
	public String Gw_user(Model model) {
		
		model.addAttribute("team", tdao.team_list());
		model.addAttribute("position", pdao.position_list());
		
		return "Gw_user";
	}
	@RequestMapping("gw_main")
	public String gw_main() {
		
		
		return "gw_main";
	}
	

	
	
	@RequestMapping("insert_user")
	public String insert_user (String user_pw, String user_name, String team_idx, String position_idx, String user_day, String user_tel, String user_email, String user_add,
								MultipartHttpServletRequest mrequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		//넘어온 파일에서 이름 뽑아내기 - filename
		
		 MultipartFile file = mrequest.getFile("user_imgname");
		String user_imgname = file.getOriginalFilename();
		System.out.println(user_imgname+"이미지가정상적정상정삭");
		
		//넘어온 name 가져오기 - name
		String name=mrequest.getParameter("name");
		System.out.println(name+"파일명파일명파일명");
		
		//넘어온 mrequest를 가지고 파일업로드 처리
		String root_path=context.getRealPath("/");
		String attach_path="resources/user_img/";
		
		//파일업로드 메서드
		//file.transferTo(new File("위치를 포함한 파일명"));
		try {
			file.transferTo(new File(root_path+attach_path+user_imgname));
			System.out.println(user_imgname+"파일 업로드 완료!");
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("user_pw", user_pw);
		map.put("user_name", user_name);
		map.put("team_idx", Integer.parseInt(team_idx));
		map.put("position_idx", Integer.parseInt(position_idx));
		map.put("user_day", user_day);
		map.put("user_tel", user_tel);
		map.put("user_email", user_email);
		map.put("user_add", user_add);
		map.put("user_imgname", user_imgname);
		map.put("user_imgpath", attach_path);

		udao.insert_user(map);
		return "redirect:/Gw_user_list";
	}
	
	@RequestMapping("Gw_user_list")
	public String Gw_user_list(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		model.addAttribute("result", udao.user_list(map));
		return "Gw_user_list";
	}
	
	
	@RequestMapping("logindo")
	public String logindo(String user_idx,String user_pw, HttpSession ses, Model model){
		 Map<String, Object> map = new HashMap<String, Object>();
	      map.put("user_idx", user_idx);
	      map.put("user_pw", user_pw);
	      
	   
	      String name = udao.loginname(map);
	      System.out.println(name);
	      
	      
		 int succes  = udao.logindo(map);
		 
	      
	    
	    
	      
	      if(succes == 1) {
	    	  int rank = udao.login_rank(map);
	    	  if(rank >= 4) {
	    		  
	    		  
	    		  ses.setAttribute("user_name", name); 
	    		  ses.setAttribute("user_idx",user_idx);
	    		
		    	  ses.setAttribute("user_rank",rank);
		    	  
		    	  return "Gw_home";
	    	  }else {
	    		  
	    		  ses.setAttribute("user_name", name);
	    		  ses.setAttribute("user_idx",user_idx);
	    		  return "Gw_home"; 
	    	  } 	    	  
	    	  
	      }else {
	    	  
	    	  System.out.println("실패");
	    	  return "Main";  
	      }   
		
	}
	
	//에이젝스 로그인 확인'
	@RequestMapping("main_loginchk")
	public void main_loginchk(HttpServletResponse res, String user_idx, String user_pw) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_idx", user_idx); 
		map.put("user_pw", user_pw); 
		
		System.out.println(user_idx);
		
		int chk = udao.logindo(map);
		JSONObject j = new JSONObject(); 
		
		if(chk == 0) {
			System.out.println("로그인 실패");
			
			j.put("result", "아이디 혹은 비밀번호가 틀립니다");			
			res.getWriter().print(j.toString());
			
			
		}else {
			System.out.println("로성공");
			
			j.put("result", "로그인 성공");			
			res.getWriter().print(j.toString());

		}
		
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		
		return "Main";
	}
	//출퇴근 
		@RequestMapping("work_user")
		public String work_user(@RequestParam(required = false, defaultValue = "1")int page, int user_idx, Model model) {
			System.out.println("출근 페이지");
			//페이징처리
			//개인 출퇴근 확인 리스트 만들기
			
			int perpageNum = 20;
			int pageStart = (page-1) * perpageNum;
			
			int totalCount = wdao.user_work_paging(user_idx);
			int tempEndPage = (int) (Math.ceil(totalCount / (double) perpageNum));
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_idx", user_idx);
			map.put("pageStart", pageStart);
			map.put("perpageNum", perpageNum);
			
			int dispageNum = 10;
			int endPage =  (int) (Math.ceil(page / (double) dispageNum) * dispageNum);
			
			int startPage =  (endPage - dispageNum) + 1;
			boolean prev = startPage == 1 ? false : true;
			boolean next = endPage * perpageNum >= totalCount ? false : true;
			if(endPage > tempEndPage) {
				endPage = tempEndPage;
				//이거 선언 안 해 주면은 dispageNum이 설정된 페이지 수만큼 나오기 때문에 꼭 설정해 줘야 댐
			}
			
			model.addAttribute("user_work", wdao.user_work_list(map));
			model.addAttribute("tempEndPage", tempEndPage);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("prev", prev);
			model.addAttribute("next", next);
			model.addAttribute("pageck", page);
			
			
			return "work_user";
		}
	
	//출근 버튼 에이젝스 
	@RequestMapping("work_ck")
	public void work_ck(String user_idx, HttpServletResponse response) throws IOException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		String today = sdf.format(cal.getTime());

		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject j = new JSONObject(); 
		
		map.put("today", today);
		map.put("user_idx", user_idx);
		int work_inck = wdao.work_inck(map);
		System.out.println(work_inck);
		if(work_inck==0) {
			j.put("result", "출근");
			response.getWriter().print(j.toString());
		}else {
			j.put("result", "이미 출근 처리되었습니다.");
			response.getWriter().print(j.toString());
		}
	}
	
	//퇴근 버튼 에이젝스
	@RequestMapping("work_offck")
	public void work_offck(String user_idx, HttpServletResponse response) throws IOException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		String today = sdf.format(cal.getTime());
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject j = new JSONObject(); 
		
		//update gw_work set work_off = NOW() WHERE DATE(work_on) = DATE(20210525) AND user_idx = 5
		
		map.put("today", today);
		map.put("user_idx", user_idx);
		
		int work_inck = wdao.work_inck(map);
		int work_offck = wdao.work_offck(map);
		// ㅋㅋㅋㅋㅋ 다ㅣ른 거 안 먹이고 에이젝스만 넣어놨네 나?ㅋㅋㅋ다른게 뭐가 필요하죠 ㅎㅎ
		//엥 갑자기 왜 퇴근 안 먹지?디
		//디비는 바꾸니거 없죠? 응응 잠만 다시 확인해볼게 이거 지금 두개라서 오류 생기는 건가? 다른 아이디로 해보죠 11 1234
		if(work_inck > 0) {
			if(work_offck == 0) {
				j.put("result", "퇴근");
				response.getWriter().print(j.toString());
			}else {
				j.put("result", "이미 퇴근 처리되었습니다.");
				response.getWriter().print(j.toString());
			}
			
		}else if(work_inck==0){
			j.put("result", "출근 등록이 되지 않았습니다. 출근 확인부터 완료해 주십시오.");
			response.getWriter().print(j.toString());
		}
		
	}
	
	@RequestMapping("work_in")
	public String work_in(int user_idx, String work_on ) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_idx", user_idx);
		
	
		System.out.println(user_idx);
		wdao.insert_work(map);
		
		return "redirect:/work_user?user_idx="+user_idx;
	}
	
	@RequestMapping("work_out")
	public String work_out(int user_idx, String work_off) {
		
		//등신짓하고 있었네;; ㅋㅋㅋ
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		String today = sdf.format(cal.getTime());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", today);
		map.put("user_idx", user_idx); 
		
		wdao.work_update(map);
		
	
		
		return "redirect:/work_user?user_idx="+user_idx;
	}
	
	@RequestMapping("work_userlist")
	public String work_userlist(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		model.addAttribute("work", wdao.work_list(map));
		return "work_userlist";
	}
	

	
	
	
	
	
	//받은메일함
	@RequestMapping("Email_list")
	public String Email_list(int email_re, Model model ,@RequestParam(required = false, defaultValue = "1")int page ) {
		System.out.println("이메일리 받은편지"+email_re);
		
		
		int pageNum = 3;
		
		int pageStart = (page-1) * pageNum;
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("email_re", email_re);
		map.put("pageStart", pageStart);
		map.put("pageNum", pageNum);
		
		int totalCnt = edao.Email_list_a(email_re);
		System.out.println("이메일re"+ edao.Email_list_a(email_re));
		
		int temp_EndPage = (int) (Math.ceil(totalCnt / (double) pageNum)); // 마지막 페이지
		
		int dispageNum = 10; // 게시판 화면에 한번에 보여질 페이지의 개수
		int end_Page =  (int) (Math.ceil(page / (double) dispageNum) * dispageNum);
		int startPage =  (end_Page - dispageNum) + 1;
		//시작 페이지 
		
		System.out.println(startPage);
		
		boolean prev = startPage == 1 ? false : true;
		//현재 보여주는 dispageNum의 시작 페이지가 1이냐 
		boolean next = end_Page * pageNum >= totalCnt ? false : true;
		//endPage * perpageNum 토탈보다 크거나 같냐
		if(end_Page > temp_EndPage) {
			end_Page = temp_EndPage;
			//이거 선언 안 해 주면은 dispageNum이 설정된 페이지 수만큼 나오기 때문에 꼭 설정해 줘야 댐
		}
		
		
		model.addAttribute("email", edao.list_email(map));
		
		model.addAttribute("temp_EndPage", temp_EndPage);
		model.addAttribute("end_Page", end_Page);
		model.addAttribute("startPage", startPage);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		model.addAttribute("page_page", page);
		
		return "Email_list";
	}
	
	
	///메일삭제
	@RequestMapping("email_delete")
	public String email_delete(Model model, String eamil_idxs, String email_re) {
		//컨트롤러에서 그대로 받아와서 
		System.out.println("이메일 아이디엑스"+email_re);
		System.out.println(eamil_idxs);
		if(eamil_idxs.contains(",")) {
			if (eamil_idxs.contains(",")) {
				String[] email_idx;
				email_idx = eamil_idxs.split(",");
				
				for(int i=0; i<email_idx.length; i++) {
					System.out.println("반복"+email_idx[i]);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("email_idx", Integer.parseInt(email_idx[i]));
					edao.emaildelete(map);	 	
					System.out.println(eamil_idxs);
					System.out.println(email_idx);
					}
			 }
		}else {
			//여기 엘스에도 그냥 값을 변형해서 넣을 수 있게 만들어둔거
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email_idx", Integer.parseInt(eamil_idxs));
			edao.emaildelete(map);
		}
		 //먹이면 오류ㅠ 나도라구요 기달려봐
		
		return "redirect:/Email_list?email_re="+email_re;
		//리다이렉트로 보낼 때 담아서 보냄
	}
	
	//리드페이지에서 메일삭제
	@RequestMapping("Email_delete")
	public String Email_delete(int email_idx, String email_re) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email_idx", email_idx);
		System.out.println("이메일 아이디엑스"+email_re);
		System.out.println("이메일 번호"+email_idx);
		edao.email_read_delete(map);
		
		return "redirect:/Email_list?email_re="+email_re;
	}
	
	
	
	
	@RequestMapping("Email_read")
	public String Email_read(Model model,int email_idx) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email_idx", email_idx);
		
		edao.Email_readtime(email_idx);
		model.addAttribute("email_re", edao.email_read(email_idx));
		
		return "Email_read";
	}
	//읽은시간 메소드

	
	
	//보낸편지함
	@RequestMapping("Email_read_sent")
	public String Email_read_sent(Model model,int email_idx) {
		
		model.addAttribute("email_re", edao.email_read(email_idx));
		
		return "Email_read_sent";
	}
	
	
	
	
	@RequestMapping("Email_insert")
	public String Email_insert() {
		
		return "Email_insert";
	}
	
	
	
	//보낸메일함
		@RequestMapping("Email_SentLetter")
		public String Email_SentLetter(int user_idx, Model model,  @RequestParam(required = false, defaultValue = "1")int page) {
			System.out.println(user_idx);
		
			
			int pageNum = 3;
			
			int pageStart = (page-1) * pageNum;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_idx", user_idx);
			map.put("pageStart", pageStart);
			map.put("pageNum", pageNum);
			 
			System.out.println("보여질개수"+pageNum);
			
			System.out.println("카웉트 갯수 유저아이디="+edao.Email_list_page(user_idx));
			
			int totalCnt = edao.Email_list_page(user_idx);
			int temp_EndPage = (int) (Math.ceil(totalCnt / (double) pageNum));
			
			System.out.println("카운트"+totalCnt);
			int dispageNum = 10; // 게시판 화면에 한번에 보여질 페이지의 개수
			int end_Page =  (int) (Math.ceil(page / (double) dispageNum) * dispageNum);
			int startPage =  (end_Page - dispageNum) + 1;
			
			
			boolean prev = startPage == 1 ? false : true;
			//현재 보여주는 dispageNum의 시작 페이지가 1이냐 
			boolean next = end_Page * pageNum >= totalCnt ? false : true;
			//endPage * perpageNum 토탈보다 크거나 같냐
			if(end_Page > temp_EndPage) {
				end_Page = temp_EndPage;
			}
			
			
			model.addAttribute("email3", edao.sent_email(map));
			
			model.addAttribute("temp_EndPage",temp_EndPage);
			model.addAttribute("end_Page", end_Page);
			model.addAttribute("startPage", startPage);
			model.addAttribute("prev", prev);
			model.addAttribute("next", next);
			model.addAttribute("page_page", page);
			
			return "Email_SentLetter";
		}
		
		
		//보낸메일함 리드에서 삭제
		@RequestMapping("Email_delete_sent")
		public String Email_delete_sent(int email_idx,String user_idx) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email_idx", email_idx);
			System.out.println("user 번호"+user_idx);
			System.out.println("이메일 번호"+email_idx);
			edao.email_read_delete(map);
			return "redirect:/Email_SentLetter?user_idx="+user_idx;
		}
		
		
		
		//보낸메이함 체크박스 삭제
		@RequestMapping("email_delete_sent_read")
		public String email_delete_sent_read(String user_idx,Model model, String eamil_idxs) {
			//컨트롤러에서 그대로 받아와서 
			System.out.println("이메일 아이디엑스"+user_idx);
			System.out.println(eamil_idxs);
			if(eamil_idxs.contains(",")) {
				if (eamil_idxs.contains(",")) {
					String[] email_idx;
					email_idx = eamil_idxs.split(",");
					
					for(int i=0; i<email_idx.length; i++) {
						System.out.println("반복"+email_idx[i]);
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("email_idx", Integer.parseInt(email_idx[i]));
						edao.emaildelete(map);	 	
						System.out.println(eamil_idxs);
						System.out.println(email_idx);
						}
				 }
			}else {
				//여기 엘스에도 그냥 값을 변형해서 넣을 수 있게 만들어둔거
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("email_idx", Integer.parseInt(eamil_idxs));
				edao.emaildelete(map);
			}
			
			return "redirect:/Email_SentLetter?user_idx="+user_idx;
		}
		
		
		
		//메일 쓰기
		@RequestMapping("email_submit")
		public String email_submit(int user_idx, int email_re, String email_title, String email_content, String email_readtime, String email_imgname
				,String email_imgpath) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_idx", user_idx);
			map.put("email_re", email_re);
			map.put("email_title", email_title);
			map.put("email_content", email_content);
			map.put("email_readtime", email_readtime);
			map.put("email_imgname", email_imgname);
			map.put("email_imgname", email_imgname);
			
			edao.insert_email(map);
			
			return "redirect:/Email_SentLetter?user_idx="+user_idx;
		}
		
		
		
		
		
		///////게시판////
		
		//공지사항 
		@RequestMapping("Gw_Noticelist")
		public String Gw_Noticelist(@RequestParam(required = false, defaultValue = "1")int page, Model model) {
			Map<String, Object> map = new HashMap<String, Object>();
			

			int perpageNum = 20;
			int pageStart = (page-1) * perpageNum;
			
			int totalCount = ndao.Notice_paging();
			int tempEndPage = (int) (Math.ceil(totalCount / (double) perpageNum));
			
			map.put("pageStart", pageStart);
			map.put("perpageNum", perpageNum);
			
			int dispageNum = 10;
			int endPage =  (int) (Math.ceil(page / (double) dispageNum) * dispageNum);
			
			int startPage =  (endPage - dispageNum) + 1;
			boolean prev = startPage == 1 ? false : true;
			boolean next = endPage * perpageNum >= totalCount ? false : true;
			if(endPage > tempEndPage) {
				endPage = tempEndPage;
				//이거 선언 안 해 주면은 dispageNum이 설정된 페이지 수만큼 나오기 때문에 꼭 설정해 줘야 댐
			}
			
			model.addAttribute("notice",ndao.notice_list(map));
			model.addAttribute("tempEndPage", tempEndPage);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("prev", prev);
			model.addAttribute("next", next);
			model.addAttribute("pageck", page);
			
			
			
			return "Gw_Noticelist";
		}
		
		
		//공지사항 글쓰기
		@RequestMapping("Gw_Noticeinsert")
		public String Gw_Noticeinsert() {
			
			
			return "Gw_Noticeinsert";
		}
		
		@RequestMapping("notice_insert")
		public String notice_insert(int user_idx, String notice_title, String notice_content) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_idx", user_idx);
			map.put("notice_title", notice_title);
			map.put("notice_content", notice_content);
			
			ndao.notice_insert(map);
			
			return "redirect:/Gw_Noticelist";
		}
		
		@RequestMapping("Gw_Noticeread")
		public String Gw_Noticeread(Model model,int notice_idx, int page) {
			
			System.out.println(notice_idx);
			
			model.addAttribute("notice2",ndao.notice_read(notice_idx));
			model.addAttribute("page", page);
			
			ndao.notice_hit(notice_idx);
			return "Gw_Noticeread";
		}
		
		//공지사항 수정페이지
		@RequestMapping("Gw_Noticeupdatepage")
		public String Gw_Noticeupdatepage(int notice_idx, Model model) {
				
			model.addAttribute("notice",ndao.notice_read(notice_idx));
			
			return "Gw_Noticeupdate";
		}
		
		@RequestMapping("Gw_Noticeupdate")
		public String Gw_Noticeupdate(String notice_title, String notice_content, int notice_idx) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("notice_title", notice_title);
			map.put("notice_content", notice_content);
			map.put("notice_idx", notice_idx);
			
			ndao.Notice_update(map);
			
			return "redirect:/Gw_Noticeread?notice_idx="+notice_idx;
			
		}
		
		//공지사항 삭제 
		@RequestMapping("Gw_Noticedelet")
		public String Gw_Noticedelet(int notice_idx) {
			ndao.Notice_delete(notice_idx);
			return "redirect:/Gw_Noticelist";
		}


		///익명 게시판
		@RequestMapping("Gw_Boardlist")
		public String Gw_Boardlist(Model model,@RequestParam(required = false, defaultValue = "1")int page ) {
			
			int pageNum = 3; //보여질 개수
			int pageStart = (page-1) * pageNum;
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("pageStart", pageStart);
			map.put("pageNum", pageNum);
			
			int totalCount = bdao.board_page();  //전체 게시글 카운트
			
			System.out.println("페이지 카운트"+bdao.board_page());
			int tempEndPage = (int) (Math.ceil(totalCount / (double) pageNum)); //여기 질문
			int dispageNum = 10; //[1],[2] ... [10]번까지 보임
			int end_Page =  (int) (Math.ceil(page / (double) dispageNum) * dispageNum); //여기 질문
			int startPage =  (end_Page - dispageNum) + 1;  //스타트 페이지와 페이지 스타트의 차이점?
			
			boolean prev = startPage == 1 ? false : true;
			boolean next = end_Page * pageNum >= totalCount ? false : true;
			if(end_Page > tempEndPage) {
				end_Page = tempEndPage;
				//이거 선언 안 해 주면은 dispageNum이 설정된 페이지 수만큼 나오기 때문에 꼭 설정해 줘야 댐
			}
			
			
			model.addAttribute("board", bdao.board_list(map));
			model.addAttribute("tempEndPage", tempEndPage);
			model.addAttribute("startPage", startPage);
			model.addAttribute("end_Page", end_Page);
			model.addAttribute("prev", prev);
			model.addAttribute("next", next);
			model.addAttribute("page_page", page);
			
			return "Gw_Boardlist";
		} 
		
		@RequestMapping("Gw_board_read")
		public String Gw_board_read(Model model,int board_idx) {
			
			
			model.addAttribute("board2",bdao.board_read(board_idx));
			model.addAttribute("comm",bcdao.comm_list(board_idx));
			model.addAttribute("comm_cnt", bcdao.comm_cnt(board_idx));
			bdao.board_hit(board_idx);
			return "Gw_board_read";
		}
		
		
		@RequestMapping("Gw_board_insert")
		public String Gw_board_insert() {
			
			
			return "Gw_board_insert";
		}
		
		@RequestMapping("board_insert")
		public String board_insert(int user_idx, String board_title, String board_content) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_idx", user_idx);
			map.put("board_title", board_title);
			map.put("board_content", board_content);
			
			
			bdao.board_insert(map);
			
			return "redirect:/Gw_Boardlist";
		}
		
		@RequestMapping("board_delete")
		public String board_delete(int board_idx){
			
			bdao.board_delete(board_idx);
			
			return "redirect:/Gw_Boardlist";
		}
		
		/////////댓글
		@RequestMapping("comm")
		public String comm(int user_idx, int board_idx, String comm_cotent, Model model) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_idx", user_idx);
			map.put("board_idx", board_idx);
			map.put("comm_cotent", comm_cotent);
			
			
			bcdao.comm_insert(map);
		
			return "redirect:/Gw_board_read?board_idx="+board_idx;
		}
		
		
		//자료실 목록
				//*페이징 처리하기
				@RequestMapping("Gw_Datapage")
				public String Gw_Datapage(@RequestParam(required = false, defaultValue = "1")int page,
						 Model model) {
					
					//페이징 관련된 주석은 여기에 어지간하면 다 달아뒀걷,ㄴ?
					//네여1! 보고 혹시 이해 안 가는 거 잇으면 물어보면 댐 기억하고 있을 때 물어보는 게 낫다 ㅋㅋㅋ ㅋㅋ빠른 시일내에 무러볼게요 저거 자료실만 먹여놓은거죠?
					//ㅇㅇㅇ 자료실만 먹여둿어 메소드로 빼려다가 구분할 때 ㅈㄴ 정신없을 것 같아서 굿둣!! 
					
					
					// page = 보여줄 페이지 번호이고 최초 실행될 때에는 디폴트값이 1로 설정된다
					//required 받아온 값이 없어도 오류가 생기지 않도록 하는 것이며 변수가 없을 때는 기본값 1을 넣어준다
					int  perpageNum = 2;
					//페이지당 보여줄 게시글의 개수 
					
					//sql구문 limit에 시작 위치 지정을 한다.
					// 예를 들어 10개씩 출력하는 경우 3페이지의 page가 1이면 0이 되어야 처음부터 10개씩 나온다
					//마이바티스 조회 쿼리의 #{pageStart}에 전달된다
					int pageStart = (page-1) * perpageNum;
					
					//SELECT * FROM gw_data_room 
					//join gw_user using(user_idx) WHERE data_idx > 0 ORDER BY data_idx DESC, data_date DESC 
					//LIMIT #{pageStart}, #{perpageNum}
					
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("pageStart", pageStart);
					map.put("perpageNum", perpageNum);
					
					int totalCount = ddao.dataroom_paging();  //전체 데이터의 개수
					int tempEndPage = (int) (Math.ceil(totalCount / (double) perpageNum)); // 마지막 페이지 //여기 질문 하기 
					//위에 까지가 페이지 안에 게시글 끊어서 보여주는 것 
					
					int dispageNum = 10; // 게시판 화면에 한번에 보여질 페이지의 개수
					int endPage =  (int) (Math.ceil(page / (double) dispageNum) * dispageNum);
					//마지막 페이지
					// 예) [1][2][3][4][5][6]까지 있으면 [5]까지만 보여준다
					int startPage =  (endPage - dispageNum) + 1;
					//시작 페이지 
					System.out.println(startPage);
					boolean prev = startPage == 1 ? false : true;
					//현재 보여주는 dispageNum의 시작 페이지가 1이냐 
					boolean next = endPage * perpageNum >= totalCount ? false : true;
					//endPage * perpageNum 토탈보다 크거나 같냐
					if(endPage > tempEndPage) {
						endPage = tempEndPage;
						//이거 선언 안 해 주면은 dispageNum이 설정된 페이지 수만큼 나오기 때문에 꼭 설정해 줘야 댐
					}
					
					model.addAttribute("dataroom",ddao.dataroom_list(map));
					model.addAttribute("tempEndPage", tempEndPage);
					model.addAttribute("startPage", startPage);
					model.addAttribute("endPage", endPage);
					model.addAttribute("prev", prev);
					model.addAttribute("next", next);
					model.addAttribute("pageck", page);
					//현재페이지에 클릭되지 않도록 조건문을 걸때 필요
					//미친 건가.. 뭐가 이렇게 많아? 사람들이 이래서 아예 클래스를 따로 빼구나..
					//메소드로 빼서 정의할 수 있는지 확인하기
					
					return "Gw_Datapage";
				}
				
				
				//자료실 게시글 읽기
				@RequestMapping("Gw_Dataread")
				public String Gw_Dataread(int data_idx,int page, Model model) {
					
					model.addAttribute("dataroom_read", ddao.dataroom_read(data_idx));
					model.addAttribute("page", page);
					
					return "Gw_Dataread";
				}
				
				//자료실 글쓰기 
				@RequestMapping("Gw_Datainsertpage")
				public String Gw_Datainsertpage() {
					
					return "Gw_Datainsert";
					
				}
				
				//자료실 글쓰기 후 DB에 insert
				@RequestMapping("dataroom_insert")
				public String Gw_Datainsert(String data_title, String data_content, int user_idx, 
						MultipartHttpServletRequest mrequest) throws IOException {
					
					//파일 이름 가져오기
					MultipartFile file = mrequest.getFile("data_up");
					String data_up = file.getOriginalFilename();
					
					System.out.println(file.getName());
					System.out.println(file.getSize());
					System.out.println(data_up);
					byte[] data = file.getBytes();
					System.out.println("실제 내용" + data);
					
					file_upload(mrequest);
					//파일 메소드 빼둔 거
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("user_idx", user_idx);
					map.put("data_title", data_title);
					map.put("data_up", data_up);
					map.put("data_content", data_content);
					map.put("data_uppath", data_uppath);
					
					ddao.dataroom_insert(map);
					
					return "redirect:/Gw_Datapage";
					
				}
				
				//파일 다운로드 컨트롤러 부분
				@RequestMapping("dataroom_filedownload")
				public void dataroom_filedownload(@RequestParam Map<String, String> map, HttpServletResponse response, HttpServletRequest request ) {
					
					String path = map.get("data_uppath");
					String filename = map.get("data_up");
					
					String root_path = context.getRealPath("/");
					
					File file = new File(root_path+path+filename);
					
					FileInputStream fileInputStream = null;
				    ServletOutputStream servletOutputStream = null;
				 
				    try{
				        String downName = null;
				        String browser = request.getHeader("User-Agent");
				        //파일 인코딩
				        if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")){//브라우저 확인 파일명 encode  
				            downName = URLEncoder.encode(filename,"UTF-8").replaceAll("\\+", "%20");
				        }else{
				            downName = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
				        }
				        
				        response.setHeader("Content-Disposition","attachment;filename=\"" + downName+"\"");             
				        response.setContentType("application/octer-stream");
				        response.setHeader("Content-Transfer-Encoding", "binary;");
				 
				        fileInputStream = new FileInputStream(file);
				        servletOutputStream = response.getOutputStream();
				 
				        byte b [] = new byte[1024];
				        int data = 0;
				 
				        while((data=(fileInputStream.read(b, 0, b.length))) != -1){
				            servletOutputStream.write(b, 0, data);
				        }
				 
				        servletOutputStream.flush();//출력
				        
				    }catch (Exception e) {
				        e.printStackTrace();
				    }finally{
				        if(servletOutputStream!=null){
				            try{
				                servletOutputStream.close();
				            }catch (IOException e){
				                e.printStackTrace();
				            }
				        }
				        if(fileInputStream!=null){
				            try{
				                fileInputStream.close();
				            }catch (IOException e){
				                e.printStackTrace();
				            }
				        }
				    }
				
				}
				
				//자료실 삭제 서버에 저장된 파일도 함께 삭제됨
				@RequestMapping("Gw_Datadelete")
				public String Gw_Datadelete(int data_idx) {
					System.out.println(data_idx);
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					String root_path = context.getRealPath("/");
					
					if(ddao.dataroom_download(data_idx) == null ) {
						System.out.println("첨부된 파일이 없습니다");
						ddao.dataroom_delete(data_idx);
					}else{
						map = ddao.dataroom_download(data_idx);
						String data_up = (String)map.get("data_up");
						File file = new File(root_path+data_uppath+data_up);
						if(data_up.length()==0){
							System.out.println("업로드된 파일의 길이가 제로");
							ddao.dataroom_delete(data_idx);
						}else if(file.exists()) {
							if(file.delete()) {
								System.out.println("파일 삭제 성공했습니다");
								ddao.dataroom_delete(data_idx);
							}else{
								System.out.println("파일 삭제 실패했습니다");
								
								return "redirect:/Gw_Dataread?data_idx"+data_idx;
							}
						}else{
							System.out.println("파일이 없습니다");
							ddao.dataroom_delete(data_idx);
						}
						
					}
					
					return "redirect:/Gw_Datapage";
				}
				
				//자료실 수정페이지 
				@RequestMapping("Gw_Dataupdatepage")
				public String Gw_Dataupdatepage(int data_idx, Model model) {
					model.addAttribute("dataroom_update", ddao.dataroom_read(data_idx));
					return "Gw_Dataupdate";
				}
				
				//자료실 수정 컨트롤러
				@RequestMapping("Gw_Dataupdate")
				public String Gw_Dataupdate(int data_idx, String data_title, String data_content, String existing_data, 
						MultipartHttpServletRequest mrequest ) {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					MultipartFile file = mrequest.getFile("data_up");
					String data_up = file.getOriginalFilename();
					//얘가 선언되면서 디비에 입력되기를 ''길이가 없는 빈공백형 문자열로 입력이 됨
					//null값이 아닌 ''공백으로 확인 
					String root_path = context.getRealPath("/");
					
					if(data_up=="") {
						//파일이 빈값인지 아닌지 체크를 위해 null로 작성하면 구분 안 됨 	
						//파일 수정이 없을 경우
						
						map.put("data_title", data_title);
						map.put("data_content", data_content);
						map.put("data_idx", data_idx);
						
						ddao.dataroom_update2(map);
						
					}else if(data_up!=""){
						//빈값이 아닐 때 파일 삭제 처리 후 재등록
						Map<String, Object> map2 = new HashMap<String, Object>();
						map2 = ddao.dataroom_download(data_idx);
						String ck = (String)map2.get("data_up");
						if  (existing_data.length()==0 || ck == null ) {
							//기존 파일이 없는 경우에 바로 생성 업데이트 처리
							//파일을 선택하지 않고 등록시에는 파일이 null이 아닌 빈 공백값으로이 입력이 된다 '' 
							//이부분
							//그래서 파일의 length값과 null값을 모두 체크한다 
							file_upload(mrequest);
							
							map.put("data_title", data_title);
							map.put("data_up", data_up);
							map.put("data_content", data_content);
							map.put("data_uppath", data_uppath);
							map.put("data_idx", data_idx);
							
							ddao.dataroom_update1(map);
							
						}else {
							//기존 파일이 있는 경우 지우고 재입력 
							//기존 파일의 이름을 받아와서 삭제 처리한다 
							File files = new File(root_path+data_uppath+existing_data);
							if(files.exists()) {
								if(files.delete()) {
									System.out.println("파일 삭제 성공했습니다");
								}else {
									System.out.println("파일 삭제 실패했습니다");
									
								}
							}else {
								System.out.println("파일이 없습니다");
							}
							
							file_upload(mrequest);
							
							map.put("data_title", data_title);
							map.put("data_up", data_up);
							map.put("data_content", data_content);
							map.put("data_uppath", data_uppath);
							map.put("data_idx", data_idx);
							
							ddao.dataroom_update1(map);
						
						}
						
					
					}else {
						System.out.println("수정에 실패했을 때");
					}
					
					return "redirect:/Gw_Dataread?data_idx="+data_idx;
				}
				
				//파일 등록 메소드 
				//업로드 부분은 전부 이 메소드 들어감
				public void file_upload(MultipartHttpServletRequest mrequest) {
					
					MultipartFile file = mrequest.getFile("data_up");
					String data_up = file.getOriginalFilename();
					String root_path = context.getRealPath("/");
					
					try {
						file.transferTo(new File(root_path+data_uppath+data_up));
						System.out.println(data_up+"업로드 완료");
						System.out.println("파일 insert 완료!");
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("예외 1");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("예외 2");
						data_up = null;
					}
					
				}		
		
	
		
		
		
		
		
		
}













