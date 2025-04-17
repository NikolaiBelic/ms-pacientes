package com.clinic.ms_pacientes.client;

import com.clinic.ms_pacientes.model.DatosContacto;
import com.clinic.ms_pacientes.model.DatosFacturacion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/datos-facturacion")
public interface IDatosFacturacion {

    @GetMapping
    public ResponseEntity<List<DatosFacturacion>> getAllDatosFacturacion();

    @PostMapping("/create")
    public ResponseEntity<DatosFacturacion> createDatosFacturacion(
            @RequestBody DatosFacturacion datosFacturacion,
            @RequestParam UUID pacienteId
    );
}
