package org.example.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Transporter {

    private int id;

    private String name;

    private String phoneNumber;

    List<Itinerary> itineraries = new ArrayList<>();
}
