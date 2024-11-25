package io.active.pharmacy.gateway.dto;

import io.active.pharmacy.gateway.validation.PasswordMatches;
import io.active.pharmacy.gateway.validation.ValidEmail;
import io.active.pharmacy.gateway.validation.ValidPassword;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Embedded;

@PasswordMatches
@Data
public class UserDto {

    @NotNull(message = "First Name cannot be null or empty")
    @Size(min = 1, message = "First Name cannot be null or empty")
    private String firstName;

    @NotNull(message = "Last Name cannot be null or empty")
    @Size(min = 1, message = "Last Name cannot be null or empty")
    private String lastName;

    @ValidPassword
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @ValidEmail
    @NotNull(message = "EMAIL")
    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;

}
