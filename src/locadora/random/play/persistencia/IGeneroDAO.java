/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package locadora.random.play.persistencia;

import java.util.List;
import locadora.random.play.Genero;

/**
 *
 * @author kauan
 */
public interface IGeneroDAO {
    
    public void inserir(Genero genero);
    
    public List<Genero> consultar();
    
    public List<Genero> consultarLimitado(int n);
    
    public List<Genero> buscar(String nome);
    
    public void atualizar(Genero genero);
    
    public void remover(Genero genero);
}
