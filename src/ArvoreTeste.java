import java.util.Stack;

/**
 * A classe {@code ArvoreTeste} representa uma árvore binária de busca que armazena números inteiros.
 * Ela fornece métodos para construção da árvore, 
 * soma dos valores das folhas, cálculo do número mínimo de ruas
 * percorridas para obter todos os doces e impressão dos nós da árvore.
 */
public class ArvoreTeste 
{

    /**
     * Classe interna privada que representa um nó da árvore.
     */
    private static class No
    {
        private No esquerda;
        private Integer info;
        private No direita;


        /**
         * Construtor que inicializa um nó com uma informação, esquerda e direita nulos.
         * @param esq O nó esquerdo.
         * @param i A informação armazenada no nó.
         * @param dir O nó direito.
         */
        public No(No esq, Integer i, No dir) 
        {
            this.esquerda = esq;
            this.info = i;
            this.direita = dir;
        }


        
        /**
         * Construtor que inicializa um nó com uma informação e filhos nulos.
         * @param i A informação armazenada no nó.
         */
        public No(Integer i) 
        {
            this(null, i, null);
        }


        /**
         * Construtor que inicializa um nó com informação 0 e filhos nulos.
         */
        public No()
         {
            this(null, 0, null);
        }


        /**
         * Retorna o nó filho esquerdo deste nó.
         * @return O nó filho esquerdo deste nó.
         */
        @SuppressWarnings("unused")
        public No getEsquerda() {return esquerda;}

        /**
         * Define o nó filho esquerdo deste nó.
         * @param esquerda O nó filho esquerdo a ser definido para este nó.
         */
        @SuppressWarnings("unused")
        public void setEsquerda(No esquerda) {this.esquerda = esquerda;}

        /**
         * Retorna a informação armazenada neste nó.
         * @return A informação armazenada neste nó.
         */
        @SuppressWarnings("unused")
        public Integer getInfo() {return info;}

        /**
         * Define a informação a ser armazenada neste nó.
         * @param info A informação a ser armazenada neste nó.
         */
        @SuppressWarnings("unused")
        public void setInfo(Integer info) {this.info = info;}

        /**
         * Retorna o nó filho direito deste nó.
         * @return O nó filho direito deste nó.
         */
        @SuppressWarnings("unused")
        public No getDireita() {return direita;}

        /**
         * Define o nó filho direito deste nó.
         * @param direita O nó filho direito a ser definido para este nó.
         */
        @SuppressWarnings("unused")
        public void setDireita(No direita) {this.direita = direita;}
    
    } // Fim da classe no.

    private No raiz;


     /**
     * Método principal para testar a classe.
     *
     * @param args Os argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) 
    {
        // Cria uma instância da classe ArvoreTeste com uma representação em forma de string da árvore.
        
        ArvoreTeste arvore = new ArvoreTeste("((1 2) (((10 10) (3 4)) ((((1 1) 1) 1) 1)))");
        
        // Imprime os nós da árvore.
        arvore.imprimirNodos();
        
        // Calcula a soma dos valores das folhas da árvore.
        int soma = arvore.somarFolhas();
        
        // Calcula o número mínimo de ruas percorridas para obter todos os doces.
        int ruas = arvore.calcularRuas();
        
        // Calcula o número mínimo de ruas percorridas para obter todos os doces.
        System.out.println("O número mínimo de ruas percorridas para obter todos os doces: " + ruas);
        System.out.println("A quantidade de doces: " + soma);
    }

    /**
     * Construtor que cria a árvore a partir de uma representação em forma de string.
     * @param vizinhanca A representação em string da árvore.
     */
    public ArvoreTeste(String vizinhanca) 
    {
        // Constrói a árvore a partir da representação em forma de string.
        this.raiz = construirArvore(vizinhanca);
    }


    /**
     * Constrói a árvore a partir da representação em forma de string.
     *
     * @param vizinhanca A representação em string da árvore.
     * @return O nó raiz da árvore construída.
     */
    private No construirArvore(String vizinhanca) 
    {
        // Cria uma pilha para auxiliar na construção da árvore.
        Stack<No> pilha = new Stack<>();

        // Inicializa o nó atual como nulo.
        No atual = null;

        // Inicializa um 'StringBuilder' para construir os números encontrados na string.
        StringBuilder numero = new StringBuilder();


        // Verifica se o primeiro caractere da string é um dígito.
        if (Character.isDigit(vizinhanca.charAt(0))) 

        {   // Se for um dígito, cria e retorna um novo nó folha 
            // com o valor convertido para inteiro.
            return new No(Integer.parseInt(vizinhanca));
        }

        // Itera sobre todos os caracteres da string de 'vizinhança'.
        for (int i = 0; i < vizinhanca.length(); i++) 
        {
            char c = vizinhanca.charAt(i);
            
            // Verifica se o caractere atual é um dígito.
            if (Character.isDigit(c)) 
            {
                // Se for um dígito, adiciona ao número em construção.
                numero.append(c);

                // Verifica se o próximo caractere também é um dígito.
                if (i < vizinhanca.length() - 1 && Character.isDigit(vizinhanca.charAt(i + 1))) 
                {
                    // Se for, continua a construção do número.
                    continue;
                }

                // Cria um novo nó folha com o valor do número em construção 
                // convertido para inteiro.
                No folha = new No(Integer.parseInt(numero.toString()));
                
                // Adiciona o nó folha como filho esquerdo ou direito do nó atual.
                if (atual.esquerda == null) 
                {
                    atual.esquerda = folha;
                } 
                else 
                {
                    atual.direita = folha;
                }

                // Reinicia o 'StringBuilder' para o próximo número.
                numero = new StringBuilder();
            } 
            else if (c == '(') 
            {
                // Se o caractere atual for '(', empilha o nó atual.
                if (atual != null)
                 {
                    pilha.push(atual);
                }

                // Cria um novo nó atual.
                atual = new No();

            } 
            else if (c == ')') 
            {   
                // Se o caractere atual for ')', desempilha o nó pai 
                if (!pilha.isEmpty()) 
                {
                    No pai = pilha.pop();
                 
                    // Se não possui filho esquerdo, define o nó atual como seu filho esquerdo.
                    if (pai.esquerda == null) 
                    {
                        pai.esquerda = atual;
                    } 
                    else 
                    {   // Se já possui filho esquerdo, define o nó atual como seu filho direito.
                        pai.direita = atual;
                    }
                    // Define o nó pai como o nó atual para continuar a construção da árvore.
                    atual = pai;
                }
            }
        }
        // Retorna o nó raiz da árvore.
        return atual;
    }


     /**
     * Calcula a soma dos valores das folhas da árvore.
     *
     * @return A soma dos valores das folhas.
     */
    public int somarFolhas() {return somarFolhas(raiz);}


    /**
     * Método auxiliar para calcular a soma dos valores das folhas de um nó.
     *
     * @param no O nó a partir do qual a soma será calculada.
     * @return A soma dos valores das folhas do nó.
     */
    private int somarFolhas(No no) 
    {
        // Verifica se o nó atual é nulo (folha).
        if (no == null)
            return 0; // Retorna 0 se for nulo, pois não há nada para somar

        // Verifica se o nó atual não possui filhos (é uma folha).    
        if (no.esquerda == null && no.direita == null)
            return no.info; // Retorna o valor armazenado no nó se for uma folha.

        // Se o nó não for uma folha, recursivamente calcula a soma dos valores das folhas,    
        // por retornar a soma das folhas da subárvore esquerda e da subárvore direita.
        return somarFolhas(no.esquerda) + somarFolhas(no.direita);
    }


    /**
     * Retorna a quantidade de nós da árvore.
     *
     * @return A quantidade de nós da árvore.
     */
    public int getQuantos() {return getQuantos(this.raiz);}


    /**
     * Método auxiliar para contar a quantidade de nós em uma subárvore.
     *
     * @param raiz O nó raiz da subárvore.
     * @return A quantidade de nós na subárvore.
     */
    private int getQuantos(No raiz)
    {

        // Verifica se o nó raiz é nulo.
        if(raiz == null) return 0; // Se for nulo, a árvore está vazia, então retorna 0.

        // Retorna a quantidade de nós na subárvore esquerda + a quantidade de nós na subárvore direita,
        // além do próprio nó raiz, que contabiliza 1.
        return 1 + getQuantos(raiz.esquerda) + getQuantos(raiz.direita);
    }



    /**
     * Calcula o número mínimo de ruas percorridas para obter todos os doces.
     * @return O número mínimo de ruas percorridas.
     */
    public int calcularRuas()
    {
        // Verifica se a informação armazenada na raiz é diferente de zero.
        if(raiz.info != 0)
        {
            // Se for diferente de zero, não há necessidade de percorrer a árvore,
            // então retorna 0.
            return 0;
        }

        // Compara o número de nós na subárvore esquerda com o número de nós na subárvore direita.
        if(getQuantos(raiz.esquerda) > getQuantos(raiz.direita))

            // Se a subárvore esquerda tiver mais nós, calcula as ruas 
            // percorrendo a esquerda como menor e a direita como maior.
            return calcularRuas(raiz.esquerda, false) + calcularRuas(raiz.direita, true);

        else
            // Caso contrário, calcula as ruas percorrendo a esquerda como maior e a direita como menor.
            return calcularRuas(raiz.esquerda, true) + calcularRuas(raiz.direita, false);
    }


    /**
     * Método auxiliar para calcular o número mínimo de ruas percorridas a partir de um nó.
     *
     * @param no      O nó a partir do qual o cálculo será realizado.
     * @param isMenor Indica se o nó é menor que o nó pai.
     * @return O número mínimo de ruas percorridas a partir do nó.
     */
    private int calcularRuas(No no, boolean isMenor)
    {
        // Verifica se a informação armazenada no nó é diferente de zero.
        if(no.info != 0)
            // Se for diferente de zero, retorna 2 se for menor, senão retorna 1.
            if(isMenor) return 2;

            else return 1;


        // Verifica se o nó é menor que o nó pai.
        if(isMenor)
            // Se for menor, retorna 2 mais as ruas percorridas pela esquerda e pela direita, 
            //considerando ambos como menores.
            return 2 + calcularRuas(no.esquerda, true) + calcularRuas(no.direita, true);

        else
            // Se não for menor, compara o número de nós na subárvore esquerda 
            // com o número de nós na subárvore direita.
            if(getQuantos(no.esquerda) > getQuantos(no.direita))
                // Se a subárvore esquerda tiver mais nós, retorna 1 mais 
                // as ruas percorridas pela esquerda como maior e pela direita como menor
                return 1 + calcularRuas(no.esquerda, false) + calcularRuas(no.direita, true);

            else
                // Caso contrário, retorna 1 mais as ruas percorridas pela esquerda 
                // como menor e pela direita como maior.
                return 1 + calcularRuas(no.esquerda, true) + calcularRuas(no.direita, false);
    }


     /**
     * Imprime os nós da árvore.
     */
    public void imprimirNodos() {imprimirNodos(raiz, "raiz");}


    /**
     * Método auxiliar para imprimir os nós da árvore.
     *
     * @param atual O nó atual.
     * @param lado  A posição do nó em relação ao nó pai.
     */
    private void imprimirNodos(No atual, String lado) 
    {
        // Verifica se o nó atual não é nulo.
        if (atual != null) 
        {   
            // Imprime o nó atual, mostrando sua informação se for diferente de zero, 
            // senão mostra o lado.
            System.out.println("Nó: " + (atual.info == 0 ? lado : atual.info));
            
            // Imprime o nó esquerdo, mostrando sua informação se não for nulo, 
            // senão mostra "null".
            System.out.println("Nó Esquerdo: " + (atual.esquerda != null ? atual.esquerda.info : "null"));
            
            // Imprime o nó direito, mostrando sua informação se não for nulo, 
            // senão mostra "null".
            System.out.println("Nó Direito: " + (atual.direita != null ? atual.direita.info : "null"));
            
            System.out.println();

            // Chama recursivamente o método para imprimir os nós da subárvore esquerda, 
            // alterando o lado para "A esquerda de " + lado.
            imprimirNodos(atual.esquerda, "A esquerda de " + lado);

            // Chama recursivamente o método para imprimir os nós da subárvore direita, 
            // alterando o lado para "A direita de " + lado.
            imprimirNodos(atual.direita, "A direita de " + lado);
        }
    }

}
