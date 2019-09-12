package br.bruno.tictactoegame;
public class Board {
	private String [][] gameMatrix;
	
	public Board() {
		this.gameMatrix = new String[3][3];
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				this.gameMatrix[i][j] = "";
			}
		}
	}
	
	public String[][] getGameMatrix() {
		return this.gameMatrix;
	}
	
	public boolean gameMatrixIsEmpty() {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(! this.gameMatrix[i][j].isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void showBoard() {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(gameMatrix[i][j].isEmpty()) {
					System.out.print(" " + " ");
				} else {
					System.out.print(gameMatrix[i][j] + " ");
				}
			}
			System.out.println("");
		}
	}
	
	public boolean gameMatrixIsFilled() {
		int countPositionsFilled = 0;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(! gameMatrix[i][j].isEmpty()) {
					countPositionsFilled++;
				}
			}
		}
		return countPositionsFilled == 9 ? true : false;
	}
}
