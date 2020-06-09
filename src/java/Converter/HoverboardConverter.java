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
import Database.HoverboardDAO;
import Entity.Hoverboard;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value ="hoverboardConverter")
public class HoverboardConverter implements Converter {

    public HoverboardDAO hoverboarddao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getHoverboarddao().find(Long.valueOf(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Hoverboard b = (Hoverboard) value;
        return b.getHoverboard_id().toString();

    }

    public HoverboardDAO getHoverboarddao() {
        if (this.hoverboarddao == null) {
            this.hoverboarddao = new HoverboardDAO();
        }
        return hoverboarddao;
    }

}


