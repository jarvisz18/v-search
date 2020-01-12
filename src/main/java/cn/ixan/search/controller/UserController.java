package cn.ixan.search.controller;

import cn.ixan.search.domain.User;
import cn.ixan.search.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author stack_zhang@outlook.com
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/syncUserInfo2ES")
	public boolean syncUserInfo2ES() {
		return userService.syncUserInfo2ES();
	}

	/**
	 * 获取用户列表
	 * 处理 "/users" 的 GET 请求，用来获取用户列表
	 * 通过 @RequestParam 传递参数，进一步实现条件查询或者分页查询
	 */
	@GetMapping("/list/{pageNum}/{pageSize}")
	public List<User> getUserList(@PathVariable(value = "pageNum")Integer pageNum,
	                              @PathVariable("pageSize")Integer pageSize) {
		List<User> users = userService.listUser(pageNum, pageSize);
		log.info("查询用户记录:[{}]",users.size());
		return users;
	}

	/**
	 * 显示创建用户表单
	 */
	@GetMapping(value = "/create")
	public String createUserForm(User user) {
		return null;
	}

	/**
	 * 创建用户
	 * 处理 "/users" 的 POST 请求，用来获取用户列表
	 * 通过 @ModelAttribute 绑定参数，也通过 @RequestParam 从页面中传递参数
	 */
	@PostMapping(value = "/create")
	public String postUser(@ModelAttribute User user) {
		userService.insertByUser(user);
		return "redirect:/users/";
	}

	/**
	 * 显示需要更新用户表单
	 * 处理 "/users/{id}" 的 GET 请求，通过 URL 中的 id 值获取 User 信息
	 * URL 中的 id ，通过 @PathVariable 绑定参数
	 */
	@GetMapping(value = "/update/{id}")
	public User getUser(@PathVariable Integer id) {
		return userService.findUserById(id);
	}

	/**
	 * 处理 "/users/{id}" 的 PUT 请求，用来更新 User 信息
	 */
	@PostMapping(value = "/update")
	public String putUser(@ModelAttribute User user) {
		userService.update(user);
		return "redirect:/users/";
	}

	/**
	 * 处理 "/users/{id}" 的 GET 请求，用来删除 User 信息
	 */
	@DeleteMapping(value = "/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.delete(id);
		return "redirect:/users/";
	}
}
