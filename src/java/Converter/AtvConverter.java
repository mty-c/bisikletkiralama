
package Converter;

import Database.AtvDAO;
import Entity.Atv;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value ="atvConverter")
public class AtvConverter implements Converter {

    public AtvDAO atvdao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getAtvdao().find(Long.valueOf(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Atv b = (Atv) value;
        return b.getAtv_id().toString();

    }

    public AtvDAO getAtvdao() {
        if (this.atvdao == null) {
            this.atvdao = new AtvDAO();
        }
        return atvdao;
    }

}

