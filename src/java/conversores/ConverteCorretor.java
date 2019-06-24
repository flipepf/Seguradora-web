package conversores;

import modelo.Corretor;
import jpa.EntityManagerUtil;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="converterCorretor")
public class ConverteCorretor implements Serializable, Converter{

    //CONVERTE A INFORMAÇÃO DA TELA PARA O CONTROLE
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null || string.equalsIgnoreCase("SELECIONE UM REGISTRO")) return null;
       
        return EntityManagerUtil.getEntityManager().find(Corretor.class, Integer.parseInt(string));
    }

    //BUSCA A INFORMAÇÃO PELO CONTROLE E CONVERTE PARA MOSTRAR NA TELA
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if ( o == null) return null;
        
        Corretor c = (Corretor) o;
        return c.getId().toString();
    }   
}