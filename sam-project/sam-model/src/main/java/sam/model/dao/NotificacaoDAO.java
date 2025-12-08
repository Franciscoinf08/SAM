package sam.model.dao;


import sam.model.common.Conexao;
import sam.model.domain.Notificacao;
import sam.model.domain.Usuario;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoDAO {
    Connection conexao = null;
    public NotificacaoDAO() {
        this.conexao = Conexao.getConnection();
    }
     public List<Notificacao> listarPorUsuario(int destinatario) throws SQLException {
        String sql = "select * from notificacoes where destinatario = ?";

        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(1, destinatario);
            try(ResultSet rs = stmt.executeQuery()){
                List<Notificacao> lista = new ArrayList<>();
                while(rs.next()){
                    Notificacao n = new Notificacao();
                    n.setTitulo(rs.getString("titulo"));
                    n.setDescricao(rs.getString("mensagem"));
                    n.setDestinatario(rs.getInt("destinatario"));
                    n.setLida(rs.getBoolean("lida"));
                    n.setId(rs.getInt("id"));
                    Timestamp timestamp = rs.getTimestamp("dataEnvio");
                    LocalDateTime data = timestamp.toLocalDateTime();
                    n.setData(data);

                    lista.add(n);

                }
            return lista;
        }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar notificacao" + e.getMessage(), e);
        }
     }

     public void salvar(Notificacao notificacao) throws SQLException {
        String sql = "insert into notificacoes (titulo, mensagem, dataEnvio, destinatario, lida) values(?,?,?,?,?)";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, notificacao.getTitulo());
            stmt.setString(2, notificacao.getDescricao());
            stmt.setTimestamp(3, Timestamp.valueOf(notificacao.getData()));
            stmt.setInt(4, notificacao.getDestinatario());
            stmt.setBoolean(5, notificacao.isLida());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao salvar notificacao" + e.getMessage(), e);
        }
     }
     public void marcarComoLida(int idNotificacao) throws SQLException {
        String sql = "update notificacoes set lida=? where id = ?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setBoolean(1, true);
            stmt.setInt(2, idNotificacao);
            stmt.executeUpdate();
        }  catch (SQLException e) {
            throw new SQLException("Erro ao marcar notificacao como lida" + e.getMessage(), e);
        }
     }

     public List<Long> buscarUsuariosProgramaExpirando(){
        List<Long> usuarios = new ArrayList<>();
        String  sql = "select usuario_id from usuario_programa where dataExpiracao <= NOW() + interval 3 day";
        try(PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                usuarios.add(rs.getLong("usuario_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
     }
}

