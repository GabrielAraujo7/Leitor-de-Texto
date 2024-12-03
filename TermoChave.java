import java.io.BufferedWriter;
import java.io.IOException;

public class TermoChave {
    private String palavra;
    private ListaDuplamenteEncadeada linhasTexto;
    
    public TermoChave(String palavra) {
        this.palavra = palavra;
        this.linhasTexto = new ListaDuplamenteEncadeada();
    }
    public String getPalavra() {
        return palavra;
    }

    public void addLinha(int linha) {
        boolean linhaJaFoiInserida = this.linhasTexto.contem(linha);
        if(!linhaJaFoiInserida){
            this.linhasTexto.insereFinal(linha);
        }
    }

    public void getlinhasTexto(BufferedWriter writer) throws IOException {
        linhasTexto.imprime(writer);
    }

}
