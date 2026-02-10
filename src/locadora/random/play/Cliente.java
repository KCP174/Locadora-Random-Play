/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora.random.play;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author kauan
 */
public class Cliente extends Pessoa{

    private String endereco;
    private String email;
    private static final List<String> DOMINIOS_PERMITIDOS = Arrays.asList(
        "gmail.com", "outlook.com", "hotmail.com", "yahoo.com", "icloud.com", "gmail.com.br", "outlook.com.br"
    );
    
    
    public Cliente() {
    }
    
    
    public Cliente(String nome, String dataNasc, String cpf, String endereco, String email) {
        setNome(nome);
        setDataNasc(dataNasc);
        setCpf(cpf);
        setEndereco(endereco);        
        setEmail(email);        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null){
            throw new RuntimeException("Email é obrigatório");
        }
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!email.matches(regex)) {
            throw new RuntimeException("Formato de email inválido: " + email);
        }
        String dominio = email.split("@")[1];
        if(!DOMINIOS_PERMITIDOS.contains(dominio)){
            throw new RuntimeException("Domínio inválido");
        }
        
        this.email = email;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public final void setEndereco(String endereco) {
        if(endereco == null){
            throw new RuntimeException("endereco é obrigatório");
        }
        this.endereco = endereco;
    }

    
}
