package com.start.doctorpatientappointment.enums;

import lombok.Getter;

@Getter
public enum AppointmentType {

    HOME("HOME");


    private final String appointmentType;

    AppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

}
