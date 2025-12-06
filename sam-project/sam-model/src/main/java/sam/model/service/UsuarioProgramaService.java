package sam.model.service;

import sam.model.common.Conexao;
import sam.model.dao.ProgramaFidelidadeDAO;
import sam.model.dao.UsuarioDAO;
import sam.model.dao.UsuarioProgramaDAO;
import sam.model.domain.ProgramaFidelidade;
import sam.model.domain.Usuario;
import sam.model.domain.UsuarioPrograma;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsuarioProgramaService {
    private UsuarioProgramaDAO upDAO;
    private UsuarioDAO uDAO;
    private ProgramaFidelidadeDAO pfDAO;
    private final Connection conexao;

    public UsuarioProgramaService() {
        this.conexao = Conexao.getConnection();
        this.upDAO = new UsuarioProgramaDAO();
        this.uDAO = UsuarioDAO.getInstance();
        this.pfDAO = new ProgramaFidelidadeDAO();
    }
    public void associar(UsuarioPrograma up) throws SQLException {
        System.out.println("ID USUARIO = " + up.getIdUsuario());
        System.out.println("ID PROGRAMA = " + up.getIdPrograma());
        System.out.println("USUARIO ENCONTRADO = " + uDAO.pesquisar((long) up.getIdUsuario()));
        System.out.println("PROGRAMA ENCONTRADO = " + pfDAO.buscarPorId(up.getIdPrograma()));
        System.out.println("VALIDAÇÃO = " + validarAssociacao(up));
        if (!validarAssociacao(up))
            throw new RuntimeException();
        upDAO.inserir(up);
    }
    public void desassociar(UsuarioPrograma up) throws SQLException {
        if(!validarAssociacao(up)){
            throw new RuntimeException();
        }
        upDAO.excluir(up.getIdUsuario(),  up.getIdPrograma());
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
}
