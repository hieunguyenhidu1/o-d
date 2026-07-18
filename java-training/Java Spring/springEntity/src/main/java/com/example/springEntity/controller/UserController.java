package com.example.springEntity.controller;

import com.example.springEntity.dto.UserDto;
import com.example.springEntity.entity.Tweet;
import com.example.springEntity.entity.User;
import com.example.springEntity.service.AutoMapper;
import com.example.springEntity.service.Mapper;
import com.example.springEntity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final Mapper mapper;
    private final AutoMapper autoMapper;

//    @Autowired
//    public UserController(UserService service, Mapstruct mapper, AutoMapper autoMapper) {
//        this.service = service;
//        this.mapper = mapper;
//        this.autoMapper = autoMapper;
//    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody User user) {
        User saved = service.createWithCascade(user);
        return ResponseEntity.ok(autoMapper.toDto(saved));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = service.getAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) throws Exception {
        User user = service.getById(id);
        System.out.println(user.getStatus());
        return ResponseEntity.ok(autoMapper.toDto(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody User user) {
        User updated = service.update(id, user);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/slow-service-tweets")
    private List<Tweet> getAllTweets() throws InterruptedException {
        Thread.sleep(2000L); // delay
        return Arrays.asList(
                new Tweet("RestTemplate rules", "@user1"),
                new Tweet("WebClient is better", "@user2"),
                new Tweet("OK, both are useful", "@user1"));
    }
}
