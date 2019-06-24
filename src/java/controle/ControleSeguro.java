package controle;

import dao.CarroDAO;
import dao.CorretorDAO;
import dao.SeguroDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Carro;
import modelo.Corretor;
import modelo.Seguro;
import util.Util;

@ManagedBean(name="controleSeguro")
@ViewScoped
public class ControleSeguro implements Serializable{
    private SeguroDAO<Seguro> dao;
    private Seguro obj;
    private CarroDAO<Carro> daoCarro;
    private CorretorDAO<Corretor> daoCorretor;
    
    public ControleSeguro() {
        dao = new SeguroDAO<>();
        daoCarro = new CarroDAO<>();
        daoCorretor = new CorretorDAO<>();
    }

    public SeguroDAO<Seguro> getDao() { return dao; }

    public void setDao(SeguroDAO<Seguro> dao) { this.dao = dao; }

    public Seguro getObj() { return obj; }

    public void setObj(Seguro obj) { this.obj = obj; }
    
    public CarroDAO<Carro> getDaoCarro() { return daoCarro; }

    public void setDaoCarro(CarroDAO<Carro> daoCarro) { this.daoCarro = daoCarro; }
    
    public CorretorDAO<Corretor> getDaoCorretor() { return daoCorretor; }

    public void setDaoCorretor(CorretorDAO<Corretor> daoCorretor) { this.daoCorretor = daoCorretor; }
    //##########################################################################
    public String listar(){
        return "privado/seguro/listar?faces-redirect=true";
    }
    
    public void novo(){
        obj = new Seguro();
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