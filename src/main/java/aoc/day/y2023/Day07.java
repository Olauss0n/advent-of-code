package aoc.day.y2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import aoc.util.Reader;

public class Day07 {

    public static void run() {
        System.out.print("For part one: ");
        runPartOne();
        System.out.print("For part two: ");
        runPartTwo();
    }

    private static void runPartOne() {
        ArrayList<HandPartOne> hands = new ArrayList<>(Reader.readInputAsList("y2023", "07").stream()
                .map(row -> new HandPartOne(
                        Arrays.asList(row.split(" ")).get(0),
                        Integer.parseInt(Arrays.asList(row.split(" ")).get(1))))
                .toList());

        Collections.sort(hands);

        ArrayList<Integer> totalWinnings = new ArrayList<>();

        for (int i = 0; i < hands.size(); i++) {
            HandPartOne hand = hands.get(i);
            Integer winning = Math.multiplyExact(hand.bid, i + 1);
            totalWinnings.add(winning);
        }
        totalWinnings.stream().reduce(Integer::sum).ifPresent(System.out::println);
    }

    private static void runPartTwo() {
        ArrayList<HandPartTwo> hands = new ArrayList<>(Reader.readInputAsList("y2023", "07").stream()
                .map(row -> new HandPartTwo(
                        Arrays.asList(row.split(" ")).get(0),
                        Integer.parseInt(Arrays.asList(row.split(" ")).get(1))))
                .toList());

        Collections.sort(hands);

        ArrayList<Integer> totalWinnings = new ArrayList<>();

        for (int i = 0; i < hands.size(); i++) {
            HandPartTwo hand = hands.get(i);
            Integer winning = Math.multiplyExact(hand.bid, i + 1);
            totalWinnings.add(winning);
        }
        totalWinnings.stream().reduce(Integer::sum).ifPresent(System.out::println);
    }

    private static class HandPartOne implements Comparable<HandPartOne> {

        private final String cards;
        private final Integer bid;
        private Type type;

        public HandPartOne(String cards, Integer bid) {
            this.cards = cards;
            this.bid = bid;
            calculateType();
        }

        public String toString() {
            return "Hand{cards=" + cards + ", bid=" + bid + ", type=" + type.name + "}";
        }

        @Override
        public int compareTo(HandPartOne otherHand) {
            if (type.value > otherHand.type.value) {
                return 1;
            } else if (type.value < otherHand.type.value) {
                return -1;
            }

            HashMap<Character, Integer> rankings = new HashMap<>();

            // 2-9 maps to 1-8
            for (int number = 2; number <= 9; number++) {
                rankings.put(Character.forDigit(number, 10), number - 1);
            }
            // A, K, Q, J, T maps to 13-9
            rankings.put('T', 9);
            rankings.put('J', 10);
            rankings.put('Q', 11);
            rankings.put('K', 12);
            rankings.put('A', 13);

            for (int index = 0; index < cards.length(); index++) {
                Integer thisCardRank = rankings.get(cards.charAt(index));
                Integer otherCardRank = rankings.get(otherHand.cards.charAt(index));

                if (thisCardRank > otherCardRank) {
                    return 1;
                } else if (thisCardRank < otherCardRank) {
                    return -1;
                }
            }

            return 0;
        }

        private void calculateType() {
            HashMap<Character, Integer> cardCount = createCardsMap();
            Type currentType = Type.HIGH_CARD;
            for (Map.Entry<Character, Integer> entry : cardCount.entrySet()) {
                if (entry.getValue() == 5) {
                    currentType = Type.FIVE_OF_A_KIND;
                } else if (entry.getValue() == 4) {
                    currentType = Type.FOUR_OF_A_KIND;
                } else if (entry.getValue() == 3) {
                    if (currentType == Type.ONE_PAIR) {
                        currentType = Type.FULL_HOUSE;
                    } else {
                        currentType = Type.THREE_OF_A_KIND;
                    }
                } else if (entry.getValue() == 2) {
                    if (currentType == Type.THREE_OF_A_KIND) {
                        currentType = Type.FULL_HOUSE;
                    } else if (currentType == Type.ONE_PAIR) {
                        currentType = Type.TWO_PAIR;
                    } else {
                        currentType = Type.ONE_PAIR;
                    }
                }
            }
            this.type = currentType;
        }

        private HashMap<Character, Integer> createCardsMap() {
            HashMap<Character, Integer> cardCount = new HashMap<>();
            for (char card : cards.toCharArray()) {
                cardCount.put(card, cardCount.getOrDefault(card, 0) + 1);
            }
            return cardCount;
        }
    }

    private static class HandPartTwo implements Comparable<HandPartTwo> {

        private final String cards;
        private final Integer bid;
        private Type type;

        public HandPartTwo(String cards, Integer bid) {
            this.cards = cards;
            this.bid = bid;
            calculateType();
        }

        public String toString() {
            return "Hand{cards=" + cards + ", bid=" + bid + ", type=" + type.name + "}";
        }

        @Override
        public int compareTo(HandPartTwo otherHand) {
            if (type.value > otherHand.type.value) {
                return 1;
            } else if (type.value < otherHand.type.value) {
                return -1;
            }

            HashMap<Character, Integer> rankings = new HashMap<>();

            // J maps to -1
            rankings.put('J', -1);

            // 2-9 maps to 1-8
            for (int number = 2; number <= 9; number++) {
                rankings.put(Character.forDigit(number, 10), number - 1);
            }
            // A, K, Q, T maps to 13-11, 9
            rankings.put('T', 9);
            rankings.put('Q', 11);
            rankings.put('K', 12);
            rankings.put('A', 13);

            for (int index = 0; index < cards.length(); index++) {
                Integer thisCardRank = rankings.get(cards.charAt(index));
                Integer otherCardRank = rankings.get(otherHand.cards.charAt(index));

                if (thisCardRank > otherCardRank) {
                    return 1;
                } else if (thisCardRank < otherCardRank) {
                    return -1;
                }
            }

            return 0;
        }

        private void calculateType() {
            HashMap<Character, Integer> cardCount = createCardsMap();
            Type currentType = Type.HIGH_CARD;
            for (Map.Entry<Character, Integer> entry : cardCount.entrySet()) {
                if (entry.getKey() == 'J') {
                    continue;
                }
                if (entry.getValue() == 5) {
                    currentType = Type.FIVE_OF_A_KIND;
                } else if (entry.getValue() == 4) {
                    currentType = Type.FOUR_OF_A_KIND;
                } else if (entry.getValue() == 3) {
                    if (currentType == Type.ONE_PAIR) {
                        currentType = Type.FULL_HOUSE;
                    } else {
                        currentType = Type.THREE_OF_A_KIND;
                    }
                } else if (entry.getValue() == 2) {
                    if (currentType == Type.THREE_OF_A_KIND) {
                        currentType = Type.FULL_HOUSE;
                    } else if (currentType == Type.ONE_PAIR) {
                        currentType = Type.TWO_PAIR;
                    } else {
                        currentType = Type.ONE_PAIR;
                    }
                }
            }
            int numberOfJs = cardCount.getOrDefault('J', 0);

            if (currentType == Type.HIGH_CARD) {
                if (numberOfJs == 1) {
                    currentType = Type.ONE_PAIR;
                } else if (numberOfJs == 2) {
                    currentType = Type.THREE_OF_A_KIND;
                } else if (numberOfJs == 3) {
                    currentType = Type.FOUR_OF_A_KIND;
                } else if (numberOfJs == 4 || numberOfJs == 5) {
                    currentType = Type.FIVE_OF_A_KIND;
                }
            } else if (currentType == Type.ONE_PAIR) {
                if (numberOfJs == 1) {
                    currentType = Type.THREE_OF_A_KIND;
                } else if (numberOfJs == 2) {
                    currentType = Type.FOUR_OF_A_KIND;
                } else if (numberOfJs == 3) {
                    currentType = Type.FIVE_OF_A_KIND;
                }
            } else if (currentType == Type.TWO_PAIR) {
                if (numberOfJs == 1) {
                    currentType = Type.FULL_HOUSE;
                }
            } else if (currentType == Type.THREE_OF_A_KIND) {
                if (numberOfJs == 1) {
                    currentType = Type.FOUR_OF_A_KIND;
                } else if (numberOfJs == 2) {
                    currentType = Type.FIVE_OF_A_KIND;
                }
            } else if (currentType == Type.FOUR_OF_A_KIND) {
                if (numberOfJs == 1) {
                    currentType = Type.FIVE_OF_A_KIND;
                }
            }

            this.type = currentType;
        }

        private HashMap<Character, Integer> createCardsMap() {
            HashMap<Character, Integer> cardCount = new HashMap<>();
            for (char card : cards.toCharArray()) {
                cardCount.put(card, cardCount.getOrDefault(card, 0) + 1);
            }
            return cardCount;
        }
    }

    private enum Type {
        HIGH_CARD("High Card", 0),
        ONE_PAIR("One Pair", 1),
        TWO_PAIR("Two Pair", 2),
        THREE_OF_A_KIND("Three of a Kind", 3),
        FULL_HOUSE("Full House", 4),
        FOUR_OF_A_KIND("Four of a Kind", 5),
        FIVE_OF_A_KIND("Five of a Kind", 6);

        private final String name;
        private final Integer value;

        Type(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public String toString() {
            return "Type{name=" + name + ", value=" + value + "}";
        }
    }
}
