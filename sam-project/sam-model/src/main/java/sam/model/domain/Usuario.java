package sam.model.domain;

import sam.model.domain.util.UsuarioTipo;

public class Usuario {
    private String nome;
    private final String cpf;
    private String email;
    private String senha;
    private UsuarioTipo tipo;

    public Usuario(String cpf) {
        this.cpf = cpf;
        tipo = UsuarioTipo.CLIENTE;
    }

    public String getNome() { return senha; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCPF() {return cpf;}

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public UsuarioTipo getTipo() { return tipo; }

    public void setTipo(UsuarioTipo tipo) { this.tipo = tipo; }
}
