
package Controller;

import Database.AtvDAO;
import Entity.Kullanici;
import Entity.Atv;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@SessionScoped
public class AtvController implements Serializable {
    private Atv atv;
    private AtvDAO atvdao;
    private Kullanici kiralayan;
    @Inject
    KullaniciController kullanicicontroller;

    
    private int gun; 
    

    public List<Atv> getAtvList() {
        return this.getAtvdao().getAtvList();
    }
    
    public String netUcret()
    {
        int a=this.getAtv().getUcret();
        int b=gun*a;
        return String.valueOf(b);
    }
    

    public void atvAta(Atv atv)
    {  
        this.atv=atv;
        
    }
    
    public void clearForm() {
        this.atv = new Atv();
    }


    public void delete() {
        this.getAtvdao().delete(atv);
        clearForm();
    }

    public void create() {
    
        this.getAtvdao().create(atv);
        clearForm();

    }
    
    public void update(){
        this.getAtvdao().update(atv);
        clearForm();
    }
  

    public void iadeEt(Atv atv) {
        this.setKiralayan(this.getKullanicicontroller().getKullanicifilter());
        this.getAtvdao().iadeEt(atv, this.getKiralayan());

    }

    public void kirala() {
        this.setKiralayan(this.getKullanicicontroller().getKullanicifilter());

        this.getAtvdao().kirala(atv, this.getKiralayan());
        clearForm();

    }

    public Kullanici getKiralayan() {

        return kiralayan;
    }

    public void setKiralayan(Kullanici kiralayan) {
        this.kiralayan = kiralayan;
    }

    public Atv getAtv() {
        if(this.atv==null)
            this.atv=new Atv();
        return atv;
    }

    public void setAtv(Atv atv) {
        this.atv = atv;
    }

    public AtvDAO getAtvdao() {
        if(this.atvdao==null)
            this.atvdao=new AtvDAO();
        return atvdao;
    }

    public void setAtvdao(AtvDAO atvdao) {
        this.atvdao = atvdao;
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
