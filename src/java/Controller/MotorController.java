package Controller;

import Database.MotorDAO;
import Database.KategoriDAO;
import Entity.Motor;
import Entity.Kategori;
import Entity.Kullanici;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class MotorController implements Serializable {

    private Motor motor;
    private MotorDAO motorDAO;
    private Kullanici kiralayan;
    private KategoriDAO kategoriDAO;
    @Inject
    KullaniciController kullanicicontroller;

    private final int admin = 1; //Rol vermek için yetkilendirme
    private final int guest = 0;

    private int gun; //Fiyat Hesabı İçin
    //NET ÜCRET HESAPLANACAK

    private int page = 1;
    private int pageSize = 12;
    private int pageCount;

    public void next() {
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getMotorDAO().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String netUcret() {
        int a = this.motor.getUcret();
        int b = gun * a;
        return String.valueOf(b);
    }

    public void motorata(Motor motor) {
        this.motor = motor;

    }

    public void clearForm() {
        this.motor = new Motor();
    }

    public void delete() {
        this.getMotorDAO().delete(motor);
        clearForm();
    }

    public void create() {
        this.getMotorDAO().create(motor);
        clearForm();
    }

    public void update() {
        this.getMotorDAO().update(motor);
        clearForm();
    }

    public List<Kategori> getKategoriById(Motor motor) {
        return getKategoriDAO().getMotorListByKategori(motor.getMotor_id());
    }

    public List<Kategori> getKategoriler() {
        return this.getKategoriDAO().tumKategoriler();
    }

    public void iadeEt(Motor motor) {
        this.setKiralayan(this.getKullanicicontroller().getKullanicifilter());
        this.getMotorDAO().iadeEt(motor, this.getKiralayan());

    }

    public void kirala() {
        this.setKiralayan(this.getKullanicicontroller().getKullanicifilter());

        this.getMotorDAO().kirala(motor, this.getKiralayan());
        clearForm();

    }

    public Kullanici getKiralayan() {

        return kiralayan;
    }

    public void setKiralayan(Kullanici kiralayan) {
        this.kiralayan = kiralayan;
    }

    public int getAdmin() {
        return admin;
    }

    public int getGuest() {
        return guest;
    }

    public List<Motor> getMotorList() {
        return this.getMotorDAO().getMotorList(page, pageSize);
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

    public MotorDAO getMotorDAO() {
        if (this.motorDAO == null) {
            this.motorDAO = new MotorDAO();
        }
        return motorDAO;
    }

    public void setMotorDAO(MotorDAO motorDAO) {
        this.motorDAO = motorDAO;
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

    public KategoriDAO getKategoriDAO() {
        if (kategoriDAO == null) {
            kategoriDAO = new KategoriDAO();
        }
        return kategoriDAO;
    }

    public void setKategoriDAO(KategoriDAO kategoriDAO) {
        this.kategoriDAO = kategoriDAO;
    }

}
