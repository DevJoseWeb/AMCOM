package com.amcom.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Leitura e manutenção de cidades de um arquivo CSV feito com Java
 *
 * @author Jose RF Junior
 * @version 1.0
 * @since 18/07/2018
 */
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "cd_ibge")
    private Integer ibge;

    @Column(name = "nm_uf")
    private String uf;

    @Column(name = "nm_city")
    private String name;

    @Column(name = "fg_capital")
    private boolean capital;

    @Column(name = "nu_longitude")
    private BigDecimal longitude;

    @Column(name = "nu_latitute")
    private BigDecimal latitude;

    @Column(name = "nm_no_accents")
    private String noAccentsName;

    @Column(name = "nm_alternative")
    private String alternativenames;

    @Column(name = "nm_microregion")
    private String microRegion;

    @Column(name = "nm_mesoregion")
    private String mesoregion;

    @Column(name = "fg_excluded")
    private boolean excluded;

    public City() {
    }

    public City(String name) {
        this.id = id;
        this.ibge = ibge;
        this.uf = uf;
        this.name = name;
        this.capital = capital;
        this.longitude = longitude;
        this.latitude = latitude;
        this.noAccentsName = noAccentsName;
        this.alternativenames = alternativenames;
        this.microRegion = microRegion;
        this.mesoregion = mesoregion;
        this.excluded = excluded;
    }

    public Integer getIbge() {
        return ibge;
    }

    public void setIbge(Integer ibge) {
        this.ibge = ibge;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getNoAccentsName() {
        return noAccentsName;
    }

    public void setNoAccentsName(String noAccentsName) {
        this.noAccentsName = noAccentsName;
    }

    public String getAlternativenames() {
        return alternativenames;
    }

    public void setAlternativenames(String alternativenames) {
        this.alternativenames = alternativenames;
    }

    public String getMicroRegion() {
        return microRegion;
    }

    public void setMicroRegion(String microRegion) {
        this.microRegion = microRegion;
    }

    public String getMesoregion() {
        return mesoregion;
    }

    public void setMesoregion(String mesoregion) {
        this.mesoregion = mesoregion;
    }

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }
}
