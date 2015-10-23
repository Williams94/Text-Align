/** CS5001 Practical 1 Text Align
 * 
 * @author Ross Williams
 */
import java.util.Arrays;

public class Align {

    public static void main(String[] args) {

        String[] p = FileUtil.readFile("test.txt");

        int line_length = 60;

        String right_align = rightAlign(p, line_length);
        String left_align = leftAlign(p, line_length);
        String centre_align = centreAlign(p, line_length);
        String full_align = fullyJust(p, line_length);

        System.out.println("Right Aligned - Line Length: " + line_length + "\n" + right_align + "\n");
        System.out.println("Left Aligned - Line Length: " + line_length + "\n" + left_align + "\n");
        System.out.println("Centre Aligned - Line Length: " + line_length + "\n" + centre_align);
        System.out.println("Fully Aligned - Line Length: " + line_length + "\n" + full_align);

    }

    /**
     * @param paragraphs	paragraphs from text file
     * @param line_length	length of line at which to wrap text
     * @return				returns String
     */
    public static String fullyJust(String[] paragraphs, int line_length){
        String output = "";

        // Splits Individual characters into an array
        String[] lines = leftAlign(paragraphs, line_length).split("\\r?\\n");

        // Iterates over lines in the array adding whitespace to the start and end of each line
        for (String line : lines){

            // Keeps looping until the length of the line matches the required line length
            while (line.length() < line_length){

                // Fully justifies by adding an extra whitespace in-between words
                line = line.replaceAll(" ", "  ");
            }

            // If the line goes over the line length then trim whitespace and reduce number of whitespaces
            if (line.length() > line_length){
                line = line.trim();
                line = line.replace("  ", " ");
            }
            output += line + "\n";
        }
        return output;
    }

    /**
     * @param paragraphs	paragraphs from text file
     * @param line_length	length of line at which to wrap text
     * @return				returns String
     */
    public static String centreAlign(String[] paragraphs, int line_length){
        String output = "";

        // Splits Individual characters into an array
        String[] lines = leftAlign(paragraphs, line_length).split("\\r?\\n");

        // Iterates over lines in the array adding whitespace to the start and end of each line
        for (String line : lines){

            // Keeps looping until the length of the line matches the required line length
            while (line.length() < line_length-1){
                line = " " + line;
                line = line + " ";
            }
            if (line.length() > line_length){
                line = line.trim();
            }
            output += line + "\n";
        }
        return output;
    }

    /**
     * @param paragraphs	paragraphs from text file
     * @param line_length	length of line at which to wrap text
     * @return				returns String
     */
    public static String rightAlign(String[] paragraphs, int line_length){
        String output = "";

        // Splits Individual characters into an array
        String[] lines = leftAlign(paragraphs, line_length).split("\\r?\\n");

        // For loop iterates over each line and uses String.Format to align the text to the right
        for (String line : lines){
            output += String.format("%" + line_length + "s", line) +"\n";
            if (line.length() > line_length){
                line = line.trim();
            }
        }
        return output;
    }

    /**
     * @param paragraphs	paragraphs from text file
     * @param line_length	length of line at which to wrap text
     * @return				returns String
     */
    public static String leftAlign(String[] paragraphs, int line_length){
        // String array to hold individual characters
        String[] words = null;
        String output = "";

        // For loop iterates over each paragraph
        for (int i = 0; i < paragraphs.length; i++){
            int x = line_length;
            int y = line_length;

            // Splits paragraphs into array of individual words
            words = paragraphs[i].split("(?!^)");

            // For loop iterates the required number of times to align text according to lineLength
            for (int j = 0; j < words.length/y; j++){

                // Iterates backwards until there is an empty space to insert a new line
                while (!words[x].matches("\\s+")){
                    x--;
                }

                // Inserts new line in the empty space found
                words[x] = "\n";

                // Increases the position at which the next new line should be entered
                x = x + y; 
            }

            // Puts the array of characters back into a readable string format
            String text = Arrays.toString(words);
            text = text.substring(1, text.length()-1).replaceAll(", ", "");
            output += text + "\n";

        }
        return output;
    }

}
