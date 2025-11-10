package sam.model.dao;

import java.sql.SQLException;

public interface GenericDAO<E, K> {
    void inserir(E entidade) throws SQLException;
    void atualizar(E entidade) throws SQLException;
    E pesquisar(K chave) throws SQLException;
}
