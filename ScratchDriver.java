package com.energyhub.interview.setgame;

import java.util.List;

/**
 * Created by JYMONTE on 4/5/2016.
 */
public class ScratchDriver {

    public static void main(String [] args) {
        PileOfCards pile = new PileOfCards(10);
        getCombinations(pile);

    }
    public static void getCombinations(PileOfCards testPile) {
        List<List<Card>> list = testPile.getCombinations(testPile.pile, testPile.pile.size());
        System.out.println(list.size() + " permutations found");
        for (List<Card> perm : list) {
            for (int i = 0; i < perm.size(); i++) {
                System.out.println(perm.get(i));
            }
            System.out.println("--------------------");
        }
    }
}
