package com.hobbyist.hobbyist;


import com.hobbyist.hobbyist.models.Hobby;
import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.models.UserHobby;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import com.hobbyist.hobbyist.repos.UserHobbyRepository;
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

import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HobbyistApplication.class)
@AutoConfigureMockMvc
public class UserHobbyStatusTest {
    private User testUser;
    private Hobby testHobbyStatus;
    private UserHobby testUserHobbyStatus;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository userDao;

    @Autowired
    HobbyRepository hobbiesDao;

    @Autowired
    UserHobbyRepository userHobbiesDao;

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
    public void testCreateHobby() throws Exception {
        this.mvc.perform(
                post("/hobby/create").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("title", "Tennis")
                        .param("description", "Tennis Hobby")
                        .param("categories", String.valueOf(1))
                        .param("patience", String.valueOf(1))
                        .param("difficulty", String.valueOf(1))
                        .param("cost", String.valueOf(1)))
                .andExpect(status().is3xxRedirection());
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
    @WithMockUser(username = "testUser", password = "Testing12345")
    public void statusAndHobbyIdConnection() throws Exception {
        User existingUser = userDao.findAll().get(0);
        testHobbyStatus = hobbiesDao.findAll().get(0);

        this.mvc.perform(
                post("/profile/status").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("hobbyId", "2"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/hobbies"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    @Test
    @WithMockUser(username = "testUser", password = "Testing12345")
    public void setHobbyStatus() throws Exception{
        this.mvc.perform(
                post("/profile/status").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("status", "INTERESTED")
                        .param("hobbyId", "2")
                        .param("userId", "2"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/hobbies"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    @Test
    @WithMockUser(username = "testUser", password = "Testing12345")
    public void updateHobbyStatusTried() throws Exception{
        this.mvc.perform(
                post("/profile/status/edit").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("status", "TRIED_IT")
                        .param("hobbyId", "3")
                        .param("userId", "2"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/profile/status"))
                .andReturn()
                .getRequest()
                .getSession();    }

    @Test
    @WithMockUser(username = "testUser", password = "Testing12345")
    public void updateHobbyStatusHobbyist() throws Exception{
        this.mvc.perform(
                post("/profile/status/edithobbyist").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("status", "HOBBYIST")
                        .param("hobbyId", "3")
                        .param("userId", "2"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/profile/status"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    @Test
    public void testDeleteFromStatus() throws Exception{

        this.mvc.perform(
                post("/profile/status/delete").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("deleteId","34"))
                .andExpect(status().is3xxRedirection());
    }

}
