package com.intellect.spring.boot.cud;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.intellect.spring.boot.cud.exception.ApplicationException;
import com.intellect.spring.boot.cud.model.User;
import com.intellect.spring.boot.cud.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntellectBootCudJarApplicationTests {

	@Autowired
	UserService userService;
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void testCreateUser() {
		User user = new User();
		user.setfName("Shafiullah");
		user.setlName("Noorbasha");
		user.setEmail("shafiknoor@gmail.com");
		user.setPinCode(605602L);
		user.setBirthDate("13-DEC-1984");
		try {
			userService.saveUser(user);
			boolean isExist = userService.isUserExist(user);
			assertTrue("User Created", isExist);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
