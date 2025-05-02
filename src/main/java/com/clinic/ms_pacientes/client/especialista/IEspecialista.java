package com.clinic.ms_pacientes.client.especialista;

import com.clinic.ms_pacientes.model.DatosAdministrativos;
import com.clinic.ms_pacientes.model.especialista.Especialista;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/especialista")
public interface IEspecialista {

    @GetMapping
    public ResponseEntity<List<Especialista>> getAllEspecialistas();
}
