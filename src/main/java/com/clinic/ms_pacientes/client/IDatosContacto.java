package com.clinic.ms_pacientes.client;

import com.clinic.ms_pacientes.model.DatosAdministrativos;
import com.clinic.ms_pacientes.model.DatosContacto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/datos-contacto")
public interface IDatosContacto {

    @GetMapping
    public ResponseEntity<List<DatosContacto>> getAllDatosContacto();

    @PostMapping("/create")
    public ResponseEntity<DatosContacto> createDatosContacto(
            @RequestBody DatosContacto datosContacto,
            @RequestParam UUID pacienteId
    );
}
