
package Converter;

import Database.BisikletDAO;
import Entity.Bisiklet;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value ="bisikletConverter")
public class BisikletConverter implements Converter {

    public BisikletDAO bisikletdao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getBisikletdao().find(Long.valueOf(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Bisiklet b = (Bisiklet) value;
        return b.getBisiklet_id().toString();

    }

    public BisikletDAO getBisikletdao() {
        if (this.bisikletdao == null) {
            this.bisikletdao = new BisikletDAO();
        }
        return bisikletdao;
    }

}
