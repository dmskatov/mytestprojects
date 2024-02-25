package org.example.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Itinerary {

    private int id;

    private String departure;

    private String destination;

    private int duration;

    List<Transporter> transporters = new ArrayList<>();

    public void addTransporter (Transporter transporter) {
        transporters.add(transporter);
    }
}
