package Converter;

/**
 *
 * @author Talha Yılmaz
 */
import Database.MotorDAO;
import Entity.Motor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "motorConverter")
public class MotorConverter implements Converter {

    public MotorDAO motorDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getMotorDAO().find(Long.valueOf(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Motor b = (Motor) value;
        return b.getId().toString();

    }

    public MotorDAO getMotorDAO() {
        if (this.motorDAO == null) {
            this.motorDAO = new MotorDAO();
        }
        return motorDAO;
    }

}
