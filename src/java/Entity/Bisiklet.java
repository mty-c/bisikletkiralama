/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


import java.util.Objects;

/**
 *
 * @author HP
 */
public class Bisiklet {
    private Long bisiklet_id;
    private String marka;
    private String model;
    private int ucret;
    private Kullanici kullanici;
  
    
    
    public Long getBisiklet_id() {
        return bisiklet_id;
    }

    public void setBisiklet_id(Long bisiklet_id) {
        this.bisiklet_id = bisiklet_id;
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
        hash = 23 * hash + Objects.hashCode(this.bisiklet_id);
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
        final Bisiklet other = (Bisiklet) obj;
        if (!Objects.equals(this.bisiklet_id, other.bisiklet_id)) {
            return false;
        }
        return true;
    }


   
    
    
}
