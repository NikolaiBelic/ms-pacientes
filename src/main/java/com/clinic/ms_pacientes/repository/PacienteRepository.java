package com.clinic.ms_pacientes.repository;

import com.clinic.ms_pacientes.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
    @Query(value = """
            SELECT *
            FROM CLINIC_PACIENTE
            WHERE DELETE_TS IS NULL
        """, nativeQuery = true)
    public List<Paciente> getAllPacientes();

    @Query(value = """
    INSERT INTO CLINIC_PACIENTE (ID, NOMBRE, APELLIDOS, FECHA_NACIMIENTO, GENERO)
    VALUES (:id, :nombre, :apellidos, :fechaNacimiento, :genero)
""", nativeQuery = true)
    void createPaciente(
            @Param("id") UUID id,
            @Param("nombre") String nombre,
            @Param("apellidos") String apellidos,
            @Param("fechaNacimiento") Date fechaNacimiento,
            @Param("genero") String genero
    );
}
