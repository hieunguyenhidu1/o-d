package com.example.springEntity;

import com.example.springEntity.entity.Order;
import com.example.springEntity.entity.User;
import com.example.springEntity.repository.OrderRepository;
import com.example.springEntity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Dùng H2 thay vì DB thật
@TestPropertySource(locations = "classpath:application-test.properties")
//Dung khi muon test controller, gui http
//@AutoConfigureMockMvc
public class UserOrderIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testCreateUserWithOrders() {
        // Tạo user mới
        User user = new User();
        user.setUsername("hieu");
        user.setEmail("hieu@example.com");
        user.setStatus(User.Status.ACTIVE);
        user.setCreatedAt(new Date());

        // Tạo đơn hàng
        Order order1 = new Order();
        order1.setOrderDate(new Date());
        order1.setUser(user);

        Order order2 = new Order();
        order2.setOrderDate(new Date());
        order2.setUser(user);

        // Set list orders vào user (1-nhiều)
        user.setOrders(List.of(order1, order2));

        // Lưu user (cascading sẽ lưu cả order)
        User savedUser = userRepository.save(user);

        // Kiểm tra user đã lưu
        assertNotNull(savedUser.getId());
        assertEquals(2, savedUser.getOrders().size());

        // Truy xuất lại từ database
        Optional<User> optionalUser = userRepository.findById(savedUser.getId());
        assertTrue(optionalUser.isPresent());

        User fetchedUser = optionalUser.get();
        assertEquals("hieu", fetchedUser.getUsername());

        // Kiểm tra đơn hàng từ OrderRepository
        List<Order> orders = orderRepository.findByUser(fetchedUser);
        assertEquals(2, orders.size());
    }
}
