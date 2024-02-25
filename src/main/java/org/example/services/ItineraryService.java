package org.example.services;

import org.example.exceptions.CustomException;
import org.example.models.Itinerary;

public interface ItineraryService {

    void createItinerary (Itinerary itinerary) throws CustomException;
}
