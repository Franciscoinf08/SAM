package sam.model.dao;
import sam.model.domain.UsuarioPrograma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioProgramaDAO{
    private Connection conexao = null;

    public UsuarioProgramaDAO(Connection conexao) {
        this.conexao = conexao;
    }


    public void associar(UsuarioPrograma entidade) throws SQLException {
        String sql = "insert into usuario_programa (id_usuario, id_programa) values (?, ?)";
        try(PreparedStatement stmt = this.conexao.prepareStatement(sql)){
            System.out.println(entidade.getIdUsuario()+" " +  entidade.getIdPrograma() + " " + entidade.getSaldoMilhas());
            stmt.setInt(1,entidade.getIdUsuario());
            stmt.setInt(2,entidade.getIdPrograma());
            stmt.execute();
        } catch (SQLException e){
            throw new SQLException(e.getMessage());
        }

    }


    public void desassociar(Integer id) {
        String sql = "delete from usuario_programa where id = ?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
