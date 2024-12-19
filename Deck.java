import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cards;
    private List<Card> discardedCards;


    public Deck() {
        cards = new ArrayList<>();
        discardedCards = new ArrayList<>();
        //создание колоды
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            return null; // Колода пуста
        }
        return cards.remove(0);
    }

    public boolean returnCard(Card card) {
        if (cards.contains(card) || discardedCards.contains(card)) {
            return false; // Карта уже в колоде или сбросе
        }
        discardedCards.add(card);
        return true;

    }

    public void reset(){
        cards.addAll(discardedCards);
        discardedCards.clear();
        shuffle();
    }

    public int size() {
        return cards.size();
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        List<Card> hand = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Card card = deck.dealCard();
            if (card != null) {
                hand.add(card);
                System.out.println("Выдана карта: " + card);
            } else {
                System.out.println("Колода пуста!");
                break;
            }
        }
        System.out.println("В руке: " + hand);

        //Возврат карты в колоду
        Card cardToReturn = hand.get(0);
        if(deck.returnCard(cardToReturn)){
            System.out.println("Карта " + cardToReturn + " успешно возвращена в сброс");
        } else {
            System.out.println("Нельзя вернуть карту " + cardToReturn);
        }
        deck.reset();
        System.out.println("Колода перетасована, карты из сброса перемещены в колоду. Размер колоды: " + deck.size());
    }

    static class Card {
        String suit;
        String rank;

        public Card(String suit, String rank) {
            this.suit = suit;
            this.rank = rank;
        }

        @Override
        public String toString() {
            return rank + " of " + suit;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Card card = (Card) obj;
            return suit.equals(card.suit) && rank.equals(card.rank);
        }

        @Override
        public int hashCode() {
            return suit.hashCode() * 31 + rank.hashCode();
        }
    }
}

