package com.services.care.rabbitmq;

import com.services.care.controller.rest.dto.AppointmentDTO;
import com.services.care.entity.Appointment;
import com.services.care.service.CareService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Server {
    private final CareService careService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void addAppointment(AppointmentDTO dto){
        Appointment appointment = new Appointment(dto.getHorseId(),dto.getSpecialistId());
        careService.createAppointment(appointment);
    }
}
