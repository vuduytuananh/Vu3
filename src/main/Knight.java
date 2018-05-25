package main;

import java.util.ArrayList;

public class Knight extends ChessPiece {

	public Knight(Position position, String name) {
		super(position, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Move> getLegalMoves(Board boardState) {
		// TODO Auto-generated method stub
		ArrayList<Move> moves = new ArrayList<>();
		Position ne;
		if(this.getName().equals("WK")) {
			ne = new Position(this.getPosition().getX() + 1, this.getPosition().getY() + 2);
			if(isValidPos(ne, boardState, "W")) {
				moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), ne.getX(), ne.getY()));
			}
		}else {
			ne = new Position(this.getPosition().getX() - 1, this.getPosition().getY() - 2);
			if(isValidPos(ne, boardState, "B")) {
				moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), ne.getX(), ne.getY()));
			}
		}
		Position nw;
		if(this.getName().equals("WK")) {
			nw = new Position(this.getPosition().getX() - 1, this.getPosition().getY() + 2);
			if(isValidPos(nw, boardState, "W")) {
				moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), nw.getX(), nw.getY()));
			}
		}else {
			nw = new Position(this.getPosition().getX() + 1, this.getPosition().getY() - 2);
			if(isValidPos(nw, boardState, "B")) {
				moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), nw.getX(), nw.getY()));
			}
		}
		Position en;
		if(this.getName().equals("WK")) {
			en = new Position(this.getPosition().getX() + 2, this.getPosition().getY() + 1);
			if(isValidPos(en, boardState, "W")) {
				moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), en.getX(), en.getY()));
			}
		}else {
			en = new Position(this.getPosition().getX() - 2, this.getPosition().getY() - 1);
			if(isValidPos(en, boardState, "B")) {
				moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), en.getX(), en.getY()));
			}
		}
		Position es;
		if(this.getName().equals("WK")) {
			es = new Position(this.getPosition().getX() + 2, this.getPosition().getY() - 1);
			if(isValidPos(es, boardState, "W")) {
				moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), es.getX(), es.getY()));
			}
		}else {
			es = new Position(this.getPosition().getX() - 2, this.getPosition().getY() + 1);
			if(isValidPos(es, boardState, "B")) {
				moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), es.getX(), es.getY()));
			}
		}
		Position se;
		if(this.getName().equals("WK")) {
			se = new Position(this.getPosition().getX() + 1, this.getPosition().getY() - 2);
			if(isValidPos(se, boardState, "W")) {
				moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), se.getX(), se.getY()));
			}
		}else {
			se = new Position(this.getPosition().getX() - 1, this.getPosition().getY() + 2);
			if(isValidPos(se, boardState, "B")) {
				moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), se.getX(), se.getY()));
			}
		}
		Position sw;
		if(this.getName().equals("WK")) {
			sw = new Position(this.getPosition().getX() - 1, this.getPosition().getY() - 2);
			if(isValidPos(sw, boardState, "W")) {
				moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), sw.getX(), sw.getY()));
			}
		}else {
			sw = new Position(this.getPosition().getX() + 1, this.getPosition().getY() + 2);
			if(isValidPos(sw, boardState, "B")) {
				moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), sw.getX(), sw.getY()));
			}
		}
		Position wn;
		if(this.getName().equals("WK")) {
			wn = new Position(this.getPosition().getX() - 2, this.getPosition().getY() + 1);
			if(isValidPos(wn, boardState, "W")) {
				moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), wn.getX(), wn.getY()));
			}
		}else {
			wn = new Position(this.getPosition().getX() + 2, this.getPosition().getY() - 1);
			if(isValidPos(wn, boardState, "B")) {
				moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), wn.getX(), wn.getY()));
			}
		}
		Position ws;
		if(this.getName().equals("WK")) {
			ws = new Position(this.getPosition().getX() - 2, this.getPosition().getY() - 1);
			if(isValidPos(ws, boardState, "W")) {
				moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), ws.getX(), ws.getY()));
			}
		}else {
			ws = new Position(this.getPosition().getX() + 2, this.getPosition().getY() + 1);
			if(isValidPos(ws, boardState, "B")) {
				moves.add(new Move(this.getPosition().getX(), this.getPosition().getY(), ws.getX(), ws.getY()));
			}
		}
		return moves;
	}
	private boolean isValidPos(Position pos, Board board, String team) {
		return (!this.standingAlly(pos, board, team)) && pos.getX() > 0 && pos.getY() > 0 && pos.getX() < board.getSize() && pos.getY() < board.getSize();
	}
	private boolean standingAlly(Position pos, Board board, String team) {
		for(int i = 0; i < board.getPieces().size(); i++) {
			ChessPiece p = board.getPieces().get(i);
			if(team == "W") {
				if((p.getName().equals("WP") || p.getName().equals("WK")) && p.getPosition().getX() == pos.getX() && p.getPosition().getY() == pos.getY()) {
					return true;
				}
			}else {
				if((p.getName().equals("BP") || p.getName().equals("BK")) && p.getPosition().getX() == pos.getX() && p.getPosition().getY() == pos.getY()) {
					return true;
				}
			}
		}
		return false;
	}
}
