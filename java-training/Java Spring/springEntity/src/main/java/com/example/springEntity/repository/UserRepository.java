package com.example.springEntity.repository;

import com.example.springEntity.dto.UserOrderInfo;
import com.example.springEntity.dto.UserRepoDTO;
import com.example.springEntity.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    // Automatic Custom Queries

    // Tìm theo username
    User findByUsername(String username);

    // Tìm tất cả user có email chứa từ khóa
    List<User> findByEmailContaining(String keyword);

    // Tìm user theo status (enum)
    List<User> findByStatus(User.Status status);

    // Tìm user tạo sau một thời điểm
    List<User> findByCreatedAtAfter(Date date);

    // Tìm user theo username và status
    List<User> findByUsernameAndStatus(String username, User.Status status);

    // Sắp xếp theo ngày tạo giảm dần
    List<User> findAllByOrderByCreatedAtDesc();

    //Manual Custom Queries (JPQL)
    //Viết JPQL để tìm User theo email.
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    //Viết JPQL để lấy tất cả User có status là 'ACTIVE'.
    @Query("SELECT u FROM User u WHERE u.status = 'ACTIVE'")
    List<User> findActiveUsers();

    //Viết JPQL để tìm tất cả User được tạo trước một ngày cụ thể.
    @Query("SELECT u FROM User u WHERE u.createdAt < :date")
    List<User> findUserCreatedAtBeforeDate(@Param("date") Date date);

    //Tìm tất cả user có nhiều hơn 2 đơn hàng
    @Query("SELECT u FROM User u WHERE SIZE(u.orders) > :count")
    List<User> findUsersWithMoreThanXOrders(@Param("count") int count);

    //Email chứa "gmail" (case-insensitive):
//    @Query("SELECT * FROM USER u WHERE LOWER(u.email) LIKE %:keyword% ")
//    List<User> findByEmailContainingIgnoreCase(@Param("keyword") String keyword);

    @Query("SELECT u FROM User u JOIN u.orders o GROUP BY u.id HAVING COUNT(o.id) > :count")
    List<User> findUsersWithOrderCountGreaterThan(@Param("count") long count);

    // User có ít nhất 1 Order:
    @Query("SELECT DISTINCT u FROM User u JOIN u.orders o")
    List<User> findUsersWithOrders();

    //Native query

    @Query(value = "SELECT * FROM users WHERE email LIKE %:keyword% ORDER BY created_at DESC",
            nativeQuery = true)
    List<User> findUsersByEmailKeyword(@Param("keyword") String keyword);

    //Customizing the Result with Spring Data Projection (open/close)

    // Customizing the Result with Class Constructors( class based projection)
    // Trả về danh sách UserDTO với status ACTIVE
    @Query("SELECT new com.example.springEntity.dto.UserRepoDTO(u.id, u.username, u.email, u.status) " +
            "FROM User u WHERE u.status = :status")
    List<UserRepoDTO> findUserDTOByStatus(User.Status status);

    //Dung interface-based projection
    // Spring sẽ tự map các field tương ứng với UserView
    List<UserView> findProjectedByStatus(User.Status status);

    // Join table
    @Query("SELECT u.username, o.orderDate FROM User u JOIN u.orders o")
    List<Object[]> findUserOrderPairs();

    @Query("SELECT new com.example.springEntity.dto.UserOrderInfo(u.username, u.email, COUNT(o)) " +
            "FROM User u LEFT JOIN u.orders o " +
            "GROUP BY u.id, u.username, u.email")
    List<UserOrderInfo> fetchUserOrderSummary();

    //Page
    // JPQL + Paging + Sorting
    @Query("SELECT u FROM User u WHERE u.status = :status")
    Page<User> findByStatus(@Param("status") User.Status status, Pageable pageable);

    //JPQL + SLICE
    @Query("SELECT u FROM User u WHERE u.status = :status")
    Slice<User> findSliceByStatus(@Param("status") User.Status status, Pageable pageable);

    // Sort rieng
    Sort sort = Sort.by("createdAt").descending();
    List<User> findByStatus(User.Status status, Sort sort);

    // native query + paging + soring: phai them countQuery thu cong
    @Query(
            value = "SELECT * FROM users WHERE status = :status",
            countQuery = "SELECT COUNT(*) FROM users WHERE status = :status",
            nativeQuery = true
    )
    Page<User> findNativeUsers(@Param("status") String status, Pageable pageable);


}
