package backend.academy;

import lombok.experimental.UtilityClass;



/**
 * Главный класс для запуска игры с лабиринтом.
 */
@UtilityClass
@SuppressWarnings("RegexpSinglelineJava")
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
