package com.start.doctorpatientappointment.users.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class UserState {
    @Id
    private String userId;
    private String email;
    private String password;
    private String name;
    private String bio;
    private String age;
    private String gender;
}
