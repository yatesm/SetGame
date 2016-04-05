package com.energyhub.interview.setgame;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by JYMONTE on 4/5/2016.
 */
public class PileOfCards {
    public final List<Card> pile;

    public PileOfCards() {
        pile = new ArrayList<Card>();
    }

    public PileOfCards(int numCards) {
        pile = new ArrayList<Card>(numCards);
        for(int i = 0; i < numCards; i++)
            pile.add(CardFactory.drawCard());
    }

    public List<List<Card>> getCombinations(List<Card> values, int size) {
        if (this.pile.size() == 0) {
            return Collections.singletonList(Collections.<Card> emptyList());
        }
        if(values.isEmpty()) {
            return Collections.emptyList();
        }
        List<List<Card>> combination = new ArrayList<List<Card>>();

        Card card = values.iterator().next();

        List<Card> subset = new ArrayList<Card>(values);
        subset.remove(card);

        List<List<Card>> subsetCombination = getCombinations(subset, size - 1);
        for (List<Card> set : subsetCombination) {
            List<Card> newSet = new ArrayList<Card>(set);
            newSet.add(0, card);
            combination.add(newSet);
        }

        combination.addAll(getCombinations(subset, size));

        return combination;
    }
}
