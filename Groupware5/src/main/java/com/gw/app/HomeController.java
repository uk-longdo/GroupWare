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
	//	//context.getRealPath澗 瀬 珍度苧 井稽(辞獄 井稽 WAR虹希 姥繕 井稽)研 
	//	//巨什滴 督析 獣什奴 井稽稽 痕発馬奄 是背 彰陥
		
	
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
		System.out.println("五昔");
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
		//角嬢紳 督析拭辞 戚硯 嗣焼鎧奄 - filename
		
		 MultipartFile file = mrequest.getFile("user_imgname");
		String user_imgname = file.getOriginalFilename();
		System.out.println(user_imgname+"戚耕走亜舛雌旋舛雌舛肢");
		
		//角嬢紳 name 亜閃神奄 - name
		String name=mrequest.getParameter("name");
		System.out.println(name+"督析誤督析誤督析誤");
		
		//角嬢紳 mrequest研 亜走壱 督析穣稽球 坦軒
		String root_path=context.getRealPath("/");
		String attach_path="resources/user_img/";
		
		//督析穣稽球 五辞球
		//file.transferTo(new File("是帖研 匂敗廃 督析誤"));
		try {
			file.transferTo(new File(root_path+attach_path+user_imgname));
			System.out.println(user_imgname+"督析 穣稽球 刃戟!");
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
	    	  
	    	  System.out.println("叔鳶");
	    	  return "Main";  
	      }   
		
	}
	
	//拭戚詮什 稽益昔 溌昔'
	@RequestMapping("main_loginchk")
	public void main_loginchk(HttpServletResponse res, String user_idx, String user_pw) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_idx", user_idx); 
		map.put("user_pw", user_pw); 
		
		System.out.println(user_idx);
		
		int chk = udao.logindo(map);
		JSONObject j = new JSONObject(); 
		
		if(chk == 0) {
			System.out.println("稽益昔 叔鳶");
			
			j.put("result", "焼戚巨 箸精 搾腔腰硲亜 堂験艦陥");			
			res.getWriter().print(j.toString());
			
			
		}else {
			System.out.println("稽失因");
			
			j.put("result", "稽益昔 失因");			
			res.getWriter().print(j.toString());

		}
		
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		
		return "Main";
	}
	//窒盗悦 
		@RequestMapping("work_user")
		public String work_user(@RequestParam(required = false, defaultValue = "1")int page, int user_idx, Model model) {
			System.out.println("窒悦 凪戚走");
			//凪戚臓坦軒
			//鯵昔 窒盗悦 溌昔 軒什闘 幻級奄
			
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
				//戚暗 識情 照 背 爽檎精 dispageNum戚 竺舛吉 凪戚走 呪幻鏑 蟹神奄 凶庚拭 伽 竺舛背 操醤 器
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
	
	//窒悦 獄動 拭戚詮什 
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
			j.put("result", "窒悦");
			response.getWriter().print(j.toString());
		}else {
			j.put("result", "戚耕 窒悦 坦軒鞠醸柔艦陥.");
			response.getWriter().print(j.toString());
		}
	}
	
	//盗悦 獄動 拭戚詮什
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
		// せせせせせ 陥び献 暗 照 股戚壱 拭戚詮什幻 隔嬢鎌革 蟹?せせせ陥献惟 更亜 琶推馬倉 ぞぞ
		//触 逢切奄 訊 盗悦 照 股走?巨
		//巨搾澗 郊荷艦暗 蒸倉? 誓誓 節幻 陥獣 溌昔背瑳惟 戚暗 走榎 砧鯵虞辞 神嫌 持奄澗 闇亜? 陥献 焼戚巨稽 背左倉 11 1234
		if(work_inck > 0) {
			if(work_offck == 0) {
				j.put("result", "盗悦");
				response.getWriter().print(j.toString());
			}else {
				j.put("result", "戚耕 盗悦 坦軒鞠醸柔艦陥.");
				response.getWriter().print(j.toString());
			}
			
		}else if(work_inck==0){
			j.put("result", "窒悦 去系戚 鞠走 省紹柔艦陥. 窒悦 溌昔採斗 刃戟背 爽淑獣神.");
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
		
		//去重憎馬壱 赤醸革;; せせせ
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
	

	
	
	
	
	
	//閤精五析敗
	@RequestMapping("Email_list")
	public String Email_list(int email_re, Model model ,@RequestParam(required = false, defaultValue = "1")int page ) {
		System.out.println("戚五析軒 閤精畷走"+email_re);
		
		
		int pageNum = 3;
		
		int pageStart = (page-1) * pageNum;
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("email_re", email_re);
		map.put("pageStart", pageStart);
		map.put("pageNum", pageNum);
		
		int totalCnt = edao.Email_list_a(email_re);
		System.out.println("戚五析re"+ edao.Email_list_a(email_re));
		
		int temp_EndPage = (int) (Math.ceil(totalCnt / (double) pageNum)); // 原走厳 凪戚走
		
		int dispageNum = 10; // 惟獣毒 鉢檎拭 廃腰拭 左食霜 凪戚走税 鯵呪
		int end_Page =  (int) (Math.ceil(page / (double) dispageNum) * dispageNum);
		int startPage =  (end_Page - dispageNum) + 1;
		//獣拙 凪戚走 
		
		System.out.println(startPage);
		
		boolean prev = startPage == 1 ? false : true;
		//薄仙 左食爽澗 dispageNum税 獣拙 凪戚走亜 1戚劃 
		boolean next = end_Page * pageNum >= totalCnt ? false : true;
		//endPage * perpageNum 塘纏左陥 滴暗蟹 旭劃
		if(end_Page > temp_EndPage) {
			end_Page = temp_EndPage;
			//戚暗 識情 照 背 爽檎精 dispageNum戚 竺舛吉 凪戚走 呪幻鏑 蟹神奄 凶庚拭 伽 竺舛背 操醤 器
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
	
	
	///五析肢薦
	@RequestMapping("email_delete")
	public String email_delete(Model model, String eamil_idxs, String email_re) {
		//珍闘継君拭辞 益企稽 閤焼人辞 
		System.out.println("戚五析 焼戚巨植什"+email_re);
		System.out.println(eamil_idxs);
		if(eamil_idxs.contains(",")) {
			if (eamil_idxs.contains(",")) {
				String[] email_idx;
				email_idx = eamil_idxs.split(",");
				
				for(int i=0; i<email_idx.length; i++) {
					System.out.println("鋼差"+email_idx[i]);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("email_idx", Integer.parseInt(email_idx[i]));
					edao.emaildelete(map);	 	
					System.out.println(eamil_idxs);
					System.out.println(email_idx);
					}
			 }
		}else {
			//食奄 燭什拭亀 益撹 葵聖 痕莫背辞 隔聖 呪 赤惟 幻級嬢黍暗
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email_idx", Integer.parseInt(eamil_idxs));
			edao.emaildelete(map);
		}
		 //股戚檎 神嫌ば 蟹亀虞姥推 奄含形坐
		
		return "redirect:/Email_list?email_re="+email_re;
		//軒陥戚刑闘稽 左馨 凶 眼焼辞 左蛙
	}
	
	//軒球凪戚走拭辞 五析肢薦
	@RequestMapping("Email_delete")
	public String Email_delete(int email_idx, String email_re) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email_idx", email_idx);
		System.out.println("戚五析 焼戚巨植什"+email_re);
		System.out.println("戚五析 腰硲"+email_idx);
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
	//石精獣娃 五社球

	
	
	//左浬畷走敗
	@RequestMapping("Email_read_sent")
	public String Email_read_sent(Model model,int email_idx) {
		
		model.addAttribute("email_re", edao.email_read(email_idx));
		
		return "Email_read_sent";
	}
	
	
	
	
	@RequestMapping("Email_insert")
	public String Email_insert() {
		
		return "Email_insert";
	}
	
	
	
	//左浬五析敗
		@RequestMapping("Email_SentLetter")
		public String Email_SentLetter(int user_idx, Model model,  @RequestParam(required = false, defaultValue = "1")int page) {
			System.out.println(user_idx);
		
			
			int pageNum = 3;
			
			int pageStart = (page-1) * pageNum;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_idx", user_idx);
			map.put("pageStart", pageStart);
			map.put("pageNum", pageNum);
			 
			System.out.println("左食霜鯵呪"+pageNum);
			
			System.out.println("朝�W闘 姐呪 政煽焼戚巨="+edao.Email_list_page(user_idx));
			
			int totalCnt = edao.Email_list_page(user_idx);
			int temp_EndPage = (int) (Math.ceil(totalCnt / (double) pageNum));
			
			System.out.println("朝錘闘"+totalCnt);
			int dispageNum = 10; // 惟獣毒 鉢檎拭 廃腰拭 左食霜 凪戚走税 鯵呪
			int end_Page =  (int) (Math.ceil(page / (double) dispageNum) * dispageNum);
			int startPage =  (end_Page - dispageNum) + 1;
			
			
			boolean prev = startPage == 1 ? false : true;
			//薄仙 左食爽澗 dispageNum税 獣拙 凪戚走亜 1戚劃 
			boolean next = end_Page * pageNum >= totalCnt ? false : true;
			//endPage * perpageNum 塘纏左陥 滴暗蟹 旭劃
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
		
		
		//左浬五析敗 軒球拭辞 肢薦
		@RequestMapping("Email_delete_sent")
		public String Email_delete_sent(int email_idx,String user_idx) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email_idx", email_idx);
			System.out.println("user 腰硲"+user_idx);
			System.out.println("戚五析 腰硲"+email_idx);
			edao.email_read_delete(map);
			return "redirect:/Email_SentLetter?user_idx="+user_idx;
		}
		
		
		
		//左浬五戚敗 端滴酵什 肢薦
		@RequestMapping("email_delete_sent_read")
		public String email_delete_sent_read(String user_idx,Model model, String eamil_idxs) {
			//珍闘継君拭辞 益企稽 閤焼人辞 
			System.out.println("戚五析 焼戚巨植什"+user_idx);
			System.out.println(eamil_idxs);
			if(eamil_idxs.contains(",")) {
				if (eamil_idxs.contains(",")) {
					String[] email_idx;
					email_idx = eamil_idxs.split(",");
					
					for(int i=0; i<email_idx.length; i++) {
						System.out.println("鋼差"+email_idx[i]);
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("email_idx", Integer.parseInt(email_idx[i]));
						edao.emaildelete(map);	 	
						System.out.println(eamil_idxs);
						System.out.println(email_idx);
						}
				 }
			}else {
				//食奄 燭什拭亀 益撹 葵聖 痕莫背辞 隔聖 呪 赤惟 幻級嬢黍暗
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("email_idx", Integer.parseInt(eamil_idxs));
				edao.emaildelete(map);
			}
			
			return "redirect:/Email_SentLetter?user_idx="+user_idx;
		}
		
		
		
		//五析 床奄
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
		
		
		
		
		
		///////惟獣毒////
		
		//因走紫牌 
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
				//戚暗 識情 照 背 爽檎精 dispageNum戚 竺舛吉 凪戚走 呪幻鏑 蟹神奄 凶庚拭 伽 竺舛背 操醤 器
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
		
		
		//因走紫牌 越床奄
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
		
		//因走紫牌 呪舛凪戚走
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
		
		//因走紫牌 肢薦 
		@RequestMapping("Gw_Noticedelet")
		public String Gw_Noticedelet(int notice_idx) {
			ndao.Notice_delete(notice_idx);
			return "redirect:/Gw_Noticelist";
		}


		///斥誤 惟獣毒
		@RequestMapping("Gw_Boardlist")
		public String Gw_Boardlist(Model model,@RequestParam(required = false, defaultValue = "1")int page ) {
			
			int pageNum = 3; //左食霜 鯵呪
			int pageStart = (page-1) * pageNum;
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("pageStart", pageStart);
			map.put("pageNum", pageNum);
			
			int totalCount = bdao.board_page();  //穿端 惟獣越 朝錘闘
			
			System.out.println("凪戚走 朝錘闘"+bdao.board_page());
			int tempEndPage = (int) (Math.ceil(totalCount / (double) pageNum)); //食奄 霜庚
			int dispageNum = 10; //[1],[2] ... [10]腰猿走 左績
			int end_Page =  (int) (Math.ceil(page / (double) dispageNum) * dispageNum); //食奄 霜庚
			int startPage =  (end_Page - dispageNum) + 1;  //什展闘 凪戚走人 凪戚走 什展闘税 託戚繊?
			
			boolean prev = startPage == 1 ? false : true;
			boolean next = end_Page * pageNum >= totalCount ? false : true;
			if(end_Page > tempEndPage) {
				end_Page = tempEndPage;
				//戚暗 識情 照 背 爽檎精 dispageNum戚 竺舛吉 凪戚走 呪幻鏑 蟹神奄 凶庚拭 伽 竺舛背 操醤 器
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
		
		/////////奇越
		@RequestMapping("comm")
		public String comm(int user_idx, int board_idx, String comm_cotent, Model model) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_idx", user_idx);
			map.put("board_idx", board_idx);
			map.put("comm_cotent", comm_cotent);
			
			
			bcdao.comm_insert(map);
		
			return "redirect:/Gw_board_read?board_idx="+board_idx;
		}
		
		
		//切戟叔 鯉系
				//*凪戚臓 坦軒馬奄
				@RequestMapping("Gw_Datapage")
				public String Gw_Datapage(@RequestParam(required = false, defaultValue = "1")int page,
						 Model model) {
					
					//凪戚臓 淫恵吉 爽汐精 食奄拭 嬢走娃馬檎 陥 含焼久鞍,い?
					//革食1! 左壱 箸獣 戚背 照 亜澗 暗 責生檎 弘嬢左檎 器 奄常馬壱 赤聖 凶 弘嬢左澗 惟 慨陥 せせせ せせ匙献 獣析鎧拭 巷君瑳惟推 煽暗 切戟叔幻 股食兜精暗倉?
					//ししし 切戟叔幻 股食�x嬢 五社球稽 皐形陥亜 姥歳拝 凶 じい 舛重蒸聖 依 旭焼辞 餌虐!! 
					
					
					// page = 左食匝 凪戚走 腰硲戚壱 置段 叔楳吃 凶拭澗 巨虹闘葵戚 1稽 竺舛吉陥
					//required 閤焼紳 葵戚 蒸嬢亀 神嫌亜 持奄走 省亀系 馬澗 依戚悟 痕呪亜 蒸聖 凶澗 奄沙葵 1聖 隔嬢層陥
					int  perpageNum = 2;
					//凪戚走雁 左食匝 惟獣越税 鯵呪 
					
					//sql姥庚 limit拭 獣拙 是帖 走舛聖 廃陥.
					// 森研 級嬢 10鯵梢 窒径馬澗 井酔 3凪戚走税 page亜 1戚檎 0戚 鞠嬢醤 坦製採斗 10鯵梢 蟹紳陥
					//原戚郊銅什 繕噺 汀軒税 #{pageStart}拭 穿含吉陥
					int pageStart = (page-1) * perpageNum;
					
					//SELECT * FROM gw_data_room 
					//join gw_user using(user_idx) WHERE data_idx > 0 ORDER BY data_idx DESC, data_date DESC 
					//LIMIT #{pageStart}, #{perpageNum}
					
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("pageStart", pageStart);
					map.put("perpageNum", perpageNum);
					
					int totalCount = ddao.dataroom_paging();  //穿端 汽戚斗税 鯵呪
					int tempEndPage = (int) (Math.ceil(totalCount / (double) perpageNum)); // 原走厳 凪戚走 //食奄 霜庚 馬奄 
					//是拭 猿走亜 凪戚走 照拭 惟獣越 快嬢辞 左食爽澗 依 
					
					int dispageNum = 10; // 惟獣毒 鉢檎拭 廃腰拭 左食霜 凪戚走税 鯵呪
					int endPage =  (int) (Math.ceil(page / (double) dispageNum) * dispageNum);
					//原走厳 凪戚走
					// 森) [1][2][3][4][5][6]猿走 赤生檎 [5]猿走幻 左食層陥
					int startPage =  (endPage - dispageNum) + 1;
					//獣拙 凪戚走 
					System.out.println(startPage);
					boolean prev = startPage == 1 ? false : true;
					//薄仙 左食爽澗 dispageNum税 獣拙 凪戚走亜 1戚劃 
					boolean next = endPage * perpageNum >= totalCount ? false : true;
					//endPage * perpageNum 塘纏左陥 滴暗蟹 旭劃
					if(endPage > tempEndPage) {
						endPage = tempEndPage;
						//戚暗 識情 照 背 爽檎精 dispageNum戚 竺舛吉 凪戚走 呪幻鏑 蟹神奄 凶庚拭 伽 竺舛背 操醤 器
					}
					
					model.addAttribute("dataroom",ddao.dataroom_list(map));
					model.addAttribute("tempEndPage", tempEndPage);
					model.addAttribute("startPage", startPage);
					model.addAttribute("endPage", endPage);
					model.addAttribute("prev", prev);
					model.addAttribute("next", next);
					model.addAttribute("pageck", page);
					//薄仙凪戚走拭 適遣鞠走 省亀系 繕闇庚聖 杏凶 琶推
					//耕庁 闇亜.. 更亜 戚係惟 弦焼? 紫寓級戚 戚掘辞 焼森 適掘什研 魚稽 皐姥蟹..
					//五社球稽 皐辞 舛税拝 呪 赤澗走 溌昔馬奄
					
					return "Gw_Datapage";
				}
				
				
				//切戟叔 惟獣越 石奄
				@RequestMapping("Gw_Dataread")
				public String Gw_Dataread(int data_idx,int page, Model model) {
					
					model.addAttribute("dataroom_read", ddao.dataroom_read(data_idx));
					model.addAttribute("page", page);
					
					return "Gw_Dataread";
				}
				
				//切戟叔 越床奄 
				@RequestMapping("Gw_Datainsertpage")
				public String Gw_Datainsertpage() {
					
					return "Gw_Datainsert";
					
				}
				
				//切戟叔 越床奄 板 DB拭 insert
				@RequestMapping("dataroom_insert")
				public String Gw_Datainsert(String data_title, String data_content, int user_idx, 
						MultipartHttpServletRequest mrequest) throws IOException {
					
					//督析 戚硯 亜閃神奄
					MultipartFile file = mrequest.getFile("data_up");
					String data_up = file.getOriginalFilename();
					
					System.out.println(file.getName());
					System.out.println(file.getSize());
					System.out.println(data_up);
					byte[] data = file.getBytes();
					System.out.println("叔薦 鎧遂" + data);
					
					file_upload(mrequest);
					//督析 五社球 皐黍 暗
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("user_idx", user_idx);
					map.put("data_title", data_title);
					map.put("data_up", data_up);
					map.put("data_content", data_content);
					map.put("data_uppath", data_uppath);
					
					ddao.dataroom_insert(map);
					
					return "redirect:/Gw_Datapage";
					
				}
				
				//督析 陥錘稽球 珍闘継君 採歳
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
				        //督析 昔坪漁
				        if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")){//崎虞酔煽 溌昔 督析誤 encode  
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
				 
				        servletOutputStream.flush();//窒径
				        
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
				
				//切戟叔 肢薦 辞獄拭 煽舌吉 督析亀 敗臆 肢薦喫
				@RequestMapping("Gw_Datadelete")
				public String Gw_Datadelete(int data_idx) {
					System.out.println(data_idx);
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					String root_path = context.getRealPath("/");
					
					if(ddao.dataroom_download(data_idx) == null ) {
						System.out.println("歎採吉 督析戚 蒸柔艦陥");
						ddao.dataroom_delete(data_idx);
					}else{
						map = ddao.dataroom_download(data_idx);
						String data_up = (String)map.get("data_up");
						File file = new File(root_path+data_uppath+data_up);
						if(data_up.length()==0){
							System.out.println("穣稽球吉 督析税 掩戚亜 薦稽");
							ddao.dataroom_delete(data_idx);
						}else if(file.exists()) {
							if(file.delete()) {
								System.out.println("督析 肢薦 失因梅柔艦陥");
								ddao.dataroom_delete(data_idx);
							}else{
								System.out.println("督析 肢薦 叔鳶梅柔艦陥");
								
								return "redirect:/Gw_Dataread?data_idx"+data_idx;
							}
						}else{
							System.out.println("督析戚 蒸柔艦陥");
							ddao.dataroom_delete(data_idx);
						}
						
					}
					
					return "redirect:/Gw_Datapage";
				}
				
				//切戟叔 呪舛凪戚走 
				@RequestMapping("Gw_Dataupdatepage")
				public String Gw_Dataupdatepage(int data_idx, Model model) {
					model.addAttribute("dataroom_update", ddao.dataroom_read(data_idx));
					return "Gw_Dataupdate";
				}
				
				//切戟叔 呪舛 珍闘継君
				@RequestMapping("Gw_Dataupdate")
				public String Gw_Dataupdate(int data_idx, String data_title, String data_content, String existing_data, 
						MultipartHttpServletRequest mrequest ) {
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					MultipartFile file = mrequest.getFile("data_up");
					String data_up = file.getOriginalFilename();
					//剰亜 識情鞠檎辞 巨搾拭 脊径鞠奄研 ''掩戚亜 蒸澗 朔因拷莫 庚切伸稽 脊径戚 喫
					//null葵戚 焼観 ''因拷生稽 溌昔 
					String root_path = context.getRealPath("/");
					
					if(data_up=="") {
						//督析戚 朔葵昔走 焼観走 端滴研 是背 null稽 拙失馬檎 姥歳 照 喫 	
						//督析 呪舛戚 蒸聖 井酔
						
						map.put("data_title", data_title);
						map.put("data_content", data_content);
						map.put("data_idx", data_idx);
						
						ddao.dataroom_update2(map);
						
					}else if(data_up!=""){
						//朔葵戚 焼諌 凶 督析 肢薦 坦軒 板 仙去系
						Map<String, Object> map2 = new HashMap<String, Object>();
						map2 = ddao.dataroom_download(data_idx);
						String ck = (String)map2.get("data_up");
						if  (existing_data.length()==0 || ck == null ) {
							//奄糎 督析戚 蒸澗 井酔拭 郊稽 持失 穣汽戚闘 坦軒
							//督析聖 識澱馬走 省壱 去系獣拭澗 督析戚 null戚 焼観 朔 因拷葵生稽戚 脊径戚 吉陥 '' 
							//戚採歳
							//益掘辞 督析税 length葵引 null葵聖 乞砧 端滴廃陥 
							file_upload(mrequest);
							
							map.put("data_title", data_title);
							map.put("data_up", data_up);
							map.put("data_content", data_content);
							map.put("data_uppath", data_uppath);
							map.put("data_idx", data_idx);
							
							ddao.dataroom_update1(map);
							
						}else {
							//奄糎 督析戚 赤澗 井酔 走酔壱 仙脊径 
							//奄糎 督析税 戚硯聖 閤焼人辞 肢薦 坦軒廃陥 
							File files = new File(root_path+data_uppath+existing_data);
							if(files.exists()) {
								if(files.delete()) {
									System.out.println("督析 肢薦 失因梅柔艦陥");
								}else {
									System.out.println("督析 肢薦 叔鳶梅柔艦陥");
									
								}
							}else {
								System.out.println("督析戚 蒸柔艦陥");
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
						System.out.println("呪舛拭 叔鳶梅聖 凶");
					}
					
					return "redirect:/Gw_Dataread?data_idx="+data_idx;
				}
				
				//督析 去系 五社球 
				//穣稽球 採歳精 穿採 戚 五社球 級嬢姶
				public void file_upload(MultipartHttpServletRequest mrequest) {
					
					MultipartFile file = mrequest.getFile("data_up");
					String data_up = file.getOriginalFilename();
					String root_path = context.getRealPath("/");
					
					try {
						file.transferTo(new File(root_path+data_uppath+data_up));
						System.out.println(data_up+"穣稽球 刃戟");
						System.out.println("督析 insert 刃戟!");
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("森須 1");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("森須 2");
						data_up = null;
					}
					
				}		
		
	
		
		
		
		
		
		
}













