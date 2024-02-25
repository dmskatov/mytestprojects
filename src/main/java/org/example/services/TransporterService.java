package org.example.services;

import org.example.exceptions.CustomException;
import org.example.models.Transporter;

public interface TransporterService {

    void createTransporter (Transporter transporter) throws CustomException;
}
