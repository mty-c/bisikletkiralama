package Controller;

import Database.KullaniciDAO;
import Entity.Bisiklet;
import Entity.Kullanici;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author dazlakgandalf
 */
@Named
@SessionScoped
public class KullaniciController implements Serializable {

    private Kullanici kullanici;
    private KullaniciDAO kullanicidao;
    private Kullanici kullanicifilter;
    
    @Inject
    private BisikletController bisikletcontroller;
    
    private final int admin=1;
    private final int guest=0;

    public Kullanici sahipsiz()
    {
        return null;
    }
    
    public void kullaniciata(Kullanici kullanici){
        if(kullanici.getKullanici_id()==1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bu Kullanici Silinemez"));
        }
        else
        this.kullanici=kullanici;
    }
    
    public void delete(){
        this.getKullanicidao().delete(this.kullanici);
        this.kullanici=new Kullanici();
    }
    
    public void update(){
        this.getKullanicidao().update(this.kullanici);
        this.kullanici=new Kullanici();
    }
    
    
    public String login() {
      
    
        this.setKullanicifilter(this.getKullanicidao().login(kullanici));
        this.kullanici=new Kullanici();
   
        
        if (this.getKullanicifilter() != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Giriş Başarılı"));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", this.getKullanicifilter());
            return "index?faces-redirect=true";
        

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("HATALI DENEME"));
            return "Login.xhtml";
        }
        
    }

    public List<Kullanici> getListe()
    {
        return this.getKullanicidao().getKullaniciList();
    }
    
    public void create()
    {
      this.getKullanicidao().create(this.kullanici);
      this.kullanici=new Kullanici();
    }
    
    
    
    public void register()
    {
        this.getKullanicidao().register(this.kullanici);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Kayıt Başarılı"));
        
    }

    public Kullanici getKullanicifilter() {
        
        return kullanicifilter;
    }

    public void setKullanicifilter(Kullanici kullanicifilter) {
        this.kullanicifilter = kullanicifilter;
    }
    
    

    public Kullanici getKullanici() {
        if (this.kullanici == null) {
            this.kullanici = new Kullanici();
        }
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    public KullaniciDAO getKullanicidao() {
        if (this.kullanicidao == null) {
            this.kullanicidao = new KullaniciDAO();
        }
        return kullanicidao;
    }

    public void setKullanicidao(KullaniciDAO kullanicidao) {
        this.kullanicidao = kullanicidao;
    }

    public BisikletController getBisikletcontroller() {
        if(this.bisikletcontroller==null)
            this.bisikletcontroller=new BisikletController();
        return bisikletcontroller;
    }

    public void setBisikletcontroller(BisikletController bisikletcontroller) {
        this.bisikletcontroller = bisikletcontroller;
    }

    public int getAdmin() {
        return admin;
    }

    public int getGuest() {
        return guest;
    }

    
    
    
}
