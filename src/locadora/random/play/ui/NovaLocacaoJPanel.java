/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package locadora.random.play.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import locadora.random.play.Cliente;
import locadora.random.play.Filme;
import locadora.random.play.ItemLocacao;
import locadora.random.play.Locacao;
import locadora.random.play.persistencia.ClienteDAOImplPsql;
import locadora.random.play.persistencia.FilmeDAOImplPsql;
import locadora.random.play.persistencia.LocacaoDAOImplPsql;

/**
 *
 * @author kauan
 */
public class NovaLocacaoJPanel extends javax.swing.JPanel {
    
    private LocacaoDAOImplPsql bancoLocacao = new LocacaoDAOImplPsql();
    private ClienteDAOImplPsql bancoCliente = new ClienteDAOImplPsql();
    private FilmeDAOImplPsql bancoFilme = new FilmeDAOImplPsql();
    private List<Filme> carrinho = new ArrayList<>();
    private LocalDate dataLocacao = LocalDate.now();
    private LocalDate dataDevolucao;
    private Double valorTotal;

    /**
     * Creates new form LocacaoJPanel
     */
    public NovaLocacaoJPanel() {
        initComponents();
        carregarTabelaClientes();
        carregarTabelaFilmes();
        calculaDias(false);
    }
    
    private void calculaDias(boolean insercaoDireta){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataLocacaoJFormattedTextField.setText(dataLocacao.format(formato));
        if(insercaoDireta){
            dataDevolucao = LocalDate.parse(dataDevolucaoJFormattedTextField.getText(), formato);
            diasJSpinner.setValue((int) ChronoUnit.DAYS.between(dataLocacao, dataDevolucao));
        }else{
            dataDevolucao = dataLocacao.plusDays((int) diasJSpinner.getValue());
            dataDevolucaoJFormattedTextField.setText(dataDevolucao.format(formato));
        }
    }
    
    private void carregarTabelaClientes(){
        DefaultTableModel dfm = (DefaultTableModel) clientesJTable.getModel();
        dfm.setRowCount(0);
        
        List<Cliente> lista = bancoCliente.consultarLimitado(20);
        for (Cliente cliente : lista) {
            Object[] linha = new Object[3];
            linha[0] = cliente.getId();
            linha[1] = cliente.getNome();
            linha[2] = cliente.getCpf();
            dfm.addRow(linha);
        }
    }
    
    private void carregarCarrinho(){
        DefaultTableModel dfm = (DefaultTableModel) carrinhoJTable.getModel();
        dfm.setRowCount(0);
        valorTotal = 0.00;
        int dias = 1;

        try{
            for(Filme f : carrinho){
                Object[] linha = new Object[2];
                linha[0] = f.getTitulo();
                linha[1] = f.getValorLocacao();
                dfm.addRow(linha);
                valorTotal += f.getValorLocacao();
            }
            dias = (int) diasJSpinner.getValue();
            if (dias <= 0){
                throw new NumberFormatException();
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(diasJSpinner, "Locação precisa ter pelo menos 1 dia!");
            diasJSpinner.setValue(1);
        }
        
        dias = (int) diasJSpinner.getValue();
        valorTotal *= dias;
        BigDecimal bd = new BigDecimal(valorTotal);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        valorTotal = bd.doubleValue();
        valorTotalJTextField.setText("R$" + valorTotal.toString());
    }
    
    private void resultaBuscaNome(String nome){
        DefaultTableModel dfm = (DefaultTableModel) clientesJTable.getModel();
        dfm.setRowCount(0);
        
        List<Cliente> lista = bancoCliente.buscar(nome);
        for (Cliente cliente : lista) {
            Object[] linha = new Object[3];
            linha[0] = cliente.getId();
            linha[1] = cliente.getNome();
            linha[2] = cliente.getCpf();
            dfm.addRow(linha);
        }
    }
    private void carregarTabelaFilmes(){
        DefaultTableModel dfm = (DefaultTableModel) filmesJTable.getModel();
        dfm.setRowCount(0);
        List<Filme> lista = bancoFilme.consultar(true);
        for (Filme filme : lista) {
            Object[] linha = new Object[4];
            linha[0] = filme.getId();
            linha[1] = filme.getTitulo();
            linha[2] = filme.generosToString();
            linha[3] = filme.getValorLocacao();
            dfm.addRow(linha);
        }
    }
    private void resultaBuscaFilmesNome(String titulo){
        DefaultTableModel dfm = (DefaultTableModel) filmesJTable.getModel();
        dfm.setRowCount(0);
        
        List<Filme> lista = bancoFilme.buscar(titulo, true);
        for (Filme filme : lista) {
           Object[] linha = new Object[4];
           linha[0] = filme.getId();
           linha[1] = filme.getTitulo();
           linha[2] = filme.generosToString();
           linha[3] = filme.getValorLocacao();
           dfm.addRow(linha);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        confirmaExclJDialog = new javax.swing.JDialog();
        simExclJButton = new javax.swing.JButton();
        naoExclJButton = new javax.swing.JButton();
        textoExclJLabel = new javax.swing.JLabel();
        confirmaLimparJDialog = new javax.swing.JDialog();
        simLimparJButton = new javax.swing.JButton();
        naoLimparJButton = new javax.swing.JButton();
        textoLimparJLabel = new javax.swing.JLabel();
        tituloJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        clientesJTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        filmesJTable = new javax.swing.JTable();
        removerJButton = new javax.swing.JButton();
        adicionarJButton = new javax.swing.JButton();
        buscaClienteJTextField = new javax.swing.JTextField();
        buscaClienteJButton = new javax.swing.JButton();
        buscaFilmeJTextField = new javax.swing.JTextField();
        buscaFilmeJButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        carrinhoJTable = new javax.swing.JTable();
        finalizarJButton = new javax.swing.JButton();
        valorTotalJLabel = new javax.swing.JLabel();
        valorTotalJTextField = new javax.swing.JTextField();
        carrinhoJLabel = new javax.swing.JLabel();
        limparJButton = new javax.swing.JButton();
        diasJLabel = new javax.swing.JLabel();
        diasJSpinner = new javax.swing.JSpinner();
        dataLocacaoJLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dataDevolucaoJFormattedTextField = new javax.swing.JFormattedTextField();
        dataLocacaoJFormattedTextField = new javax.swing.JFormattedTextField();

        confirmaExclJDialog.setSize(new java.awt.Dimension(330, 190));

        simExclJButton.setText("Sim");
        simExclJButton.addActionListener(this::simExclJButtonActionPerformed);

        naoExclJButton.setText("Não");
        naoExclJButton.addActionListener(this::naoExclJButtonActionPerformed);

        textoExclJLabel.setText("TEXTO DE EXCLUSÃO");

        javax.swing.GroupLayout confirmaExclJDialogLayout = new javax.swing.GroupLayout(confirmaExclJDialog.getContentPane());
        confirmaExclJDialog.getContentPane().setLayout(confirmaExclJDialogLayout);
        confirmaExclJDialogLayout.setHorizontalGroup(
            confirmaExclJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmaExclJDialogLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(confirmaExclJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoExclJLabel)
                    .addGroup(confirmaExclJDialogLayout.createSequentialGroup()
                        .addComponent(simExclJButton)
                        .addGap(39, 39, 39)
                        .addComponent(naoExclJButton)))
                .addContainerGap(274, Short.MAX_VALUE))
        );
        confirmaExclJDialogLayout.setVerticalGroup(
            confirmaExclJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmaExclJDialogLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(textoExclJLabel)
                .addGap(40, 40, 40)
                .addGroup(confirmaExclJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simExclJButton)
                    .addComponent(naoExclJButton))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        confirmaLimparJDialog.setSize(new java.awt.Dimension(330, 190));

        simLimparJButton.setText("Sim");
        simLimparJButton.addActionListener(this::simLimparJButtonActionPerformed);

        naoLimparJButton.setText("Não");
        naoLimparJButton.addActionListener(this::naoLimparJButtonActionPerformed);

        textoLimparJLabel.setText("Remover todos itens do Carrinho?");

        javax.swing.GroupLayout confirmaLimparJDialogLayout = new javax.swing.GroupLayout(confirmaLimparJDialog.getContentPane());
        confirmaLimparJDialog.getContentPane().setLayout(confirmaLimparJDialogLayout);
        confirmaLimparJDialogLayout.setHorizontalGroup(
            confirmaLimparJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmaLimparJDialogLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(confirmaLimparJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoLimparJLabel)
                    .addGroup(confirmaLimparJDialogLayout.createSequentialGroup()
                        .addComponent(simLimparJButton)
                        .addGap(39, 39, 39)
                        .addComponent(naoLimparJButton)))
                .addContainerGap(274, Short.MAX_VALUE))
        );
        confirmaLimparJDialogLayout.setVerticalGroup(
            confirmaLimparJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmaLimparJDialogLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(textoLimparJLabel)
                .addGap(40, 40, 40)
                .addGroup(confirmaLimparJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simLimparJButton)
                    .addComponent(naoLimparJButton))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        tituloJLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tituloJLabel.setText("NOVA LOCAÇÃO");

        clientesJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "CPF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(clientesJTable);

        filmesJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Título", "Gêneros", "Valor Locação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(filmesJTable);

        removerJButton.setText("Remover do Carrinho");
        removerJButton.addActionListener(this::removerJButtonActionPerformed);

        adicionarJButton.setText("Adicionar ao Carrinho");
        adicionarJButton.addActionListener(this::adicionarJButtonActionPerformed);

        buscaClienteJButton.setText("Buscar Cliente");
        buscaClienteJButton.addActionListener(this::buscaClienteJButtonActionPerformed);

        buscaFilmeJButton.setText("Buscar Filme");
        buscaFilmeJButton.addActionListener(this::buscaFilmeJButtonActionPerformed);

        carrinhoJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título", "Valor Locação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(carrinhoJTable);

        finalizarJButton.setText("Finalizar");
        finalizarJButton.addActionListener(this::finalizarJButtonActionPerformed);

        valorTotalJLabel.setText("VALOR TOTAL:");

        valorTotalJTextField.setEditable(false);
        valorTotalJTextField.setFocusable(false);

        carrinhoJLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        carrinhoJLabel.setText("CARRINHO");
        carrinhoJLabel.setToolTipText("");

        limparJButton.setText("Limpar");
        limparJButton.addActionListener(this::limparJButtonActionPerformed);

        diasJLabel.setText("Qntd Dias:");

        diasJSpinner.setValue(1);
        diasJSpinner.addChangeListener(this::diasJSpinnerStateChanged);
        diasJSpinner.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                diasJSpinnerInputMethodTextChanged(evt);
            }
        });

        dataLocacaoJLabel.setText("Data Locação:");

        jLabel1.setText("---->");

        jLabel2.setText("Data Devolução:");

        try {
            dataDevolucaoJFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dataDevolucaoJFormattedTextField.addActionListener(this::dataDevolucaoJFormattedTextFieldActionPerformed);

        dataLocacaoJFormattedTextField.setEditable(false);
        try {
            dataLocacaoJFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dataLocacaoJFormattedTextField.setEnabled(false);
        dataLocacaoJFormattedTextField.setFocusable(false);
        dataLocacaoJFormattedTextField.addActionListener(this::dataLocacaoJFormattedTextFieldActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(buscaClienteJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(buscaClienteJButton)
                .addGap(43, 43, 43)
                .addComponent(tituloJLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(buscaFilmeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(buscaFilmeJButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(adicionarJButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dataLocacaoJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))
                            .addComponent(dataLocacaoJLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dataDevolucaoJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(diasJLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(diasJSpinner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(valorTotalJLabel)
                        .addGap(18, 18, 18)
                        .addComponent(valorTotalJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(finalizarJButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(carrinhoJLabel)
                        .addGap(101, 101, 101)
                        .addComponent(limparJButton))
                    .addComponent(removerJButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tituloJLabel)
                            .addComponent(buscaClienteJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscaClienteJButton))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buscaFilmeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscaFilmeJButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(limparJButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(carrinhoJLabel))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removerJButton)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(dataLocacaoJLabel)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(dataLocacaoJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(diasJLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(diasJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dataDevolucaoJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adicionarJButton)
                    .addComponent(finalizarJButton)
                    .addComponent(valorTotalJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorTotalJLabel))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buscaClienteJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaClienteJButtonActionPerformed
        if (buscaClienteJTextField.getText().isBlank()){
            JOptionPane.showMessageDialog(buscaClienteJTextField, "Digite um nome!");
        }else{
            resultaBuscaNome(buscaClienteJTextField.getText());
        }
    }//GEN-LAST:event_buscaClienteJButtonActionPerformed

    private void buscaFilmeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaFilmeJButtonActionPerformed
        if (buscaFilmeJTextField.getText().isBlank()){
            JOptionPane.showMessageDialog(buscaFilmeJTextField, "Digite um título!");
        }else{
            resultaBuscaFilmesNome(buscaFilmeJTextField.getText());
        }
    }//GEN-LAST:event_buscaFilmeJButtonActionPerformed

    private void adicionarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarJButtonActionPerformed
        int linhaSelecionada = filmesJTable.getSelectedRow();
        if(filmesJTable.getSelectedRowCount() != 1){
            JOptionPane.showMessageDialog(adicionarJButton, "Selecione 1 Filme");
        }else{
            boolean adicionado = false;
            Filme selecionado = new Filme();
            selecionado.setId((int) filmesJTable.getValueAt(linhaSelecionada, 0));
            selecionado.setTitulo((String) filmesJTable.getValueAt(linhaSelecionada, 1));
            selecionado.setValorLocacao((double) filmesJTable.getValueAt(linhaSelecionada, 3));
            for (Filme f : carrinho){
                adicionado = selecionado.getTitulo().equals(f.getTitulo());
                if (adicionado){
                    break;
                }
            }
            if (!adicionado){
                carrinho.add(selecionado);
                carregarCarrinho();
            } else{
                JOptionPane.showMessageDialog(adicionarJButton, "Filme já foi adicionado ao carrinho");
            }
        }
    }//GEN-LAST:event_adicionarJButtonActionPerformed

    private void removerJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerJButtonActionPerformed
        int[] selecionados = carrinhoJTable.getSelectedRows();
        if(selecionados.length == 1){
            textoExclJLabel.setText("Remover o filme: " + carrinho.get(selecionados[0]).getTitulo() + " do carrinho?");
            confirmaExclJDialog.setVisible(true);
        } else if (selecionados.length != 0){
            textoExclJLabel.setText("Remover " + selecionados.length + " filmes do carrinho?");
            confirmaExclJDialog.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(removerJButton, "Selecione pelo menos 1 filme!");
        }
        
    }//GEN-LAST:event_removerJButtonActionPerformed

    private void simExclJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simExclJButtonActionPerformed
        int[] selecionados = carrinhoJTable.getSelectedRows();
        if(selecionados.length == 1){
            carrinho.remove(selecionados[0]);
        } else if (selecionados.length != 0){
            for (int i = selecionados.length - 1; i > -1; i--){
                carrinho.remove(i);
            }
        }
        confirmaExclJDialog.setVisible(false);
        carregarCarrinho();
    }//GEN-LAST:event_simExclJButtonActionPerformed

    private void naoExclJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_naoExclJButtonActionPerformed
        confirmaExclJDialog.setVisible(false);
    }//GEN-LAST:event_naoExclJButtonActionPerformed

    private void limparJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparJButtonActionPerformed
        if (carrinho.isEmpty()){
            JOptionPane.showMessageDialog(limparJButton, "Carrinho está vazio!");
        }else{
            confirmaLimparJDialog.setVisible(true);
        }
    }//GEN-LAST:event_limparJButtonActionPerformed

    private void finalizarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarJButtonActionPerformed
        calculaDias(true);
        carregarCarrinho();
        if (clientesJTable.getSelectedRowCount() != 1){
            JOptionPane.showMessageDialog(finalizarJButton, "Selecione 1 cliente");
        } else if (carrinho.isEmpty()){
            JOptionPane.showMessageDialog(finalizarJButton, "Nenhum filme foi adicionado ao carrinho!");
        } else{
            JOptionPane.showMessageDialog(finalizarJButton, "Efetue o pagamento: " + valorTotalJTextField.getText());
            JOptionPane.showMessageDialog(finalizarJButton, "Locação realizada com sucesso!");

            Locacao locacao = new Locacao(dataDevolucao, valorTotal, (int) clientesJTable.getValueAt(clientesJTable.getSelectedRow(), 0), Sessao.idLogado);
            List<ItemLocacao> carrinhoLocacao = new ArrayList<>();
            for (Filme f : carrinho){
                carrinhoLocacao.add(f.toItem());
            }
            bancoLocacao.inserir(locacao, carrinhoLocacao);
            
            carrinho.removeAll(carrinho);
            diasJSpinner.setValue(1);
            calculaDias(false);
            carregarCarrinho();
            carregarTabelaClientes();
            carregarTabelaFilmes();
        }
    }//GEN-LAST:event_finalizarJButtonActionPerformed

    private void diasJSpinnerInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_diasJSpinnerInputMethodTextChanged
        
    }//GEN-LAST:event_diasJSpinnerInputMethodTextChanged

    private void diasJSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_diasJSpinnerStateChanged
        calculaDias(false);
        carregarCarrinho();
    }//GEN-LAST:event_diasJSpinnerStateChanged

    private void dataDevolucaoJFormattedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataDevolucaoJFormattedTextFieldActionPerformed
        calculaDias(true);
    }//GEN-LAST:event_dataDevolucaoJFormattedTextFieldActionPerformed

    private void dataLocacaoJFormattedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataLocacaoJFormattedTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataLocacaoJFormattedTextFieldActionPerformed

    private void simLimparJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simLimparJButtonActionPerformed
        carrinho.removeAll(carrinho);
        carregarCarrinho();
        confirmaLimparJDialog.setVisible(false);
    }//GEN-LAST:event_simLimparJButtonActionPerformed

    private void naoLimparJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_naoLimparJButtonActionPerformed
        confirmaLimparJDialog.setVisible(false);
    }//GEN-LAST:event_naoLimparJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarJButton;
    private javax.swing.JButton buscaClienteJButton;
    private javax.swing.JTextField buscaClienteJTextField;
    private javax.swing.JButton buscaFilmeJButton;
    private javax.swing.JTextField buscaFilmeJTextField;
    private javax.swing.JLabel carrinhoJLabel;
    private javax.swing.JTable carrinhoJTable;
    private javax.swing.JTable clientesJTable;
    private javax.swing.JDialog confirmaExclJDialog;
    private javax.swing.JDialog confirmaLimparJDialog;
    private javax.swing.JFormattedTextField dataDevolucaoJFormattedTextField;
    private javax.swing.JFormattedTextField dataLocacaoJFormattedTextField;
    private javax.swing.JLabel dataLocacaoJLabel;
    private javax.swing.JLabel diasJLabel;
    private javax.swing.JSpinner diasJSpinner;
    private javax.swing.JTable filmesJTable;
    private javax.swing.JButton finalizarJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton limparJButton;
    private javax.swing.JButton naoExclJButton;
    private javax.swing.JButton naoLimparJButton;
    private javax.swing.JButton removerJButton;
    private javax.swing.JButton simExclJButton;
    private javax.swing.JButton simLimparJButton;
    private javax.swing.JLabel textoExclJLabel;
    private javax.swing.JLabel textoLimparJLabel;
    private javax.swing.JLabel tituloJLabel;
    private javax.swing.JLabel valorTotalJLabel;
    private javax.swing.JTextField valorTotalJTextField;
    // End of variables declaration//GEN-END:variables
}
