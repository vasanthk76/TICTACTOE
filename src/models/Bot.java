package models;

import strategies.botplayingstrategy.BotPlayingStrategy;
import strategies.botplayingstrategy.RandomMoveStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, char symbol, PlayerType type, BotDifficultyLevel botDifficultyLevel,String botPlayingStrategy) {
        super(name, symbol, type);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = new RandomMoveStrategy();
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move decideMove(Board board) {
        System.out.println("BOT deciding move");
        return botPlayingStrategy.decideMove(this,board);
    }
}
