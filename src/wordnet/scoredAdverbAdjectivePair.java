package wordnet;

public class scoredAdverbAdjectivePair
{
    String adverb;
    String adjective;
    double adverbScore;
    double adjectiveScore;
    String adverbType;

    scoredAdverbAdjectivePair(String adverb, String adjective, double adverbScore, double adjectiveScore, String adverbType)
    {
        this.adverb=adverb;
    	this.adjective=adjective;
        this.adverbScore=adverbScore;
    	this.adjectiveScore=adjectiveScore;
        this.adverbType=adverbType;
    }

}
