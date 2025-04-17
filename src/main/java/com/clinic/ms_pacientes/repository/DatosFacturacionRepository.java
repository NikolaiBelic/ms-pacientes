package com.clinic.ms_pacientes.repository;

import com.clinic.ms_pacientes.model.DatosFacturacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DatosFacturacionRepository extends JpaRepository<DatosFacturacion, UUID> {
    /**
     * Obtiene todos los datos de facturación que no han sido eliminados lógicamente.
     *
     * @return Lista de objetos DatosFacturacion.
     */
     @Query(value = """
             SELECT *
             FROM CLINIC_DATOS_FACTURACION
             WHERE DELETE_TS IS NULL
         """, nativeQuery = true)
    public List<DatosFacturacion> getAllDatosFacturacion();
}
