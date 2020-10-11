package com.homework.demosite.service;

import com.homework.demosite.DemoSiteApplication;
import com.homework.demosite.model.User;
import com.homework.demosite.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSiteApplication.class)
class UserServiceTest {

	@MockBean
	private UserRepository repository;
	@Autowired
	private UserService service;
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@Test
	void verifyLastLoginUpdateSaves() {
		User user = new User();
		user.setUsername("test");

		when(service.findUserByUsername(anyString())).thenReturn(user);
		service.updateLastLogin(user);

		verify(repository).save(user);
	}

}
