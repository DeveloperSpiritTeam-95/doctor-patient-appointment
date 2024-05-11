package com.start.doctorpatientappointment.doctorappointment.util;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentLatency {
    private String appointmentId;
    private String doctorId;
}