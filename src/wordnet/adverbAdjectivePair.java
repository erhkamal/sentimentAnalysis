package wordnet;

public class adverbAdjectivePair
{
    String adverb;
    String adjective;

    adverbAdjectivePair(String adverb,String adjective)
    {
        this.adverb = adverb;
        this.adjective = adjective;
    }

    public String toString()
    {
    	return this.adverb + this.adjective;
    }
}