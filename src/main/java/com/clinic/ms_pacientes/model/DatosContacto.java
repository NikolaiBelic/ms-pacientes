package com.clinic.ms_pacientes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "CLINIC_DATOS_CONTACTO")
public class DatosContacto {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "PACIENTE_ID", nullable = false)
    @JsonBackReference
    private Paciente paciente;

    @Column(name = "TELEFONO", length = 9)
    private String telefono;

    @Column(name = "EMAIL", length = 60)
    private String email;

    @Column(name = "CALLE", length = 150)
    private String calle;

    @Column(name = "NUMERO", length = 50)
    private String numero;

    @Column(name = "CODIGO_POSTAL", length = 5)
    private String codigoPostal;

    @Column(name = "CIUDAD", length = 30)
    private String ciudad;

    @Column(name = "PROVINCIA", length = 50)
    private String provincia;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Integer version;

    // Constructores
    public DatosContacto() {
    }

    public DatosContacto(UUID id, Paciente paciente, String telefono, String email, String calle,
                         String numero, String codigoPostal, String ciudad, String provincia, Integer version) {
        this.id = id;
        this.paciente = paciente;
        this.telefono = telefono;
        this.email = email;
        this.calle = calle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.provincia = provincia;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Integer getVersion() {
        return version;
    }
}