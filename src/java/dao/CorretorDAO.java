package dao;

import modelo.Corretor;
import java.io.Serializable;

public class CorretorDAO<TIPO> extends DAOGenerico<Corretor> implements Serializable{

    public CorretorDAO() {
        super();
        classePersistente = Corretor.class;
    }    
}

