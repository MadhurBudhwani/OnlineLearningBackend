package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.COURSE_INFO;
import com.example.demo.model.USER_INFO;
import com.example.demo.model.ENROLLMENT;
import com.example.demo.model.QUIZ_DATA;

import com.example.demo.repo.userInfo;
import com.example.demo.repo.courseRepo;
import com.example.demo.repo.enrollmentRepo;
import com.example.demo.repo.quizRepo;

import jakarta.servlet.http.HttpSession;

@RestController("courseUser")
public class userController {

	@Autowired
	userInfo userRepo;
	
	@Autowired
	courseRepo courseRepo;
	
	@Autowired
	enrollmentRepo enrollRepo;
	
	@Autowired
	quizRepo quizRepo;
	
	@RequestMapping("/getUser")
	@CrossOrigin(origins = "http://localhost:3000")
	public String getUser() {
		Iterable<USER_INFO> users=userRepo.findAll();
		String userdata="";
		for(USER_INFO info:users) {
			userdata+=info.getUserId()+"--"+info.getUserName();
		}
		
		
		return userdata;
	}
	
	@RequestMapping("/doLogin")
	@CrossOrigin(origins = "http://localhost:3000")
	public String loginAuthenticate(@RequestParam String userName,@RequestParam String password,HttpSession session) {
		String userRole ="notUserFound";
		
		try {
		USER_INFO user=userRepo.findByUserName(userName).get(0);
		if(user!=null) {
			
			if(user.getPassword().equals(password)) {
				userRole=user.getUserRole()+"##"+user.getUserId()+"##"+user.getUserName();
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("userName", user.getUserName());
				session.setAttribute("userRole", user.getUserRole());
				
			}
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
//		HashMap<String, String > hm=new HashMap<String,String>();
//		hm.put('"'+"response"+'"','"'+userRole+'"');
		System.out.println("user--"+userRole);
		return userRole;
	}
	@RequestMapping("/doLogout")
	@CrossOrigin(origins = "http://localhost:3000")
	public String loginOut(HttpSession session) {
		String userRole ="loggedOut";
		
		
		session.invalidate();
		return userRole;
	}
	
	@RequestMapping("/signUp")
	@CrossOrigin(origins = "http://localhost:3000")
	public String signUp(USER_INFO userInfo) {
		String response ="user Name already taken please change it.";
		
		try {
			
			List<USER_INFO> userList=userRepo.findByUserName(userInfo.getUserName());
					
			if(userList.size()==0) {
				userInfo.setUserRole("user");
			USER_INFO user=userRepo.save(userInfo);
			if(user.getUserName().equals(userInfo.getUserName()))
				response="new user created with "+userInfo.getUserName()+" user name.";
			}
		}catch (Exception e) {
			e.printStackTrace();
			response ="error occured!";
		}
		System.out.println(response);
		return response;
	}
	
	@RequestMapping("/enrolling")
	@CrossOrigin(origins = "http://localhost:3000")
	public String enrolling(HttpSession session,@RequestParam String userId,@RequestParam String courseId) {
		String response ="not enreolled";
		
		try {
			if(true) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calTemp = Calendar.getInstance();

			String enrolledDate = formatter.format(calTemp.getTime());
			
			ENROLLMENT enroll=new ENROLLMENT();
			
			enroll.setCourse_id(courseId);;
			
			enroll.setUser_id(userId+"");
			enroll.setEnrollment_date(enrolledDate);
			
			enrollRepo.save(enroll);
			response="enrolled";
			}
		}catch (Exception e) {
			e.printStackTrace();
			response ="error occured!";
		}
		System.out.println(response);
		return response;
	}
	
	@RequestMapping("/getHomePageData")
	@CrossOrigin(origins = "http://localhost:3000")
	@Transactional 
	public String getHomePageData(HttpSession session) {
		HashMap<String,Object> responseMap=new HashMap<String,Object>();
		String response ='"'+"userNotLogin"+'"';
		String ar="";
		try {
			if(true) {
//				response='"'+"userLoggedIn"+'"';
				HashMap<String,ArrayList<String>> courses=new HashMap<String,ArrayList<String>>();
				Iterable<COURSE_INFO> allCourses=courseRepo.findAll();
				System.out.println(allCourses);
//				ArrayList<String> ar=new ArrayList<String>();\
				
				for(COURSE_INFO course:allCourses) {
					
//					ar+=(+course.getCourse_id()+"~~");
					ar+=(course.getTitle()+"~~");
					ar+=(course.getDescription()+"~~");
					ar+=(course.getBgimg()+"~~");
					ar+=(course.getLink()+"~~"+course.getCourse_id()+"#####");
//					System.out.println(course.getTitle());
//					courses.put(course.getCourse_id()+"", ar);
				}
				ar=ar.substring(0, ar.lastIndexOf("#####"));
//				responseMap.put('"'+"courses"+'"',"["+ ar+"]");
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			response ="error occured!";
		}
		System.out.println("user-------------------->"+session.getAttribute("userId"));
//		responseMap.put('"'+"response"+'"',response);
		return ar;
	}
	
	@RequestMapping("/getcourseById")
	@CrossOrigin(origins = "http://localhost:3000")
	@Transactional 
	public String getcourseById(HttpSession session,@RequestParam String courseId,@RequestParam String userId) {
		HashMap<String,Object> responseMap=new HashMap<String,Object>();
		String response ='"'+"userNotLogin"+'"';
//		Integer userId=(Integer) session.getAttribute("userId");
		boolean isEnrolled=false;
		try {
			if(true) {
				response='"'+"userLoggedIn"+'"';
				HashMap<String,ArrayList<String>> courses=new HashMap<String,ArrayList<String>>();
				COURSE_INFO allCourses=courseRepo.findById(courseId).orElse(null);
				ArrayList<String> ar=new ArrayList<String>();
				ar.add('"'+allCourses.getTitle()+'"');
				ar.add('"'+allCourses.getDescription()+'"');
				ar.add('"'+allCourses.getBgimg()+'"');
				ar.add('"'+allCourses.getLink()+'"');
				ar.add('"'+allCourses.getCourse_id()+'"');
				System.out.println(allCourses.getTitle());
//				courses.put(allCourses.getCourse_id()+"", ar);
				responseMap.put('"'+"courses"+'"', ar);
				
				ENROLLMENT enrollment=enrollRepo.findByCourseIdAndUserId(userId+"",courseId);
				
				if(enrollment!=null) {
					if(enrollment.getEnrollment_id()>0) {
						isEnrolled=true;
					}
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			response ="error occured!";
		}
		responseMap.put('"'+"response"+'"',response);
		responseMap.put('"'+"isEnrolled"+'"',isEnrolled);
		return responseMap.toString().replace("=", ":");
	}
	
	
	@RequestMapping("/addCourse")
	@CrossOrigin(origins = "http://localhost:3000")
	@Transactional 
	public String addCourse(HttpSession session,COURSE_INFO course) {
		String response ="userNotLogin";
//		Integer userId=(Integer) session.getAttribute("userId");
		try {
			if(true) {
				
				response="userLoggedIn";
				 COURSE_INFO saved=courseRepo.save(course);
				 if(saved.getTitle().length()>0) {
					 response+=",dataSaved";
				 }
			}
		}catch(Exception e) {
			e.printStackTrace();
			response="error occured!";
		}
		return response;
	}
	
	
	@RequestMapping("/addQuiz")
	@CrossOrigin(origins = "http://localhost:3000")
	@Transactional 
	public String addQuiz(HttpSession session,QUIZ_DATA quizData) {
		String response ="userNotLogin";
		Integer userId=(Integer) session.getAttribute("userId");
		try {
			if(true) {
				response="userLoggedIn";
				System.out.println(quizData.getCorrect_option());
				System.out.println(quizData.getCourse_id());
				System.out.println(quizData.getOption_selected());
				QUIZ_DATA saved=quizRepo.save(quizData);
				 if(saved.getQuestion_id()>0) {
					 response+=",dataSaved";
				 }
			}
		}catch(Exception e) {
			response="error occured!";
		}
		return response;
	}
	
	@RequestMapping("/getQuizData")
	@CrossOrigin(origins = "http://localhost:3000")
	@Transactional 
	public String getQuizData(HttpSession session) {
		HashMap<String,Object> responseMap=new HashMap<String,Object>();
		String response ="userNotLogin";
		Integer userId=(Integer) session.getAttribute("userId");
		try {
			if(true) {
				response='"'+"userLoggedIn"+'"';
				HashMap<String,ArrayList<String>> quizes=new HashMap<String,ArrayList<String>>();
				Iterable<QUIZ_DATA> allData=quizRepo.findAll();
				System.out.println(allData);
				for(QUIZ_DATA quiz:allData) {
					
					ArrayList<String> ar=new ArrayList<String>();
					ar.add('"'+quiz.getCourse_id()+""+'"');
					ar.add('"'+quiz.getQuestion_des()+'"');
					ar.add('"'+quiz.getOptions()+'"');
					ar.add('"'+quiz.getCorrect_option()+'"');
					ar.add('"'+quiz.getOption_selected()+'"');
					quizes.put(quiz.getQuestion_id()+"", ar);
				}
				responseMap.put('"'+"quizes"+'"', quizes);
				
			}
		}catch(Exception e) {
			response="error occured!";
		}
		responseMap.put('"'+"response"+'"',response);
		
		return responseMap.toString();
	}
	
}
