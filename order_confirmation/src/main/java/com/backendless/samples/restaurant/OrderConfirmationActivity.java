package com.backendless.samples.restaurant;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.backendless.samples.restaurant.adapters.OrderConfirmationAdapter;
import com.backendless.samples.restaurant.entities.Location;
import com.backendless.samples.restaurant.entities.Order;
import com.backendless.samples.restaurant.entities.Restaurant;

public class OrderConfirmationActivity extends ListActivity
{
  private Restaurant restaurant;
  private Location location;
  private Order order;

  private OrderConfirmationAdapter adapter;

  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_order_confirmation );

    restaurant = (Restaurant) getIntent().getSerializableExtra( "restaurant" );
    location = (Location) getIntent().getSerializableExtra( "location" );
    order = (Order) getIntent().getSerializableExtra( "order" );

    String title = restaurant.getName() + " " + getString( R.string.order_confirmation_title_text );
    setTitle( title );

    TextView screenTitle = (TextView) findViewById( R.id.title );
    screenTitle.setText( title );

    View footerView = getLayoutInflater().inflate( R.layout.list_footer_order_confirmation, null );
    getListView().addFooterView( footerView );

    TextView totalPriceView = (TextView) footerView.findViewById( R.id.price );

    adapter = new OrderConfirmationAdapter( this, R.layout.list_item_order_confirmation, order.getItems(), order, totalPriceView );
    setListAdapter( adapter );
  }
}
