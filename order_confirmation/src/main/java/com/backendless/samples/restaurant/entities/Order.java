package com.backendless.samples.restaurant.entities;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Order implements Serializable
{
  private java.util.Date created;
  private String objectId;
  private java.util.Date updated;
  private String ownerId;
  private BackendlessUser customer;
  private java.util.List<OrderItem> items;

  public Order()
  {
    items = new ArrayList<>();
  }

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

  public BackendlessUser getCustomer()
  {
    return customer;
  }

  public void setCustomer( BackendlessUser customer )
  {
    this.customer = customer;
  }

  public java.util.List<OrderItem> getItems()
  {
    return items;
  }

  public void setItems( java.util.List<OrderItem> items )
  {
    this.items = items;
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

  /**
   * Returns count of given menu item in order.
   *
   * @param menuItem item to search for
   * @return count of menu item in this order, 0 if order doesn't contain it
   */
  public int getCount( MenuItem menuItem )
  {
    for( OrderItem orderItem : items )
    {
      if( orderItem.getMenuItem().getObjectId().equals( menuItem.getObjectId() ) )
      {
        return orderItem.getQuantity();
      }
    }

    return 0;
  }

  /**
   * Tells whether this order contains given MenuItem.
   *
   * @param menuItem menu item to search for
   * @return true if order contains menu item. else false
   */
  public boolean containsMenuItem( MenuItem menuItem )
  {
    for( OrderItem orderItem : items )
    {
      if( orderItem.getMenuItem().getObjectId().equals( menuItem.getObjectId() ) )
      {
        return true;
      }
    }

    return false;
  }

  /**
   * Calculates total sum for this order.
   *
   * @return order sum
   */
  public double calculateTotal()
  {
    double sum = 0.0;

    for( OrderItem orderItem : items )
    {
      sum += orderItem.getMenuItem().getPrice() * orderItem.getQuantity();
    }

    return sum;
  }

  public OrderItem getOrderItem( MenuItem menuItem )
  {
    for( OrderItem orderItem : items )
    {
      if( orderItem.getMenuItem().getObjectId().equals( menuItem.getObjectId() ) )
      {
        return orderItem;
      }
    }

    return null;
  }

  public void removeItem( MenuItem menuItem )
  {
    Iterator<OrderItem> iterator = items.iterator();
    while( iterator.hasNext() )
    {
      OrderItem orderItem = iterator.next();
      if( orderItem.getMenuItem().getObjectId().equals( menuItem.getObjectId() ) )
      {
        iterator.remove();
        return;
      }
    }
  }
}