/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora.random.play;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kauan
 */
public final class Filme extends EntidadeBase{
    private String titulo;
    private String autor;
    private String descricao;
    private int duracao;
    private double valorLocacao;
    private int qntdEstoque;
    private List<Genero> generosFilme = new ArrayList<>();
    
    
    
    public Filme() {
    }

    public Filme(String titulo, String autor, String descricao, double valorLocacao, int qntdEstoque, int duracao, List<Genero> generosFilme) {
        setTitulo(titulo);
        setAutor(autor);
        setDescricaoFilme(descricao);
        setDuracao(duracao);
        setValorLocacao(valorLocacao);
        setQntdEstoque(qntdEstoque);
        setGenerosFilme(generosFilme);
    }
    
    public ItemLocacao toItem(){
        ItemLocacao item = new ItemLocacao();
        item.setIdFilme(getId());
        item.setValorLocacao(valorLocacao);
        return item;
    }
        public void setDescricao(String descricao) {
        //Verificars fodas
        this.descricao = descricao;
    }

    public void setGenerosFilme(List<Genero> generosFilme) {
        this.generosFilme = generosFilme;
    }

    public List<Genero> getGenerosFilme() {
        return generosFilme;
    }
    
    public String generosToString(){
        String generosStr = "";
        for (Genero genero : generosFilme){
            if (generosStr == ""){
                generosStr = genero.getDescricao();
            }else{  
                generosStr += ", " + genero.getDescricao();
            }
        }
        return generosStr;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQntdEstoque() {
        return qntdEstoque;
    }

    public double getValorLocacao() {
        return valorLocacao;
    }
    
    public String getAutor() {
        return autor;
    }

    public int getDuracao() {
        return duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setGenero(Genero genero) {
        if (genero == null){
            throw new RuntimeException("Gênero inválido");
        }
        generosFilme.add(genero);
    }

    public void setDescricaoFilme(String descricao) {
        if (descricao.equals("") || descricao.trim().length() < 5){
            throw new RuntimeException("Descrição inválida");
        }
        this.descricao = descricao;
    }

    public void setQntdEstoque(int qntdEstoque) {
        if (qntdEstoque < 0){
            throw new RuntimeException("Quantidade inválida");
        }
        this.qntdEstoque = qntdEstoque;
    }
    
    public void setValorLocacao(double valorLocacao) {
        if (valorLocacao <= 0){
            throw new RuntimeException("Valor Locacao inválido");
        }
        this.valorLocacao = valorLocacao;
    }

    public void setAutor(String autor) {
        if (autor.equals("")){
            throw new RuntimeException("Autor inválido");
        }
        this.autor = autor;
    }

    public void setDuracao(int duracao) {
        if (duracao <= 0){
            throw new RuntimeException("Duração inválida");
    }
        this.duracao = duracao;
    }

    public void setTitulo(String novoTitulo) {
        if (novoTitulo.equals("") || novoTitulo.trim().length() < 5){
            throw new RuntimeException("Titulo inválido");
        }
        titulo = novoTitulo;
    }
    
}
