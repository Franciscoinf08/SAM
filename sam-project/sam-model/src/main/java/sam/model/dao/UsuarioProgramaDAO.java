package sam.model.dao;

import sam.model.common.Conexao;
import sam.model.domain.UsuarioPrograma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioProgramaDAO{
    private Connection conexao = null;

    public UsuarioProgramaDAO() {
        this.conexao = Conexao.getConnection();
    }

    public void inserir(UsuarioPrograma entidade){
        String sql = "insert into usuario_programa(usuario_id, programa_id, saldo_milhas) values(?, ?, ?)";
        try(PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, entidade.getIdUsuario());
            stmt.setInt(2, entidade.getIdPrograma());
            stmt.setDouble(3, entidade.getSaldoMilhas());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()){
                if (rs.next()) {
                    entidade.setIdUsuarioPrograma(rs.getInt(1));
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void excluir(Integer idUsuario,  Integer idPrograma) {
        String sql = "delete from usuario_programa where usuario_id = ? and programa_id = ?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idPrograma);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    public List<Integer> buscarUsuariosComProgramaExpirando(int dias) {
        List<Integer> usuarios = new ArrayList<>();
        System.out.println("Esta chegando no dao de usuario programa");
        String sql = """
        SELECT DISTINCT up.usuario_id
        FROM usuario_programa up
        JOIN programa_fidelidade pf
            ON pf.idProgramaFidelidade = up.programa_id
        WHERE DATE_ADD(
                  up.data_associacao,
                  INTERVAL pf.duracao MONTH
              ) <= DATE_ADD(NOW(), INTERVAL ? DAY)
    """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, dias);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(rs.getInt("usuario_id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao buscar usuários com programa expirando", e
            );
        }

        return usuarios;
    }

}