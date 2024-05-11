package com.start.doctorpatientappointment.users.util;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserStateDto {
    private String userId;
    private String email;
    private String password;
    private String name;
    private String bio;
    private String age;
    private String gender;
}

