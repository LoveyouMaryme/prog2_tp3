
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ListeChaineExpressTest {
    // insérer dans une liste vide.
    @Test
    void add1() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 4 );
        a.add( 0, 5 );
        assertEquals( "< taille=\"1\" : [ nb=\"1\" : 5 ] >", a.toString() );
    }

    // insérer un élément à la dernière case libre d'un tableau non plein.
    @Test
    void add2() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 4 );
        a.add( 0, 5 );
        a.add( 1, -8 );
        assertEquals( "< taille=\"2\" : [ nb=\"2\" : 5, -8 ] >", a.toString() );
    }

    // insérer un élément dans une case non libre d'un tableau non plein.
    @Test
    void add3() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 4 );
        a.add( 0, 5 );
        a.add( 1, -8 );
        a.add( 1, 2 );
        assertEquals( "< taille=\"3\" : [ nb=\"3\" : 5, 2, -8 ] >", a.toString() );
    }

    // insérer le dernier élément d'un tableau.
    @Test
    void add4() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 4 );
        a.add( 0, 5 );
        a.add( 1, -8 );
        a.add( 1, 2 );
        a.add( 3, 7 );
        assertEquals( "< taille=\"4\" : [ nb=\"4\" : 5, 2, -8, 7 ] >", a.toString() );
    }

    // insérer un élément à l'intérieur d'un tableau plein.
    @Test
    void add5() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 4 );
        a.add( 0, 5 );
        a.add( 1, -8 );
        a.add( 1, 2 );
        a.add( 3, 7 );
        a.add( 2, 10 );
        assertEquals( "< taille=\"5\" : [ nb=\"4\" : 5, 2, 10, -8 ], [ nb=\"1\" : 7 ] >", a.toString() );
    }

    // insérer un élément à la suite d'un tableau plein à la fin de la liste.
    @Test
    void add6() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 4 );
        a.add( 0, 5 );
        a.add( 1, -8 );
        a.add( 1, 2 );
        a.add( 3, 7 );
        a.add( 4, 14 );
        assertEquals( "< taille=\"5\" : [ nb=\"4\" : 5, 2, -8, 7 ], [ nb=\"1\" : 14 ] >", a.toString() );
    }

    // insérer un élément à l'intérieur d'un tableau plein à l'intérieur de la liste.
    @Test
    void add7() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 4 );
        a.add( 0, 5 );
        a.add( 1, -8 );
        a.add( 1, 2 );
        a.add( 3, 7 );
        a.add( 4, 14 );
        a.add( 2, 6 );
        assertEquals( "< taille=\"6\" : [ nb=\"4\" : 5, 2, 6, -8 ], [ nb=\"1\" : 7 ], [ nb=\"1\" : 14 ] >", a.toString() );
    }

    // tester l'exception pour les index trop petit.
    @Test
    void addException1() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 9 );
        a.add( 0, 5 );
        a.add( 1, -8 );
        a.add( 2, 6 );

        assertEquals( "< taille=\"3\" : [ nb=\"3\" : 5, -8, 6 ] >", a.toString() );
        assertThrows( IndexOutOfBoundsException.class, () -> a.add( -1, 3 ) );
        assertThrows( IndexOutOfBoundsException.class, () -> a.add( -3, 3 ) );
    }

    // tester l'exception pour les index trop grand.
    @Test
    void addException2() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 9 );
        a.add( 0, 5 );
        a.add( 1, -8 );
        a.add( 2, 6 );

        assertEquals( "< taille=\"3\" : [ nb=\"3\" : 5, -8, 6 ] >", a.toString() );
        assertThrows( IndexOutOfBoundsException.class, () -> a.add( 4, 3 ) );
        assertThrows( IndexOutOfBoundsException.class, () -> a.add( 5, 3 ) );
    }

    // tester l'exception pour les éléments null.
    @Test
    void addException3() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 9 );
        a.add( 0, 5 );
        a.add( 1, -8 );
        a.add( 2, 6 );

        assertEquals( "< taille=\"3\" : [ nb=\"3\" : 5, -8, 6 ] >", a.toString() );
        assertThrows( NullPointerException.class, () -> a.add( 1, null ) );
    }

    // Enlever le dernier élément du tableau.
    @Test
    void remove1() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 6 );
        a.add( 0, 5 );
        a.add( 1, -8 );
        a.add( 2, 6 );
        a.add( 3, 7 );
        a.add( 4, 14 );
        assertEquals( "< taille=\"5\" : [ nb=\"5\" : 5, -8, 6, 7, 14 ] >", a.toString() );

        assertEquals( 14, a.remove( 4 ) );
        assertEquals( "< taille=\"4\" : [ nb=\"4\" : 5, -8, 6, 7 ] >", a.toString() );
    }

    // Enlever un élément à l'intérieur du tableau.
    @Test
    void remove2() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 6 );
        a.add( 0, 5 );
        a.add( 1, -8 );
        a.add( 2, 6 );
        a.add( 3, 7 );
        a.add( 4, 14 );
        assertEquals( "< taille=\"5\" : [ nb=\"5\" : 5, -8, 6, 7, 14 ] >", a.toString() );

        assertEquals( -8, a.remove( 1 ) );
        assertEquals( "< taille=\"4\" : [ nb=\"4\" : 5, 6, 7, 14 ] >", a.toString() );
    }

    // Enlever le premier élément du tableau.
    @Test
    void remove3() {
        ListeChaineExpress<Integer> a = new ListeChaineExpress(6);
        a.add(0, 5);
        a.add(1, -8);
        a.add(2, 6);
        a.add(3, 7);
        a.add(4, 14);
        assertEquals("< taille=\"5\" : [ nb=\"5\" : 5, -8, 6, 7, 14 ] >", a.toString());

        assertEquals(5, a.remove(0));
        assertEquals("< taille=\"4\" : [ nb=\"4\" : -8, 6, 7, 14 ] >", a.toString());
    }

    // Enlever le seul élément du seul chainon.
    @Test
    void remove4() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 3 );
        a.add( 0, 5 );
        assertEquals( "< taille=\"1\" : [ nb=\"1\" : 5 ] >", a.toString() );

        assertEquals( 5, a.remove( 0 ) );
        assertEquals( "< taille=\"0\" :  >", a.toString() );
    }

    // Enlever le seul élément d'un chainon a l'intérieur de la liste.
    @Test
    void remove5() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 3 );
        for( int i = 0; i < 8; ++ i ) {
            a.add(i, 2 * i);
        }

        assertEquals( "< taille=\"8\" : [ nb=\"3\" : 0, 2, 4 ], [ nb=\"3\" : 6, 8, 10 ], [ nb=\"2\" : 12, 14 ] >", a.toString() );

        assertEquals( 10, a.remove( 5 ) );
        assertEquals( 8, a.remove( 4 ) );
        assertEquals( 6, a.remove( 3 ) );
        assertEquals( "< taille=\"5\" : [ nb=\"3\" : 0, 2, 4 ], [ nb=\"2\" : 12, 14 ] >", a.toString() );
    }

    // Enlever le seul élément du dernier chainon.
    @Test
    void remove6() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 3 );
        for( int i = 0; i < 7; ++ i ) {
            a.add(i, 2 * i + 1);
        }

        assertEquals( "< taille=\"7\" : [ nb=\"3\" : 1, 3, 5 ], [ nb=\"3\" : 7, 9, 11 ], [ nb=\"1\" : 13 ] >", a.toString() );

        assertEquals( 13, a.remove( 6 ) );
        assertEquals( "< taille=\"6\" : [ nb=\"3\" : 1, 3, 5 ], [ nb=\"3\" : 7, 9, 11 ] >", a.toString() );
    }

    // Enlever le seul élément du premier chainon.
    @Test
    void remove7() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 3 );
        for( int i = 0; i < 4; ++ i ) {
            a.add(i, 3 * i);
        }

        assertEquals( "< taille=\"4\" : [ nb=\"3\" : 0, 3, 6 ], [ nb=\"1\" : 9 ] >", a.toString() );

        assertEquals( 6, a.remove( 2 ) );
        assertEquals( 3, a.remove( 1 ) );
        assertEquals( 0, a.remove( 0 ) );
        assertEquals( "< taille=\"1\" : [ nb=\"1\" : 9 ] >", a.toString() );
    }

    // Enlever un élément dont l'index est trop petit.
    @Test
    void removeException1() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress();
        for( int i = 0; i < 4; ++ i ) {
            a.add(i, i);
        }

        assertEquals( "< taille=\"4\" : [ nb=\"4\" : 0, 1, 2, 3 ] >", a.toString() );
        assertThrows( IndexOutOfBoundsException.class, () -> a.remove( -1 ) );
        assertThrows( IndexOutOfBoundsException.class, () -> a.remove( -3 ) );
    }

    // Enlever un élément dont l'index est trop petit.
    @Test
    void removeException2() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress();
        for( int i = 0; i < 4; ++ i ) {
            a.add(i, i);
        }

        assertEquals( "< taille=\"4\" : [ nb=\"4\" : 0, 1, 2, 3 ] >", a.toString() );
        assertThrows( IndexOutOfBoundsException.class, () -> a.remove( 4 ) );
        assertThrows( IndexOutOfBoundsException.class, () -> a.remove( 6 ) );
    }

    // acceder au seul élément d'une liste de 1 élément.
    @Test
    void get1() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 16 );
        a.add( 0, 15 );

        assertEquals( 15, a.get( 0 ) );
    }

    // acceder au premier élément d'une liste avec premier chainon a plus d'element.
    @Test
    void get2() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 10 );
        a.add( 0, 15 );
        a.add( 1, 13 );
        a.add( 2, 11 );
        a.add( 3, 9 );

        assertEquals( 15, a.get( 0 ) );
    }

    // acceder au dernier élément d'une liste.
    @Test
    void get3() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 4 );
        a.add( 0, 15 );
        a.add( 1, 13 );
        a.add( 2, 11 );
        a.add( 3, 9 );

        assertEquals( 9, a.get( 3 ) );
    }

    // acceder au dernier élément d'un chainon qui n'est pas le dernier.
    @Test
    void get4() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 3 );
        a.add( 0, 15 );
        a.add( 1, 13 );
        a.add( 2, 11 );
        a.add( 3, 9 );
        a.add( 4, 7 );

        assertEquals( 11, a.get( 2 ) );
    }

    // acceder au premier élément d'un chainon qui n'est pas le premier.
    @Test
    void get5() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 3 );
        a.add( 0, 15 );
        a.add( 1, 13 );
        a.add( 2, 11 );
        a.add( 3, 9 );
        a.add( 4, 7 );

        assertEquals( 9, a.get( 3 ) );
    }

    // acceder au premier élément d'un chainon qui n'est pas le premier.
    @Test
    void get6() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 3 );
        a.add( 0, 15 );
        a.add( 1, 13 );
        a.add( 2, 11 );
        a.add( 3, 9 );
        a.add( 4, 7 );
        a.add( 5, 5 );
        a.add( 6, 3 );

        assertEquals( 7, a.get( 4 ) );
    }

    // acceder a un index negatif.
    @Test
    void getException1() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 3 );
        a.add( 0, 15 );
        a.add( 1, 13 );
        a.add( 2, 11 );
        a.add( 3, 9 );
        a.add( 4, 7 );
        a.add( 5, 5 );
        a.add( 6, 3 );

        assertThrows( IndexOutOfBoundsException.class, () -> a.get( -1 ) );
        assertThrows( IndexOutOfBoundsException.class, () -> a.get( -5 ) );
    }

    // acceder a un index plus grand que la taille.
    @Test
    void getException2() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 3 );
        a.add( 0, 15 );
        a.add( 1, 13 );
        a.add( 2, 11 );
        a.add( 3, 9 );
        a.add( 4, 7 );
        a.add( 5, 5 );
        a.add( 6, 3 );

        assertThrows( IndexOutOfBoundsException.class, () -> a.get( 7 ) );
        assertThrows( IndexOutOfBoundsException.class, () -> a.get( 12 ) );
    }

    // modifier le seul élément d'une liste de 1 élément.
    @Test
    void set1() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 16 );
        a.add( 0, 15 );

        assertEquals( 15, a.set( 0, 2 ) );
        assertEquals( "< taille=\"1\" : [ nb=\"1\" : 2 ] >", a.toString() );
    }

    // modifier le premier élément d'une liste avec premier chainon a plus d'element.
    @Test
    void set2() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 10 );
        a.add( 0, 15 );
        a.add( 1, 13 );
        a.add( 2, 11 );
        a.add( 3, 9 );

        assertEquals( 15, a.set( 0, 2 ) );
        assertEquals( "< taille=\"4\" : [ nb=\"4\" : 2, 13, 11, 9 ] >", a.toString() );
    }

    // modifier le dernier élément d'une liste.
    @Test
    void set3() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 4 );
        a.add( 0, 15 );
        a.add( 1, 13 );
        a.add( 2, 11 );
        a.add( 3, 9 );

        assertEquals( 9, a.set( 3, 8 ) );
        assertEquals( "< taille=\"4\" : [ nb=\"4\" : 15, 13, 11, 8 ] >", a.toString() );
    }

    // modifier le dernier élément d'un chainon qui n'est pas le dernier.
    @Test
    void set4() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 3 );
        a.add( 0, 15 );
        a.add( 1, 13 );
        a.add( 2, 11 );
        a.add( 3, 9 );
        a.add( 4, 7 );

        assertEquals( 11, a.set( 2, 6 ) );
        assertEquals( "< taille=\"5\" : [ nb=\"3\" : 15, 13, 6 ], [ nb=\"2\" : 9, 7 ] >", a.toString() );
    }

    // modifier le premier élément d'un chainon qui n'est pas le premier.
    @Test
    void set5() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 3 );
        a.add( 0, 15 );
        a.add( 1, 13 );
        a.add( 2, 11 );
        a.add( 3, 9 );
        a.add( 4, 7 );

        assertEquals( 9, a.set( 3, 8 ) );
        assertEquals( "< taille=\"5\" : [ nb=\"3\" : 15, 13, 11 ], [ nb=\"2\" : 8, 7 ] >", a.toString() );
    }

    // modifier le premier élément d'un chainon qui n'est pas le premier.
    @Test
    void set6() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 3 );
        a.add( 0, 15 );
        a.add( 1, 13 );
        a.add( 2, 11 );
        a.add( 3, 9 );
        a.add( 4, 7 );
        a.add( 5, 5 );
        a.add( 6, 3 );

        assertEquals( 7, a.set( 4, 10 ) );
        assertEquals( "< taille=\"7\" : [ nb=\"3\" : 15, 13, 11 ], [ nb=\"3\" : 9, 10, 5 ], [ nb=\"1\" : 3 ] >", a.toString() );
    }

    // modification a un index negatif.
    @Test
    void setException1() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 3 );
        a.add( 0, 15 );
        a.add( 1, 13 );
        a.add( 2, 11 );
        a.add( 3, 9 );
        a.add( 4, 7 );
        a.add( 5, 5 );
        a.add( 6, 3 );

        assertThrows( IndexOutOfBoundsException.class, () -> a.set( -1, 8 ) );
        assertThrows( IndexOutOfBoundsException.class, () -> a.set( -5, 9 ) );
    }

    // modification a un index plus grand que la taille.
    @Test
    void setException2() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 3 );
        a.add( 0, 15 );
        a.add( 1, 13 );
        a.add( 2, 11 );
        a.add( 3, 9 );
        a.add( 4, 7 );
        a.add( 5, 5 );
        a.add( 6, 3 );

        assertThrows( IndexOutOfBoundsException.class, () -> a.set( 7, 6 ) );
        assertThrows( IndexOutOfBoundsException.class, () -> a.set( 12, 6 ) );
    }

    // modification a un index plus grand que la taille.
    @Test
    void setException3() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 3 );
        a.add( 0, 15 );
        a.add( 1, 13 );
        a.add( 2, 11 );
        a.add( 3, 9 );
        a.add( 4, 7 );
        a.add( 5, 5 );
        a.add( 6, 3 );

        assertThrows( NullPointerException.class, () -> a.set( 3, null ) );
    }

    // Trouve l'élément qui est a la premier case d'une liste de 1 case.
    @Test
    void indexOf1() {
        ListeChaineExpress< String > a = new ListeChaineExpress( 3 );
        a.add( 0, "a" );

        assertEquals( 0, a.indexOf( "a" ) );
    }

    // Trouve l'élément qui est a la derniere case d'une liste.
    @Test
    void indexOf2() {
        ListeChaineExpress< String > a = new ListeChaineExpress( 12 );
        a.add( 0, "a" );
        a.add( 1, "b" );
        a.add( 2, "c" );
        a.add( 3, "d" );
        a.add( 4, "e" );

        assertEquals( 4, a.indexOf( "e" ) );
    }

    // Trouve l'élément qui est dans une case a l'interieur de la liste.
    @Test
    void indexOf3() {
        ListeChaineExpress< String > a = new ListeChaineExpress( 12 );
        a.add( 0, "a" );
        a.add( 1, "b" );
        a.add( 2, "c" );
        a.add( 3, "d" );
        a.add( 4, "e" );

        assertEquals( 3, a.indexOf( "d" ) );
    }

    // Trouve l'élément qui est dans une case a l'interieur de la liste et a la fin d'un tableau.
    @Test
    void indexOf4() {
        ListeChaineExpress< String > a = new ListeChaineExpress( 3 );
        a.add( 0, "a" );
        a.add( 1, "b" );
        a.add( 2, "c" );
        a.add( 3, "d" );
        a.add( 4, "e" );

        assertEquals( 2, a.indexOf( "c" ) );
    }

    // Trouve l'élément qui est dans une case a l'interieur de la liste et au debut d'un tableau.
    @Test
    void indexOf5() {
        ListeChaineExpress< String > a = new ListeChaineExpress( 3 );
        a.add( 0, "a" );
        a.add( 1, "b" );
        a.add( 2, "c" );
        a.add( 3, "d" );
        a.add( 4, "e" );

        assertEquals( 3, a.indexOf( "d" ) );
    }

    // Trouve l'élément qui est le premier de plusieurs sur deux tableaux.
    @Test
    void indexOf6() {
        ListeChaineExpress< String > a = new ListeChaineExpress( 3 );
        a.add( 0, "a" );
        a.add( 1, "b" );
        a.add( 2, "c" );
        a.add( 3, "b" );
        a.add( 4, "a" );

        assertEquals( 1, a.indexOf( "b" ) );
    }

    // Trouve l'élément qui est le premier de plusieurs sur un tableaux.
    @Test
    void indexOf7() {
        ListeChaineExpress< String > a = new ListeChaineExpress( 9 );
        a.add( 0, "a" );
        a.add( 1, "b" );
        a.add( 2, "c" );
        a.add( 3, "b" );
        a.add( 4, "a" );

        assertEquals( 1, a.indexOf( "b" ) );
    }

    // Ne Trouve pas l'élément.
    @Test
    void indexOf8() {
        ListeChaineExpress< String > a = new ListeChaineExpress( 9 );
        a.add( 0, "a" );
        a.add( 1, "b" );
        a.add( 2, "c" );
        a.add( 3, "b" );
        a.add( 4, "a" );

        assertEquals( -1, a.indexOf( "f" ) );
    }

    // Ne Trouve pas l'élément dans une liste vide.
    @Test
    void indexOf9() {
        ListeChaineExpress< String > a = new ListeChaineExpress( 9 );

        assertEquals( -1, a.indexOf( "f" ) );
    }

    // Exeption pour l'élément null.
    @Test
    void indexOfException1() {
        ListeChaineExpress< String > a = new ListeChaineExpress( 9 );
        a.add( 0, "a" );
        a.add( 1, "b" );
        a.add( 2, "c" );
        a.add( 3, "b" );
        a.add( 4, "a" );

        assertThrows( NullPointerException.class, () -> a.indexOf( null ) );
    }

    // remplace dans une liste vide.
    @Test
    void replaceAll1() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 5 );
        a.replaceAll( ( x ) -> x + 1 );
        assertEquals( "< taille=\"0\" :  >", a.toString() );
    }

    // remplace dans une liste de 1 élément.
    @Test
    void replaceAll2() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 5 );
        for( int i = 0; i < 1; ++ i ) {
            a.add( i, i * 2 );
        }

        a.replaceAll( ( x ) -> x + 1 );
        assertEquals( "< taille=\"1\" : [ nb=\"1\" : 1 ] >", a.toString() );
    }

    // remplace dans une liste de quelques éléments dans 1 tableau.
    @Test
    void replaceAll3() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 5 );
        for( int i = 0; i < 4; ++ i ) {
            a.add( i, i * 3 );
        }

        a.replaceAll( ( x ) -> x + 2 );
        assertEquals( "< taille=\"4\" : [ nb=\"4\" : 2, 5, 8, 11 ] >", a.toString() );
    }

    // remplace dans une liste de quelques éléments dans plusieurs tableaux.
    @Test
    void replaceAll4() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 2 );
        for( int i = 0; i < 5; ++ i ) {
            a.add( i, i + 1 );
        }

        a.replaceAll( ( x ) -> x * 2 );
        assertEquals( "< taille=\"5\" : [ nb=\"2\" : 2, 4 ], [ nb=\"2\" : 6, 8 ], [ nb=\"1\" : 10 ] >", a.toString() );
    }

    // L'opérateur est null.
    @Test
    void replaceAllException1() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 2 );
        for( int i = 0; i < 5; ++ i ) {
            a.add( i, i + 1 );
        }

        assertThrows( NullPointerException.class, () -> a.replaceAll( null ) );
    }

    // L'opérateur donne une valeur null.
    @Test
    void replaceAllException2() {
        ListeChaineExpress< Integer > a = new ListeChaineExpress( 2 );
        for( int i = 0; i < 5; ++ i ) {
            a.add( i, i - 2 );
        }

        assertThrows( NullPointerException.class, () -> a.replaceAll( ( x ) -> 0 == x ? null : 1 ) );
    }

    // tests de charge.
    // Pour étudier les performances de la structure.

    public static final int NB_ITERATION_CHARGE = 75_000;
    // public static final int CAPACITE = 2; // 5.045 s
    // public static final int CAPACITE = 4; // 3.018 s
    // public static final int CAPACITE = 8; // 2.269 s
    // public static final int CAPACITE = 16; // 1.804 s
    // public static final int CAPACITE = 24; // 1.634 s
    // public static final int CAPACITE = 32; // 1.294 s
    public static final int CAPACITE = 40; // 1.174 s
    // public static final int CAPACITE = 48; // 1.186 s
    // public static final int CAPACITE = 56; // 1.330 s
    // public static final int CAPACITE = 64; // 1.408 s

    // avec un ArrayList : 0.032 s
    // acec un LinkedList : 2.641 s

    // charge du Add
    @Test
    void chargeListeChaineExpress() {
        Random generateur = new Random( 0 );
        ListeChaineExpress< Integer > a = new ListeChaineExpress( CAPACITE );
        a.add( 0, 1 );
        for( int i = 1; i < NB_ITERATION_CHARGE; ++ i ) {
            a.add( generateur.nextInt( i ), 0 );
        }
    }

    // charge du ArrayList
    @Test
    void chargeArrayList() {
        Random generateur = new Random( 0 );
        ArrayList< Integer > a = new ArrayList();
        a.add( 0, 1 );
        for( int i = 1; i < NB_ITERATION_CHARGE; ++ i ) {
            a.add( generateur.nextInt( i ), 0 );
        }
    }

    // charge du LinkedList
    @Test
    void chargeLinkedList() {
        Random generateur = new Random( 0 );
        LinkedList< Integer > a = new LinkedList();
        a.add( 0, 1 );
        for( int i = 1; i < NB_ITERATION_CHARGE; ++ i ) {
            a.add( generateur.nextInt( i ), 0 );
        }
    }
}