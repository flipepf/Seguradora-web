package conversores;

import modelo.Pessoa;
import jpa.EntityManagerUtil;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="converterPessoa")
public class ConvertePessoa implements Serializable, Converter{

    //CONVERTE A INFORMAÇÃO DA TELA PARA O CONTROLE
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null || string.equalsIgnoreCase("SELECIONE UM REGISTRO")) return null;
       
        return EntityManagerUtil.getEntityManager().find(Pessoa.class, Integer.parseInt(string));
    }

    //BUSCA A INFORMAÇÃO PELO CONTROLE E CONVERTE PARA MOSTRAR NA TELA
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if ( o == null) return null;
        
        Pessoa p = (Pessoa) o;
        return p.getId().toString();
    }   
}