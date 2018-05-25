package main;
import java.io.*;
import java.util.*;
/*Driver program for 2-person game containing black and white player. Run time arguments to the program are 5 in number and they are:
size of the board, 
-h or -c for first player (-h indicates human player and -c indicates computer program as the player), 
name of first player, 
-h or -c for second player, 
name of second player.
*/
public 
class Driver{
	static Scanner sc= new Scanner(System.in);
	/* 
	the following line 
	represents 5 seconds per move. This time variable is not used in the code, except for extra credit. It can be
	used by players to determine the depth of the tree they wish to explore. */
	public static void main(String[] args) {
		final int MAX_TIME_PER_MOVE = 5;       
		Game gameBoard = new Game(args[0]);
		BlackPlayer blackPlayer= null;
		WhitePlayer whitePlayer=null;
		int boardSize = new Integer(args[0]).intValue(); // get from compile time
		if (args[1].equals("-c")) {
			blackPlayer= new BlackPlayer(args[2], boardSize, MAX_TIME_PER_MOVE);
		}
		if (args[3].equals("-c")) {
			whitePlayer = new WhitePlayer(args[4],boardSize, MAX_TIME_PER_MOVE); 
		}
		Move currentMove = null;
		boolean done = false;
		String turn = "white";
		gameBoard.printBoard();
		int time_for_last_move = 4;
		while (!done){
			if (turn.equals("black")) {
				if (blackPlayer != null) {
//					System.out.println("Mark");
					currentMove = blackPlayer.getMove();
				}else {
					currentMove = getHumanMove(args[2]);
				}
			}else {
				if (whitePlayer != null) {         
					currentMove = whitePlayer.getMove();
				}else {
					currentMove = getHumanMove(args[4]);
				}
			}
//			System.out.println(turn + " X1: " +currentMove.getX1()+ " Y1: " +currentMove.getY1()+ " X2: " +currentMove.getX2()+" Y2: " + currentMove.getY2());
			if (gameBoard.isIllegalMove(currentMove)|| (gameBoard.isGameOver()) || time_for_last_move > MAX_TIME_PER_MOVE){
				done = true;
				String winner = "";
				// FILL CODE HERE TO PRINT WHO MADE ILLEGAL MOVE AND WHY OR IF THE GAME IS 
				// OVER WHO THE WINNER IS OR IF TIME IS EXCEEDED EXPLAIN WHO THE WINNR IS
				if(gameBoard.isGameOver()) {
					winner = turn;
				}
				if(gameBoard.isIllegalMove(currentMove) || time_for_last_move > MAX_TIME_PER_MOVE) {
					winner = turn.equals("black") ? "white" : "black";
				}
				System.out.print("GAME OVER\r\nWinner: " + winner + "\n\r");
//				gameBoard.printBoard();	
			}else{
				gameBoard.update(currentMove, turn);
				gameBoard.printBoard();
				sc.nextLine();
				if (turn.equals("black")){
					whitePlayer.update(currentMove);
					turn = "white";
				}else{
					blackPlayer.update(currentMove);
					turn = "black";
				}
			}
		} // end while
	}// end main

	public static Move getHumanMove(String name){
		System.out.println("Hello  " + name + "\n provide x1 y1 x2 y2 for choosing (x1,y1) and (x2,y2)");
		int x1 = sc.nextInt();
		int y1 = sc.nextInt();
		int x2 = sc.nextInt();
		int y2 = sc.nextInt();
		Move m = new Move(x1,y1,x2,y2);
		return m;
	}     
}// end class
