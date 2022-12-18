package ScannerCls;

import java.io.File;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class ScnrCls {
    public static void main(String[] args) throws FileNotFoundException {
        urlFile();
        streamInput();
    }

    static void streamInput() {
        // Create a File object that represents the file we want to read
        URL url = ScnrCls.class.getResource("Angka.txt");
        assert url != null;
        File file = new File(url.getPath());

        // Create an InputStream object
        InputStream inputStream = null;

        try {
            // Open the file
            inputStream = new FileInputStream(file);

            // Create a buffer to hold the data from the file
            byte[] buffer = new byte[1024];

            // Keep track of how many bytes have been read
            int bytesRead = 0;

            // Read the data from the file and store it in the buffer
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                // Print out the data that was read from the file
                System.out.println(new String(buffer, 0, bytesRead));
            }
        } catch (IOException ex) {
            // Handle any errors that may have occurred
            ex.printStackTrace();
        } finally {
            // Close the input stream
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    static void urlFile() throws FileNotFoundException {
        // Create a File object that represents the file we want to read
        URL url = ScnrCls.class.getResource("Teks.txt");
        assert url != null;
        File file = new File(url.getPath());

        // Create a Scanner object that reads from the file
        Scanner scanner = new Scanner(file);

        // Read each line of the file and print it to the screen
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
    }
}

/*
 *     Import the Scanner class: import java.util.Scanner;
 *
 *     Create a Scanner object that reads from the file you want to read. You will need to pass the Scanner object a File object that represents the file you want to read. You can create a File object using the following code: File file = new File("path/to/your/file.txt");
 *
 *     Use the Scanner object's nextLine() method to read a line of text from the file. This method returns a String that contains the line of text that was read. You can use the following code to read a line of text from the file: String line = scanner.nextLine();
 *
 *     When you are done reading from the file, use the Scanner object's close() method to close the file. This will free up any resources that were being used to read from the file. You can use the following code to close the file: scanner.close();
 *
 * In this example, we create a File object that represents the file we want to read, then we create a Scanner object that reads from that file. We use a while loop to read each line of the file and print it to the screen. Finally, we close the file using the close() method.
 * */

/*
* Here's a step-by-step explanation of the code:

   * Import the InputStream and IOException classes from the java.io package. These are used to create an InputStream object and handle any errors that may occur when reading from the file.

  *  Create a class named ScnrCls that contains a main method. This method will be the entry point for the program and will contain the code for reading from the file.

   * Inside the main method, declare a String variable named fileName and initialize it with the name of the file that you want to read from. In this example, the file is called "Angka.txt".

   * Declare an InputStream variable named inputStream and initialize it to null. This variable will be used to hold the InputStream object that is created in the next step.

   * Inside a try block, create a new InputStream object and assign it to the inputStream variable. This object will be used to read data from the file.

   * Create a byte array named buffer with a size of 1024 bytes. This array will be used to hold the data that is read from the file.

   * Declare an int variable named bytesRead and initialize it to 0. This variable will be used to keep track of how many bytes have been read from the file.

   * Inside a while loop, call the read method on the inputStream object and pass in the buffer array as an argument. This will read the data from the file and store it in the buffer array. The read method returns the number of bytes that were read, so this value is assigned to the bytesRead variable.

   * The while loop continues until the read method returns -1, which indicates that the end of the file has been reached.
* */
