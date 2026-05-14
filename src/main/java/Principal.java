
import javax.swing.JOptionPane;

public class Principal {

    static String[] nomes = new String[100];
    static double[] precos = new double[100];
    static String[] unidades = new String[100];
    static int[] quantidades = new int[100];

    static int totalProdutos = 0;

    public static void main(String[] args) {

        // TELA 1.0
        int opcao;

        do {

            opcao = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "XYZ COMERCIO DE PRODUTOS LTDA.\n"
                            + "SISTEMA DE CONTROLE DE ESTOQUE\n"
                            + "\n"
                            + "MENU PRINCIPAL\n"
                            + "\n"
                            + "1 - CADASTRO DE PRODUTOS\n"
                            + "2 - MOVIMENTAÇÃO\n"
                            + "3 - REAJUSTE DE PREÇOS\n"
                            + "4 - RELOTÓRIOS\n"
                            + "0 - FINALIZAR\n"
                            + "\n"
                            + "OPÇÃO: "
                    )
            );

            switch (opcao) {

                case 1:
                    menuCadastro();
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Sistema finalizado.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }

        } while (opcao != 0);

        String[][] tabela = new String[100][4];
        for (int i = 0; i < (totalProdutos + 1); i++) {
            tabela[i][0] = nomes[i];
            tabela[i][1] = Double.toString(precos[i]);
            tabela[i][2] = unidades[i];
            tabela[i][3] = Integer.toString(quantidades[i]);

            System.out.println(tabela[i][0]);
            System.out.println(tabela[i][1]);
            System.out.println(tabela[i][2]);
            System.out.println(tabela[i][3]);
        }

    }

    // TELA 1.1
    public static void menuCadastro() {

        int opcao;

        do {

            opcao = Integer.parseInt(
                    JOptionPane.showInputDialog("""
                                                XYZ COMERCIO DE PRODUTOS LTDA.
                                                SISTEMA DE CONTROLE DE ESTOQUE
                                                
                                                CADASTRO DE PRODUTOS
                                                
                                                1 - INCLUSÃO
                                                2 - ALTERAÃO
                                                3 - CONSULTA
                                                4 - EXCLUSÃO
                                                0 - RETORNAR
                                                
                                                OP\u00c7\u00c3O: """)
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
                    JOptionPane.showMessageDialog(null, "Retornando ao menu principal...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }

        } while (opcao != 0);
    }
    // TELA 1.1.1

    public static void incluirProduto() {

        char novaInclusao;
        String nome;
        double preco;
        String unidade;
        int quantidade;
        char confirma;

        do {

            // NOME
            while (true) {

                nome = JOptionPane.showInputDialog("""
                                                   INCLUSÃO DE PRODUTO
                                                   NOME: """);

                if (Biblioteca.produtoExiste(nome)) {

                    JOptionPane.showMessageDialog(null, "ERRO: Produto já cadastrado.");

                } else {
                    break;
                }
            }

            // PREÇO
            while (true) {

                preco = Double.parseDouble(
                        JOptionPane.showInputDialog("PREÇO: ")
                );

                if (preco <= 0) {

                    JOptionPane.showMessageDialog(null, "ERRO: O preço deve ser maior que zero.");

                } else {
                    break;
                }
            }

            // UNIDADE
            unidade = JOptionPane.showInputDialog("UNIDADE: ");

            // QUANTIDADE
            while (true) {

                quantidade = Integer.parseInt(
                        JOptionPane.showInputDialog("QUANTIDADE: ")
                );

                if (quantidade < 0) {

                    JOptionPane.showMessageDialog(null, "ERRO: Quantidade inválida.");

                } else {
                    break;
                }
            }

            // CONFIRMAÇÃO
            confirma = JOptionPane.showInputDialog(
                    "CONFIRMA INCLUSÃO (S/N)?"
            ).toUpperCase().charAt(0);

            if (confirma == 'S') {

                nomes[totalProdutos] = nome;
                precos[totalProdutos] = preco;
                unidades[totalProdutos] = unidade;
                quantidades[totalProdutos] = quantidade;

                totalProdutos++;

                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

            } else {

                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            }

            // SE SIM PARA NOVA INCLUSÃO
            novaInclusao = JOptionPane.showInputDialog(
                    "NOVA INCLUSÃO (S/N)?"
            ).toUpperCase().charAt(0);

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
                itemSolicitado = JOptionPane.showInputDialog("""
                                                             ALTERAÇÃO DE PRODUTO
                                                             
                                                             NOME:
                                                             """);

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
            if(Biblioteca.verificaSeEstaVazio(nomes) == false){
                break;
            }
        
            while (true) {
                itemSelecionado = JOptionPane.showInputDialog("""
                                                              CONSULTA DE PRODUTO
                                                              
                                                              Produto que deseja consultar: """);

                
                if (Biblioteca.produtoExiste(itemSelecionado) != true) {
                    JOptionPane.showMessageDialog(null, "ERRO: Produto não encontrado.");
                } else {
                    indice = Biblioteca.acharIndice(itemSelecionado);
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

}
