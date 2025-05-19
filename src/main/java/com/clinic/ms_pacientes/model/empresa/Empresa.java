package com.clinic.ms_pacientes.model.empresa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "CLINIC_DATOS_EMPRESA")
@Entity(name = "clinic_DatosEmpresa")
public class Empresa {

    @Id
    private UUID id;

    @NotNull
    @Column(name = "NOMBRE", nullable = false, length = 150)
    private String nombre;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "NIF", length = 9)
    private String nif;

    @Column(name = "TELEFONO", length = 9)
    private String telefono;

    @Column(name = "EMAIL", length = 75)
    private String email;

    @Version
    @Column(name = "VERSION", nullable = false)
    protected Integer version;

    public UUID getId() { return id; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}