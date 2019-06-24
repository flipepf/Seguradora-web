package dao;

import modelo.Cobertura;
import java.io.Serializable;

public class CoberturaDAO<TIPO> extends DAOGenerico<Cobertura> implements Serializable{

    public CoberturaDAO() {
        super();
        classePersistente = Cobertura.class;
    }    
}
