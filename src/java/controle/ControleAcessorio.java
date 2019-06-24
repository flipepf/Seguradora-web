package controle;

import dao.AcessorioDAO;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Acessorio;
import util.Util;

@ManagedBean(name="controleAcessorio")
@ViewScoped
public class ControleAcessorio implements Serializable{
    private AcessorioDAO<Acessorio> dao;
    private Acessorio obj;
    
    public ControleAcessorio() {
        dao = new AcessorioDAO<>();
    }

    public AcessorioDAO<Acessorio> getDao() { return dao; }

    public void setDao(AcessorioDAO<Acessorio> dao) { this.dao = dao; }

    public Acessorio getObj() { return obj; }

    public void setObj(Acessorio obj) { this.obj = obj; }
    
    //##########################################################################
    public String listar(){
        return "privado/acessorio/listar?faces-redirect=true";
    }
    
    public void novo(){
        obj = new Acessorio();
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