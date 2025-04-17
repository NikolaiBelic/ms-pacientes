package com.clinic.ms_pacientes.controller;

import com.clinic.ms_pacientes.client.IDatosAdministrativos;
import com.clinic.ms_pacientes.model.DatosAdministrativos;
import com.clinic.ms_pacientes.model.Paciente;
import com.clinic.ms_pacientes.service.DatosAdministrativosService;
import com.clinic.ms_pacientes.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class DatosAdministrativosController implements IDatosAdministrativos {

    @Autowired
    private DatosAdministrativosService datosAdministrativosService;

    @Autowired
    private PacienteService pacienteService;

    @Override
    public ResponseEntity<List<DatosAdministrativos>> getAllDatosAdministrativos() {
        List<DatosAdministrativos> datosAdministrativos = datosAdministrativosService.getAllDatosAdministrativos();

        return ResponseEntity.status(HttpStatus.OK).body(datosAdministrativos);
    }

    /*@Override
    public ResponseEntity<DatosAdministrativos> createDatosAdministrativos(DatosAdministrativos datosAdiminstrativos) {
        DatosAdministrativos datosAdministrativos = datosAdministrativosService.createDatosAdministrativos(datosAdiminstrativos);
        return ResponseEntity.status(HttpStatus.CREATED).body(datosAdiminstrativos);
    }*/

    /*@Override
    public ResponseEntity<DatosAdministrativos> createDatosAdministrativos(
            DatosAdministrativos datosAdministrativos, UUID pacienteId
    ) {
        datosAdministrativos.setPaciente(new Paciente(pacienteId)); // Asocia el paciente por ID
        DatosAdministrativos created = datosAdministrativosService.createDatosAdministrativos(datosAdministrativos);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }*/

    public ResponseEntity<DatosAdministrativos> createDatosAdministrativos(
            DatosAdministrativos datosAdministrativos, UUID pacienteId
    ) {
        // Recupera el paciente desde la base de datos
        Paciente paciente = pacienteService.getPacienteById(pacienteId);
        if (paciente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Maneja el caso de paciente no encontrado
        }

        // Asocia el paciente existente
        datosAdministrativos.setPaciente(paciente);
        DatosAdministrativos created = datosAdministrativosService.createDatosAdministrativos(datosAdministrativos);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
