package ua.nure.cs.kondratiuk.usermanagement.db;

import java.util.Collection;

import ua.nure.cs.kondratiuk.usermanagement.User;


public interface Dao<T> {
	T create(T entity) throws DatabaseExeption;
	
	T find(Long id) throws DatabaseExeption;
	
	void update(T entity) throws DatabaseExeption;
	
	void delete(T entity) throws DatabaseExeption;
	
	Collection<T> findAll() throws DatabaseExeption;
}
