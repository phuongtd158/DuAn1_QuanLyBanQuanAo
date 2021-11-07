/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;

/**
 *
 * @author ASUS
 */
public abstract class Main < E , K >{
    abstract public void insert( E entity ) ;
    abstract public void update( E entity ) ;
    abstract public void delete( K id ) ;
    abstract public List<E> selectAll() ;
    abstract public E selectByID( K id ) ;
    abstract protected List<E> selectBySQL( String sql , Object ...args ) ;   
}
