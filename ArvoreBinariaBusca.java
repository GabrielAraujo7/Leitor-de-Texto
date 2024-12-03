import java.io.BufferedWriter;
import java.io.IOException;

public class ArvoreBinariaBusca {

    class Nodo {

        public TermoChave elemento;
        public Nodo esquerdo;
        public Nodo direito;

        public Nodo(TermoChave elemento) {
            this.elemento = elemento;
            this.esquerdo = null;
            this.direito = null;
        }
    }

    public Nodo raiz;
    public int nElementos;

    public ArvoreBinariaBusca() {
        this.raiz = null;
        this.nElementos = 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public boolean estaVazia() {
        return this.raiz == null;
    }


    public void imprime(BufferedWriter writer) throws IOException {
        emOrdemToFile(this.raiz, writer);
    }

    public void emOrdemToFile(Nodo nodo, BufferedWriter writer) throws IOException {
        if (nodo == null)
            return;

        emOrdemToFile(nodo.esquerdo, writer);
        writer.write(nodo.elemento.getPalavra() + " ");
        nodo.elemento.getlinhasTexto(writer);
        writer.newLine();
        emOrdemToFile(nodo.direito, writer);
    }

    public void insere(TermoChave elemento) {
        this.insere(elemento, this.raiz);
    }

    public void insere(TermoChave elemento, Nodo nodo) {

        Nodo novo = new Nodo(elemento);

        if (nodo == null) {
            this.raiz = novo;
            this.nElementos++;
            return;
        }
        int comparisonResult = elemento.getPalavra().compareTo(nodo.elemento.getPalavra());

        if (comparisonResult < 0) {
            if (nodo.esquerdo == null) {
                nodo.esquerdo = novo;
                this.nElementos++;
                return;
            } else {
                this.insere(elemento, nodo.esquerdo);
            }
        }

        if (comparisonResult > 0) {
            if (nodo.direito == null) {
                nodo.direito = novo;
                this.nElementos++;
                return;
            } else {
                this.insere(elemento, nodo.direito);
            }
        }
    }

    private Nodo menorElemento(Nodo nodo) {
        while (nodo.esquerdo != null) {
            nodo = nodo.esquerdo;
        }
        return nodo;
    }

    public boolean remove(TermoChave elemento) {
        return this.remove(elemento, this.raiz) != null;
    }

    private Nodo remove(TermoChave elemento, Nodo nodo) {

        if (nodo == null) {
            System.out.println("Valor não encontrado");
            return null;
        }
        int comparisonResult = elemento.getPalavra().compareTo(nodo.elemento.getPalavra());

        if (comparisonResult < 0) {
            nodo.esquerdo = this.remove(elemento, nodo.esquerdo);
        } else if (comparisonResult > 0) {
            nodo.direito = this.remove(elemento, nodo.direito);
        } else {

            if (nodo.esquerdo == null) {
                this.nElementos--;
                return nodo.direito;
            } else if (nodo.direito == null) {
                this.nElementos--;
                return nodo.esquerdo;
            } else {
                Nodo substituto = this.menorElemento(nodo.direito);
                nodo.elemento = substituto.elemento;
                this.remove(substituto.elemento, nodo.direito);
            }
        }

        return nodo;
    }

    public TermoChave busca(String elemento) {
        return this.busca(elemento, this.raiz);

    }

    public TermoChave busca(String elemento, Nodo nodo) {

        if (nodo == null) {
            return null;
        }

        int comparisonResult = elemento.compareTo(nodo.elemento.getPalavra());

        if (comparisonResult < 0) {
            return this.busca(elemento, nodo.esquerdo);
        } else if (comparisonResult > 0) {
            return this.busca(elemento, nodo.direito);
        } else {
            return nodo.elemento;
        }
    }

    private int altura(Nodo nodo) {

        if (nodo == null) {
            return -1;
        }

        int alturaEsquerda = this.altura(nodo.esquerdo) + 1;
        int alturaDireita = this.altura(nodo.direito) + 1;

        int altura = alturaEsquerda > alturaDireita ? alturaEsquerda : alturaDireita;

        return altura;

    }

    public int altura() {
        return this.altura(this.raiz);
    }
}