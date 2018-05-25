package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {
	private ArrayList<ChessPiece> pieces;
	private int size;
	public Board(int size) {
		// TODO Auto-generated constructor stub
		this.size = size;
		pieces = new ArrayList<>();
		// add white pieces
		for(int i = 0; i < size; i++) {
			pieces.add(new Pawn(new Position(i, size - 2), "BP"));
		}
		pieces.add(new Knight(new Position((int) (size/4) - 1, size - 1), "BK"));
		
		// add black pieces
		for(int i = 0; i < size; i++) {
			pieces.add(new Pawn(new Position(i, 1), "WP"));
		}
		pieces.add(new Knight(new Position(size - (int) (size/4), 0), "WK"));
	}
	public int getSize() {
		return this.size;
	}
	public ArrayList<ChessPiece> getPieces(){
		return this.pieces;
	}
	public void setPieces(ArrayList<ChessPiece> pieces){
		this.pieces = pieces;
	}
	public boolean isTerminalBoard() {
		boolean bkExist = false;
		boolean wkExist = false;
		for(int i = 0; i < this.getPieces().size(); i++) {
			ChessPiece p = this.getPieces().get(i);
			if((p.getName() == "WP" && p.getPosition().getY() == this.getSize() - 1 )||(p.getName() == "BP" && p.getPosition().getY() == 0 )) {
				return true;
			}
			if(p.getName() == "WK") {
				wkExist = true;
			}
			if(p.getName() == "BK") {
				bkExist = true;
			}
		}
		return !bkExist || !wkExist;
	}
	public HashMap<Move,Board> getPossibleBoards(String knightName, String pawnName){
		HashMap<Move,Board> possibleBoards = new HashMap<>();
		for(int i = 0 ; i < this.getPieces().size(); i++) {
			ChessPiece p = this.getPieces().get(i);
			if((!p.getName().equals(knightName)) && (!p.getName().equals(pawnName))) {
//				System.out.println("Mark: " + p.getName());
				continue;
			}
			for(int j = 0; j < p.getLegalMoves(this).size(); j++) {
				Board newBoard = new Board(this.getSize());
				ArrayList<ChessPiece> newPieces = new ArrayList<>();
				for (int k = 0; k < this.getPieces().size(); k++) {
					newPieces.add((ChessPiece) this.getPieces().get(k).clone());
				}
				newPieces.remove(i);
				ChessPiece newPiece = (ChessPiece) p.clone();
				newPiece.setPosition(new Position(p.getLegalMoves(this).get(j).getX2(), p.getLegalMoves(this).get(j).getY2()));
				newPieces.add(newPiece);
				newBoard.setPieces(newPieces);
				possibleBoards.put(p.getLegalMoves(this).get(j), newBoard);
			}
		}
		return possibleBoards;
	}
	public void getMoveToNewBoard(Move move) {
		if (move == null) {
			return;
		}
		ChessPiece p = null;
		int removeIdx = -1;
		for(int i = 0; i < this.getPieces().size(); i++) {
			ChessPiece iterPiece = this.getPieces().get(i);
//			System.out.println(move);
			if(iterPiece.getPosition().getX() == move.getX1() && iterPiece.getPosition().getY() == move.getY1()) {
				p = iterPiece;
			}
		}
		for(int i = 0; i < this.getPieces().size(); i++) {
			ChessPiece iterPiece = this.getPieces().get(i);
			if(iterPiece.getPosition().getX() == move.getX2() && iterPiece.getPosition().getY() == move.getY2()) {
				removeIdx = i;
			}
		}
		if (removeIdx != -1) {
			this.getPieces().remove(removeIdx);
		}
		p.setPosition(new Position(move.getX2(), move.getY2()));
	}
}
