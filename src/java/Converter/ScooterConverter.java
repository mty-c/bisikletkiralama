
package Converter;


import Database.ScooterDAO;
import Entity.Scooter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value ="scooterConverter")
public class ScooterConverter implements Converter {

    public ScooterDAO scooterdao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getScooterdao().find(Long.valueOf(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Scooter b = (Scooter) value;
        return b.getScooter_id().toString();

    }

    public ScooterDAO getScooterdao() {
        if (this.scooterdao == null) {
            this.scooterdao = new ScooterDAO();
        }
        return scooterdao;
    }

}

