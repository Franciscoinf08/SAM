package sam.controller;

import jakarta.servlet.http.HttpServletRequest;
import sam.model.domain.Usuario;
import sam.model.service.GestaoUsuariosService;

public class AlteracaoPerfilController {
    public static String alterar(HttpServletRequest request) {
        String jsp = "/";

        try {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

            String nome = request.getParameter("nome");
            String senha = request.getParameter("email");
            String email = request.getParameter("senha");

            if (!"".equals(nome))
                usuario.setNome(nome);
            if (!"".equals(senha))
                usuario.setSenha(senha);
            if (!"".equals(email))
                usuario.setEmail(email);
            
            GestaoUsuariosService manterUsuario = new GestaoUsuariosService();

            if (manterUsuario.pesquisarPorEmail(email) != null) {
                String erro = "E-mail já cadastrado";
                request.setAttribute("erro", erro);
                jsp = "/";
            } else {
                manterUsuario.alterar(usuario);
                request.getSession().setAttribute("usuario", usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/";
        }
        return jsp;
    }
}
