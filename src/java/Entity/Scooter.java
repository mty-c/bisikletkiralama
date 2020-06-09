
package Entity;

import java.util.List;
import java.util.Objects;


public class Scooter {
    private Long scooter_id;
    private String marka;
    private String model;
    private String boyut;
    private Kullanici kullanici;
    private int ucret;
    private List<Kategori> kategoriList;

    public List<Kategori> getKategoriList() {
        return kategoriList;
    }

    public void setKategoriList(List<Kategori> kategoriList) {
        this.kategoriList = kategoriList;
    }

    
    public Long getScooter_id() {
        return scooter_id;
    }

    public void setScooter_id(Long scooter_id) {
        this.scooter_id =scooter_id;
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

    public String getBoyut() {
        return boyut;
    }

    public void setBoyut(String boyut) {
        this.boyut = boyut;
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
        hash = 23 * hash + Objects.hashCode(this.scooter_id);
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
        final Scooter other = (Scooter) obj;
        if (!Objects.equals(this.scooter_id, other.scooter_id)) {
            return false;
        }
        return true;
    }
    
}
