package newProject;

import java.io.*;
import java.util.Arrays;

public class MyProgram {

    private double maxSquare = 0;
    private int[] arrayMaxSquare = new int[5];
    private String[] array = new String[5];

    public static void main(String[] args) {

        MyProgram myProgram = new MyProgram();
        myProgram.readFile("in.txt");
        myProgram.writeFile("out.txt");

    }

    private void readFile(String inFile) {
        try (FileInputStream inputStream = new FileInputStream(inFile)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while ((inFile = br.readLine()) != null) {
                if (inFile.isEmpty()) {
                    continue;
                }
                array = (inFile.trim()).split("\\s+");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private int[] searchMaxSquare(String[] array) {
        try {
            if (array.length != 0) {
                int[] a = new int[array.length];
                for (int i = 0; i < a.length; i++) {
                    a[i] = Integer.parseInt(array[i]);
                }
                if (a.length == 6 && isIsoscelesTriangle(a)) {
                    Double square = 0.5 * (Math.abs((a[0] - a[4]) * (a[3] - a[5])) - ((a[2] - a[4]) * (a[1] - a[5])));
                    if (square > maxSquare) {
                        maxSquare = square;
                        arrayMaxSquare = a.clone();
                    }
                } else {
                    System.out.println("Равнобедренных треугольников в файле нет или нарушено " +
                                       "количество координат вершин равнобедренного треугольника");
                }
            }
        } catch (NumberFormatException e) {

            System.out.println("Некорректно заданы координаты вершин равнобедренного треугольника");
        }
        return arrayMaxSquare;
    }

    private void writeFile(String outFile) {
        try (FileOutputStream outputStream = new FileOutputStream(outFile)) {
            if (searchMaxSquare(array).length == 6) {
                outFile = Arrays.toString(this.searchMaxSquare(array))
                        .replace('[', ' ')
                        .replace(']', ' ')
                        .replace(',', ' ')
                        .trim();
                outputStream.write(outFile.getBytes());
            } else {
                outFile = "";
                outputStream.write(outFile.getBytes());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean isIsoscelesTriangle(int[] a) {
        double ab, ac, bc;
        ab = Math.sqrt(((a[2] - a[0]) * (a[2] - a[0])) + ((a[3] - a[1]) * (a[3] - a[1])));
        ac = Math.sqrt(((a[4] - a[0]) * (a[4] - a[0])) + ((a[5] - a[1]) * (a[5] - a[1])));
        bc = Math.sqrt(((a[4] - a[2]) * (a[4] - a[2])) + ((a[5] - a[3]) * (a[5] - a[3])));
        return ab == ac || ab == bc || ac == bc;
    }
}