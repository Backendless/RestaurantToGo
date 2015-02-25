package com.backendless.samples.restaurant.entities;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

import java.io.Serializable;

public class MenuItem implements Serializable
{
  private java.util.Date created;
  private String ownerId;
  private String description;
  private java.util.Date updated;
  private String name;
  private String objectId;
  private Double price;

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription( String description )
  {
    this.description = description;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public Double getPrice()
  {
    return price;
  }

  public void setPrice( Double price )
  {
    this.price = price;
  }

  public MenuItem save()
  {
    return Backendless.Data.of( MenuItem.class ).save( this );
  }

  public Future<MenuItem> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<MenuItem> future = new Future<MenuItem>();
      Backendless.Data.of( MenuItem.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<MenuItem> callback )
  {
    Backendless.Data.of( MenuItem.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( MenuItem.class ).remove( this );
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
      Backendless.Data.of( MenuItem.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( MenuItem.class ).remove( this, callback );
  }

  public static MenuItem findById( String id )
  {
    return Backendless.Data.of( MenuItem.class ).findById( id );
  }

  public static Future<MenuItem> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<MenuItem> future = new Future<MenuItem>();
      Backendless.Data.of( MenuItem.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<MenuItem> callback )
  {
    Backendless.Data.of( MenuItem.class ).findById( id, callback );
  }

  public static MenuItem findFirst()
  {
    return Backendless.Data.of( MenuItem.class ).findFirst();
  }

  public static Future<MenuItem> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<MenuItem> future = new Future<MenuItem>();
      Backendless.Data.of( MenuItem.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<MenuItem> callback )
  {
    Backendless.Data.of( MenuItem.class ).findFirst( callback );
  }

  public static MenuItem findLast()
  {
    return Backendless.Data.of( MenuItem.class ).findLast();
  }

  public static Future<MenuItem> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<MenuItem> future = new Future<MenuItem>();
      Backendless.Data.of( MenuItem.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<MenuItem> callback )
  {
    Backendless.Data.of( MenuItem.class ).findLast( callback );
  }

  public static BackendlessCollection<MenuItem> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( MenuItem.class ).find( query );
  }

  public static Future<BackendlessCollection<MenuItem>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<MenuItem>> future = new Future<BackendlessCollection<MenuItem>>();
      Backendless.Data.of( MenuItem.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<MenuItem>> callback )
  {
    Backendless.Data.of( MenuItem.class ).find( query, callback );
  }
}