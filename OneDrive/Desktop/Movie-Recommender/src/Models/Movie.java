package Models;
import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.List;


import com.google.common.base.Objects;



public class Movie {
	static Long	counter = 0l;
	
	public String title;
	public String year;
	public String url;
	public List<Ratings> ratings = new ArrayList<Ratings>();

	public Long id;
	
	
	public Movie(String title, String year, String url){
		this.id = counter ++;
		this.title = title;
		this.year = year;
		this.url = url;
	}
	
	
	public Movie(Long id, String title, String year, String url){
		this.id = id;
		if(id >= counter){
			counter = id + 1;
		}
		this.title = title;
		this.year = year;
		this.url = url;
	}
	
	@Override
	public String toString(){
		return toStringHelper(this).addValue(id)
				                   .addValue(title)
				                   .addValue(year)
				                   .addValue(url)
				
				                   .toString();
	}
	
	@Override
	public boolean equals(final Object obj){
		if (obj instanceof Movie){
			final Movie other = (Movie) obj;
			return Objects.equal(title, other.title)
					&& Objects.equal(year, other.year)
					&& Objects.equal(url, other.url);
		}else{
			return false;
		}
		
	}
	
	
	
	public void addRating(Models.Ratings rating){
		ratings.add(rating);
	}
	
	public List<Ratings> getRatings(){
		return ratings;
	}
	
	public void setRatings(List<Ratings> ratings){
		this.ratings = ratings;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public void setYear(String year){
		this.year = year;
	}
	
	public String getYear(){
		return year;
	}
	
	public double averageRating(){
		double total = 0;
		int count = 0;
		for(Ratings rating : ratings){
			total += rating.rating;
			count++;
		}
		if(count != 0){
			return total/count;
		}else{
			return 0;
		}	
	}
	
	@Override
	public int hashCode(){
		return Objects.hashCode(this.id, this.title, this.url);
	}
	
	
	
	public int compareTo(Movie other) {
		
		return Double.compare(this.averageRating(), other.averageRating());
	}

	
}
