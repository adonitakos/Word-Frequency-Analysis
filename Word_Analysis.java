import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class Word_Analysis {

    final static String alice_Path = "alice29.txt";
    final static String stopwords_Path = "stopwords.txt";

    public static String TXTtoString(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        // Delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String textRaw = stringBuilder.toString();
        textRaw = textRaw.toLowerCase(); // make everything lowercase (NO NEED FOR UPPERCASE LETTERS!)
        return textRaw;
    } // <--- TXTtoString() method ends here


    public static void main(String[] args) throws IOException, FileNotFoundException {
        //PrintWriter outToTXT = new PrintWriter("aliceHashSet.txt");

        System.out.println("\n\n"); // line break from the directories in the console

// --------------------------- Read through the alice29.txt file and store in a String variable ----------------- \\
      
        String aliceRaw = TXTtoString(alice_Path);
    // Optional print statement:
        // System.out.println("\n ----------- aliceRaw ----------- \n" + aliceRaw); 
        // PrintWriter aliceRawWriter = new PrintWriter("aliceRawOutput.txt");
        // aliceRawWriter.println(aliceRaw);


        System.out.println("\n\n\n"); // <--- line break

// --------------------------- Remove punctuation & numbers from the aliceRaw String variable--------------------------- \\
        String aliceCleaned = aliceRaw.replaceAll("\\p{Punct}", ""); // this will replace any of the specified characters with that of nothing
        aliceCleaned = aliceCleaned.replace("→", "");

    // Optional print statement
        // System.out.println("\n ----------- aliceCleaned ----------- \n" + aliceCleaned); //  

// --------------------------- Tokenize each individual word from the String variable --------------------------- \\

        StringTokenizer tokenizer = new StringTokenizer(aliceCleaned);

        ArrayList <String> Alice_tokenStringList = new ArrayList<String>();

        for (int i = 1; tokenizer.hasMoreTokens(); i++) {
            Alice_tokenStringList.add(tokenizer.nextToken()); // tokenizing all the individual words and then storing them in a String ArrayList
           // System.out.println("Token " + i + ": " + tokenizer.nextToken());
        }

        String[] Alice_Tokens_Array = new String[ Alice_tokenStringList.size() ];
        Alice_tokenStringList.toArray(Alice_Tokens_Array);


// Optional print statement:
        // String Alice_Tokens__output = "-------- Alice Tokens ----------\n" + Arrays.toString(Alice_Tokens_Array);
        // PrintWriter alicetokenwriter = new PrintWriter("alicetokens.txt");
        // alicetokenwriter.println(Alice_Tokens__output); // this should have no stopwords removed


// ----------------------- Get stopwords into a String variable ----------------------------- \\

// HIGH LEVEL METHOD THAT REQUIRES JAVA 9 or newer
// Make sure you have the most up-to-date JDK on your system: https://www.oracle.com/java/technologies/downloads/
        Path fileName = Path.of(stopwords_Path);
        String stopwords = Files.readString(fileName);
        
        StringTokenizer stopwords_tokenizer = new StringTokenizer(stopwords);

        ArrayList <String> stopwords_TokenList = new ArrayList<String>();

        for (int i = 1; stopwords_tokenizer.hasMoreTokens(); i++) {
            stopwords_TokenList.add(stopwords_tokenizer.nextToken()); // tokenizing all the individual words and then storing them in a String ArrayList
           // System.out.println("Token " + i + ": " + tokenizer.nextToken());
        }

        String[] stopwords_TokensArray = new String[ stopwords_TokenList.size() ];
        stopwords_TokenList.toArray(stopwords_TokensArray);

     // Optional print statement
        //System.out.println("StopWords Tokens\n" + Arrays.toString(stopwords_TokensArray));
                            
// ------------------------------------------- Remove stopwords from alice ---------------------------------------- \\
       
        ArrayList<String> wordsList = new ArrayList<String>();
        Set<String> stopWordsSet = new HashSet<String>();
        for(String word: stopwords_TokensArray) {
            //System.out.println(word);
            stopWordsSet.add(word);
        } // <--- for() loop ends here

        //System.out.println("============ Alice After StopWords ===========");
        for(String word : Alice_Tokens_Array)
        {
            if(!stopWordsSet.contains(word)) {
                wordsList.add(word);
            }
        } // <--- for() loop ends here

        PrintWriter newAliceOutput = new PrintWriter("aliceAfterStopWords.txt");
        newAliceOutput.println("============ Alice After StopWords ===========");
        for (String word: wordsList){
            newAliceOutput.print(word + " ");
        } // <--- for() loop ends here


// --------------------------------------- Word frequency --------------------------------------- \\ 

    Map<String, Integer> hashMap = new HashMap<>();
    //  word    frequency

    for (String word : wordsList) {

        // Asking whether the HashMap contains the
        // key or not. Will return null if not.
        Integer integer = hashMap.get(word);

        if (integer == null) {
            // Storing the word as key and its
            // occurrence as value in the HashMap.
            hashMap.put(word, 1);
        }
        else {
            // Incrementing the value if the word
            // is already present in the HashMap.
            hashMap.put(word, integer + 1);
        }
    } // <--- for() loop ends here

    // Depending on your console, this may only show a bottom portion of the output:
    // (I was using Visual Studio Code with a BASH console)
    for (Map.Entry<String, Integer> en : hashMap.entrySet()) {
        System.out.println("Word = " + en.getKey() +
                    ", Frequency = " + en.getValue());
    }
// This is far more reliable because you are not limited by the console, so look out for a text file with the following name
    PrintWriter unsortedHashMap_output = new PrintWriter("unsortedHashMap.txt");
    for (Map.Entry<String, Integer> en : hashMap.entrySet()) {
        unsortedHashMap_output.println("Word = " + en.getKey() +
                    ", Frequency = " + en.getValue());
    }


// --------------------------------------- hashMap sorting --------------------------------------- \\ 
    Map<String, Integer> hashMapSorted = hashMap.entrySet().stream()
        .sorted(Comparator.comparingInt(e -> -e.getValue()))
        .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (a, b) -> { throw new AssertionError(); },
                LinkedHashMap::new
        )); // <--- Lamba sorting function ends here
        System.out.println("\n---------- Sorted Hash Map ---------------");
    
    // Depending on your console, this may only show a bottom portion of the output:
    // (I was using Visual Studio Code with a BASH console)
        for (Map.Entry<String, Integer> en : hashMapSorted.entrySet()) {
            System.out.println("Word = " + en.getKey() +
                        ", Frequency = " + en.getValue());
        } // <--- for() loop ends here
    // This is far more reliable because you are not limited by the console, so look out for a text file with the following name
        PrintWriter sortedHashMap_output = new PrintWriter("sortedHashMap.txt");
        for (Map.Entry<String, Integer> en : hashMapSorted.entrySet()) {
            sortedHashMap_output.println("Word = " + en.getKey() +
                        ", Frequency = " + en.getValue());
        } // <--- for() loop ends here

// -------------------------------------------- Task 4: Statistics --------------------------------------------------- \\ 
        
// Brute Force method: (not the most ideal, but very capable of returning results)
// Checks every character at in the file and checks to see if any of the punctuation symbols are present. If so, it will increment the counter variable.
        int punctuationCount = 0;  
        for (int i = 0; i < aliceRaw.length(); i++) {  
                //Checks whether given character is punctuation mark  
            if(aliceRaw.charAt(i) == '!' || aliceRaw.charAt(i) == ',' || aliceRaw.charAt(i) == ';' || aliceRaw.charAt(i) == '.' || aliceRaw.charAt(i) == '?' || aliceRaw.charAt(i) == '-' ||  
                aliceRaw.charAt(i) == '\'' || aliceRaw.charAt(i) == '\"' || aliceRaw.charAt(i) == ':' || aliceRaw.charAt(i) == '`' || aliceRaw.charAt(i) == '*' || aliceRaw.charAt(i) == '(' || aliceRaw.charAt(i) == ')' 
                || aliceRaw.charAt(i) == '_') 
            {  
                punctuationCount++;  
            }  
        } // <--- for() loop ends here

    // I tried this method because it was similar to my punctuation REMOVAL steps, and it seemed smarter
    // but unfortunately, it only outputted the value as 0:  

        // int punctuationCount = 0;  
        // for (int i = 0; i < aliceRaw.length(); i++) {  
        //         //Checks whether given character is punctuation mark  
        //     if (Pattern.matches("\\p{Punct}", aliceRaw)) {
        //         punctuationCount++;
        //     }
        // }  
        String sizeOfDataset = "The size of the dataset (prior to processing) of Alice was: " + aliceRaw.length() + " bytes";
        String numberOfWords = "The number of words remaining after pre-processing is:  " + wordsList.size() + " words";
        String numberOfStopWords = "The number of stop words was: " + stopwords_TokenList.size() + " words";
        String numberOfPunctuation = "The number of punctuation symbols was: " + punctuationCount + " symbols";

        String stats_output = "\n-------- Statistics -------- " + "\n"
        + sizeOfDataset + "\n" 
        + numberOfWords + "\n" 
        + numberOfStopWords + "\n"
        + numberOfPunctuation + "\n";

        System.out.println(stats_output);

    // For some reason, this method prints nothing to a text file (I'm not sure why)
        PrintWriter statsOutputWriter = new PrintWriter("statistics.txt");
        statsOutputWriter.print(stats_output);


    } // <--- main() method ends here

}  // <--- Word_Analysis{} class ends here