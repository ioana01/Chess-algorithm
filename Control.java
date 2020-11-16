// clasa ce contine metodele pentru lucrul cu matricea
public class Control {

    // metoda ce creeaza matricea (reprezentarea tablii de sah)
    public void makeTable(ChessTable t) {
        Integer[] numbers = {8, 7, 6, 5, 4, 3, 2, 1};
        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h"};
        int i, j;

        //setam cordonatele tablei de 8 X 8 pozitii
        for(i = 0; i < 8; i++)
            for(j = 0; j < 8; j++) {
                t.setPosition(i, j, numbers[i], letters[j]);
            }

        for(i = 0; i < 8; i++) {
            // asezam randul de pioni albi (sunt pe linia 7 pe tabla)
            t.setPiece(new Piece(PieceType.p, Color.w), 6, i);
            // asezam randul de pioni negri (sunt pe linia 2 pe tabla)
            t.setPiece(new Piece(PieceType.p, Color.b), 1, i);
        }

        // asezam cei 2 regi pe pozitiile de inceput
        t.setPiece(new Piece(PieceType.k, Color.w), 7, 4);
        t.setPiece(new Piece(PieceType.k, Color.b), 0, 4);

        // aezam cele 2 regine pe pozitiile de inceput
        t.setPiece(new Piece(PieceType.q, Color.w), 7, 3);
        t.setPiece(new Piece(PieceType.q, Color.b), 0, 3);

        // asezam cei 4 nebuni pe poitiile de inceput
        t.setPiece(new Piece(PieceType.b, Color.w), 7, 2);
        t.setPiece(new Piece(PieceType.b, Color.w), 7, 5);
        t.setPiece(new Piece(PieceType.b, Color.b), 0, 2);
        t.setPiece(new Piece(PieceType.b, Color.b), 0, 5);

        // asezam cei 4 cai pe pozitiile de inceput
        t.setPiece(new Piece(PieceType.n, Color.w), 7, 1);
        t.setPiece(new Piece(PieceType.n, Color.w), 7, 6);
        t.setPiece(new Piece(PieceType.n, Color.b), 0, 1);
        t.setPiece(new Piece(PieceType.n, Color.b), 0, 6);

        // asezam cele 4 ture pe pozitiile de inceput
        t.setPiece(new Piece(PieceType.r, Color.w), 7, 0);
        t.setPiece(new Piece(PieceType.r, Color.w), 7, 7);
        t.setPiece(new Piece(PieceType.r, Color.b), 0, 0);
        t.setPiece(new Piece(PieceType.r, Color.b), 0, 7);

    }

    // metoda ce afiseaza matricea
    public void printTable(ChessTable t) {
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                // afisarea e dupa formatul CuloareTip
                System.out.print(t.getPosition(i, j).piece.color.toString() + t.getPosition(i, j).piece.pieceType + " ");

            System.out.println();
        }
        System.out.println();
    }

}
