package com.clinic.ms_pacientes.controller;

import com.clinic.ms_pacientes.client.IDatosContacto;
import com.clinic.ms_pacientes.model.DatosAdministrativos;
import com.clinic.ms_pacientes.model.DatosContacto;
import com.clinic.ms_pacientes.model.Paciente;
import com.clinic.ms_pacientes.service.DatosContactoService;
import com.clinic.ms_pacientes.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class DatosContactoController implements IDatosContacto {

    @Autowired
    private DatosContactoService datosContactoService;

    @Autowired
    private PacienteService pacienteService;

    @Override
    public ResponseEntity<List<DatosContacto>> getAllDatosContacto() {
        List<DatosContacto> datosContacto = datosContactoService.getAllDatosContacto();
        return ResponseEntity.status(HttpStatus.OK).body(datosContacto);
    }

    public ResponseEntity<DatosContacto> createDatosContacto(
            DatosContacto datosContacto, UUID pacienteId
    ) {
        // Recupera el paciente desde la base de datos
        Paciente paciente = pacienteService.getPacienteById(pacienteId);
        if (paciente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Maneja el caso de paciente no encontrado
        }

        // Asocia el paciente existente
        datosContacto.setPaciente(paciente);
        DatosContacto datosC = datosContactoService.createDatosContacto(datosContacto);
        return ResponseEntity.status(HttpStatus.CREATED).body(datosC);
    }
}
