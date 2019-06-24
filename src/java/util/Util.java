package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Util {
    
    public static String getMensagemErro(Exception e){
        while(e.getCause() != null){
            e = (Exception) e.getCause();
        }
        String retorno = e.getMessage();
        if (retorno.contains("foreign key")) 
            retorno = "REGISTRO NÃO PODE SER EXCLUIDO POR POSSUIR REFERENCIA A OUTROS REGISTROS";
        return retorno;
    }
    
    public static void mensagemInformacao (String msg){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg,"");
        facesContext.addMessage(null, mensagem);
    }
    
    public static void mensagemErro(String msg){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,"");
        facesContext.addMessage(null, mensagem);
    }
}