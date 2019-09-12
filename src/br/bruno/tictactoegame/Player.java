package br.bruno.tictactoegame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Player {
	private String playerName;
	private String symbol;
	private BufferedReader inputReader;
	private boolean winner;
	
	public Player(String playerName, String symbol) {
		this.playerName = playerName;
		this.symbol = symbol;
		this.inputReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void play(Board board) {
		List<Integer> positionList = new LinkedList<>();
		if(board.gameMatrixIsEmpty()) {
			positionList = readLineAndColumnInput();
			int j = ((LinkedList<Integer>) positionList).removeLast();
			int i = ((LinkedList<Integer>) positionList).removeLast();
			board.getGameMatrix()[i][j] = symbol;
		} else {
			System.out.println("Now the board looks like this:");
			board.showBoard();
			do {
				positionList = readLineAndColumnInput();
				if(verifyInput(positionList, board)) {
					int j = ((LinkedList<Integer>) positionList).removeLast();
					int i = ((LinkedList<Integer>) positionList).removeLast();
					board.getGameMatrix()[i][j] = symbol;
					checkPlay(i, j, board, symbol);
					break;					
				}
			}while(true);
		}
	}
	
	private void checkPlay(int i, int j, Board board, String symbol) {
		
		switch(i) {
			case 0:
				if(j == 0) {
					if((board.getGameMatrix()[0][1].equals(symbol) && board.getGameMatrix()[0][2].equals(symbol)) ||
							(board.getGameMatrix()[1][0].equals(symbol) && board.getGameMatrix()[2][0].equals(symbol)) ||
							(board.getGameMatrix()[1][1].equals(symbol) && board.getGameMatrix()[2][2].equals(symbol))) {
						this.winner = true;
					}
				} else if(j == 1) {
					if((board.getGameMatrix()[0][0].equals(symbol) && board.getGameMatrix()[0][2].equals(symbol)) ||
							(board.getGameMatrix()[1][1].equals(symbol) && board.getGameMatrix()[2][1].equals(symbol))) {
						this.winner = true;
					}
				} else if(j == 2) {
					if((board.getGameMatrix()[0][0].equals(symbol) && board.getGameMatrix()[0][1].equals(symbol)) ||
							(board.getGameMatrix()[1][2].equals(symbol) && board.getGameMatrix()[2][2].equals(symbol)) ||
							(board.getGameMatrix()[1][1].equals(symbol) && board.getGameMatrix()[2][0].equals(symbol))) {
						this.winner = true;
					}
				}
				break;
				
			case 1:
				if(j == 0) {
					if((board.getGameMatrix()[0][0].equals(symbol) && board.getGameMatrix()[2][0].equals(symbol)) ||
							(board.getGameMatrix()[1][1].equals(symbol) && board.getGameMatrix()[1][2].equals(symbol))) {
						this.winner = true;
					}
				} else if(j == 1) {
					if((board.getGameMatrix()[1][0].equals(symbol) && board.getGameMatrix()[1][2].equals(symbol)) ||
							(board.getGameMatrix()[0][1].equals(symbol) && board.getGameMatrix()[2][1].equals(symbol)) ||
							(board.getGameMatrix()[0][0].equals(symbol) && board.getGameMatrix()[2][2].equals(symbol)) ||
							(board.getGameMatrix()[0][2].equals(symbol) && board.getGameMatrix()[2][0].equals(symbol))) {
						this.winner = true;
					}
				} else if(j == 2) {
					if((board.getGameMatrix()[0][2].equals(symbol) && board.getGameMatrix()[2][2].equals(symbol)) ||
							(board.getGameMatrix()[1][0].equals(symbol) && board.getGameMatrix()[1][1].equals(symbol))) {
						this.winner = true;
					}
				}
				break;
				
			case 2:
				if(j == 0) {
					if((board.getGameMatrix()[0][0].equals(symbol) && board.getGameMatrix()[1][0].equals(symbol)) ||
							(board.getGameMatrix()[2][1].equals(symbol) && board.getGameMatrix()[2][2].equals(symbol)) ||
							(board.getGameMatrix()[1][1].equals(symbol) && board.getGameMatrix()[0][2].equals(symbol))) {
						this.winner = true;
					}
				} else if(j == 1) {
					if((board.getGameMatrix()[2][0].equals(symbol) && board.getGameMatrix()[2][2].equals(symbol)) ||
							(board.getGameMatrix()[0][1].equals(symbol) && board.getGameMatrix()[1][1].equals(symbol))) {
						this.winner = true;
					}
				} else if(j == 2) {
					if((board.getGameMatrix()[2][0].equals(symbol) && board.getGameMatrix()[2][1].equals(symbol)) ||
							(board.getGameMatrix()[0][2].equals(symbol) && board.getGameMatrix()[1][2].equals(symbol)) ||
							(board.getGameMatrix()[0][0].equals(symbol) && board.getGameMatrix()[1][1].equals(symbol))) {
						this.winner = true;
					}
				}
				break;
			
			default:
				break;
		}
	}

	public String getPlayerName() {
		return playerName;
	}
	
	private List<Integer> readLineAndColumnInput() {
		
		List<Integer> positionList = new LinkedList<>();
		int i = -1000;
		int j = -1000;
		
		while(i<0 || i>2 || j<0 || j>2) {
			System.out.println("Enter a valid number for line and column");
			System.out.println("Enter line number (0-2):");
			i = getLineAndColumn();
			
			System.out.println("Enter column number (0-2):");
			j = getLineAndColumn();
		}
		
		positionList.add(i);
		positionList.add(j);
		
		return positionList;
	}
	
	private boolean verifyInput(List<Integer> positionList, Board board) {
		
		int j = ((LinkedList<Integer>) positionList).getLast();
		int i = positionList.get(positionList.size()-2);
		if(! board.getGameMatrix()[i][j].isEmpty()) {
			System.out.println("The position " + i + j
					+ " is already filled. Try another");
			board.showBoard();
			return false;
		}
		return true;
	}
	
	private int getLineAndColumn() {
		String dataInput = null;
		try {
			dataInput = this.inputReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(dataInput);
	}
	
	public boolean isWinner() {
		return this.winner;
	}
	
	public String getSymbol() {
		return symbol;
	}
}
