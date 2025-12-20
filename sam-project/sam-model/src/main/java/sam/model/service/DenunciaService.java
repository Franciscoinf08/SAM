package sam.model.service;

import sam.model.common.Conexao;
import sam.model.dao.UsuarioDAO;
import sam.model.dao.DenunciaDAO;
import sam.model.domain.Denuncia;
import sam.model.domain.Usuario;
import sam.model.domain.util.TipoAtividades;

import java.sql.Connection;

public class DenunciaService {

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

        try (Connection conexao = Conexao.getConnection()) {

            DenunciaDAO denunciaDAO = new DenunciaDAO();

            Denuncia denuncia = new Denuncia(
                    0,
                    denunciante,
                    denunciado,
                    motivo,
                    detalhes
            );
            denuncia.setStatus("ABERTA");


            denunciaDAO.inserir(denuncia);
            emailNotificador.notificarDenuncia(denuncia);
        }
        String descricao = "O usuario " + denunciante.getNome()+ " denunciou o usuario "+denunciado.getNome() + " por " + motivo;
        atividadeService.registrarAtividade(TipoAtividades.DENUNCIA, descricao, denuncianteId);
    }
}
