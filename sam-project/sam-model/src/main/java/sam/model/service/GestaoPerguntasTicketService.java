package sam.model.service;

import sam.model.common.exception.PersistenciaException;
import sam.model.dao.PerguntaTicketDAO;
import sam.model.domain.PerguntaTicket;
import sam.model.domain.Usuario;
import sam.model.domain.util.TipoAtividades;
import sam.model.helper.PerguntaTicketHelper;

import java.sql.SQLException;
import java.util.List;

public class GestaoPerguntasTicketService {

    private final PerguntaTicketDAO perguntaTicketDAO;
    private final AtividadeService atividadeService = new AtividadeService();

    public GestaoPerguntasTicketService() {
        perguntaTicketDAO = PerguntaTicketDAO.getInstance();
    }

    public void cadastrar(PerguntaTicket pergunta) throws SQLException, PersistenciaException {
        if (!"".equals(PerguntaTicketHelper.validarPergunta(pergunta)))
            throw new PersistenciaException(PerguntaTicketHelper.validarPergunta(pergunta));

        try {
            perguntaTicketDAO.inserir(pergunta);
            String descricao = "o usuario "+ pergunta.getIdUsuario() + " enviou um ticket";
            atividadeService.registrarAtividade(TipoAtividades.CADASTRAR_TICKET.name(), descricao, pergunta.getIdUsuario());
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void atualizar(PerguntaTicket pergunta) throws SQLException, PersistenciaException {

        if (!"".equals(PerguntaTicketHelper.validarPergunta(pergunta)))
            throw new PersistenciaException(PerguntaTicketHelper.validarPergunta(pergunta));

        try {
            perguntaTicketDAO.atualizar(pergunta);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public PerguntaTicket pesquisar(Long id) throws PersistenciaException, SQLException {

        if (id == null || id <= 0)
            throw new PersistenciaException("Id inválido");

        try {
            return perguntaTicketDAO.pesquisar(id);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<PerguntaTicket> listarPorUsuario(Usuario usuario) throws SQLException {
        List<PerguntaTicket> listaPerguntas;

        try {
            listaPerguntas = perguntaTicketDAO.pesquisarPorUsuario(usuario);
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        return listaPerguntas;
    }

    public List<PerguntaTicket> listarSemResposta() throws SQLException {
        List<PerguntaTicket> listaPerguntas;

        try {
            listaPerguntas = perguntaTicketDAO.pesquisarSemResposta();
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        return listaPerguntas;
    }

    public List<PerguntaTicket> listarComResposta() throws SQLException {
        List<PerguntaTicket> listaPerguntas;

        try {
            listaPerguntas = perguntaTicketDAO.pesquisarComResposta();
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        return listaPerguntas;
    }

    public void remover(Long id) throws PersistenciaException, SQLException {
        if (perguntaTicketDAO.pesquisar(id) == null)
            throw new PersistenciaException("A pergunta não existe");

        try {
            perguntaTicketDAO.remover(id);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
