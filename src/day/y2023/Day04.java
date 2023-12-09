package day.y2023;

import util.Reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day04 {

    public static void run() {
        System.out.print("For part one: ");
        runPartOne();
        System.out.print("For part two: ");
        runPartTwo();
    }

    private static void runPartOne() {
        List<String> input = Reader.readFileAsList("y2023", "04");

        List<Card> cards = input.stream().map(Card::new).toList();

        cards.stream().map(card -> card.cardPoints).reduce(Integer::sum).ifPresent(System.out::println);
    }

    private static void runPartTwo() {
        List<String> input = Reader.readFileAsList("y2023", "04");

        List<Card> cards = input.stream().map(Card::new).toList();

        List<Card> cardPile = new ArrayList<>();

        for (Card card : cards) {
            handleCard(card, cards, cardPile);
        }
        System.out.println(cardPile.size());
    }

    private static void runPartOneWithOutCardClass() {
        List<String> cards = Reader.readFileAsList("y2023", "04");

        List<Integer> cardPoints = new ArrayList<>();

        for (String card : cards) {
            List<String> cardList = Arrays.asList(card.split(":|\\|"));
            String id = cardList.get(0).replace("Card", "").strip();
            List<String> winningNumbers = Arrays.asList(cardList.get(1).strip().split(" +"));
            List<String> yourNumbers = Arrays.asList(cardList.get(2).strip().split(" +"));

            List<String> cardWinningNumbers = winningNumbers.stream().filter(yourNumbers::contains).toList();

            if (!cardWinningNumbers.isEmpty()) {
                /*
                Antal rätt  -> Antal poäng
                0           -> 0            0
                1           -> 1            2^0
                2           -> 2            2^1
                3           -> 4            2^2
                4           -> 8            2^3
                n           ->              2^n-1
                 */
                int points = (int) Math.pow(2, cardWinningNumbers.size() - 1);
                cardPoints.add(points);
            }
        }
        cardPoints.stream().reduce(Integer::sum).ifPresent(System.out::println);
    }

    private static void handleCard(Card card, List<Card> cards, List<Card> cardPile) {
        cardPile.add(card);

        for (int i = card.cardId; i < card.cardWinningNumbers.size() + card.cardId; i++) {
            handleCard(cards.get(i), cards, cardPile);
        }
    }

    private static final class Card {
        int cardId;
        List<String> winningNumbers;
        List<String> yourNumbers;
        List<String> cardWinningNumbers;
        int cardPoints = 0;

        private Card(String input) {
            List<String> cardInfo = Arrays.asList(input.split(":|\\|"));
            this.cardId = Integer.parseInt(cardInfo.get(0).replace("Card", "").strip());
            this.winningNumbers = Arrays.asList(cardInfo.get(1).strip().split(" +"));
            this.yourNumbers = Arrays.asList(cardInfo.get(2).strip().split(" +"));
            this.cardWinningNumbers = winningNumbers.stream().filter(yourNumbers::contains).toList();

            if (!cardWinningNumbers.isEmpty()) {
                cardPoints = (int) Math.pow(2, cardWinningNumbers.size() - 1);
            }
        }
    }
}