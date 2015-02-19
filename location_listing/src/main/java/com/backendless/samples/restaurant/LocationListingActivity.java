package com.backendless.samples.restaurant;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.backendless.samples.restaurant.adapters.LocationAdapter;
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

    TextView screenTitle = (TextView) findViewById( R.id.locationsTitle );
    screenTitle.setText( title );

    adapter = new LocationAdapter( this, R.layout.list_item_location, restaurant.getLocations() );

    setListAdapter( adapter );
  }
}
