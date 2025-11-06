package sam.model.domain;

import sam.model.domain.util.UsuarioTipo;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private final String cpf;
    private String senha;
    private UsuarioTipo tipo;

    public Usuario(String cpf) {
        this.cpf = cpf;
        tipo = UsuarioTipo.CLIENTE;
    }

    public Usuario(String nome, String email, String cpf, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        tipo = UsuarioTipo.CLIENTE;
    }

    public Usuario(Usuario usuario) {
        id = usuario.getId();
        nome = usuario.getNome();
        email = usuario.getEmail();
        cpf = usuario.getCPF();
        senha = usuario.getSenha();
        tipo = usuario.getTipo();
    }

    @Override
    public boolean equals(Object comparar) {
        Usuario usuarioComparar = (Usuario) comparar;
        return id.equals(usuarioComparar.getId());
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getCPF() {return cpf;}

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public UsuarioTipo getTipo() { return tipo; }

    public void setTipo(UsuarioTipo tipo) { this.tipo = tipo; }
}
