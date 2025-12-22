package sam.model.domain;

import java.time.LocalDateTime;

public class UsuarioPrograma {
    int idUsuarioPrograma;
    int idPrograma;
    int idUsuario;
    double saldoMilhas;
    LocalDateTime dataAssociacao;


    public UsuarioPrograma(int idUsuario, int idPrograma) {
        this.idUsuario = idUsuario;
        this.idPrograma = idPrograma;
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

    public LocalDateTime getDataAssociacao() {
        return dataAssociacao;
    }

    public void setDataAssociacao(LocalDateTime dataAssociacao) {
        this.dataAssociacao = dataAssociacao;
    }
}
