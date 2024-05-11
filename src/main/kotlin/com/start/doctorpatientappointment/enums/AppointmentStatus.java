package com.start.doctorpatientappointment.enums;

import lombok.Getter;

@Getter
public enum AppointmentStatus {

    REQUEST("REQUEST"),
    ACCEPTED("ACCEPTED"),
    CANCELED("CANCELED"),
    COMPLETED("COMPLETED");

    private final String appointmentStatus;

    AppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }



}
