package backend.academy;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/**
 * Класс, представляющий игру с лабиринтом.
 * Этот класс управляет процессом создания лабиринта, получения начальной и конечной точки,
 * выбора метода генерации лабиринта,
 * а также решением лабиринта с использованием выбранного алгоритма.
 */
@SuppressWarnings("RegexpSinglelineJava")
public class Game {
    private IGenerator generator;
    private Solver solver;

    /**
     * Начало игры и выбор соответствующих параметров
     */
    public void start() {
        int generateType;
        int solutionType;
        boolean check = false;
        Coordinate start = null;
        Coordinate end = null;
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        //указание размера лабиринта
        System.out.println("Введите ширину лабиринта: ");
        int width = validation(scanner);
        System.out.print("Введите высоту лабиринта: ");
        int height = validation(scanner);

        //выбор типа генерации
        System.out.println("Создание лабиринта. Выберите тип генерации:");
        generateType = generationType(scanner);
        if (generateType == 1) {
            generator = new GenerateGrowingTree();
        } else {
            generator = new GenerateRandomWalk();
        }
        Render renderer = new Render();
        Maze maze = generator.generate(height, width);
        System.out.println(renderer.render(maze));

        //указание начально точки
        while (!check) {
            System.out.println("Введите координаты начальной точки (строка): ");

            int startRow = scanner.nextInt();
            while (startRow == 0 || startRow > height) {
                System.out.println(MessageConstants.PLEASE_VALID + height);
                startRow = scanner.nextInt();

            }
            startRow = startRow - 1;
            System.out.println("Введите координаты начальной точки (столбец): ");
            int startCol = scanner.nextInt();
            while (startCol == 0 || startCol > width) {
                System.out.println(MessageConstants.PLEASE_VALID + width);
                startCol = scanner.nextInt();
            }
            startCol = startCol - 1;
            start = new Coordinate(startRow, startCol);
            check = startEndPoint(maze, start, Render.PointType.START);
        }

        //указание конечной точки
        check = false;
        while (!check) {
            System.out.println("Введите координаты конечной точки (строка): ");
            int endRow = scanner.nextInt();
            while (endRow == 0 || endRow > height) {
                System.out.println(MessageConstants.PLEASE_VALID + height);
                endRow = scanner.nextInt();
            }
            endRow = endRow - 1;
            System.out.println("Введите координаты конечной точки (столбец): ");
            int endCol = scanner.nextInt();
            while (endCol == 0 || endCol > width) {
                System.out.println(MessageConstants.PLEASE_VALID + width);
                endRow = scanner.nextInt();
            }
            endCol = endCol - 1;
            end = new Coordinate(endRow, endCol);
            check = startEndPoint(maze, end, Render.PointType.END);
        }
        startEndPoint(maze, end, Render.PointType.END);
        System.out.println(renderer.render(maze));
        solutionType = solutionType();
        if (solutionType == 1) {
            solver = new SearchSolv();
        } else {
            solver = new DepthSolver();
        }

        List<Coordinate> path = solver.solve(maze, start, end);

        if (!path.isEmpty()) {
            System.out.println("Решение лабиринта:");
            System.out.println(renderer.renderPath(maze, path));

        } else {
            System.out.println("No path found from start to end.");
        }
    }

    /**
     * Метод для проверки корректности начальной и конечной точки.
     *
     * @param maze Лабиринт, который нужно проверить.
     * @param point Координаты точки, которую нужно проверить.
     * @param pointType Тип точки (начальная или конечная).
     */
    public static boolean startEndPoint(Maze maze, Coordinate point, Render.PointType pointType) {
        boolean check = false;
        Render render = new Render();
        check = render.startMaze(maze, point, pointType);
        if (!check) {
            System.out.println("Пожалуйста введите валидные координаты точки, она не должна быть стеной");
            return false;
        }
        return true;

    }

    /**
     * Метод для валидации ввода целого числа.
     */
    public static int validation(Scanner scanner) {
        int intType = 0;
        boolean validInput = false;

        while (!validInput) {
            if (scanner.hasNextInt()) {
                intType = scanner.nextInt();
                validInput = true;
            } else {
                System.out.println("Ошибка: введено не целое число. Пожалуйста введите числовой формат:");
                scanner.next();
            }
        }
        return intType;
    }

    /**
     * Метод для выбора типа генерации лабиринта.
     */
    public static int generationType(Scanner scanner) {
        int generationType = 0;
        boolean validInput = false;
        System.out.println("Пжалуйста, выберите тип генеррации");
        System.out.println("1-Растущее дерево");
        System.out.println("2-Алгоритм Прима");

        while (!validInput) {
            if (scanner.hasNextInt()) {
                generationType = scanner.nextInt();
                if (generationType == 1 || generationType == 2) {
                    validInput = true;
                } else {
                    System.out.println(MessageConstants.ERROR_MESSAGE + generationType + MessageConstants.TWO_OR_ONE);
                    scanner.next();
                }
            } else {
                System.out.println(MessageConstants.ONE_TWO);
                scanner.next();
            }
        }
        return generationType;
    }

    /**
     * Метод для выбора типа решения лабиринта.
     */
    public static int solutionType() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        int solutionType = 0;
        boolean validInput = false;
        System.out.println("Пожаллуйста выберите вариант решения лабиринта:");
        System.out.println("1-В ширину");
        System.out.println("2-В глубину");

        while (!validInput) {
            if (scanner.hasNextInt()) {
                solutionType = scanner.nextInt();
                if (solutionType == 1 || solutionType == 2) {
                    validInput = true;
                } else {
                    System.out.println(MessageConstants.ERROR_MESSAGE + solutionType + MessageConstants.TWO_OR_ONE);
                    scanner.next();

                }
            } else {
                System.out.println(MessageConstants.ONE_TWO);
                scanner.next();
            }

        }
        return solutionType;
    }



}
