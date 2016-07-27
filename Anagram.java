import java.io.*;
import java.util.*;

public class Anagram {
    public static void main(String[] args) {
        HashMap<String,String> anagrams = new HashMap<>();
        ArrayList<String> lines = getLines(args[0]);
        for (String line : lines){
            String[] words = getWords(line);
            anagrams = getAnagrams(words, anagrams);
        }
        System.out.println(displayAnagrams(anagrams));
    }

    public static String sortWord(String word){
        String sortedWord = "";
        ArrayList<Character>AlphabetList = new ArrayList<>();
        for(int i = 0; i < word.length(); i++){
            AlphabetList.add(word.charAt(i));
        }
        Collections.sort(AlphabetList);
        for (char alphabet : AlphabetList){
            sortedWord += String.valueOf(alphabet);
        }

        return sortedWord;
    }

    public static String displayAnagrams(HashMap<String,String> anagrams){
        String anagramsPairs = "";
        Set<Map.Entry<String, String>> allKeys = anagrams.entrySet();
        for(Map.Entry<String, String> entry : allKeys){
            if(entry.getValue().contains(" ")){
                anagramsPairs += entry.getValue() + "\n";
            }
        }
        return anagramsPairs;
    }

    public static HashMap<String, String> getAnagrams(String[] words, HashMap<String, String> anagrams){
        for(String word : words){
            String sortedWord = sortWord(word);
            if(anagrams.containsKey(sortedWord)){
                anagrams.put(sortedWord, "" + anagrams.get(sortedWord) + " " + word);
            } else{
                anagrams.put(sortedWord, word);
            }
        }
        return anagrams;
    }
    public static ArrayList<String> getLines (String filename){
        File f = new File(filename);
        BufferedReader br = null;
        ArrayList<String> lineArray = new ArrayList<>();
        try{
            FileReader fr = new FileReader(f);
            br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                lineArray.add(line);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("error");
        }
        catch (IOException e){
            System.out.println("can't read");
        }
        try{
            br.close();
        }
        catch(IOException e){
            System.out.println("can't close");
        }
        catch(NullPointerException e){
            System.out.println("File never opened");
        }
        return lineArray;
    }

    public static String[] getWords(String line) {
        return line.replaceAll("[[^a-zA-Z]&&[^a-zA-z'a-zA-Z]]", " ").split(" +");
    }
}
