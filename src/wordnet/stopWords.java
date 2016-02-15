package wordnet;

import java.io.*;
import java.util.*;

class wordPOS
{
    String word;
    String pos;
    wordPOS(String word,String pos)
    {
        this.word = word;
        this.pos = pos;
    }

	public String toString()
	    {
	        return (this.word + " " + this.pos);
	    }
	}

public class stopWords
{
    HashSet <String> set;
    FileReader fileReader;

    void readStopWords() throws FileNotFoundException, IOException
    {
        set = new HashSet <String> ();
        fileReader = new FileReader("C:\\Users\\Kamal\\Desktop\\stopwords.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine()) != null)
        {
            set.add(line);
        }
        fileReader.close();
    }


    void removeStopWords() throws FileNotFoundException, IOException
    {
    	UI uiInstance = new UI();
    	String selectedFile = uiInstance.getInputString();
	    fileReader = new FileReader(selectedFile);
	    FileWriter fileWriter = new FileWriter("C:\\Users\\Kamal\\Desktop\\test1.txt");
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        int count = 0;
        StringTokenizer tokenizer;
        String word;
        while((line = bufferedReader.readLine()) != null)
         {
            tokenizer = new StringTokenizer(line, ",.-:; ");
            while(tokenizer.hasMoreTokens())
            {
                 word = tokenizer.nextToken();
                 if(set.contains(word) == false)
                 {
                     fileWriter.write(word+ " ");
                 }
            }
            fileWriter.write("\r\n");
         }
        fileReader.close();
        fileWriter.close();
        System.out.println(count);
     }
}