package com.example.springEntity.service;
import com.example.springEntity.dto.OrderDto;
import com.example.springEntity.dto.UserDto;
import com.example.springEntity.entity.Order;
import com.example.springEntity.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutoMapper {
    // khi đã dùng componentModel = "spring" thì không cần dòng này, vì Spring sẽ inject giúp bạn
//    AutoMapper INSTANCE = Mappers.getMapper(AutoMapper.class);

    UserDto toDto(User user);
    OrderDto toDto(Order order);
}
