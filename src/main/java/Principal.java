
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

      
    }

    // TELA 1.1
    public static void menuCadastro() {

        int opcao;

        do {

            opcao = Integer.parseInt(
                    JOptionPane.showInputDialog("XYZ COMERCIO DE PRODUTOS LTDA.\n"
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
                    )
            );

            switch (opcao) {

                case 1:
                    incluirProduto();
                    break;

                case 2:
                    alterarProduto();
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

                nome = JOptionPane.showInputDialog(
                        "INCLUSÃO DE PRODUTO\n"
                        + "NOME: "
                );

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

                precos[acharIndice(itemSolicitado)] = novoPreco;
                unidades[acharIndice(itemSolicitado)] = novaUnidade;
                quantidades[acharIndice(itemSolicitado)] = novaQuantidade;

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

    //PROCURA O INDICE DO PRODUTO A SER ALTERADO
    static int acharIndice(String itemSelecionado) {
        int index = -1;

        for (int i = 0; i < totalProdutos; i++) {
            if (nomes[i].equals(itemSelecionado)) {
                index = i;
                break;
            }
        }
        return index;
    }

}
