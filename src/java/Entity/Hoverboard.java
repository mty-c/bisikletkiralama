/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author 90549
 */
public class Hoverboard implements Serializable {
    
    private Long hoverboard_id;
    private String marka;
    private String model;
    private String renk;
    private Kullanici kullanici;
    private int ucret;
    private List<Kategori> kategoriList;

    public List<Kategori> getKategoriList() {
        return kategoriList;
    }

    public void setKategoriList(List<Kategori> kategoriList) {
        this.kategoriList = kategoriList;
    }

    public Long getHoverboard_id() {
        return hoverboard_id;
    }

    public void setHoverboard_id(Long hoverboard_id) {
        this.hoverboard_id = hoverboard_id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }

    public Kullanici getKullanici() {
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    public int getUcret() {
        return ucret;
    }

    public void setUcret(int ucret) {
        this.ucret = ucret;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.hoverboard_id);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hoverboard other = (Hoverboard) obj;
        if (!Objects.equals(this.hoverboard_id, other.hoverboard_id)) {
            return false;
        }
        return true;
    }
    
}
