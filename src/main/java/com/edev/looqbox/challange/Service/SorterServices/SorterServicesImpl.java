package com.edev.looqbox.challange.Service.SorterServices;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SorterServicesImpl implements SorterServices{

//    Ambos os métodos implementam o Bubble Sort, é um algoritimo de ordenação simples.
//    Quando o algorítimo esta ordenado de forma inversa ocorre o pior caso do Big-θ, pois o tempo tomado é maior
//    Levando a formula de O(n²), n = list.length, pois cada elemento deve ser comparado com o restante da lista para ser comparado
// Se a lista ja estiver ordenada, ocorre o melhor caso, entretanto, o algorítimo ainda necessitará percorrer toda a lista para
// Comparar todos os elementos
// O espaço se dar a por O(1), pois nãp há a necessidade de memória adicional

// O Sistema do BubbleSort foi escolhido por mim, pois pode não ser o mais eficiente, entretanto, é o mais coeso de se colocar em
// Uma aplicação que não faz a transferência de grandes números de dados como esta.

    @Override
    public List<String> sortbByName(List<String> results) {
        int n = results.size();
        for (int i = 0; i < n - 1; i++) { // Loop que percorre toda a lista
            for (int j = 0; j < n - i - 1; j++) { // Loop que compara pares adjacentes

                // Se o elemento atual for maior (alfabeticamente) que o próximo, trocamos eles
                if (results.get(j).compareToIgnoreCase(results.get(j + 1)) > 0) {
                    String temp = results.get(j);
                    results.set(j, results.get(j + 1));
                    results.set(j + 1, temp);
                }
            }
        }
        return results; // Retorna a lista ordenada
    }


    @Override
    public List<String> sortbByLength(List<String> results) {
        int n = results.size();
        for (int i = 0; i < n - 1; i++) { // Loop que percorre toda a lista
            for (int j = 0; j < n - i - 1; j++) { // Loop que compara pares adjacentes

                // Se o comprimento da String atual for maior que o próximo, eles são trocados
                if (results.get(j).length() > results.get(j + 1).length()) {
                    String temp = results.get(j);
                    results.set(j, results.get(j + 1));
                    results.set(j + 1, temp);
                }
            }
        }
        return results; // Retorna a lista ordenada por comprimento
    }

}
