package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ABPruning {
	private long miliSecs;
	private final int MAX_DEPTH = 4;
	private boolean whiteTeam;
	static class Result {
		public Board board;
		public double score;
		public long timeTaken;
		public Result(Board board, double score, long timeTaken) {
			this.board = board;
			this.score = score;
			this.timeTaken = timeTaken;
		}
	}
	public ABPruning(int sec, boolean whiteTeam) {
		// TODO Auto-generated constructor stub
		this.miliSecs = sec * 1000 - 150;
		this.whiteTeam = whiteTeam;
	}
	public Result getResult(Board board) {
		long startTime = System.currentTimeMillis();
		return this.whiteTeam ? minRound(board, 0, Double.MIN_VALUE, Double.MAX_VALUE, startTime) : maxRound(board, 0, Double.MIN_VALUE, Double.MAX_VALUE, startTime);
	}
	private Result maxRound(Board board, int depth, double alpha, double beta, long startTime) {
//		System.out.println(depth);
		if(this.isTerminalState(board, depth, startTime)){
			return new Result(board, this.getTerminalScore(board, "W"), System.currentTimeMillis() - startTime);
		}
		double max = Double.MIN_VALUE;
		Collection<Board> possibleBoards = board.getPossibleBoards("WK", "WP").values();
		ArrayList<Board> boardList = new ArrayList<>();
		boardList.addAll(possibleBoards);
		for(int i = 0; i < boardList.size(); i++) {
			board = boardList.get(i);
			Result minScore = minRound(board, depth + 1, alpha, beta, startTime);
			if(minScore.score > max) {
				max = minScore.score;
			}
			if(minScore.score >= beta) {
				return new Result(board, max, System.currentTimeMillis() - startTime);
			}
			if(minScore.score > alpha) {
				alpha = minScore.score;
			}
		}
		return new Result(board, max, System.currentTimeMillis() - startTime);
	}
	
	private Result minRound(Board board, int depth, double alpha, double beta, long startTime) {
		if(this.isTerminalState(board, depth, startTime)){
			return new Result(board, this.getTerminalScore(board, "B"), System.currentTimeMillis() - startTime);
		}
		double min = Double.MAX_VALUE;
		Collection<Board> possibleBoards = board.getPossibleBoards("BK", "BP").values();
		ArrayList<Board> boardList = new ArrayList<>();
		boardList.addAll(possibleBoards);
		for(int i = 0; i < boardList.size(); i++) {
			board = boardList.get(i);
			Result minScore = minRound(board, depth + 1, alpha, beta, startTime);
			if(minScore.score < min) {
				min = minScore.score;
			}
			if(minScore.score <= alpha) {
				return new Result(board, min, System.currentTimeMillis() - startTime);
			}
			if(minScore.score < beta) {
				beta = minScore.score;
			}
		}
		return new Result(board, min, System.currentTimeMillis() - startTime);
	}
	private boolean isTerminalState(Board board, int depth, long startTime) {
		return depth == MAX_DEPTH || board.isTerminalBoard() || this.miliSecs <= (System.currentTimeMillis() - startTime);
	}
	private double getTerminalScore(Board board, String team) {
		double score;
		double black_score = Double.MIN_VALUE;
		double white_score = Double.MIN_VALUE;
		for(int i = 0; i < board.getPieces().size(); i++) {
			ChessPiece p = board.getPieces().get(i);
//			double farOff;
			if(p.getName().equals("WP")) {
				white_score = white_score > (double) p.getPosition().getY() ? white_score : (double) p.getPosition().getY();
			}
			if(p.getName().equals("BP")) {
				white_score = black_score > board.getSize() - 1 - (double) p.getPosition().getY() ? black_score : board.getSize() - 1 - (double) p.getPosition().getY();
			}
			if(p.getName().equals("WK")) {
				white_score += (double) board.getSize();
			}
			if(p.getName().equals("BK")) {
				white_score += (double) board.getSize();
			}
		}
		score = white_score - black_score;
//		System.out.println(score);
		return score;
	}

}
