package com.duxsoftware.apirest.apirest.DTO;

import jakarta.validation.constraints.NotBlank;

public class EquipoRequest {
    @NotBlank
    private String nombre;
    @NotBlank
    private String liga;
    @NotBlank
    private String pais;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
