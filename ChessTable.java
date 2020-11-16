import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

// clasa ce descrie tabla de sah
public class ChessTable implements Cloneable {
	// tabla de sah e reprezentata sub forma unei matrici de 8 X 8 Entry-uri
	public Entry[][] table;
	public static int Inf = 123456789;

	// constructorul clasei
	public ChessTable() {
		this.table = new Entry[8][8];
	}

	// metoda ce creeaza un Entry (coordonate + tipul de piesa) pentru pozitia (p1, p2)
	public void setPosition(int p1, int p2, Integer nr, String lt) {

		table[p1][p2] = new Entry(nr, lt);
	}

	// metoda ce returneaza Entry-ul de pe pozitia (p1, p2)
	public Entry getPosition(int p1, int p2) {

		return table[p1][p2];
	}

	// metoda ce seteaza o piesa pe pozitia (p1, p2)
	public void setPiece(Piece piece, int p1, int p2) {
		if(getPosition(p1, p2) != null) {
			// particularizam membrul "piece" pentru Entry-urile care contin piese
			// cand au fost instantiate dupa apelul constructorului, au fost setate ca avand piese de tip "free"
			getPosition(p1, p2).piece = piece;
		}

	}

	// metoda ce adauga urmatoarele miscari posibile ale unui pion si intoarce una random
	public Vector<Position> pawnMoves(int i, int j) {
		// vector de mutari posibile
		Vector<Position> availablePos = new Vector<>();
		int ok;

		ok = 0;

		// verificam daca piesa e pion alb, pe pozitia initiala
		// ca prima mutare poate alege daca se deplaseaza o pozitie sau doua
		if (i == 6 && getPosition(i, j).piece.color == Color.w) {
			// daca urmatoarele 2 pozitii sunt libere, o putem adauga si pe a doua la vector
			// nu poate sari peste o piesa pentru a ajunge pe a doua pozitie
			if (getPosition(i - 2, j).piece.pieceType == PieceType.f && getPosition(i - 1, j).piece.pieceType == PieceType.f) {
				availablePos.add(new Position(i - 2, j));
				ok = 1;
			}

			// verificam daca piesa e pion negru, pe pozitia initiala
			// ca prima mutare poate alege daca se deplaseaza o pozitie sau doua
		} else if (i == 1 && getPosition(i, j).piece.color == Color.b) {
			if (getPosition(i + 2, j).piece.pieceType == PieceType.f && getPosition(i + 1, j).piece.pieceType == PieceType.f) {
				// daca urmatoarele 2 pozitii sunt libere, o putem adauga si pe a doua la vector
				// nu poate sari peste o piesa pentru a ajunge pe a doua pozitie
				availablePos.add(new Position(i + 2, j));
				ok = 1;
			}
		}

		if(getPosition(i, j).piece.color == Color.w) {
			// adaugam pozitia imediat urmatoare din fata lui daca e libera
			if(i - 1 >= 0) {
				if (getPosition(i - 1, j).piece.pieceType == PieceType.f) {
					availablePos.add(new Position(i - 1, j));
					ok = 1;
				}
			}
			if(i - 1 >= 0 && j + 1 < 8) {
				if (getPosition(i - 1, j + 1).piece.pieceType != PieceType.f) {
					if (getPosition(i - 1, j + 1).piece.color == Color.b) {
						availablePos.add(new Position(i - 1, j + 1));
						ok = 1;
					}
				}
			}
			if(i - 1 >= 0 && j - 1 >= 0) {
				if (getPosition(i - 1, j - 1).piece.pieceType != PieceType.f) {
					if (getPosition(i - 1, j - 1).piece.color == Color.b) {
						availablePos.add(new Position(i - 1, j - 1));
						ok = 1;
					}
				}
			}
			// verificam daca e un pion negru pe o pozitie oarecare
		} else if(getPosition(i, j).piece.color == Color.b) {
			// adaugam pozitia imediat urmatoare din fata lui daca e libera
			if(i + 1 < 8) {
				if (getPosition(i + 1, j).piece.pieceType == PieceType.f) {
					availablePos.add(new Position(i + 1, j));
					ok = 1;
				}
			}
			if(i + 1 < 8 && j + 1 < 8) {
				if (getPosition(i + 1, j + 1).piece.pieceType != PieceType.f) {
					if (getPosition(i + 1, j + 1).piece.color == Color.w) {
						availablePos.add(new Position(i + 1, j + 1));
						ok = 1;
					}
				}
			}
			if(i + 1 < 8 && j - 1 >= 0) {
				if (getPosition(i + 1, j - 1).piece.pieceType != PieceType.f) {
					if (getPosition(i + 1, j - 1).piece.color == Color.w) {
						availablePos.add(new Position(i + 1, j - 1));
						ok = 1;
					}
				}
			}
		}

		// daca ok = 1 inseamna ca am gasit cel putin o mutare disponibila
		if(ok == 1) {
			return availablePos;
		} else
			return null;
	}

	// metoda ce returneaza o pozitie random disponibila pentru o tura
	public Position moveRook(int i, int j) {
		Vector<Position> availablePos;

		// apelam metoda ce intoarce vectorul de mutari disponibile
		availablePos = rookMoves(i, j);

		int random = new Random().nextInt(availablePos.size());

		return availablePos.get(random);
	}

	// metoda ce returneaza o pozitie random disponibila pentru un rege
	public Position moveKing(int i, int j) {
		Vector<Position> availablePos;

		// apelam metoda ce intoarce vectorul de mutari disponibile
		availablePos = kingMoves(i, j);

		int random = new Random().nextInt(availablePos.size());

		return availablePos.get(random);
	}

	// metoda ce returneaza o pozitie random disponibila pentru un nebun
	public Position moveBishop(int i, int j) {
		Vector<Position> availablePos = new Vector<>();

		// apelam metoda ce intoarce vectorul de mutari disponibile
		availablePos = bishopMoves(i, j);

		int random = new Random().nextInt(availablePos.size());

		return availablePos.get(random);
	}

	// metoda ce returneaza o pozitie random disponibila pentru un cal
	public Position moveKnight(int i, int j) {
		Vector<Position> availablePos = new Vector<>();

		// apelam metoda ce intoarce vectorul de mutari disponibile
		availablePos = knightMoves(i, j);

		int random = new Random().nextInt(availablePos.size());

		return availablePos.get(random);
	}

	// metoda ce returneaza o pozitie random disponibila pentru o regina
	public Vector<Position> moveQueen(int i, int j) {
		Vector<Position> availablePos = new Vector<>();
		Vector<Position> aux = new Vector<>();

		// regina se poate deplasa pe aceleasi pozitii ca si tura si nebunul
		// adaugam in vector mutarile pozitiile disponibile pentru tura si nebun si alegem una
//        if(!availablePos.contains(moveRook(i, j)))
//            availablePos.add(moveRook(i, j));
//        if(!availablePos.contains(moveBishop(i, j)))
//            availablePos.add(moveBishop(i, j));
		availablePos = rookMoves(i, j);
		aux = bishopMoves(i, j);

		for(int k = 0; k < aux.size(); k++) {
			availablePos.add(aux.get(k));
		}

		return availablePos;
	}

	// metoda ce intoarce vectorul de pozitii disponibile pentru cal
	// sunt calculate in functie de limitele tablii si de modul de deplasare al piesei
	public Vector<Position> knightMoves(int i1, int i2) {
		Vector<Position> legalMoves = new Vector<>();

		if(getPiece(i1, i2).color == Color.b) {
			if (i1 - 2 >= 0 && i2 + 1 < 8 && getPiece(i1 - 2, i2 + 1).color != Color.b)
				legalMoves.add(new Position(i1 - 2, i2 + 1));
			if (i1 - 2 >= 0 && i2 - 1 >= 0 && getPiece(i1 - 2, i2 - 1).color != Color.b)
				legalMoves.add(new Position(i1 - 2, i2 - 1));
			if (i1 + 2 < 8 && i2 + 1 < 8 && getPiece(i1 + 2, i2 + 1).color != Color.b)
				legalMoves.add(new Position(i1 + 2, i2 + 1));
			if (i1 + 2 < 8 && i2 - 1 >= 0 && getPiece(i1 + 2, i2 - 1).color != Color.b)
				legalMoves.add(new Position(i1 + 2, i2 - 1));
			if (i1 - 1 >= 0 && i2 + 2 < 8 && getPiece(i1 - 1, i2 + 2).color != Color.b)
				legalMoves.add(new Position(i1 - 1, i2 + 2));
			if (i1 - 1 >= 0 && i2 - 2 >= 0 && getPiece(i1 - 1, i2 - 2).color != Color.b)
				legalMoves.add(new Position(i1 - 1, i2 - 2));
			if (i1 + 1 < 8 && i2 + 2 < 8 && getPiece(i1 + 1, i2 + 2).color != Color.b)
				legalMoves.add(new Position(i1 + 1, i2 + 2));
			if (i1 + 1 < 8 && i2 - 2 >= 0 && getPiece(i1 + 1, i2 - 2).color != Color.b)
				legalMoves.add(new Position(i1 + 1, i2 - 2));
		} else if(getPiece(i1, i2).color == Color.w) {
			if (i1 - 2 >= 0 && i2 + 1 < 8 && getPiece(i1 - 2, i2 + 1).color != Color.w)
				legalMoves.add(new Position(i1 - 2, i2 + 1));
			if (i1 - 2 >= 0 && i2 - 1 >= 0 && getPiece(i1 - 2, i2 - 1).color != Color.w)
				legalMoves.add(new Position(i1 - 2, i2 - 1));
			if (i1 + 2 < 8 && i2 + 1 < 8 && getPiece(i1 + 2, i2 + 1).color != Color.w)
				legalMoves.add(new Position(i1 + 2, i2 + 1));
			if (i1 + 2 < 8 && i2 - 1 >= 0 && getPiece(i1 + 2, i2 - 1).color != Color.w)
				legalMoves.add(new Position(i1 + 2, i2 - 1));
			if (i1 - 1 >= 0 && i2 + 2 < 8 && getPiece(i1 - 1, i2 + 2).color != Color.w)
				legalMoves.add(new Position(i1 - 1, i2 + 2));
			if (i1 - 1 >= 0 && i2 - 2 >= 0 && getPiece(i1 - 1, i2 - 2).color != Color.w)
				legalMoves.add(new Position(i1 - 1, i2 - 2));
			if (i1 + 1 < 8 && i2 + 2 < 8 && getPiece(i1 + 1, i2 + 2).color != Color.w)
				legalMoves.add(new Position(i1 + 1, i2 + 2));
			if (i1 + 1 < 8 && i2 - 2 >= 0 && getPiece(i1 + 1, i2 - 2).color != Color.w)
				legalMoves.add(new Position(i1 + 1, i2 - 2));
		}

		return legalMoves;
	}

	// metoda ce intoarce vectorul de pozitii disponibile pentru tura
	// sunt calculate in functie de limitele tablii si de modul de deplasare al piesei
	public Vector<Position> rookMoves(int i1, int i2) {
		Vector<Position> legalMoves = new Vector<>();

		int p;

		if(getPiece(i1, i2).color == Color.b) {
			// miscarile pentru tura sunt cautate in 4 directii
			for (p = i1 + 1; p < 8; p++)
				if (getPosition(p, i2).piece.pieceType == PieceType.f)
					legalMoves.add(new Position(p, i2));
				else {
					// daca in drumul sau tura intalneste o alta piesa
					// nu poate avansa mai departe de aceasta intr-o singura miscare
					if(getPiece(p, i2).color == Color.w) {
						legalMoves.add(new Position(p, i2));
					}
					break;
				}

			for (p = i1 - 1; p >= 0; p--) {
				if (getPosition(p, i2).piece.pieceType == PieceType.f)
					legalMoves.add(new Position(p, i2));
				else {
					// daca in drumul sau tura intalneste o alta piesa
					// nu poate avansa mai departe de aceasta intr-o singura miscare
					if(getPiece(p, i2).color == Color.w) {
						legalMoves.add(new Position(p, i2));
					}
					break;
				}
			}

			for (p = i2 + 1; p < 8; p++)
				if (getPosition(i1, p).piece.pieceType == PieceType.f)
					legalMoves.add(new Position(i1, p));
				else {
					// daca in drumul sau tura intalneste o alta piesa
					// nu poate avansa mai departe de aceasta intr-o singura miscare
					if(getPiece(i1, p).color == Color.w) {
						legalMoves.add(new Position(i1, p));
					}
					break;
				}

			for (p = i2 - 1; p >= 0; p--) {
				if (getPosition(i1, p).piece.pieceType == PieceType.f)
					legalMoves.add(new Position(i1, p));
				else {
					// daca in drumul sau tura intalneste o alta piesa
					// nu poate avansa mai departe de aceasta intr-o singura miscare
					if(getPiece(i1, p).color == Color.w) {
						legalMoves.add(new Position(i1, p));
					}
					break;
				}
			}
		} else if(getPiece(i1, i2).color == Color.w) {
			// miscarile pentru tura sunt cautate in 4 directii
			for (p = i1 + 1; p < 8; p++)
				if (getPosition(p, i2).piece.pieceType == PieceType.f)
					legalMoves.add(new Position(p, i2));
				else {
					// daca in drumul sau tura intalneste o alta piesa
					// nu poate avansa mai departe de aceasta intr-o singura miscare
					if(getPiece(p, i2).color == Color.b) {
						legalMoves.add(new Position(p, i2));
					}
					break;
				}

			for (p = i1 - 1; p >= 0; p--) {
				if (getPosition(p, i2).piece.pieceType == PieceType.f)
					legalMoves.add(new Position(p, i2));
				else {
					// daca in drumul sau tura intalneste o alta piesa
					// nu poate avansa mai departe de aceasta intr-o singura miscare
					if(getPiece(p, i2).color == Color.b) {
						legalMoves.add(new Position(p, i2));
					}
					break;
				}
			}

			for (p = i2 + 1; p < 8; p++)
				if (getPosition(i1, p).piece.pieceType == PieceType.f)
					legalMoves.add(new Position(i1, p));
				else {
					// daca in drumul sau tura intalneste o alta piesa
					// nu poate avansa mai departe de aceasta intr-o singura miscare
					if(getPiece(i1, p).color == Color.b) {
						legalMoves.add(new Position(i1, p));
					}
					break;
				}

			for (p = i2 - 1; p >= 0; p--) {
				if (getPosition(i1, p).piece.pieceType == PieceType.f)
					legalMoves.add(new Position(i1, p));
				else {
					// daca in drumul sau tura intalneste o alta piesa
					// nu poate avansa mai departe de aceasta intr-o singura miscare
					if(getPiece(i1, p).color == Color.b) {
						legalMoves.add(new Position(i1, p));
					}
					break;
				}
			}
		}

		return legalMoves;
	}

	// metoda ce intoarce vectorul de pozitii disponibile pentru nebun
	// sunt calculate in functie de limitele tablii si de modul de deplasare al piesei
	public Vector<Position> bishopMoves(int i1, int i2) {
		Vector<Position> legalMoves = new Vector<>();

		int p, q;

		if(getPiece(i1, i2).color == Color.b) {
			// miscarile pentru nebun sunt cautate in 4 directii
			p = i1 + 1;
			q = i2 + 1;
			while (p < 8 && q < 8) {

				if (getPosition(p, q).piece.pieceType == PieceType.f) {
					legalMoves.add(new Position(p, q));

					// daca in drumul sau nebunul intalneste o alta piesa
					// nu poate avansa mai departe de aceasta intr-o singura miscare
				} else if (getPosition(p, q).piece.pieceType != PieceType.f) {
					if(getPosition(p, q).piece.color == Color.w)
						legalMoves.add(new Position(p, q));
					break;
				}

				p++;
				q++;
			}

			p = i1 + 1;
			q = i2 - 1;
			while (p < 8 && q >= 0) {
				if (getPosition(p, q).piece.pieceType == PieceType.f) {
					legalMoves.add(new Position(p, q));

					// daca in drumul sau nebunul intalneste o alta piesa
					// nu poate avansa mai departe de aceasta intr-o singura miscare
				} else if (getPosition(p, q).piece.pieceType != PieceType.f) {
					if(getPosition(p, q).piece.color == Color.w)
						legalMoves.add(new Position(p, q));
					break;
				}

				p++;
				q--;
			}

			p = i1 - 1;
			q = i2 - 1;
			while (p >= 0 && q >= 0) {
				if (getPosition(p, q).piece.pieceType == PieceType.f) {
					legalMoves.add(new Position(p, q));

					// daca in drumul sau nebunul intalneste o alta piesa
					// nu poate avansa mai departe de aceasta intr-o singura miscare
				} else if (getPosition(p, q).piece.pieceType != PieceType.f) {
					if(getPosition(p, q).piece.color == Color.w)
						legalMoves.add(new Position(p, q));
					break;
				}

				p--;
				q--;
			}

			p = i1 - 1;
			q = i2 + 1;
			while (p >= 0 && q < 8) {
				if (getPosition(p, q).piece.pieceType == PieceType.f) {
					legalMoves.add(new Position(p, q));

					// daca in drumul sau nebunul intalneste o alta piesa
					// nu poate avansa mai departe de aceasta intr-o singura miscare
				} else if (getPosition(p, q).piece.pieceType != PieceType.f) {
					if(getPosition(p, q).piece.color == Color.w)
						legalMoves.add(new Position(p, q));
					break;
				}

				p--;
				q++;
			}
		} else if(getPiece(i1, i2).color == Color.w) {
			// miscarile pentru nebun sunt cautate in 4 directii
			p = i1 + 1;
			q = i2 + 1;
			while (p < 8 && q < 8) {

				if (getPosition(p, q).piece.pieceType == PieceType.f) {
					legalMoves.add(new Position(p, q));

					// daca in drumul sau nebunul intalneste o alta piesa
					// nu poate avansa mai departe de aceasta intr-o singura miscare
				} else if (getPosition(p, q).piece.pieceType != PieceType.f) {
					if(getPosition(p, q).piece.color == Color.b)
						legalMoves.add(new Position(p, q));
					break;
				}

				p++;
				q++;
			}

			p = i1 + 1;
			q = i2 - 1;
			while (p < 8 && q >= 0) {
				if (getPosition(p, q).piece.pieceType == PieceType.f) {
					legalMoves.add(new Position(p, q));

					// daca in drumul sau nebunul intalneste o alta piesa
					// nu poate avansa mai departe de aceasta intr-o singura miscare
				} else if (getPosition(p, q).piece.pieceType != PieceType.f) {
					if(getPosition(p, q).piece.color == Color.b)
						legalMoves.add(new Position(p, q));
					break;
				}

				p++;
				q--;
			}

			p = i1 - 1;
			q = i2 - 1;
			while (p >= 0 && q >= 0) {
				if (getPosition(p, q).piece.pieceType == PieceType.f) {
					legalMoves.add(new Position(p, q));

					// daca in drumul sau nebunul intalneste o alta piesa
					// nu poate avansa mai departe de aceasta intr-o singura miscare
				} else if (getPosition(p, q).piece.pieceType != PieceType.f) {
					if(getPosition(p, q).piece.color == Color.b)
						legalMoves.add(new Position(p, q));
					break;
				}

				p--;
				q--;
			}

			p = i1 - 1;
			q = i2 + 1;
			while (p >= 0 && q < 8) {
				if (getPosition(p, q).piece.pieceType == PieceType.f) {
					legalMoves.add(new Position(p, q));

					// daca in drumul sau nebunul intalneste o alta piesa
					// nu poate avansa mai departe de aceasta intr-o singura miscare
				} else if (getPosition(p, q).piece.pieceType != PieceType.f) {
					if(getPosition(p, q).piece.color == Color.b)
						legalMoves.add(new Position(p, q));
					break;
				}

				p--;
				q++;
			}
		}

		return legalMoves;
	}

	// metoda ce intoarce vectorul de pozitii disponibile pentru rege
	// sunt calculate in functie de limitele tablii si de modul de deplasare al piesei
	public Vector<Position> kingMoves(int i1, int i2) {
		Vector<Position> legalMoves = new Vector<>();

		// regele se deplaseaza o singura pozitie
		// nu mai trebuie verificat unde se opreste
		if(getPiece(i1, i2).color == Color.b) {
			if (i1 + 1 < 8 && getPiece(i1 + 1, i2).color != Color.b)
				legalMoves.add(new Position(i1 + 1, i2));
			if (i1 - 1 >= 0 && getPiece(i1 - 1, i2).color != Color.b)
				legalMoves.add(new Position(i1 - 1, i2));
			if (i2 - 1 >= 0 && getPiece(i1, i2 - 1).color != Color.b)
				legalMoves.add(new Position(i1, i2 - 1));
			if (i2 + 1 < 8 && getPiece(i1, i2 + 1).color != Color.b)
				legalMoves.add(new Position(i1, i2 + 1));
			if (i2 + 1 < 8 && i1 + 1 < 8 && getPiece(i1 + 1, i2 + 1).color != Color.b)
				legalMoves.add(new Position(i1 + 1, i2 + 1));
			if (i2 + 1 < 8 && i1 - 1 >= 0 && getPiece(i1 - 1, i2 + 1).color != Color.b)
				legalMoves.add(new Position(i1 - 1, i2 + 1));
			if (i2 - 1 >= 0 && i1 - 1 >= 0 && getPiece(i1 - 1, i2 - 1).color != Color.b)
				legalMoves.add(new Position(i1 - 1, i2 - 1));
			if (i2 - 1 >= 0 && i1 + 1 < 8 && getPiece(i1 + 1, i2 - 1).color != Color.b)
				legalMoves.add(new Position(i1 + 1, i2 - 1));
		} else if(getPiece(i1, i2).color == Color.w) {
			if (i1 + 1 < 8 && getPiece(i1 + 1, i2).color != Color.w)
				legalMoves.add(new Position(i1 + 1, i2));
			if (i1 - 1 >= 0 && getPiece(i1 - 1, i2).color != Color.w)
				legalMoves.add(new Position(i1 - 1, i2));
			if (i2 - 1 >= 0 && getPiece(i1, i2 - 1).color != Color.w)
				legalMoves.add(new Position(i1, i2 - 1));
			if (i2 + 1 < 8 && getPiece(i1, i2 + 1).color != Color.w)
				legalMoves.add(new Position(i1, i2 + 1));
			if (i2 + 1 < 8 && i1 + 1 < 8 && getPiece(i1 + 1, i2 + 1).color != Color.w)
				legalMoves.add(new Position(i1 + 1, i2 + 1));
			if (i2 + 1 < 8 && i1 - 1 >= 0 && getPiece(i1 - 1, i2 + 1).color != Color.w)
				legalMoves.add(new Position(i1 - 1, i2 + 1));
			if (i2 - 1 >= 0 && i1 - 1 >= 0 && getPiece(i1 - 1, i2 - 1).color != Color.w)
				legalMoves.add(new Position(i1 - 1, i2 - 1));
			if (i2 - 1 >= 0 && i1 + 1 < 8 && getPiece(i1 + 1, i2 - 1).color != Color.w)
				legalMoves.add(new Position(i1 + 1, i2 - 1));
		}

		return legalMoves;
	}

	// metoda ce verifica daca o mutare poate fi efectuata
	public boolean isLegal(int i1, int i2, int f1, int f2, PieceType type) {
		Vector<Position> legalMoves = new Vector<>();

		// obtinem vectorul de miscari posibile pentru tipul de piesa dat ca parametru
		if(type == PieceType.n) {
			legalMoves = knightMoves(i1, i2);
		} else if(type == PieceType.r) {
			legalMoves = rookMoves(i1, i2);
		} else if(type == PieceType.b){
			legalMoves = bishopMoves(i1, i2);
		} else if(type == PieceType.k) {
			legalMoves = kingMoves(i1, i2);
		} else if(type == PieceType.q) {
			// in cazul reginei obtinem mutarile valabile pentru tura si nebun
			legalMoves = rookMoves(i1, i2);
			Vector<Position> aux = bishopMoves(i1, i2);

			int ok;
			for(int k = 0; k < aux.size(); k++) {
				ok = 0;
				// verificam ce mutari din vectorul pentru nebun nu se afla si in cel pentru tura
				for (int r = 0; r < legalMoves.size(); r++)
					if (legalMoves.get(r).i == aux.get(k).i && legalMoves.get(r).j == aux.get(k).j) {
						ok = 1;
					}

				// si le adaugam pe cele care nu au duplicat
				if(ok == 0)
					legalMoves.add(aux.get(k));
			}
		}


		// verificam daca mutarea pe care vrem sa o facem se afla in vectorul de mutari posibile
		for(int p = 0; p < legalMoves.size(); p++)
			if(legalMoves.get(p).i == f1 && legalMoves.get(p).j == f2) {
				// daca se afla inseamna ca mutarea e posibila si returnam true
				return true;
			}

		return false;
	}

	// metoda pentru returnarea unei piese de pe pozitia (p1, p2)
	public Piece getPiece(int p1, int p2) {

		return table[p1][p2].piece;
	}

	// metoda pentru mutarea unei piese
	public void movePiece(int initial1, int initial2, int final1, int final2) {
		Piece auxPiece = getPiece(initial1, initial2);

		// setam pozitia finala  ca fiind goala
		setPiece(new Piece(PieceType.f, Color.n), final1, final2);
		// setam pozitia initiala ca fiind goala
		setPiece(new Piece(PieceType.f, Color.n), initial1, initial2);
		// setam piesa pe care dorim sa o deplasam pe pozitia finala
		setPiece(auxPiece, final1, final2);
	}

	public boolean checkSlavDefense() {
		if((getPiece(4, 3).pieceType == PieceType.p && getPiece(4, 3).color == Color.w)
				&& (getPiece(4, 2).pieceType == PieceType.p && getPiece(4, 2).color == Color.w)
				&& (getPiece(3, 3).pieceType == PieceType.p && getPiece(3, 3).color == Color.b)) {
			return true;
		}

		return false;
	}

	public boolean checkRuyLopezOpening() {
		if((getPiece(4, 4).pieceType == PieceType.p && getPiece(4, 4).color == Color.w)
				&& (getPiece(3, 4).pieceType == PieceType.p && getPiece(3, 4).color == Color.b)
				&& (getPiece(5, 5).pieceType == PieceType.n && getPiece(5, 5).color == Color.w)) {
			return true;
		}

		return false;
	}

	// metoda ce returneaza pozitia unei piese cautate dupa nume si culoare
	public Position getPieceByName(Color color, PieceType type) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(table[i][j].piece.pieceType == type && table[i][j].piece.color == color) {
					return new Position(i, j);
				}
			}
		}

		return new Position(-1, -1);
	}

	// metoda ce returneaza toate pozitiile din jurul unui rege
	// nu sunt neaparat 8
	// ex. regele e pe margine
	public Vector<Position> aroundKing(int p1, int p2) {
		Vector<Position> result = new Vector<>();

		if(p1 - 1 >= 0) {
			result.add(new Position(p1 - 1, p2));
		}

		if(p1 + 1 < 8) {
			result.add(new Position(p1 + 1, p2));
		}

		if(p2 - 1 >= 0) {
			result.add(new Position(p1, p2 - 1));
		}

		if(p2 + 1 < 8) {
			result.add(new Position(p1, p2 + 1));
		}

		if(p1 - 1 >= 0 && p2 - 1 >= 0) {
			result.add(new Position(p1 - 1, p2 - 1));
		}

		if(p1 + 1 < 8 && p2 - 1 >= 0) {
			result.add(new Position(p1 + 1, p2 - 1));
		}

		if(p1 - 1 >= 0 && p2 + 1 < 8) {
			result.add(new Position(p1 - 1, p2 + 1));
		}

		if(p1 + 1 < 8 && p2 + 1 < 8) {
			result.add(new Position(p1 + 1, p2 + 1));
		}

		return result;
	}


	// metoda ce verifica daca un regele oponentului e in pericol
	public boolean inDanger(int player) {
		// multimea mutarilor posibile ale jucatorului dat ca parametru
		ArrayList<Move> moves = get_moves(player);
		// pozitia regelui alb
		Position whiteK = getPieceByName(Color.w, PieceType.k);
		// pozitia regelui negru
		Position blackK = getPieceByName(Color.b, PieceType.k);

		// daca playerul dat joaca cu white
		if(player == 1) {
			for(int i = 0; i < moves.size(); i++) {
				// daca destinatia vreuneia dintre mutarile lui posibile
				// corespunde cu pozitia regelui adversarului
				// atunci il pune in pericol
				if(moves.get(i).x2 == blackK.i && moves.get(i).y2 == blackK.j) {
					return true;
				}
			}
		} else {
			// daca joaca cu black
			for(int i = 0; i < moves.size(); i++) {
				if(moves.get(i).x2 == whiteK.i && moves.get(i).y2 == whiteK.j) {
					return true;
				}
			}
		}

		return false;
	}

	public int ended() {
		Position blackKing = getPieceByName(Color.b, PieceType.k);
		Position whiteKing = getPieceByName(Color.w, PieceType.k);
		Vector<Position> aroundWhiteKing = new Vector<>();
		Vector<Position> aroundBlackKing = new Vector<>();
		Vector<Position> auxBlack = new Vector<>();
		Vector<Position> auxWhite = new Vector<>();

		// verificam daca vreunul din regi e in pericol
		if(inDanger(1) || inDanger(-1)) {
			// obtinem pozitiile din jurul lor
			if (whiteKing.i != -1 && whiteKing.j != -1)
				aroundWhiteKing = aroundKing(whiteKing.i, whiteKing.j);
			if (blackKing.i != -1 && blackKing.j != -1)
				aroundBlackKing = aroundKing(blackKing.i, blackKing.j);

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (getPiece(i, j).color == Color.w) {
						// daca oponentul oate ajunge pe o pozitie din jurul regelui
						// o adaugam intr-un vector auxiliar
						// si o setam (-1, -1) pentru a nu o mai verifica iar
						for (int k = 0; k < aroundBlackKing.size(); k++) {
							if (isLegal(i, j, aroundBlackKing.get(k).i, aroundBlackKing.get(k).j, getPiece(i, j).pieceType)) {
								auxBlack.add(aroundBlackKing.get(k));
								aroundBlackKing.get(k).i = -1;
								aroundBlackKing.get(k).j = -1;
							}
						}
					} else if (getPiece(i, j).color == Color.b) {
						// acelasi procedeu pentru cealalta culoare
						for (int k = 0; k < aroundWhiteKing.size(); k++) {
							if (isLegal(i, j, aroundWhiteKing.get(k).i, aroundWhiteKing.get(k).j, getPiece(i, j).pieceType)) {
								auxWhite.add(aroundWhiteKing.get(k));
								aroundWhiteKing.get(k).i = -1;
								aroundWhiteKing.get(k).j = -1;
							}
						}
					}
				}
			}

			// daca toate pozitiile din jurul unui rege in pericol pot fi atacate
			// atunci jocul s-a terminat -> sah mat
			if (auxBlack.size() == 8) {
				return 1;
			} else if (auxWhite.size() == 8) {
				return -1;
			}
		}

		// daca functia returneaza 0 atunci jocul nu s-a terminat inca
		return 0;
	}

	// verificam catigatorul
	public boolean winner(int player) {
		if(ended() == player * (-1))
			return true;

		return false;
	}

	// setam pentru fiecare tip de piesa o valoare
	// pentru a putea realiza o evaluare a state-ului curent
	public double getScoreByPieceType(int i, int j, ValueTable improve) {
		Piece piece = table[i][j].piece;

		if (piece.color == Color.b) {
			if (piece.pieceType == PieceType.p)
				return 10 + improve.get(piece.pieceType).get(7-i).get(j);
			else if (piece.pieceType == PieceType.n || piece.pieceType == PieceType.b) {
				return 30 + improve.get(piece.pieceType).get(7-i).get(j);
			} else if (piece.pieceType == PieceType.r) {
				return 50 + improve.get(piece.pieceType).get(7-i).get(j);
			} else if (piece.pieceType == PieceType.q) {
				return 90 + improve.get(piece.pieceType).get(7-i).get(j);
			}
			else if (piece.pieceType == PieceType.k) {
				return 2000 + improve.get(piece.pieceType).get(7-i).get(j);
			}
		}
		else if(piece.color == Color.w) {
			if (piece.pieceType == PieceType.p)
				return -10 - improve.get(piece.pieceType).get(i).get(j);
			else if (piece.pieceType == PieceType.n || piece.pieceType == PieceType.b) {
				return -30 - improve.get(piece.pieceType).get(i).get(j);
			} else if (piece.pieceType == PieceType.r) {
				return -50 - improve.get(piece.pieceType).get(i).get(j);
			} else if (piece.pieceType == PieceType.q) {
				return -90 - improve.get(piece.pieceType).get(i).get(j);
			}
			else if (piece.pieceType == PieceType.k) {
				return -2000 - improve.get(piece.pieceType).get(i).get(j);
			}
		}

		return 0;
	}

	// metoda ce ia in considerare pozitiile pionilor
	public double getScoreByPawnProtection(int player){
		double score = 0, score2 = 0;
		double pawnsPerLine = 0, blockedPawns = 0, isolatedPawns = 0;

		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (player == 1){
					// pioni conectati
					if (table[i][j].piece.pieceType == PieceType.p && table[i][j].piece.color == Color.w){
						pawnsPerLine++;
					}

					// daca un pion nu poate avansa, e blocat
					if (i > 0 && table[i-1][j].piece.pieceType != PieceType.f && table[i-1][j].piece.color == Color.b){
						blockedPawns++;  //d
					}

					// daca un pion nu are niciun aliat in jurul lui, e izolat
					if ((i > 0 && j > 0 && i < 7 && j < 7) &&
							(table[i-1][j].piece.color != Color.w && table[i-1][j-1].piece.color != Color.w
					&& table[i][j-1].piece.color != Color.w && table[i+1][j].piece.color != Color.w
					&& table[i][j+1].piece.color != Color.w && table[i+1][j+1].piece.color != Color.w
					&& table[i-1][j+1].piece.color != Color.w && table[i+1][j-1].piece.color != Color.w)){
						isolatedPawns++;
					}

					// lanturi de pioni
					if (((i > 0 && j > 0) && (table[i-1][j-1].piece.pieceType == PieceType.p && table[i-1][j-1].piece.color == Color.w))
					|| ((i > 0 && j < 7) && (table[i-1][j+1].piece.pieceType == PieceType.p && table[i-1][j+1].piece.color == Color.w))){
						score++;
					}
				}
				else {
					if (table[i][j].piece.pieceType == PieceType.p && table[i][j].piece.color == Color.b){
						pawnsPerLine++;
					}

					if (i < 7 && table[i+1][j].piece.pieceType != PieceType.f && table[i+1][j].piece.color == Color.w){
						blockedPawns++;
					}

					if ((i > 0 && j > 0 && i < 7 && j < 7) &&
							(table[i-1][j].piece.color != Color.b && table[i-1][j-1].piece.color != Color.b
							&& table[i][j-1].piece.color != Color.b && table[i+1][j].piece.color != Color.b
							&& table[i][j+1].piece.color != Color.b && table[i+1][j+1].piece.color != Color.b
							&& table[i-1][j+1].piece.color != Color.b && table[i+1][j-1].piece.color != Color.b)){
						isolatedPawns++;
					}

					if (((i < 7 && j < 7) && (table[i+1][j+1].piece.pieceType == PieceType.p && table[i+1][j+1].piece.color == Color.w))
							|| ((i < 7 && j > 0) && (table[i+1][j-1].piece.pieceType == PieceType.p && table[i+1][j-1].piece.color == Color.w))){
						score++;
					}
				}
			}

			// perechi de pioni de aceeasi culoare
			if (pawnsPerLine == 2){
				score -= 1;
			} else {
				score += (pawnsPerLine - 1);
			}
			pawnsPerLine = 0;
		}
		return score - (blockedPawns/2 + isolatedPawns/2);
	}

	//  un jucator este avantajat daca are piese in mijlocul tablei
	public int getScoreByCenterPieces(int player){
		Color color = Color.n;
		if (player == 1){
			color = Color.w;
		} else {
			color = Color.b;
		}
		int score = 0;
		for (int i = 3; i <= 4; i++){
			for (int j = 0 ; j < 8; j++){
				if(table[i][j].piece.color == color){
					score++;
				}
			}
		}
		return score;
	}

	//	pioni care nu pot fi atacati si au sansa de a fi promovati
	public int getScoreByPassedPawns(int player){
		int score = 0;
		boolean ok = false;
		ArrayList<Position> pawns = new ArrayList<Position>();
		ArrayList<Move> moves = get_moves(-player);
		Color color = Color.n;

		if (player == 1){
			color = Color.w;
		} else{
			color = Color.b;
		}

		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (table[i][j].piece.pieceType == PieceType.p && table[i][j].piece.color == color){
					pawns.add(new Position(i, j));
				}
			}
		}

		for (Position pawn: pawns){
			for(Move i: moves){
				if(i.x2 == pawn.i && i.y2 == pawn.j && i.piece == PieceType.p){
					ok = true;
					break;
				}
			}
			if (ok == false){
				score += 2;
			}
			ok = false;
		}

		return score;
	}


	// metoda pentru evaluare starii curente a jocului
	public double eval(int player, ValueTable improve) {
		// verificam daca jocul s-a terminat
		if(ended() != 0) {
			if(winner(player)) {
				return Inf;
			} else {
				return -Inf;
			}
		} else {
			Color color = Color.n;

			// vedem din perspectiva carei culori evaluam in functie de player
			if (player == -1){
				color = Color.w;
			}
			else {
				color = color.b;
			}

			double score = 0;

			// parcurgem tabla de sah
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					score += (getScoreByPieceType(i, j, improve) * player);
					if (table[i][j].piece.pieceType == PieceType.q && table[i][j].piece.color == color){
						score -= 100;
					}
					if (table[i][j].piece.pieceType == PieceType.r && table[i][j].piece.color == color){
						score -= 50;
					}
				}
			}
			double mobilityScore = 0.1 * (get_moves(1).size() - get_moves(-1).size());

			// adunam scorul in urma apelului celor 3 functii pentru verificarea strategiei
			score += (getScoreByPawnProtection(player) - getScoreByPawnProtection(-player));
			score += (getScoreByCenterPieces(player) - getScoreByCenterPieces(-player));
			score += (getScoreByPassedPawns(player) - getScoreByPassedPawns(-player));

			return score + mobilityScore;
		}
	}

	// metoda ce intoarce multimea mutarilor posibile ale playerului dat ca parametru
	public ArrayList<Move> get_moves(int player) {
		Color color = Color.n;
		ArrayList<Move> moves = new ArrayList<>();
		Vector<Position> aux = new Vector<>();

		// stabilim culoarea pentru care cautam mutari
		if(player == 1) {
			color = Color.w;
		} else if(player == -1) {
			color = Color.b;
		}

		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(getPiece(i, j).color == color) {
					// cautam mutarile in functie de tipul piesei
					if(getPiece(i, j).pieceType == PieceType.p) {
						aux = pawnMoves(i, j);
					} else if(getPiece(i, j).pieceType == PieceType.b) {
						aux = bishopMoves(i, j);
					} else if(getPiece(i, j).pieceType == PieceType.n) {
						aux = knightMoves(i, j);
					} else if(getPiece(i, j).pieceType == PieceType.r) {
						aux = rookMoves(i, j);
					} else if(getPiece(i, j).pieceType == PieceType.k) {
						aux = kingMoves(i, j);
					} else if(getPiece(i, j).pieceType == PieceType.q) {
						aux = moveQueen(i, j);
					}

					// verificam ca piesa d pe pozitia respectiva se poate deplasa undeva
					if(aux != null) {
						for (int k = 0; k < aux.size(); k++) {
							moves.add(new Move(player, i, j, aux.get(k).i, aux.get(k).j, getPiece(i, j).pieceType));
						}
					}
				}
			}
		}

		return moves;
	}

	// metoda ce intoarce tabla de joc in starea de dinainte de a fi savarsit o mutare
	public void undoMove(Piece initialPiece, Piece finalPiece, int initial1, int initial2, int final1, int final2) {
		Piece auxPiece = getPiece(initial1, initial2);

		// setam pozitia finala  ca fiind goala
		setPiece(new Piece(PieceType.f, Color.n), final1, final2);
		// setam pozitia initiala ca fiind goala
		setPiece(new Piece(PieceType.f, Color.n), initial1, initial2);
		// setam piesa pe care dorim sa o deplasam pe pozitia finala
		setPiece(finalPiece, final1, final2);
		setPiece(initialPiece, initial1, initial2);
	}


//	metoda ce verifica daca se poate face rocada
	public boolean checkCastling(int x1, int y1, int x2, int y2){
		if (x1 != x2){
			return false;
		}
		if (x1 != 0 && x1 != 7){
			return false;
		}
		if (y1 != 4 && (y2 != 2 || y2 != 6)){
			return false;
		}
		if (y2 == 2){
			for (int i = y1 - 3; i <= y1 - 1; i++)
				if (getPiece(x2, i).pieceType != PieceType.f){
					return false;
				}
		}
		if(y2 == 6){
			for (int i = y1 + 1; i <= y1 + 2; i++)
				if (getPiece(x2, i).pieceType != PieceType.f){
					return false;
				}
		}

		return true;
	}

	//	metoda care face rocada
	public void castling(int x1, int y1, int x2, int y2){
		Piece auxKing = new Piece(PieceType.k, getPiece(x1, y1).color);

		setPiece(new Piece(PieceType.f, Color.n), x1, y1);
		// se pune regele pe destinatie
		setPiece(new Piece(PieceType.k, auxKing.color), x2, y2);
		//  se pune tura corespunzator langa rege, in functie de tipul rocadei
		if (y2 == 2) {
			setPiece(new Piece(PieceType.f, Color.n), x2, 0);
			setPiece(new Piece(PieceType.r, auxKing.color), x2, 3);
		} else if (y2 == 6){
			setPiece(new Piece(PieceType.f, Color.n), x2, 7);
			setPiece(new Piece(PieceType.r, auxKing.color), x2, 5);
		}

	}


}