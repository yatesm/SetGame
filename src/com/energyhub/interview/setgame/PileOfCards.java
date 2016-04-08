package com.energyhub.interview.setgame;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
  * PileOfCards class.  Represents a pile of N cards, specified by the called constructor.
 */
public class PileOfCards {
    private final List<Card> pile;
    private final Set<String> descriptors;

    /**
     * Constructor.  Constructs a pile of cards containing numCards randomly generated cards.
     *
     * @param numCards number of cards to be dealt in total.
     */
    public PileOfCards(int numCards) {
        descriptors = CardFactory.getDescriptorKeySet();
        pile = new ArrayList<Card>(numCards);
        for(int i = 0; i < numCards; i++)
            pile.add(CardFactory.drawCard());
    }


    /**
     *
     * @return Returns List\<Hand\> which represents the power set of the pile of cards.
     */
    public List<Hand> getHands(int handSize) {
        List<Hand> hands = new ArrayList<Hand>();
        //Computer the size of the resulting power set
        int powerSetSize = (int) Math.pow(2.0, pile.size());
        //Iterate over each subset of the power set
        for(int counter = 0; counter < powerSetSize; counter++) {
            //Create new hand (subset)
            hands.add(new Hand());
            //Iterate over each card in the pile, determining if it is part of the current subset
            for(int i = 0; i < pile.size(); i++) {
                //Bitwise magic to determine if a particular card is in power set.
                //The counter's binary representation and'd with 1, bit shift by the index of the current card we're
                //encodes an looking an inclusion/exclusion list for each card in the pile.
                if ((counter & (1 << i)) != 0) {
                    hands.get(counter).addCard(pile.get(i));
                }
            }
        }
        if(handSize > 0)
            pruneHands(hands, handSize);
        return hands;
    }

    /**
     * Method takes a list of Hand objects and removes any Hand object with size (number of cards) not equal to
     * handsize.
     *
     * @param hands List of Hands to prune
     * @param handSize Desired number of cards in hands
     */
    public void pruneHands(List<Hand> hands, int handSize) {
        //Prune the power set to exclude all hands with size != size
        Iterator<Hand> it = hands.iterator();
        while(it.hasNext())
            if(it.next().size() != handSize)
                it.remove();

    }
}
