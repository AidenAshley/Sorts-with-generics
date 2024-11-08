import java.io.*;
import java.util.Random; 
import java.util.Scanner; 

public class App 
{

    public static Integer[] createRandomArray(int arrayLength) 
    {
        Random random = new Random(); 
        Integer[] array = new Integer[arrayLength]; 

        for (int i = 0; i < arrayLength; i++) 
        {
            array[i] = random.nextInt(101); 
        }
        return array; 
    }

    public static <T> void writeArrayToFile(T[] array, String filename) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) 
        {
            for (T num : array) 
            {
                writer.write(num.toString() + "\n"); 
            }
            System.out.println("Array written to file: " + filename);
        } catch (IOException e) 
        {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static Integer[] readFileToArray(String filename) 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
        {
            return reader.lines().map(Integer::parseInt).toArray(Integer[]::new); 
        } catch (IOException e) 
        {
            System.out.println("Error reading from file: " + e.getMessage());
            return new Integer[0]; 
        }
    }

    public static <T extends Comparable<T>> void mergeSort(T[] array) 
    {
        if (array.length < 2) 
        {
            return; 
        }
        int mid = array.length / 2;

        T[] left = (T[]) new Comparable[mid];
        T[] right = (T[]) new Comparable[array.length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }

    private static <T extends Comparable<T>> void merge(T[] array, T[] left, T[] right) 
    {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) 
        {
            if (left[i].compareTo(right[j]) <= 0) 
            {
                array[k++] = left[i++];
            } else 
            {
                array[k++] = right[j++];
            }
        }

        while (i < left.length) 
        {
            array[k++] = left[i++];
        }
        while (j < right.length) 
        {
            array[k++] = right[j++];
        }
    }

    public static <T extends Comparable<T>> void bubbleSort(T[] array) 
    {
        int n = array.length; 
        for (int i = 0; i < n - 1; i++) 
        {
            for (int j = 0; j < n - i - 1; j++) 
            {
                if (array[j].compareTo(array[j + 1]) > 0) 
                {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in); 

        System.out.print("Enter the length of the array: ");
        int length = scanner.nextInt(); 

        Integer[] randomArray = createRandomArray(length);

        String filename = "array.txt";

        writeArrayToFile(randomArray, filename);

        Integer[] readArray = readFileToArray(filename);
        System.out.println("Array read from file:");

        for (Integer num : readArray) 
        {
            System.out.print(num + " "); 
        }
        System.out.println(); 

        mergeSort(readArray);
        System.out.println("Sorted array using merge sort:");

        for (Integer num : readArray) 
        {
            System.out.print(num + " "); 
        }
        System.out.println(); 

        randomArray = createRandomArray(length);
        System.out.println("Array before bubble sort:");

        for (Integer num : randomArray) 
        {
            System.out.print(num + " "); 
        }
        System.out.println(); 

        bubbleSort(randomArray);
        System.out.println("Sorted array using bubble sort:");

        for (Integer num : randomArray) 
        {
            System.out.print(num + " "); 
        }
        System.out.println(); 

        scanner.close();
    }
}