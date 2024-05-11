package com.start.doctorpatientappointment.users.controller;

import com.start.doctorpatientappointment.users.model.UserState;
import com.start.doctorpatientappointment.users.service.UserStateService;
import com.start.doctorpatientappointment.users.util.UserLogin;
import com.start.doctorpatientappointment.users.util.UserStateDto;
import com.start.doctorpatientappointment.util.AllPageResponse;
import com.start.doctorpatientappointment.util.PageRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserStateController {

    @Autowired
    private UserStateService service;

    @PostMapping(value = "/userSignup")
    public ResponseEntity<String> userSignup(@RequestBody UserStateDto input){
        return this.service.userSignup(input);
    }

    @PutMapping(value = "/updateProfile/{userId}")
    public ResponseEntity<Optional<UserState>> updateProfile(@PathVariable("userId") String userId , @RequestBody UserStateDto input){
        return this.service.updateProfile(userId,input);
    }

    @PostMapping(value = "/userLogin")
    public ResponseEntity<String> userLogin(@RequestBody UserLogin input){
        return this.service.userLogin(input);
    }

    @GetMapping(value = "/getUserProfileById/{userId}")
    public ResponseEntity<Optional<UserState>> getUserProfileById(@PathVariable("userId") String userId){
        return this.service.getUserProfileById(userId);
    }

    @GetMapping(value = "/getUserProfileByEmail/{email}")
    public ResponseEntity<Optional<UserState>> getUserProfileByEmail(@PathVariable("email") String email){
        return this.service.getUserProfileByEmail(email);
    }

    @GetMapping(value = "/getAllUserProfiles")
    public ResponseEntity<List<UserState>> getAllUserProfiles(){
        return this.service.getAllUserProfiles();
    }


    @DeleteMapping(value = "/deleteUserProfileByEmail/{email}")
    public ResponseEntity<String> deleteUserProfileByEmail(@PathVariable("email") String email){
        return this.service.deleteUserProfileByEmail(email);
    }

    @DeleteMapping(value = "/deleteUserProfileById/{userId}")
    public ResponseEntity<String> deleteUserProfileById(@PathVariable("userId") String userId){
        return this.service.deleteUserProfileById(userId);
    }


    @GetMapping("/searchByNamePagination")
    public ResponseEntity<AllPageResponse> findBySearchString(@RequestBody PageRequestDto pageRequestDto){
        return this.service.findBySearchString(pageRequestDto);
    }



}
