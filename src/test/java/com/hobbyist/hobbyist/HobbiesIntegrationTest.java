package com.hobbyist.hobbyist;

import com.hobbyist.hobbyist.models.Hobby;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.servlet.http.HttpSession;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = HobbyistApplication.class)
@AutoConfigureMockMvc
public class HobbiesIntegrationTest {

    private User testUser;
    private Hobby testHobby;
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

        if (testUser == null) {
            User newUser = new User();
            newUser.setFirstName("test");
            newUser.setLastName("test");
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("Testing12345"));
            newUser.setEmail("testuser@mail.com");
            testUser = userDao.save(newUser);
        }

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
        assertNotNull(mvc);
    }

    @Test
    public void testIfUserSessionIsActive() throws Exception {
        assertNotNull(httpSession);
    }

    // create
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
    // Read
    @Test
    public void testAdsIndex() throws Exception {
        Hobby existingHobby = hobbiesDao.findAll().get(0);

        // Makes a Get request to /ads and verifies that we get some of the static text of the ads/index.html template and at least the title from the first Ad is present in the template.
        this.mvc.perform(get("/hobbies"))
                .andExpect(status().isOk());
    }

    // update
    @Test
    public void testEditHobby() throws Exception {
        // Gets the first Ad for tests purposes
        Hobby existingHobby = hobbiesDao.findAll().get(0);

        // Makes a Post request to /ads/{id}/edit and expect a redirection to the Ad show page
        this.mvc.perform(
                post("/hobby/" + existingHobby.getId() + "/edit").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("title", "edited title")
                        .param("description", "edited description")
                        .param("categories", String.valueOf(1))
                        .param("patience", String.valueOf(1))
                        .param("difficulty", String.valueOf(1))
                        .param("cost", String.valueOf(1)));
    }

    // Delete
    @Test
    public void testDeleteHobby() throws Exception {
        // Creates a test Ad to be deleted
        this.mvc.perform(
                post("/hobby/create").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("title", "Crikey")
                        .param("description", "won't last long")
                        .param("categories", String.valueOf(1))
                        .param("patience", String.valueOf(1))
                        .param("difficulty", String.valueOf(1))
                        .param("cost", String.valueOf(1)));

        // Get the recent Ad that matches the title
        Hobby existingHobby = hobbiesDao.getOneByTitle("Crikey");

        // Makes a Post request to /ads/{id}/delete and expect a redirection to the Ads index
        this.mvc.perform(
                post("/hobby/" + existingHobby.getId() + "/delete").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("id", String.valueOf(existingHobby.getId())));
    }
}
