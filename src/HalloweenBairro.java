import java.util.Scanner;

/**
 * A classe {@code HalloweenBairro} contém o método principal para executar o programa.
 */
public class HalloweenBairro
{

    /**
     * O método principal que inicia o programa.
     * @param args Os argumentos de linha de comando (não utilizados neste programa).
     */
    public static void main(String[] args)
    {
        // Cria um Scanner para ler a entrada do usuário.
        Scanner scanner = new Scanner(System.in);

        // Loop para solicitar a entrada do usuário cinco vezes.
        for (int i = 0; i < 5; i++)
        {
            // Solicita ao usuário que digite a string da árvore binária.
            System.out.println("Digite a string da árvore binária:");
            String vizinhanca = scanner.nextLine();

            // Verifica se a entrada do usuário é válida usando o método 'Auxiliar.validarEntrada()'.
            if (Auxiliar.validarEntrada(vizinhanca)) 
            {
                // Se a entrada for válida, cria uma nova instância de 'ArvoreTeste' 
                // com a string da árvore.
                ArvoreTeste arvore = new ArvoreTeste(vizinhanca);
                
                // Calcula a soma dos doces coletados na árvore.
                int soma = arvore.somarFolhas();
                
                // Calcula o número mínimo de ruas percorridas para obter todos os doces.
                int ruas = arvore.calcularRuas(); System.out.println("A soma dos doces coletados é: " + soma);

                // Imprime a soma dos doces e o número mínimo de ruas percorridas.
                System.out.println("O número mínimo de ruas percorridas para obter todos os doces é: " + ruas);
            }

            else
            {   // Se a entrada do usuário for inválida, exibe uma mensagem de erro.
                System.out.println("Formato da String invalida. Verifique e tente novamente");
            }
        }

        // Fecha o Scanner após o término do loop.
        scanner.close();
    }
}

// Primeiro: 0 13
// Segundo: 10 10
// Terceiro: 15 6
// Quarto: 25 40
// Quinto: 34 35