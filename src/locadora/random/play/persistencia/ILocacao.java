/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package locadora.random.play.persistencia;

import java.util.List;
import locadora.random.play.ItemLocacao;
import locadora.random.play.Locacao;

/**
 *
 * @author kauan
 */
public interface ILocacao {
    
    public void inserir(Locacao locacao, List<ItemLocacao> itens);
    
    public List<Locacao> consultarLocacoes();
    
    public List<Locacao> consultarLocacoesDoCliente(int idCliente);
    
    public List<ItemLocacao> consultarFilmesLocados();
    
    public List<ItemLocacao> consultarFilmesLocadosLocacao(int id);
    
    public List<ItemLocacao> consultarFilmesLocadosCliente(int idCliente);
    
    public List<Locacao> consultarLocacoesLimitado(int n);
    
    public void remover(Locacao locacao);
    
    public void devolver(int idLocacao);
}
