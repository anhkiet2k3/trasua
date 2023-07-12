/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import java.util.List;



/**
 *
 * @author nhatt
 */
abstract public class EduSysDAO<Entity,KeyType> {
    abstract public void insert(Entity entity);
    abstract public void update(Entity entity);
    abstract public void delete(KeyType key);
    abstract public List<Entity> selectAll();
    abstract public Entity selectById(KeyType key);
    abstract protected List<Entity> selectBySQL(String sql, Object...args);
}
