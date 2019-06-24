package controle;

import dao.AcessorioDAO;
import dao.CarroDAO;
import dao.PessoaDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Acessorio;
import modelo.Carro;
import modelo.Pessoa;
import util.Util;

@ManagedBean(name="controleCarro")
@ViewScoped
public class ControleCarro implements Serializable{
    private CarroDAO<Carro> dao;
    private Carro obj;
    private PessoaDAO<Pessoa> daoPessoa;
    private AcessorioDAO<Acessorio> daoAcessorio;
    private Acessorio acessorio;

    public ControleCarro() {
         dao = new CarroDAO<>();
         daoPessoa = new PessoaDAO<>();
         daoAcessorio = new AcessorioDAO<>();
    }

    public CarroDAO<Carro> getDao() { return dao; }

    public void setDao(CarroDAO<Carro> dao) { this.dao = dao; }

    public Carro getObj() { return obj; }

    public void setObj(Carro obj) { this.obj = obj; }

    public PessoaDAO getDaoPessoa() { return daoPessoa; }

    public void setDaoPessoa(PessoaDAO daoPessoa) { this.daoPessoa = daoPessoa; }

    public AcessorioDAO<Acessorio> getDaoAcessorio() { return daoAcessorio; }

    public void setDaoAcessorio(AcessorioDAO<Acessorio> daoAcessorio) { this.daoAcessorio = daoAcessorio; }

    public Acessorio getAcessorio() { return acessorio; }

    public void setAcessorio(Acessorio acessorio) { this.acessorio = acessorio; }
    //########################################################################
        public String listar(){
        return "privado/carro/listar?faces-redirect=true";
    }
    
    public void novo(){
        obj = new Carro();
    }
    
    public void salvar(){
        boolean persistiu;
        if (obj.getId() == null) persistiu = dao.persist(obj);
        else persistiu = dao.merge(obj);
        
        if (persistiu) Util.mensagemInformacao(dao.getMensagem());
        else Util.mensagemErro(dao.getMensagem());
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
    //#########################################################################
    public void addAcessorio(){
       if (acessorio != null){
            if (!obj.getAcessorios().contains(acessorio)){
               obj.adicionarAcessorio(acessorio);
               Util.mensagemInformacao("ACESSÓRIO ADICIONADO COM SUCESSO!");
            } else {
                Util.mensagemErro("ACESSÓRIO JÁ FOI ADICIONADO A ESTE CARRO");
            }
       }
   }
    
    public void removerAcessorio(int index){
        Object[] lista = obj.getAcessorios().toArray();
        Acessorio a = (Acessorio) lista[index];
        obj.getAcessorios().remove(a);
        Util.mensagemInformacao("ACESSÓRIO REMOVIDO COM SUCESSO");
    }
}