package wordnet;

import java.util.*;

class variableScore
{
    public scoredAdverbAdjectivePair advAdjPairInstance;
    ArrayList <Double> scoreList = new ArrayList <Double> ();
    public ArrayList compute(ArrayList a)
    {
        for(int i = 0; i < a.size(); i++)
        {
            advAdjPairInstance = (scoredAdverbAdjectivePair)a.get(i);
            if(advAdjPairInstance.adverbType == "AFF" || advAdjPairInstance.adverbType == "STRONG")
            {
                scoreList.add(AFF_STRONG(advAdjPairInstance));
            }
            if(advAdjPairInstance.adverbType == "WEAK" || advAdjPairInstance.adverbType == "DOUBT")
            {
                scoreList.add(WEAK_DOUBT(advAdjPairInstance));
            }
        }
        return scoreList;
    }

    public double AFF_STRONG(scoredAdverbAdjectivePair instance)
    {
        double score = 0;
        if(instance.adjectiveScore > 0)
        {
            score = instance.adjectiveScore + (1 - (instance.adjectiveScore)) * instance.adverbScore;
        }
        else if(instance.adjectiveScore < 0)
        {
            score = instance.adjectiveScore - (1 - (instance.adjectiveScore)) * instance.adverbScore;
        }
        return score;
    }
    
    public double WEAK_DOUBT(scoredAdverbAdjectivePair instance)
    {
        double score = 0;
        if(instance.adjectiveScore > 0)
        {
            score = instance.adjectiveScore - (1 - (instance.adjectiveScore)) * instance.adverbScore;
        }
        else if(instance.adjectiveScore < 0)
        {
            score = instance.adjectiveScore + (1 - (instance.adjectiveScore)) * instance.adverbScore;
        }
        return score;
    }
}