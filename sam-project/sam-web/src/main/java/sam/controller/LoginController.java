package sam.controller;

import jakarta.servlet.http.HttpServletRequest;
import sam.model.domain.Usuario;
import sam.model.service.GestaoUsuariosService;

public class LoginController {
    public static String logar(HttpServletRequest request) {
        String jsp = "/";

        try {
            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("senha");

            GestaoUsuariosService manterUsuario = new GestaoUsuariosService();
            Usuario usuario = manterUsuario.pesquisarConta(cpf, senha);

            if (usuario == null) {
                String erro = "Usuario não encontrado";
                request.setAttribute("erro", erro);
                jsp = "";
            } else {
                request.getSession().setAttribute("usuario", usuario);
                switch (usuario.getTipo()) {
                    case CLIENTE:
                        jsp = "/core/cliente/dashboard.jsp";
                        break;
                    case GESTOR:
                        jsp = "/core/gestor/apuracao.jsp";
                        break;
                    case DESENVOLVEDOR:
                        jsp = "/core/perfil.jsp";
                        break;
                    default:
                        jsp = "/";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/";
        }
        return jsp;
    }
}
