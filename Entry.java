//clasa Entry descrie o pozitie de pe tabla de sah
public class Entry {
    //coordonata din multimea {1, 2, 3, 4, 5, 6, 7, 8}
    public Integer number;
    //coordonata din multimea {a, b, c, d, e, f, g, h}
    public String letter;
    //piesa aflata pe pozitia descrisa de coordonatele anterioare
    public Piece piece;

    //constructorul clasei
    public Entry(Integer nr, String lt) {
        this.letter = lt;
        this.number = nr;
        //la creerea matricei initial toate pozitiile vor fi goale
        this.piece = new Piece(PieceType.f, Color.n);
    }
}
