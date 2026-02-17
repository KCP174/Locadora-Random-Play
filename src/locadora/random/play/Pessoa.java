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
public abstract class Pessoa extends EntidadeBase{
    String nome;
    LocalDate dataNasc;
    String cpf;

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public final void setNome(String nome) {
        if(nome == null){
            throw new RuntimeException("Nome é obrigatório");
        }
        if(nome.length() < 5){
            throw new RuntimeException("É necessário nome e sobrenome");
        }
        this.nome = nome;
    }

    public final void setDataNasc(LocalDate dataNasc) {
        if(dataNasc.isAfter(LocalDate.now())) {
            throw new RuntimeException("Data de nascimento inválida");
        }else if(!dataNasc.isBefore(LocalDate.now().minusYears(18))) {
            throw new RuntimeException("Pessoa precisa ter pelo menos 18 anos!");
        }else if(dataNasc.isBefore(LocalDate.now().minusYears(100))) {
            throw new RuntimeException("Data de nascimento inválida");
        } 
        this.dataNasc = dataNasc;
    }

    public final void setCpf(String cpf) {
        if(cpf == null){
            throw new RuntimeException("cpf é obrigatório");
        }
        String regex = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
        if (!cpf.matches(regex)) {
            throw new RuntimeException("Formato de cpf inválido: " + cpf);
        }    
        this.cpf = cpf;
    }
    
    
}
