package com.clinic.ms_pacientes.service.especialista;

import com.clinic.ms_pacientes.model.Paciente;
import com.clinic.ms_pacientes.model.especialista.Especialista;
import com.clinic.ms_pacientes.repository.especialista.EspecialistaRepository;
import com.clinic.ms_pacientes.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class EspecialistaService {

    @Autowired
    private EspecialistaRepository especialistaRepository;

    @Autowired
    private RedisService redisService;

    private static final String ESPECIALISTAS_KEY = "ESPECIALISTAS";


    public List<Especialista> getAllEspecialistas() {
        // Check if the data is already in Redis
        List<Especialista> especialistas = (List<Especialista>) redisService.get(ESPECIALISTAS_KEY);

        if (especialistas == null) {
            // If not, fetch from the repository and save to Redis
            especialistas = especialistaRepository.getAllEspecialistas();
            redisService.save(ESPECIALISTAS_KEY, especialistas, 1, TimeUnit.HOURS);
            System.out.println("LLama a BBDD");// Cache for 1 hour
        }
        System.out.println("LLama a Redis");
        return especialistas;
    }

}
