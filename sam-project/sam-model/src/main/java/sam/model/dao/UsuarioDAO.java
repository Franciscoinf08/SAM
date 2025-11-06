package sam.model.dao;

import sam.model.dao.exception.PersistenciaException;
import sam.model.domain.Usuario;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO implements GenericDAO<Usuario, Long> {

    private Connection conexao;
    private static UsuarioDAO usuarioDAO;

    static {
        UsuarioDAO.usuarioDAO = null;
    }

    private UsuarioDAO() {
        this.conexao = Conexao.getConnection();
    }
    public static UsuarioDAO getInstance() {
        if (usuarioDAO == null)
            usuarioDAO = new UsuarioDAO();

        return usuarioDAO;
    }

    @Override
    public Long inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios(nome, email, cpf, senha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getCPF());
            preparedStatement.setString(4, usuario.getSenha());

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next())
                usuario.setId(rs.getLong(1));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar usuário", e);
        }
        return usuario.getId();
    }

    @Override
    public Long atualizar(Usuario usuario) throws SQLException {
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
        return usuario.getId();
    }

    @Override
    public Usuario pesquisar(Long id) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                String senha = rs.getString("senha");
                usuario = new Usuario(nome,email, cpf, senha);
                usuario.setId(id);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar usuário", e);
        }
        return usuario;
    }

    public Usuario pesquisarPorNome(String nome) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE nome = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, nome);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                String senha = rs.getString("senha");
                usuario = new Usuario(nome,email, cpf, senha);
                usuario.setId(id);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar usuário", e);
        }
        return usuario;
    }

    public Usuario pesquisarPorEmail(String email) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String senha = rs.getString("senha");
                usuario = new Usuario(nome, email, cpf, senha);
                usuario.setId(id);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar usuário", e);
        }
        return usuario;
    }

    public Usuario pesquisarPorCPF(String cpf) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE cpf = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, cpf);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                usuario = new Usuario(nome, email, cpf, senha);
                usuario.setId(id);
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