import java.util.*;

class Card {
    private String symbol;
    private String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "Card{" +
                "symbol='" + symbol + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

public class CardCollection {
    private Collection<Card> cards;

    public CardCollection() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> findCardsBySymbol(String symbol) {
        List<Card> result = new ArrayList<>();
        for (Card card : cards) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                result.add(card);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        CardCollection cardCollection = new CardCollection();

        // Adding some cards to the collection
        cardCollection.addCard(new Card("Hearts", "Ace"));
        cardCollection.addCard(new Card("Hearts", "2"));
        cardCollection.addCard(new Card("Spades", "King"));
        cardCollection.addCard(new Card("Diamonds", "Queen"));

        // Finding all cards with the symbol "Hearts"
        List<Card> heartsCards = cardCollection.findCardsBySymbol("Hearts");
        System.out.println("Cards with symbol 'Hearts': " + heartsCards);
    }
}
