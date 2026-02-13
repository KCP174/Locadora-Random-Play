/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package locadora.random.play.persistencia;

import java.util.List;
import locadora.random.play.Filme;
import locadora.random.play.Genero;

/**
 *
 * @author kauan
 */
public interface IFilmeDAO {
        
    public Filme buscaId(int i);
    
    public List<Genero> retornaGeneros(int idFilme);
    
    public void inserir(Filme filme);
    
    public List<Filme> consultar(boolean emEstoque);
    
    public List<Filme> consultarLimitado(int n, boolean emEstoque);

    public List<Filme> buscar(String titulo, boolean emEstoque);
    
    public void atualizar(Filme filme);
    
    public void remover(Filme filme);
}
