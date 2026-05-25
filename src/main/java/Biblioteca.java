
import javax.swing.JOptionPane;

/**
 * Classe responsável por armazenar métodos auxiliares utilizados no sistema de
 * controle de estoque.
 *
 * <p>
 * Contém métodos de validação, leitura de dados, confirmação de operações e
 * manipulação de produtos.</p>
 *
 * @author Julia Dick, Eduardo Gonçalves, Melissa Monteiro e João Trilha
 * @version 1.0
 */
public class Biblioteca {

    /**
     * Construtor privado da classe Biblioteca.
     *
     * <p>
     * Impede a instanciação da classe, pois todos os métodos são estáticos.</p>
     */
    private Biblioteca() {
    }

    /**
     * Verifica se um produto já está cadastrado no sistema.
     *
     * <p>
     * Realiza a busca pelo nome do produto no vetor de produtos
     * cadastrados.</p>
     *
     * @param nome nome do produto a ser pesquisado
     * @return true caso o produto exista, false caso contrário
     */
    public static boolean produtoExiste(String nome) {

        /**
         * Percorre todos os produtos cadastrados no sistema.
         */
        for (int i = 0; i < Principal.totalProdutos; i++) {

            if (Principal.nomes[i].equalsIgnoreCase(nome)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Monta e retorna uma string contendo os dados de um produto específico.
     *
     * @param indiceNome índice do nome do produto
     * @param indicePreco índice do preço do produto
     * @param indiceUnidades índice da unidade do produto
     * @param indiceQuantidade índice da quantidade do produto
     * @return string formatada com os dados do produto
     */
    public static String mostrarDados(int indiceNome, int indicePreco, int indiceUnidades, int indiceQuantidade) {
        String produtoAExibir = null;
        return produtoAExibir = """
                                                     NOME        : %s
                                                     PREÇO       : %.2f
                                                     UNIDADE     : %s
                                                     QUANTIDADE  : %d""".formatted(Principal.nomes[indiceNome],
                Principal.precos[indicePreco],
                Principal.unidades[indiceUnidades],
                Principal.quantidades[indiceQuantidade]);
    }

    /**
     * Localiza o índice correspondente ao produto informado.
     *
     * @param itemSelecionado nome do produto pesquisado
     * @return índice do produto encontrado ou -1 caso não exista
     */
    public static int acharIndice(String itemSelecionado) {
        int index = -1;

        for (int i = 0; i < Principal.totalProdutos; i++) {
            if (Principal.nomes[i].equalsIgnoreCase(itemSelecionado)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Verifica se o vetor de produtos está vazio.
     *
     * @param nomes vetor de nomes dos produtos
     * @return true caso o vetor esteja vazio, false caso exista algum produto
     * cadastrado
     */
    public static boolean verificaSeEstaVazio(String[] nomes) {

        for (int i = 0; i < Principal.nomes.length; i++) {
            if (nomes[i] != null) {
                return false; // ENCONTROU PRODUTO
            }
        }
        return true; // NÃO ENCONTROU PRODUTO
    }

    /**
     * Remove os dados de um produto dos vetores do sistema.
     *
     * @param indice posição do produto que será removido
     */
    public static void exclusaoDeDados(int indice) {
        Principal.nomes[indice] = null;
        Principal.precos[indice] = 0.0;
        Principal.unidades[indice] = null;
        Principal.quantidades[indice] = 0;
    }

    /**
     * Realiza a leitura de valores textuais informados pelo usuário.
     *
     * <p>
     * O método valida campos vazios e trata o cancelamento da operação.</p>
     *
     * @param mensagem mensagem exibida ao usuário
     * @return texto informado pelo usuário
     */
    public static String lerTexto(String mensagem) {

        while (true) {

            String texto = JOptionPane.showInputDialog(mensagem);

            // CANCELAR
            if (texto == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "Operação cancelada."
                );

                System.exit(0);

                return "";
            }

            // CAMPO VAZIO
            if (texto.trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Digite um valor."
                );

                continue;
            }

            return texto;
        }
    }

    /**
     * Realiza a leitura de números inteiros.
     *
     * <p>
     * Valida entradas vazias e impede caracteres inválidos.</p>
     * 
     * @param mensagem mensagem exibida ao usuário
     * @return valor inteiro informado
     */
    public static int lerInteiro(String mensagem) {

        while (true) {

            String entrada = JOptionPane.showInputDialog(mensagem);

            // CANCELAR OU FECHAR
            if (entrada == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "Sistema finalizado."
                );

                System.exit(0);
            }

            // CAMPO VAZIO
            if (entrada.trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Digite uma opção."
                );

                continue;
            }

            try {

                return Integer.parseInt(entrada);

            } catch (NumberFormatException erro) {

                JOptionPane.showMessageDialog(
                        null,
                        "Digite apenas números inteiros."
                );
            }
        }
    }

    /**
     * Realiza a leitura de valores do tipo double.
     *
     * <p>
     * Valida se o valor digitado é numérico.</p>
     *
     * @param mensagem mensagem exibida ao usuário
     * @return valor decimal informado
     */
    public static double lerDouble(String mensagem) {

        while (true) {

            try {

                String entrada = lerTexto(mensagem);

                return Double.parseDouble(entrada);

            } catch (NumberFormatException erro) {

                JOptionPane.showMessageDialog(
                        null,
                        "Digite um valor numérico válido."
                );
            }
        }
    }

    /**
     * Solicita uma confirmação do usuário utilizando as opções S (Sim) ou N
     * (Não).
     *
     * @param mensagem mensagem exibida ao usuário
     * @return caractere correspondente à confirmação informada
     */
    public static char confirmar(String mensagem) {

        while (true) {

            String resposta = lerTexto(mensagem);

            char opcao = resposta.toUpperCase().charAt(0);

            if (opcao == 'S' || opcao == 'N') {

                return opcao;
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Digite apenas S ou N."
            );
        }
    }
}
