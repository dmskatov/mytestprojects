package org.example.services.impl;

import org.example.exceptions.CustomException;
import org.example.models.Itinerary;
import org.example.repositories.ItineraryRepository;
import org.example.services.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItineraryServiceImpl implements ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Override
    public void createItinerary(Itinerary itinerary) throws CustomException {
        if (itinerary == null) {
            throw new CustomException("Enter correct data for itinerary");
        }
        itineraryRepository.save(itinerary);
    }
}
