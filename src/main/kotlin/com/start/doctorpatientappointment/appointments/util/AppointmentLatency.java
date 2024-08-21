package com.start.doctorpatientappointment.appointments.util;

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