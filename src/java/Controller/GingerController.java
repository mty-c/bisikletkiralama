package Controller;

import Database.GingerDAO;
import Database.KategoriDAO;
import Entity.Ginger;
import Entity.Kategori;
import Entity.Kullanici;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class GingerController implements Serializable {

    private Ginger ginger;
    private GingerDAO gingerDAO;
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
        this.pageCount = (int) Math.ceil(this.getGingerdao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String netUcret() {
        int a = this.ginger.getUcret();
        int b = gun * a;
        return String.valueOf(b);
    }

    public void gingerata(Ginger ginger) {
        this.ginger = ginger;

    }

    public void clearForm() {
        this.ginger = new Ginger();
    }

    public void delete() {
        this.getGingerdao().delete(ginger);

        clearForm();
    }

    public void create() {

        this.getGingerdao().create(this.ginger);
        clearForm();
    }

    public void update() {
        this.getGingerdao().update(ginger);
        clearForm();
    }

    public List<Kategori> getKategoriById(Ginger ginger) {
        return getKategoriDAO().getGingerListByKategori(ginger.getGinger_id());
    }

    public List<Kategori> getKategoriler() {
        return this.getKategoriDAO().tumKategoriler();
    }

    public void iadeEt(Ginger ginger) {
        this.setKiralayan(this.getKullanicicontroller().getKullanicifilter());
        this.getGingerdao().iadeEt(ginger, this.getKiralayan());

    }

    public void kirala() {
        this.setKiralayan(this.getKullanicicontroller().getKullanicifilter());

        this.getGingerdao().kirala(ginger, this.getKiralayan());
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

    public List<Ginger> getGingerList() {
        return this.getGingerdao().getGingerList(page, pageSize);
    }

    public Ginger getGinger() {
        if (this.ginger == null) {
            this.ginger = new Ginger();
        }
        return ginger;
    }

    public void setGinger(Ginger ginger) {
        this.ginger = ginger;
    }

    public GingerDAO getGingerdao() {
        if (this.gingerDAO == null) {
            this.gingerDAO = new GingerDAO();
        }
        return gingerDAO;
    }

    public void setGingerdao(GingerDAO gingerdao) {
        this.gingerDAO = gingerdao;
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
