package com.exemplo.comite.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@DynamoDbBean
public class Comite {

    private String id;
    private String dataFundacao;
    private String mentor;
    private String regiaoNova;
    private String comite;
    private String status;
    private String presidente;
    private String mandatoPresidencia;
    private String vicePresidente;
    private String emailComite;
    private String telefonePresidente;
    private String cidadesAbrangencia;
    private Boolean enviouAta2026;
    private Boolean enviouPlanejamento2026;
    private String mudancaPresidente;

    @DynamoDbPartitionKey
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    // Getters e setters dos demais campos
    public String getDataFundacao() { return dataFundacao; }
    public void setDataFundacao(String dataFundacao) { this.dataFundacao = dataFundacao; }

    public String getMentor() { return mentor; }
    public void setMentor(String mentor) { this.mentor = mentor; }

    public String getRegiaoNova() { return regiaoNova; }
    public void setRegiaoNova(String regiaoNova) { this.regiaoNova = regiaoNova; }

    public String getComite() { return comite; }
    public void setComite(String comite) { this.comite = comite; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPresidente() { return presidente; }
    public void setPresidente(String presidente) { this.presidente = presidente; }

    public String getMandatoPresidencia() { return mandatoPresidencia; }
    public void setMandatoPresidencia(String mandatoPresidencia) { this.mandatoPresidencia = mandatoPresidencia; }

    public String getVicePresidente() { return vicePresidente; }
    public void setVicePresidente(String vicePresidente) { this.vicePresidente = vicePresidente; }

    public String getEmailComite() { return emailComite; }
    public void setEmailComite(String emailComite) { this.emailComite = emailComite; }

    public String getTelefonePresidente() { return telefonePresidente; }
    public void setTelefonePresidente(String telefonePresidente) { this.telefonePresidente = telefonePresidente; }

    public String getCidadesAbrangencia() { return cidadesAbrangencia; }
    public void setCidadesAbrangencia(String cidadesAbrangencia) { this.cidadesAbrangencia = cidadesAbrangencia; }

    public Boolean getEnviouAta2026() { return enviouAta2026; }
    public void setEnviouAta2026(Boolean enviouAta2026) { this.enviouAta2026 = enviouAta2026; }

    public Boolean getEnviouPlanejamento2026() { return enviouPlanejamento2026; }
    public void setEnviouPlanejamento2026(Boolean enviouPlanejamento2026) { this.enviouPlanejamento2026 = enviouPlanejamento2026; }

    public String getMudancaPresidente() { return mudancaPresidente; }
    public void setMudancaPresidente(String mudancaPresidente) { this.mudancaPresidente = mudancaPresidente; }
}
