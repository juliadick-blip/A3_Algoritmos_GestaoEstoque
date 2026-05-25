
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Classe principal do sistema de controle de estoque da empresa XYZ Comércio de
 * Produtos LTDA.
 *
 * <p>
 * Esta classe é responsável pelo gerenciamento das funcionalidades de cadastro
 * de produtos, movimentação de estoque, reajuste de preços e emissão de
 * relatórios.</p>
 *
 * @author Julia Dick, Eduardo Gonçalves, Melissa Monteiro e João Trilha
 * @version 1.0
 */
public class Principal {

    /**
     * Construtor privado da classe Principal.
     *
     * <p>
     * Impede a instanciação da classe, pois todos os métodos são estáticos.</p>
     */
    private Principal() {
    }

    /**
     * Vetores responsáveis por armazenar os dados dos produtos cadastrados.
     */
    static String[] nomes = new String[100];
    static double[] precos = new double[100];
    static String[] unidades = new String[100];
    static int[] quantidades = new int[100];
    static int indice = -1;

    static int totalProdutos = 0;

    /**
     * TELA 1.0 Método principal responsável por iniciar o sistema.
     *
     * <p>
     * Exibe o menu principal e direciona o usuário para as funcionalidades
     * disponíveis do sistema, incluindo:</p>
     *
     * <ul>
     * <li>Cadastro de produtos</li>
     * <li>Movimentação de estoque</li>
     * <li>Reajuste de preços</li>
     * <li>Emissão de relatórios</li>
     * <li>Encerramento do sistema</li>
     * </ul>
     *
     * @param args argumentos recebidos pela linha de comando
     */
    public static void main(String[] args) {

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

    /**
     * TELA 1.1 Exibe o menu de cadastro de produtos.
     *
     * <p>
     * Permite realizar operações de inclusão, alteração, consulta e exclusão de
     * produtos cadastrados.
     * </p>
     */
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

    /**
     * TELA 1.1.1 Realiza o cadastro de novos produtos no sistema.
     *
     * <p>
     * Solicita ao usuário as informações do produto, valida os dados informados
     * e armazena os valores nos vetores correspondentes. Também há a validação
     * se o produto já está cadastrado evitando duplicações.
     *
     * <p>
     * Os dados armazenados incluem:</p>
     * <ul>
     * <li>Nome</li>
     * <li>Preço</li>
     * <li>Unidade</li>
     * <li>Quantidade em estoque</li>
     * </ul>
     */
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
                                           INCLUSÃO DE PRODUTO
                                           
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
            confirma = Biblioteca.confirmar("""
                                            
                    DADOS DO PRODUTO
                                            
                    Nome: %s
                    Preço: %.2f
                    Unidade: %s
                    Quantidade: %d
                                                              
                    CONFIRMA INCLUSÃO (S/N)?""".formatted(nome, preco, unidade, quantidade)
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

    /**
     * TELA 1.1.2 Permite alterar os dados de um produto já cadastrado.
     *
     * <p>
     * O usuário informa o produto desejado e pode modificar preço, unidade e
     * quantidade em estoque.</p>
     *
     * <ul>
     * <li>Preço</li>
     * <li>Unidade de medida</li>
     * <li>Quantidade em estoque</li>
     * </ul>
     */
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
            confirma = Biblioteca.confirmar("""
                    DADOS A SEREM ALTERADOS
                                                          
                    Nome: %s
                    Preço: %.2f
                    Unidade: %s
                    Quantidade: %d
                                                              
                    CONFIRMA ALTERAÇÃO (S/N)?""".formatted(itemSolicitado, novoPreco, novaUnidade, novaQuantidade)
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

    /**
     * TELA 1.1.3 Realiza a consulta de um produto cadastrado.
     *
     * <p>
     * Exibe as informações detalhadas do produto selecionado pelo usuário.
     * </p>
     */
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
                                                     
                                                     %s
                                                     
                                                     Digite '0' para retornar.
                                                     """.formatted(Biblioteca.mostrarDados(indice, indice, indice, indice)
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

    /**
     * TELA 1.1.4 Remove um produto do sistema.
     *
     * <p>
     * Após confirmação do usuário, os dados do produto são excluídos e os
     * vetores são reorganizados para evitar espaços vazios.
     * </p>
     */
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
                                                                            
                                                                           %s
                                                                            
                                                                            CONFIRMA EXCLUSÃO (S/N)?
                                                                            """.formatted(Biblioteca.mostrarDados(indice, indice, indice, indice)));
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

    /**
     * Tela 1.2 Exibe o menu de movimentação de estoque.
     *
     * <p>
     * Permite registrar entradas e saídas de produtos no estoque.
     * </p>
     */
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

    /**
     * Tela 1.2.1 Realiza a entrada de produtos no estoque.
     *
     * <p>
     * Atualiza a quantidade disponível do produto selecionado após confirmação
     * do usuário.
     * </p>
     */
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

    /**
     * Tela 1.2.2 Realiza a saída de produtos do estoque.
     *
     * <p>
     * Valida a disponibilidade em estoque antes de efetuar a baixa da
     * quantidade informada.
     * </p>
     */
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

    /**
     * TELA 1.3 Exibe o menu de reajuste de preços.
     *
     * <p>
     * Permite aplicar reajustes gerais ou reajustes específicos para um
     * produto.</p>
     */
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

    /**
     * Realiza o reajuste de preço de um único produto.
     *
     * <p>
     * O usuário informa o percentual de reajuste que será aplicado ao produto
     * selecionado.
     * </p>
     */
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

    /**
     * Aplica reajuste de preços em todos os produtos cadastrados.
     *
     * <p>
     * O percentual informado pelo usuário será aplicado sobre todos os produtos
     * do estoque.
     * </p>
     */
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

    /**
     * TELA 1.4 Exibe o menu de relatórios do sistema.
     *
     * * <p>
     * Permite gerar relatórios de:</p>
     * <ul>
     * <li>Lista de preços</li>
     * <li>Balanço físico-financeiro</li>
     * </ul>
     */
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
                    fisicoFinanceiro();

                case 0:
                    JOptionPane.showMessageDialog(null, "Retornando ao menu principal...");
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }

        }
    }

    /**
     * Gera o relatório de lista de preços dos produtos.
     *
     * <p>
     * Exibe todos os produtos cadastrados contendo:</p>
     * <ul>
     * <li>Nome do produto</li>
     * <li>Unidade de medida</li>
     * <li>Preço unitário</li>
     * </ul>
     */
    public static void listaPrecos() {

        String relatorio = "";

        relatorio += """
        XYZ COMERCIO DE PRODUTOS LTDA.
        SISTEMA DE CONTROLE DE ESTOQUE

        LISTA DE PREÇOS

        %-20s %-10s %-10s
        -------------------------------------------
        """.formatted("PRODUTO", "UNIDADE", "PREÇO");

        // PRODUTOS
        for (int i = 0; i < totalProdutos; i++) {

            relatorio += String.format(
                    "%-20s %-10s R$ %6.2f\n",
                    nomes[i],
                    unidades[i],
                    precos[i]
            );
        }

        // ÁREA DE TEXTO
        JTextArea areaTexto = new JTextArea(relatorio);

        // FONTE MONOESPAÇADA
        areaTexto.setFont(
                new Font("Monospaced", Font.PLAIN, 14)
        );

        //PARA QUEM ESTÁ LENDO, NÃO CONSEGUIR EDITAR
        areaTexto.setEditable(false);

        // SCROLL
        JScrollPane scroll = new JScrollPane(areaTexto);

        //DIMENSÃO DA JANELA
        scroll.setPreferredSize(new Dimension(500, 300));

        // EXIBE
        JOptionPane.showMessageDialog(
                null,
                scroll,
                "RELATÓRIO",
                JOptionPane.PLAIN_MESSAGE
        );
    }

    /**
     * Gera o relatório de balanço físico-financeiro.
     *
     * <p>
     * O relatório apresenta: </p>
     * <ul>
     * <li>Produtos cadastrados</li>
     * <li>Quantidades em estoque</li>
     * <li>Valores unitários</li>
     * <li>Valor total por produto</li>
     * <li>Total geral do estoque</li>
     * </ul>
     */
    public static void fisicoFinanceiro() {

        String relatorio = "";
        LocalDate data = LocalDate.now();
        String dataFormatada = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        double valorTotal = 0;
        int itensTotais = 0;

        relatorio += """
                 XYZ COMERCIO DE PRODUTOS LTDA.
                 SISTEMA DE CONTROLE DE ESTOQUE

                 DATA: %-15s          %-35s %-8s

                 --------------------------------------------------------------------------------
                 %-25s %-8s %-15s %-8s %-15s
                 --------------------------------------------------------------------------------
                 """.formatted(dataFormatada, "BALANÇO FÍSICO-FINANCEIRO", "PG 001", "PRODUTO", "UND", "PREÇO UNITÁRIO", "QTDE", "PREÇO TOTAL");

        // PRODUTOS
        for (int i = 0; i < totalProdutos; i++) {

            valorTotal += (precos[i] * quantidades[i]);
            itensTotais += quantidades[i];

            relatorio += String.format(
                    "%-25s %-8s R$ %-12.2f %-8d %-12.2f\n",
                    nomes[i],
                    unidades[i],
                    precos[i],
                    quantidades[i],
                    (precos[i] * quantidades[i])
            );
        }

        relatorio += """
                     
                     TOTAL DE ITENS NO ESTOQUE : %d
                     VALOR TOTAL DO ESTOQUE    : %.2f
                     """.formatted(itensTotais, valorTotal);

        // ÁREA DE TEXTO
        JTextArea areaTexto = new JTextArea(relatorio);

        // FONTE MONOESPAÇADA
        areaTexto.setFont(
                new Font("Monospaced", Font.PLAIN, 14)
        );

        //PARA QUEM ESTÁ LENDO, NÃO CONSEGUIR EDITAR
        areaTexto.setEditable(false);

        // SCROLL
        JScrollPane scroll = new JScrollPane(areaTexto);

        //DIMENSÃO DA JANELA
        scroll.setPreferredSize(new Dimension(850, 400));

        JOptionPane.showMessageDialog(null, scroll, "RELATORIO", JOptionPane.PLAIN_MESSAGE);
    }
}
