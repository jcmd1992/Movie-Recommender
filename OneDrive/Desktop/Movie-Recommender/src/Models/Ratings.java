package Models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;




public class Ratings {
	
	static long counter = 0l;

	public long userId;
	public long movieId;
	public double rating;
	
	public long id; 
	
	
	public Ratings(long userId, long movieId, double rating){
		this.userId = userId;
		this.movieId = movieId;
		this.rating = rating;
	}
	
	
	public Ratings(long id, long userId, long movieId, double rating){
		this.id = id;
		if(id >= counter){
			counter = id + 1;
		}
		this.userId = userId;
		this.movieId = movieId;
		this.rating = rating;
	}
	
	@Override
	public String toString(){
		return toStringHelper(this).addValue(userId)
				                   .addValue(movieId)
				                   .addValue(rating)
				                   .toString();
	}
	
	@Override
	public int hashCode(){
		return Objects.hashCode(this.userId, this.movieId, this.rating);
	}
	
	public int compareTo(Ratings that){
		return Double.compare(this.getRating(), that.getRating());
	}
	
	@Override
	public boolean equals(final Object obj){
		if(obj instanceof Ratings){
			final Ratings other = (Ratings) obj;
			return Objects.equal(userId, other.userId)
					&& Objects.equal(movieId, other.movieId)
					&& Objects.equal(rating, other.rating);
		}else{
			return false;
		}
	}
	
	
	
	public double getRating(){
		return rating;
	}
	
	public void setRating(Long rating){
		this.rating = rating;
	}
	
	public long getUserId(){
		return userId;
	}
	
	public void setUserId(long userId){
		this.userId = userId;
	}
	
	public long getMovieId(){
		return movieId;
	}
	
	public void setMovieId(long movieId){
		this.movieId = movieId;
	}
}

