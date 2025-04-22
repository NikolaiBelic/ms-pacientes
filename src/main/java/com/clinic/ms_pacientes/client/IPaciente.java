package com.clinic.ms_pacientes.client;

import com.clinic.ms_pacientes.model.Paciente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.UUID;

@RequestMapping
public interface IPaciente {

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes();

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable("id") UUID id);

    @PostMapping("/create")
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente);

    @GetMapping("/filter")
    public ResponseEntity<List<Paciente>> findPacientesByFilter(
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) String apellidos,
        @RequestParam(required = false) Date fechaNacimiento,
        @RequestParam(required = false) String genero,
        @RequestParam(required = false) String estadoPaciente,
        @RequestParam(required = false) String ciudadNacimiento,
        @RequestParam(required = false) String nacionalidad,
        @RequestParam(required = false) String provinciaNacimiento,
        @RequestParam(required = false) String tipoDocumento,
        @RequestParam(required = false) String numeroDocumento
    );


    /*@GetMapping("/filter")
    public ResponseEntity<Paciente> findPacientesByFilter(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "apellidos", required = false) String apellidos,
            @RequestParam(value = "fechaNacimiento", required = false) Date fechaNacimiento,
            @RequestParam(value = "genero", required = false) String genero,
            @RequestParam(value = "estadoPaciente", required = false) String estadoPaciente,
            @RequestParam(value = "ciudadNacimiento", required = false) String ciudadNacimiento,
            @RequestParam(value = "nacionalidad", required = false) String nacionalidad,
            @RequestParam(value = "provinciaNacimiento", required = false) String provinciaNacimiento,
            @RequestParam(value = "tipoDocumento", required = false) String tipoDocumento,
            @RequestParam(value = "numeroDocumento", required = false) String numeroDocumento
    );*/
}
