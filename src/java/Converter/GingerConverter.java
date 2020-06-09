/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

/**
 *
 * @author 90549
 */
import Database.GingerDAO;
import Entity.Ginger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value ="gingerConverter")
public class GingerConverter implements Converter {

    public GingerDAO gingerdao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getGingerdao().find(Long.valueOf(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Ginger b = (Ginger) value;
        return b.getGinger_id().toString();

    }

    public GingerDAO getGingerdao() {
        if (this.gingerdao == null) {
            this.gingerdao = new GingerDAO();
        }
        return gingerdao;
    }

}


