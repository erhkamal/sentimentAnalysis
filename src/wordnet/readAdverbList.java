package wordnet;

import java.io.*;
import java.util.*;

public class readAdverbList
{
	ArrayList <adverb> adverbScoreList = new ArrayList <adverb>();
    adverb adverbObject = new adverb();
    public ArrayList <adverb> extract() throws Exception
    {
        int count=1;
        FileReader fileReader = new FileReader("C:\\Users\\Kamal\\Desktop\\adverbs.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = new String();
        StringTokenizer tokenizer;
        while((line=bufferedReader.readLine())!=null)
        {
            tokenizer = new StringTokenizer(line," ");
            count=1;
            while(tokenizer.hasMoreTokens())
            {
                if(count == 1)
                {
                    adverbObject.adverb = tokenizer.nextToken();
                }
                if(count == 2)
                {
                    adverbObject.score = Double.valueOf(tokenizer.nextToken());
                }
                if(count == 3)
                {
                    adverbObject.type = tokenizer.nextToken(line);
                }
                count++;
            }
            adverbScoreList.add(adverbObject);
        }
        bufferedReader.close();
        return adverbScoreList;
    }
}
