package com.backendless.samples.restaurant.entities;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

import java.io.Serializable;

public class Restaurant implements Serializable
{
  private String name;
  private String ownerId;
  private String objectId;
  private String cuisine;
  private java.util.Date updated;
  private java.util.Date created;
  private java.util.List<Location> locations;
  private BackendlessUser owner;

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getCuisine()
  {
    return cuisine;
  }

  public void setCuisine( String cuisine )
  {
    this.cuisine = cuisine;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public java.util.List<Location> getLocations()
  {
    return locations;
  }

  public void setLocations( java.util.List<Location> locations )
  {
    this.locations = locations;
  }

  public BackendlessUser getOwner()
  {
    return owner;
  }

  public void setOwner( BackendlessUser owner )
  {
    this.owner = owner;
  }

  public Restaurant save()
  {
    return Backendless.Data.of( Restaurant.class ).save( this );
  }

  public Future<Restaurant> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Restaurant> future = new Future<Restaurant>();
      Backendless.Data.of( Restaurant.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<Restaurant> callback )
  {
    Backendless.Data.of( Restaurant.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Restaurant.class ).remove( this );
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
      Backendless.Data.of( Restaurant.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Restaurant.class ).remove( this, callback );
  }

  public static Restaurant findById( String id )
  {
    return Backendless.Data.of( Restaurant.class ).findById( id );
  }

  public static Future<Restaurant> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Restaurant> future = new Future<Restaurant>();
      Backendless.Data.of( Restaurant.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<Restaurant> callback )
  {
    Backendless.Data.of( Restaurant.class ).findById( id, callback );
  }

  public static Restaurant findFirst()
  {
    return Backendless.Data.of( Restaurant.class ).findFirst();
  }

  public static Future<Restaurant> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Restaurant> future = new Future<Restaurant>();
      Backendless.Data.of( Restaurant.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<Restaurant> callback )
  {
    Backendless.Data.of( Restaurant.class ).findFirst( callback );
  }

  public static Restaurant findLast()
  {
    return Backendless.Data.of( Restaurant.class ).findLast();
  }

  public static Future<Restaurant> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Restaurant> future = new Future<Restaurant>();
      Backendless.Data.of( Restaurant.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<Restaurant> callback )
  {
    Backendless.Data.of( Restaurant.class ).findLast( callback );
  }

  public static BackendlessCollection<Restaurant> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( Restaurant.class ).find( query );
  }

  public static Future<BackendlessCollection<Restaurant>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<Restaurant>> future = new Future<BackendlessCollection<Restaurant>>();
      Backendless.Data.of( Restaurant.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Restaurant>> callback )
  {
    Backendless.Data.of( Restaurant.class ).find( query, callback );
  }
}