package com.start.doctorpatientappointment.doctors.controller;

import com.start.doctorpatientappointment.doctors.model.DoctorState;
import com.start.doctorpatientappointment.doctors.service.DoctorStateService;
import com.start.doctorpatientappointment.doctors.util.DoctorLogin;
import com.start.doctorpatientappointment.doctors.util.DoctorStateDto;
import com.start.doctorpatientappointment.util.AllPageResponse;
import com.start.doctorpatientappointment.util.PageRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorStateController {

    @Autowired
    private DoctorStateService service;

    @PostMapping(value = "/doctorSignup")
    public ResponseEntity<String> doctorSignup(@RequestBody DoctorStateDto input){
        return this.service.doctorSignup(input);
    }

    @PutMapping(value = "/updateProfile/{doctorId}")
    public ResponseEntity<Optional<DoctorState>> updateProfile(@PathVariable("doctorId") String doctorId , @RequestBody DoctorStateDto input){
        return this.service.updateProfile(doctorId,input);
    }

    @PostMapping(value = "/doctorLogin")
    public ResponseEntity<String> doctorLogin(@RequestBody DoctorLogin input){
        return this.service.doctorLogin(input);
    }

    @GetMapping(value = "/getDoctorProfileById/{doctorId}")
    public ResponseEntity<Optional<DoctorState>> getDoctorProfileById(@PathVariable("doctorId") String doctorId){
        return this.service.getDoctorProfileById(doctorId);
    }

    @GetMapping(value = "/getDoctorProfileByEmail/{email}")
    public ResponseEntity<Optional<DoctorState>> getDoctorProfileByEmail(@PathVariable("email") String email){
        return this.service.getDoctorProfileByEmail(email);
    }

    @GetMapping(value = "/getAllDoctorProfiles")
    public ResponseEntity<List<DoctorState>> getAllDoctorProfiles(){
        return this.service.getAllDoctorProfiles();
    }


    @DeleteMapping(value = "/deleteDoctorProfileByEmail/{email}")
    public ResponseEntity<String> deleteDoctorProfileByEmail(@PathVariable("email") String email){
        return this.service.deleteDoctorProfileByEmail(email);
    }

    @DeleteMapping(value = "/deleteDoctorProfileById/{doctorId}")
    public ResponseEntity<String> deleteDoctorProfileById(@PathVariable("doctorId") String doctorId){
        return this.service.deleteDoctorProfileById(doctorId);
    }

    @GetMapping("/searchByNamePagination")
    public ResponseEntity<AllPageResponse> findBySearchString(@RequestBody PageRequestDto pageRequestDto){
        return this.service.findBySearchString(pageRequestDto);
    }


}
