package sam.model.service;

import sam.model.common.Conexao;
import sam.model.dao.ProgramaFidelidadeDAO;
import sam.model.dao.UsuarioDAO;
import sam.model.dao.UsuarioProgramaDAO;
import sam.model.domain.ProgramaFidelidade;
import sam.model.domain.Usuario;
import sam.model.domain.UsuarioPrograma;
import sam.model.domain.util.TipoAtividades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioProgramaService {
    private UsuarioProgramaDAO upDAO;
    private UsuarioDAO uDAO;
    private ProgramaFidelidadeDAO pfDAO;
    private final Connection conexao;
    private final AtividadeService atividadeService = new AtividadeService();

    public UsuarioProgramaService() {
        this.conexao = Conexao.getConnection();
        this.upDAO = new UsuarioProgramaDAO();
        this.uDAO = UsuarioDAO.getInstance();
        this.pfDAO = new ProgramaFidelidadeDAO();
    }
    public void associar(UsuarioPrograma up, int idUsuarioExecutor) throws SQLException {
        if (!validarAssociacao(up))
            throw new RuntimeException();
        upDAO.inserir(up);

        String descricao =  "o usuario: " + up.getIdUsuario() + " foi associado ao programa: " + up.getIdPrograma();
        atividadeService.registrarAtividade(TipoAtividades.valueOf(TipoAtividades.ASSOCIACAO_CLIENTE_PROGRAMA.name()), descricao, (long) idUsuarioExecutor);

    }
    public void desassociar(UsuarioPrograma up, int idUsuarioExecutor) throws SQLException {
        if(!validarAssociacao(up)){
            throw new RuntimeException();
        }
        upDAO.excluir(up.getIdUsuario(),  up.getIdPrograma());
        String descricao = "o usuario: "+up.getIdUsuario()+ " foi desassociado ao programa: " + up.getIdPrograma();
        atividadeService.registrarAtividade(TipoAtividades.valueOf(TipoAtividades.DESASSOCIACAO_CLIENTE_PROGRAMA.name()), descricao, (long) idUsuarioExecutor);
    }
    public List<ProgramaFidelidade> listarNaoAssociados(int idUsuario) throws SQLException {
        Usuario usuario = uDAO.pesquisar((long) idUsuario);
        if (usuario == null) throw new RuntimeException("Usuário não encontrado");

        return pfDAO.listarNaoAssociados(usuario);
    }

    private boolean validarAssociacao(UsuarioPrograma usuarioPrograma) throws SQLException {
        int idUsuario = usuarioPrograma.getIdUsuario();
        int idPrograma = usuarioPrograma.getIdPrograma();
        ProgramaFidelidade pf =pfDAO.buscarPorId(idPrograma);
        Usuario u = uDAO.pesquisar((long) idUsuario);
        if (usuarioPrograma.getIdUsuario() == 0 || usuarioPrograma.getIdPrograma() == 0){return false;}

        if (pf == null || u == null){return false;}

        return true;
    }

    public List<ProgramaFidelidade> listarAssociados(int idUsuario) throws SQLException {
        Usuario usuario = uDAO.pesquisar((long) idUsuario);
        if (usuario == null)
            throw new RuntimeException("usuario nao encontrado");
        return pfDAO.listarAssociados(usuario);
    }

    public List<Integer> buscarUsuariosComProgramaExpirando(int dias) {
        List<Integer> usuarios = new ArrayList<>();

        String sql = """
        SELECT DISTINCT up.usuario_id
        FROM usuario_programa up
        JOIN programa_fidelidade pf
          ON pf.idProgramaFidelidade = up.programa_id
        WHERE DATE_ADD(up.data_associacao, INTERVAL pf.duracao MONTH)
              <= NOW() + INTERVAL ? DAY
    """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, dias);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                usuarios.add(rs.getInt("usuario_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuarios;
    }
}
