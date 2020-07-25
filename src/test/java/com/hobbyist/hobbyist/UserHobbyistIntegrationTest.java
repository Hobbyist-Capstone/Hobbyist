package com.hobbyist.hobbyist;

import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;

import static org.junit.Assert.assertNotNull;

import javax.servlet.http.HttpSession;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HobbyistApplication.class)
@AutoConfigureMockMvc
public class UserHobbyistIntegrationTest {

    private User testUser;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository userDao;

    @Autowired
    HobbyRepository hobbiesDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() throws Exception {

        testUser = userDao.findByUsername("testUser");

        // Creates the test user if not exists
        if (testUser == null) {
            User newUser = new User();
            newUser.setFirstName("test");
            newUser.setLastName("test");
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("Testing12345"));
            newUser.setEmail("testuser@mail.com");
            testUser = userDao.save(newUser);
        }

        // Throws a Post request to /login and expect a redirection to the Ads index page after being logged in
        httpSession = this.mvc.perform(post("/login").with(csrf())
                .param("username", "testUser")
                .param("password", "Testing12345"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/profile"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    @Test
    public void contextLoads() {
        // Sanity Test, just to make sure the MVC bean is working
        assertNotNull(mvc);
    }

    @Test
    public void testIfUserSessionIsActive() throws Exception {
        // It makes sure the returned session is not null
        assertNotNull(httpSession);
    }

    @Test
    public void testCreateUser() throws Exception {
        // Makes a Post request to /ads/create and expect a redirection to the Ad
        this.mvc.perform(
                post("/login").with(csrf())
                        .session((MockHttpSession) httpSession)
                        // Add all the required parameters to your request like this
                        .param("firstName", "firstName")
                        .param("lastName", "lastName")
                        .param("userName", "testUser")
                        .param("password", "Testing12345")
                        .param("email", "email@email.com"))

                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testRegister() throws Exception{
        this.mvc.perform (get("/register"))
                .andExpect(status().isOk());
    }



    @Test
    public void testRedirectProfile() throws Exception {
        // Makes a Get request to /profile and expect a redirection to the login page
        this.mvc.perform(get("/profile" ))
                .andExpect(status().is(302));
    }


    @Test
    @WithMockUser(username="testUser", password="Testing12345")
    public void testShowProfile() throws Exception{
        //test that user can login and be directed to profile
        this.mvc.perform(get("/profile"))
                .andExpect(status().isOk());
    }







}
