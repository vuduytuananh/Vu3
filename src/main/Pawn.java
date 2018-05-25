package main;

import java.util.ArrayList;

public class Pawn extends ChessPiece {

	public Pawn(Position position, String name) {
		super(position, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Move> getLegalMoves(Board boardState) {
		// TODO Auto-generated method stub
		ArrayList<Move> moves = new ArrayList<>();
		Position advance1;
		if(this.getName().equals("WP")) {
			advance1= new Position(this.getPosition().getX(), this.getPosition().getY() + 1);
		}else {
			advance1= new Position(this.getPosition().getX(), this.getPosition().getY() - 1);
		}
		if(!this.standingPiece(advance1, boardState) && advance1.getY() > 0 && advance1.getY() < boardState.getSize() && advance1.getX() > 0 && advance1.getX() < boardState.getSize()) {
			moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), advance1.getX(), advance1.getY()));
		}
		
		Position advance2 = null;
		if(this.getName().equals("WP") && this.getPosition().getY() == 1) {
			advance2= new Position(this.getPosition().getX(), this.getPosition().getY() + 2);
		}
		if(this.getName().equals("BP") && this.getPosition().getY() == boardState.getSize() - 2){
			advance2= new Position(this.getPosition().getX(), this.getPosition().getY() - 2);
		}
		if(advance2 != null  && !this.standingPiece(advance2, boardState) && !this.standingPiece(advance1, boardState)) {
			moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), advance2.getX(), advance2.getY()));	
		}
		
		Position takeLeft;
		if(this.getName().equals("WP")) {
			takeLeft= new Position(this.getPosition().getX() - 1, this.getPosition().getY() + 1);
		}else {
			takeLeft= new Position(this.getPosition().getX() + 1, this.getPosition().getY() - 1);
		}
		if(this.standingEnemy(takeLeft, boardState, this.getName().equals("WP") ? "W" : "B")) {
			moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), takeLeft.getX(), takeLeft.getY()));
		}
		
		Position takeRight;
		if(this.getName().equals("WP")) {
			takeRight= new Position(this.getPosition().getX() + 1, this.getPosition().getY() + 1);
		}else {
			takeRight= new Position(this.getPosition().getX() - 1, this.getPosition().getY() - 1);
		}
		if(this.standingEnemy(takeRight, boardState, this.getName().equals("WP") ? "W" : "B")) {
			moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), takeRight.getX(), takeRight.getY()));
		}
		return moves;
	}
	public boolean standingPiece(Position pos, Board board) {
		for(int i = 0; i < board.getPieces().size(); i++) {
			ChessPiece p = board.getPieces().get(i);
			if(p.getPosition().getX() == pos.getX() && p.getPosition().getY() == pos.getY()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean standingEnemy(Position pos, Board board, String team) {
		for(int i = 0; i < board.getPieces().size(); i++) {
			ChessPiece p = board.getPieces().get(i);
			if(team == "W") {
				if((p.getName().equals("BP") || p.getName().equals("BK")) && p.getPosition().getX() == pos.getX() && p.getPosition().getY() == pos.getY()) {
					return true;
				}
			}else {
				if((p.getName().equals("WP") || p.getName().equals("WK")) && p.getPosition().getX() == pos.getX() && p.getPosition().getY() == pos.getY()) {
					return true;
				}
			}
		}
		return false;
	}
}
