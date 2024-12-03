import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HashTable {

    public ArvoreBinariaBusca vetor[];
    public int nElementos;

    public HashTable(int capacidade) {
        this.vetor = new ArvoreBinariaBusca[capacidade];
        for (int i = 0; i < vetor.length; i++) {
            this.vetor[i] = new ArvoreBinariaBusca();
        }
        this.nElementos = 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public void imprime(String saidaFile) {
        try {
            FileWriter fileWriter = new FileWriter(saidaFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < vetor.length; i++) {
                vetor[i].imprime(bufferedWriter);
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int funcaoHashDiv(String elemento) {
        char firstChar = elemento.toLowerCase().charAt(0);
        int index = firstChar - 'a';
        return index % this.vetor.length;
    }

    public void insere(TermoChave elemento) {
        int endereco = funcaoHashDiv(elemento.getPalavra());
        this.vetor[endereco].insere(elemento);
        this.nElementos++;
    }

    public boolean remove(TermoChave elemento) {
        int endereco = funcaoHashDiv(elemento.getPalavra());
        boolean removeu = this.vetor[endereco].remove(elemento);

        if(removeu) this.nElementos--;

        return removeu;
    }

    public TermoChave contem(String elemento) {
        int endereco = funcaoHashDiv(elemento);
        return this.vetor[endereco].busca(elemento);
    }
}