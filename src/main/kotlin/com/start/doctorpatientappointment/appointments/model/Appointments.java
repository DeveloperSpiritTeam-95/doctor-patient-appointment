package com.start.doctorpatientappointment.appointments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.start.doctorpatientappointment.enums.AppointmentStatus;
import com.start.doctorpatientappointment.enums.AppointmentType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Appointments {

    @Id
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
