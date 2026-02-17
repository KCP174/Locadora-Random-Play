/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora.random.play.persistencia;

import java.util.ArrayList;
import java.util.List;
import locadora.random.play.Cliente;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import locadora.random.play.Locacao;

/**
 *
 * @author kauan
 */
public class ClienteDAOImplPsql implements IClienteDAO {
    
    private Banco banco = new Banco();
    private LocacaoDAOImplPsql bancoLocacoes = new LocacaoDAOImplPsql();
    
    @Override
    public void inserir(Cliente cliente) {
        banco.conectar();
        String sql = "INSERT INTO cliente (nome, cpf, endereco, data_nascimento, email) VALUES (";
        sql += "'" + cliente.getNome() + "',";
        sql += "'" + cliente.getCpf()+ "',";
        sql += "'" + cliente.getEndereco()+ "',";
        sql += "'" + cliente.getDataNasc() + "',";
        sql += "'" + cliente.getEmail()+ "'";
        sql += ");";
        banco.executarSQL(sql);
        banco.fechar();
    }
    
    @Override
    public String retornaNome(int id){
        banco.conectar();
        ResultSet rs = banco.executarConsulta("SELECT * FROM cliente WHERE id = " + id);
        try{    
            if(rs.next()){
                return rs.getString("nome");
            }else{
                return null;
            }
        }catch (Exception erro){
            erro.printStackTrace();
            throw new RuntimeException(erro);
        }
    }
    
    @Override
    public List<Cliente> consultar() {
        List<Cliente> lista = new ArrayList<>();
        banco.conectar();
        String sql = "SELECT * FROM cliente ORDER BY id;";
        ResultSet rs = banco.executarConsulta(sql);
        
        try {
            while(rs.next()){
                Cliente registro = new Cliente(rs.getString("nome"), LocalDate.parse(rs.getString(("data_nascimento"))), rs.getString("cpf"), rs.getString("endereco"), rs.getString("email"));
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
    public List<Cliente> consultarLimitado(int n) {
        List<Cliente> lista = new ArrayList<>();
        banco.conectar();
        String sql = "SELECT * FROM cliente ORDER BY id LIMIT ";
        sql += n + ";";
        ResultSet rs = banco.executarConsulta(sql);
        
        try {
            while(rs.next()){
                Cliente registro = new Cliente(rs.getString("nome"), LocalDate.parse(rs.getString(("data_nascimento"))), rs.getString("cpf"), rs.getString("endereco"), rs.getString("email"));
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
    public List<Cliente> buscarNome(String nome) {
        List<Cliente> lista = new ArrayList<>();
        banco.conectar();
        String sql = "SELECT *, nome ILIKE '%" + nome + "%' ";
        sql += "AS score FROM cliente WHERE nome ILIKE '%" + nome + "%' ";
        sql += "ORDER BY score DESC;";
        ResultSet rs = banco.executarConsulta(sql);
        
        try {
            while(rs.next()){
                Cliente registro = new Cliente(rs.getString("nome"), LocalDate.parse(rs.getString(("data_nascimento"))), rs.getString("cpf"), rs.getString("endereco"), rs.getString("email"));
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
    public Cliente buscarPorCpf(String cpf) {
        Cliente registro = null;
        banco.conectar();
        String sql = "SELECT * FROM cliente WHERE cpf = '" + cpf + "';";
        ResultSet rs = banco.executarConsulta(sql);
        
        try {
            if(rs.next()){
                registro = new Cliente(rs.getString("nome"), LocalDate.parse(rs.getString(("data_nascimento"))), rs.getString("cpf"), rs.getString("endereco"), rs.getString("email"));
                registro.setId(rs.getInt("id"));
            }
        }catch (Exception erro){
            erro.printStackTrace();
            throw new RuntimeException(erro);
        }
        
        banco.fechar();
        return registro;
    }

    @Override
    public void atualizar(Cliente cliente) {
        banco.conectar();
        String sql = "UPDATE cliente SET nome = ?, cpf = ?, endereco = ?, data_nascimento = ?, email = ?";
        sql += " WHERE id = ?;";
        List parametros = new ArrayList();
        parametros.add(cliente.getNome());
        parametros.add(cliente.getCpf());
        parametros.add(cliente.getEndereco());
        parametros.add(cliente.getDataNasc());
        parametros.add(cliente.getEmail());
        parametros.add(cliente.getId());
        
        banco.executarPreparedStatement(sql, parametros);
        banco.fechar();
    }

    @Override
    public void remover(Cliente cliente) {
        banco.conectar();
        //Verifica se o cliente tem locações ativas e impede a remoção caso tenha.
        List<Locacao> locacoesCliente = bancoLocacoes.consultarLocacoesAtivasDoCliente(cliente.getId());
        try{
            //getFirst() da um throw caso não tenha o primeiro elemento
            locacoesCliente.getFirst();
            throw new RuntimeException("Cliente ainda tem locações ativas!");
        }catch (NoSuchElementException e){ 
            String sql = "DELETE FROM cliente WHERE id = " + cliente.getId() + ";";
            banco.executarSQL(sql);
        }
        
        banco.fechar();
    }

    
}
