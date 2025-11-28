package sam.model.service;

import sam.model.dao.Conexao;
import sam.model.dao.UsuarioDAO;
import sam.model.common.exception.PersistenciaException;
import sam.model.domain.Usuario;
import sam.model.domain.util.UsuarioTipo;
import sam.model.helper.UsuarioHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GestaoUsuariosService {


    public GestaoUsuariosService() {}

    public Usuario pesquisarConta(String cpf, String senha) throws PersistenciaException, SQLException {
        Connection conexao= null;
        try {
            conexao = Conexao.getConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            return usuarioDAO.pesquisarPorCPFSenha(cpf, senha);
        } catch (PersistenciaException e) {
            throw new PersistenciaException(e.getLocalizedMessage());
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ignored) {

                }
            }
        }
    }

    public void cadastrar(Usuario usuario) throws PersistenciaException, SQLException {
        if (!"".equals(UsuarioHelper.validarCadastroUsuario(usuario)))
            throw new PersistenciaException(UsuarioHelper.validarCadastroUsuario(usuario));
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            conexao.setAutoCommit(false);
            usuarioDAO.inserir(usuario);
            conexao.commit();
        } catch (SQLException e) {
            if(conexao != null) {
                try {
                    conexao.rollback();
                } catch (SQLException ex) {}
            }
            throw new PersistenciaException(UsuarioHelper.validarCadastroUsuario(usuario));
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                }  catch (SQLException ignored) {

                }
            }
        }
    }

    public void atualizar(Usuario usuario) throws PersistenciaException, SQLException {
        if (!"".equals(UsuarioHelper.validarAtualizacaoUsuario(usuario)))
            throw new PersistenciaException(UsuarioHelper.validarAtualizacaoUsuario(usuario));
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            conexao.setAutoCommit(false);
            usuarioDAO.atualizar(usuario);
            conexao.commit();
        } catch (SQLException e) {
            if(conexao != null) {
                try {
                    conexao.rollback();
                } catch (SQLException ex) {}
            }
            throw new PersistenciaException(UsuarioHelper.validarAtualizacaoUsuario(usuario));
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                }  catch (SQLException ignored) {

                }
            }
        }
    }
    public List<Usuario> getListaClientes(Usuario usuario) throws SQLException {
        if (usuario.getTipo() != UsuarioTipo.GESTOR)
            throw new RuntimeException("Acesso negado");
        Connection conexao = null;
        try{
            conexao = Conexao.getConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            return usuarioDAO.getListaClientes(usuario);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }



}
