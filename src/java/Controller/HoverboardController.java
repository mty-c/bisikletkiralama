package Controller;

import Database.HoverboardDAO;
import Database.KategoriDAO;
import Entity.Hoverboard;
import Entity.Kategori;
import Entity.Kullanici;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class HoverboardController implements Serializable {

    private Hoverboard hoverboard;
    private HoverboardDAO hoverboardDAO;
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
        this.pageCount = (int) Math.ceil(this.getHoverboarddao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String netUcret() {
        int a = this.hoverboard.getUcret();
        int b = gun * a;
        return String.valueOf(b);
    }

    public void hoverboardata(Hoverboard hoverboard) {
        this.hoverboard = hoverboard;

    }

    public void clearForm() {
        this.hoverboard = new Hoverboard();
    }

    public void delete() {
        this.getHoverboarddao().delete(hoverboard);

        clearForm();
    }

    public void create() {

        this.getHoverboarddao().create(this.hoverboard);
        clearForm();
    }

    public void update() {
        this.getHoverboarddao().update(hoverboard);
        clearForm();
    }

    public List<Kategori> getKategoriById(Hoverboard hoverboard) {
        return getKategoriDAO().getHoverboardListByKategori(hoverboard.getHoverboard_id());
    }

    public List<Kategori> getKategoriler() {
        return this.getKategoriDAO().tumKategoriler();
    }

    public void iadeEt(Hoverboard hoverboard) {
        this.setKiralayan(this.getKullanicicontroller().getKullanicifilter());
        this.getHoverboarddao().iadeEt(hoverboard, this.getKiralayan());

    }

    public void kirala() {
        this.setKiralayan(this.getKullanicicontroller().getKullanicifilter());

        this.getHoverboarddao().kirala(hoverboard, this.getKiralayan());
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

    public List<Hoverboard> getHoverboardList() {
        return this.getHoverboarddao().getHoverboardList(page, pageSize);
    }

    public Hoverboard getHoverboard() {
        if (this.hoverboard == null) {
            this.hoverboard = new Hoverboard();
        }
        return hoverboard;
    }

    public void setHoverboard(Hoverboard hoverboard) {
        this.hoverboard = hoverboard;
    }

    public HoverboardDAO getHoverboarddao() {
        if (this.hoverboardDAO == null) {
            this.hoverboardDAO = new HoverboardDAO();
        }
        return hoverboardDAO;
    }

    public void setHoverboarddao(HoverboardDAO hoverboarddao) {
        this.hoverboardDAO = hoverboarddao;
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
