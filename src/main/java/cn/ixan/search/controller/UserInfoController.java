package cn.ixan.search.controller;

import cn.ixan.search.domain.UserInfo;
import cn.ixan.search.service.ExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @date 19-9-4
 */
@RestController
@RequestMapping("/users")
public class UserInfoController {
	private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	@Autowired
	private ExportService userservice;

	private List<UserInfo> getUserList(){
		List<UserInfo> lists = userservice.getUserList();
		logger.info("查询到的用户信息:[{}]",lists);
		return lists;
	}
	@GetMapping
	public ModelAndView userList(Model model){
		model.addAttribute("userList",getUserList());
		model.addAttribute("title","用户管理");
		return new ModelAndView("index","userModel",model);
	}
	/**
	 *创建表单
	 */
	@GetMapping("/form")
	public ModelAndView createForm(Model model){
		model.addAttribute("user",new UserInfo());
		model.addAttribute("title","添加用户");
		return new ModelAndView("add","userModel",model);
	}

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@PostMapping("/add")
	public ModelAndView addUser(UserInfo user){
		int result = userservice.saveUserInfo(user);
		logger.info("添加结果:[{}]",result);
		return new ModelAndView("redirect:/users");
	}

}
