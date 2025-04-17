package com.clinic.ms_pacientes.service;

import com.clinic.ms_pacientes.model.DatosContacto;
import com.clinic.ms_pacientes.repository.DatosContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatosContactoService {

    @Autowired
    private DatosContactoRepository datosContactoRepository;

    public List<DatosContacto> getAllDatosContacto () {
        return datosContactoRepository.getAllDatosContacto();
    }

    public DatosContacto createDatosContacto(DatosContacto datosContacto) {
        return datosContactoRepository.save(datosContacto);
    }
}
