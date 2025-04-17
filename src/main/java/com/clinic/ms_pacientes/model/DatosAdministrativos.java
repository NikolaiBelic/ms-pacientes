package com.clinic.ms_pacientes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "CLINIC_DATOS_ADMINISTRATIVOS")
public class DatosAdministrativos {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "PACIENTE_ID", nullable = false)
    @JsonBackReference
    private Paciente paciente;

    @Column(name = "ESTADO_PACIENTE", length = 50)
    private String estadoPaciente;

    @Column(name = "CIUDAD_NACIMIENTO", length = 50)
    private String ciudadNacimiento;

    @Column(name = "NACIONALIDAD", length = 50)
    private String nacionalidad;

    @Column(name = "PROVINCIA_NACIMIENTO", length = 50)
    private String provinciaNacimiento;

    @Column(name = "TIPO_DOCUMENTO", length = 20)
    private String tipoDocumento;

    @Column(name = "NUMERO_DOCUMENTO", length = 9)
    private String numeroDocumento;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Integer version;

    // Constructores
    public DatosAdministrativos() {}

    public DatosAdministrativos(UUID id, Paciente paciente, String estadoPaciente, String ciudadNacimiento,
                                String nacionalidad, String provinciaNacimiento, String tipoDocumento,
                                String numeroDocumento, Integer version) {
        this.id = id;
        this.paciente = paciente;
        this.estadoPaciente = estadoPaciente;
        this.ciudadNacimiento = ciudadNacimiento;
        this.nacionalidad = nacionalidad;
        this.provinciaNacimiento = provinciaNacimiento;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.version = version;
    }

    // Getters y setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getEstadoPaciente() {
        return estadoPaciente;
    }

    public void setEstadoPaciente(String estadoPaciente) {
        this.estadoPaciente = estadoPaciente;
    }

    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getProvinciaNacimiento() {
        return provinciaNacimiento;
    }

    public void setProvinciaNacimiento(String provinciaNacimiento) {
        this.provinciaNacimiento = provinciaNacimiento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Integer getVersion() { return version; }
}