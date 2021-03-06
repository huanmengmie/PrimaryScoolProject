package com.primaryschool.home.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.primaryschool.global.config.PageSizeConfig;
import com.primaryschool.home.entity.DepartmentLinkContent;
import com.primaryschool.home.entity.Education;
import com.primaryschool.home.entity.IndexImages;
import com.primaryschool.home.entity.Manage;
import com.primaryschool.home.entity.Party;
import com.primaryschool.home.entity.Student;
import com.primaryschool.home.entity.Teacher;
import com.primaryschool.home.entity.TeachingResourcesClass;
import com.primaryschool.home.entity.Trends;
import com.primaryschool.home.service.IDepartmentLinkService;
import com.primaryschool.home.service.IEducationService;
import com.primaryschool.home.service.IIndexImagesService;
import com.primaryschool.home.service.IManageService;
import com.primaryschool.home.service.IPartyService;
import com.primaryschool.home.service.IStudentService;
import com.primaryschool.home.service.ITeacherService;
import com.primaryschool.home.service.ITeachingResourcesService;
import com.primaryschool.home.service.ITrendsService;

/**
 * 
* @ClassName: IndexController
* @Description: TODO 主页控制器
* @author Mingshan
* @param <T>
* @date 2017年3月26日 下午2:28:44
*
 */

@Controller
@RequestMapping("/main")
public class IndexController<T> {
    @Autowired  
    private ITrendsService<Trends> trendsService;
   
    @Autowired 
    private IEducationService<Education> educationService;
    
    @Autowired 
    private IStudentService<Student> studentService;
    
    @Autowired
    private ITeacherService<Teacher> teacherService;
    
    @Autowired
    private IManageService<Manage> manageService;
    
    @Autowired
    private IPartyService<Party> partyService;
    
    @Autowired
    private IIndexImagesService<T> indexImagesService;
    
    @Autowired
    private ITeachingResourcesService<T> teachingResourcesService;
    
    @Autowired
    private IDepartmentLinkService<T> departmentLinkService;
    /**
     * 
    * @Title: index
    * @Description: TODO 首页内容加载
    * @param @return    设定文件
    * @return String    返回类型
    * @throws
     */
	@SuppressWarnings("unchecked")
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		int position=0;
		int item_per_page=PageSizeConfig.HOME_INDEX_PAGESIZE;
		int slidePageSize=PageSizeConfig.HOME_SLIDE_PAGESIZE;
		//获取校园动态信息
		
		//获取校内新闻信息
		String newsFlag="news";
	 
		ArrayList<Trends>  news=(ArrayList<Trends>) trendsService.findTrendsInfo(newsFlag, position, item_per_page);
 		/*for(Trends t:news){
 			System.out.println("id--"+t.getId());
 		}*/
		//获取校内通知
		String noticeFlag="notice";
		ArrayList<Trends>  notice=(ArrayList<Trends>) trendsService.findTrendsInfo(noticeFlag, position, item_per_page);
		
		//获取阳光教育-主题教育
		String educationFlag="education";
		ArrayList<Education>  education=(ArrayList<Education>)educationService.findEducationInfo(educationFlag, position, item_per_page);
		//获取
		//获取学生天地-沐浴书香
		String  studentFlag="book";
		
		ArrayList<Student>  student=(ArrayList<Student>)studentService.findStudentInfo(studentFlag, position, item_per_page);
		
		//获取教师园地 -教学资源
		/*String  teacherFlag="resources";
		ArrayList<Teacher>  teacher=(ArrayList<Teacher>)teacherService.findTeacherInfo(teacherFlag, position, item_per_page);
		*/
		ArrayList<TeachingResourcesClass> teacherResources=(ArrayList<TeachingResourcesClass>) teachingResourcesService.findHomeTeachingResourcesContent(0, item_per_page);
		
		//获取学校管理 -部门链接
		String  manageFlag="department";
		ArrayList<Manage>  manage=(ArrayList<Manage>)manageService.findManageInfo(manageFlag, position, item_per_page);
		
		//获取党务工会 -支部活动
		String partyFlag="branch";
		ArrayList<Party>  party=(ArrayList<Party>)partyService.findPartyInfo(partyFlag, position, item_per_page);
		
		//首页 大图片轮播
		ArrayList<IndexImages> indexImages=(ArrayList<IndexImages>) indexImagesService.findIndexImages(0, 6);
		
		
		//获取部门链接内容
		
		ArrayList<DepartmentLinkContent> deptContent=(ArrayList<DepartmentLinkContent>) departmentLinkService.findIndexDepartmentLinkInfo(0, item_per_page);
		
		/*********图片轮播********/
		
		//获取校内新闻  图片轮播   --新闻
		ArrayList<Trends> sildeNews=(ArrayList<Trends>) trendsService.findSlideTrendsInfo(newsFlag, 0, slidePageSize);
		
		//获取阳光德育 图片轮播 --主题教育
		ArrayList<Education>  sildeEdu=(ArrayList<Education>)educationService.findSlideTrendsInfo(educationFlag,  0, slidePageSize);
		
		//获取学生天地轮播   --活动风采
		ArrayList<Student>  slideStudent=(ArrayList<Student>) studentService.findSliderStudentInfo("activity", 0, slidePageSize);
		
		//获取教师园地轮播图 --教师风采
		ArrayList<Teacher>  slideTeacher=(ArrayList<Teacher>) teacherService.findSliderTreacherInfo("teachers", 0, slidePageSize);
 		
		
		request.setAttribute("news", news);
		request.setAttribute("notice", notice);		
		request.setAttribute("education", education);
		request.setAttribute("student", student);
		request.setAttribute("teacher", teacherResources);
		request.setAttribute("manage", manage);
		request.setAttribute("party", party);
		
		request.setAttribute("deptContent", deptContent);
		
		request.setAttribute("indexImages", indexImages);
		request.setAttribute("sildeNews", sildeNews);
	    request.setAttribute("sildeEdu", sildeEdu);
	    request.setAttribute("slideStudent", slideStudent);
	    request.setAttribute("slideTeacher",slideTeacher);
	    
		request.setAttribute("newsFlag", newsFlag);
		request.setAttribute("noticeFlag", noticeFlag);
		request.setAttribute("educationFlag", educationFlag);
		request.setAttribute("studentFlag", studentFlag);
		request.setAttribute("partyFlag", partyFlag);
		return "home/index";
	}
	
	
	
}
