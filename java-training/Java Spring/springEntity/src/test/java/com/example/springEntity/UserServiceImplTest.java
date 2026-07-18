package com.example.springEntity;

import com.example.springEntity.entity.Order;
import com.example.springEntity.entity.User;
import com.example.springEntity.repository.OrderRepository;
import com.example.springEntity.repository.UserRepository;
import com.example.springEntity.service.impl.UserServiceImpl;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

//Mock va Inject Mock thuong dung cho service, repository khong dung vi la interface

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testCreateWithCascade_ShouldSetOrdersAndSaveUser() {
        // Arrange
        User user = new User();
        user.setUsername("hieu");
        user.setEmail("hieu@example.com");
        user.setStatus(User.Status.ACTIVE);

        Order order1 = new Order();
        Order order2 = new Order();

        user.setOrders(List.of(order1, order2));

        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        User savedUser = userService.createWithCascade(user);

        // Assert
        assertNotNull(savedUser.getCreatedAt());
        assertEquals(savedUser, order1.getUser());
        assertEquals(savedUser, order2.getUser());

        verify(userRepository, times(1)).save(user);
    }
}
