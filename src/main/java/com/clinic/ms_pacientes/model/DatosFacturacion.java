package com.clinic.ms_pacientes.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "CLINIC_DATOS_FACTURACION")
public class DatosFacturacion {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "PACIENTE_ID", nullable = false)
    private Paciente paciente;

    @Column(name = "NIF", length = 9)
    private String nif;

    @Column(name = "NOMBRE", length = 50)
    private String nombre;

    @Column(name = "APELLIDOS", length = 100)
    private String apellidos;

    @Column(name = "CALLE", length = 150)
    private String calle;

    @Column(name = "NUMERO", length = 50)
    private String numero;

    @Column(name = "CIUDAD", length = 50)
    private String ciudad;

    @Column(name = "PROVINCIA", length = 50)
    private String provincia;

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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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
}