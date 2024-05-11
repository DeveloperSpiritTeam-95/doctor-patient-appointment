package com.start.doctorpatientappointment.doctors.util;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorStateDto {
    private String doctorId;
    private String email;
    private String password;
    private String name;
    private String bio;
    private String age;
    private String gender;
}
