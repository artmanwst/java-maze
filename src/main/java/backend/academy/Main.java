package backend.academy;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import lombok.experimental.UtilityClass;



@UtilityClass
@SuppressWarnings("RegexpSinglelineJava")
public class Main {
    private static final String ONE_TWO = "Ошибка: введено не целое число. Пожалуйста введите числовой формат 1 или 2:";
    private static final String ERROR_MESSAGE = "Ошибка, введено число:";
    private static final String TWO_OR_ONE = "Пожалуйста введите 1 или 2";


    public static void main(String[] args) {
        int generateType;
        int solutionType;
        IGenerator generator;
        Solver solver;
        boolean check = false;
        Coordinate start = null;
        Coordinate end = null;
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.println("Введите ширину лабиринта: ");
        int width = validation(scanner);
        System.out.print("Введите высоту лабиринта: ");
        int height = validation(scanner);
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
        while (!check) {
            System.out.println("Введите координаты начальной точки (строка): ");
            int startRow = scanner.nextInt() - 1;
            System.out.println("Введите координаты начальной точки (столбец): ");
            int startCol = scanner.nextInt() - 1;
            start = new Coordinate(startRow, startCol);
            check = startEndPoint(maze, start, Render.PointType.START);
        }
        check = false;
        while (!check) {
            System.out.println("Введите координаты конечной точки (строка): ");
            int endRow = scanner.nextInt() - 1;
            System.out.println("Введите координаты конечной точки (столбец): ");
            int endCol = scanner.nextInt() - 1;
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
                    System.out.println(ERROR_MESSAGE + generationType + TWO_OR_ONE);
                    scanner.next();
                }
            } else {
                System.out.println(ONE_TWO);
                scanner.next();
            }
        }
        return generationType;
    }

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
                    System.out.println(ERROR_MESSAGE + solutionType + TWO_OR_ONE);
                    scanner.next();

                }
            } else {
                    System.out.println(ONE_TWO);
                scanner.next();
            }

        }
        return solutionType;
    }

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
}
