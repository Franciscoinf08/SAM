package sam.model.dao;

import sam.model.domain.FaqEntry;

import java.sql.SQLException;

public class FaqDAO implements GenericDAO<FaqEntry, Long> {
    @Override
    public void inserir(FaqEntry pergunta) throws SQLException {

    }

    @Override
    public void atualizar(FaqEntry pergunta) throws SQLException {

    }

    @Override
    public FaqEntry pesquisar(Long id) throws SQLException {
        return null;
    }
}
