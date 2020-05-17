package Controller;

import Database.MotorDAO;

import Entity.Kullanici;
import Entity.Motor;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Talha Yılmaz
 */
@Named
@SessionScoped
public class MotorController implements Serializable {

    private Motor motor;
    private MotorDAO motordao;
    private Kullanici kiralayan;
    @Inject
    KullaniciController kullanicicontroller;

    private int gun;

    public List<Motor> getMotorList() {
        return this.getMotordao().getMotorList();
    }

    public String netUcret() {
        int a = this.getMotor().getUcret();
        int b = gun * a;
        return String.valueOf(b);
    }

    public void motorAta(Motor motor) {
        this.motor = motor;

    }

    public void clearForm() {
        this.motor = new Motor();
    }

    public void delete() {
        this.getMotordao().delete(motor);
        clearForm();
    }

    public void create() {

        this.getMotordao().create(motor);
        clearForm();

    }

    public void update() {
        this.getMotordao().update(motor);
        clearForm();
    }

    public void iadeEt(Motor motor) {
        this.setKiralayan(this.getKullanicicontroller().getKullanicifilter());
        this.getMotordao().iadeEt(motor, this.getKiralayan());

    }

    public void kirala() {
        this.setKiralayan(this.getKullanicicontroller().getKullanicifilter());

        this.getMotordao().kirala(motor, this.getKiralayan());
        clearForm();

    }

    public Kullanici getKiralayan() {

        return kiralayan;
    }

    public void setKiralayan(Kullanici kiralayan) {
        this.kiralayan = kiralayan;
    }

    public Motor getMotor() {
        if (this.motor == null) {
            this.motor = new Motor();
        }
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public MotorDAO getMotordao() {
        if (this.motordao == null) {
            this.motordao = new MotorDAO();
        }
        return motordao;
    }

    public void setMotordao(MotorDAO motordao) {
        this.motordao = motordao;
    }

    public KullaniciController getKullanicicontroller() {
        if (this.kullanicicontroller == null) {
            this.kullanicicontroller = new KullaniciController();
        }
        return kullanicicontroller;
    }

    public void setKullanicicontroller(KullaniciController kullanicicontroller) {
        this.kullanicicontroller = kullanicicontroller;
    }

    public int getGun() {
        return gun;
    }

    public void setGun(int gun) {
        this.gun = gun;
    }

}
