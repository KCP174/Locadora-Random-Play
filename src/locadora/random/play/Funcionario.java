/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora.random.play;

import java.time.LocalDate;

/**
 *
 * @author kauan
 */
public class Funcionario extends Pessoa{
    
    private String login;
    private String senha;
    private boolean adminPerm;

    public Funcionario() {
    }
    
    public Funcionario(String nome, String login, String senha, LocalDate dataNasc, String cpf, boolean adminPerm) {
        setNome(nome);
        setDataNasc(dataNasc);
        setCpf(cpf);
        setLogin(login);
        setSenha(senha);
        setAdminPerm(adminPerm);
    }

    public boolean isAdminPerm() {
        return adminPerm;
    }

    public void setAdminPerm(boolean adminPerm) {
        this.adminPerm = adminPerm;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setLogin(String login) {
        if (login == null || login.isBlank()){
            throw new RuntimeException("Login inválido");
        }
        this.login = login;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.isBlank()){
            throw new RuntimeException("Senha inválida");
        }
        this.senha = senha;
    }
}