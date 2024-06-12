package models;

import java.util.Scanner;

public class Player {
    private char symbol;
    private String name;
    private PlayerType type;

    public Player(String name, char symbol, PlayerType type) {
        this.symbol = symbol;
        this.name = name;
        this.type = type;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public Move decideMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(this.name+"'s turn, please make the next move:");
        String pos = scanner.next();

        int row = Integer.parseInt(pos.substring(0,1));
        int col = Integer.parseInt(pos.substring(1,2));




        Move newMove = new Move(this,new Cell(row,col));
        newMove.getCell().setPlayer(this);
        return newMove;
    }
}
