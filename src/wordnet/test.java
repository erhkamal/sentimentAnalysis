package wordnet;

import rita.wordnet.RiWordnet;

public class test
{
    public static void main(String[] args)
    {
       RiWordnet wordnet = new RiWordnet();
       String a[] = wordnet.getPos("very");
       String b[] = wordnet.getAntonyms("love", "a");
       for(int i=0; i < b.length; i++)
           System.out.println(b[i]);
    }
}
