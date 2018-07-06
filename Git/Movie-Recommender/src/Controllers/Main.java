package Controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import Models.User;
import Utils.Serializer;
import Utils.XMLSerializer;
import Models.Movie;
import Models.Ratings;

public class Main {
	
	public MovieAPI movieAPI;
	private User user;
	private Movie movie;
	private Ratings rating;

	public Main() throws Exception{	
		File datastore = new File("./Files/dataStore.xml");
		Serializer serializer = new XMLSerializer(datastore);
		
		movieAPI = new MovieAPI(serializer);
		if(datastore.isFile()){
			movieAPI.load();
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		Main main = new Main();
		main.movieAPI.initalLoad();
		Shell shell = ShellFactory.createConsoleShell("MR", "Welcome to Movie Recommender - ?help for instructions", main);
		shell.commandLoop();
	    main.movieAPI.store();
	}
	

	
	
	
	public String load() throws Exception{
		movieAPI.initalLoad();
		return "Data Loaded";
	}
	
	
	
	@Command(description="Get all users details")
	  public  void getAllUsers ()
	  {
	   
	    System.out.println(movieAPI.getUsers());
	  }
	
	@Command(description = "Add-User")
	public String addUser ( long UserId, String firstName,
										 String lastName,
										 String age,
										 String gender,
										 String occupation){
		user = movieAPI.addUser(UserId, firstName, lastName, gender, age, occupation);
		return "\nUSER: " + user.firstName + " added.";
	}
	
	
	@Command(description = "delete User")
	public void removeUser(long userID) throws Exception{	
		User user = movieAPI.getUser(userID);
		movieAPI.removeUser(userID);
		System.out.println("\nUser: " + user.firstName + " removed.");	
	}
	
	
	@Command(description = "Get Specicfic User")
	public String getUser( long userID){
		user = movieAPI.getUser(userID);
		return user.toString();
	}
	
	
	@Command(description = "get all movies")
	public void getMovies(){
		Collection<Movie>movies = movieAPI.getMovies();
		System.out.println(movies);
	}
	
	
	@Command(description = "add movie")
	public String addMovie(String title,
									 String year,
									String url){
		
		movie = movieAPI.addMovie(title, year, url);
		return "\nMovie: " + movie.title + " added";
	}
	
	
	@Command(description = "delete movie")
	public void removeMovie(long movieID) throws Exception{
		Movie movie = movieAPI.getMovie(movieID);
		movieAPI.removeMovie(movie);
		System.out.println("\nMovie: " + movie.title + " removed.");
	}
	
	
	@Command(description = "get specific movie")
	public String getMovie(long movieID){
		movie = movieAPI.getMovie(movieID);
		return movie.toString();	
	}
	
	
@Command(description = "add rating")
	public String addRating(long userID, long movieID, double rating){
		movieAPI.addRating(userID, movieID, rating);
		movie = movieAPI.getMovie(movieID);
		user = movieAPI.getUser(userID);
		return "Movie " + movie.title + " Rated " + rating + " by " + user.firstName;
	}
	
	
	@Command(description = "get rating")
	public String getRating(long id){
		rating = movieAPI.getRating(id);
		return rating.toString();
	}
	

	
	
	@Command(description = "get top ten movies")
	public void getTopTenMovies(){
		ArrayList<Movie> movies = new ArrayList<>(movieAPI.getTopTenMovie());
		if(movies.size()>0){
			for(Movie movie : movies){
				System.out.println(movie.toString());
			}
		}else{
			System.out.println("No top movies to show");
		}
	}
	

	@Command(description = "get all ratings")
	public void getRatings(){
		Collection<Ratings> ratings = movieAPI.getRatings();
		System.out.println(ratings);
	}
	
}
