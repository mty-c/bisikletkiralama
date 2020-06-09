
package Entity;

import java.util.List;
import java.util.Objects;


public class Ginger {

    private Long ginger_id;
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


    public Long getGinger_id() {
        return ginger_id;
    }

    public void setGinger_id(Long ginger_id) {
        this.ginger_id = ginger_id;
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
        hash = 23 * hash + Objects.hashCode(this.ginger_id);
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
        final Ginger other = (Ginger) obj;
        if (!Objects.equals(this.ginger_id, other.ginger_id)) {
            return false;
        }
        return true;
    }

}
