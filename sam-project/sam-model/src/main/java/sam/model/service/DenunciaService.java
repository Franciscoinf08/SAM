package sam.model.service;

import sam.model.common.Conexao;
import sam.model.dao.UsuarioDAO;
import sam.model.dao.DenunciaDAO;
import sam.model.domain.AtividadeReferencia;
import sam.model.domain.Denuncia;
import sam.model.domain.Usuario;
import sam.model.helper.EnviarEmailHelper;
import sam.model.domain.util.TipoAtividades;
import sam.model.domain.util.TipoEntidades;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DenunciaService {

    private final Connection conexao;
    private final DenunciaDAO denunciaDAO = new DenunciaDAO();
    private final EnviarEmailHelper emailHelper =  new EnviarEmailHelper();

    public DenunciaService() {
        this.conexao = Conexao.getConnection();

    }

    public void registrarDenuncia(Long denuncianteId, Long denunciadoId, String motivo, String detalhes) throws Exception {
    private final EmailNotificador emailNotificador = new EmailNotificador();
    private final AtividadeService atividadeService = new AtividadeService();
    public void registrarDenuncia(Long denuncianteId,
                                  Long denunciadoId,
                                  String motivo,
                                  String detalhes) throws Exception {


        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();

        Usuario denunciante = usuarioDAO.pesquisar(denuncianteId);
        Usuario denunciado = usuarioDAO.pesquisar(denunciadoId);

        if (denunciante == null) throw new Exception("Denunciante não encontrado.");
        if (denunciado == null) throw new Exception("Denunciado não encontrado.");

        Denuncia denuncia = new Denuncia(denunciante, denunciado, motivo, detalhes);
        denuncia.setStatus("ABERTA");
        try {
            denunciaDAO.inserir(denuncia);

        }catch (SQLException e){
            throw new RuntimeException("Erro ao inserir denuncia:" + e.getMessage());
        }finally{
            emailHelper.enviarEmailDenuncia(denuncia);
        }
        String descricao = "O usuario " + denunciante.getNome()+ " denunciou o usuario "+denunciado.getNome() + " por " + motivo;
        atividadeService.registrarAtividade(TipoAtividades.DENUNCIA.name(), descricao, denuncianteId);

    }
}
