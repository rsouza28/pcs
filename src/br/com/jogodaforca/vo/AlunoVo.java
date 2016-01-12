package br.com.jogodaforca.vo;

public class AlunoVo {
    
    private String nome;
    private Integer serie;
    private Integer idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return nome + ":" + serie + ":" + idade + "\r\n";
    }
    
}
