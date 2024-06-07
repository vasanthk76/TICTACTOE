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
        System.out.println("please give the row to make the move:");
        int row = scanner.nextInt();
        System.out.println("please give the column to make the move:");
        int col = scanner.nextInt();

        return new Move(this,new Cell(row,col));
    }
}
