
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Principal {

    static String[] nomes = new String[100];
    static double[] precos = new double[100];
    static String[] unidades = new String[100];
    static int[] quantidades = new int[100];
    static int indice = -1;

    static int totalProdutos = 0;

    public static void main(String[] args) {

        // TELA 1.0
        int opcao;

        do {

            opcao = Biblioteca.lerInteiro("""
                                          XYZ COMERCIO DE PRODUTOS LTDA.
                                          SISTEMA DE CONTROLE DE ESTOQUE
                                          
                                          MENU PRINCIPAL
                                          
                                          1 - CADASTRO DE PRODUTOS
                                          2 - MOVIMENTAÇÃO
                                          3 - REAJUSTE DE PREÇOS
                                          4 - RELATÓRIOS
                                          0 - FINALIZAR
                                          
                                          OPÇÃO: """);

            switch (opcao) {

                case 1:
                    menuCadastro();
                    break;
                case 2:
                    movimentacaoProduto();
                    break;
                case 3:
                    reajustePrecos();
                    break;
                case 4:
                    relatorios();
                    break;
                case 0:

                    JOptionPane.showMessageDialog(
                            null,
                            "Sistema finalizado."
                    );

                    System.exit(0);
                    break;

                default:

                    JOptionPane.showMessageDialog(
                            null,
                            "Opção inválida."
                    );
            }

        } while (true);
    }

    // TELA 1.1
    public static void menuCadastro() {

        int opcao;

        do {

            opcao = Biblioteca.lerInteiro("""
                                          XYZ COMERCIO DE PRODUTOS LTDA.
                                          SISTEMA DE CONTROLE DE ESTOQUE
                                          
                                          CADASTRO DE PRODUTOS
                                          
                                          1 - INCLUSÃO
                                          2 - ALTERAÇÃO
                                          3 - CONSULTA
                                          4 - EXCLUSÃO
                                          0 - RETORNAR
                                          
                                          OPÇÃO: """);

            switch (opcao) {

                case 1:
                    incluirProduto();
                    break;

                case 2:
                    alterarProduto();
                    break;

                case 3:
                    consultarProduto();
                    break;

                case 4:
                    excluirProduto();
                    break;

                case 0:

                    JOptionPane.showMessageDialog(
                            null,
                            "Retornando ao menu principal..."
                    );

                    return;

                default:

                    JOptionPane.showMessageDialog(
                            null,
                            "Opção inválida."
                    );
            }

        } while (true);
    }

    // TELA 1.1.1
    public static void incluirProduto() {

        char novaInclusao;

        do {

            String nome;
            double preco;
            String unidade;
            int quantidade;
            char confirma;

            // NOME
            while (true) {

                nome = Biblioteca.lerTexto("""
                                           INCLUSÇÃO DE PRODUTO
                                           
                                           NOME:""");

                if (Biblioteca.produtoExiste(nome)) {

                    JOptionPane.showMessageDialog(
                            null,
                            "ERRO: Produto já cadastrado."
                    );

                } else {
                    break;
                }
            }

            // PREÇO
            while (true) {

                preco = Biblioteca.lerDouble("PREÇO:");

                if (preco <= 0) {

                    JOptionPane.showMessageDialog(
                            null,
                            "ERRO: O preço deve ser maior que zero."
                    );

                } else {
                    break;
                }
            }

            // UNIDADE
            unidade = Biblioteca.lerTexto("UNIDADE:");

            // QUANTIDADE
            while (true) {
                quantidade = Biblioteca.lerInteiro("QUANTIDADE:");

                if (quantidade < 0) {

                    JOptionPane.showMessageDialog(
                            null,
                            "ERRO: Quantidade inválida."
                    );

                } else {
                    break;
                }
            }

            // CONFIRMAÇÃO
            confirma = Biblioteca.confirmar(
                    "CONFIRMA INCLUSÃO (S/N)?"
            );

            if (confirma == 'S') {

                nomes[totalProdutos] = nome;
                precos[totalProdutos] = preco;
                unidades[totalProdutos] = unidade;
                quantidades[totalProdutos] = quantidade;

                totalProdutos++;

                JOptionPane.showMessageDialog(
                        null,
                        "Produto cadastrado com sucesso!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "Cadastro cancelado."
                );
            }

            // NOVA INCLUSÃO
            novaInclusao = Biblioteca.confirmar(
                    "NOVA INCLUSÃO (S/N)?"
            );

        } while (novaInclusao == 'S');
    }

    //TELA 1.1.2
    public static void alterarProduto() {
        String itemSolicitado = null;
        double novoPreco;
        String novaUnidade;
        int novaQuantidade;
        char novaAlteracao;
        char confirma;

        do {
            //QUAL ITEM SERÁ ALTERADO
            if (Biblioteca.verificaSeEstaVazio(nomes)) {
                JOptionPane.showMessageDialog(null, "ERRO: Nenhum produto cadastrado.");
                break;
            }
            while (true) {
                itemSolicitado = Biblioteca.lerTexto("""
                                                     ALTERAÇÃO DE PRODUTO
                                                     NOME: """);

                if (Biblioteca.produtoExiste(itemSolicitado) != true) {
                    JOptionPane.showMessageDialog(null, "ERRO: Produto não encontrado.");
                } else {
                    break;
                }
            }

            //NOVO VALOR
            while (true) {
                try {
                    novoPreco = Biblioteca.lerDouble("NOVO PREÇO: ");

                    if (novoPreco <= 0) {
                        JOptionPane.showMessageDialog(null, "ERRO: O preço deve ser maior que zero.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "ERRO: Valor inválido");
                }
            }

            //NOVA UNIDADE
            novaUnidade = Biblioteca.lerTexto("NOVA UNIDADE: ");

            //NOVA QUANTIDADE
            while (true) {
                try {
                    novaQuantidade = Biblioteca.lerInteiro("NOVA QUANTIDADE: ");

                    if (novaQuantidade <= 0) {
                        JOptionPane.showMessageDialog(null, "ERRO: A quantidade deve ser maior que zero.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "ERRO: Valor inválido");
                }
            }

            // CONFIRMAÇÃO
            confirma = Biblioteca.confirmar(
                    "CONFIRMA ALTERAÇÃO (S/N)?"
            );

            if (confirma == 'S') {
                indice = Biblioteca.acharIndice(itemSolicitado);
                precos[indice] = novoPreco;
                unidades[indice] = novaUnidade;
                quantidades[indice] = novaQuantidade;

                JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");

            } else {

                JOptionPane.showMessageDialog(null, "Alteração cancelada.");
            }

            // SE SIM PARA NOVA ALTERACAO
            novaAlteracao = Biblioteca.confirmar(
                    "NOVA ALTERAÇÃO (S/N)?"
            );

        } while (novaAlteracao == 'S');

    }
    //TELA 1.1.3

    public static void consultarProduto() {
        int retornar;
        String itemSelecionado;
        char novaConsulta;

        do {
            if (Biblioteca.verificaSeEstaVazio(nomes)) {
                JOptionPane.showMessageDialog(null, "ERRO: Nenhum produto cadastrado.");
                break;
            }

            while (true) {
                itemSelecionado = Biblioteca.lerTexto("""
                                                      CONSULTA DE PRODUTO
                                                      
                                                      Produto que deseja consultar: """);

                if (Biblioteca.produtoExiste(itemSelecionado) != true) {
                    JOptionPane.showMessageDialog(null, "ERRO: Produto não encontrado.");
                } else {
                    indice = Biblioteca.acharIndice(itemSelecionado);
                    break;
                }

                if (itemSelecionado == null) {
                    break;
                }
            }
            retornar = 1;
            while (retornar != 0) {
                try {
                    retornar = Biblioteca.lerInteiro("""
                                                     CONSULTA DE DADOS
                                                     
                                                     NOME        : %s
                                                     PREÇO       : %.2f
                                                     UNIDADE     : %s
                                                     QUANTIDADE  : %d
                                                     
                                                     Digite '0' para retornar.
                                                     """.formatted(nomes[indice],
                            precos[indice],
                            unidades[indice],
                            quantidades[indice]
                    ));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Valor inválido.");
                }
            }
            // SE SIM PARA NOVA ALTERACAO
            novaConsulta = Biblioteca.confirmar(
                    "NOVA CONSULTA (S/N)?"
            );

        } while (novaConsulta == 'S');

    }

    //TELA 1.1.4
    public static void excluirProduto() {
        char retornar;
        String itemSelecionado;
        char novaExclusao;

        do {
            if (Biblioteca.verificaSeEstaVazio(nomes)) {
                JOptionPane.showMessageDialog(null, "ERRO: Nenhum produto cadastrado.");
                break;
            }
            while (true) {
                itemSelecionado = Biblioteca.lerTexto("""
                                                      EXCLUSÃO DE PRODUTO
                                                      
                                                      Produto que deseja excluir: """);

                if (Biblioteca.produtoExiste(itemSelecionado) != true) {
                    JOptionPane.showMessageDialog(null, "ERRO: Produto não encontrado.");
                } else {
                    indice = Biblioteca.acharIndice(itemSelecionado);
                    break;

                }

            }
            retornar = 'N';
            while (retornar != 'S') {

                retornar = Biblioteca.confirmar("""
                                                                            DADOS DO PRODUTO
                                                                            
                                                                            NOME        : %s
                                                                            PREÇO       : %.2f
                                                                            UNIDADE     : %s
                                                                            QUANTIDADE  : %d
                                                                            
                                                                            CONFIRMA EXCLUSÃO (S/N)?
                                                                            """.formatted(nomes[indice],
                        precos[indice],
                        unidades[indice],
                        quantidades[indice]
                ));
            }

            //FAZ A EXCLUSÃO DOS DADOS
            Biblioteca.exclusaoDeDados(indice);

            //MOVE TODOS OS ÍNDICES UMA CASA PARA TRÁS
            for (int i = indice; i < Principal.totalProdutos - 1; i++) {

                Principal.nomes[i] = Principal.nomes[i + 1];
                Principal.precos[i] = Principal.precos[i + 1];
                Principal.unidades[i] = Principal.unidades[i + 1];
                Principal.quantidades[i] = Principal.quantidades[i + 1];
            }

            Principal.totalProdutos--;

            //FAZ A EXCLUSÃO DOS DADOS DO VALOR QUE FICA DUPLICADO APÓS JOGAR TODOS UMA CASA PARA TRÁS.
            Biblioteca.exclusaoDeDados(totalProdutos);

            // SE SIM PARA NOVA EXCLUSAO
            novaExclusao = Biblioteca.confirmar(
                    "NOVA EXCLUSÃO (S/N)?"
            );

        } while (novaExclusao == 'S');

    }

    // Tela 1.2
    public static void movimentacaoProduto() {

        if (Biblioteca.verificaSeEstaVazio(nomes)) {
            JOptionPane.showMessageDialog(null, "ERRO: Nenhum produto cadastrado.");
            return;
        }

        while (true) {

            String entrada = JOptionPane.showInputDialog("""
                                                         XYZ COMERCIO DE PRODUTOS LTDA.
                                                         SISTEMA DE CONTROLE DE ESTOQUE
                                                         
                                                         MOVIMENTAÇÃO
                                                         
                                                         1 - ENTRADA
                                                         2 - SAÍDA
                                                         0 - RETORNAR
                                                         
                                                         OPÇÃO:""");

            if (entrada == null) {
                JOptionPane.showMessageDialog(null, "Retornando ao menu principal...");
                return;
            }

            int opcao;

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ERRO: Digite apenas números.");
                continue;
            }

            switch (opcao) {

                case 1:
                    entradaDeProduto();
                    break;

                case 2:
                    saidaDeProduto();
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Retornando ao menu principal...");
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }

    // Tela 1.2.1
    public static void entradaDeProduto() {

        String nomeProduto;
        int indice;
        int qtdEntrada;
        int qtdFinal;
        String confirmacao;
        char novaInclusao;

        do {

            while (true) {

                nomeProduto = JOptionPane.showInputDialog(
                        null,
                        "Qual o nome do Produto?"
                );

                if (nomeProduto == null) {
                    return;
                }

                indice = Biblioteca.acharIndice(nomeProduto.trim());

                if (indice == -1) {

                    JOptionPane.showMessageDialog(
                            null,
                            "ERRO: Produto não cadastrado."
                    );

                    continue;
                }

                break;
            }

            while (true) {

                String entrada = JOptionPane.showInputDialog(
                        null,
                        "PRODUTO: " + nomes[indice]
                        + "\nQTDE ATUAL: " + quantidades[indice]
                        + "\n\nDigite a quantidade de entrada:"
                );

                if (entrada == null) {
                    return;
                }

                try {

                    qtdEntrada = Integer.parseInt(entrada);

                    if (qtdEntrada <= 0) {

                        JOptionPane.showMessageDialog(
                                null,
                                "ERRO: Quantidade inválida."
                        );

                        continue;
                    }

                    break;

                } catch (NumberFormatException e) {

                    JOptionPane.showMessageDialog(
                            null,
                            "ERRO: Digite apenas números."
                    );
                }
            }

            qtdFinal = quantidades[indice] + qtdEntrada;

            while (true) {

                confirmacao = JOptionPane.showInputDialog(
                        null,
                        "PRODUTO: " + nomes[indice]
                        + "\nQTDE ATUAL: " + quantidades[indice]
                        + "\nQTDE ENTRADA: " + qtdEntrada
                        + "\nQTDE FINAL: " + qtdFinal
                        + "\n\nCONFIRMA ENTRADA (S/N)?"
                );

                if (confirmacao == null) {
                    return;
                }

                if (confirmacao.equalsIgnoreCase("S")) {
                    quantidades[indice] = qtdFinal;
                    break;
                }

                if (confirmacao.equalsIgnoreCase("N")) {
                    break;
                }

                JOptionPane.showMessageDialog(
                        null,
                        "ERRO: Digite apenas S ou N."
                );
            }

            novaInclusao = Biblioteca.confirmar(
                    "NOVA INCLUSÃO (S/N)?"
            );

        } while (novaInclusao == 'S');
    }

    // Tela 1.2.2
    public static void saidaDeProduto() {

        String nomeProduto;
        int indice;
        int qtdSaida;
        int qtdFinal;
        String confirmacao;
        char novaSaida;

        do {

            while (true) {

                nomeProduto = JOptionPane.showInputDialog(
                        null,
                        "Qual o nome do Produto?"
                );

                if (nomeProduto == null) {
                    return;
                }

                indice = Biblioteca.acharIndice(nomeProduto.trim());

                if (indice == -1) {

                    JOptionPane.showMessageDialog(
                            null,
                            "ERRO: Produto não cadastrado."
                    );

                    continue;
                }

                break;
            }

            while (true) {

                String saida = JOptionPane.showInputDialog(
                        null,
                        "PRODUTO: " + nomes[indice]
                        + "\nQTDE ATUAL: " + quantidades[indice]
                        + "\n\nDigite a quantidade de saída:"
                );

                if (saida == null) {
                    return;
                }

                try {

                    qtdSaida = Integer.parseInt(saida);

                    if (qtdSaida <= 0) {

                        JOptionPane.showMessageDialog(
                                null,
                                "ERRO: Quantidade inválida."
                        );

                        continue;
                    }

                    if (qtdSaida > quantidades[indice]) {

                        JOptionPane.showMessageDialog(
                                null,
                                "ERRO: Estoque insuficiente."
                        );

                        continue;
                    }

                    break;

                } catch (NumberFormatException e) {

                    JOptionPane.showMessageDialog(
                            null,
                            "ERRO: Digite apenas números."
                    );
                }
            }

            qtdFinal = quantidades[indice] - qtdSaida;

            while (true) {

                confirmacao = JOptionPane.showInputDialog(
                        null,
                        "PRODUTO: " + nomes[indice]
                        + "\nQTDE ATUAL: " + quantidades[indice]
                        + "\nQTDE SAÍDA: " + qtdSaida
                        + "\nQTDE FINAL: " + qtdFinal
                        + "\n\nCONFIRMA SAÍDA (S/N)?"
                );

                if (confirmacao == null) {
                    return;
                }

                if (confirmacao.equalsIgnoreCase("S")) {
                    quantidades[indice] = qtdFinal;
                    break;
                }

                if (confirmacao.equalsIgnoreCase("N")) {
                    break;
                }

                JOptionPane.showMessageDialog(
                        null,
                        "ERRO: Digite apenas S ou N."
                );
            }

            novaSaida = Biblioteca.confirmar(
                    "NOVA SAÍDA (S/N)?"
            );

        } while (novaSaida == 'S');
    }

    //TELA 1.3
    public static void reajustePrecos() {

        if (Biblioteca.verificaSeEstaVazio(nomes)) {
            JOptionPane.showMessageDialog(null, "ERRO: Nenhum produto cadastrado.");
            return;
        }

        while (true) {

            String entrada = JOptionPane.showInputDialog("""
                                                         XYZ COMERCIO DE PRODUTOS LTDA.
                                                         SISTEMA DE CONTROLE DE ESTOQUE
                                                         
                                                         REAJUSTE DE PREÇOS
                                                         
                                                         1- REAJUSTE GERAL
                                                         2- REAJUSTE DE UM PRODUTO
                                                         0- RETORNAR
                                                         
                                                         OPÇÃO:""");
            if (entrada == null) {
                JOptionPane.showMessageDialog(null, "Retornando ao menu principal...");
                return;
            }

            int opcao;

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ERRO: Digite apenas números.");
                continue;
            }

            switch (opcao) {
                case 1:
                    reajusteGeral();
                    break;

                case 2:
                    reajusteProduto();
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Retornando ao menu principal...");
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }

        }
    }

    public static void reajusteProduto() {
        String nomeProduto;
        int indice;
        char confirma;
        double percentual;
        char novaAlteracao;

        do {
            nomeProduto = JOptionPane.showInputDialog(
                    null,
                    "Qual o nome do Produto?"
            );

            if (nomeProduto == null) {
                return;
            }

            indice = Biblioteca.acharIndice(nomeProduto.trim());

            if (indice == -1) {

                JOptionPane.showMessageDialog(
                        null,
                        "ERRO: Produto não cadastrado."
                );

                continue;
            }

            break;

        } while (true);

        do {

            percentual = Biblioteca.lerDouble(
                    "PRODUTO: " + nomes[indice]
                    + "\nUNIDADE: " + unidades[indice]
                    + "\nPREÇO ATUAL: " + precos[indice]
                    + "\n\nPERCENTUAL DE REAJUSTE: "
            );

            // CONFIRMAÇÃO
            confirma = Biblioteca.confirmar(
                    "CONFIRMA ALTERAÇÃO (S/N)?"
            );

            if (confirma == 'S') {
                precos[indice] = (precos[indice] * (percentual / 100)) + precos[indice];

                JOptionPane.showMessageDialog(null, "Preço alterado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Alteração Cancelada.");
            }
            // SE SIM PARA NOVA ALTERACAO
            novaAlteracao = JOptionPane.showInputDialog(
                    "NOVO REAJUSTE (S/N)?"
            ).toUpperCase().charAt(0);

        } while (novaAlteracao == 'S');
    }

    public static void reajusteGeral() {
        char confirma;
        double percentual;
        char novaAlteracao;

        do {
            percentual = Biblioteca.lerDouble(
                    "PERCENTUAL DE REAJUSTE GERAL: "
            );

            // CONFIRMAÇÃO
            confirma = Biblioteca.confirmar(
                    "CONFIRMA ALTERAÇÃO (S/N)?"
            );

            if (confirma == 'S') {
                for (int i = 0; i < totalProdutos; i++) {
                    precos[i] = precos[i] + (precos[i] * (percentual / 100));
                }

                JOptionPane.showMessageDialog(null, "Preço alterado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Alteração Cancelada.");
            }
            // SE SIM PARA NOVA ALTERACAO
            novaAlteracao = JOptionPane.showInputDialog(
                    "NOVO REAJUSTE (S/N)?"
            ).toUpperCase().charAt(0);

        } while (novaAlteracao == 'S');
    }

    //TELA 1.4
    public static void relatorios() {

        if (Biblioteca.verificaSeEstaVazio(nomes)) {
            JOptionPane.showMessageDialog(null, "ERRO: Nenhum produto cadastrado.");
            return;
        }

        while (true) {
            String entrada = JOptionPane.showInputDialog("""
                                                         XYZ COMERCIO DE PRODUTOS LTDA.
                                                         SISTEMA DE CONTROLE DE ESTOQUE
                                                         
                                                         RELATÓRIOS
                                                         
                                                         1- LISTA DE PREÇOS: 
                                                         2- BALANÇO FÍSICO FINANCEIRO: 
                                                         0- RETORNAR
                                                         
                                                         OPÇÃO:""");
            if (entrada == null) {
                JOptionPane.showMessageDialog(null, "Retornando ao menu principal...");
                return;
            }

            int opcao;

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ERRO: Digite apenas números.");
                continue;
            }

            switch (opcao) {
                case 1:
                    listaPrecos();
                    break;

                case 2:
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Retornando ao menu principal...");
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }

        }
    }

    public static void listaPrecos() {

        String relatorio = "";

        relatorio += "XYZ COMERCIO DE PRODUTOS LTDA.\n";
        relatorio += "SISTEMA DE CONTROLE DE ESTOQUE\n\n";

        relatorio += "LISTA DE PREÇOS\n\n";

        relatorio += String.format(
                "%-20s %-10s %-10s\n",
                "PRODUTO",
                "UNIDADE",
                "PREÇO"
        );

        relatorio += "-------------------------------------------\n";

        // PRODUTOS
        for (int i = 0; i < totalProdutos; i++) {

            relatorio += String.format(
                    "%-20s %-10s R$ %7.2f\n",
                    nomes[i],
                    unidades[i],
                    precos[i]
            );
        }

        // =========================
        // ÁREA DE TEXTO
        // =========================
        JTextArea areaTexto = new JTextArea(relatorio);

        // FONTE MONOESPAÇADA
        areaTexto.setFont(
                new Font("Monospaced", Font.PLAIN, 14)
        );

        areaTexto.setEditable(false);

        // SCROLL
        JScrollPane scroll = new JScrollPane(areaTexto);

        scroll.setPreferredSize(new Dimension(500, 300));

        // EXIBE
        JOptionPane.showMessageDialog(
                null,
                scroll,
                "RELATÓRIO",
                JOptionPane.PLAIN_MESSAGE
        );
    }
}
