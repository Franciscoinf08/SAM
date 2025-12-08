package sam.model.dao;

import sam.model.domain.PerguntaTicket;

import java.sql.SQLException;

public class PerguntaTicketDAO implements GenericDAO<PerguntaTicket, Long> {
    @Override
    public void inserir(PerguntaTicket pergunta) throws SQLException {

    }

    @Override
    public void atualizar(PerguntaTicket pergunta) throws SQLException {

    }

    @Override
    public PerguntaTicket pesquisar(Long id) throws SQLException {
        return null;
    }
}
