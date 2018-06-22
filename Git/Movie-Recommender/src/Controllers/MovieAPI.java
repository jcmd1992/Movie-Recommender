package Controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Models.Ratings;
import Models.User;
import Models.Movie;
import Utils.XMLSerializer;
import Utils.Serializer;




public class MovieAPI {

	
	public Map<Long, User> userIndex = new HashMap<>();
	public Map<Long, Movie> movieIndex = new HashMap<>();
	public Map<Long, Ratings> ratingIndex = new HashMap<>();
	
	private Serializer serializer;
	Ratings rating;
	
	public MovieAPI(Serializer serializer){
		this.serializer = serializer;
	}
	
	public void initalLoad() throws IOException {
		String delims = "[|]";
		Scanner scanner = new Scanner(new File("./Files/users5.dat"));
		while (scanner.hasNextLine()) {
			String userDetails = scanner.nextLine();
			// parse user details string
			String[] userTokens = userDetails.split(delims);

			if (userTokens.length == 7) {
				createUser(userTokens[1], userTokens[2], Integer.valueOf(userTokens[3]), userTokens[4], userTokens[5]);
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + userTokens.length);
			}
		}

		scanner = new Scanner(new File("./Files/items5.dat"));
		while (scanner.hasNextLine()) {
			String movieDetails = scanner.nextLine();
			// parse user details string
			String[] movieTokens = movieDetails.split(delims);

			if (movieTokens.length == 23) {
				addMovie(movieTokens[1], movieTokens[2], movieTokens[3]);
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + movieTokens.length);
			}
		}

		scanner = new Scanner(new File("./Files/ratings5.dat"));
		while (scanner.hasNextLine()) {
			String userDetails2 = scanner.nextLine();
			// parse user details string
			String[] userTokens2 = userDetails2.split(delims);
			if (userTokens2.length == 4) {
				addRating(Long.valueOf(userTokens2[0]), Long.valueOf(userTokens2[1]), Integer.valueOf(userTokens2[2]));
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + userTokens2.length);
			}

		}
		scanner.close();

	}

	private void createUser(String string, String string2, Integer valueOf, String string3, String string4) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	public void load() throws Exception {
		serializer.read();
		userIndex = (Map<Long, User>) serializer.pop();
		movieIndex = (Map<Long, Movie>) serializer.pop();
		ratingIndex = (Map<Long, Ratings>) serializer.pop();
		// usersName = (Map<String, Users>) serializer.pop();
	}

	public void store() throws Exception {
		serializer.push(ratingIndex);
		serializer.push(movieIndex);
		serializer.push(userIndex);
		
		serializer.write();
	}

    public User addUser(long userId, String firstName, String lastName, String gender, String age, String occupation) {
		User user = new User(userId, firstName, lastName, gender, age, occupation);
		user.id = userIndex.size() + 1l;
		userIndex.put(user.id, user);
		return user;
	}
	
	public void removeUser(Long userID) throws Exception{
		userIndex.remove(userID);
	}
	
	public User getUserById(long userId){
		return userIndex.get(userId);
	}
	
	public User getUser(Long id){
		for(User user : userIndex.values())
			if(user.id == id)
				return user;
	    return null;
	  }
	
	public Long getMaxUserId(){
		Long maxId = 0l;
		for (Long id : userIndex.keySet()){
			if (id>maxId){
				maxId = id;
			}
		}return maxId;
	}
	
	public Collection<User> getUsers(){
		return userIndex.values();
	}
	
	public Movie addMovie(String title, String year, String url) {
		Movie movie = new Movie(title, year, url);
		movie.id = movieIndex.size() + 1l;
		movieIndex.put(movie.id, movie);
		return movie;
	}
	
	public void removeMovie(Movie movie) throws Exception{
		movieIndex.remove(movie.id);	
	}
	
	public Collection<Movie> getMovies(){
		return movieIndex.values();
	}

	public Ratings addRating(long userID, long movieID, double rating) {
		Ratings r = new Ratings(userID, movieID, rating);
		r.id = ratingIndex.size() + 1l;
		ratingIndex.put(r.id, r);
		getMovie(movieID).ratings.add(r);
		return r;	
	}
	
	public Long getMaxMovieId(){
		Long maxId = 0l;
		for(Long id : movieIndex.keySet()){
			if(id>maxId){
				maxId=id;
			}
		}return maxId;
	}

	public Movie getMovie(long movieID) {	
		for(Movie movie : movieIndex.values())
			if(movie.id == movieID)
				return movie;
			
		return null;	
	}

	public double getUserRating(long userID) {
		
		return 0;
	}
	

	public Ratings getRating(long movieId){
		return ratingIndex.get(movieId);
	}

	public double getUserRecommendations(long userID) {
		
		return 0;
	}
	
	public Collection<Ratings> getRatings(){
		return ratingIndex.values();
	}
	

	
public List<Movie>getTopTenMovie(){
		Collection<Movie> allMovie = getMovies();
		List<Movie> movieList = new ArrayList<Movie>(allMovie);
		List<Movie> sub = movieList.subList(0, 10 > movieList.size() ? movieList.size() : 10);
		return sub;	
	}
}
	
	
	


	