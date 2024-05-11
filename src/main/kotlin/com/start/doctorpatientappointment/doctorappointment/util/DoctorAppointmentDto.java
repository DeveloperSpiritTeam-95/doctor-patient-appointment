package com.start.doctorpatientappointment.doctorappointment.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.start.doctorpatientappointment.enums.AppointmentStatus;
import com.start.doctorpatientappointment.enums.AppointmentType;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorAppointmentDto {
    private String appointmentId;
    private String doctorId;
    private String userId;
    @JsonProperty("creationDate")
    private LocalDateTime creationDate;
    @JsonProperty("appointmentDate")
    private LocalDateTime appointmentDate;
    private AppointmentType appointmentType;
    private AppointmentStatus appointmentStatus;
    private String reason;
    private Boolean canceled;
}
