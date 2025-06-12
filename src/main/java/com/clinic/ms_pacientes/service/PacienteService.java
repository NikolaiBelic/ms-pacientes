package com.clinic.ms_pacientes.service;

import com.clinic.ms_pacientes.model.DatosAdministrativos;
import com.clinic.ms_pacientes.model.DatosContacto;
import com.clinic.ms_pacientes.model.DatosFacturacion;
import com.clinic.ms_pacientes.model.Paciente;
import com.clinic.ms_pacientes.model.empresa.Empresa;
import com.clinic.ms_pacientes.repository.PacienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinic.ms_pacientes.utils.DateFormat;

import java.sql.Date;
import java.util.*;

import static com.clinic.ms_pacientes.utils.DateFormat.ajustarFechaAEspana;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Paciente> getAllPacientes () {
        return pacienteRepository.getAllPacientes();
    }
    public Paciente getPacienteById(UUID id) {
        return pacienteRepository.getPacienteById(id);
    }

    public Paciente createPaciente(Paciente paciente) {

        // Buscar la empresa por ID
        UUID empresaId = UUID.fromString("90630F2E-7F6A-D11A-E95C-790C6655F474");
        Empresa responsable = entityManager.find(Empresa.class, empresaId);

        if (responsable == null) {
            throw new EntityNotFoundException("Empresa no encontrada con ID: " + empresaId);
        }

        // Asignar la empresa como responsable de tratamiento de datos
        DatosAdministrativos datosAdministrativos = paciente.getDatosAdministrativos();
        datosAdministrativos.setResponsableTratamientoDatos(responsable);
        datosAdministrativos.setCreateTs(ajustarFechaAEspana(datosAdministrativos.getCreateTs()));
        datosAdministrativos.setUpdateTs(ajustarFechaAEspana(datosAdministrativos.getUpdateTs()));
        datosAdministrativos.setPaciente(paciente); // Relación bidireccional
        paciente.setDatosAdministrativos(datosAdministrativos);

        DatosContacto datosContacto = paciente.getDatosContacto();
        datosContacto.setCreateTs(ajustarFechaAEspana(datosContacto.getCreateTs()));
        datosContacto.setUpdateTs(ajustarFechaAEspana(datosContacto.getUpdateTs()));
        datosContacto.setPaciente(paciente); // Relación bidireccional
        paciente.setDatosContacto(datosContacto);

        DatosFacturacion datosFacturacion = paciente.getDatosFacturacion();
        datosFacturacion.setCreateTs(ajustarFechaAEspana(datosFacturacion.getCreateTs()));
        datosFacturacion.setUpdateTs(ajustarFechaAEspana(datosFacturacion.getUpdateTs()));
        datosFacturacion.setPaciente(paciente); // Relación bidireccional
        paciente.setDatosFacturacion(datosFacturacion);

        paciente.setCreateTs(ajustarFechaAEspana(paciente.getCreateTs()));
        paciente.setUpdateTs(ajustarFechaAEspana(paciente.getUpdateTs()));

        return pacienteRepository.save(paciente);
    }

    public Paciente updatePaciente(Paciente paciente) {
        System.out.println("Paciente ID: " + paciente.getId());
        if (paciente.getId() == null) {
            throw new IllegalArgumentException("El ID del paciente no puede ser null para actualizar.");
        }

        Optional<Paciente> existingPacienteOpt = pacienteRepository.findById(paciente.getId());
        if (existingPacienteOpt.isEmpty()) {
            throw new EntityNotFoundException("Paciente no encontrado con ID: " + paciente.getId());
        }

        System.out.println("Tipo de dato de createTs: " + (paciente.getCreateTs() != null ? paciente.getCreateTs().getClass().getName() : "null"));

        Paciente existingPaciente = existingPacienteOpt.get();

        // Actualizar campos principales
        existingPaciente.setNombre(paciente.getNombre());
        existingPaciente.setApellidos(paciente.getApellidos());
        existingPaciente.setGenero(paciente.getGenero());
        existingPaciente.setFechaNacimiento(paciente.getFechaNacimiento());
        existingPaciente.setUpdateTs(ajustarFechaAEspana(paciente.getUpdateTs()));
        existingPaciente.setUpdatedBy(paciente.getUpdatedBy());

        // Actualizar DatosAdministrativos sin reemplazar la instancia
        DatosAdministrativos datosExistentes = existingPaciente.getDatosAdministrativos();
        DatosAdministrativos nuevosDatos = paciente.getDatosAdministrativos();
        datosExistentes.setEstadoPaciente(nuevosDatos.getEstadoPaciente());
        datosExistentes.setCiudadNacimiento(nuevosDatos.getCiudadNacimiento());
        datosExistentes.setNacionalidad(nuevosDatos.getNacionalidad());
        datosExistentes.setProvinciaNacimiento(nuevosDatos.getProvinciaNacimiento());
        datosExistentes.setTipoDocumento(nuevosDatos.getTipoDocumento());
        datosExistentes.setNumeroDocumento(nuevosDatos.getNumeroDocumento());
        datosExistentes.setUpdateTs(ajustarFechaAEspana(nuevosDatos.getUpdateTs()));
        datosExistentes.setUpdatedBy(nuevosDatos.getUpdatedBy());

        // Actualizar DatosContacto sin reemplazar la instancia
        DatosContacto contactoExistente = existingPaciente.getDatosContacto();
        DatosContacto nuevoContacto = paciente.getDatosContacto();
        contactoExistente.setTelefono(nuevoContacto.getTelefono());
        contactoExistente.setEmail(nuevoContacto.getEmail());
        contactoExistente.setCalle(nuevoContacto.getCalle());
        contactoExistente.setNumero(nuevoContacto.getNumero());
        contactoExistente.setCopiaContactoFacturacion(nuevoContacto.getCopiaContactoFacturacion());
        contactoExistente.setCodigoPostal(nuevoContacto.getCodigoPostal());
        contactoExistente.setCiudad(nuevoContacto.getCiudad());
        contactoExistente.setProvincia(nuevoContacto.getProvincia());

        contactoExistente.setUpdateTs(ajustarFechaAEspana(nuevoContacto.getUpdateTs()));
        contactoExistente.setUpdatedBy(nuevoContacto.getUpdatedBy());

        // Actualizar DatosFacturacion sin reemplazar la instancia
        DatosFacturacion facturacionExistente = existingPaciente.getDatosFacturacion();
        DatosFacturacion nuevaFacturacion = paciente.getDatosFacturacion();
        facturacionExistente.setNif(nuevaFacturacion.getNif());
        facturacionExistente.setNombre(nuevaFacturacion.getNombre());
        facturacionExistente.setApellidos(nuevaFacturacion.getApellidos());
        facturacionExistente.setCalle(nuevaFacturacion.getCalle());
        facturacionExistente.setNumero(nuevaFacturacion.getNumero());
        facturacionExistente.setCiudad(nuevaFacturacion.getCiudad());
        facturacionExistente.setProvincia(nuevaFacturacion.getProvincia());
        facturacionExistente.setUpdateTs(ajustarFechaAEspana(nuevaFacturacion.getUpdateTs()));
        facturacionExistente.setUpdatedBy(nuevaFacturacion.getUpdatedBy());

        return pacienteRepository.save(existingPaciente);
    }

    @Transactional
    public void softDeletePacientes(List<UUID> ids, String deletedBy) {
        java.util.Date deleteTs = new java.util.Date();
        pacienteRepository.softDeletePacientes(ids, deleteTs, deletedBy);
        pacienteRepository.softDeleteDatosAdministrativos(ids, deleteTs, deletedBy);
        pacienteRepository.softDeleteDatosContacto(ids, deleteTs, deletedBy);
        pacienteRepository.softDeleteDatosFacturacion(ids, deleteTs, deletedBy);
        pacienteRepository.softDeleteCitas(ids, deleteTs, deletedBy);
    }

    public List<Paciente> findPacientesByFilter(
            String nombre,
            String apellidos,
            Date fechaNacimiento,
            String genero,
            String estadoPaciente,
            String ciudadNacimiento,
            String nacionalidad,
            String provinciaNacimiento,
            String tipoDocumento,
            String numeroDocumento
    ) {
        return pacienteRepository.findPacientesByFilter(nombre, apellidos, fechaNacimiento,
                genero, estadoPaciente, ciudadNacimiento, nacionalidad, provinciaNacimiento,
                tipoDocumento, numeroDocumento);
    }

    public List<Paciente> findPacientesByFiltro(String trackingId, int page, int size, Map<String, Object> filtros) {
        StringBuilder sql = new StringBuilder(
                "SELECT p.* " +
                /*"SELECT p.ID, p.NOMBRE, p.APELLIDOS, p.FECHA_NACIMIENTO, p.GENERO, da.ID, " +
                        "da.TIPO_DOCUMENTO, da.NUMERO_DOCUMENTO, da.NACIONALIDAD, " +
                        "da.ESTADO_PACIENTE, da.CIUDAD_NACIMIENTO, da.PROVINCIA_NACIMIENTO " +*/
                        "FROM CLINIC_PACIENTE p " +
                        "LEFT JOIN CLINIC_DATOS_ADMINISTRATIVOS da ON p.ID = da.PACIENTE_ID " +
                        "WHERE 1 = 1 AND p.DELETE_TS IS NULL");
        Map<String, Object> paramsQuery = new HashMap<>();

        if (filtros.containsKey("nombre")) {
            sql.append(" AND p.NOMBRE LIKE :nombre");
            paramsQuery.put("nombre", "%" + filtros.get("nombre") + "%");
        }

        if (filtros.containsKey("apellidos")) {
            sql.append(" AND p.APELLIDOS LIKE :apellidos");
            paramsQuery.put("apellidos", "%" + filtros.get("apellidos") + "%");
        }

        if (filtros.containsKey("fechaNacimiento")) {
            sql.append(" AND p.FECHA_NACIMIENTO = :fechaNacimiento");
            paramsQuery.put("fechaNacimiento", new java.sql.Date((Long) filtros.get("fechaNacimiento")));
        }

        if (filtros.containsKey("genero")) {
            sql.append(" AND p.GENERO LIKE :genero");
            paramsQuery.put("genero", "%" + filtros.get("genero") + "%");
        }

        if (filtros.containsKey("estadoPaciente")) {
            sql.append(" AND da.ESTADO_PACIENTE LIKE :estadoPaciente");
            paramsQuery.put("estadoPaciente", "%" + filtros.get("estadoPaciente") + "%");
        }

        if (filtros.containsKey("ciudadNacimiento")) {
            sql.append(" AND da.CIUDAD_NACIMIENTO LIKE :ciudadNacimiento");
            paramsQuery.put("ciudadNacimiento", "%" + filtros.get("ciudadNacimiento") + "%");
        }

        if (filtros.containsKey("nacionalidad")) {
            sql.append(" AND da.NACIONALIDAD LIKE :nacionalidad");
            paramsQuery.put("nacionalidad", "%" + filtros.get("nacionalidad") + "%");
        }

        if (filtros.containsKey("provinciaNacimiento")) {
            sql.append(" AND da.PROVINCIA_NACIMIENTO LIKE :provinciaNacimiento");
            paramsQuery.put("provinciaNacimiento", "%" + filtros.get("provinciaNacimiento") + "%");
        }

        if (filtros.containsKey("tipoDocumento")) {
            sql.append(" AND da.TIPO_DOCUMENTO LIKE :tipoDocumento");
            paramsQuery.put("tipoDocumento", "%" + filtros.get("tipoDocumento") + "%");
        }

        if (filtros.containsKey("numeroDocumento")) {
            sql.append(" AND da.NUMERO_DOCUMENTO LIKE :numeroDocumento");
            paramsQuery.put("numeroDocumento", "%" + filtros.get("numeroDocumento") + "%");
        }

        sql.append(" ORDER BY p.ID");
        sql.append(" OFFSET :page ROWS FETCH NEXT :size ROWS ONLY");

        Query query = entityManager.createNativeQuery(sql.toString(), Paciente.class);
        query.setParameter("page", page);
        query.setParameter("size", size);
        paramsQuery.forEach(query::setParameter);

        System.out.println(sql.toString());

        return query.getResultList();
    }

    public Long getTotalFiltros(String trackingId, Map<String, Object> filtros) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM CLINIC_PACIENTE p " +
                "LEFT JOIN CLINIC_DATOS_ADMINISTRATIVOS da ON p.ID = da.PACIENTE_ID " +
                "WHERE 1 = 1 AND p.DELETE_TS IS NULL");
        Map<String, Object> paramsQuery = new HashMap<>();

        if (filtros.containsKey("nombre")) {
            sql.append(" AND p.NOMBRE LIKE :nombre");
            paramsQuery.put("nombre", "%" + filtros.get("nombre") + "%");
        }

        if (filtros.containsKey("apellidos")) {
            sql.append(" AND p.APELLIDOS LIKE :apellidos");
            paramsQuery.put("apellidos", "%" + filtros.get("apellidos") + "%");
        }

        if (filtros.containsKey("fechaNacimiento")) {
            sql.append(" AND p.FECHA_NACIMIENTO LIKE :fechaNacimiento");
            paramsQuery.put("fechaNacimiento", "%" + filtros.get("fechaNacimiento") + "%");
        }

        if (filtros.containsKey("genero")) {
            sql.append(" AND p.GENERO LIKE :genero");
            paramsQuery.put("genero", "%" + filtros.get("genero") + "%");
        }

        if (filtros.containsKey("estadoPaciente")) {
            sql.append(" AND da.ESTADO_PACIENTE LIKE :estadoPaciente");
            paramsQuery.put("estadoPaciente", "%" + filtros.get("estadoPaciente") + "%");
        }

        if (filtros.containsKey("ciudadNacimiento")) {
            sql.append(" AND da.CIUDAD_NACIMIENTO LIKE :ciudadNacimiento");
            paramsQuery.put("ciudadNacimiento", "%" + filtros.get("ciudadNacimiento") + "%");
        }

        if (filtros.containsKey("nacionalidad")) {
            sql.append(" AND da.NACIONALIDAD LIKE :nacionalidad");
            paramsQuery.put("nacionalidad", "%" + filtros.get("nacionalidad") + "%");
        }

        if (filtros.containsKey("provinciaNacimiento")) {
            sql.append(" AND da.PROVINCIA_NACIMIENTO LIKE :provinciaNacimiento");
            paramsQuery.put("provinciaNacimiento", "%" + filtros.get("provinciaNacimiento") + "%");
        }

        if (filtros.containsKey("tipoDocumento")) {
            sql.append(" AND da.TIPO_DOCUMENTO LIKE :tipoDocumento");
            paramsQuery.put("tipoDocumento", "%" + filtros.get("tipoDocumento") + "%");
        }

        if (filtros.containsKey("numeroDocumento")) {
            sql.append(" AND da.NUMERO_DOCUMENTO LIKE :numeroDocumento");
            paramsQuery.put("numeroDocumento", "%" + filtros.get("numeroDocumento") + "%");
        }

        Query query = entityManager.createNativeQuery(sql.toString(), Long.class);
        paramsQuery.forEach(query::setParameter);

        System.out.println(sql.toString());

        return (Long) query.getSingleResult();
    }

}
