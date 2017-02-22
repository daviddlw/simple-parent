package com.david.facade;

public interface MongodbService {
	
	void mongodbConnection();
	
	void mongodbCreateCollection();
	
	void mongodbGetCollection();
	
	void mongodbInsert();
	
	void mongodbFind();
	
	void mongodbUpdate();
	
	void mongodbDelete();
}
