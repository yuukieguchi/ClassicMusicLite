package net.classicmusiclite.eguchi;

public class ArtistInfoId {

	private int Id;
	private String name;

	// private String wiki;
	// private ImageView image;

	public ArtistInfoId(int id, String name) {
		this.Id = id;
		this.name = name;
		// this.wiki = wiki;
		// this.image = image;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public int getId() {
		return this.Id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	/*
	 * public void setWiki(String wiki) { this.wiki = wiki; }
	 * 
	 * public String getWiki() { return this.wiki; }
	 * 
	 * public void setImgae(ImageView image) { this.image = image; }
	 * 
	 * public ImageView getImage() { return this.image; }
	 */
}
