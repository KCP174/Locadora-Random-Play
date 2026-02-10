/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora.random.play;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kauan
 */
public class Locacao extends EntidadeBase{
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private LocalDate dataDevolucaoReal;
    private double valorTotal;
    private int idCliente;
    private int idFuncionario;
    private List<ItemLocacao> itensLocados = new ArrayList<>();

    public Locacao(LocalDate dataDevolucao, double valorTotal, int idCliente, int idFuncionario) {
        this.dataLocacao = LocalDate.now();
        this.dataDevolucao = dataDevolucao;
        this.valorTotal = valorTotal;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
    }

    public Locacao() {
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }
    
    public int getIdCliente() {
        return idCliente;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public List<ItemLocacao> getItensLocados() {
        return itensLocados;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public void setItensLocados(List<ItemLocacao> itensLocados) {
        this.itensLocados = itensLocados;
    }

    
    
    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    
    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    
}
