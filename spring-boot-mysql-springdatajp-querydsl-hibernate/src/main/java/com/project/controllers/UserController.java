package com.project.controllers;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysema.query.types.expr.BooleanExpression;
import com.project.models.User;
import com.project.models.UserDao;
import com.project.models.UserRepository;
import com.project.queryBuilder.UserPredicatesBuilder;

@Controller
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository myUserRepository;

	@Autowired
	private UserDao userDao;

	@RequestMapping(method = RequestMethod.GET, value = "/myusers")
	@ResponseBody
	public Iterable<User> search(@RequestParam(value = "search") String search) {
		UserPredicatesBuilder builder = new UserPredicatesBuilder();

		if (search != null) {
			Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
			java.util.regex.Matcher matcher = pattern.matcher(search + ",");
			while (matcher.find()) {
				builder.with(matcher.group(1), matcher.group(2),
						matcher.group(3));
			}
		}
		BooleanExpression exp = builder.build();
		return myUserRepository.findAll(exp);
	}

	@RequestMapping("/create")
	@ResponseBody
	public String create(String email, String name) {
		User user = null;
		try {
			user = new User(email, name);
			userDao.save(user);
		} catch (Exception ex) {
			return "Error creating the user: " + ex.toString();
		}
		return "User succesfully created! (id = " + user.getId() + ")";
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(long id) {
		try {
			User user = new User(id);
			userDao.delete(user);
		} catch (Exception ex) {
			return "Error deleting the user: " + ex.toString();
		}
		return "User succesfully deleted!";
	}

	@RequestMapping("/get-by-email/{emailid}")
	@ResponseBody
	public String getByEmail(@PathVariable("emailid") String email) {
		String userId;
		try {
			User user = userDao.findByEmail(email);
			System.out.println("emailid---" + email);
			userId = String.valueOf(user.getId());
		} catch (Exception ex) {
			return "User not found";
		}
		return "The user id is: " + userId;
	}

	@RequestMapping("id/{id}")
	@ResponseBody
	public User getById(@PathVariable("id") long ident) {
		String userId;

		User user = userDao.findById(ident);

		return user;

	}

	@RequestMapping("/update")
	@ResponseBody
	public String updateUser(long id, String email, String name) {
		try {
			User user = userDao.findOne(id);
			user.setEmail(email);
			user.setName(name);
			userDao.save(user);
		} catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		return "User succesfully updated!";
	}

}
