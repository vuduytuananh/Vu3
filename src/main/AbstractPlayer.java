package main;

public abstract class AbstractPlayer {
	private String name;
	private int boardSize;
	private int timeMax;
	protected Board board;
	protected boolean teamWhite;
	protected ABPruning alg;
	public AbstractPlayer(String name, int boardSize, int timeMax) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.boardSize = boardSize;
		this.timeMax = timeMax;
		this.board = new Board(boardSize);
		this.setTeam();
		alg = new ABPruning(timeMax, this.teamWhite);
	}
	
	public abstract Move getMove();
	protected abstract void setTeam();
	public void update(Move currentMove) {
		this.board.getMoveToNewBoard(currentMove);
	}
}
