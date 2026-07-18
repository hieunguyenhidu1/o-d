package com.example.springEntity;

import com.example.springEntity.entity.User;
import com.example.springEntity.repository.OrderRepository;
import com.example.springEntity.repository.UserRepository;
import com.example.springEntity.service.impl.UserServiceImpl;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManualMockTest {
    @Test
    public void testManualMock() {
        UserRepository mockRepo = Mockito.mock(UserRepository.class);
        Mockito.when(mockRepo.count()).thenReturn(123L);

        assertEquals(123L, mockRepo.count());
    }

    @Test
    void testGetByIdFound() {
        // 👉 1. Tạo mock
        UserRepository mockUserRepo = Mockito.mock(UserRepository.class);
        OrderRepository mockOrderRepo = Mockito.mock(OrderRepository.class);
        EntityManager mockEntityManager = Mockito.mock(EntityManager.class);

        // 👉 2. Tạo đối tượng service thật, inject mock
        UserServiceImpl userService = new UserServiceImpl(mockUserRepo, mockOrderRepo, mockEntityManager);

        // 👉 3. Giả lập dữ liệu trả về từ repo
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("mock-user");
        Mockito.when(mockUserRepo.findById(1L)).thenReturn(Optional.of(mockUser));

        // 👉 4. Gọi hàm và assert
        User result = userService.getById(1L);
        assertEquals("mock-user", result.getUsername());
    }

    //Khong dung de test controller do controller dung http de goi, kiem tra status code, response body
    // -> Dùng @WebMvcTest + MockMvc + @MockBean
}
