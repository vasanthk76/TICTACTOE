package strategies.gamewinningstrategy;

import models.Board;
import models.Cell;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements GameWinningStrategy{
    private List<HashMap<Character,Integer>> rowSymbolCounts = new ArrayList<>();
    private List<HashMap<Character,Integer>> colSymbolCounts = new ArrayList<>();

    HashMap<Character,Integer> topLeftDiagonal = new HashMap<>();
    HashMap<Character,Integer> topRightDiagonal = new HashMap<>();
    int dimension;

    public OrderOneWinningStrategy(int dimension){
        this.dimension=dimension;
        for(int i=0;i<dimension;i++){
            rowSymbolCounts.add(new HashMap<>());
            colSymbolCounts.add(new HashMap<>());
        }
    }

    @Override
    public boolean checkWinner(Board board, Cell moveCell,Player player) {
        boolean result = true;
//        Player player = moveCell.getPlayer();

        int row = moveCell.getRow();
        int col = moveCell.getCol();
        char symbol = player.getSymbol();

        HashMap<Character,Integer> rowMap = rowSymbolCounts.get(row);
        HashMap<Character,Integer> colMap = colSymbolCounts.get(col);

        if(!rowMap.containsKey(symbol))
            rowMap.put(symbol,0);
        else
            rowMap.put(symbol,rowMap.get(symbol)+1);

        if(!colMap.containsKey(symbol))
            colMap.put(symbol,0);
        else
            colMap.put(symbol,colMap.get(symbol)+1);

        if(rowMap.get(symbol)==dimension || colMap.get(symbol)==dimension) {
            System.out.println("inside 50");
            return true;
        }

        if(row==col){
            if(topLeftDiagonal.containsKey(symbol)) {
                topLeftDiagonal.put(symbol, topLeftDiagonal.get(symbol) + 1);
            }
            else{
                topLeftDiagonal.put(symbol,1);
            }
            if(topLeftDiagonal.get(symbol)==dimension) {
                System.out.println("inside 63");
                return true;
            }
        }

        if(row+col==dimension-1){
            if(topRightDiagonal.containsKey(symbol))
                topRightDiagonal.put(symbol,topRightDiagonal.get(symbol)+1);
            else
                topRightDiagonal.put(symbol,1);
            if(topRightDiagonal.get(symbol)==dimension) {
                System.out.println("inside 74");
                return true;
            }
        }

        return false;
    }
}
