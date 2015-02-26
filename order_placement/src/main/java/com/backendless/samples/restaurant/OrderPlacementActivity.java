package com.backendless.samples.restaurant;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.backendless.Backendless;
import com.backendless.samples.restaurant.adapters.OrderPlacementAdapter;
import com.backendless.samples.restaurant.entities.Location;
import com.backendless.samples.restaurant.entities.Menu;
import com.backendless.samples.restaurant.entities.Restaurant;
import com.backendless.samples.restaurant.utility.LoadingCallback;

import java.util.Arrays;

public class OrderPlacementActivity extends ListActivity
{
  private Restaurant restaurant;
  private Location location;
  private OrderPlacementAdapter adapter;

  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_order_placement );

    restaurant = (Restaurant) getIntent().getSerializableExtra( "restaurant" );
    location = (Location) getIntent().getSerializableExtra( "location" );

    String title = restaurant.getName() + " " + getString( R.string.order_text );

    setTitle( title );

    final TextView orderTotalView = (TextView) findViewById( R.id.orderTotal );
    orderTotalView.setText( String.format( getString( R.string.order_total_text ), 0.00 ) );

    final LoadingCallback<Menu> menuLoadingCallback = new LoadingCallback<Menu>( this, getString( R.string.loading_menu_items ), true )
    {
      @Override
      public void handleResponse( Menu menu )
      {

        adapter = new OrderPlacementAdapter( OrderPlacementActivity.this, R.layout.list_item_order_placement, menu.getMenuItem(), orderTotalView );

        setListAdapter( adapter );

        super.handleResponse( menu );
      }
    };

    LoadingCallback<Location> locationLoadingCallback = new LoadingCallback<Location>( this, getString( R.string.loading_menu_items ), true )
    {
      @Override
      public void handleResponse( Location location )
      {
        Menu menu = location.getMenu();
        Backendless.Data.of( Menu.class ).loadRelations( menu, Arrays.asList( "menuItem" ), menuLoadingCallback );

        super.handleResponse( location );
      }
    };

    Backendless.Data.of( Location.class ).loadRelations( location, Arrays.asList( "menu" ), locationLoadingCallback );
  }
}
