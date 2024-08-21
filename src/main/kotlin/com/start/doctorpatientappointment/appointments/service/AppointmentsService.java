package com.start.doctorpatientappointment.appointments.service;

import com.start.doctorpatientappointment.appointments.model.Appointments;
import com.start.doctorpatientappointment.appointments.repository.AppointmentsRepository;
import com.start.doctorpatientappointment.appointments.util.AppointmentLatency;
import com.start.doctorpatientappointment.appointments.util.CancelAppointment;
import com.start.doctorpatientappointment.appointments.util.AppointmentDto;
import com.start.doctorpatientappointment.doctors.model.DoctorState;
import com.start.doctorpatientappointment.doctors.repository.DoctorStateRepository;
import com.start.doctorpatientappointment.enums.AppointmentStatus;
import com.start.doctorpatientappointment.enums.AppointmentType;
import com.start.doctorpatientappointment.users.repository.UserStateRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentsService {

    @Autowired
    private AppointmentsRepository appointmentRepository;

    @Autowired
    private DoctorStateRepository doctorStateRepository;

    @Autowired
    private UserStateRepository userStateRepository;


    public ResponseEntity<String> createAppointment(@NotNull AppointmentDto input) {
        if (this.userStateRepository.findById(input.getUserId()).isPresent()) {
            Appointments appointment = new Appointments();

            appointment.setAppointmentId(UUID.randomUUID().toString());
            appointment.setUserId(input.getUserId());
            appointment.setDoctorId(input.getDoctorId());
            appointment.setCreationDate(LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
            appointment.setAppointmentDate(input.getAppointmentDate());
            appointment.setAppointmentType(AppointmentType.HOME);
            appointment.setReason(input.getReason());
            appointment.setAppointmentStatus(AppointmentStatus.REQUEST);
            appointment.setCanceled(false);

            this.appointmentRepository.save(appointment);
            return new ResponseEntity<>("Appointment Created. ", HttpStatus.OK);
        } else
            return new ResponseEntity<>("User Does not Exist.", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> reScheduleAppointment(@NotNull AppointmentDto input) {
        Optional<Appointments> appointment = this.appointmentRepository.findById(input.getAppointmentId());
        if (appointment.isPresent()) {
            appointment.get().setUserId(appointment.get().getUserId());
            appointment.get().setDoctorId(appointment.get().getDoctorId());
            appointment.get().setCreationDate(LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
            appointment.get().setAppointmentDate(input.getAppointmentDate());
            appointment.get().setAppointmentType(AppointmentType.HOME);
            appointment.get().setAppointmentStatus(AppointmentStatus.REQUEST);
            appointment.get().setCanceled(false);

            this.appointmentRepository.save(appointment.get());
            return new ResponseEntity<>("Appointment Re-Scheduled. ", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Appointment Does not Exist.", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> acceptAppointmentByDoctor(@NotNull AppointmentLatency input) {
        Optional<Appointments> appointment = this.appointmentRepository.findById(input.getAppointmentId());
        Optional<DoctorState> doctor = this.doctorStateRepository.findById(input.getDoctorId());
        if (appointment.isPresent()) {
            if (doctor.isPresent()) {

                appointment.get().setDoctorId(input.getDoctorId());
                appointment.get().setAppointmentStatus(AppointmentStatus.ACCEPTED);
                this.appointmentRepository.save(appointment.get());
                return new ResponseEntity<>("Appointment Accepted.", HttpStatus.OK);

            } else
                return new ResponseEntity<>("Doctor Not Found With this Id.", HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>("appointment Not Found", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> completeAppointmentByDoctor(@NotNull AppointmentLatency input) {
        Optional<Appointments> appointment = this.appointmentRepository.findById(input.getAppointmentId());
        if (appointment.isPresent() && appointment.get().getUserId() != null) {
            if (appointment.get().getAppointmentStatus() == AppointmentStatus.ACCEPTED) {

                appointment.get().setAppointmentStatus(AppointmentStatus.COMPLETED);
                this.appointmentRepository.save(appointment.get());
                return new ResponseEntity<>("Appointment Completed.", HttpStatus.OK);

            } else
                return new ResponseEntity<>("You can not complete this appointment", HttpStatus.CONFLICT);
        } else
            return new ResponseEntity<>("Appointment Not Found", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> cancelAppointmentByDoctor(@NotNull CancelAppointment input) {
        Optional<Appointments> appointment = this.appointmentRepository.findById(input.getAppointmentId());
        if (appointment.isPresent()) {
            if (appointment.get().getAppointmentStatus() == AppointmentStatus.ACCEPTED) {

                appointment.get().setReason(input.getReason());
                appointment.get().setAppointmentStatus(AppointmentStatus.CANCELED);
                this.appointmentRepository.save(appointment.get());
                return new ResponseEntity<>("Appointment Canceled.", HttpStatus.OK);

            } else
                return new ResponseEntity<>("You can't cancel appointment", HttpStatus.CONFLICT);
        } else
            return new ResponseEntity<>("appointment Not Found", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> cancelAppointmentByUser(@NotNull CancelAppointment input) {
        Optional<Appointments> appointment = this.appointmentRepository.findById(input.getAppointmentId());
        if (appointment.isPresent()) {
            appointment.get().setReason(input.getReason());
            appointment.get().setAppointmentStatus(AppointmentStatus.CANCELED);

            this.appointmentRepository.save(appointment.get());
            return new ResponseEntity<>("Appointment Canceled.", HttpStatus.OK);
        } else
            return new ResponseEntity<>("appointment Not Found", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Appointments>> getAppointmentsByUser(String userId) {

        List<Appointments> appointments = this.appointmentRepository.findByUserId(userId);
        if (appointments.isEmpty())
            return new ResponseEntity<>(Collections.emptyList(),HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(appointments,HttpStatus.OK);
    }

    public ResponseEntity<List<Appointments>> getAppointmentsByDoctor(String doctorId) {
        List<Appointments> appointments = this.appointmentRepository.findByDoctorId(doctorId);
        if (appointments.isEmpty())
            return new ResponseEntity<>(Collections.emptyList(),HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(appointments,HttpStatus.OK);
    }

    public ResponseEntity<Optional<Appointments>> getAppointmentById(String appointmentId) {
        Optional<Appointments> appointment = this.appointmentRepository.findById(appointmentId);
        if (appointment.isPresent())
            return new ResponseEntity<>(appointment,HttpStatus.OK);
        else
            return new ResponseEntity<>(Optional.empty(),HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<List<Appointments>> getAllAppointmentsByStatus(AppointmentStatus status) {
        List<Appointments> appointments = this.appointmentRepository.findByAppointmentStatus(status);
        if (appointments.isEmpty())
            return new ResponseEntity<>(Collections.emptyList(),HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(appointments,HttpStatus.OK);
    }
}
