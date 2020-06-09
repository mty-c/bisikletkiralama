/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;


import Database.DocumentDAO;
import Entity.Document;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "documentConverter")
public class DocumentConverter implements Converter{
    private DocumentDAO documentDao;

    public DocumentDAO getDocumentDao() {
        if(this.documentDao == null){
            this.documentDao = new DocumentDAO();
        }
        return documentDao;
    }

    public void setDocumentDao(DocumentDAO documentDao) {
        this.documentDao = documentDao;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String string) {
        return this.getDocumentDao().find(Integer.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        Document d = (Document) object;
        return String.valueOf(d.getDocumentId());
    }
    
}
