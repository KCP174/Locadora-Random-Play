/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora.random.play.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locadora.random.play.Filme;
import locadora.random.play.Filme;
import locadora.random.play.Genero;

/**
 *
 * @author kauan
 */
public class FilmeDAOImplPsql implements IFilmeDAO{
    
    private Banco banco = new Banco();

    private List<Genero> retornaGeneros(int idFilme){
        List<Genero> generosFilme = new ArrayList<>();
        String sql = "SELECT * FROM generos_filme WHERE id_filme = " + idFilme;
        ResultSet rs = banco.executarConsulta(sql);
        
        try {
            while(rs.next()){
                Genero g = new Genero();
                g.setId(rs.getInt("id_genero"));
                
                sql = "SELECT * FROM genero WHERE id = " + g.getId();
                ResultSet rsGenero = banco.executarConsulta(sql);
                if(rsGenero.next()){
                    g.setDescricao(rsGenero.getString("descricao"));
                }
                
                generosFilme.add(g);
            }
        } catch (Exception erro){
            erro.printStackTrace();
            throw new RuntimeException(erro);
        }
        
        return generosFilme;
    }
    
    
    @Override
    public Filme buscaId(int i){
        banco.conectar();
        String sql = "SELECT * FROM filme WHERE id = " + i + ";";
        ResultSet rs = banco.executarConsulta(sql);
        Filme registro = new Filme();
        try {
            if(rs.next()){
                registro = new Filme(rs.getString("titulo"), rs.getString("autor"), rs.getString("descricao"), rs.getDouble("valor_locacao"), rs.getInt("qntd_estoque"), rs.getInt("duracao"), retornaGeneros(i));
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
    public void inserir(Filme filme) {
        banco.conectar();
        String sql = "INSERT INTO filme (titulo, autor, descricao, valor_locacao, qntd_estoque, duracao) VALUES (";
        sql += "'" + filme.getTitulo()+ "',";
        sql += "'" + filme.getAutor()+ "',";
        sql += "'" + filme.getDescricao()+ "',";
        sql += "'" + filme.getValorLocacao()+ "',";
        sql += "'" + filme.getQntdEstoque()+ "',";
        sql += "'" + filme.getDuracao()+ "'";
        sql += ") RETURNING id;";
        ResultSet rs = banco.executarConsulta(sql);
        
        int ultimoId = -1;
        try {
            if (rs.next()) { 
                ultimoId = rs.getInt("id");
            }
        } catch (Exception erro){
            erro.printStackTrace();
            throw new RuntimeException(erro);
        }
        
        for (Genero g : filme.getGenerosFilme()){
            sql = "INSERT INTO generos_filme (id_filme, id_genero) VALUES (";
            sql += ultimoId + ", ";
            sql += g.getId() + ");";
            banco.executarSQL(sql);
        }
        
        banco.fechar();
    }

    @Override
    public List<Filme> consultar(boolean emEstoque) {
        List<Filme> lista = new ArrayList<>();
        banco.conectar();
        String sql;
        if (emEstoque){
            sql = "SELECT * FROM filme WHERE qntd_estoque > 0 ORDER BY id;";
        } else{
            sql = "SELECT * FROM filme ORDER BY id;";
        }
        
        ResultSet rs = banco.executarConsulta(sql);
        
        try {
            while(rs.next()){
                Filme registro = new Filme(rs.getString("titulo"), rs.getString("autor"), rs.getString("descricao"), rs.getDouble("valor_locacao"), rs.getInt("qntd_estoque"), rs.getInt("duracao"), retornaGeneros(rs.getInt("id")));
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
    public List<Filme> consultarLimitado(int n, boolean emEstoque) {
        List<Filme> lista = new ArrayList<>();
        banco.conectar();
        
        String sql;
        if (emEstoque){
            sql = "SELECT * FROM filme WHERE qntd_estoque > 0 ORDER BY id LIMIT ";
        } else{
            sql = "SELECT * FROM filme ORDER BY id LIMIT ";
        }
        sql += n;
        ResultSet rs = banco.executarConsulta(sql);
        
        try {
            while(rs.next()){
                Filme registro = new Filme(rs.getString("titulo"), rs.getString("autor"), rs.getString("descricao"), rs.getDouble("valor_locacao"), rs.getInt("qntd_estoque"), rs.getInt("duracao"), retornaGeneros(rs.getInt("id")));
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
    public List<Filme> buscar(String titulo, boolean emEstoque) {
        List<Filme> lista = new ArrayList<>();
        banco.conectar();
        String sql;
        if (emEstoque){
            sql = "SELECT *, titulo ILIKE '%" + titulo + "%' ";
            sql += "AS score FROM filme WHERE titulo ILIKE '%" + titulo + "%' AND qntd_estoque > 0 ";
            sql += "ORDER BY score DESC;";
        } else{
            sql = "SELECT *, titulo ILIKE '%" + titulo + "%' ";
            sql += "AS score FROM filme WHERE titulo ILIKE '%" + titulo + "%' ";
            sql += "ORDER BY score DESC;";
        }
        
        ResultSet rs = banco.executarConsulta(sql);
        
        try {
            while(rs.next()){
                Filme registro = new Filme(rs.getString("titulo"), rs.getString("autor"), rs.getString("descricao"), rs.getDouble("valor_locacao"), rs.getInt("qntd_estoque"), rs.getInt("duracao"), retornaGeneros(rs.getInt("id")));
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
    public void atualizar(Filme filme) {
        banco.conectar();
        String sql = "UPDATE filme SET titulo = ?, autor = ?, descricao = ?, valor_locacao = ?, qntd_estoque = ?, duracao = ?";
        sql += " WHERE id = ?;";
        List parametros = new ArrayList();
        parametros.add(filme.getTitulo());
        parametros.add(filme.getAutor());
        parametros.add(filme.getDescricao());
        parametros.add(filme.getValorLocacao());
        parametros.add(filme.getQntdEstoque());
        parametros.add(filme.getDuracao());
        parametros.add(filme.getId());
        banco.executarPreparedStatement(sql, parametros);
        
        
        List<Genero> generosFilme = retornaGeneros(filme.getId());
        for(Genero g : generosFilme){
            sql = "DELETE FROM generos_filme WHERE id_genero = " + g.getId() + " AND id_filme = " + filme.getId();
            banco.executarSQL(sql);
        }
        
        for (Genero g : filme.getGenerosFilme()){
            sql = "INSERT INTO generos_filme (id_filme, id_genero) VALUES (";
            sql += filme.getId() + ", ";
            sql += g.getId() + ");";
            banco.executarSQL(sql);
        }
        
        banco.fechar();
    }

    @Override
    public void remover(Filme filme) {
        banco.conectar();
        String sql = "DELETE FROM filme WHERE id = " + filme.getId() + ";";
        banco.executarSQL(sql);
        banco.fechar();
    }
    
}
