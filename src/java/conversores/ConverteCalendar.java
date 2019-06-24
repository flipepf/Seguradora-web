package conversores;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="converterCalendar")
public class ConverteCalendar implements Serializable, Converter{
     
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //CONVERTE A INFORMAÇÃO DA TELA PARA O CONTROLE
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String stringData) {
        if(stringData!=null){
           try {
               Calendar dt = Calendar.getInstance();
               dt.setTime(sdf.parse(stringData));
               return dt;
           } catch (ParseException ex) {
               return null;
           }
       } else {
           return null;
       }
    }

    //BUSCA A INFORMAÇÃO PELO CONTROLE E CONVERTE PARA MOSTRAR NA TELA
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Calendar dt = (Calendar) o;
        return sdf.format(dt.getTime());
    }     
}