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
	//	//context.getRealPath�� �� ������ ���(���� ��� WAR���� ���� ���)�� 
	//	//��ũ ���� �ý��� ��η� ��ȯ�ϱ� ���� ����
		
	
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
		System.out.println("����");
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
		//�Ѿ�� ���Ͽ��� �̸� �̾Ƴ��� - filename
		
		 MultipartFile file = mrequest.getFile("user_imgname");
		String user_imgname = file.getOriginalFilename();
		System.out.println(user_imgname+"�̹�������������������");
		
		//�Ѿ�� name �������� - name
		String name=mrequest.getParameter("name");
		System.out.println(name+"���ϸ����ϸ����ϸ�");
		
		//�Ѿ�� mrequest�� ������ ���Ͼ��ε� ó��
		String root_path=context.getRealPath("/");
		String attach_path="resources/user_img/";
		
		//���Ͼ��ε� �޼���
		//file.transferTo(new File("��ġ�� ������ ���ϸ�"));
		try {
			file.transferTo(new File(root_path+attach_path+user_imgname));
			System.out.println(user_imgname+"���� ���ε� �Ϸ�!");
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
	    	  
	    	  System.out.println("����");
	    	  return "Main";  
	      }   
		
	}
	
	//�������� �α��� Ȯ��'
	@RequestMapping("main_loginchk")
	public void main_loginchk(HttpServletResponse res, String user_idx, String user_pw) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_idx", user_idx); 
		map.put("user_pw", user_pw); 
		
		System.out.println(user_idx);
		
		int chk = udao.logindo(map);
		JSONObject j = new JSONObject(); 
		
		if(chk == 0) {
			System.out.println("�α��� ����");
			
			j.put("result", "���̵� Ȥ�� ��й�ȣ�� Ʋ���ϴ�");			
			res.getWriter().print(j.toString());
			
			
		}else {
			System.out.println("�μ���");
			
			j.put("result", "�α��� ����");			
			res.getWriter().print(j.toString());

		}
		
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		
		return "Main";
	}
	//����� 
		@RequestMapping("work_user")
		public String work_user(@RequestParam(required = false, defaultValue = "1")int page, int user_idx, Model model) {
			System.out.println("��� ������");
			//����¡ó��
			//���� ����� Ȯ�� ����Ʈ �����
			
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
				//�̰� ���� �� �� �ָ��� dispageNum�� ������ ������ ����ŭ ������ ������ �� ������ ��� ��
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
	
	//��� ��ư �������� 
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
			j.put("result", "���");
			response.getWriter().print(j.toString());
		}else {
			j.put("result", "�̹� ��� ó���Ǿ����ϴ�.");
			response.getWriter().print(j.toString());
		}
	}
	
	//��� ��ư ��������
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
		// ���������� �٤Ӹ� �� �� ���̰� ���������� �־���� ��?�������ٸ��� ���� �ʿ����� ����
		//�� ���ڱ� �� ��� �� ����?��
		//���� �ٲٴϰ� ����? ���� �Ḹ �ٽ� Ȯ���غ��� �̰� ���� �ΰ��� ���� ����� �ǰ�? �ٸ� ���̵�� �غ��� 11 1234
		if(work_inck > 0) {
			if(work_offck == 0) {
				j.put("result", "���");
				response.getWriter().print(j.toString());
			}else {
				j.put("result", "�̹� ��� ó���Ǿ����ϴ�.");
				response.getWriter().print(j.toString());
			}
			
		}else if(work_inck==0){
			j.put("result", "��� ����� ���� �ʾҽ��ϴ�. ��� Ȯ�κ��� �Ϸ��� �ֽʽÿ�.");
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
		
		//������ϰ� �־���;; ������
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
	

	
	
	
	
	
	//����������
	@RequestMapping("Email_list")
	public String Email_list(int email_re, Model model ,@RequestParam(required = false, defaultValue = "1")int page ) {
		System.out.println("�̸��ϸ� ��������"+email_re);
		
		
		int pageNum = 3;
		
		int pageStart = (page-1) * pageNum;
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("email_re", email_re);
		map.put("pageStart", pageStart);
		map.put("pageNum", pageNum);
		
		int totalCnt = edao.Email_list_a(email_re);
		System.out.println("�̸���re"+ edao.Email_list_a(email_re));
		
		int temp_EndPage = (int) (Math.ceil(totalCnt / (double) pageNum)); // ������ ������
		
		int dispageNum = 10; // �Խ��� ȭ�鿡 �ѹ��� ������ �������� ����
		int end_Page =  (int) (Math.ceil(page / (double) dispageNum) * dispageNum);
		int startPage =  (end_Page - dispageNum) + 1;
		//���� ������ 
		
		System.out.println(startPage);
		
		boolean prev = startPage == 1 ? false : true;
		//���� �����ִ� dispageNum�� ���� �������� 1�̳� 
		boolean next = end_Page * pageNum >= totalCnt ? false : true;
		//endPage * perpageNum ��Ż���� ũ�ų� ����
		if(end_Page > temp_EndPage) {
			end_Page = temp_EndPage;
			//�̰� ���� �� �� �ָ��� dispageNum�� ������ ������ ����ŭ ������ ������ �� ������ ��� ��
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
	
	
	///���ϻ���
	@RequestMapping("email_delete")
	public String email_delete(Model model, String eamil_idxs, String email_re) {
		//��Ʈ�ѷ����� �״�� �޾ƿͼ� 
		System.out.println("�̸��� ���̵𿢽�"+email_re);
		System.out.println(eamil_idxs);
		if(eamil_idxs.contains(",")) {
			if (eamil_idxs.contains(",")) {
				String[] email_idx;
				email_idx = eamil_idxs.split(",");
				
				for(int i=0; i<email_idx.length; i++) {
					System.out.println("�ݺ�"+email_idx[i]);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("email_idx", Integer.parseInt(email_idx[i]));
					edao.emaildelete(map);	 	
					System.out.println(eamil_idxs);
					System.out.println(email_idx);
					}
			 }
		}else {
			//���� �������� �׳� ���� �����ؼ� ���� �� �ְ� �����а�
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email_idx", Integer.parseInt(eamil_idxs));
			edao.emaildelete(map);
		}
		 //���̸� ������ �����󱸿� ��޷���
		
		return "redirect:/Email_list?email_re="+email_re;
		//�����̷�Ʈ�� ���� �� ��Ƽ� ����
	}
	
	//�������������� ���ϻ���
	@RequestMapping("Email_delete")
	public String Email_delete(int email_idx, String email_re) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email_idx", email_idx);
		System.out.println("�̸��� ���̵𿢽�"+email_re);
		System.out.println("�̸��� ��ȣ"+email_idx);
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
	//�����ð� �޼ҵ�

	
	
	//����������
	@RequestMapping("Email_read_sent")
	public String Email_read_sent(Model model,int email_idx) {
		
		model.addAttribute("email_re", edao.email_read(email_idx));
		
		return "Email_read_sent";
	}
	
	
	
	
	@RequestMapping("Email_insert")
	public String Email_insert() {
		
		return "Email_insert";
	}
	
	
	
	//����������
		@RequestMapping("Email_SentLetter")
		public String Email_SentLetter(int user_idx, Model model,  @RequestParam(required = false, defaultValue = "1")int page) {
			System.out.println(user_idx);
		
			
			int pageNum = 3;
			
			int pageStart = (page-1) * pageNum;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_idx", user_idx);
			map.put("pageStart", pageStart);
			map.put("pageNum", pageNum);
			 
			System.out.println("����������"+pageNum);
			
			System.out.println("ī�WƮ ���� �������̵�="+edao.Email_list_page(user_idx));
			
			int totalCnt = edao.Email_list_page(user_idx);
			int temp_EndPage = (int) (Math.ceil(totalCnt / (double) pageNum));
			
			System.out.println("ī��Ʈ"+totalCnt);
			int dispageNum = 10; // �Խ��� ȭ�鿡 �ѹ��� ������ �������� ����
			int end_Page =  (int) (Math.ceil(page / (double) dispageNum) * dispageNum);
			int startPage =  (end_Page - dispageNum) + 1;
			
			
			boolean prev = startPage == 1 ? false : true;
			//���� �����ִ� dispageNum�� ���� �������� 1�̳� 
			boolean next = end_Page * pageNum >= totalCnt ? false : true;
			//endPage * perpageNum ��Ż���� ũ�ų� ����
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
		
		
		//���������� ���忡�� ����
		@RequestMapping("Email_delete_sent")
		public String Email_delete_sent(int email_idx,String user_idx) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email_idx", email_idx);
			System.out.println("user ��ȣ"+user_idx);
			System.out.println("�̸��� ��ȣ"+email_idx);
			edao.email_read_delete(map);
			return "redirect:/Email_SentLetter?user_idx="+user_idx;
		}
		
		
		
		//���������� üũ�ڽ� ����
		@RequestMapping("email_delete_sent_read")
		public String email_delete_sent_read(String user_idx,Model model, String eamil_idxs) {
			//��Ʈ�ѷ����� �״�� �޾ƿͼ� 
			System.out.println("�̸��� ���̵𿢽�"+user_idx);
			System.out.println(eamil_idxs);
			if(eamil_idxs.contains(",")) {
				if (eamil_idxs.contains(",")) {
					String[] email_idx;
					email_idx = eamil_idxs.split(",");
					
					for(int i=0; i<email_idx.length; i++) {
						System.out.println("�ݺ�"+email_idx[i]);
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("email_idx", Integer.parseInt(email_idx[i]));
						edao.emaildelete(map);	 	
						System.out.println(eamil_idxs);
						System.out.println(email_idx);
						}
				 }
			}else {
				//���� �������� �׳� ���� �����ؼ� ���� �� �ְ� �����а�
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("email_idx", Integer.parseInt(eamil_idxs));
				edao.emaildelete(map);
			}
			
			return "redirect:/Email_SentLetter?user_idx="+user_idx;
		}
		
		
		
		//���� ����
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
		
		
		
		
		
		///////�Խ���////
		
		//�������� 
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
				//�̰� ���� �� �� �ָ��� dispageNum�� ������ ������ ����ŭ ������ ������ �� ������ ��� ��
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
		
		
		//�������� �۾���
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
		
		//�������� ����������
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
		
		//�������� ���� 
		@RequestMapping("Gw_Noticedelet")
		public String Gw_Noticedelet(int notice_idx) {
			ndao.Notice_delete(notice_idx);
			return "redirect:/Gw_Noticelist";
		}


		///�͸� �Խ���
		@RequestMapping("Gw_Boardlist")
		public String Gw_Boardlist(Model model,@RequestParam(required = false, defaultValue = "1")int page ) {
			
			int pageNum = 3; //������ ����
			int pageStart = (page-1) * pageNum;
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("pageStart", pageStart);
			map.put("pageNum", pageNum);
			
			int totalCount = bdao.board_page();  //��ü �Խñ� ī��Ʈ
			
			System.out.println("������ ī��Ʈ"+bdao.board_page());
			int tempEndPage = (int) (Math.ceil(totalCount / (double) pageNum)); //���� ����
			int dispageNum = 10; //[1],[2] ... [10]������ ����
			int end_Page =  (int) (Math.ceil(page / (double) dispageNum) * dispageNum); //���� ����
			int startPage =  (end_Page - dispageNum) + 1;  //��ŸƮ �������� ������ ��ŸƮ�� ������?
			
			boolean prev = startPage == 1 ? false : true;
			boolean next = end_Page * pageNum >= totalCount ? false : true;
			if(end_Page > tempEndPage) {
				end_Page = tempEndPage;
				//�̰� ���� �� �� �ָ��� dispageNum�� ������ ������ ����ŭ ������ ������ �� ������ ��� ��
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
		
		/////////���
		@RequestMapping("comm")
		public String comm(int user_idx, int board_idx, String comm_cotent, Model model) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_idx", user_idx);
			map.put("board_idx", board_idx);
			map.put("comm_cotent", comm_cotent);
			
			
			bcdao.comm_insert(map);
		
			return "redirect:/Gw_board_read?board_idx="+board_idx;
		}
		
		
		//�ڷ�� ���
				//*����¡ ó���ϱ�
				@RequestMapping("Gw_Datapage")
				public String Gw_Datapage(@RequestParam(required = false, defaultValue = "1")int page,
						 Model model) {
					
					//����¡ ���õ� �ּ��� ���⿡ �������ϸ� �� �޾Ƶװ�,��?
					//�׿�1! ���� Ȥ�� ���� �� ���� �� ������ ����� �� ����ϰ� ���� �� ����� �� ���� ������ �������� ���ϳ��� �������Կ� ���� �ڷ�Ǹ� �Կ���������?
					//������ �ڷ�Ǹ� �Կ��x�� �޼ҵ�� �����ٰ� ������ �� ���� ���ž��� �� ���Ƽ� �µ�!! 
					
					
					// page = ������ ������ ��ȣ�̰� ���� ����� ������ ����Ʈ���� 1�� �����ȴ�
					//required �޾ƿ� ���� ��� ������ ������ �ʵ��� �ϴ� ���̸� ������ ���� ���� �⺻�� 1�� �־��ش�
					int  perpageNum = 2;
					//�������� ������ �Խñ��� ���� 
					
					//sql���� limit�� ���� ��ġ ������ �Ѵ�.
					// ���� ��� 10���� ����ϴ� ��� 3�������� page�� 1�̸� 0�� �Ǿ�� ó������ 10���� ���´�
					//���̹�Ƽ�� ��ȸ ������ #{pageStart}�� ���޵ȴ�
					int pageStart = (page-1) * perpageNum;
					
					//SELECT * FROM gw_data_room 
					//join gw_user using(user_idx) WHERE data_idx > 0 ORDER BY data_idx DESC, data_date DESC 
					//LIMIT #{pageStart}, #{perpageNum}
					
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("pageStart", pageStart);
					map.put("perpageNum", perpageNum);
					
					int totalCount = ddao.dataroom_paging();  //��ü �������� ����
					int tempEndPage = (int) (Math.ceil(totalCount / (double) perpageNum)); // ������ ������ //���� ���� �ϱ� 
					//���� ������ ������ �ȿ� �Խñ� ��� �����ִ� �� 
					
					int dispageNum = 10; // �Խ��� ȭ�鿡 �ѹ��� ������ �������� ����
					int endPage =  (int) (Math.ceil(page / (double) dispageNum) * dispageNum);
					//������ ������
					// ��) [1][2][3][4][5][6]���� ������ [5]������ �����ش�
					int startPage =  (endPage - dispageNum) + 1;
					//���� ������ 
					System.out.println(startPage);
					boolean prev = startPage == 1 ? false : true;
					//���� �����ִ� dispageNum�� ���� �������� 1�̳� 
					boolean next = endPage * perpageNum >= totalCount ? false : true;
					//endPage * perpageNum ��Ż���� ũ�ų� ����
					if(endPage > tempEndPage) {
						endPage = tempEndPage;
						//�̰� ���� �� �� �ָ��� dispageNum�� ������ ������ ����ŭ ������ ������ �� ������ ��� ��
					}
					
					model.addAttribute("dataroom",ddao.dataroom_list(map));
					model.addAttribute("tempEndPage", tempEndPage);
					model.addAttribute("startPage", startPage);
					model.addAttribute("endPage", endPage);
					model.addAttribute("prev", prev);
					model.addAttribute("next", next);
					model.addAttribute("pageck", page);
					//������������ Ŭ������ �ʵ��� ���ǹ��� �ɶ� �ʿ�
					//��ģ �ǰ�.. ���� �̷��� ����? ������� �̷��� �ƿ� Ŭ������ ���� ������..
					//�޼ҵ�� ���� ������ �� �ִ��� Ȯ���ϱ�
					
					return "Gw_Datapage";
				}
				
				
				//�ڷ�� �Խñ� �б�
				@RequestMapping("Gw_Dataread")
				public String Gw_Dataread(int data_idx,int page, Model model) {
					
					model.addAttribute("dataroom_read", ddao.dataroom_read(data_idx));
					model.addAttribute("page", page);
					
					return "Gw_Dataread";
				}
				
				//�ڷ�� �۾��� 
				@RequestMapping("Gw_Datainsertpage")
				public String Gw_Datainsertpage() {
					
					return "Gw_Datainsert";
					
				}
				
				//�ڷ�� �۾��� �� DB�� insert
				@RequestMapping("dataroom_insert")
				public String Gw_Datainsert(String data_title, String data_content, int user_idx, 
						MultipartHttpServletRequest mrequest) throws IOException {
					
					//���� �̸� ��������
					MultipartFile file = mrequest.getFile("data_up");
					String data_up = file.getOriginalFilename();
					
					System.out.println(file.getName());
					System.out.println(file.getSize());
					System.out.println(data_up);
					byte[] data = file.getBytes();
					System.out.println("���� ����" + data);
					
					file_upload(mrequest);
					//���� �޼ҵ� ���� ��
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("user_idx", user_idx);
					map.put("data_title", data_title);
					map.put("data_up", data_up);
					map.put("data_content", data_content);
					map.put("data_uppath", data_uppath);
					
					ddao.dataroom_insert(map);
					
					return "redirect:/Gw_Datapage";
					
				}
				
				//���� �ٿ�ε� ��Ʈ�ѷ� �κ�
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
				        //���� ���ڵ�
				        if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")){//������ Ȯ�� ���ϸ� encode  
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
				 
				        servletOutputStream.flush();//���
				        
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
				
				//�ڷ�� ���� ������ ����� ���ϵ� �Բ� ������
				@RequestMapping("Gw_Datadelete")
				public String Gw_Datadelete(int data_idx) {
					System.out.println(data_idx);
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					String root_path = context.getRealPath("/");
					
					if(ddao.dataroom_download(data_idx) == null ) {
						System.out.println("÷�ε� ������ �����ϴ�");
						ddao.dataroom_delete(data_idx);
					}else{
						map = ddao.dataroom_download(data_idx);
						String data_up = (String)map.get("data_up");
						File file = new File(root_path+data_uppath+data_up);
						if(data_up.length()==0){
							System.out.println("���ε�� ������ ���̰� ����");
							ddao.dataroom_delete(data_idx);
						}else if(file.exists()) {
							if(file.delete()) {
								System.out.println("���� ���� �����߽��ϴ�");
								ddao.dataroom_delete(data_idx);
							}else{
								System.out.println("���� ���� �����߽��ϴ�");
								
								return "redirect:/Gw_Dataread?data_idx"+data_idx;
							}
						}else{
							System.out.println("������ �����ϴ�");
							ddao.dataroom_delete(data_idx);
						}
						
					}
					
					return "redirect:/Gw_Datapage";
				}
				
				//�ڷ�� ���������� 
				@RequestMapping("Gw_Dataupdatepage")
				public String Gw_Dataupdatepage(int data_idx, Model model) {
					model.addAttribute("dataroom_update", ddao.dataroom_read(data_idx));
					return "Gw_Dataupdate";
				}
				
				//�ڷ�� ���� ��Ʈ�ѷ�
				@RequestMapping("Gw_Dataupdate")
				public String Gw_Dataupdate(int data_idx, String data_title, String data_content, String existing_data, 
						MultipartHttpServletRequest mrequest ) {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					MultipartFile file = mrequest.getFile("data_up");
					String data_up = file.getOriginalFilename();
					//�갡 ����Ǹ鼭 ��� �ԷµǱ⸦ ''���̰� ���� ������� ���ڿ��� �Է��� ��
					//null���� �ƴ� ''�������� Ȯ�� 
					String root_path = context.getRealPath("/");
					
					if(data_up=="") {
						//������ ������ �ƴ��� üũ�� ���� null�� �ۼ��ϸ� ���� �� �� 	
						//���� ������ ���� ���
						
						map.put("data_title", data_title);
						map.put("data_content", data_content);
						map.put("data_idx", data_idx);
						
						ddao.dataroom_update2(map);
						
					}else if(data_up!=""){
						//���� �ƴ� �� ���� ���� ó�� �� ����
						Map<String, Object> map2 = new HashMap<String, Object>();
						map2 = ddao.dataroom_download(data_idx);
						String ck = (String)map2.get("data_up");
						if  (existing_data.length()==0 || ck == null ) {
							//���� ������ ���� ��쿡 �ٷ� ���� ������Ʈ ó��
							//������ �������� �ʰ� ��Ͻÿ��� ������ null�� �ƴ� �� ���鰪������ �Է��� �ȴ� '' 
							//�̺κ�
							//�׷��� ������ length���� null���� ��� üũ�Ѵ� 
							file_upload(mrequest);
							
							map.put("data_title", data_title);
							map.put("data_up", data_up);
							map.put("data_content", data_content);
							map.put("data_uppath", data_uppath);
							map.put("data_idx", data_idx);
							
							ddao.dataroom_update1(map);
							
						}else {
							//���� ������ �ִ� ��� ����� ���Է� 
							//���� ������ �̸��� �޾ƿͼ� ���� ó���Ѵ� 
							File files = new File(root_path+data_uppath+existing_data);
							if(files.exists()) {
								if(files.delete()) {
									System.out.println("���� ���� �����߽��ϴ�");
								}else {
									System.out.println("���� ���� �����߽��ϴ�");
									
								}
							}else {
								System.out.println("������ �����ϴ�");
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
						System.out.println("������ �������� ��");
					}
					
					return "redirect:/Gw_Dataread?data_idx="+data_idx;
				}
				
				//���� ��� �޼ҵ� 
				//���ε� �κ��� ���� �� �޼ҵ� ��
				public void file_upload(MultipartHttpServletRequest mrequest) {
					
					MultipartFile file = mrequest.getFile("data_up");
					String data_up = file.getOriginalFilename();
					String root_path = context.getRealPath("/");
					
					try {
						file.transferTo(new File(root_path+data_uppath+data_up));
						System.out.println(data_up+"���ε� �Ϸ�");
						System.out.println("���� insert �Ϸ�!");
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("���� 1");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("���� 2");
						data_up = null;
					}
					
				}		
		
	
		
		
		
		
		
		
}













