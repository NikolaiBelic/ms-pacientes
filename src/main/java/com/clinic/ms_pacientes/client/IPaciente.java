package com.clinic.ms_pacientes.client;

import com.clinic.ms_pacientes.model.Paciente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
