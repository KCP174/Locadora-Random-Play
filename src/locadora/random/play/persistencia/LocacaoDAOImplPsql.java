/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora.random.play.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import locadora.random.play.ItemLocacao;
import locadora.random.play.Locacao;

/**
 *
 * @author kauan
 */
//Essa classe implementa também o DAO da clase ItemLocacao
public class LocacaoDAOImplPsql implements ILocacao{
    private Banco banco = new Banco();
    
    
    @Override
    public void inserir(Locacao locacao, List<ItemLocacao> itens) {
        banco.conectar();
        String sqlLocacao = "INSERT INTO locacao (data_locacao, data_devolucao, valor_total, id_cliente, id_funcionario) VALUES (";
        sqlLocacao += "'" + locacao.getDataLocacao()+ "',";
        sqlLocacao += "'" + locacao.getDataDevolucao()+ "',";
        sqlLocacao += "'" + locacao.getValorTotal()+ "',";
        sqlLocacao += "'" + locacao.getIdCliente()+ "',";
        sqlLocacao += "'" + locacao.getIdFuncionario()+ "'";
        sqlLocacao += ") RETURNING id;";
        ResultSet rs = banco.executarConsulta(sqlLocacao);
        
        int ultimoId = -1;
        try {
            if (rs.next()) { 
                ultimoId = rs.getInt("id");
            }
        } catch (Exception erro){
            erro.printStackTrace();
            throw new RuntimeException(erro);
        }
       
        for (ItemLocacao i : itens){
            String sqlItens = "INSERT INTO itens_locacao (id_locacao, id_filme, valor_unitario) VALUES (";
            sqlItens += "'" + ultimoId + "',";
            sqlItens += "'" + i.getIdFilme() + "',";
            sqlItens += "'" + i.getValorLocacao()+ "'";
            sqlItens += ");";
            
            String sqlFilmes = "UPDATE filme SET qntd_estoque = qntd_estoque - 1  WHERE id = ";
            sqlFilmes += i.getIdFilme() + ";";
            
            banco.executarSQL(sqlItens);
            banco.executarSQL(sqlFilmes);
        }
    }

    @Override
    public List<Locacao> consultarLocacoes() {
        List<Locacao> lista = new ArrayList<>();
        banco.conectar();
        String sql = "SELECT * FROM locacao ORDER BY id;";
        ResultSet rs = banco.executarConsulta(sql);
        try {
            while(rs.next()){
                Locacao registro = new Locacao(LocalDate.parse(rs.getString("data_devolucao")), rs.getDouble("valor_total"), rs.getInt("id_cliente"), rs.getInt("id_funcionario"));
                registro.setDataLocacao(LocalDate.parse(rs.getString("data_locacao")));
                if(rs.getString("data_devolucao_real") != null){
                    registro.setDataDevolucaoReal(LocalDate.parse(rs.getString("data_devolucao_real")));
                }
                    
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
    
    public List<Locacao> consultarLocacoesAtivas() {
        List<Locacao> lista = new ArrayList<>();
        banco.conectar();
        String sql = "SELECT * FROM locacao WHERE data_devolucao_real IS null ORDER BY id;";
        ResultSet rs = banco.executarConsulta(sql);
        try {
            while(rs.next()){
                Locacao registro = new Locacao(LocalDate.parse(rs.getString("data_devolucao")), rs.getDouble("valor_total"), rs.getInt("id_cliente"), rs.getInt("id_funcionario"));
                registro.setDataLocacao(LocalDate.parse(rs.getString("data_locacao")));
                if(rs.getString("data_devolucao_real") != null){
                    registro.setDataDevolucaoReal(LocalDate.parse(rs.getString("data_devolucao_real")));
                }
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
    public List<Locacao> consultarLocacoesDoCliente(int idCliente) {
        banco.conectar();
        List<Locacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM locacao WHERE id_cliente = '" + idCliente + "' ORDER BY id;";
        ResultSet rs = banco.executarConsulta(sql);
        try {
            while(rs.next()){
                Locacao registro = new Locacao(LocalDate.parse(rs.getString("data_devolucao")), rs.getDouble("valor_total"), rs.getInt("id_cliente"), rs.getInt("id_funcionario"));
                registro.setDataLocacao(LocalDate.parse(rs.getString("data_locacao")));
                if(rs.getString("data_devolucao_real") != null){
                    registro.setDataDevolucaoReal(LocalDate.parse(rs.getString("data_devolucao_real")));
                }
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
    
    public List<Locacao> consultarLocacoesAtivasDoCliente(int idCliente) {
        List<Locacao> lista = new ArrayList<>();
        banco.conectar();
        String sql = "SELECT * FROM locacao WHERE id_cliente = " + idCliente + " AND data_devolucao_real IS null ORDER BY id;";
        ResultSet rs = banco.executarConsulta(sql);
        try {
            while(rs.next()){
                Locacao registro = new Locacao(LocalDate.parse(rs.getString("data_devolucao")), rs.getDouble("valor_total"), rs.getInt("id_cliente"), rs.getInt("id_funcionario"));
                registro.setDataLocacao(LocalDate.parse(rs.getString("data_locacao")));
                if(rs.getString("data_devolucao_real") != null){
                    registro.setDataDevolucaoReal(LocalDate.parse(rs.getString("data_devolucao_real")));
                }
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
    public List<Locacao> consultarLocacoesLimitado(int n) {
        List<Locacao> lista = new ArrayList<>();
        banco.conectar();
        String sql = "SELECT * FROM locacao ORDER BY id LIMIT ";
        sql += n;
        ResultSet rs = banco.executarConsulta(sql);
        
        try {
            while(rs.next()){
                Locacao registro = new Locacao(LocalDate.parse(rs.getString("data_devolucao")), rs.getDouble("valor_total"), rs.getInt("id_cliente"), rs.getInt("id_funcionario"));
                registro.setDataLocacao(LocalDate.parse(rs.getString("data_locacao")));
                if(rs.getString("data_devolucao_real") != null){
                    registro.setDataDevolucaoReal(LocalDate.parse(rs.getString("data_devolucao_real")));
                }
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
    public void remover(Locacao locacao) {
        banco.conectar();
        String sql = "DELETE FROM locacao WHERE id = " + locacao.getId() + ";";
        banco.executarSQL(sql);
        
        List<ItemLocacao> aSerRemovido = consultarFilmesLocadosLocacao(locacao.getId());
        for(ItemLocacao i : aSerRemovido){
            sql = "DELETE FROM itens_locacao WHERE id = " + i.getId()+ ";";
            banco.executarSQL(sql);
            sql = "UPDATE filme SET qntd_estoque = qntd_estoque + 1  WHERE id = " + i.getId()+ ";";
            banco.executarSQL(sql);
        }
        
        banco.fechar();
    }

    public List<ItemLocacao> consultarItens(int id) {
        List<ItemLocacao> lista = new ArrayList<>();
        banco.conectar();
        if (id != -1){
            String sql = "SELECT * FROM itens_locacao WHERE id_locacao = " + id + " ORDER BY id;";
            ResultSet rs = banco.executarConsulta(sql);
            try {
                while(rs.next()){
                    ItemLocacao registro = new ItemLocacao(rs.getInt("id"), rs.getInt("id_filme"), rs.getDouble("valor_unitario"));
                    registro.setId(rs.getInt("id"));
                    lista.add(registro);
                }   
            }catch (Exception erro){
                erro.printStackTrace();
                throw new RuntimeException(erro);
            }
        }else{
            String sql = "SELECT * FROM itens_locacao ORDER BY id;";
            ResultSet rs = banco.executarConsulta(sql);
            try {
                while(rs.next()){
                    ItemLocacao registro = new ItemLocacao(rs.getInt("id"), rs.getInt("id_filme"), rs.getDouble("valor_unitario"));
                    registro.setId(rs.getInt("id"));
                    lista.add(registro);
                }
            }catch (Exception erro){
                erro.printStackTrace();
                throw new RuntimeException(erro);
            }
        }
        
        banco.fechar();
        return lista;
    }
    
    public String retornaTitulo(int id){
        banco.conectar();
        ResultSet rs;
        rs = banco.executarConsulta("SELECT * FROM filme WHERE id = " + id);
        try{
            if(rs.next()){
                return rs.getString("titulo");
            }else{
                return null;
            }
        }catch (Exception erro){
            erro.printStackTrace();
            throw new RuntimeException(erro);
        }
    }
    
    @Override
    public List<ItemLocacao> consultarFilmesLocados() {
        List<ItemLocacao> lista = new ArrayList<>();
        banco.conectar();
        String sql = "SELECT * FROM itens_locacao ORDER BY id;";
        ResultSet rs = banco.executarConsulta(sql);
        try {
            while(rs.next()){
                ItemLocacao registro = new ItemLocacao(rs.getInt("id_locacao"), rs.getInt("id_filme"), rs.getDouble("valor_unitario"));
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

    //Consulta usando id da Locação
    @Override
    public List<ItemLocacao> consultarFilmesLocadosLocacao(int id) {
        banco.conectar();
        List<ItemLocacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM itens_locacao WHERE id_locacao = '" + id + "' ORDER BY id;";
        ResultSet rs = banco.executarConsulta(sql);
        try {
            while(rs.next()){
                ItemLocacao registro = new ItemLocacao(rs.getInt("id_locacao"), rs.getInt("id_filme"), rs.getDouble("valor_unitario"));
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
    public List<ItemLocacao> consultarFilmesLocadosCliente(int idCliente) {
        banco.conectar();
        List<Locacao> locacoesCliente = consultarLocacoesDoCliente(idCliente);
        List<ItemLocacao> filmesLocados = new ArrayList<>();
        for(Locacao l : locacoesCliente){
            filmesLocados.addAll(consultarFilmesLocadosLocacao(l.getId()));
        }
        banco.fechar();
        return filmesLocados;
    }
    
    public void devolver(int idLocacao) {
        banco.conectar();
        List<ItemLocacao> itens = consultarFilmesLocadosLocacao(idLocacao);
        
        String sqlLocacao = "UPDATE locacao SET data_devolucao_real = '" + LocalDate.now() + "' WHERE id = " + idLocacao;
        banco.executarSQL(sqlLocacao);
        
        for (ItemLocacao i : itens){
            String sqlItens = "UPDATE filme SET qntd_estoque = qntd_estoque + 1  WHERE id = ";
            sqlItens += i.getIdFilme() + ";";
            
            banco.executarSQL(sqlItens);
        }
    }
    
}