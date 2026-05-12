
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
}
