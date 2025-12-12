package sam.model.service;

import sam.model.common.exception.PersistenciaException;
import sam.model.dao.RespostaTicketDAO;
import sam.model.domain.PerguntaTicket;
import sam.model.domain.RespostaTicket;
import sam.model.helper.RespostaTicketHelper;

import java.sql.SQLException;
import java.util.List;

public class GestaoRespostasTicketService {

    private final RespostaTicketDAO respostaTicketDAO;

    public GestaoRespostasTicketService() {
        respostaTicketDAO = RespostaTicketDAO.getInstance();
    }

    public void cadastrar(RespostaTicket resposta) throws SQLException, PersistenciaException {
        if (!"".equals(RespostaTicketHelper.validarResposta(resposta)))
            throw new PersistenciaException(RespostaTicketHelper.validarResposta(resposta));

        try {
            respostaTicketDAO.inserir(resposta);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void atualizar(RespostaTicket resposta) throws SQLException, PersistenciaException {

        if (!"".equals(RespostaTicketHelper.validarResposta(resposta)))
            throw new PersistenciaException(RespostaTicketHelper.validarResposta(resposta));

        try {
            respostaTicketDAO.atualizar(resposta);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public RespostaTicket pesquisar(Long id) throws PersistenciaException, SQLException {

        if (id == null || id <= 0)
            throw new PersistenciaException("Id inválido");

        try {
            return respostaTicketDAO.pesquisar(id);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<RespostaTicket> listarPorPergunta(PerguntaTicket pergunta) throws SQLException {
        List<RespostaTicket> listaRespostas;

        try {
            listaRespostas = respostaTicketDAO.pesquisarPorPergunta(pergunta);
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        return listaRespostas;
    }

    public void remover(Long id) throws PersistenciaException, SQLException {

        if (respostaTicketDAO.pesquisar(id) == null)
            throw new PersistenciaException("A resposta não existe");

        try {
            respostaTicketDAO.remover(id);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
