package com.backendless.samples.restaurant;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.backendless.samples.restaurant.adapters.LocationAdapter;
import com.backendless.samples.restaurant.adapters.OrderPlacementAdapter;
import com.backendless.samples.restaurant.entities.Restaurant;

public class LocationListingActivity extends ListActivity
{
  private Restaurant restaurant;
  private LocationAdapter adapter;

  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_location_listing );

    restaurant = (Restaurant) getIntent().getSerializableExtra( "restaurant" );

    String title = restaurant.getName() + " " + getString( R.string.locations_text );

    setTitle( title );

    TextView screenTitle = (TextView) findViewById( R.id.title );
    screenTitle.setText( title );

    adapter = new LocationAdapter( this, R.layout.list_item_location, restaurant.getLocations() );

    setListAdapter( adapter );
  }

  @Override
  protected void onListItemClick( ListView l, View v, int position, long id )
  {
    Intent showOrderPlacementIntent = new Intent( this, OrderPlacementActivity.class );
    showOrderPlacementIntent.putExtra( "restaurant", restaurant );
    showOrderPlacementIntent.putExtra( "location", restaurant.getLocations().get( position ) );
    startActivity( showOrderPlacementIntent );
  }
}
