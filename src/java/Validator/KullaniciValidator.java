package Validator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named("kullanicivalidator")
@SessionScoped
public class KullaniciValidator implements Serializable {

    private Collection<FacesMessage> msgList = new ArrayList<>();

    public boolean validateAd(FacesContext fc, UIComponent uı, Object v) {

        boolean isValid = true;
        msgList.clear();

        String value = (String) v;
        if (value.equals("")) {
            msgList.add(new FacesMessage("Lütfen Adınızı Giriniz ! "));
            isValid = false;
        } else if (value.length() < 3 || value.length() > 50) {
            msgList.add(new FacesMessage("Ad 3'den Küçük 50 den Büyük Olamaz!"));
            isValid = false;
        }

        if (!isValid) {
            throw new ValidatorException(msgList);
        } else {
            return true;
        }

    }

    public boolean validateSoyad(FacesContext fc, UIComponent uı, Object v) {

        boolean isValid = true;
        msgList.clear();

        String value = (String) v;
        if (value.equals("")) {
            msgList.add(new FacesMessage("Lütfen Soyadınızı Giriniz  ! "));
            isValid = false;
        } else if (value.length() < 2 || value.length() > 30) {
            msgList.add(new FacesMessage("Soyad 2'den Küçük 30 den Büyük Olamaz!"));
            isValid = false;
        }

        if (!isValid) {
            throw new ValidatorException(msgList);
        } else {
            return true;
        }

    }
    public boolean validateSifre(FacesContext fc, UIComponent uı, Object v) {

        boolean isValid = true;
        msgList.clear();

        String value = (String) v;
        if (value.equals("")) {
            msgList.add(new FacesMessage("Lütfen Şifrenizi Giriniz  ! "));
            isValid = false;
        } else if (value.length() < 5) {
            msgList.add(new FacesMessage("Şifreniz En Az 5 Haneli Olmalı!"));
            isValid = false;
        }

        if (!isValid) {
            throw new ValidatorException(msgList);
        } else {
            return true;
        }

    }
    public boolean validateCinsiyet(FacesContext fc, UIComponent uı, Object v) {
        boolean isValid = true;
        msgList.clear();

        String value = (String) v;

        if (value == null || value.equals("") || value.equalsIgnoreCase("Seçiniz")) {
            msgList.add(new FacesMessage("Lütfen Cinsiyet Seçiniz!"));
            isValid = false;
        }
        if (!isValid) {
            throw new ValidatorException(msgList);

        } else {
            return true;
        }
    }
}
