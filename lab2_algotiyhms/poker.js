import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String args[]) throws FileNotFoundException {
        File file = new File("//home/lenovo/Завантаження/idea-IU-192.7142.36//tall.txt");
        Scanner scanner = new Scanner(file);


        int lengthOfNumbers = 0;
        while (scanner.hasNextInt()) {
            scanner.nextInt();
            ++lengthOfNumbers;
        }
        scanner.close();

        Scanner c = new Scanner(file);

        int[] cardArray = new int[lengthOfNumbers];

        int indexOfNumberInFile = 0;
        while (indexOfNumberInFile < lengthOfNumbers) {
            cardArray[indexOfNumberInFile] = c.nextInt();
            indexOfNumberInFile++;
        }

        for (int runByIteratorOfArray : cardArray) {

            System.out.println(runByIteratorOfArray);
        }

        int amountOfZeros = 0;
        for (int runByIteratorOfArray : cardArray) {
            if (runByIteratorOfArray == 0) ++amountOfZeros;
        }
        System.out.println("Jokers: " + amountOfZeros);

        int longestCombination = 0;

        for (int startNumber = 0; startNumber < lengthOfNumbers; ++startNumber) {
            int firstNumber = cardArray[startNumber];
            int lengthOfCombination = 0;
            int localAmountOfJokers = amountOfZeros;
            for (int nextNumberForCombiination = 1; nextNumberForCombiination < lengthOfNumbers; ++nextNumberForCombiination) {
                int lookingNumber = cardArray[startNumber] + nextNumberForCombiination;
                boolean found = false;
                for (int runByIteratorOfArray : cardArray) {
                    if (runByIteratorOfArray == lookingNumber) found = true;
                }
                if (found) {
                    lengthOfCombination++;
                } else {
                    if (localAmountOfJokers == 0) break;
                    localAmountOfJokers--;
                    lengthOfCombination++;
                }
            }

            if (longestCombination < lengthOfCombination) longestCombination = lengthOfCombination;

        }

        System.out.println("Longest combination:" + (longestCombination));

    }
}
