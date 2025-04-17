package com.clinic.ms_pacientes.client;

import com.clinic.ms_pacientes.model.Paciente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public interface IPaciente {

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes();

    @PostMapping("/create")
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente);
}
