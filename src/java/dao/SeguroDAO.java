package dao;

import modelo.Seguro;
import java.io.Serializable;

public class SeguroDAO<TIPO> extends DAOGenerico<Seguro> implements Serializable{

    public SeguroDAO() {
        super();
        classePersistente = Seguro.class;
    }    
}
