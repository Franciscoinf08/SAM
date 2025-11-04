package sam.model.service;

import sam.model.domain.Usuario;

// Placeholder
public class GestaoUsuariosService {
    public Usuario pesquisarConta(String cpf, String senha) {
        Usuario usuario = new Usuario(cpf);
        usuario.setNome("Mateus0Vasconcelos");
        usuario.setEmail("mateus17vasc@gmail.com");
        usuario.setSenha(senha);
        return usuario;
    }

    public void cadastrar(Usuario usuario) {

    }

    public void alterar(Usuario usuario) {

    }

    public Usuario pesquisarPorCPF(String cpf) {
        Usuario usuario = new Usuario(cpf);
        usuario.setNome("Mateus0Vasconcelos");
        usuario.setEmail("mateus17vasc@gmail.com");
        usuario.setSenha("senha");
        return usuario;
    }

    public Usuario pesquisarPorEmail(String email) {
        Usuario usuario = new Usuario("cpf");
        usuario.setNome("Mateus0Vasconcelos");
        usuario.setEmail(email);
        usuario.setSenha("senha");
        return usuario;
    }
}
