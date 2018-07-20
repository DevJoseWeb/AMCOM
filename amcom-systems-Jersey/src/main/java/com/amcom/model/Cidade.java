package com.amcom.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe que representa uma cidade. Optei por usar nomes das propriedades
 * em português apenas para demonstrar uma anotação JPA a mais.
 *
 * @author Jose RF Junior
 *
 */
@Entity
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cidade implements Serializable {
    private static final long serialVersionUID = 5385195064403945183L;

    @Id
    @Column(name = "ibge_id")
    private BigInteger idIbge;
    @Column(name = "uf")
    private String estado;
    @Column(name = "name")
    private String nome;
    @Column(name = "no_accents")
    private String nomeSemAcento;
    @Column(name = "alternative_names")
    private String nomeAlternativo;
    private String capital;
    @Column(name = "microregion")
    private String microregiao;
    @Column(name = "mesoregion")
    private String mesoregiao;
    @Column(name = "lat")
    private Float latitude;
    @Column(name = "lon")
    private Float longitude;

    public BigInteger getIdIbge() {
        return idIbge;
    }

    public void setIdIbge(BigInteger idIbge) {
        this.idIbge = idIbge;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeSemAcento() {
        return nomeSemAcento;
    }

    public void setNomeSemAcento(String nomeSemAcento) {
        this.nomeSemAcento = nomeSemAcento;
    }

    public String getNomeAlternativo() {
        return nomeAlternativo;
    }

    public void setNomeAlternativo(String nomeAlternativo) {
        this.nomeAlternativo = nomeAlternativo;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getMicroregiao() {
        return microregiao;
    }

    public void setMicroregiao(String microregiao) {
        this.microregiao = microregiao;
    }

    public String getMesoregiao() {
        return mesoregiao;
    }

    public void setMesoregiao(String mesoregiao) {
        this.mesoregiao = mesoregiao;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
