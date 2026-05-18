
import javax.swing.JOptionPane;

public class Biblioteca {

    // SUBROTINA DE VALIDAÇÃO SE O PRODUTO EXISTE
    public static boolean produtoExiste(String nome) {

        for (int i = 0; i < Principal.totalProdutos; i++) {

            if (Principal.nomes[i].equalsIgnoreCase(nome)) {
                return true;
            }
        }

        return false;
    }

    //PROCURA O INDICE DO PRODUTO A SER ALTERADO
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

    //VERIFICA SE O VETOR ESTÁ VAZIO
    public static boolean verificaSeEstaVazio(String[] nomes) {

        for (int i = 0; i < Principal.nomes.length; i++) {
            if (nomes[i] != null) {
                return false; // ENCONTROU PRODUTO
            }
        }
        return true; // NÃO ENCONTROU PRODUTO
    }

    //EXCLUI OS DADOS DOS PRODUTOS
    public static void exclusaoDeDados() {
        Principal.nomes[Principal.indice] = null;
        Principal.precos[Principal.indice] = 0.0;
        Principal.unidades[Principal.indice] = null;
        Principal.quantidades[Principal.indice] = 0;
    }

    // LER TEXTO
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

    // LER INTEIRO
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
// LER DOUBLE

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

// CONFIRMAÇÃO S/N
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
