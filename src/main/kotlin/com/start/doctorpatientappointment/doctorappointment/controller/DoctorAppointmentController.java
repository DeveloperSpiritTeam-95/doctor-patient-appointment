package com.start.doctorpatientappointment.doctorappointment.controller;

import com.start.doctorpatientappointment.doctorappointment.model.DoctorAppointment;
import com.start.doctorpatientappointment.doctorappointment.service.DoctorAppointmentService;
import com.start.doctorpatientappointment.doctorappointment.util.AppointmentLatency;
import com.start.doctorpatientappointment.doctorappointment.util.CancelAppointment;
import com.start.doctorpatientappointment.doctorappointment.util.DoctorAppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/appointments")
public class DoctorAppointmentController {

    @Autowired
    private DoctorAppointmentService service;


    @PostMapping(value = "/createAppointment")
    public ResponseEntity<String> createAppointment(@RequestBody DoctorAppointmentDto input){
        return this.service.createAppointment(input);
    }

    @PutMapping(value = "/reScheduleAppointment")
    public ResponseEntity<String> reScheduleAppointment(@RequestBody DoctorAppointmentDto input){
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
    public ResponseEntity<List<DoctorAppointment>> getAppointmentsByUser(@PathVariable("userId") String userId){
        return this.service.getAppointmentsByUser(userId);
    }

    @GetMapping(value = "/getAppointmentsByDoctor/{doctorId}")
    public ResponseEntity<List<DoctorAppointment>> getAppointmentsByDoctor(@PathVariable("doctorId") String doctorId){
        return this.service.getAppointmentsByDoctor(doctorId);
    }

    @GetMapping(value = "/getAppointmentById/{appointmentId}")
    public ResponseEntity<Optional<DoctorAppointment>> getAppointmentById(@PathVariable("appointmentId") String appointmentId){
        return this.service.getAppointmentById(appointmentId);
    }



}
