package com.example.springEntity;

import com.example.springEntity.dto.UserDto;
import com.example.springEntity.entity.User;
import com.example.springEntity.service.AutoMapper;
import com.example.springEntity.service.Mapper;
import com.example.springEntity.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService; // Thay thế bean thật trong Spring context

    @MockBean
    private Mapper mapper;

    @MockBean
    private AutoMapper autoMapper;

    @Test
    void testGetUserById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("hieu");

        UserDto dto = new UserDto();
        dto.setId(1L);
        dto.setUsername("hieu");

        when(userService.getById(1L)).thenReturn(user);
        when(autoMapper.toDto(user)).thenReturn(dto);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("hieu"));
    }
}
