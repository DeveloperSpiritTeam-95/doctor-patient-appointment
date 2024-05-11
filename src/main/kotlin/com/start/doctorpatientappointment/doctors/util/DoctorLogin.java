package com.start.doctorpatientappointment.doctors.util;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorLogin {
    private String email;
    private String password;
}
