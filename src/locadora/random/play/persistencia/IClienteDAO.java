/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package locadora.random.play.persistencia;

import java.util.List;
import locadora.random.play.Cliente;

/**
 *
 * @author kauan
 */
public interface IClienteDAO {
 
    public void inserir(Cliente cliente);
    
    public String retornaNome(int id);
    
    public List<Cliente> consultar();
    
    public List<Cliente> consultarLimitado(int n);
    
    public List<Cliente> buscar(String nome);
    
    public Cliente buscarCpf(String cpf);
    
    public void atualizar(Cliente cliente);
    
    public void remover(Cliente cliente);
}
