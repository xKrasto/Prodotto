
package cassa;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 *
 * @author Alex
 */
public class ProdNonAli extends Prodotto {

    private final String materiale;
    public static final String[] ListaRiciclabili = new String[]{"vetro", "carta", "plastica"};

    public ProdNonAli(String barcode, String nome, BigDecimal prezzoU, String materiale) {
        super(barcode, nome, prezzoU);
        this.materiale = materiale;
    }

    @Override
    public String toString() {
        return super.toString().concat("materiale: " + materiale + "\n");
    }

    @Override
    public BigDecimal applicaSconto(int qta) {
        if (Arrays.asList(ListaRiciclabili).contains(this.materiale.toLowerCase())) {
            return super.applicaSconto(qta, 10);
        } else {
            return prezzoU.multiply(BigDecimal.valueOf((long) qta)); //Ovvero sconto non applicato
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ProdNonAli || obj instanceof String)) {
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
        final ProdNonAli foo = (ProdNonAli) obj;
        if (foo.barcode.toLowerCase().equals(this.barcode.toLowerCase()) || foo.nome.toLowerCase().equals(this.nome.toLowerCase())) {
            return true;
        }
        return false;
    }

}
