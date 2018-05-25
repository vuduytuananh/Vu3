package main;

import java.util.HashMap;

import main.ABPruning.Result;

public class WhitePlayer extends AbstractPlayer {

	public WhitePlayer(String arg, int boardSize, int timeMax) {
		super(arg, boardSize, timeMax);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Move getMove() {
		Move theMove = null;
		double maxScore = Double.MIN_VALUE;
		HashMap<Move, Board> possibleMoves = this.board.getPossibleBoards("WK", "WP");
//		System.out.println(possibleMoves);
		for(Move m : possibleMoves.keySet()) {
//			Move m = possibleMoves.keySet().
			Board b = possibleMoves.get(m);
//			System.out.println(m);
			Result result = this.alg.getResult(b);
			if(result.score > maxScore) {
				maxScore = result.score;
				theMove = m;
			}
		}
//		Update
		
		this.update(theMove);
		return theMove;
	}

	@Override
	protected void setTeam() {
		// TODO Auto-generated method stub
		this.teamWhite = true;
	}
}
