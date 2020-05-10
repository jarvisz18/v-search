package cn.ixan.search.web.controller;

import cn.ixan.search.domain.UserInfo;
import cn.ixan.search.service.ExportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "对用户信息数据操作")
@RestController
@RequestMapping("/users")
public class UserInfoController {
	private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	@Autowired
	private ExportService userservice;

	/**
	 * 跳转主页
	 * @return String
	 */
	@ApiOperation(value = "toIndex",hidden = true)
	@GetMapping(value = "/main.html")
	public String toIndex(){
		return "index";
	}

	private List<UserInfo> getUserList(){
		List<UserInfo> lists = userservice.getUserList();
		logger.info("查询到的用户信息:[{}]",lists);
		return lists;
	}

	@ApiOperation(value = "userList",hidden = true)
	@GetMapping
	public ModelAndView userList(Model model){
		model.addAttribute("userList",getUserList());
		model.addAttribute("title","用户管理");
		return new ModelAndView("index","userModel",model);
	}
	/**
	 *创建表单
	 */
	@ApiOperation(value = "createForm",hidden = true)
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
	@ApiOperation(value = "addUser",hidden = true)
	@PostMapping("/add")
	public ModelAndView addUser(UserInfo user){
		int result = userservice.saveUserInfo(user);
		logger.info("添加结果:[{}]",result);
		return new ModelAndView("redirect:/users");
	}

}
