package strategies.botplayingstrategy;

import models.*;

import java.util.List;

public class RandomMoveStrategy implements BotPlayingStrategy{

    @Override
    public Move decideMove(Player player, Board board) {
        List<List<Cell>> b = board.getBoard();
        for(int i=0;i<b.size();i++){
            for(int j=0;j<b.size();j++){
                if(b.get(i).get(j).getCellState()== CellState.EMPTY)
                    return new Move(player,new Cell(i,j));
            }
        }

        return null;
    }
}
