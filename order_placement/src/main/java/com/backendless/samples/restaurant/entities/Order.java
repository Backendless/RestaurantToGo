package com.backendless.samples.restaurant.entities;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

import java.io.Serializable;

public class Order implements Serializable
{
  private java.util.Date created;
  private String objectId;
  private java.util.Date updated;
  private String ownerId;
  private java.util.List<MenuItem> menuItem;
  private BackendlessUser customer;

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public java.util.List<MenuItem> getMenuItem()
  {
    return menuItem;
  }

  public void setMenuItem( java.util.List<MenuItem> menuItem )
  {
    this.menuItem = menuItem;
  }

  public BackendlessUser getCustomer()
  {
    return customer;
  }

  public void setCustomer( BackendlessUser customer )
  {
    this.customer = customer;
  }

  public Order save()
  {
    return Backendless.Data.of( Order.class ).save( this );
  }

  public Future<Order> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Order> future = new Future<Order>();
      Backendless.Data.of( Order.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<Order> callback )
  {
    Backendless.Data.of( Order.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Order.class ).remove( this );
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
      Backendless.Data.of( Order.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Order.class ).remove( this, callback );
  }

  public static Order findById( String id )
  {
    return Backendless.Data.of( Order.class ).findById( id );
  }

  public static Future<Order> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Order> future = new Future<Order>();
      Backendless.Data.of( Order.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<Order> callback )
  {
    Backendless.Data.of( Order.class ).findById( id, callback );
  }

  public static Order findFirst()
  {
    return Backendless.Data.of( Order.class ).findFirst();
  }

  public static Future<Order> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Order> future = new Future<Order>();
      Backendless.Data.of( Order.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<Order> callback )
  {
    Backendless.Data.of( Order.class ).findFirst( callback );
  }

  public static Order findLast()
  {
    return Backendless.Data.of( Order.class ).findLast();
  }

  public static Future<Order> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Order> future = new Future<Order>();
      Backendless.Data.of( Order.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<Order> callback )
  {
    Backendless.Data.of( Order.class ).findLast( callback );
  }

  public static BackendlessCollection<Order> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( Order.class ).find( query );
  }

  public static Future<BackendlessCollection<Order>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<Order>> future = new Future<BackendlessCollection<Order>>();
      Backendless.Data.of( Order.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<Order>> callback )
  {
    Backendless.Data.of( Order.class ).find( query, callback );
  }
}