package main;

import java.util.HashMap;

import main.ABPruning.Result;

public class BlackPlayer extends AbstractPlayer {
	
	public BlackPlayer(String arg, int boardSize, int timeMax) {
		super(arg, boardSize, timeMax);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Move getMove() {
		Move theMove = null;
		double minScore = Double.MAX_VALUE;
		HashMap<Move, Board> possibleMoves = this.board.getPossibleBoards("BK", "BP");
		for(Move m : possibleMoves.keySet()) {
			Board b = possibleMoves.get(m);
			Result result = this.alg.getResult(b);
			if(result.score < minScore) {
				minScore = result.score;
				theMove = m;
			}
//			System.out.println("X1: " +m.getX1()+ " Y1: " +m.getY1()+ " X2: " +m.getX2()+" Y2: " + m.getY2());

		}
//		Update
		this.update(theMove);
		return theMove;
	}

	@Override
	protected void setTeam() {
		// TODO Auto-generated method stub
		this.teamWhite = false;
	}

}
