package models;

import exceptions.InvalidDimensionException;
import exceptions.InvalidNumberOfPlayers;
import strategies.gamewinningstrategy.GameWinningStrategy;
import strategies.gamewinningstrategy.OrderOneWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private Player winner;
    private GameWinningStrategy gameWinningStrategy;

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public void displayBoard() {
        this.board.displayBoard();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean makeNextMove(){
        //get the next player
        Player currentMovePlayer = players.get(nextPlayerIndex);

        //player should decide the move
        Move move = currentMovePlayer.decideMove(this.getBoard());

        //validate move TODO

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        boolean validMove = validateMove(row,col);

        while(!validMove){
            System.out.println("INVALID MOVE!");
            move = currentMovePlayer.decideMove(this.getBoard());
            row = move.getCell().getRow();
            col = move.getCell().getCol();
            validMove = validateMove(row,col);
        }

        //make the move if it is valid
        this.board.getBoard().get(row).get(col).setCellState(CellState.FILLED).setPlayer(currentMovePlayer);

        //add the move to the list of moves
        this.moves.add(move);

        //check winner
        if(gameWinningStrategy.checkWinner(board,move.getCell(),currentMovePlayer)){
            setGameStatus(GameStatus.ENDED);
            winner=currentMovePlayer;
        }

        //move to next player
        nextPlayerIndex++;
        nextPlayerIndex%=players.size();
        return true;
    }

    private boolean validateMove(int row, int col) {
        if(row>=board.getBoard().size() || col>=board.getBoard().size())
            return false;
        Cell cell = board.getBoard().get(row).get(col);
        if(cell.getCellState()==CellState.FILLED)
            return false;
        return true;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public static GameBuilder getBuilder(){
        return new GameBuilder();
    }

    public void undoMove() {
        Move lastMove = moves.removeLast();
        board.getBoard().get(lastMove.getCell().getRow()).get(lastMove.getCell().getCol()).setCellState(CellState.EMPTY);
        System.out.println("move has been undone");
    }

    public static class GameBuilder{
        private int dimension;
        private List<Player> players;

        public int getDimension() {
            return dimension;
        }

        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private boolean isValid() throws InvalidDimensionException, InvalidNumberOfPlayers {
            if(this.dimension<3){
                throw new InvalidDimensionException("Dimension can't be less than 3");
            }
            if(this.players.size() != dimension-1){
                throw new InvalidNumberOfPlayers("Number of players should be 1 less than the game dimension");
            }
            // check if each player has different symbol or not
            return true;
        }

        public Game build(){
            //validation.
            try{
                isValid();
            }catch (Exception e){
                System.out.println("Exception occurred during Game creation");
            }

            Game game = new Game();
            game.setBoard(new Board(dimension));
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setMoves(new ArrayList<>());
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneWinningStrategy(dimension));
            return game;
        }
    }
}
