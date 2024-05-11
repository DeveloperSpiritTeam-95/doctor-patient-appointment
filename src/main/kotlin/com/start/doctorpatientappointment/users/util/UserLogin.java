package com.start.doctorpatientappointment.users.util;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLogin{
    private String email;
    private String password;
}
