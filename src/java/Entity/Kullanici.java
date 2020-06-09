
package Entity;

import java.util.List;
import java.util.Objects;

public class Kullanici { //SABAH KULLANİCİ BİSİKLET MANY TO MANY YAPILACAK BİR BİSİKLETİN BİRDEN FAZLA KULLANICISI OLABİLİR..
    
    private Long kullanici_id;
    private String pass;
            
    private int role;
    private String name;
    private String surname;
    private boolean gender;
    
    private List<Bisiklet> bisikletler;
    private List<Motor> motorlar;
    private List<Atv> atvler;
    private List<Ginger> gingerlar;
    private List<Hoverboard> hoverboardlar;
    private List<Scooter> scooterlar;

    public Kullanici() {
        
    }

    public List<Ginger> getGingerlar() {
        return gingerlar;
    }

    public void setGingerlar(List<Ginger> gingerlar) {
        this.gingerlar = gingerlar;
    }

    public List<Hoverboard> getHoverboardlar() {
        return hoverboardlar;
    }

    public void setHoverboardlar(List<Hoverboard> hoverboardlar) {
        this.hoverboardlar = hoverboardlar;
    }

    public List<Scooter> getScooterlar() {
        return scooterlar;
    }

    public void setScooterlar(List<Scooter> scooterlar) {
        this.scooterlar = scooterlar;
    }

    public List<Atv> getAtvler() {
        return atvler;
    }

    public void setAtvler(List<Atv> atvler) {
        this.atvler = atvler;
    }

    public List<Motor> getMotorlar() {
        return motorlar;
    }

    public void setMotorlar(List<Motor> motorlar) {
        this.motorlar = motorlar;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
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
