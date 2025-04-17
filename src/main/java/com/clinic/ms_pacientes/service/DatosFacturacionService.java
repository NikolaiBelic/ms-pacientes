package com.clinic.ms_pacientes.service;

import com.clinic.ms_pacientes.model.DatosContacto;
import com.clinic.ms_pacientes.model.DatosFacturacion;
import com.clinic.ms_pacientes.repository.DatosFacturacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatosFacturacionService {

    @Autowired
    private DatosFacturacionRepository datosFacturacionRepository;

    public List<DatosFacturacion> getAllDatosFacturacion() {
        return datosFacturacionRepository.getAllDatosFacturacion();
    }

    public DatosFacturacion createDatosFacturacion(DatosFacturacion datosFacturacion) {
        return datosFacturacionRepository.save(datosFacturacion);
    }
}
