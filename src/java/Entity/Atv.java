package Entity;

import java.util.List;
import java.util.Objects;


public class Atv {

    private Long atv_id;
    private String marka;
    private String model;
    private int motorhacmi;
    private Kullanici kullanici;
    private int ucret;
    private List<Kategori> kategoriList;

    public List<Kategori> getKategoriList() {
        return kategoriList;
    }

    public void setKategoriList(List<Kategori> kategoriList) {
        this.kategoriList = kategoriList;
    }
    public Long getAtv_id() {
        return atv_id;
    }

    public void setAtv_id(Long atv_id) {
        this.atv_id = atv_id;
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

    public int getMotorhacmi() {
        return motorhacmi;
    }

    public void setMotorhacmi(int motorhacmi) {
        this.motorhacmi = motorhacmi;
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
        hash = 23 * hash + Objects.hashCode(this.atv_id);
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
        final Atv other = (Atv) obj;
        if (!Objects.equals(this.atv_id, other.atv_id)) {
            return false;
        }
        return true;
    }
}
