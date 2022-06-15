package com.abou.webapp.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.abou.webapp.Model.User;
import com.abou.webapp.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#createUser(org.springframework.ui.Model)}
     */
    @Test
    void testCreateUser() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/createUser");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("formNewUser"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("formNewUser"));
    }

    /**
     * Method under test: {@link UserController#deleteUser(int)}
     */
    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(this.userService).deleteUser(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/deleteUser/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    /**
     * Method under test: {@link UserController#deleteUser(int)}
     */
    @Test
    void testDeleteUser2() throws Exception {
        doNothing().when(this.userService).deleteUser(anyInt());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/deleteUser/{id}", 1);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    /**
     * Method under test: {@link UserController#saveUser(User)}
     */
    @Test
    void testSaveUser() throws Exception {
        User user = new User();
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setOccupation("Occupation");
        user.setPassword("iloveyou");
        when(this.userService.saveUser((User) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveUser");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    /**
     * Method under test: {@link UserController#saveUser(User)}
     */
    @Test
    void testSaveUser2() throws Exception {
        User user = new User();
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setOccupation("Occupation");
        user.setPassword("iloveyou");
        when(this.userService.saveUser((User) any())).thenReturn(user);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/saveUser");
        postResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    /**
     * Method under test: {@link UserController#createUser(org.springframework.ui.Model)}
     */
    @Test
    void testCreateUser2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/createUser", "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("formNewUser"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("formNewUser"));
    }

    /**
     * Method under test: {@link UserController#home(org.springframework.ui.Model)}
     */
    @Test
    void testHome() throws Exception {
        when(this.userService.getUsers()).thenReturn((Iterable<User>) mock(Iterable.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"))
                .andExpect(MockMvcResultMatchers.view().name("home"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("home"));
    }

    /**
     * Method under test: {@link UserController#home(org.springframework.ui.Model)}
     */
    @Test
    void testHome2() throws Exception {
        when(this.userService.getUsers()).thenReturn((Iterable<User>) mock(Iterable.class));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"))
                .andExpect(MockMvcResultMatchers.view().name("home"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("home"));
    }

    /**
     * Method under test: {@link UserController#updateUser(int, org.springframework.ui.Model)}
     */
    @Test
    void testUpdateUser() throws Exception {
        User user = new User();
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setOccupation("Occupation");
        user.setPassword("iloveyou");
        when(this.userService.getUser(anyInt())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/updateUser/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("formUpdateUser"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("formUpdateUser"));
    }

    /**
     * Method under test: {@link UserController#updateUser(int, org.springframework.ui.Model)}
     */
    @Test
    void testUpdateUser2() throws Exception {
        User user = new User();
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setOccupation("Occupation");
        user.setPassword("iloveyou");
        when(this.userService.getUser(anyInt())).thenReturn(user);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/updateUser/{id}", 1);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("formUpdateUser"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("formUpdateUser"));
    }
}

