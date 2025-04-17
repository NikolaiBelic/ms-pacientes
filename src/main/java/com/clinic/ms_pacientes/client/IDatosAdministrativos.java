package com.clinic.ms_pacientes.client;

import com.clinic.ms_pacientes.model.DatosAdministrativos;
import com.clinic.ms_pacientes.model.Paciente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/datos-administrativos")
public interface IDatosAdministrativos {

    @GetMapping
    public ResponseEntity<List<DatosAdministrativos>> getAllDatosAdministrativos();

    /*@PostMapping("/create")
    public ResponseEntity<DatosAdministrativos> createDatosAdministrativos(@RequestBody DatosAdministrativos datosAdministrativos);*/

    @PostMapping("/create")
    public ResponseEntity<DatosAdministrativos> createDatosAdministrativos(
            @RequestBody DatosAdministrativos datosAdministrativos,
            @RequestParam UUID pacienteId
    );
}
