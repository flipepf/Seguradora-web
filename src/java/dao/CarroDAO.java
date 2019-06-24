package dao;

import modelo.Carro;
import java.io.Serializable;

public class CarroDAO<TIPO> extends DAOGenerico<Carro> implements Serializable{

    public CarroDAO() {
        super();
        classePersistente = Carro.class;
    }    
}
