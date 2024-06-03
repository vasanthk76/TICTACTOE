import controllers.GameController;
import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("enter dimensions");
        int dimension = scanner.nextInt();

        System.out.println("Add a bot to the Game?");

        String isBot = scanner.next();

        int numberOfPlayers = dimension - 1;
        int numberOfHumanPlayers = numberOfPlayers;

        List<Player> players = new ArrayList<>();

        if(isBot.charAt(0)=='y'){
            numberOfHumanPlayers --;

            System.out.println("Enter name of the bot");
            String botName = scanner.next();
            System.out.println("Enter the symbol of the bot");
            String symbol = scanner.next();

            players.add(new Bot(botName,symbol.charAt(0),PlayerType.BOT, BotDifficultyLevel.EASY));
        }

        for(int i=0;i<numberOfHumanPlayers;i++){
            System.out.println("Enter the name of the player");
            String name = scanner.next();

            System.out.println("Enter the player symbol");
            String symbol = scanner.next();

            players.add(new Player(name,symbol.charAt(0), PlayerType.HUMAN));
        }

        Game game = gameController.createGame(dimension,players);

        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){

            System.out.println("CURRENT BOARD");
            gameController.displayBoard(game);

            System.out.println("Do you want to undo? Y/N");
            String isUndo = scanner.next();
            if(isUndo.charAt(0)=='y'){
                gameController.undo();
            }else{
                gameController.executeNextMove();
            }
        }

        if(gameController.getGameStatus(game).equals(GameStatus.ENDED)){
            System.out.println("Winner is "+ game.getWinner().getName());
        }else{
            System.out.println("GAME DRAW.");
        }
    }
}