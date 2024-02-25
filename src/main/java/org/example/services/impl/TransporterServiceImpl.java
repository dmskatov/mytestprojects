package org.example.services.impl;

import org.example.exceptions.CustomException;
import org.example.models.Transporter;
import org.example.repositories.TransporterRepository;
import org.example.services.TransporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransporterServiceImpl implements TransporterService {

    @Autowired
    private TransporterRepository transporterRepository;

    @Override
    public void createTransporter(Transporter transporter) throws CustomException {
        if (transporter == null) {
            throw new CustomException("Enter correct data for transporter");
        }
        transporterRepository.save(transporter);
    }
}
