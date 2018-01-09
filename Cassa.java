
package cassa;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author Alex
 */
public class Cassa {

    /*TODO                   02/01/2018
    *Aggiungere al prodotto un boolean scontoApplicato altrimenti è possibili applicare lo sconto più volte     !Fixato 03/01
        modificare di conseguennza case 4 e 5 del secondo switch 257 e 278 (Bookmark segnati).              
    *Modificare l'estetica sia del codie che dell'output. FAI PENA                                              #Ancora da fixare, troppo noioso.
                             03/01/2018
    *Potrebbe non essere più utile lo scontoApplicato, sia del prodotto che di questa classe. Da vedere a cervello fresco   !Fixato 09/01 (Avevi ragione)
                             09/01/2018
    *Rimosso scontoApplicato e ultimo test(non è vero). Avevo dimenticato un controllo(81-87), aggiunto. 
    *Pubblicazione su GitHub.
    
     */
    public static Date insData() {
        int aa, mm, gg;
        Date d;
        do {
            do {
                aa = Input.reqInt("Inserisci l'anno: ");
                if (aa >= java.time.Year.now().getValue()) {
                    break;
                } else {
                    System.err.println("Anno non valido!");
                }
            } while (true);
            boolean bisestile = java.time.Year.isLeap((long) aa);
            do {
                mm = Input.reqInt("Inserisci il mese: ");
            } while (mm < 0 || mm > 12);
            do {
                gg = Input.reqInt("Inserisci il giorno: ");
                if (bisestile && mm == 2) {
                    if (gg > 0 && gg <= 29) {
                        break;
                    } else {
                        System.err.println("Deve essere compreso tra 1 e 29!\n");
                    }
                }
                if ((!bisestile && mm == 2)) {
                    if (gg > 0 && gg <= 28) {
                        break;
                    } else {
                        System.err.println("Deve essere compreso tra 1 e 29!\n");
                    }
                } else if (mm != 2 && gg >= 1 && gg <= 31) {
                    break;
                }
            } while (true);
            d = new Date(aa, mm, gg);
            if (d.before(new Date())) {
                System.err.println("Data già passata!");
            } else {
                break;
            }
        } while (true);
        return d;
    }

    public static ProdAli insProdAli(Vector v) {
        String barcode;
        while (true) {
            barcode = Input.reqString("Inserisci il barcode del prodotto: ");
            if (Prodotto.contains(v, barcode)) {
                System.out.println("Barcode già esistente!");
            } else {
                break;
            }
        }
        String nome;
        while (true) {
            nome = Input.reqString("Inserisci il nome del prodotto: ");
            if (Prodotto.contains(v, nome)) {
                System.out.println("Nome già esistente!");
            } else {
                break;
            }
        }

        BigDecimal prezzoU = BigDecimal.valueOf(Input.reqDouble("Inserisci il prezzo unitario del prodotto: "));
        System.out.println("----Inserimento data di scadenza----");
        Date d = Cassa.insData();
        return new ProdAli(barcode, nome, prezzoU, d);
    }

    public static ProdNonAli insProdNonAli(Vector v) {
        boolean esiste = true;
        String barcode, nome;
        do {
            barcode = Input.reqString("Inserisci il barcode del prodotto: ");
            esiste = Prodotto.contains(v, barcode);
            if (esiste) {
                System.out.println("Barcode già esistente!");
            }
        } while (esiste);
        esiste = false;
        do {
            nome = Input.reqString("Inserisci il nome del prodotto: ");
            esiste = Prodotto.contains(v, nome);
            if (esiste) {
                System.out.println("Nome già esistente!");
            }
        } while (esiste);
        BigDecimal prezzoU = BigDecimal.valueOf(Input.reqDouble("Inserisci il prezzo unitario del prodotto: "));
        String materiale = Input.reqString("Inserisci il materiale del prodotto: ");
        return new ProdNonAli(barcode, nome, prezzoU, materiale);
    }

    public static Object selezioneProdotto(Vector v, String comparatorString) {  //Fatto in questo modo per ritornare l'elemento e non un boolean
        for (int i = 0; i < v.size(); i++) {
            if (v.elementAt(i) instanceof ProdAli) {
                ProdAli elementAt = (ProdAli) v.elementAt(i);
                if (elementAt.equals(comparatorString)) {
                    return v.elementAt(i);
                }
            } else if (v.elementAt(i) instanceof ProdNonAli) {
                ProdNonAli elementAt = (ProdNonAli) v.elementAt(i);
                if (elementAt.equals(comparatorString)) {
                    return v.elementAt(i);
                }
            }

        }
        return null;
    }

    public static void main(String[] args) {
        Vector prodotti = new Vector(1, 2);
        Vector carrello = new Vector(3, 3);     //Struttura: 
        //Prodotto:ProdAli/ProdNonAli, 
        //qta del prodotto precedente:long,
        //prezzo totale di quel prodotto:BigDecimal,
        BigDecimal prezzoFinale = new BigDecimal(0d);
        boolean scontiApplicati = false;                            //Potrebbe non essere più utile
        int scelta;
        System.out.println("\t--Inserimento database--\n");
        do {
            do {
                scelta = Input.reqInt("Inserisci:\n"
                        + "1 per aggiungere un prodotto alimentare al database\n"
                        + "2 per aggiungere un prodotto non alimentare al database\n"
                        + "3 per un riepilogo dei prodotti inseriti\n"
                        + "4 per uscire dalla creazione del database\n\t\t\t");
            } while (!(scelta > 0 && scelta <= 4));
            switch (scelta) {
                case 1:
                    prodotti.add(Cassa.insProdAli(prodotti));
                    break;
                case 2:
                    prodotti.add(Cassa.insProdNonAli(prodotti));
                    break;
                case 3: {
                    Iterator it = prodotti.iterator();
                    while (it.hasNext()) {
                        System.out.println(it.next());
                        System.out.println("---");
                    }
                    break;
                }
                default:
                    break;
            }
        } while (scelta != 4);
        System.out.println("\t--Inizializzazione carrello--\n");
        do {
            do {
                scelta = Input.reqInt("Inserisci:\n"
                        + "1 per aggiungere un prodotto al carrello\n"
                        + "2 per rimuovere un prodotto dal carrello\n"
                        + "3 per un riepilogo dei prodotti sul carrello\n"
                        + "4 per applicare tutti gli sconti possibili\n"
                        + "5 per uscire e vedere lo scontrino\n\t\t\t");
            } while (!(scelta > 0 || scelta <= 5));
            switch (scelta) {
                case 1: {                                                       //aggiungere elemento al carrello
                    if (prodotti.isEmpty()) {
                        System.err.println("Apparentemente non hai inserito nessun prodotto. Termine del programma...");
                        System.exit(-1);
                    }
                    Object o = Cassa.selezioneProdotto(prodotti, Input.reqString("Inserisci il barcode o nome del prodotto: "));
                    long qta;
                    if (o == null) {
                        System.out.println("Prodotto non presente nel database");
                        break;
                    }
                    do {
                        qta = (long) Input.reqInt("Inserisci quanti ne vuoi acquistare: ");
                    } while (qta <= 0);
                    BigDecimal prezzoTotProdotto = new BigDecimal(0);
                    if (o instanceof ProdAli) {
                        ProdAli temp = (ProdAli) o;
                        carrello.add(temp);
                        carrello.add(qta);
                        prezzoTotProdotto.add(temp.getPrezzoU().multiply(BigDecimal.valueOf(qta))); //Praticamente moltiplica prezzoU*qta e lo aggiunge al prezzoTotaleProdotto

                    } else if (o instanceof ProdNonAli) {
                        ProdNonAli temp = (ProdNonAli) o;
                        carrello.add(temp);
                        carrello.add(qta);
                        prezzoTotProdotto.add(temp.getPrezzoU().multiply(BigDecimal.valueOf(qta))); //Praticamente moltiplica prezzoU*qta e lo aggiunge al prezzoTotaleProdotto
                    }
                    break;
                }
                case 2: {                                                       //Rimuovere dal carrello
                    if (carrello.isEmpty()) {
                        System.out.println("Carrello già vuoto!\n");
                        break;
                    }
                    Object o = Cassa.selezioneProdotto(carrello, Input.reqString("Inserisci il barcode o nome del prodotto: "));
                    int ind;
                    if (o == null) {
                        System.out.println("Prodotto non presente nel carrello\n");
                        break;
                    }
                    if (o instanceof ProdAli) {
                        ind = carrello.indexOf((ProdAli) o);
                    } else {
                        ind = carrello.indexOf((ProdNonAli) o);
                    }

                    if (Input.reqBoolean("Vuoi rimuoverli tutti?")) {
                        carrello.removeElementAt(ind);          //Rimuovo sia l'oggetto
                        carrello.removeElementAt(ind + 1);      //che la sua relativa quantità
                        carrello.removeElementAt(ind + 2);      //che il suo relativo prezzo

                    } else {                                    //Se non vuoi rimuoverli tutti(ovvero modificare la qta)
                        carrello.removeElementAt(ind + 1);      //Rimuovo la quantità vecchia
                        long qta2;
                        do {
                            qta2 = (long) Input.reqInt("Inserisci la nuova quantità: ");   //Ne faccio inserire una nuova
                        } while (qta2 <= 0);
                        carrello.add(ind + 1, qta2);             // e la posiziono esattamente dov'era prima
                    }
                    break;
                }
                case 3: {                                                       //Riepilogo carrello
                    if (carrello.isEmpty()) {
                        System.out.println("Carrello vuoto!\n");
                        break;
                    }
                    for (int i = 0; i < carrello.size(); i += 3) {
                        System.out.print("Prodotto " + (i + 1) + "\n" + carrello.elementAt(i));
                        System.out.println("Quantità: " + carrello.elementAt(i + 1));
                    }
                    break;
                }
                case 4: {                                                       //Applicare tutti i possibili sconti
                    if (carrello.isEmpty()) {
                        System.out.println("Carrello vuoto!");
                        break;
                    }
                    for (int i = 0; i < carrello.size(); i += 3) {
                        int qta = (int) carrello.elementAt(i + 1);
                        BigDecimal prezzoProdotto = (BigDecimal) carrello.elementAt(i + 2);
                        if (carrello.elementAt(i) instanceof ProdAli) {
                            ProdAli temp = (ProdAli) carrello.elementAt(i);
                            prezzoProdotto = temp.applicaSconto(qta);
                        }
                        if (carrello.elementAt(i) instanceof ProdNonAli) {
                            ProdNonAli temp = (ProdNonAli) carrello.elementAt(i);
                            prezzoProdotto = temp.applicaSconto(qta);
                        }
                    }
                    scontiApplicati = true;
                    break;
                }
                case 5: {                                                       //Scontrino & Out
                    if (carrello.isEmpty()) {
                        System.out.println("Carrello vuoto!\n");
                        break;
                    }
                    for (int i = 2; i < carrello.size(); i += 3) {                //Scorro i vari prezzi totali dei prodotti(2-5-8-11...)
                        prezzoFinale.add((BigDecimal) carrello.elementAt(i));
                    }
                    for (int i = 0; i < carrello.size(); i += 3) {
                        int g = 0;
                        g++;            //Per un numero più user-friendly
                        System.out.print("Prodotto " + g + "\n" + carrello.elementAt(i));
                        System.out.println("Quantità: " + carrello.elementAt(i + 1));
                    }
                    System.out.println("Prezzo Finale: €" + prezzoFinale);
                    break;
                }
                case 6: {
                    break;
                }
                default:
                    scelta = 0;
                    break;
            }
        } while (scelta != 6);
        System.exit(0);
    }
}