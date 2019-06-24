package controle;

import dao.PessoaDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Pessoa;
import util.Util;

@ManagedBean(name="controlePessoa")
@ViewScoped
public class ControlePessoa implements Serializable{
    private PessoaDAO<Pessoa> dao;
    private Pessoa obj;
    
    public ControlePessoa() {
        dao = new PessoaDAO<>();
    }

    public PessoaDAO<Pessoa> getDao() { return dao; }

    public void setDao(PessoaDAO<Pessoa> dao) { this.dao = dao; }

    public Pessoa getObj() { return obj; }

    public void setObj(Pessoa obj) { this.obj = obj; }
    
    //##########################################################################
    public String listar(){
        return "privado/pessoa/listar?faces-redirect=true";
    }
    
    public void novo(){
        obj = new Pessoa();
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