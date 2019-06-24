package dao;

import modelo.Pessoa;
import java.io.Serializable;

public class PessoaDAO<TIPO> extends DAOGenerico<Pessoa> implements Serializable{

    public PessoaDAO() {
        super();
        classePersistente = Pessoa.class;
    }    
}
