package desafio;
import java.util.Stack;

public class Desafio {

    public int RetanguloMax(char[][] matriz) {
        if (matriz == null || matriz.length == 0 || matriz[0].length == 0) {
            return 0;
        }

        int maiorArea = 0; 
        int numColunas = matriz[0].length; 

        int[] alturas = new int[numColunas];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < numColunas; j++) {
                if (matriz[i][j] == '1') {
                    alturas[j]++; 
                } else {
                    alturas[j] = 0;
                }
            }
            maiorArea = Math.max(maiorArea, encontrarMaiorRetangulo(alturas));
        }

        return maiorArea; 
    }

    private int encontrarMaiorRetangulo(int[] alturas) {
        Stack<Integer> pilha = new Stack<>(); 
        int maiorAreaRet = 0; 
        int n = alturas.length; 

        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : alturas[i];

            while (!pilha.isEmpty() && alturas[pilha.peek()] >= h) {
                int alturaDaBarraRemovida = alturas[pilha.pop()];
                int largura = pilha.isEmpty() ? i : i - pilha.peek() - 1;
                
                maiorAreaRet = Math.max(maiorAreaRet, alturaDaBarraRemovida * largura);
            }
            pilha.push(i);
        }
        return maiorAreaRet;
    }

    public static void main(String[] args) {
        Desafio solucionador = new Desafio();

        char[][] matriz = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        System.out.println("A Área do retângulo maior é: " + solucionador.RetanguloMax(matriz));

    }
}