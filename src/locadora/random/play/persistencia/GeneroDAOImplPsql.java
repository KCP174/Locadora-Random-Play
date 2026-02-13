/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora.random.play.persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import locadora.random.play.Genero;

/**
 *
 * @author kauan
 */
public class GeneroDAOImplPsql implements IGeneroDAO{

    public GeneroDAOImplPsql() {
    }
    
    private Banco banco = new Banco();

    @Override
    public void inserir(Genero genero) {
        banco.conectar();
        String sql = "INSERT INTO genero (descricao) VALUES ('";
        sql += genero.getDescricao() + "');";
        banco.executarSQL(sql);
        banco.fechar();
    }

    @Override
    public List<Genero> consultar() {
        List<Genero> lista = new ArrayList<>();
        banco.conectar();
        String sql = "SELECT * FROM genero ORDER BY id;";
        ResultSet rs = banco.executarConsulta(sql);
        
        try {
            while(rs.next()){
                Genero registro = new Genero(rs.getString("descricao"));
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
    public List<Genero> consultarLimitado(int n) {
        List<Genero> lista = new ArrayList<>();
        banco.conectar();
        String sql = "SELECT * FROM genero ORDER BY id LIMIT ";
        sql += n + ";";
        ResultSet rs = banco.executarConsulta(sql);
        
        try {
            while(rs.next()){
                Genero registro = new Genero(rs.getString("descricao"));
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
    public List<Genero> buscar(String desc) {
        List<Genero> lista = new ArrayList<>();
        banco.conectar();
        String sql = "SELECT *, descricao ILIKE '%" + desc + "%' ";
        sql += "AS score FROM genero WHERE descricao ILIKE '%" + desc + "%' ";
        sql += "ORDER BY score DESC;";
        ResultSet rs = banco.executarConsulta(sql);
        
        try {
            while(rs.next()){
                Genero registro = new Genero(rs.getString("descricao"));
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
    public void atualizar(Genero genero) {
        banco.conectar();
        String sql = "UPDATE genero SET descricao = ?";
        sql += " WHERE id = ?;";
        List parametros = new ArrayList();
        parametros.add(genero.getDescricao());
        parametros.add(genero.getId());
        
        banco.executarPreparedStatement(sql, parametros);
        banco.fechar();
    }

    //Tá implementado mas não é utilizado no sistema
    //A ideia seria a de que Gêneros não sejam apagados do sistema
    @Override
    public void remover(Genero genero) {
        banco.conectar();        
        String sql = "DELETE FROM generos_filme WHERE id_genero = " + genero.getId() + ";";
        banco.executarSQL(sql);
        sql = "DELETE FROM genero WHERE id = " + genero.getId() + ";";
        banco.executarSQL(sql);
        banco.fechar();
    }

    
}
