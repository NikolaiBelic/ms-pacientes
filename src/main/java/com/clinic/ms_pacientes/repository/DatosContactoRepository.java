package com.clinic.ms_pacientes.repository;

import com.clinic.ms_pacientes.model.DatosAdministrativos;
import com.clinic.ms_pacientes.model.DatosContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DatosContactoRepository extends JpaRepository<DatosContacto, UUID> {

    /**
     * Obtiene todos los datos de contacto que no han sido eliminados lógicamente.
     *
     * @return Lista de objetos DatosContacto.
     */
    @Query(value = """
            SELECT *
            FROM CLINIC_DATOS_CONTACTO
            WHERE DELETE_TS IS NULL
        """, nativeQuery = true)
    public List<DatosContacto> getAllDatosContacto();
}
