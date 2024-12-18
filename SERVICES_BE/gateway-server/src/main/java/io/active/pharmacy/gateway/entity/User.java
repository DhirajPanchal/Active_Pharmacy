package io.active.pharmacy.gateway.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("crm_user")
public class User {

    @Id
    private Long id;

    @Column("email")
    private String email;

    @Column("role")
    private String role;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("phone_number")
    private String phoneNumber;

    @Column("address_id")
    private Long addressId;

    @Column("password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
