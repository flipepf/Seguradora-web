package controle;

import dao.CorretorDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Corretor;
import util.Util;

@ManagedBean(name="controleCorretor")
@ViewScoped
public class ControleCorretor implements Serializable{
    private CorretorDAO<Corretor> dao;
    private Corretor obj;
    
    public ControleCorretor() {
        dao = new CorretorDAO<>();
    }

    public CorretorDAO<Corretor> getDao() { return dao; }

    public void setDao(CorretorDAO<Corretor> dao) { this.dao = dao; }

    public Corretor getObj() { return obj; }

    public void setObj(Corretor obj) { this.obj = obj; }
    
    //##########################################################################
    public String listar(){
        return "privado/corretor/listar?faces-redirect=true";
    }
    
    public void novo(){
        obj = new Corretor();
    }
    
    public void salvar(){
        boolean persistiu;
        if (obj.getId() == null) persistiu = dao.persist(obj);
        else persistiu = dao.merge(obj);
        
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public void editar(Integer id){
        try {
            obj = dao.localizar(id);
        } catch (Exception e) {
            Util.mensagemErro("ERRO AO RECUPERAR OBJETO: " +Util.getMensagemErro(e));
        }
    }
    
    public void remover(Integer id){
        obj = dao.localizar(id);
        if(dao.remove(obj)) Util.mensagemInformacao(dao.getMensagem());
        else Util.mensagemErro(dao.getMensagem());
    }
    //##########################################################################
}