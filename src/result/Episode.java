package result;

/**
 * The Class holds an episode information.
 */
public class Episode extends Result{
	private String Season;
	private String Episode;
	private String seriesID;
	
	public String getSeason() {
		return Season;
	}
	public void setSeason(String season) {
		Season = season;
	}
	public String getEpisode() {
		return Episode;
	}
	public void setEpisode(String episode) {
		Episode = episode;
	}
	public String getSeriesID() {
		return seriesID;
	}
	public void setSeriesID(String seriesID) {
		this.seriesID = seriesID;
	}
}
