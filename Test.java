import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Test {

	public static Pair minimax(ChessTable table, int player, int depth, ValueTable improve, double alfa, double beta) {
		if(table.ended() != 0 || depth == 0) {
			return new Pair(new Move(player), table.eval(player, improve));
		}

		ArrayList<Move> moves = table.get_moves(player);
		double max = -table.Inf;
		Move bestMove = new Move(player);
		Piece p1;
		Piece p2;

		int go;

		// iteram prin toate mutarile posibile ale playerului
		for(int m = 0; m < moves.size(); m++) {

			// obtinem piesele de pe pozitia initiala si finala din mutarea curenta
			p1 = table.getPiece(moves.get(m).x1, moves.get(m).y1);
			p2 = table.getPiece(moves.get(m).x2, moves.get(m).y2);

			// realizam mutarea
			table.movePiece(moves.get(m).x1, moves.get(m).y1, moves.get(m).x2, moves.get(m).y2);

			// verificam daca mutarea curenta pune regele in sah
			if(table.inDanger(-player)){
				// daca da, aplicam functia de undo si nu mai apelam minimax pentru mutarea curenta
				// deoarece rezultatul nu ar fi cel dorit, scopul fiind sa protejam regele
				table.undoMove(p1, p2, moves.get(m).x1, moves.get(m).y1, moves.get(m).x2, moves.get(m).y2);
				continue;
			}

			// daca mutarea e in regula aplicam minimax pentru oponent
			Pair newPair = minimax(table, -player, depth - 1, improve, -beta, -alfa);

			// verificam ce mutare ar fi mai avantajoasa pentru jucatorul curent
			// si o actualizam daca e cazul
			if(newPair.val >= alfa) {
				alfa = newPair.val;
				bestMove = moves.get(m);
			}

			if(alfa >= beta) {
				break;
			}

			// intoarcem tabla la forma initiala
			// pentru ca in main sa se realizeze doar mutarea returnata, nu toate
			table.undoMove(p1, p2, moves.get(m).x1, moves.get(m).y1, moves.get(m).x2, moves.get(m).y2);
		}

		// returnam perechea formata din cea mai buna mutare si valoarea acesteia
		// daca nu s-a gasit o mutare legala intoarcem Pair((-1,-1), -table.Inf)
		// -table.Inf alterneaza inte Inf si -Inf in functie de player
		// Inf e definit in ChessTable
		return new Pair(bestMove, alfa);
	}


	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		// stringul citit de la stdin
		String myString = scan.nextLine();

		// matricea pentru tabla de sah
		ChessTable table = new ChessTable();

		// instantierea clasei Control pentru a-i putea apela metodele
		Control create = new Control();

		String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h"};

		boolean legalCastling = false;
		boolean pericol;

		Pair newPair;
		boolean first = true;

		// varibila in care retinem culoarea engineului nostru
		Color engineColor = Color.n;

		// variabila in care retinem daca modul force e activat
		boolean force = false;
		// initializam tabela pentru miscari favorabile
		ValueTable improve = new ValueTable();

		// cream matricea
		create.makeTable(table);

		// cat timp nu primim mesajele "quit" sau "resign", citim in continuu de la stdin
		while(!myString.startsWith("quit") && table.ended() == 0) {
			if(myString.equals("new")) {
				// cand primim mesajul "new", readucem matricea la forma initiala
				create.makeTable(table);
				first = true;
				engineColor = Color.n;
				legalCastling = false;
				force = false;



			} else if(myString.startsWith("protover")) {
				// dezactivam semnalul "sigint" pentru a nu mai primi intreruperi
				// dezactivam semnalul "sigterm" (termination signal)
				// activam semnalul "done" (incheie timeout-ul)
				// dezactivam semnalul "colors" (comenzile "white" si "black" devin obsolete)
				System.out.println("feature sigint=0 sigterm=0 done=1 san=0 colors=0");
				System.out.flush();

			} else if(myString.startsWith("force")) {
				// activam modul "force"
				force = true;


			} else if(myString.startsWith("go")) {
				force = false;

				// daca primim semnalul go si nu avem nicio culoare setata pentru engine
				// inseamna ca jucam cu white si o setam
				if(engineColor == Color.n){
					engineColor = Color.w;
				}

				if (engineColor == Color.w) {
					if (first == true){
						System.out.println("move a2a3");
						System.out.flush();
						table.movePiece(6, 0, 5, 0);
					}
				} else if (engineColor == Color.b) {
					if (first == true){
						System.out.println("move a7a6");
						System.out.flush();
						table.movePiece(1, 0, 2, 0);
					}
				}
				first = false;

			} else if(myString.equals("offer draw")) {
				System.out.println("1/2-1/2 {Draw}");
				System.out.flush();
				System.out.println("quit");
				System.out.flush();

				// verificam daca am primit o mutare la input
			} else if(myString.length() > 1 && 0 <= "12345678".indexOf(myString.charAt(1))){
				// daca am primit o mutare si nu e nicio culoare setata
				// inseamna ca engineul nostru joaca cu black
				if(engineColor == Color.n){
					engineColor = Color.b;
				}

				if(myString.charAt(0) == 'e' && (myString.charAt(2) == 'c' || myString.charAt(2) == 'g')) {
					legalCastling = table.checkCastling(8 - (myString.charAt(1) - 48), myString.charAt(0) - 97, 8 - (myString.charAt(3) - 48), myString.charAt(2) - 97);
					if (legalCastling == false) {
					} else {
						table.castling(8 - (myString.charAt(1) - 48), myString.charAt(0) - 97, 8 - (myString.charAt(3) - 48), myString.charAt(2) - 97);
						System.out.println("Am facut rocada");
						System.out.flush();
						legalCastling = false;
					}
				}
				if (legalCastling == false){
					if ((table.getPiece(8 - (myString.charAt(1) - 48), myString.charAt(0) - 97).pieceType == PieceType.p)  //daca vrea sa mute un pion
							&& (table.getPiece(8 - (myString.charAt(3) - 48), myString.charAt(2) - 97).pieceType == PieceType.f) //pe o pozitie libera
							&& ((myString.charAt(0) - 97) != (myString.charAt(2) - 97))){											//in diagonala
						if (table.getPiece(8 - (myString.charAt(1) - 48), myString.charAt(0) - 97).color == Color.b){
							table.setPiece(new Piece(PieceType.f, Color.n), 8 - (myString.charAt(3) - 48) - 1, myString.charAt(2) - 97);
						}
						else {
							table.setPiece(new Piece(PieceType.f, Color.n), 8 - (myString.charAt(3) - 48) + 1, myString.charAt(2) - 97);
						}
					}

					// mutam piesa pe reprezentarea interna a tablei
					table.movePiece(8 - (myString.charAt(1) - 48), myString.charAt(0) - 97, 8 - (myString.charAt(3) - 48), myString.charAt(2) - 97);

					// verificam daca mutarea primita se termina "q"
					// daca da, inseamna ca un pion a ajuns in pozitia in care se schimba in regina
					if (myString.endsWith("q")) {
						if (engineColor == Color.w) {
							table.setPiece(new Piece(PieceType.q, Color.b), 8 - (myString.charAt(3) - 48), myString.charAt(2) - 97);
						}
						else if (engineColor == Color.b){
							table.setPiece(new Piece(PieceType.q, Color.w), 8 - (myString.charAt(3) - 48), myString.charAt(2) - 97);
						}
					}
				}


				int player = 1;

				// setam playerul in funtie de culoarea engineului nostru
				if (engineColor == Color.b) {
					player = -1;
				} else if (engineColor == Color.w) {
					player = 1;
				}

				// daca modul firce e activat
				// citim in continuare de la stdin, nu mai dam nimic la stdout
				if(force == true){
					System.out.flush();
					myString = scan.nextLine();
					continue;
				}

				// verificam daca regele nostru e in sah inainte sa facem mutarea
				pericol = table.inDanger(-player);

				// apelam minimax pentru a obtine cea mai avantajoasa mutare
				newPair = minimax(table, player, 3, improve, -Integer.MAX_VALUE, Integer.MAX_VALUE);
				Move auxMove;
				auxMove = newPair.move;

				// daca mutarea intoarsa e (-1, -1) inseamna ca nu mai avem variante de deplasare
				if (auxMove.x1 != -1 && auxMove.y1 != -1 && auxMove.x2 != -1 && auxMove.y2 != -1) {
					table.movePiece(auxMove.x1, auxMove.y1, auxMove.x2, auxMove.y2);
				}

				// daca regele nostru era in pericol si nu s-a gasit nicio mutare posibila
				// atunci am pierdut si afisam mesajul corespunzator
				// in functie de culoarea engineului nostru
				if (pericol && newPair.val == -table.Inf) {    //daca am pierdut noi
					if(engineColor == Color.b){
						System.out.println("1-0 {Black resigns}");
						System.out.flush();
						System.out.println("quit");
						System.out.flush();
					}
					else {
						System.out.println("0-1 {White resigns}");
						System.out.flush();
						System.out.println("quit");
						System.out.flush();
					}
				}
				// daca regele nu era in pericol si nu s-a gasit nicio mutare posibila
				// atunci e remiza
				else if (newPair.val == -table.Inf) {
					System.out.println("1/2-1/2 {Stalemate}");
					System.out.flush();
					System.out.println("quit");
					System.out.flush();
				}

				// dam la stdout mutarea returnata de minimax
				if (auxMove.piece == PieceType.p && (auxMove.x2 == 0 || auxMove.x2 == 7)) {
					System.out.println("move " + letters[auxMove.y1] + (8 - auxMove.x1) + letters[auxMove.y2] + (8 - auxMove.x2) + "q");
				} else if(auxMove.y1 != -1 && auxMove.y2 != -1){
					System.out.println("move " + letters[auxMove.y1] + (8 - auxMove.x1) + letters[auxMove.y2] + (8 - auxMove.x2));
					System.out.flush();
				} else {
					if(engineColor == Color.b){
						System.out.println("1-0 {Black resigns}");
						System.out.flush();
						System.out.println("quit");
						System.out.flush();
					}
					else {
						System.out.println("0-1 {White resigns}");
						System.out.flush();
						System.out.println("quit");
						System.out.flush();
					}
				}
				if (table.inDanger(player) && newPair.val == table.Inf) {    //daca am castigat noi
					if(engineColor == Color.b){
						System.out.println("1-0 {Black resigns}");
						System.out.flush();
						System.out.println("quit");
						System.out.flush();
					}
					else {
						System.out.println("0-1 {White resigns}");
						System.out.flush();
						System.out.println("quit");
						System.out.flush();
					}

				}
				//create.printTable(table);

				// verificam daca engineul care joaca cu alb a renuntat
			} else if (myString.startsWith("result")) {
				System.out.println("0-1 {White resigns}");
				System.out.flush();
			}

			// cat timp nu primim mesajul "quit" la stdin, citirea (jocul) continua
			if (!myString.startsWith("quit"))
				myString = scan.nextLine();
			else {
				System.out.println(myString);
				System.out.flush();
			}
		}
//
	}
}