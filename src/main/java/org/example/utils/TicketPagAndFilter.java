package org.example.utils;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class TicketPagAndFilter {

    private int page;

    private int size;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private String time;

    private String departure;

    private String destination;

    private String transporterName;
}
