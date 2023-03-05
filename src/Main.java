import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static final int MATCH_ID = 0;
    public static final int MATCH_SEASON = 1;
    public static final int MATCH_CITY = 2;
    public static final int MATCH_DATE = 3;
    public static final int MATCH_TEAM1 = 4;
    public static final int MATCH_TEAM2 = 5;
    public static final int MATCH_TOSS_WINNER = 6;
    public static final int MATCH_TOSS_DECISION = 7;
    public static final int MATCH_RESULT = 8;
    public static final int MATCH_DL_APPLIED = 9;
    public static final int MATCH_WINNER = 10;
    public static final int MATCH_WIN_BY_RUNS = 11;
    public static final int MATCH_WIN_BY_WICKETS = 12;
    public static final int MATCH_PLAYER_OF_MATCH = 13;
    public static final int MATCH_VENUE = 14;
    public static final int MATCH_UMPIRE_1 = 15;
    public static final int MATCH_UMPIRE_2 = 16;
    public static final int MATCH_UMPIRE_3 = 17;
    public static final int DELIVERY_ID = 0;
    public static final int DELIVERY_INNINGS = 1;
    public static final int DELIVERY_BATTING_TEAM = 2;
    public static final int DELIVERY_BOWLING_TEAM = 3;
    public static final int DELIVERY_OVER = 4;
    public static final int DELIVERY_BALL = 5;
    public static final int DELIVERY_BATSMAN = 6;
    public static final int DELIVERY_NON_STRIKER = 7;
    public static final int DELIVERY_BOWLER = 8;
    public static final int DELIVERY_SUPER_OVER = 9;
    public static final int DELIVERY_WIDE_RUNS = 10;
    public static final int DELIVERY_BYE_RUNS = 11;
    public static final int DELIVERY_LEG_BYE = 12;
    public static final int DELIVERY_NO_BALL = 13;
    public static final int DELIVERY_PENALTY_RUNS = 14;
    public static final int DELIVERY_BATSMAN_RUNS = 15;
    public static final int DELIVERY_EXTRA_RUNS = 16;
    public static final int DELIVERY_TOTAL_RUNS = 17;
    public static final int DELIVERY_PLAYER_DISMISSED = 18;
    public static final int DELIVERY_DISMISSAL_KIND = 19;
    public static final int DELIVERY_FIELDER = 20;

    public static void main(String[] args) {
        List<Match> matches = getMatchData();
        matches.remove(0);

        List<Delivery> deliveries = getDeliveryData();
        deliveries.remove(0);

        System.out.println("Number of matches played per year of all the years in IPL->");
        TreeMap<String, Integer> numberOfMatchesPlayed = matchesPlayedPerYear(matches);
        System.out.println(numberOfMatchesPlayed);
        System.out.println();

        System.out.println("Number of matches won of all teams over all the years of IPL->");
        TreeMap<String, Integer> numberOfMatchesWon = matchesWonInAllYears(matches);
        System.out.println(numberOfMatchesWon);
        System.out.println();

        System.out.println("For the year 2016 extra runs conceded per team->");
        TreeMap<String, Integer> extraRunsConceded = extraRunsConceded(deliveries);
        System.out.println(extraRunsConceded);
        System.out.println();

        System.out.println("For the year 2015, top economical bowler->");
        topEconomicalBowler(deliveries);
        System.out.println();

        System.out.println("in year 2008, list the teams that win the toss and the match as well and count such events also.");
        TreeMap<String, Integer> tossAndMatchWinnerCount = winnerOfTossAndMatch(matches);
        System.out.println(tossAndMatchWinnerCount);
        System.out.println();

        bhuvi(deliveries);
    }

    private static List<Match> getMatchData() {
        List<Match> matches = new ArrayList<>();
        try {
            BufferedReader sc = new BufferedReader(new FileReader("/home/umesh/IdeaProjects/IPL_Project/Data/matches.csv"));
            String line = "";
            String splitBy = ",";
            while ((line = sc.readLine()) != null) {
                String[] data = line.split(splitBy);
                Match match = new Match();
                match.setId(data[MATCH_ID]);
                match.setSeason(data[MATCH_SEASON]);
                match.setCity(data[MATCH_CITY]);
                match.setDate(data[MATCH_DATE]);
                match.setTeam1(data[MATCH_TEAM1]);
                match.setTeam2(data[MATCH_TEAM2]);
                match.setToss_winner(data[MATCH_TOSS_WINNER]);
                match.setToss_decision(data[MATCH_TOSS_DECISION]);
                match.setResult(data[MATCH_RESULT]);
                match.setDl_applied(data[MATCH_DL_APPLIED]);
                match.setWinner(data[MATCH_WINNER]);
                match.setWin_by_runs(data[MATCH_WIN_BY_RUNS]);
                match.setWin_by_wickets(data[MATCH_WIN_BY_WICKETS]);
                match.setPlayer_of_the_match(data[MATCH_PLAYER_OF_MATCH]);
                match.setVenue(data[MATCH_VENUE]);

                if (data.length > (MATCH_VENUE + 1)) {
                    match.setUmpire1(data[MATCH_UMPIRE_1]);
                }

                if (data.length > (MATCH_VENUE + 2)) {
                    match.setUmpire2(data[MATCH_UMPIRE_2]);
                }

                if (data.length > (MATCH_VENUE + 3)) {
                    match.setUmpire3(data[MATCH_UMPIRE_3]);
                }
                matches.add(match);
            }
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    private static List<Delivery> getDeliveryData() {
        List<Delivery> deliveries = new ArrayList<>();

        try {
            BufferedReader sc = new BufferedReader(new FileReader("/home/umesh/IdeaProjects/IPL_Project/Data/deliveries.csv"));
            String line = "";
            String splitBy = ",";
            while ((line = sc.readLine()) != null) {
                String[] data = line.split(splitBy);
                Delivery delivery = new Delivery();
                delivery.setMatch_id(data[DELIVERY_ID]);
                delivery.setInnings(data[DELIVERY_INNINGS]);
                delivery.setBatting_team(data[DELIVERY_BATTING_TEAM]);
                delivery.setBowling_team(data[DELIVERY_BOWLING_TEAM]);
                delivery.setOver(data[DELIVERY_OVER]);
                delivery.setBall(data[DELIVERY_BALL]);
                delivery.setBatsman(data[DELIVERY_BATSMAN]);
                delivery.setNon_striker(data[DELIVERY_NON_STRIKER]);
                delivery.setBowler(data[DELIVERY_BOWLER]);
                delivery.setIs_super_over(data[DELIVERY_SUPER_OVER]);
                delivery.setWide_runs(data[DELIVERY_WIDE_RUNS]);
                delivery.setBye_runs(data[DELIVERY_BYE_RUNS]);
                delivery.setLegbye_runs(data[DELIVERY_LEG_BYE]);
                delivery.setNoball_runs(data[DELIVERY_NO_BALL]);
                delivery.setPenalty_runs(data[DELIVERY_PENALTY_RUNS]);
                delivery.setBatsman(data[Main.DELIVERY_BATSMAN_RUNS]);
                delivery.setExtra_runs(data[DELIVERY_EXTRA_RUNS]);
                delivery.setTotal_runs(data[DELIVERY_TOTAL_RUNS]);

                if (data.length > (DELIVERY_TOTAL_RUNS + 1)) {
                    delivery.setPlayer_dismissed(data[DELIVERY_PLAYER_DISMISSED]);
                }

                if (data.length > (DELIVERY_TOTAL_RUNS + 2)) {
                    delivery.setDismissal_kind(data[DELIVERY_DISMISSAL_KIND]);
                }

                if (data.length > DELIVERY_TOTAL_RUNS + 3) {
                    delivery.setFielder(data[DELIVERY_FIELDER]);
                }

                deliveries.add(delivery);
            }

            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    private static TreeMap<String, Integer> matchesPlayedPerYear(List<Match> match) {
        TreeMap<String, Integer> yearVsMatchesPlayed = new TreeMap<>();
        for (Match matchData : match) {
            String year = matchData.getSeason();
            if (yearVsMatchesPlayed.containsKey(year)) {
                int val = yearVsMatchesPlayed.get(year) + 1;
                yearVsMatchesPlayed.put(year, val);
            } else {
                yearVsMatchesPlayed.put(year, 1);
            }
        }
        return yearVsMatchesPlayed;
    }

    private static TreeMap<String, Integer> matchesWonInAllYears(List<Match> match) {
        TreeMap<String, Integer> teamVsYear = new TreeMap<>();
        for (Match matchData : match) {
            String winningTeam = matchData.getWinner();
            if (winningTeam.equals("")) {
                winningTeam = winningTeam + "no result";
            }
            if (teamVsYear.containsKey(winningTeam)) {
                int val = teamVsYear.get(winningTeam) + 1;
                teamVsYear.put(winningTeam, val);
            } else {
                teamVsYear.put(winningTeam, 1);
            }
        }
        return teamVsYear;
    }

    private static TreeMap<String, Integer> extraRunsConceded(List<Delivery> delivery) {
        TreeMap<String, Integer> bowlerVsExtraRuns = new TreeMap<>();
        for (Delivery deliveryData : delivery) {
            int id = Integer.parseInt(deliveryData.getMatch_id());
            if (id >= 577 && id <= 636) {
                String team = deliveryData.getBowling_team();
                int extraRuns = Integer.parseInt(deliveryData.getExtra_runs());
                if (bowlerVsExtraRuns.containsKey(team)) {
                    int val = bowlerVsExtraRuns.get(team) + extraRuns;
                    bowlerVsExtraRuns.put(team, val);
                } else {
                    bowlerVsExtraRuns.put(team, extraRuns);
                }
            }
        }
        return bowlerVsExtraRuns;
    }

    private static void topEconomicalBowler(List<Delivery> delivery) {
        TreeMap<String, Integer> bowlerVsBalls = new TreeMap<>();
        for (Delivery deliveryData : delivery) {
            int id = Integer.parseInt(deliveryData.getMatch_id());
            if (id >= 518 && id <= 576) {
                String bow = deliveryData.getBowler();
                int ball = Integer.parseInt(deliveryData.getBall());

                if (bowlerVsBalls.containsKey(bow)) {
                    int val = bowlerVsBalls.get(bow) + 1;
                    bowlerVsBalls.put(bow, val);
                } else {
                    bowlerVsBalls.put(bow, 1);
                }
            }
        }

        ArrayList<String> bowlers = new ArrayList<>();
        TreeMap<String, Float> bowlerVsRuns = new TreeMap<>();
        for (Delivery deliveryData : delivery) {
            int id = Integer.parseInt(deliveryData.getMatch_id());
            if (id >= 518 && id <= 576) {
                String bow = deliveryData.getBowler();
                float totalRuns = Float.parseFloat(deliveryData.getTotal_runs());
                if (bowlerVsRuns.containsKey(bow)) {
                    float val = bowlerVsRuns.get(bow) + Float.parseFloat(deliveryData.getTotal_runs());
                    bowlerVsRuns.put(bow, val);
                } else {
                    bowlerVsRuns.put(bow, Float.parseFloat(deliveryData.getTotal_runs()));
                    bowlers.add(bow);
                }
            }
        }

        HashMap<String, Float> bowlerVsEconomy = new HashMap<>();
        bowlers.forEach(key -> {
            int balls = bowlerVsBalls.get(key);
            float over = balls / (6.0f) + ((float) (balls % 6)) / 6;
            float economy = bowlerVsRuns.get(key) / over;
            bowlerVsEconomy.put(key, economy);
        });

        float topEconomy = Collections.min(bowlerVsEconomy.values());
        String topEconomicalBowler = "";
        Iterator<Map.Entry<String, Float>> it = bowlerVsEconomy.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Float> entry = it.next();
            if (entry.getValue() == topEconomy) {
                topEconomicalBowler = entry.getKey();
            }
        }
        System.out.println("Top Economical Bowler: " + topEconomicalBowler);
        System.out.println("Economy: " + String.format("%.2f", topEconomy));
    }

    private static TreeMap<String, Integer> winnerOfTossAndMatch(List<Match> match) {
        ArrayList<String> tossMatchWinner = new ArrayList<>();
        for (Match matchData : match) {
            int id = Integer.parseInt(matchData.getId());
            if (id >= 60 && id <= 117) {
                String tossWinner = matchData.getToss_winner();
                String matchWinner = matchData.getWinner();
                if (tossWinner.equals(matchWinner)) {
                    tossMatchWinner.add(matchWinner);
                }
            }
        }

        TreeMap<String, Integer> teamVsWinCount = new TreeMap<>();
        for (int i = 0; i < tossMatchWinner.size(); i++) {
            String team = tossMatchWinner.get(i);
            if (teamVsWinCount.containsKey(team)) {
                int val = teamVsWinCount.get(team) + 1;
                teamVsWinCount.put(team, val);
            } else {
                teamVsWinCount.put(team, 1);
            }
        }
        return teamVsWinCount;
    }

    public static void bhuvi(List<Delivery> delivery){
        int count = 0;
        for(int i=0; i<delivery.size();i++){
            String bowler = delivery.get(i).getBowler();
            if(bowler.equals("B Kumar")){
                if(delivery.get(i).getPlayer_dismissed() != null){
                    count++;
                }
            }
        }
        System.out.println("Bhuvi " +count);

    }
}
