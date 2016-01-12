package br.com.jogodaforca.vo;

public class AdminVo {

    private Long login;

    public Long getLogin() {
        return login;
    }

    public void setLogin(Long login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return  login + "\r\n";
    }

}
