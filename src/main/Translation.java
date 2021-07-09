package main;

/**
 * The Enum that saves texts in 2 languages.
 */
public enum Translation {
	
	SITETEXTLABEL ("<html><h3>For more information visit</h3></html>", "<html><h3>Հավելյալ տեղեկության համար այցելել՝ </h3></html>"),
	ABOUT( "<html>"
			+ "<h2>Welcome to the Movie Finder application</h2>"
			+"<h3>You can search a movie by just typing it`s name in the label above. In order to filter the search, type the movie year (*optional) in the smaller label.</h3>"
			+"<h3>If the movie has a website, just click the \"Website\" link in the movie description page to see the details of the movie in web.</h3>"
			+"<h3>In Favourites page you can find your favourite selected movies. For adding a movie to favourites list just click the star in the movie description page or in search list.</h3>"
			+"<h3>You can easilly search a favourite movie by typing it`s name in the small label in \"Favourites\" page. Also you can refresh list by clicking \"Refresh\".</h3>"
		+ "</html>",
		
		 "<html>"
			+ "<h2>Բարի գալուստ ֆիլմերի որոնման ծրագիր</h2>"
			+"<h3>Դուք կարող եք որոնել ֆիլմեր ուղղակի գրելով դրա վերնագիրը վերևի տեքստային դաշտում։ Արդյունքները ֆիլտրելու համար հարկավոր է գրել ֆիլմի տարեթիվը փոքր տեքստային դաշտում (*ընտրովի)։</h3>"
			+"<h3>Եթե ֆիլմը ունի կայք, ողղակի սեղմեք \"Կայք\" -ի հղումը ֆիլմի նկարագրության էջում՝ ֆիլմի մասին ավելի մանրամասն տեղեկություն ստանալու համար։</h3>"
			+"<h3>Հավանած ֆիլմերի էջում դուք կգտնեք ձեր կողմից ընտրված ֆիլմերի ցանկը։ Ֆիլմը ցանկում ավելացնելու համար նկարագրության կամ ֆիլմերի ցանկի էջում սեղմեք աստղի վրա։</h3>"
			+"<h3>Դուք հեշտորեն կարող եք որոնում կատարել հավանած ֆիլմերի ցանկում՝ ֆիլմի անունը գրելով փոքր տեքստային դաշտում \"Հավանածները\" էջում։ Նաև դուք կարող եք թարմացնել ցանկը սեղմելով \"Թարմացնել\":</h3>"
		+ "</html>"),
	
	HOME("Home","Գլխավոր"),
	FAVOURITES("Favourites","Հավանածներ"),
	SEARCH("Search","Որոնել"),
	YEAR("Year","Տարեթիվ"),
	DETAILS("Details","Ավելին"),
	REFRESH("Refresh","Թարմացնել"),
	BACK("Back","Հետ"),
	WEBBUTTON("View in IMDb","Բացել IMDb-ում"),
	TITLE("Title","Վերնագիր"),
	RATED("Rated","Գնահատված"),
	RELEASED("Released","Թողարկված"),
	RUNTIME("Runtime","Ժանանակ"),
	GENRE("Genre","Ժանր"),
	DIRECTOR("Director","Ռեժիսոր"),
	LANGUAGE("Language","Լեզու"),
	COUNTRY("Country","Երկիր"),
	AWARDS("Awards","Մրցանակներ"),
	METASCORE("Metascore","Միավոր"),
	IMDBRATING("imdbRating","imdb Գնահատական"),
	IMDBVOTES("imdbVotes","imdb Քվեարկում"),
	TYPE("Type","Տեսակ"),
	BOXOFFICE("BoxOffice","Տոմսարկղ"),
	PRODUCTION("Production","Արտադրությունը"),
	PLOT("Plot","Սյուժե"),
	ACTORS("Actors","Դերասաններ"),
	WRITER("Writer","Սցենարի հեղինակ"),
	WEBSITE("Website","Կայք"),
	SEARCHBYMOVIENAME("Search by movie name","Փնտրել ըստ ֆիլմի անունի"),
	FILTERBYYEAR("Filter by year","Ֆիլտրել ըստ տարեթվի"),
	THELISTEMPTY("You haven't added any movie to this list","Դուք դեռ ոչ մի ֆիլմ չեք ավելացրել հավանածների ցուցակում"),
	SEARCHLISTEMPTY("We couldn't find any movie with the typed name","Մենք չկարողացանք գտնել որևէ ֆիլմ մուտքագրված վերնագրով");
	
	/** The English translation of the word. */
	private String english;
	
	/** The Armenian translation of the word. */
	private String armenian;
	
	/**
	 * Instantiates a new translation enum.
	 *
	 * @param english the English translation
	 * @param armenian the Armenian translation
	 */
	private Translation(String english, String armenian) {
		this.english = english;
		this.armenian = armenian;
	}
	
	/**
	 * Gets the translation from the data.
	 *
	 * @param language the given language to translate
	 * @return the translation
	 */
	public String getTranslation(String language) {
		switch (language) {
		case "English":
			return english;
		case "Armenian":
			return armenian;
		default:
			return english;
		}
	}
	
}
