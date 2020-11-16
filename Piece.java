enum PieceType {
    //    KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN, FREE
    k, q, r, b, n, p, f
}

enum Color {
    //    BLACK, WHITE, NONE
    b, w, n
}

// clasa Piece descrie o piesa prin tipul piesei si culoare
// daca o pozitie e libera va avea tipul "free" si culoarea "none"
public class Piece {
    public PieceType pieceType;
    public Color color;

    // constructorul clasei
    public Piece(PieceType p, Color c) {
        this.color = c;
        this.pieceType = p;
    }
}
