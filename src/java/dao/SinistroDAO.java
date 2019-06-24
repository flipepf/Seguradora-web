package dao;

import modelo.Sinistro;
import java.io.Serializable;

public class SinistroDAO<TIPO> extends DAOGenerico<Sinistro> implements Serializable{

    public SinistroDAO() {
        super();
        classePersistente = Sinistro.class;
    }    
}

