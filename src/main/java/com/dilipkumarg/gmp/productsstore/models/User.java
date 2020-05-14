package com.dilipkumarg.gmp.productsstore.models;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User implements Identifiable<String> {

    private String id;
    private String name;
    private String address;
    private LocalDate dob;

    @Builder.Default
    private Role role = Role.USER;

    private UserCart cart; // for persisting by user
}
