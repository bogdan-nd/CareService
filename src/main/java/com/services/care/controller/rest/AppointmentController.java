package com.services.care.controller.rest;


import com.services.care.controller.rest.dto.AppointmentDTO;
import com.services.care.entity.Appointment;
import com.services.care.service.CareService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("appointments")
@AllArgsConstructor
public class AppointmentController {
    private final CareService service;

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDTO dto){
        Appointment appointment = new Appointment(dto.getHorseId(),dto.getSpecialistId());

        return ResponseEntity.ok(service.createAppointment(appointment));
    }

    @GetMapping(value = "horses/{horseId}")
    public ResponseEntity<List<Appointment>> getHorsesAppointment(@PathVariable UUID horseId){
        return ResponseEntity.ok(service.getHorseAppointments(horseId));
    }
}
