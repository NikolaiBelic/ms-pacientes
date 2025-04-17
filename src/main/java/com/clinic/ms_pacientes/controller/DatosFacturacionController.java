package com.clinic.ms_pacientes.controller;

import com.clinic.ms_pacientes.client.IDatosFacturacion;
import com.clinic.ms_pacientes.model.DatosContacto;
import com.clinic.ms_pacientes.model.DatosFacturacion;
import com.clinic.ms_pacientes.model.Paciente;
import com.clinic.ms_pacientes.service.DatosFacturacionService;
import com.clinic.ms_pacientes.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class DatosFacturacionController implements IDatosFacturacion {

    @Autowired
    private DatosFacturacionService datosFacturacionService;

    @Autowired
    private PacienteService pacienteService;

    public ResponseEntity<List<DatosFacturacion>> getAllDatosFacturacion() {
        List<DatosFacturacion> datosFacturacion = datosFacturacionService.getAllDatosFacturacion();
        return ResponseEntity.status(HttpStatus.OK).body(datosFacturacion);
    }

    public ResponseEntity<DatosFacturacion> createDatosFacturacion(
            DatosFacturacion datosFacturacion, UUID pacienteId
    ) {
        // Recupera el paciente desde la base de datos
        Paciente paciente = pacienteService.getPacienteById(pacienteId);
        if (paciente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Maneja el caso de paciente no encontrado
        }

        // Asocia el paciente existente
        datosFacturacion.setPaciente(paciente);
        DatosFacturacion datosF = datosFacturacionService.createDatosFacturacion(datosFacturacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(datosF);
    }
}
