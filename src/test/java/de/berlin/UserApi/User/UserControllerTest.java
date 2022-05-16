package de.berlin.UserApi.User;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testPostUserThroughAllLayers() throws Exception {
        User userToPost = createUserBobSmith();

        mockMvc.perform(post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userToPost)))
                .andExpect(status().isCreated());

        User foundUser = userRepository.findAllByFirstName(userToPost.getFirstName()).iterator().next();

        Assertions.assertEquals(userToPost.getFirstName(), foundUser.getFirstName());
        Assertions.assertEquals(userToPost.getLastName(), foundUser.getLastName());
        Assertions.assertEquals(userToPost.getEmail(), foundUser.getEmail());
    }

    @Test
    void testPutUserThroughAllLayers() throws Exception {
        User userToUpdate = userRepository.save(createUserBobSmith());
        long id = userToUpdate.getId();

        User userToPut = createUserAliceBing();

        mockMvc.perform(put("/user/{id}", id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userToPut)))
                .andExpect(status().isCreated());


        User foundUser = userRepository.findById(id).get();

        Assertions.assertEquals(userToPut.getFirstName(), foundUser.getFirstName());
        Assertions.assertEquals(userToPut.getLastName(), foundUser.getLastName());
        Assertions.assertEquals(userToPut.getEmail(), foundUser.getEmail());

    }

    @Test
    void testDeleteUserThroughAllLayers() throws Exception {
        User userToUpdate = userRepository.save(createUserBobSmith());
        long id = userToUpdate.getId();

        mockMvc.perform(delete("/user/{id}", id))
                .andExpect(status().isOk());

        Assertions.assertFalse(userRepository.findById(id).isPresent());
    }

    @Test
    void testGetUserByFirstNameThroughAllLayers() throws Exception {
        userRepository.save(createUserBobSmith());
        userRepository.save(createUserBobBing());
        userRepository.save(createUserAliceBing());


        MvcResult res = mockMvc.perform(get("/user/byFirstName/Bob"))
                .andExpect(status().isOk()).andReturn();

        String resString = res.getResponse().getContentAsString();

        List<User> users = objectMapper.readValue(resString, new TypeReference<List<User>>() {
        });

        for (User user : users) {
            Assertions.assertEquals("Bob", user.getFirstName());
        }
    }


    private User createUserBobSmith() {
        User user = new User();
        user.setFirstName("Bob");
        user.setLastName("Smith");
        user.setEmail("BobSmith@test.mail");

        return user;
    }

    private User createUserAliceBing() {
        User user = new User();
        user.setFirstName("Alice");
        user.setLastName("Bing");
        user.setEmail("AliceBing@test.mail");

        return user;
    }

    private User createUserBobBing() {
        User user = new User();
        user.setFirstName("Bob");
        user.setLastName("Bing");
        user.setEmail("BobBing@test.mail");
        return user;
    }


}
