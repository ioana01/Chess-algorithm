class Move {
	public int player, x1, y1, x2, y2;
	PieceType piece;

	public Move(int player, int x1, int y1, int x2, int y2, PieceType piece) {
		this.player = player; /* Jucatorul care face mutarea */
		this.x1 = x1; /* Linia */
		this.y1 = y1; /* Coloana */
		this.x2 = x2;
		this.y2 = y2;
		this.piece = piece;
	}

	public Move(int player) {
		this(player, -1, -1, -1, -1, PieceType.f);
	}
}