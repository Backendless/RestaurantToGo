package com.backendless.samples.restaurant;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import com.backendless.samples.restaurant.entities.Location;
import com.backendless.samples.restaurant.entities.Order;
import com.backendless.samples.restaurant.entities.Restaurant;

public class OrderSuccessActivity extends ActionBarActivity
{
  private Restaurant restaurant;
  private Location location;
  private Order order;

  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_order_success );

    restaurant = (Restaurant) getIntent().getSerializableExtra( "restaurant" );
    location = (Location) getIntent().getSerializableExtra( "location" );
    order = (Order) getIntent().getSerializableExtra( "order" );

    String title = restaurant.getName() + " " + getString( R.string.order_confirmation_title_text );
    setTitle( title );

    TextView screenTitle = (TextView) findViewById( R.id.title );
    screenTitle.setText( title );

    TextView orderNumberView = (TextView) findViewById( R.id.orderNumber );
    orderNumberView.setText( String.format( getString( R.string.order_number ), order.getNumber() ) );

    TextView addressView = (TextView) findViewById( R.id.address );
    addressView.setText( String.format( getString( R.string.pickup_address_text ), restaurant.getName(), location.getCity(), location.getStreetAddress() ) );
  }
}
