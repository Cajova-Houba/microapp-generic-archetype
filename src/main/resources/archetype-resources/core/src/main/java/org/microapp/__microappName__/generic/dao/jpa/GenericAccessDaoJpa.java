package org.microapp.${microappName}.generic.dao.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.microapp.${microappName}.generic.dao.GenericAccessDao;
import org.microapp.${microappName}.generic.model.BaseAccessObject;

public class GenericAccessDaoJpa<T extends BaseAccessObject, PK extends Serializable> extends GenericDaoJpa<T, PK> implements
		GenericAccessDao<T, PK> {

	 /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing or using dependency injection.
     * @param persistentClass the class type you'd like to persist
     */
    public GenericAccessDaoJpa(final Class<T> persistentClass) {
        super(persistentClass);
    }

    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing or using dependency injection.
     * @param persistentClass the class type you'd like to persist
     * @param entityManager the configured EntityManager for JPA implementation.
     */
    public GenericAccessDaoJpa(final Class<T> persistentClass, EntityManager entityManager) {
        super(persistentClass, entityManager);
    }
	
    /**
     * {@inheritDoc}
     */
	public List<T> getAllForPerson(PK personId) {
		// TODO Auto-generated method stub
		String query = "SELECT obj FROM "+getPersistentClass().getName()+ " obj WHERE "+BaseAccessObject.ACCESS_ID_COLUMN_NAME+"=?";
		Query q = getEntityManager().createQuery(query);
		q.setParameter(1, personId);
		
		return q.getResultList();
	}


}
