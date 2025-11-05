package sam.model.dao;

import sam.model.dao.exception.PersistenciaException;
import sam.model.domain.Usuario;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public class UsuarioDAO implements GenericDAO<Usuario, Long> {

    private Connection conexao;

    private final Map<Long, Usuario> usuarios;

    private static UsuarioDAO usuarioDAO;
    private static Long sequenciaId;

    static {
        UsuarioDAO.usuarioDAO = null;
        UsuarioDAO.sequenciaId = 0L;
    }

    public static Long getNextId() { return UsuarioDAO.sequenciaId++; }

    private UsuarioDAO() {
        this.conexao = Conexao.getConnection();
        usuarios = new HashMap();
    }
    public static UsuarioDAO getInstance() {
        if (usuarioDAO == null)
            usuarioDAO = new UsuarioDAO();

        return usuarioDAO;
    }

    @Override
    public void inserir(Usuario usuario) throws PersistenciaException, SQLException {
        String sql = "INSERT INTO usuarios(nome, email, cpf, senha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getCPF());
            preparedStatement.setString(4, usuario.getSenha());

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next())
                usuario.setId(rs.getLong(1));
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar usuário", e);
        }
    }

    @Override
    public void atualizar(Usuario usuario) throws PersistenciaException, SQLException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, cpf = ?, senha = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getCPF());
            preparedStatement.setString(4, usuario.getSenha());
            preparedStatement.setLong(5, usuario.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar usuário", e);
        }
    }

    @Override
    public Usuario pesquisar(Long id) throws PersistenciaException, SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                String senha = rs.getString("senha");
                usuario = new Usuario(nome,email, cpf, senha);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar usuário", e);
        }
        return usuario;
    }

    public Usuario pesquisarPorNome(String nome) throws PersistenciaException, SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE nome = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, nome);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                String senha = rs.getString("senha");
                usuario = new Usuario(nome,email, cpf, senha);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar usuário", e);
        }
        return usuario;
    }

    public Usuario pesquisarPorEmail(String email) throws PersistenciaException, SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String senha = rs.getString("senha");
                usuario = new Usuario(nome,email, cpf, senha);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar usuário", e);
        }
        return usuario;
    }

    public Usuario pesquisarPorCPF(String cpf) throws PersistenciaException, SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE cpf = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, cpf);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                usuario = new Usuario(nome,email, cpf, senha);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar usuário", e);
        }
        return usuario;
    }

    public Usuario pesquisarPorCPFSenha(String cpf, String senha) throws PersistenciaException, SQLException {
        try {
            Usuario usuario = pesquisarPorCPF(cpf);
            if (senha.equals(usuario.getSenha()))
                return usuario;
            throw new PersistenciaException("Senha incorreta");
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar usuário", e);
        }
    }
}
