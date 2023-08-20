package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.metamodel.EntityType;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public abstract class AbstractRepository<T extends BaseEntity<K>, K>
        implements BaseRepository<T, K> {

    @PersistenceContext
    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    private final Class<T> entityClass;
    private final Class<K> idClass;

    //abstract void update(T prevState, T nextState);

    protected AbstractRepository() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        entityClass = (Class<T>) type.getActualTypeArguments()[0];
        idClass = (Class<K>) type.getActualTypeArguments()[1];
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> readAll() {
        TypedQuery<T> query = entityManager.createQuery("SELECT e FROM "
            + entityClass.getSimpleName() + " e", entityClass);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> readById(K id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    @Transactional
    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public T update(T entity) {
        return create(entity);
    }

    @Override
    @Transactional
    public boolean deleteById(K id) {
        if (id == null) {
            return false;
        }

        T entityRef = getReference(id);
        entityManager.remove(entityRef);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existById(K id) {
        EntityType<T> entityType = entityManager.getMetamodel().entity(entityClass);
        String idFieldName = entityType.getId(idClass).getName();

        Query query = entityManager
            .createQuery("SELECT COUNT(*) FROM " + entityClass.getSimpleName() + " WHERE " + idFieldName + " = ?1")
            .setParameter(1, id);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public T getReference(K id) {
        return entityManager.getReference(this.entityClass, id);
    }
}
