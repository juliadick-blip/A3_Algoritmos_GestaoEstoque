

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
            if (Principal.nomes[i].equals(itemSelecionado)) {
                index = i;
                break;
            }
        }
        return index;
    }
    
    public static boolean verificaSeEstaVazio(String []nomes){
        boolean achou = true;
            for(int i = 0; i < (Principal.totalProdutos + 1); i++){
                if(nomes[i] != null){
                    break;
                }else{
                    achou = false;
                }
            }
            return achou;
            
    }
 
}
