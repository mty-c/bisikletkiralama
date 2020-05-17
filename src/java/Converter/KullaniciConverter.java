package Converter;

import Database.KullaniciDAO;
import Entity.Kullanici;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="kullaniciConverter")
public class KullaniciConverter implements Converter {
    
 
    public KullaniciDAO kullanicidao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getKullanicidao().find(Long.valueOf(value));
        

           }
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
         Kullanici k=(Kullanici) value;
         
         return k.getKullanici_id().toString();
         
    }

    public KullaniciDAO getKullanicidao(){
        if(this.kullanicidao==null)
            this.kullanicidao=new KullaniciDAO();
        return kullanicidao;
    }

    
}
