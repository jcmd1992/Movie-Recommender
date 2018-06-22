package Models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.List;
import com.google.common.base.Objects;


public class User {

	
	
	static long counter= 0l;
	
	public long id;
	public String firstName;
	public String lastName;
	public String gender;
	public String age;
	public String occupation;
	
	
	public List<Ratings> ratings = new ArrayList<>();
	
	
	
	public User(long id, String firstName, String lastName, String gender, String age, String occupation){
		
		if(firstName == null || firstName == "" || lastName == null
				|| lastName == ""){
			throw new IllegalArgumentException();
		}
		this.id = counter++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender	= gender;
		this.age = age;
		this.occupation = occupation;
	}
	
	 
	public void addUser(Long id, String firstName, String lastName, String gender, String age, String occupation){
		this.id = id;
		if(id >= counter){
			counter = id + 1;
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender	= gender;
		this.age = age;
		this.occupation = occupation;
	}
	
	
	public String toString(){
		return toStringHelper(this).addValue(id)
								   .addValue(firstName)
								   .addValue(lastName)
								   .addValue(gender)
								   .addValue(age)
								   .addValue(occupation)
								   .toString();
	}
	
	@Override
	public int hashCode(){
		return Objects.hashCode(this.lastName, this.firstName, this.age, this.gender, this.occupation);
	}
	
	@Override
	public boolean equals(final Object obj){
		if(obj instanceof User){
			final User other = (User) obj;
			return Objects.equal(firstName, other.firstName)
					&& Objects.equal(lastName, other.lastName)
					&& Objects.equal(gender, other.gender)
					&& Objects.equal(age, other.age)
					&& Objects.equal(occupation, other.occupation)
					&& Objects.equal(ratings, other.ratings);
		}else{
			return false;
		}
	}
	
	public void addUser(User user){
		return;
	}
	
	public List<Ratings> getRatings(){
		return ratings;
	}
	
	
	public void setRatings(List<Ratings> ratings){
		this.ratings = ratings;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getAge(){
		return age;
	}
	
	public void setAge(String age){
		this.age = age;
	}
	
	public String getOccupation(){
		return occupation;
	}
	
	public void setOccupation(String occupation){
		this.occupation = occupation;
	}
}
