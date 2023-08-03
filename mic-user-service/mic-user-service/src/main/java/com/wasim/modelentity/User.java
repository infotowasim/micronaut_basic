package com.wasim.modelentity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class User {

    private int id;
    private String name;
    private String mobileNumber;
    private String email;

}
