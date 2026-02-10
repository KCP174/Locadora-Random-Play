/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package locadora.random.play.persistencia;

import java.util.List;
import locadora.random.play.Funcionario;

/**
 *
 * @author kauan
 */
public interface IFuncionarioDAO {
    
    public void inserir(Funcionario funcionario);
    
    public Funcionario logar(String nome, String senha);
    
    public List<Funcionario> consultar();
    
    public List<Funcionario> consultarLimitado(int n);
    
    public List<Funcionario> buscar(String nome);
    
    public void atualizar(Funcionario funcionario);
    
    public void remover(Funcionario funcionario);
}
