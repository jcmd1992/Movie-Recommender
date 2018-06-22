package Controllers;

import java.util.Collection;

import Models.Movie;
import Models.User;

public interface Interface {
	public String load() throws Exception;

	public void store() throws Exception;

	public void addUser(String ID,String firstName, String lastName, int age, String gender, String occupation);

	public User getUserByName(String name);

	public User getUserById(Long id);

	public void deleteUser(Long id);
	
	public void getMovies();

	public Movie getMovieByTitle(String title);

	public void addRatings(Long userID, Long movieID, int rating);

}