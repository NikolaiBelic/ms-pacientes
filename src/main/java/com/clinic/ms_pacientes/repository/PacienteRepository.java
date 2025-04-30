package com.clinic.ms_pacientes.repository;

import com.clinic.ms_pacientes.model.Paciente;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;


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
        SELECT *
        FROM CLINIC_PACIENTE
        WHERE ID = :id AND DELETE_TS IS NULL
    """, nativeQuery = true)
    Paciente getPacienteById(@Param("id") UUID id);

    @Query(value = """
            SELECT DISTINCT p
            FROM Paciente p
            LEFT JOIN FETCH p.datosAdministrativos da
            WHERE
                (:nombre IS NULL OR p.nombre = :nombre) AND
                (:apellidos IS NULL OR p.apellidos = :apellidos) AND
                (:fechaNacimiento IS NULL OR p.fechaNacimiento = :fechaNacimiento) AND
                (:genero IS NULL OR p.genero = :genero) AND
                (:estadoPaciente IS NULL OR da.estadoPaciente = :estadoPaciente) AND
                (:ciudadNacimiento IS NULL OR da.ciudadNacimiento = :ciudadNacimiento) AND
                (:nacionalidad IS NULL OR da.nacionalidad = :nacionalidad) AND
                (:provinciaNacimiento IS NULL OR da.provinciaNacimiento = :provinciaNacimiento) AND
                (:tipoDocumento IS NULL OR da.tipoDocumento = :tipoDocumento) AND
                (:numeroDocumento IS NULL OR da.numeroDocumento = :numeroDocumento)
    """)
    List<Paciente> findPacientesByFilter(
            @Param("nombre") String nombre,
            @Param("apellidos") String apellidos,
            @Param("fechaNacimiento") Date fechaNacimiento,
            @Param("genero") String genero,
            @Param("estadoPaciente") String estadoPaciente,
            @Param("ciudadNacimiento") String ciudadNacimiento,
            @Param("nacionalidad") String nacionalidad,
            @Param("provinciaNacimiento") String provinciaNacimiento,
            @Param("tipoDocumento") String tipoDocumento,
            @Param("numeroDocumento") String numeroDocumento
    );

    @Query(value = """
    SELECT DISTINCT p.*
    FROM CLINIC_PACIENTE p
    LEFT JOIN CLINIC_DATOS_ADMINISTRATIVOS da ON p.ID = da.PACIENTE_ID
    WHERE
        (:nombre IS NULL OR p.NOMBRE = :nombre) AND
        (:apellidos IS NULL OR p.APELLIDOS = :apellidos) AND
        (:fechaNacimiento IS NULL OR p.FECHA_NACIMIENTO = :fechaNacimiento) AND
        (:genero IS NULL OR p.GENERO = :genero) AND
        (:estadoPaciente IS NULL OR da.ESTADO_PACIENTE = :estadoPaciente) AND
        (:ciudadNacimiento IS NULL OR da.CIUDAD_NACIMIENTO = :ciudadNacimiento) AND
        (:nacionalidad IS NULL OR da.NACIONALIDAD = :nacionalidad) AND
        (:provinciaNacimiento IS NULL OR da.PROVINCIA_NACIMIENTO = :provinciaNacimiento) AND
        (:tipoDocumento IS NULL OR da.TIPO_DOCUMENTO = :tipoDocumento) AND
        (:numeroDocumento IS NULL OR da.NUMERO_DOCUMENTO = :numeroDocumento) AND
        p.DELETE_TS IS NULL
""", nativeQuery = true)
    List<Paciente> findPacientesByFilter(
            @Param("nombre") String nombre,
            @Param("apellidos") String apellidos,
            @Param("fechaNacimiento") Date fechaNacimiento,
            @Param("genero") String genero,
            @Param("estadoPaciente") String estadoPaciente,
            @Param("ciudadNacimiento") String ciudadNacimiento,
            @Param("nacionalidad") String nacionalidad,
            @Param("provinciaNacimiento") String provinciaNacimiento,
            @Param("tipoDocumento") String tipoDocumento,
            @Param("numeroDocumento") String numeroDocumento,
            Pageable pageable
    );


    @Transactional
    @Query(value = """
    INSERT INTO CLINIC_PACIENTE (ID, NOMBRE, APELLIDOS, FECHA_NACIMIENTO, GENERO, VERSION)
    VALUES (:id, :nombre, :apellidos, :fechaNacimiento, :genero, 1)
""", nativeQuery = true)
    Paciente createPaciente(
            @Param("id") UUID id,
            @Param("nombre") String nombre,
            @Param("apellidos") String apellidos,
            @Param("fechaNacimiento") Date fechaNacimiento,
            @Param("genero") String genero
    );
}
