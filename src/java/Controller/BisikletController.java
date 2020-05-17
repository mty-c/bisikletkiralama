/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.BisikletDAO;
import Entity.Bisiklet;
import Entity.Kullanici;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author HP
 */
@Named
@SessionScoped
public class BisikletController implements Serializable {

    private Bisiklet bisiklet;
    private BisikletDAO bisikletdao;
    private Kullanici kiralayan;
    @Inject
    KullaniciController kullanicicontroller;

    private final int admin = 1; //Rol vermek için yetkilendirme
    private final int guest = 0;
    
    private int gun; //Fiyat Hesabı İçin
    //NET ÜCRET HESAPLANACAK
    
    private int page=1;
    private int pageSize=12;
    private int pageCount;
    
    public void next(){
        if(this.page==this.getPageCount())
            this.page=1;
        else
            this.page++;
    }
    
    public void previous(){
        if(this.page==1)
            this.page=this.getPageCount();
        else
            this.page--;
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
        this.pageCount=(int)Math.ceil(this.getBisikletdao().count()/(double)pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    
    
    public String netUcret()
    {
        int a=this.bisiklet.getUcret();
        int b=gun*a;
        return String.valueOf(b);
    }
    

    public void bisikletata(Bisiklet bisiklet)
    {  
        this.bisiklet=bisiklet;
        
    }
    
    public void clearForm() {
        this.bisiklet = new Bisiklet();
    }


    public void delete() {
        this.getBisikletdao().delete(bisiklet);
        clearForm();
    }

    public void create() {
    
        this.getBisikletdao().create(bisiklet);
        clearForm();

    }
    
    public void update(){
        this.getBisikletdao().update(bisiklet);
        clearForm();
    }
           
  

    public void iadeEt(Bisiklet bisiklet) {
        this.setKiralayan(this.getKullanicicontroller().getKullanicifilter());
        this.getBisikletdao().iadeEt(bisiklet, this.getKiralayan());

    }

    public void kirala() {
        this.setKiralayan(this.getKullanicicontroller().getKullanicifilter());

        this.getBisikletdao().kirala(bisiklet, this.getKiralayan());
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

    public List<Bisiklet> getBisikletList() {
        return this.getBisikletdao().getBisikletList(page,pageSize);
    }

    public Bisiklet getBisiklet() {
        if (this.bisiklet == null) {
            this.bisiklet = new Bisiklet();
        }
        return bisiklet;
    }

    public void setBisiklet(Bisiklet bisiklet) {
        this.bisiklet = bisiklet;
    }

    public BisikletDAO getBisikletdao() {
        if (this.bisikletdao == null) {
            this.bisikletdao = new BisikletDAO();
        }
        return bisikletdao;
    }

    public void setBisikletdao(BisikletDAO bisikletdao) {
        this.bisikletdao = bisikletdao;
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



