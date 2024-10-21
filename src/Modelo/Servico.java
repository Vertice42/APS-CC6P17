package Modelo;

import java.sql.Date;

public class Servico {
    private String NameSocilitante;
    private String LocalServico;
    private String TipoServico;
    private String Obeservacao;
    private Date DataServico;


    public String getNameSocilitante() {
        return NameSocilitante;
    }

    public void setNameSocilitante(String nameSocilitante) {
        NameSocilitante = nameSocilitante;
    }

    public String getLocalServico() {
        return LocalServico;
    }

    public void setLocalServico(String localServico) {
        LocalServico = localServico;
    }

    public String getTipoServico() {
        return TipoServico;
    }

    public void setTipoServico(String tipoServico) {
        TipoServico = tipoServico;
    }

    public String getObeservacao() {
        return Obeservacao;
    }

    public void setObeservacao(String obeservacao) {
        Obeservacao = obeservacao;
    }

    public Date getDataServico() {
        return DataServico;
    }

    public void setDataServico(Date dataServico) {
        DataServico = dataServico;
    }
}
