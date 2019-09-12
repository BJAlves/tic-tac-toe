package br.bruno.tictactoegame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Match {
	private Player playerOne;
	private Player playerTwo;
	private Board board;
	
	public Match() {
		System.out.println("Type name of first player:");
		String playerNameOne = getDataFromUser();
		playerOne = new Player(playerNameOne, "X");
		
		System.out.println("Type name of second player:");
		String playerNameTwo = getDataFromUser();
		playerTwo = new Player(playerNameTwo, "O");
		
		board = new Board();
	}
	
	private static String getDataFromUser() {
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		try {
			input = inputReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	private void directTheGame(Player actualPlayer, Player nextPlayer) {
		if((! actualPlayer.isWinner()) && (! this.board.gameMatrixIsFilled())) {
			actualPlayer = nextPlayer;
			passTheTurn(actualPlayer);
		} else if((! actualPlayer.isWinner()) && (this.board.gameMatrixIsFilled())) {
			this.board.showBoard();
			showDrawMessage();
		} else {
			this.board.showBoard();
			showWinnerMessage(actualPlayer.getPlayerName());
		}
	}
	
	private void passTheTurn(Player actualPlayer) {
		System.out.println("---------------------------------");
		System.out.println(actualPlayer.getPlayerName() + "'s turn:");
		actualPlayer.play(this.board);
		switch(actualPlayer.getSymbol()) {
			case "X":
				directTheGame(actualPlayer, playerTwo);
				break;
			case "O":
				directTheGame(actualPlayer, playerOne);
				break;
			default:
				break;
		}		
	}
	
	public Player getPlayerOne() {
		return playerOne;
	}
	
	public Player getPlayerTwo() {
		return playerTwo;		
	}
	
	public Board getBoard() {
		return board;
	}
	
	private void showWinnerMessage(String playerName) {
		System.out.println(playerName + " is the winner!");
	}
	
	private void showDrawMessage() {
		System.out.println("We tied!");
	}
	
	private static void initMatch() {
		Match match = new Match();
		match.passTheTurn(match.getPlayerOne());
	}
	
	private static String validateAnswer(String answer) {
		while((! answer.equals("y")) && (! answer.equals("n"))) {
			System.out.println("Invalid answer. Please, type y or n");
			answer = getDataFromUser();
		}
		return answer;
	}
	
	public static void main(String[] args) {
		String userChoice = "";
		
		do {
			initMatch();
			System.out.println("Want to play another match? y/n?");
			userChoice = validateAnswer(getDataFromUser());
		}while(userChoice.equals("y"));
	}
}
