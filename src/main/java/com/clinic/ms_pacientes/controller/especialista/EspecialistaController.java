package com.clinic.ms_pacientes.controller.especialista;

import com.clinic.ms_pacientes.client.especialista.IEspecialista;
import com.clinic.ms_pacientes.model.Paciente;
import com.clinic.ms_pacientes.model.especialista.Especialista;
import com.clinic.ms_pacientes.service.especialista.EspecialistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EspecialistaController implements IEspecialista {

    @Autowired
    private EspecialistaService especialistaService;

    @Override
    public ResponseEntity<List<Especialista>> getAllEspecialistas() {
        List<Especialista> especialistas = especialistaService.getAllEspecialistas();

        return ResponseEntity.status(HttpStatus.OK).body(especialistas);
    }

}
