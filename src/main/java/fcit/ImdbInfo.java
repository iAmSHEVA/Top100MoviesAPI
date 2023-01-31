package fcit;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ImdbInfo {
    private int rank;
    private String rating;
    private String title;
    private int year;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String  getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("rank")
    private void unpackNestedR(Map<String, Object> rank) {
        this.rank = (int) rank.get("rank");
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("title")
    private void unpackNestedT(Map<String, Object> title) {
        this.title = (String) title.get("title");
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("year")
    private void unpackNestedY(Map<String, Object> year) {
        this.year = (int) year.get("year");
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("rating")
    private void unpackNestedRate(Map<String, Object> rating) {
        this.rating = (String) rating.get("rating");
    }

    public String toString() {
        return "Rank: " + rank +
                "\nTitle: " + title.replace("\"","") +
                "\nYear: " + year +
                "\nRating: " + rating.replace("\"","");
    }
}
