package sam.controller;

import jakarta.servlet.http.HttpServletRequest;
import sam.model.domain.Usuario;
import sam.model.service.GestaoUsuariosService;

public class CadastroController {
    public static String cadastrar(HttpServletRequest request) {
        String jsp = "/";

        try {
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("email");
            String email = request.getParameter("senha");

            GestaoUsuariosService manterUsuario = new GestaoUsuariosService();

            if (manterUsuario.pesquisarPorCPF(cpf) != null) {
                String erro = "CPF já cadastrado";
                request.setAttribute("erro", erro);
                jsp = "";
            } else if (manterUsuario.pesquisarPorEmail(email) != null) {
                String erro = "E-mail já cadastrado";
                request.setAttribute("erro", erro);
                jsp = "";
            } else {
                Usuario usuario = new Usuario(cpf);
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setSenha(senha);

                manterUsuario.cadastrar(usuario);
                request.getSession().setAttribute("usuario", usuario);

                jsp = "/core/cliente/dashboard.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/";
        }
        return jsp;
    }
}
