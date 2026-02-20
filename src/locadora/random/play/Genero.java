/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora.random.play;

/**
 *
 * @author kauan
 */
public class Genero extends EntidadeBase{
    private String descricao;

    public Genero() {
    }

    public Genero(String descricao) {
        setDescricao(descricao);
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String novaDescricao) {
        if (novaDescricao == null || novaDescricao.isBlank()){
            throw new RuntimeException("Descrição inválida");
        }
        descricao = novaDescricao;
    }
    
}
