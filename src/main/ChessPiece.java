package main;

import java.util.ArrayList;

public abstract class ChessPiece implements Cloneable{
	private Position position;
	private String name;
	protected ChessPiece(Position position, String name) {
		this.position = position;
		this.name = name;
	}
	public Position getPosition() {
		return this.position;
	}
	public void setPosition(Position pos) {
		this.position = pos;
	}
	public String getName() {
		return this.name;
	}
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public abstract ArrayList<Move> getLegalMoves(Board boardState);
}
