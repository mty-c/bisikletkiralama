/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Database.KategoriDAO;
import Database.KullaniciDAO;
import Entity.Kategori;
import Entity.Kullanici;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "kategoriConverter")
public class KategoriConverter implements Converter {

    public KategoriDAO katdao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getKategoriDAO().find(Long.valueOf(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Kategori k = (Kategori) value;

        return k.getKategori_id().toString();

    }

    public KategoriDAO getKategoriDAO() {
        if (this.katdao == null) {
            this.katdao = new KategoriDAO();
        }
        return katdao;
    }

}
