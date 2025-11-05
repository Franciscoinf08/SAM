package sam.model.dao;

import sam.model.dao.exception.PersistenciaException;

import java.sql.SQLException;

public interface GenericDAO<E, K> {
    void inserir(E entidade) throws PersistenciaException, SQLException;
    void atualizar(E entidade) throws PersistenciaException, SQLException;
    E pesquisar(K chave) throws PersistenciaException, SQLException;
}
