package Controller;

import Database.ScooterDAO;
import Database.KategoriDAO;
import Entity.Scooter;
import Entity.Kategori;
import Entity.Kullanici;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ScooterController implements Serializable {

    private Scooter scooter;
    private ScooterDAO scooterDAO;
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
        this.pageCount = (int) Math.ceil(this.getScooterdao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String netUcret() {
        int a = this.scooter.getUcret();
        int b = gun * a;
        return String.valueOf(b);
    }

    public void scooterata(Scooter scooter) {
        this.scooter = scooter;

    }

    public void clearForm() {
        this.scooter = new Scooter();
    }

    public void delete() {
        this.getScooterdao().delete(scooter);

        clearForm();
    }

    public void create() {

        this.getScooterdao().create(this.scooter);
        clearForm();
    }

    public void update() {
        this.getScooterdao().update(scooter);
        clearForm();
    }

    public List<Kategori> getKategoriById(Scooter scooter) {
        return getKategoriDAO().getScooterListByKategori(scooter.getScooter_id());
    }

    public List<Kategori> getKategoriler() {
        return this.getKategoriDAO().tumKategoriler();
    }

    public void iadeEt(Scooter scooter) {
        this.setKiralayan(this.getKullanicicontroller().getKullanicifilter());
        this.getScooterdao().iadeEt(scooter, this.getKiralayan());

    }

    public void kirala() {
        this.setKiralayan(this.getKullanicicontroller().getKullanicifilter());

        this.getScooterdao().kirala(scooter, this.getKiralayan());
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

    public List<Scooter> getScooterList() {
        return this.getScooterdao().getScooterList(page, pageSize);
    }

    public Scooter getScooter() {
        if (this.scooter == null) {
            this.scooter = new Scooter();
        }
        return scooter;
    }

    public void setScooter(Scooter scooter) {
        this.scooter = scooter;
    }

    public ScooterDAO getScooterdao() {
        if (this.scooterDAO == null) {
            this.scooterDAO = new ScooterDAO();
        }
        return scooterDAO;
    }

    public void setScooterdao(ScooterDAO scooterdao) {
        this.scooterDAO = scooterdao;
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
