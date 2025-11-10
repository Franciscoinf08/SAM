package sam.model.domain;

public class UsuarioPrograma {
    int idUsuarioPrograma;
    int idPrograma;
    int idUsuario;
    double saldoMilhas;

    public UsuarioPrograma(int idPrograma, int idUsuario) {
        this.idPrograma = idPrograma;
        this.idUsuario = idUsuario;
        this.saldoMilhas = 0;
    }

    public UsuarioPrograma() {

    }

    public int getIdUsuarioPrograma() {
        return idUsuarioPrograma;
    }

    public void setIdUsuarioPrograma(int idUsuarioPrograma) {
        this.idUsuarioPrograma = idUsuarioPrograma;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getSaldoMilhas() {
        return saldoMilhas;
    }

    public void setSaldoMilhas(double saldoMilhas) {
        this.saldoMilhas = saldoMilhas;
    }
}
