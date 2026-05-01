/* ListeChaineExpress.java - Création d'une liste chaînée rapide
 * Auteur :  Love-Mary Victor, Sami Lies Mouzai
 * Code permanent :  VICL12599701, MOUS27039501
 */
import java.util.function.UnaryOperator;

/**
 * Cette classe permet d'avoir les avantages des listes chainées avec des coups de
 * déplacement réduit.
 * @param <E> Le type des éléments de la liste.
 */
public class ListeChaineExpress< E > {
    public static final int CAPACITE_MINIMUM_PAR_CHAINON = 2;

    /**
     * Classe interne non statique pour représenter les chainons de la liste.
     */
    private class Chainon {
        public E [] elements = (E []) new Object[ capaciteMaxParChainon ];
        public int nbCaseUtilisees = 0;
        public Chainon precedant;
        public Chainon suivant;

        /**
         * Transforme un chainon en chaine de caractère qui sera placée dans le
         * {@code StringBuilder} en entrée.
         * @param sb le {@code StringBuilder} dans lequel le résultat est concaténé.
         */
        public void appendStringBuilder( StringBuilder sb ) {
            sb.append("[ nb=\"").append(nbCaseUtilisees).append("\" : ");
            sb.append( elements[ 0 ] );
            for( int i = 1; i < nbCaseUtilisees; ++ i ) {
                sb.append(", ").append(elements[i]);
            }
            sb.append( " ]" );
        }
    }

    private int capaciteMaxParChainon = 8;
    private int taille = 0;
    private Chainon debut = null;
    private Chainon fin = null;

    /**
     * Construit une liste vide avec une capacité maximum de 8 pour chaque chainon.
     */
    public ListeChaineExpress() {}

    /**
     * Construit une liste vide.
     * @param nbElementMaxParChainon Indique la capacité maximum de chaque chainon.
     * @throws IllegalArgumentException si la valeur est plus petite que la capacité minimum
     * accepté par la liste (2).
     */
    public ListeChaineExpress( int nbElementMaxParChainon ) {
        if( nbElementMaxParChainon < CAPACITE_MINIMUM_PAR_CHAINON ) {
            throw new IllegalArgumentException();
        }
        this.capaciteMaxParChainon = nbElementMaxParChainon;
    }

    /**
     * Insère l'élément à la position donnée.
     * Décale les éléments présents à partir de cet index (et les suivants)
     * d'une position vers la droite. Si un chaînon est plein lors de
     * l'insertion, un nouveau chaînon est créé pour recevoir l'élément qui
     * déborde.
     * @param index l'index où l'élément sera inséré.
     * @param element l'élément inséré.
     * @throws IndexOutOfBoundsException si l'index est plus petit que 0 ou plus grand que la
     * taille de la liste.
     * @throws NullPointerException si l'élément est {@code null}.
     */
    public void add( int index, E element ) {
        if(index < 0 || index > taille) {
            throw new IndexOutOfBoundsException();
        }
        if(element == null) {
            throw new NullPointerException();
        }

        if(debut == null) {
            debut = new Chainon();
            fin = debut;
        }

        Chainon chainonCourant = debut;
        int indexDansChainon = index;
        while(indexDansChainon > chainonCourant.nbCaseUtilisees) {
            indexDansChainon -= chainonCourant.nbCaseUtilisees;
            chainonCourant = chainonCourant.suivant;
        }

        if (indexDansChainon == chainonCourant.nbCaseUtilisees) {
            if (chainonCourant.nbCaseUtilisees < capaciteMaxParChainon) {
                chainonCourant.elements[ indexDansChainon ] = element;
                chainonCourant.nbCaseUtilisees++;
            } else {
                insererNouveauChainon(chainonCourant, element );
            }
        } else {
            if (chainonCourant.nbCaseUtilisees < capaciteMaxParChainon) {
                for (int i = chainonCourant.nbCaseUtilisees; i > indexDansChainon; i-- ) {
                    chainonCourant.elements[ i ] = chainonCourant.elements[ i - 1 ];
                }
                chainonCourant.elements[ indexDansChainon ] = element;
                chainonCourant.nbCaseUtilisees++;
            } else {
                E deborde = chainonCourant.elements[ capaciteMaxParChainon - 1 ];
                for ( int i = capaciteMaxParChainon - 1; i > indexDansChainon; i-- ) {
                    chainonCourant.elements[ i ] = chainonCourant.elements[ i - 1 ];
                }
                chainonCourant.elements[ indexDansChainon ] = element;
                insererNouveauChainon(chainonCourant, deborde );
            }
        }
        taille++;
    }


    private void insererNouveauChainon(Chainon precedent, E element) {
        Chainon nouveau = new Chainon();
        nouveau.elements[0] = element;
        nouveau.nbCaseUtilisees = 1;
        nouveau.precedant = precedent;
        nouveau.suivant = precedent.suivant;
        if (precedent.suivant != null) {
            precedent.suivant.precedant = nouveau;
        } else {
            fin = nouveau;
        }
        precedent.suivant = nouveau;
    }

    /**
     * Supprime l'élément situé à l'index indiqué.
     * Les éléments suivant cette position seront décalé de 1 vers la gauche.
     * (1 est soustrait à leurs positions).
     * L'élément supprimé est retourné par la méthode.
     * @param index l'index de l'élément qui sera supprimé.
     * @return l'élément supprimé.
     * @throws IndexOutOfBoundsException si l'index est plus petit que 0 ou plus grand que la
     * taille - 1 de la liste.
     */
    public E remove( int index ) {
        E elementRetire = null;

        if(index < 0 || index > taille) {
            throw new IndexOutOfBoundsException();
        }

        Chainon chainonCourant = debut;

        int chainonCible = index / capaciteMaxParChainon;
        int chainonIndex = index % capaciteMaxParChainon;

        for(int i = 0; i < chainonCible; i++){
            chainonCourant = chainonCourant.suivant;
        }

        E [] nouvelleListe = (E[]) new Object[chainonCourant.nbCaseUtilisees -1];


        for (int i = 0; i <= chainonCourant.nbCaseUtilisees - 1  ; i++){
            if(i == chainonIndex){
                elementRetire = chainonCourant.elements[i];
            } else {
                if(i > chainonIndex){
                    nouvelleListe[i - 1] = chainonCourant.elements[i];
                }else{
                    nouvelleListe[i] = chainonCourant.elements[i];
                }
            }
        }

        chainonCourant.elements = nouvelleListe;

        chainonCourant.nbCaseUtilisees--;

        taille--;

        if(taille == 0){
            debut = null;
            fin = null;
        } else if (chainonCourant.nbCaseUtilisees == 0){
            if(chainonCourant == fin) {
                fin = chainonCourant.precedant;
                fin.suivant = null;
            } else if(chainonCourant == debut) {
                debut = chainonCourant.suivant;
                debut.precedant = null;
            } else{
                chainonCourant.precedant.suivant = chainonCourant.suivant;
                chainonCourant.suivant.precedant = chainonCourant.precedant;
            }
        }
        return elementRetire;
    }

    /**
     * Retourne l'élément à la position indiquée.
     * @param index la position de l'élément à retourner.
     * @return l'élément à la position indiquée.
     * @throws IndexOutOfBoundsException si l'index est plus petit que 0 ou plus grand que la
     * taille - 1 de la liste.
     */
    public E get( int index ) {
        E elementRetire = null;

        if(index < 0 || index >= taille) {
            throw new IndexOutOfBoundsException();
        }

        Chainon chainonCourant = debut;

        int chainonCible = index / capaciteMaxParChainon;
        int chainonIndex = index % capaciteMaxParChainon;

        for(int i = 0; i < chainonCible; i++){
            chainonCourant = chainonCourant.suivant;
        }


        for (int i = 0; i <= chainonCourant.nbCaseUtilisees - 1  ; i++) {
            if (i == chainonIndex) {
                elementRetire = chainonCourant.elements[i];
            }
        }

        return elementRetire;


    }

    /**
     * Remplace l'élément à la position indiquée par un nouvel élément.
     * L'élément remplacé est retourné par la méthode.
     * @param index la position où le remplacement a lieu.
     * @param element le nouvel élément.
     * @return l'élément qui a été remplacé.
     * @throws IndexOutOfBoundsException si l'index est plus petit que 0 ou plus grand que la
     * taille - 1 de la liste.
     * @throws NullPointerException si l'élément est {@code null}.
     */
    public E set( int index, E element ) {
        E elementRetire = null;

        if(index < 0 || index >= taille ) {
            throw new IndexOutOfBoundsException();
        }

        if (element == null){
            throw new NullPointerException();
        }

        Chainon chainonCourant = debut;

        int chainonCible = index / capaciteMaxParChainon;
        int chainonIndex = index % capaciteMaxParChainon;

        for(int i = 0; i < chainonCible; i++){
            chainonCourant = chainonCourant.suivant;
        }


        for (int i = 0; i <= chainonCourant.nbCaseUtilisees - 1  ; i++) {
            if (i == chainonIndex) {
                elementRetire = chainonCourant.elements[i];
                chainonCourant.elements[i] = element;
            }
        }
        return elementRetire;
        }


    /**
     * Retourne l'index de la première occurrence dans la liste de l'élément indiqué.
     * Si l'élément n'est pas dans la liste, alors la méthode retourne -1.
     * @param target l'élément recherché.
     * @return l'index de la première occurrence de l'élément recherché ou -1 si l'élément
     * n'est pas présent.
     * @throws NullPointerException si l'élément cible est {@code null}.
     */
    public int indexOf( Object target ) {

        if(target == null){
            throw new NullPointerException();
        }

        int trouve = -1;
        int nombreChainon = 0;
        Chainon chainonCourant = debut;

        while (chainonCourant != null && trouve == -1) {
            for (int i = 0; i < chainonCourant.nbCaseUtilisees && trouve == -1; i++) {
                if (chainonCourant.elements[i].equals(target)) {
                    trouve = i;
                }
            }

            if (trouve == -1) {
                nombreChainon++;
                chainonCourant = chainonCourant.suivant;
            }
        }

        if (trouve == -1) {
            return -1;
        }

        trouve = (nombreChainon * capaciteMaxParChainon) + trouve;
        return trouve;
    }

    /**
     * Remplace chaque élément de la liste par le résultat de l'application
     * de l'opérateur à cet élément.
     * @param operator l'opérateur qui est appliqué à chaque élément.
     * @throws NullPointerException si l'opérateur est {@code null} ou si l'application de l'opérateur
     * donne {@code null}.
     */
    public void replaceAll( UnaryOperator< E > operator ) {

        if(operator == null){
            throw new NullPointerException();
        }

        Chainon chainonCourant = debut;

        while (chainonCourant != null) {
            for (int i = 0; i < chainonCourant.nbCaseUtilisees; i++) {
                chainonCourant.elements[i] = operator.apply(chainonCourant.elements[i]);

                if(chainonCourant.elements[i] == null){
                    throw new NullPointerException();
                }

            }

                chainonCourant = chainonCourant.suivant;

        }

    }


    // ***********************************
    // Code déjà écrit

    /**
     * Retourne le nombre d'éléments que contient la liste.
     * @return le nombre d'éléments que contient la liste.
     */
    public int size() {
        return taille;
    }

    /**
     * Indique si la liste est vide.
     * @return {@code true} si la liste est vide, {@code false} sinon.
     */
    public boolean isEmpty() {
        return 0 == taille;
    }

    /**
     * Construit une représention sous forme de chaine de caractères de la liste.
     * @return la chaine de caractère contenant la représentation de la liste.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("< taille=\"").append(taille).append("\" : ");
        Chainon courant = debut;
        if( null != courant ) {
            courant.appendStringBuilder( sb );
            courant = courant.suivant;
        }
        while( null != courant ) {
            sb.append( ", " );
            courant.appendStringBuilder( sb );
            courant = courant.suivant;
        }
        sb.append( " >" );

        return sb.toString();
    }
}
