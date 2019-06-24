package dao;

import modelo.Acessorio;
import java.io.Serializable;

public class AcessorioDAO<TIPO> extends DAOGenerico<Acessorio> implements Serializable{

    public AcessorioDAO() {
        super();
        classePersistente = Acessorio.class;
    }    
}
