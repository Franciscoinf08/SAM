package sam.model.domain;

public class Feedback {

    private int id;
    private Usuario autor;
    private Usuario avaliado;
    private String comentario;
    private Integer nota; 

    public Feedback(int id, Usuario autor, Usuario avaliado, String comentario, int nota) {
        this.id = id;
        this.autor = autor;
        this.avaliado = avaliado;
        this.comentario = comentario;
        this.nota = nota;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }

    public Usuario getAvaliado() { return avaliado; }
    public void setAvaliado(Usuario avaliado) { this.avaliado = avaliado; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public Integer getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }
}
