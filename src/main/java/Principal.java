
import javax.swing.JOptionPane;

public class Principal {

    static String[] nomes = new String[100];
    static double[] precos = new double[100];
    static String[] unidades = new String[100];
    static int[] quantidades = new int[100];

    static int totalProdutos = 0;

    public static void main(String[] args) {

        // TELA 1.0
        int opcao = 0;

        do {

            opcao = Biblioteca.lerInteiro(
                    "XYZ COMERCIO DE PRODUTOS LTDA.\n"
                    + "SISTEMA DE CONTROLE DE ESTOQUE\n"
                    + "\n"
                    + "MENU PRINCIPAL\n"
                    + "\n"
                    + "1 - CADASTRO DE PRODUTOS\n"
                    + "2 - MOVIMENTAÇÃO\n"
                    + "3 - REAJUSTE DE PREÇOS\n"
                    + "4 - RELATÓRIOS\n"
                    + "0 - FINALIZAR\n"
                    + "\n"
                    + "OPÇÃO: "
            );

            switch (opcao) {

                case 1:
                    menuCadastro();
                    break;
                case 2:
                    movimentacaoProduto();
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

            opcao = Biblioteca.lerInteiro(
                    "XYZ COMERCIO DE PRODUTOS LTDA.\n"
                    + "SISTEMA DE CONTROLE DE ESTOQUE\n"
                    + "\n"
                    + "CADASTRO DE PRODUTOS\n"
                    + "\n"
                    + "1 - INCLUSÃO\n"
                    + "2 - ALTERAÇÃO\n"
                    + "3 - CONSULTA\n"
                    + "4 - EXCLUSÃO\n"
                    + "0 - RETORNAR\n"
                    + "\n"
                    + "OPÇÃO: "
            );

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

                nome = Biblioteca.lerTexto(
                        "INCLUSÃO DE PRODUTO\n\n"
                        + "NOME:"
                );

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
        String itemSolicitado;
        double novoPreco;
        String novaUnidade;
        int novaQuantidade;
        char novaAlteracao;
        char confirma;

        do {
            //QUAL ITEM SERÁ ALTERADO
            while (true) {
                itemSolicitado = JOptionPane.showInputDialog("ALTERAÇÃO DE PRODUTO\n"
                        + "NOME: ");

                if (Biblioteca.produtoExiste(itemSolicitado) != true) {
                    JOptionPane.showMessageDialog(null, "ERRO: Produto não encontrado.");
                } else {
                    break;
                }
            }

            //NOVO VALOR
            while (true) {
                try {
                    novoPreco = Double.parseDouble(JOptionPane.showInputDialog("NOVO PREÇO: "));

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
            novaUnidade = JOptionPane.showInputDialog("NOVA UNIDADE: ");

            //NOVA QUANTIDADE
            while (true) {
                try {
                    novaQuantidade = Integer.parseInt(JOptionPane.showInputDialog("NOVA QUANTIDADE: "));

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
            confirma = JOptionPane.showInputDialog(
                    "CONFIRMA ALTERAÇÃO (S/N)?"
            ).toUpperCase().charAt(0);

            if (confirma == 'S') {

                precos[Biblioteca.acharIndice(itemSolicitado)] = novoPreco;
                unidades[Biblioteca.acharIndice(itemSolicitado)] = novaUnidade;
                quantidades[Biblioteca.acharIndice(itemSolicitado)] = novaQuantidade;

                JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");

            } else {

                JOptionPane.showMessageDialog(null, "Alteração cancelada.");
            }

            // SE SIM PARA NOVA ALTERACAO
            novaAlteracao = JOptionPane.showInputDialog(
                    "NOVA ALTERAÇÃO (S/N)?"
            ).toUpperCase().charAt(0);

        } while (novaAlteracao == 'S');

    }
    //TELA 1.1.3

    public static void consultarProduto() {
        int retornar;
        String itemSelecionado;
        char novaConsulta;
        int indice = -1;

        do {
            while (true) {
                itemSelecionado = JOptionPane.showInputDialog("CONSULTA DE PRODUTO\n"
                        + "\n"
                        + "Produto que deseja consultar: ");

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
                    retornar = Integer.parseInt(JOptionPane.showInputDialog("""
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
                    )));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Valor inválido.");
                }
            }
            // SE SIM PARA NOVA ALTERACAO
            novaConsulta = JOptionPane.showInputDialog(
                    "NOVA CONSULTA (S/N)?"
            ).toUpperCase().charAt(0);

        } while (novaConsulta == 'S');

    }

    //TELA 1.1.4
    public static void excluirProduto() {
        char retornar;
        String itemSelecionado;
        char novaExclusao;
        int indice = -1;

        do {
            if (Biblioteca.verificaSeEstaVazio(nomes) == false) {
                JOptionPane.showMessageDialog(null, "ERRO: Nenhum produto cadastrado.");
                break;
            }
            while (true) {
                itemSelecionado = JOptionPane.showInputDialog("EXCLUSÃO DE PRODUTO\n"
                        + "\n"
                        + "Produto que deseja excluir: ");

                if (Biblioteca.produtoExiste(itemSelecionado) != true) {
                    JOptionPane.showMessageDialog(null, "ERRO: Produto não encontrado.");
                } else {
                    indice = Biblioteca.acharIndice(itemSelecionado);
                    break;

                }

            }
            retornar = 'N';
            while (retornar != 'S') {

                retornar = JOptionPane.showInputDialog("""
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
                )).toUpperCase().charAt(0);
            }

            //FAZ A EXCLUSÃO DOS DADOS
            nomes[indice] = null;
            precos[indice] = 0.0;
            unidades[indice] = null;
            quantidades[indice] = 0;

            //MOVE TODOS OS ÍNDICES UMA CASA PARA TRÁS
            for (int i = indice; i < Principal.totalProdutos - 1; i++) {

                Principal.nomes[i] = Principal.nomes[i + 1];
                Principal.precos[i] = Principal.precos[i + 1];
                Principal.unidades[i] = Principal.unidades[i + 1];
                Principal.quantidades[i] = Principal.quantidades[i + 1];
            }

            Principal.totalProdutos--;

            //FAZ A EXCLUSÃO DOS DADOS DO VALOR QUE FICA DUPLICADO APÓS JOGAR TODOS UMA CASA PARA TRÁS.
            nomes[totalProdutos] = null;
            precos[totalProdutos] = 0.0;
            unidades[totalProdutos] = null;
            quantidades[totalProdutos] = 0;

            // SE SIM PARA NOVA EXCLUSAO
            novaExclusao = JOptionPane.showInputDialog(
                    "NOVA EXCLUSÃO (S/N)?"
            ).toUpperCase().charAt(0);

        } while (novaExclusao == 'S');

    }

    // Tela 1.2
    public static void movimentacaoProduto() {

        if (!Biblioteca.verificaSeEstaVazio(nomes)) {
            JOptionPane.showMessageDialog(null, "ERRO: Nenhum produto cadastrado.");
            return;
        }

        while (true) {

            String entrada = JOptionPane.showInputDialog(
                    "XYZ COMERCIO DE PRODUTOS LTDA.\nSISTEMA DE CONTROLE DE ESTOQUE\n\nMOVIMENTAÇÃO\n\n"
                            + "1 - ENTRADA\n"
                            + "2 - SAÍDA\n"
                            + "0 - RETORNAR\n\n"
                            + "OPÇÃO:"
            );

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
}
