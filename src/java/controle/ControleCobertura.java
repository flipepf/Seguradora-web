package controle;

import dao.CoberturaDAO;
import dao.SeguroDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Cobertura;
import modelo.Seguro;
import util.Util;

@ManagedBean(name="controleCobertura")
@ViewScoped
public class ControleCobertura implements Serializable{
    private CoberturaDAO<Cobertura> dao;
    private Cobertura obj;
    private SeguroDAO<Seguro> daoSeguro;
    
    public ControleCobertura() {
        dao = new CoberturaDAO<>();
        daoSeguro = new SeguroDAO<>();
    }

    public CoberturaDAO<Cobertura> getDao() { return dao; }

    public void setDao(CoberturaDAO<Cobertura> dao) { this.dao = dao; }

    public Cobertura getObj() { return obj; }

    public void setObj(Cobertura obj) { this.obj = obj; }
    
    public SeguroDAO<Seguro> getDaoSeguro() { return daoSeguro; }

    public void setDaoSeguro(SeguroDAO<Seguro> daoSeguro) { this.daoSeguro = daoSeguro; }
    //##########################################################################
    public String listar(){
        return "privado/cobertura/listar?faces-redirect=true";
    }
    
    public void novo(){
        obj = new Cobertura();
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