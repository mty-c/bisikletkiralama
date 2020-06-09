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
import Database.MotorDAO;
import Entity.Motor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value ="motorConverter")
public class MotorConverter implements Converter {

    public MotorDAO motordao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getMotordao().find(Long.valueOf(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Motor b = (Motor) value;
        return b.getMotor_id().toString();

    }

    public MotorDAO getMotordao() {
        if (this.motordao == null) {
            this.motordao = new MotorDAO();
        }
        return motordao;
    }

}

