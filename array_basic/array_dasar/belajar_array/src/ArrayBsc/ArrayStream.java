package ArrayBsc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.IntStream;

class ArrayStream {
    private static int[] numbers = new int[10];
    private static Random random = new Random();
    public static void main(String[] args) {
        setIntegerArrayValue(0, 1000);
        showFullIntegerArray();
        getLessThan(500);


    }

    private static void setIntegerArrayValue(int first, int last){
        for (int x = first; x < numbers.length; x++){
            numbers[x] = random.nextInt(last);
        }
    }

    private static void showFullIntegerArray(){
        System.out.println("\nSemua elemen array: ");
        Arrays.sort(numbers);
        int[] descendingArray = IntStream.range(0, numbers.length)
                .map(i -> numbers[numbers.length - i - 1])
                .toArray();
        for(int number : descendingArray){
            System.out.print(number + " ");
        }
    }

    private static void getLessThan(int value){
        int[] descendingArray = IntStream.range(0, numbers.length)
                .map(i -> numbers[numbers.length - i - 1])
                .toArray();

        int[] lessThan = IntStream.of(descendingArray)
                .filter(x -> x < value)
                .toArray();

        System.out.println("\n\nAmbil elemen array kurang dari: " + value);

        for(int number : lessThan){
            System.out.print(number + " ");
        }
    }
}
