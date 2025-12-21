package sam.model.domain;

public class Denuncia {

    private int id;
    private Usuario denunciante;
    private Usuario denunciado;
    private String motivo;
    private String detalhes;
    private String status;

    public Denuncia(Usuario denunciante, Usuario denunciado, String motivo, String detalhes) {
        this.denunciante = denunciante;
        this.denunciado = denunciado;
        this.motivo = motivo;
        this.detalhes = detalhes;
        this.status = "ABERTA";
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Usuario getDenunciante() { return denunciante; }
    public void setDenunciante(Usuario denunciante) { this.denunciante = denunciante; }

    public Usuario getDenunciado() { return denunciado; }
    public void setDenunciado(Usuario denunciado) { this.denunciado = denunciado; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getDetalhes() { return detalhes; }
    public void setDetalhes(String detalhes) { this.detalhes = detalhes; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
