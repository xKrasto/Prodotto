
package cassa;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Alex
 */
public class ProdAli extends Prodotto {

    private final Date scadenza;

    public ProdAli(String barcode, String nome, BigDecimal prezzoU, Date scadenza) {
        super(barcode, nome, prezzoU);
        this.scadenza = scadenza;
    }

    @Override
    public String toString() {
        return super.toString().concat("scadenza: " + scadenza.getDay() + "/" + scadenza.getMonth() + "/" + scadenza.getYear() + "\n");
    }

    @Override
    public BigDecimal applicaSconto(int qta) {
        if (this.scadenza.compareTo(new Date()) <= 10) {
            return super.applicaSconto(qta, 20);
        } else {
            return prezzoU.multiply(BigDecimal.valueOf((long) qta));     //Ovvero non eseguito
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ProdAli || obj instanceof String)) {
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
        final ProdAli foo = (ProdAli) obj;
        if (foo.barcode.toLowerCase().equals(this.barcode.toLowerCase()) || foo.nome.toLowerCase().equals(this.nome.toLowerCase())) {
            return true;
        }
        return false;
    }

}
