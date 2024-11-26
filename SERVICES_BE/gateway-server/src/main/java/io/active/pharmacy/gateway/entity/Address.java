package io.active.pharmacy.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("crm_user_address")
public class Address {

    @Id
    private Long id;

//    @Column("user_id")
//    private Long userId;

    @Column("address_1")
    private String address1;

    @Column("address_2")
    private String address2;

    @Column("city")
    private String city;

    @Column("state")
    private String state;

    @Column("country")
    private String country;

    @Column("zip_code")
    private String zipCode;

    @Column("email")
    private String email;

    @Column("phone_number")
    private String phoneNumber;


}