package Entity;

/**
 *
 * @author 90549
 */
public class Atv {

    private Long id;
    private String marka;
    private String model;
    private int motorhacmi;
    private Kullanici kullanici;
    private int ucret;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
