package sam.model.dao;

import sam.model.dao.exception.PersistenciaException;

import java.sql.SQLException;

public interface GenericDAO<E, K> {
    K inserir(E entidade) throws SQLException;
    void atualizar(E entidade) throws SQLException;
    E pesquisar(K chave) throws SQLException;
}
