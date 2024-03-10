/**
 * A classe {@code Auxiliar} fornece métodos utilitários para operações diversas.
 */
public class Auxiliar
{

    /**
     * Valida a entrada de uma representação de árvore binária.
     * @param vizinhanca A representação em string da árvore binária.
     * @return true se a entrada for válida, false caso contrário.
     */
    public static boolean validarEntrada(String vizinhanca)
    {
        // Inicializa um contador para controlar o número de parênteses.
        int contadorParenteses = 0;

        // Indica se foram encontrados números na entrada.
        boolean numerosEncontrados = false;

        // Itera sobre cada caractere na representação da árvore.
        for (char caractere : vizinhanca.toCharArray())
         {
            // Se o caractere for '(', incrementa o contador de parênteses.
            if (caractere == '(') 
            {
                contadorParenteses++;
            } 

            // Se o caractere for ')', decrementa o contador de parênteses.
            else if (caractere == ')')
            {
                contadorParenteses--;

                // Verifica se há mais parênteses de fechamento do que de abertura.
                if (contadorParenteses < 0) 
                {
                    return false; // Mais fechamentos do que aberturas.
                }
            } 

            // Se o caractere for um dígito, define a flag numerosEncontrados como verdadeira.
            else if (Character.isDigit(caractere)) 
            {
                numerosEncontrados = true;
            }
        }

        // Retorna true se não houver parênteses desbalanceados e pelo menos um número foi encontrado.
        return contadorParenteses == 0 && numerosEncontrados;
    }

}
