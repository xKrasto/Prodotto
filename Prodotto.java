
package cassa;

import java.math.BigDecimal;
import java.util.Vector;

/**
 *
 * @author Alex
 */
public class Prodotto {

    protected final String barcode;
    protected String nome;
    protected BigDecimal prezzoU;
    public Prodotto(String barcode, String nome, BigDecimal prezzoU) {
        this.barcode = barcode;
        this.nome = nome;
        this.prezzoU = prezzoU;
    }

    
    public BigDecimal applicaSconto(int qta) {
        BigDecimal prezzoTot, prezzoFinale, sconto;
        BigDecimal percSconto = BigDecimal.valueOf((double) 100 / 5);
        prezzoTot = BigDecimal.valueOf((double) qta).multiply(this.prezzoU);
        sconto = percSconto.multiply(prezzoTot);
        prezzoFinale = prezzoTot.subtract(sconto);
        return prezzoFinale;
        
    }

    protected BigDecimal applicaSconto(int qta, int scontoPercentuale) {
        BigDecimal prezzoTot, prezzoFinale, sconto;
        BigDecimal percSconto = BigDecimal.valueOf((double) 100 / scontoPercentuale);
        prezzoTot = BigDecimal.valueOf((double) qta).multiply(this.prezzoU);
        sconto = percSconto.multiply(prezzoTot);
        prezzoFinale = prezzoTot.subtract(sconto);
        return prezzoFinale;
        
    }

    @Override
    public String toString() {
        return "\nProdotto: "
                + "\nbarcode: " + barcode
                + "\nnome: " + nome
                + "\nprezzo unitario: " + prezzoU + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Prodotto || obj instanceof String)) {
            return false;
        }
        if (obj instanceof String) {
            String objStringa = (String) obj;
            if (this.barcode.toLowerCase().equals(objStringa.toLowerCase()) || this.nome.toLowerCase().equals(objStringa.toLowerCase())) {
                return true;
            } else {
                return false;
            }

        }
        final Prodotto foo = (Prodotto) obj;
        if (foo.barcode.toLowerCase().equals(this.barcode.toLowerCase()) || foo.nome.toLowerCase().equals(this.nome.toLowerCase())) {
            return true;
        }
        return false;
    }

    public static boolean contains(Vector v, String comparatorString) {    // NON FUNGE UNFORTUNATELY
        for (int i = 0; i < v.size(); i++) {
            Prodotto elementAt = (Prodotto) v.elementAt(i);
            if (elementAt.equals(comparatorString)) {
                return true;
            }
        }
        return false;
    }

    public BigDecimal getPrezzoU() {
        return prezzoU;
    }

    
}
