package main;

import java.util.ArrayList;

public class Game {		
	Board gameBoard;
	String turn;
	public Game(String sizeInStr) {
		int size = new Integer(sizeInStr).intValue();
		gameBoard = new Board(size);
		this.turn = "White";
	}

	public void printBoard() {
		// TODO Auto-generated method stub
		int size = this.gameBoard.getSize();
		for(int i = size - 1; i > -1; i--) {
			print("" + i);
			for(int j = 0; j < size; j++) {
				print("[");
				String pieceName = null;
				for( int k = 0; k < this.gameBoard.getPieces().size(); k++) {
					Position pos = this.gameBoard.getPieces().get(k).getPosition();
					if(pos.getX() == j && pos.getY() == i) {
						pieceName = this.gameBoard.getPieces().get(k).getName();
					}
				}
				if (pieceName == null){
					pieceName = "  ";
				}
				print(pieceName);
				print("]");
			}
			print("\r\n");
		}
		print("   ");
		for(int i = 0; i < size; i++) {
			print("" + i + "   ");
		}
		print("\r\n\r\n");
	}

	public boolean isIllegalMove(Move currentMove) {
		if(currentMove == null) {
			return true;
		}
		// TODO Auto-generated method stub
		ChessPiece p = null;
		for(int i = 0; i < this.gameBoard.getPieces().size(); i++) {
			ChessPiece iterPiece = this.gameBoard.getPieces().get(i);
			if(iterPiece.getPosition().getX() == currentMove.getX1() && iterPiece.getPosition().getY() == currentMove.getY1()) {
				p = iterPiece;
			}
		}
		if(p == null) {
			return true;
		}else {
			ArrayList<Move> possibleMoves = p.getLegalMoves(this.gameBoard);
			for(int i = 0; i < possibleMoves.size(); i++) {
				if(possibleMoves.get(i).getX2() == currentMove.getX2() && possibleMoves.get(i).getY2() == currentMove.getY2()) {
					return false;
				}
			}
		}
		
		return true;
	}

	public boolean isGameOver() {
		boolean bkExist = false;
		boolean wkExist = false;
		int numPossibleMoves = 0;
		for(int i = 0; i < this.gameBoard.getPieces().size(); i++) {
			ChessPiece p = this.gameBoard.getPieces().get(i);
			if((p.getName() == "WP" && p.getPosition().getY() == this.gameBoard.getSize() - 1 )||(p.getName() == "BP" && p.getPosition().getY() == 0 )) {
				return true;
			}
			if(p.getName() == "WK") {
				wkExist = true;
			}
			if(p.getName() == "BK") {
				bkExist = true;
			}
		}
		if(turn.equals("White")) {
			for(int i = 0; i < this.gameBoard.getPieces().size(); i++) {
				ChessPiece p = this.gameBoard.getPieces().get(i);
				if(p.getName().equals("WP") || p.getName().equals("WK")) {
					numPossibleMoves += p.getLegalMoves(this.gameBoard).size();
				}
			}
		}else {
			for(int i = 0; i < this.gameBoard.getPieces().size(); i++) {
				ChessPiece p = this.gameBoard.getPieces().get(i);
				if(p.getName().equals("BP") || p.getName().equals("BK")) {
					numPossibleMoves += p.getLegalMoves(this.gameBoard).size();
				}
			}
		}
		return !bkExist || !wkExist || numPossibleMoves == 0;
	}

	public void update(Move currentMove, String turn) {
		// TODO Auto-generated method stub
		this.turn = turn;
		this.gameBoard.getMoveToNewBoard(currentMove);
	}
	private void print(String str) {
		System.out.print(str);
	}
	
}
