package sam.model.dao;

import sam.model.common.Conexao;
import sam.model.common.exception.PersistenciaException;
import sam.model.common.seguranca.PasswordDigest;
import sam.model.domain.Usuario;
import sam.model.domain.util.UsuarioTipo;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class UsuarioDAO implements GenericDAO<Usuario, Long> {

    private final Connection conexao;
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
    public void inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios(nome, email, cpf, senha, tipo) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            String senha = PasswordDigest.passwordDigestMD5(usuario.getSenha());
            usuario.setSenha(senha);

            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getCPF());
            preparedStatement.setString(4, usuario.getSenha());
            preparedStatement.setString(5, usuario.getTipo().toString());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next())
                usuario.setId(rs.getLong(1));
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar usuário", e);
        }
    }

    @Override
    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, cpf = ?, senha = ?, tipo = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            if (!Objects.equals(pesquisar(usuario.getId()).getSenha(), usuario.getSenha())) {
                String senhaCriptografada = PasswordDigest.passwordDigestMD5(usuario.getSenha());
                usuario.setSenha(senhaCriptografada);
            }

            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getCPF());
            preparedStatement.setString(4, usuario.getSenha());
            preparedStatement.setString(5, usuario.getTipo().toString());
            preparedStatement.setLong(6, usuario.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar usuário", e);
        }
    }

    @Override
    public Usuario pesquisar(Long id) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Long idGestor = rs.getLong("id_gestor");
                if (rs.wasNull()) {
                    idGestor = null;
                }
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                String senha = rs.getString("senha");
                String tipo = rs.getString("tipo");
                usuario = new Usuario(nome, email, cpf, senha, UsuarioTipo.strTo(tipo));
                usuario.setId(id);
                usuario.setIdGestor(idGestor);
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
                Long idGestor = rs.getLong("id_gestor");
                if (rs.wasNull()) {
                    idGestor = null;
                }
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String senha = rs.getString("senha");
                String tipo = rs.getString("tipo");
                usuario = new Usuario(nome, email, cpf, senha, UsuarioTipo.strTo(tipo));
                usuario.setId(id);
                usuario.setIdGestor(idGestor);
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
                Long idGestor = rs.getLong("id_gestor");
                if (rs.wasNull()) {
                    idGestor = null;
                }
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String tipo = rs.getString("tipo");
                usuario = new Usuario(nome, email, cpf, senha, UsuarioTipo.strTo(tipo));
                usuario.setId(id);
                usuario.setIdGestor(idGestor);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar usuário", e);
        }
        return usuario;
    }

    public Usuario pesquisarPorCPFSenha(String cpf, String senha) throws PersistenciaException, SQLException {
        try {
            String senhaCriptografada = PasswordDigest.passwordDigestMD5(senha);

            Usuario usuario = pesquisarPorCPF(cpf);
            if (usuario != null && senhaCriptografada.equals(usuario.getSenha()))
                return usuario;
            throw new PersistenciaException("CPF ou Senha incorreto");
        } catch (SQLException e) {
            throw new SQLException("Erro ao pesquisar usuário", e);
        }
    }

    public List<Usuario> getListaClientes(Usuario usuario) throws SQLException {
        List<Usuario> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE id_gestor = ?";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, usuario.getId());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                String senha = rs.getString("senha");
                String tipo = rs.getString("tipo");
                Long id = rs.getLong("id");
                Long idGestor = rs.getLong("id_gestor");
                if (rs.wasNull()) {
                    idGestor = null;
                }
                Usuario cliente = new Usuario(nome, email, cpf, senha, UsuarioTipo.strTo(tipo));
                cliente.setId(id);
                cliente.setIdGestor(idGestor);

                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar clientes", e);
        }

        return listaClientes;
    }

    public List<Usuario> listarPorTipo(String tipo) throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE tipo = ?";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, tipo);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                Long id = rs.getLong("id");
                Long idGestor = rs.getLong("id_gestor");
                if (rs.wasNull()) {
                    idGestor = null;
                }
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                String senha = rs.getString("senha");
                String tipoUser = rs.getString("tipo");

                Usuario usuario = new Usuario(nome, email, cpf, senha, UsuarioTipo.strTo(tipoUser));
                usuario.setId(id);
                usuario.setIdGestor(idGestor);

                lista.add(usuario);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar usuários por tipo", e);
        }

        return lista;
    }

    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery()){

            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                String senha = rs.getString("senha");
                String tipo = rs.getString("tipo");
                Long id = rs.getLong("id");
                Long idGestor = rs.getLong("id_gestor");
                if (rs.wasNull()) {
                    idGestor = null;
                }
                Usuario cliente = new Usuario(nome, email, cpf, senha, UsuarioTipo.strTo(tipo));
                cliente.setId(id);
                cliente.setIdGestor(idGestor);

                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar clientes", e);
        }

        return listaClientes;
    }
}