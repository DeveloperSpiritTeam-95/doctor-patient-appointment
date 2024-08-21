package com.start.doctorpatientappointment.appointments.controller;

import com.start.doctorpatientappointment.appointments.model.Appointments;
import com.start.doctorpatientappointment.appointments.service.AppointmentsService;
import com.start.doctorpatientappointment.appointments.util.AppointmentLatency;
import com.start.doctorpatientappointment.appointments.util.CancelAppointment;
import com.start.doctorpatientappointment.appointments.util.AppointmentDto;
import com.start.doctorpatientappointment.enums.AppointmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentsService service;


    @PostMapping(value = "/createAppointment")
    public ResponseEntity<String> createAppointment(@RequestBody AppointmentDto input){
        return this.service.createAppointment(input);
    }

    @PutMapping(value = "/reScheduleAppointment")
    public ResponseEntity<String> reScheduleAppointment(@RequestBody AppointmentDto input){
        return this.service.reScheduleAppointment(input);
    }

    @PutMapping(value = "/acceptAppointmentByDoctor")
    public ResponseEntity<String> acceptAppointmentByDoctor(@RequestBody AppointmentLatency input){
        return this.service.acceptAppointmentByDoctor(input);
    }

    @PutMapping(value = "/completeAppointmentByDoctor")
    public ResponseEntity<String> completeAppointmentByDoctor(@RequestBody AppointmentLatency input){
        return this.service.completeAppointmentByDoctor(input);
    }

    @PutMapping(value =  "/cancelAppointmentByDoctor")
    public ResponseEntity<String> cancelAppointmentByDoctor(@RequestBody CancelAppointment input){
        return this.service.cancelAppointmentByDoctor(input);
    }

    @PutMapping(value = "/cancelAppointmentByUser")
    public ResponseEntity<String> cancelAppointmentByUser(@RequestBody CancelAppointment input){
        return this.service.cancelAppointmentByUser(input);
    }

    @GetMapping(value = "/getAppointmentsByUser/{userId}")
    public ResponseEntity<List<Appointments>> getAppointmentsByUser(@PathVariable("userId") String userId){
        return this.service.getAppointmentsByUser(userId);
    }

    @GetMapping(value = "/getAppointmentsByDoctor/{doctorId}")
    public ResponseEntity<List<Appointments>> getAppointmentsByDoctor(@PathVariable("doctorId") String doctorId){
        return this.service.getAppointmentsByDoctor(doctorId);
    }

    @GetMapping(value = "/getAppointmentById/{appointmentId}")
    public ResponseEntity<Optional<Appointments>> getAppointmentById(@PathVariable("appointmentId") String appointmentId){
        return this.service.getAppointmentById(appointmentId);
    }

    @GetMapping(value = "/getAllAppointmentsByStatus/{status}")
    public ResponseEntity<List<Appointments>> getAllAppointmentsByStatus(@PathVariable AppointmentStatus status){
        return this.service.getAllAppointmentsByStatus(status);
    }



}
