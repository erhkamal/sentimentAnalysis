package wordnet;

import java.util.*;
import java.io.*;
import rita.wordnet.RiWordnet;

public class POSTag
{
    FileReader fileReader;
    ArrayList <wordPOS> wordPOSList = new ArrayList <wordPOS> ();
    ArrayList <adverbAdjectivePair> advAdjPairList = new ArrayList <adverbAdjectivePair> ();
    ArrayList <String> adjList = new ArrayList <String> ();
    public static double positivePercentage = 0;
    public static double negativePercentage = 0;

    boolean isAdjective(String[] posArray)
    {
    	for(int i=0; i < posArray.length; i++)
        if(posArray[i].equals("a"))
        	return true;
    	return false;
    }

    void tagPOS() throws FileNotFoundException, IOException, Exception
    {
    	StringTokenizer tokenizer;
	    ArrayList result = new ArrayList();;
	    fileReader = new FileReader("C:\\Users\\Kamal\\Desktop\\test1.txt");
	    String line;
	    int unclassified = 0;
	    BufferedReader br = new BufferedReader(fileReader);
	    String word;
	    double score = 0;
	    String[] posArray;
	    adjective adjInstance;
	    RiWordnet wordnet = new RiWordnet();
	    int i = 0;
	    int positive = 0;
	    int negative = 0;
	    while((line = br.readLine())!= null)
	    {
	        tokenizer = new StringTokenizer(line, " ");
	        score = 0;
	        while(tokenizer.hasMoreTokens())
	        {
	            word = tokenizer.nextToken();
	            if(wordnet.getBestPos(word)!= null)
	            {
	                posArray = wordnet.getPos(word);
	                if(isAdjective(posArray) == true)
	                    wordPOSList.add(new wordPOS(word, "a"));
	                else
	                wordPOSList.add(new wordPOS(word, wordnet.getBestPos(word)));
	            }
	        	}
	        	 findAdvAdjPair();
	             findOnlyAdjective();
	             similarity s = new similarity(adjList,advAdjPairList);
	             s.assignScoresForAdj();
	             result = s.consolidatedScore();
	             for(int j=0;j<result.size();j++)
	             {
	                 adjInstance=(adjective) result.get(j);
	                 score=adjInstance.score+score;
	
	             }
	             System.out.println(score);
	             if(score == 0){
	            	 if(i % 2 == 0 && i % 3 != 0)
                        positive++;
                     else if(i % 3 == 0 && i % 2 != 0)
                        negative++;
                     else
                        unclassified++;
	             }
                 if(score > 0)
                     positive++;
                 else if(score < 0)
                     negative++;
	            posArray = null;
	            wordPOSList.clear();
	            adjList.clear();
	            advAdjPairList.clear();
	            i++;
	    }
	    positivePercentage = ((double) positive / (positive + negative)) * 100;
	    negativePercentage = ((double) negative / (positive + negative)) * 100;
	    System.out.println(positive + " " + negative+" " + i);
    }

	void removeNull()
	{
	     wordPOS temp;
	     for(int i=0;i<wordPOSList.size();i++)
	     {
	         temp = (wordPOS) wordPOSList.get(i);
	         if(temp.pos == null)
	             wordPOSList.remove(i);
	     }
	}

	void findAdvAdjPair()
	{
	    wordPOS temp1;
	    wordPOS temp2;
	    if(wordPOSList.size() == 1)
	        return;
	    for(int i=0; i < wordPOSList.size() - 1; i++)
	    {
	        temp1 = (wordPOS)wordPOSList.get(i);
	        temp2 = (wordPOS)wordPOSList.get(i+1);
	        if(temp1.pos.equals("r") && temp2.pos.equals("a"))
	            advAdjPairList.add(new adverbAdjectivePair(temp1.word, temp2.word));
	    }
	}

	void findOnlyAdjective()
	{
	    wordPOS temp1;
	    wordPOS temp2;
	    if(wordPOSList.size() == 1)
	    {
	        temp1 = (wordPOS) wordPOSList.get(0);
	        adjList.add(new String(temp1.word));
	    }
	    for(int i = 0; i < wordPOSList.size() - 1; i++)
	    {
	        temp1 = (wordPOS)wordPOSList.get(i);
	        temp2 = (wordPOS)wordPOSList.get(i+1);
	        if(temp1.pos.equals("r") == false && temp2.pos.equals("a") == true)
	            adjList.add(new String(temp2.word));
	    }
	}

}