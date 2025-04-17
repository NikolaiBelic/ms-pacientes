package com.clinic.ms_pacientes.service;

import com.clinic.ms_pacientes.model.DatosAdministrativos;
import com.clinic.ms_pacientes.model.Paciente;
import com.clinic.ms_pacientes.repository.DatosAdministrativosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatosAdministrativosService {

    @Autowired
    private DatosAdministrativosRepository datosAdministrativosRepository;

    public List<DatosAdministrativos> getAllDatosAdministrativos () {
        return datosAdministrativosRepository.getAllDatosAdministrativos();
    }

    public DatosAdministrativos createDatosAdministrativos(DatosAdministrativos datosAdministrativos) {
        return datosAdministrativosRepository.save(datosAdministrativos);
        /*paciente.setId(UUID.randomUUID());
        return pacienteRepository.createPaciente(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getApellidos(),
                new java.sql.Date(paciente.getFechaNacimiento().getTime()),
                paciente.getGenero()
        );*/
    }
}
