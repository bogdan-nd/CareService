package com.services.care.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class AppointmentDTO {
    private UUID horseId;
    private UUID specialistId;
}
