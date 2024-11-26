package io.active.pharmacy.gateway.util;

import io.active.pharmacy.gateway.dto.UserDto;
import io.active.pharmacy.gateway.entity.Address;
import io.active.pharmacy.gateway.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class MappingUtil {


    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public static User dto2User(UserDto dto) {
        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setRole("ROLE_USER");
        return entity;
    }

    public static UserDto userAndAddress2Dto(User entity, Address add) {

        UserDto dto = new UserDto();

        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setRole(entity.getRole());

        if (add != null && add.getId() != null) {
            dto.setAddress(add);
        }

        return dto;

    }


}
