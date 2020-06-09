
package Entity;

import java.util.List;
import java.util.Objects;


public class Kategori {
    private Long kategori_id;
    private String kategori_ad;
    private List<Bisiklet> bisikletList;
    private List<Motor> motorList;
    private List<Atv> atvList;
    private List<Scooter> scooterList;
    private List<Ginger> gingerList;
    private List<Hoverboard> hoverboardList;

    public Kategori() {
    }

    public Kategori(Long kategori_id, String kategori_ad) {
        this.kategori_id = kategori_id;
        this.kategori_ad = kategori_ad;
    }

    public List<Atv> getAtvList() {
        return atvList;
    }

    public void setAtvList(List<Atv> atvList) {
        this.atvList = atvList;
    }

    public List<Scooter> getScooterList() {
        return scooterList;
    }

    public void setScooterList(List<Scooter> scooterList) {
        this.scooterList = scooterList;
    }

    public List<Ginger> getGingerList() {
        return gingerList;
    }

    public void setGingerList(List<Ginger> gingerList) {
        this.gingerList = gingerList;
    }

    public List<Hoverboard> getHoverboardList() {
        return hoverboardList;
    }

    public void setHoverboardList(List<Hoverboard> hoverboardList) {
        this.hoverboardList = hoverboardList;
    }

    public List<Motor> getMotorList() {
        return motorList;
    }

    public void setMotorList(List<Motor> motorList) {
        this.motorList = motorList;
    }

    public List<Bisiklet> getBisikletList() {
        return bisikletList;
    }

    public void setBisikletList(List<Bisiklet> bisikletList) {
        this.bisikletList = bisikletList;
    }

    
    
    public Long getKategori_id() {
        return kategori_id;
    }

    public void setKategori_id(Long kategori_id) {
        this.kategori_id = kategori_id;
    }

    public String getKategori_ad() {
        return kategori_ad;
    }

    public void setKategori_ad(String kategori_ad) {
        this.kategori_ad = kategori_ad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.kategori_id);
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
        final Kategori other = (Kategori) obj;
        if (!Objects.equals(this.kategori_id, other.kategori_id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
