package controllers;

import models.Game;
import models.GameStatus;
import models.Player;

import java.util.List;

public class GameController {
    //gameController will have all the methods that a client needs from the game
    public void undo(){

    }
    public Game createGame(int dimension, List<Player> players){
        try{
            Game game = Game.getBuilder().setdimension(dimension).setPlayers(players).build();
            return game;
        }catch (Exception e){
            return null;
        }
    }

    public Player getWinner(){

    }

    public void displayBoard(){

    }

    public void executeNextMove(){

    }

    public GameStatus getGameStatus(){

    }
}

