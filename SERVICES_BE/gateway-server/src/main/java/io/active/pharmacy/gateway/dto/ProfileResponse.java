package io.active.pharmacy.gateway.dto;

import io.active.pharmacy.gateway.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfileResponse {

    User user;
//    String username;
    Set<String> roles;



}
