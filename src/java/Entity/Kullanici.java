package Entity;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author dazlakgandalf
 */
public class Kullanici { 
    
    private Long kullanici_id;
    private int pass;
            
    private int role;
    private String name;
    private String surname;
    private boolean gender;
    
    private List<Bisiklet> bisikletler;

    public Kullanici() {
        
    }

    public List<Bisiklet> getBisikletler() {
        return bisikletler;
    }

    public void setBisikletler(List<Bisiklet> bisikletler) {
        this.bisikletler = bisikletler;
    }

  
    
    
    public Long getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(Long kullanici_id) {
        this.kullanici_id = kullanici_id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.kullanici_id);
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
        final Kullanici other = (Kullanici) obj;
        if (!Objects.equals(this.kullanici_id, other.kullanici_id)) {
            return false;
        }
        return true;
    }

 

       
    
}
