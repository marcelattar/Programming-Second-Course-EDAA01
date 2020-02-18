import java.awt.Color;
import java.util.*;

import java.lang.NullPointerException;

public class Sudoku {
private Integer[][] sudoku;

	
	/** The constructor. */
	public Sudoku() {
		sudoku = new Integer[9][9];
		
		
		for (int i = 0; i<9; i++) {
			for (int j=0; j<9;j++) {
				sudoku[i][j] = 0; //Sätter alla rutor till 0. 
				
			}
		}
	}
	
	/** Ett test för att visualisera Sudokun. */ 
	public void display() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<9;i++) {
			for (int j = 0; j<9;j++) {
				sb.append(sudoku[i][j] + " ");
			}
			sb.append(System.lineSeparator());
		}
		System.out.print(sb);
		
		
	}
	
	/** Sätter in ett värde i en koordinat.
	 * @param x-värde
	 * @param y-värde
	 * @param värdet 
	 */
	public void put(int x,int y, int value) { // Jag tänker mig att detta ska användas till GUI
		if (x>=0 && x <9 && y>=0 && y<9 && value>= 0 && value <10) {
			sudoku[x][y] = value;
		
		}
	}
	
	/** Returnerar ett värde i en koordinat.
	 * @param x-värde
	 * @param y-värde
	 * @return värdet 
	 */ 
	public int get(int x, int y) {
		if (x>=0 && x <9 && y>=0 && y<9) {
			return sudoku[x][y];
		} else {
			return 0;
		}
	}
	
	/**Returnerar true om regionen INTE innehåller testvalue.
	 * @param x-värde
	 * @param y-värde
	 * @param det värdet som testas
	 * @return true om regionen inte innehåller värdet, annars false.
	 */
	private boolean checkRegion(int x, int y, int testValue) {
		int xtemp = (x/3)*3;
		int ytemp = (y/3)*3;
	
		for (int i = 0; i <3; i++) {
			for (int j = 0; j <3; j++) {
				if (!(i+xtemp == x && j+ytemp == y)) {	 
					if (get(i+xtemp,j+ytemp)==testValue ) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**Returnerar true om ingen colonn eller raden innehåller testValue.
	 * @param x-värde
	 * @param y-värde
	 * @param det värdet som testas
	 * @return true om kolonnen eller raden inte innehåller värdet, annars false.
	 */
	private boolean checkRowsAndCols(int x,int y, int testValue) {
		for (int i = 0; i < 9; i++) {
			if (i != y) {
				if (get(x,i) == testValue) {
					return false;
				}
			}
			if (i !=x) {
				if (get(i,y) == testValue) {
					return false;
				}
			}
		}
		return true;
	}
	
	/** Kollar om de två reglerna för sudoku uppfylls. 
	 * Returnerar true om de gör det.  */
	private boolean check(int x, int y, int testValue) {
		if (checkRegion(x,y,testValue) && checkRowsAndCols(x,y,testValue)) {
			return true;
		}
		else {
			return false;
		}
	}
	/** Löser Sudokun enligt reglerna för spelet.
	 * @return True om det gick att lösa, annars false. */
	public boolean solve() {
		return solve(0,0);
	}
	
	/** Vår rekursiva solvemetod. */ 
	private boolean solve(int x, int y) {
		
		if (x>8) {
			return true;
		}
		
		if (get(x,y) == 0) { //Rutan har INTE ett värde sedan innan (fall 1).
			for(int testValue = 1; testValue<10;testValue++) { //Testar värden från 1-9
				
				if (check(x,y,testValue)) { // Här stämmer alltså testvalue.
					put(x,y, testValue);
					if (y==8) {
						if (x==8) {
							return true;
						}
						if (solve(x+1,0)) {
							return true;
						}
					} else {
						if (solve(x,y+1)) {
							return true;
						}
					}
				}	
			} 
			put(x,y,0);
			return false; // I detta fall har inget värde funkat och vi backtrackar då genom att returnera false
		} 
		else if (get(x,y)!= 0) { // Rutan har ett värde sedan innan (fall 2)
			int testValue = get(x,y); //Här plockar jag ut värdet som redan är insatt och ska nu kontrollera det
			if (check(x,y,testValue)) { 
				if (y==8) {
					return solve(x+1,0);
				} else {
					return solve(x,y+1);
				}
			}
		}
		return false;
	}
	
	/** Rensar Sudokun på dess värden. */
	public void clear() {
		for (int i = 0; i<9; i++) {
			for (int j=0; j<9;j++) {
				sudoku[i][j] = 0; //Sätter alla rutor till 0. 
			}
		}
	}
	/** Byter ut Sudokun.
	 * @param input[][] som är ett ny uppsättning, med värdena instoppade i en matris.
	 */
	public void setBoard(int input[][]) {
		clear();
		for (int row = 0; row < input[0].length; row++) {
			for (int col = 0; col < input.length; col++) {
				sudoku[row][col] = input[row][col];
			}
		}
	}
	
}
