/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora.random.play;

/**
 *
 * @author kauan
 */
//Filme quando é locado
public class ItemLocacao extends EntidadeBase{
    private int idLocacao;
    private int idFilme;
    private double valorLocacao;

    public ItemLocacao(int idLocacao, int idFilme, double valorLocacao) {
        this.idLocacao = idLocacao;
        this.idFilme = idFilme;
        this.valorLocacao = valorLocacao;
    }

    public ItemLocacao() {
    }
    
    public int getIdLocacao() {
        return idLocacao;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public double getValorLocacao() {
        return valorLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public void setValorLocacao(double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }
    
    
}
