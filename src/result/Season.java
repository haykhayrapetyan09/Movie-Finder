package result;

/**
 * The Class holds a series season information.
 */
public class Season {
	private String Title;
	private String totalSeasons;
	private String Season;
	private Episode[] Episodes;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getTotalSeasons() {
		return totalSeasons;
	}
	public void setTotalSeasons(String totalSeasons) {
		this.totalSeasons = totalSeasons;
	}
	public String getSeason() {
		return Season;
	}
	public void setSeason(String season) {
		Season = season;
	}
	public Episode[] getEpisodes() {
		return Episodes;
	}
	public void setEpisodes(Episode[] episodes) {
		Episodes = episodes;
	}
}
