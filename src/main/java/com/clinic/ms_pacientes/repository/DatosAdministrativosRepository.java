package com.clinic.ms_pacientes.repository;

import com.clinic.ms_pacientes.model.DatosAdministrativos;
import com.clinic.ms_pacientes.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DatosAdministrativosRepository extends JpaRepository<DatosAdministrativos, UUID> {
    @Query(value = """
            SELECT *
            FROM CLINIC_DATOS_ADMINISTRATIVOS
            WHERE DELETE_TS IS NULL
        """, nativeQuery = true)
    public List<DatosAdministrativos> getAllDatosAdministrativos();
}
