package com.backendless.samples.restaurant.entities;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

import java.io.Serializable;

public class Menu implements Serializable
{
  private String objectId;
  private java.util.Date created;
  private String ownerId;
  private java.util.Date updated;
  private java.util.List<MenuItem> menuItem;
  public String getObjectId()
  {
    return objectId;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public java.util.List<MenuItem> getMenuItem()
  {
    return menuItem;
  }

  public void setMenuItem( java.util.List<MenuItem> menuItem )
  {
    this.menuItem = menuItem;
  }

                                                    
  public Menu save()
  {
    return Backendless.Data.of( Menu.class ).save( this );
  }

  public Future<Menu> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Menu> future = new Future<Menu>();
      Backendless.Data.of( Menu.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<Menu> callback )
  {
    Backendless.Data.of( Menu.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Menu.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( Menu.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Menu.class ).remove( this, callback );
  }

  public static Menu findById( String id )
  {
    return Backendless.Data.of( Menu.class ).findById( id );
  }

  public static Future<Menu> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Menu> future = new Future<Menu>();
      Backendless.Data.of( Menu.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<Menu> callback )
  {
    Backendless.Data.of( Menu.class ).findById( id, callback );
  }

  public static Menu findFirst()
  {
    return Backendless.Data.of( Menu.class ).findFirst();
  }

  public static Future<Menu> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Menu> future = new Future<Menu>();
      Backendless.Data.of( Menu.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<Menu> callback )
  {
    Backendless.Data.of( Menu.class ).findFirst( callback );
  }

  public static Menu findLast()
  {
    return Backendless.Data.of( Menu.class ).findLast();
  }

  public static Future<Menu> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Menu> future = new Future<Menu>();
      Backendless.Data.of( Menu.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<Menu> callback )
  {
    Backendless.Data.of( Menu.class ).findLast( callback );
  }

  public static BackendlessCollection<Menu> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( Menu.class ).find( query );
  }

  public static Future<BackendlessCollection<Menu>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<Menu>> future = new Future<BackendlessCollection<Menu>>();
      Backendless.Data.of( Menu.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Menu>> callback )
  {
    Backendless.Data.of( Menu.class ).find( query, callback );
  }
}