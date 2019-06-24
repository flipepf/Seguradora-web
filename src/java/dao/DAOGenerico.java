package dao;

import util.Util;
import jpa.EntityManagerUtil;
import java.util.List;
import javax.persistence.EntityManager;

public class DAOGenerico<TIPO> {
    private List<TIPO> listaObjetos; 
    private List<TIPO> listaTodos;
    protected EntityManager em;
    protected Class classePersistente;
    private String mensagem;
    protected String ordem = "id";
    protected String filtro = "";
    protected Integer maximoObjetos = 10;
    protected Integer posicaoAtual = 0;
    protected Integer totalObjetos =0;
    
    
    
    public DAOGenerico(){ em = EntityManagerUtil.getEntityManager(); }

    public String getMensagem() { return mensagem; }

    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    
    public Integer getMaximoObjetos() {  return maximoObjetos; }

    public void setMaximoObjetos(Integer maximoObjetos) { this.maximoObjetos = maximoObjetos;    }

    public Integer getPosicaoAtual() { return posicaoAtual; }

    public void setPosicaoAtual(Integer posicaoAtual) { this.posicaoAtual = posicaoAtual;    }

    public Integer getTotalObjetos() { return totalObjetos;    }

    public void setTotalObjetos(Integer totalObjetos) {        this.totalObjetos = totalObjetos;    }

    public String getOrdem() { return ordem; }

    public void setOrdem(String ordem) { this.ordem = ordem; }

    public String getFiltro() { return filtro; }

    public void setFiltro(String filtro) { this.filtro = filtro; }
    //####################################################################
    public List<TIPO> getListaTodos() { 
        String sql = "from " + classePersistente.getSimpleName();
        return em.createQuery(sql).getResultList(); 
    }

    public List<TIPO> getListaObjetos() { 
        String sql = "from " + classePersistente.getSimpleName();
        String where = "";
        //PROTEGER CONTRA SQL INJECTION
        filtro = filtro.replaceAll("[';-]", "");
        if (filtro.length()>0){
            if (ordem.equalsIgnoreCase("ID")) { 
                try {
                    Integer.parseInt(filtro);
                    where += " where " +ordem+ " = " +filtro+ "'";
                } catch (Exception e) {
                }               
            } else where += " where upper("+ordem+") like '" +filtro.toUpperCase()+ "%' ";
        }
        sql += where;
        sql += " order by "+ordem;
        totalObjetos = em.createQuery(sql).getResultList().size(); 
        return em.createQuery(sql).setFirstResult(posicaoAtual).setMaxResults(maximoObjetos).getResultList(); 
    }
    
    public boolean persist(TIPO obj){
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            mensagem = "OBJETO PERSISITDO COM SUCESSO!";
            return true;
        } catch (Exception e) {
            rollback();
            mensagem = "ERRO AO PERSISTIR: " + Util.getMensagemErro(e);
            return false;
        }
    }
    
    public void rollback(){
        if (em.getTransaction().isActive())
            em.getTransaction().begin();
        em.getTransaction().rollback();
    }
    
    public boolean merge(TIPO obj){
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            mensagem = "OBJETO ATUALIZADO COM SUCESSO!";
            return true;
        } catch (Exception e) {
            rollback();
            mensagem = "ERRO AO ATUALIZAR: " + Util.getMensagemErro(e);
            return false;
        }
    }
    
     public boolean remove(TIPO obj){
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "OBJETO REMOVIDO COM SUCESSO!";
            return true;
        } catch (Exception e) {
            rollback();
            mensagem = "ERRO AO REMOVER: " + Util.getMensagemErro(e);
            return false;
        }
    }
     
    public TIPO localizar(Integer id){
        TIPO obj = (TIPO) em.find(classePersistente, id);
        return obj;
    }
    
    //#################################################### MÉTODOS DE NAVEGAÇÃO
    public void primeiro(){
        posicaoAtual=0;
    }
    
    public void anterior(){
        posicaoAtual -= maximoObjetos;
        if (posicaoAtual <0) posicaoAtual=0;
    }
    
    public void proximo(){
        if (posicaoAtual + maximoObjetos < totalObjetos) posicaoAtual += maximoObjetos;
    }
    
    public void ultimo(){
        int resto = totalObjetos % maximoObjetos;
        if (resto >0)  posicaoAtual = totalObjetos - resto;
        else posicaoAtual = totalObjetos -maximoObjetos;
    }
    
    public String getMensagemNavegacao(){
        int ate = posicaoAtual + maximoObjetos;
        if(ate>totalObjetos) ate=totalObjetos;
        return "Listando de " +(posicaoAtual + 1)+ " até " +ate+ " de " +totalObjetos+ " registros.";
    }
}
