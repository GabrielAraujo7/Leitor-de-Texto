import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String chaves = "C:\\Users\\Gabriel Araujo\\Downloads\\ehoindice\\txts\\chaves.txt";
        String texto = "C:\\Users\\Gabriel Araujo\\Downloads\\ehoindice\\txts\\texto.txt";
        String saida = "C:\\Users\\Gabriel Araujo\\Downloads\\ehoindice\\txts\\saida.txt";
        String[] keys = null;
        HashTable hash = new HashTable(26);

        try (BufferedReader br = new BufferedReader(new FileReader(chaves))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                keys = linha.split(" ");
            }
            for (int index = 0; index < keys.length; index++) {
                TermoChave palavra = new TermoChave(keys[index]);
                hash.insere(palavra);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        try (BufferedReader br = new BufferedReader(new FileReader(texto))) {
            int numeroLinha = 1;
            String frase;
            String[] lista = null;
            while ((frase = br.readLine()) != null) {
                lista = frase.split(" ");
                for(String word : lista) {
                    TermoChave wordInRemissiveIndex = hash.contem(word);
                    if(wordInRemissiveIndex != null) {
                        wordInRemissiveIndex.addLinha(numeroLinha);
                    }
                }
                numeroLinha++;
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        hash.imprime(saida);
    }
}
