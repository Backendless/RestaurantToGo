package com.backendless.samples.restaurant.entities;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

import java.io.Serializable;

public class OrderItem implements Serializable
{
  private String ownerId;
  private java.util.Date updated;
  private String objectId;
  private Integer quantity;
  private java.util.Date created;
  private MenuItem menuItem;

  public String getOwnerId()
  {
    return ownerId;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public Integer getQuantity()
  {
    return quantity;
  }

  public void setQuantity( Integer quantity )
  {
    this.quantity = quantity;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public MenuItem getMenuItem()
  {
    return menuItem;
  }

  public void setMenuItem( MenuItem menuItem )
  {
    this.menuItem = menuItem;
  }

  public OrderItem save()
  {
    return Backendless.Data.of( OrderItem.class ).save( this );
  }

  public Future<OrderItem> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<OrderItem> future = new Future<OrderItem>();
      Backendless.Data.of( OrderItem.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<OrderItem> callback )
  {
    Backendless.Data.of( OrderItem.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( OrderItem.class ).remove( this );
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
      Backendless.Data.of( OrderItem.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( OrderItem.class ).remove( this, callback );
  }

  public static OrderItem findById( String id )
  {
    return Backendless.Data.of( OrderItem.class ).findById( id );
  }

  public static Future<OrderItem> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<OrderItem> future = new Future<OrderItem>();
      Backendless.Data.of( OrderItem.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<OrderItem> callback )
  {
    Backendless.Data.of( OrderItem.class ).findById( id, callback );
  }

  public static OrderItem findFirst()
  {
    return Backendless.Data.of( OrderItem.class ).findFirst();
  }

  public static Future<OrderItem> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<OrderItem> future = new Future<OrderItem>();
      Backendless.Data.of( OrderItem.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<OrderItem> callback )
  {
    Backendless.Data.of( OrderItem.class ).findFirst( callback );
  }

  public static OrderItem findLast()
  {
    return Backendless.Data.of( OrderItem.class ).findLast();
  }

  public static Future<OrderItem> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<OrderItem> future = new Future<OrderItem>();
      Backendless.Data.of( OrderItem.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<OrderItem> callback )
  {
    Backendless.Data.of( OrderItem.class ).findLast( callback );
  }

  public static BackendlessCollection<OrderItem> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( OrderItem.class ).find( query );
  }

  public static Future<BackendlessCollection<OrderItem>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<OrderItem>> future = new Future<BackendlessCollection<OrderItem>>();
      Backendless.Data.of( OrderItem.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<OrderItem>> callback )
  {
    Backendless.Data.of( OrderItem.class ).find( query, callback );
  }

  /**
   * Performs comparison by objectIds if they're not null,
   * else compares objects by fields.
   *
   * @param o the object to compare this instance with
   * @return {@code true} if objects are the same or equal; {@code false} otherwise.
   */
  @Override
  public boolean equals( Object o )
  {
    if( this == o )
    {
      return true;
    }
    if( o == null || getClass() != o.getClass() )
    {
      return false;
    }

    OrderItem orderItem = (OrderItem) o;

    if( objectId != null && orderItem.getObjectId() != null )
    {
      return objectId.equals( orderItem.getObjectId() );
    }

    if( created != null ? !created.equals( orderItem.created ) : orderItem.created != null )
    {
      return false;
    }
    if( menuItem != null ? !menuItem.equals( orderItem.menuItem ) : orderItem.menuItem != null )
    {
      return false;
    }
    if( objectId != null ? !objectId.equals( orderItem.objectId ) : orderItem.objectId != null )
    {
      return false;
    }
    if( ownerId != null ? !ownerId.equals( orderItem.ownerId ) : orderItem.ownerId != null )
    {
      return false;
    }
    if( !quantity.equals( orderItem.quantity ) )
    {
      return false;
    }
    if( updated != null ? !updated.equals( orderItem.updated ) : orderItem.updated != null )
    {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode()
  {
    int result = ownerId != null ? ownerId.hashCode() : 0;
    result = 31 * result + (updated != null ? updated.hashCode() : 0);
    result = 31 * result + (objectId != null ? objectId.hashCode() : 0);
    result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
    result = 31 * result + (created != null ? created.hashCode() : 0);
    result = 31 * result + (menuItem != null ? menuItem.hashCode() : 0);
    return result;
  }
}