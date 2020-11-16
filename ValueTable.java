import java.util.ArrayList;
import java.util.Collections;

// tabela cu valori specifice pentru fiecare pozitie in care se afla o piesa pe tabla,
//conferindu-i un avantaj sau dezavantaj
public class ValueTable {
	ArrayList<ArrayList<Double>> kingValue = new ArrayList<>();
	ArrayList<ArrayList<Double>> queenValue = new ArrayList<>();
	ArrayList<ArrayList<Double>> rookValue = new ArrayList<>();
	ArrayList<ArrayList<Double>> bishopValue = new ArrayList<>();
	ArrayList<ArrayList<Double>> knightValue = new ArrayList<>();
	ArrayList<ArrayList<Double>> pawnValue = new ArrayList<>();

	public ValueTable() {
		ArrayList<Double> lin1 = new ArrayList<Double>();
		lin1.add(-3.0);
		lin1.add(-4.0);
		lin1.add(-4.0);
		lin1.add(-5.0);
		lin1.add(-5.0);
		lin1.add(-4.0);
		lin1.add(-4.0);
		lin1.add(-3.0);
		kingValue.add(lin1);
		kingValue.add(lin1);
		kingValue.add(lin1);
		kingValue.add(lin1);

		ArrayList<Double> lin2 = new ArrayList<Double>();
		lin2.add(-2.0);
		lin2.add(-3.0);
		lin2.add(-3.0);
		lin2.add(-4.0);
		lin2.add(-4.0);
		lin2.add(-3.0);
		lin2.add(-3.0);
		lin2.add(-2.0);
		kingValue.add(lin2);

		ArrayList<Double> lin3 = new ArrayList<Double>();
		lin3.add(-1.0);
		lin3.add(-2.0);
		lin3.add(-2.0);
		lin3.add(-2.0);
		lin3.add(-2.0);
		lin3.add(-2.0);
		lin3.add(-2.0);
		lin3.add(-1.0);
		kingValue.add(lin3);

		ArrayList<Double> lin4 = new ArrayList<Double>();
		lin4.add(2.0);
		lin4.add(2.0);
		lin4.add(0.0);
		lin4.add(0.0);
		lin4.add(0.0);
		lin4.add(0.0);
		lin4.add(2.0);
		lin4.add(2.0);
		kingValue.add(lin4);

		ArrayList<Double> lin5 = new ArrayList<Double>();
		lin5.add(2.0);
		lin5.add(3.0);
		lin5.add(1.0);
		lin5.add(0.0);
		lin5.add(0.0);
		lin5.add(1.0);
		lin5.add(3.0);
		lin5.add(2.0);
		kingValue.add(lin5);

		//queen

		ArrayList<Double> lin6 = new ArrayList<Double>();
		lin6.add(-2.0);
		lin6.add(-1.0);
		lin6.add(-1.0);
		lin6.add(-0.5);
		lin6.add(-0.5);
		lin6.add(-1.0);
		lin6.add(-1.0);
		lin6.add(-2.0);
		queenValue.add(lin6);

		ArrayList<Double> lin7 = new ArrayList<Double>();
		lin7.add(-1.0);
		lin7.add(0.0);
		lin7.add(0.0);
		lin7.add(0.0);
		lin7.add(0.0);
		lin7.add(0.0);
		lin7.add(0.0);
		lin7.add(-1.0);
		queenValue.add(lin7);

		ArrayList<Double> lin8 = new ArrayList<Double>();
		lin8.add(-1.0);
		lin8.add(0.0);
		lin8.add(0.5);
		lin8.add(0.5);
		lin8.add(0.5);
		lin8.add(0.5);
		lin8.add(0.0);
		lin8.add(-1.0);
		queenValue.add(lin8);

		ArrayList<Double> lin9 = new ArrayList<Double>();
		lin9.add(-0.5);
		lin9.add(0.0);
		lin9.add(0.5);
		lin9.add(0.5);
		lin9.add(0.5);
		lin9.add(0.5);
		lin9.add(0.0);
		lin9.add(-0.5);
		queenValue.add(lin9);

		ArrayList<Double> lin10 = new ArrayList<Double>();
		lin10.add(0.0);
		lin10.add(0.0);
		lin10.add(0.5);
		lin10.add(0.5);
		lin10.add(0.5);
		lin10.add(0.5);
		lin10.add(0.0);
		lin10.add(-0.5);
		queenValue.add(lin10);

		ArrayList<Double> lin11 = new ArrayList<Double>();
		lin11.add(-1.0);
		lin11.add(0.5);
		lin11.add(0.5);
		lin11.add(0.5);
		lin11.add(0.5);
		lin11.add(0.5);
		lin11.add(0.0);
		lin11.add(-1.0);
		queenValue.add(lin11);

		ArrayList<Double> lin12 = new ArrayList<Double>();
		lin12.add(-1.0);
		lin12.add(0.0);
		lin12.add(0.5);
		lin12.add(0.0);
		lin12.add(0.0);
		lin12.add(0.0);
		lin12.add(0.0);
		lin12.add(-1.0);
		queenValue.add(lin12);

		ArrayList<Double> lin13 = new ArrayList<Double>();
		lin13.add(-2.0);
		lin13.add(-1.0);
		lin13.add(-1.0);
		lin13.add(-0.5);
		lin13.add(-0.5);
		lin13.add(-1.0);
		lin13.add(-1.0);
		lin13.add(-2.0);
		queenValue.add(lin13);

		//rook
		ArrayList<Double> lin14 = new ArrayList<Double>();
		lin14.add(0.0);
		lin14.add(0.0);
		lin14.add(0.5);
		lin14.add(0.0);
		lin14.add(0.0);
		lin14.add(0.0);
		lin14.add(0.0);
		lin14.add(0.0);
		rookValue.add(lin14);

		ArrayList<Double> lin15 = new ArrayList<Double>();
		lin15.add(0.5);
		lin15.add(1.0);
		lin15.add(1.0);
		lin15.add(1.0);
		lin15.add(1.0);
		lin15.add(1.0);
		lin15.add(1.0);
		lin15.add(0.5);
		rookValue.add(lin15);

		ArrayList<Double> lin16 = new ArrayList<Double>();
		lin16.add(-0.5);
		lin16.add(0.0);
		lin16.add(0.0);
		lin16.add(0.0);
		lin16.add(0.0);
		lin16.add(0.0);
		lin16.add(0.0);
		lin16.add(-0.5);
		rookValue.add(lin16);

		rookValue.add(lin16);

		rookValue.add(lin16);

		rookValue.add(lin16);

		rookValue.add(lin16);

		ArrayList<Double> lin24 = new ArrayList<Double>();
		lin24.add(0.0);
		lin24.add(0.0);
		lin24.add(0.0);
		lin24.add(0.5);
		lin24.add(0.5);
		lin24.add(0.0);
		lin24.add(0.0);
		lin24.add(0.0);
		rookValue.add(lin24);

		//bishop

		ArrayList<Double> lin25 = new ArrayList<Double>();
		lin25.add(-2.0);
		lin25.add(-1.0);
		lin25.add(-1.0);
		lin25.add(-1.0);
		lin25.add(-1.0);
		lin25.add(-1.0);
		lin25.add(-1.0);
		lin25.add(-2.0);
		bishopValue.add(lin25);

		ArrayList<Double> lin26 = new ArrayList<Double>();
		lin26.add(-1.0);
		lin26.add(0.0);
		lin26.add(0.0);
		lin26.add(0.0);
		lin26.add(0.0);
		lin26.add(0.0);
		lin26.add(0.0);
		lin26.add(-1.0);
		bishopValue.add(lin26);

		ArrayList<Double> lin27 = new ArrayList<Double>();
		lin27.add(-1.0);
		lin27.add(0.0);
		lin27.add(0.5);
		lin27.add(1.0);
		lin27.add(1.0);
		lin27.add(0.5);
		lin27.add(0.0);
		lin27.add(-1.0);
		bishopValue.add(lin27);

		ArrayList<Double> lin28 = new ArrayList<Double>();
		lin28.add(-1.0);
		lin28.add(0.5);
		lin28.add(0.5);
		lin28.add(1.0);
		lin28.add(1.0);
		lin28.add(0.5);
		lin28.add(0.5);
		lin28.add(-1.0);
		bishopValue.add(lin28);

		ArrayList<Double> lin29 = new ArrayList<Double>();
		lin29.add(-1.0);
		lin29.add(0.0);
		lin29.add(1.0);
		lin29.add(1.0);
		lin29.add(1.0);
		lin29.add(1.0);
		lin29.add(0.0);
		lin29.add(-1.0);
		bishopValue.add(lin29);

		ArrayList<Double> lin30 = new ArrayList<Double>();
		lin30.add(-1.0);
		lin30.add(1.0);
		lin30.add(1.0);
		lin30.add(1.0);
		lin30.add(1.0);
		lin30.add(1.0);
		lin30.add(1.0);
		lin30.add(-1.0);
		bishopValue.add(lin30);

		ArrayList<Double> lin31 = new ArrayList<Double>();
		lin31.add(-1.0);
		lin31.add(0.5);
		lin31.add(0.0);
		lin31.add(0.0);
		lin31.add(0.0);
		lin31.add(0.0);
		lin31.add(0.5);
		lin31.add(-1.0);
		bishopValue.add(lin31);

		bishopValue.add(lin25);

		//knight

		ArrayList<Double> lin33 = new ArrayList<Double>();
		lin33.add(-5.0);
		lin33.add(-4.0);
		lin33.add(-3.0);
		lin33.add(-3.0);
		lin33.add(-3.0);
		lin33.add(-3.0);
		lin33.add(-4.0);
		lin33.add(-5.0);
		knightValue.add(lin33);

		ArrayList<Double> lin34 = new ArrayList<Double>();
		lin34.add(-4.0);
		lin34.add(-2.0);
		lin34.add(0.0);
		lin34.add(0.0);
		lin34.add(0.0);
		lin34.add(0.0);
		lin34.add(-2.0);
		lin34.add(-4.0);
		knightValue.add(lin34);

		ArrayList<Double> lin35 = new ArrayList<Double>();
		lin35.add(-3.0);
		lin35.add(0.0);
		lin35.add(1.0);
		lin35.add(1.5);
		lin35.add(1.5);
		lin35.add(1.0);
		lin35.add(0.0);
		lin35.add(-3.0);
		knightValue.add(lin35);

		ArrayList<Double> lin36 = new ArrayList<Double>();
		lin36.add(-3.0);
		lin36.add(0.5);
		lin36.add(1.5);
		lin36.add(2.0);
		lin36.add(2.0);
		lin36.add(1.5);
		lin36.add(0.5);
		lin36.add(-3.0);
		knightValue.add(lin36);

		ArrayList<Double> lin37 = new ArrayList<Double>();
		lin37.add(-3.0);
		lin37.add(0.0);
		lin37.add(1.5);
		lin37.add(2.0);
		lin37.add(2.0);
		lin37.add(1.5);
		lin37.add(0.0);
		lin37.add(-3.0);
		knightValue.add(lin37);

		ArrayList<Double> lin38 = new ArrayList<Double>();
		lin38.add(-3.0);
		lin38.add(0.5);
		lin38.add(1.0);
		lin38.add(1.5);
		lin38.add(1.5);
		lin38.add(1.0);
		lin38.add(0.5);
		lin38.add(-3.0);
		knightValue.add(lin38);

		ArrayList<Double> lin39 = new ArrayList<Double>();
		lin39.add(-4.0);
		lin39.add(-2.0);
		lin39.add(0.0);
		lin39.add(0.5);
		lin39.add(0.5);
		lin39.add(0.0);
		lin39.add(-2.0);
		lin39.add(-4.0);
		knightValue.add(lin39);

		knightValue.add(lin33);

		//pawn
		ArrayList<Double> lin40 = new ArrayList<Double>();
		lin40.add(0.0);
		lin40.add(0.0);
		lin40.add(0.0);
		lin40.add(0.0);
		lin40.add(0.0);
		lin40.add(0.0);
		lin40.add(0.0);
		lin40.add(0.0);
		pawnValue.add(lin40);

		ArrayList<Double> lin41 = new ArrayList<Double>();
		lin41.add(5.0);
		lin41.add(5.0);
		lin41.add(5.0);
		lin41.add(5.0);
		lin41.add(5.0);
		lin41.add(5.0);
		lin41.add(5.0);
		lin41.add(5.0);
		pawnValue.add(lin41);

		ArrayList<Double> lin42 = new ArrayList<Double>();
		lin42.add(1.0);
		lin42.add(1.0);
		lin42.add(2.0);
		lin42.add(3.0);
		lin42.add(3.0);
		lin42.add(2.0);
		lin42.add(1.0);
		lin42.add(1.0);
		pawnValue.add(lin42);

		ArrayList<Double> lin43 = new ArrayList<Double>();
		lin43.add(1.0);
		lin43.add(1.0);
		lin43.add(2.0);
		lin43.add(3.0);
		lin43.add(3.0);
		lin43.add(2.0);
		lin43.add(1.0);
		lin43.add(1.0);
		pawnValue.add(lin43);

		ArrayList<Double> lin44 = new ArrayList<Double>();
		lin44.add(0.5);
		lin44.add(0.5);
		lin44.add(1.0);
		lin44.add(2.5);
		lin44.add(2.5);
		lin44.add(1.0);
		lin44.add(0.5);
		lin44.add(0.5);
		pawnValue.add(lin44);

		ArrayList<Double> lin45 = new ArrayList<Double>();
		lin45.add(0.0);
		lin45.add(0.0);
		lin45.add(0.0);
		lin45.add(2.0);
		lin45.add(2.0);
		lin45.add(0.0);
		lin45.add(0.0);
		lin45.add(0.0);
		pawnValue.add(lin45);

		ArrayList<Double> lin46 = new ArrayList<Double>();
		lin46.add(0.5);
		lin46.add(-0.5);
		lin46.add(-1.0);
		lin46.add(0.0);
		lin46.add(0.0);
		lin46.add(-1.0);
		lin46.add(-0.5);
		lin46.add(0.5);
		pawnValue.add(lin46);

		ArrayList<Double> lin47 = new ArrayList<Double>();
		lin47.add(0.5);
		lin47.add(1.0);
		lin47.add(1.0);
		lin47.add(-2.0);
		lin47.add(-2.0);
		lin47.add(1.0);
		lin47.add(1.0);
		lin47.add(0.5);
		pawnValue.add(lin47);

		pawnValue.add(lin40);

	}

	public ArrayList<ArrayList<Double>> get(PieceType piece) {
		if (piece == PieceType.k)
			return kingValue;
		if (piece == PieceType.q)
			return queenValue;
		if (piece == PieceType.r)
			return rookValue;
		if (piece == PieceType.b)
			return bishopValue;
		if (piece == PieceType.n)
			return knightValue;
		if (piece == PieceType.p)
			return pawnValue;
		return null;
	}

//	public static void main(String[] args){
//		ValueTable tabla = new ValueTable();
//		for(ArrayList<Double> i: tabla.get(PieceType.k)){
//			System.out.println(i);
//		}
//	}
}