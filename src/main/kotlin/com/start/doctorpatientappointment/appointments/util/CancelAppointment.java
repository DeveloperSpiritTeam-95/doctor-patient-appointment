package com.start.doctorpatientappointment.appointments.util;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancelAppointment {
    private String appointmentId;
    private String inputId;
    private String reason;
}