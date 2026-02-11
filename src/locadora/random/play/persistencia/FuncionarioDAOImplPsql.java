/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora.random.play.persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import locadora.random.play.Funcionario;

/**
 *
 * @author kauan
 */
public class FuncionarioDAOImplPsql implements IFuncionarioDAO{
    
    private Banco banco = new Banco();

    @Override
    public void inserir(Funcionario funcionario) {
        banco.conectar();
        String sql = "INSERT INTO funcionario (nome, login, senha, data_nascimento, cpf, admin) VALUES (";
        sql += "'" + funcionario.getNome() + "',";
        sql += "'" + funcionario.getLogin() + "',";
        sql += "'" + funcionario.getSenha() + "',";
        sql += "'" + funcionario.getDataNasc() + "',";
        sql += "'" + funcionario.getCpf()+ "',";
        sql += "'" + funcionario.isAdminPerm()+ "'";
        sql += ");";
        banco.executarSQL(sql);
        banco.fechar();
    }
    
    @Override
    public Funcionario logar(String login, String senha) {
        banco.conectar();
        String sql = "SELECT * FROM funcionario WHERE login = '" + login + "' AND senha = '" + senha + "';";
        ResultSet rs = banco.executarConsulta(sql);
        try {
            if(rs.next()){
                Funcionario registro = new Funcionario(rs.getString("nome"), rs.getString("login"), rs.getString("senha"), rs.getString("data_nascimento"), rs.getString("cpf"), rs.getBoolean("admin"));
                registro.setId(rs.getInt("id"));
                return registro;
            }
        }catch (Exception erro){
            erro.printStackTrace();
            throw new RuntimeException(erro);
        }
        banco.fechar();
        return null; 
    }

    @Override
    public List<Funcionario> consultar() {
        List<Funcionario> lista = new ArrayList<>();
        banco.conectar();
        String sql = "SELECT * FROM funcionario ORDER BY id;";
        ResultSet rs = banco.executarConsulta(sql);
        
        try {
            while(rs.next()){
                Funcionario registro = new Funcionario(rs.getString("nome"), rs.getString("login"), rs.getString("senha"), rs.getString("data_nascimento"), rs.getString("cpf"), rs.getBoolean("admin"));
                registro.setId(rs.getInt("id"));
                lista.add(registro);
            }
        }catch (Exception erro){
            erro.printStackTrace();
            throw new RuntimeException(erro);
        }
        
        banco.fechar();
        return lista;
    }
    
    @Override
    public List<Funcionario> consultarLimitado(int n) {
        List<Funcionario> lista = new ArrayList<>();
        banco.conectar();
        String sql = "SELECT * FROM funcionario ORDER BY id LIMIT ";
        sql += n;
        ResultSet rs = banco.executarConsulta(sql);
        
        try {
            while(rs.next()){
                Funcionario registro = new Funcionario(rs.getString("nome"), rs.getString("login"), rs.getString("senha"), rs.getString("data_nascimento"), rs.getString("cpf"), rs.getBoolean("admin"));
                registro.setId(rs.getInt("id"));
                lista.add(registro);
            }
        }catch (Exception erro){
            erro.printStackTrace();
            throw new RuntimeException(erro);
        }
        
        banco.fechar();
        return lista;
    }
    
    @Override
    public List<Funcionario> buscar(String nome) {
        List<Funcionario> lista = new ArrayList<>();
        banco.conectar();
        String sql = "SELECT *, nome ILIKE '%" + nome + "%' ";
        sql += "AS score FROM funcionario WHERE nome ILIKE '%" + nome + "%' ";
        sql += "ORDER BY score DESC;";
        ResultSet rs = banco.executarConsulta(sql);
        
        try {
            while(rs.next()){
                Funcionario registro = new Funcionario(rs.getString("nome"), rs.getString("login"), rs.getString("senha"), rs.getString("data_nascimento"), rs.getString("cpf"), rs.getBoolean("admin"));
                registro.setId(rs.getInt("id"));
                lista.add(registro);
            }
        }catch (Exception erro){
            erro.printStackTrace();
            throw new RuntimeException(erro);
        }
        
        banco.fechar();
        return lista;
    }

    @Override
    public void atualizar(Funcionario funcionario) {
        banco.conectar();
        String sql = "UPDATE funcionario SET nome = ?, login = ?, senha = ?, data_nascimento = ?, cpf = ?, admin = ?";
        sql += " WHERE id = ?;";
        List parametros = new ArrayList();
        parametros.add(funcionario.getNome());
        parametros.add(funcionario.getLogin());
        parametros.add(funcionario.getSenha());
        parametros.add(funcionario.getDataNasc());
        parametros.add(funcionario.getCpf());
        parametros.add(funcionario.isAdminPerm());
        parametros.add(funcionario.getId());
        
        banco.executarPreparedStatement(sql, parametros);
        banco.fechar();
    }

    @Override
    public void remover(Funcionario funcionario) {
        banco.conectar();
        String sql = "DELETE FROM funcionario WHERE id = " + funcionario.getId() + ";";
        banco.executarSQL(sql);
        banco.fechar();
    }
    
}
