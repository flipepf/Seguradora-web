package controle;

import dao.SeguroDAO;
import dao.SinistroDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Seguro;
import modelo.Sinistro;
import util.Util;

@ManagedBean(name="controleSinistro")
@ViewScoped
public class ControleSinistro implements Serializable{
    private SinistroDAO<Sinistro> dao;
    private Sinistro obj;
    private SeguroDAO<Seguro> daoSeguro;
    
    public ControleSinistro() {
        dao = new SinistroDAO<>();
        daoSeguro = new SeguroDAO<>();
        
    }

    public SinistroDAO<Sinistro> getDao() { return dao; }

    public void setDao(SinistroDAO<Sinistro> dao) { this.dao = dao; }

    public Sinistro getObj() { return obj; }

    public void setObj(Sinistro obj) { this.obj = obj; }
    
    public SeguroDAO<Seguro> getDaoSeguro() { return daoSeguro; }

    public void setDaoSeguro(SeguroDAO<Seguro> daoSeguro) { this.daoSeguro = daoSeguro; }
    
    //##########################################################################
    public String listar(){
        return "privado/sinistro/listar?faces-redirect=true";
    }
    
    public void novo(){
        obj = new Sinistro();
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
}