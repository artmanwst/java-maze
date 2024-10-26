package backend.academy;

/**
 * Интерфейс для генераторов лабиринтов.
 * Этот интерфейс определяет метод для генерации лабиринта заданной высоты и ширины.
 */
public interface IGenerator {
    Maze generate(int height, int width);
}
