package com.backendless.samples.restaurant;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.samples.restaurant.adapters.OrderConfirmationAdapter;
import com.backendless.samples.restaurant.entities.Location;
import com.backendless.samples.restaurant.entities.Order;
import com.backendless.samples.restaurant.entities.Restaurant;
import com.backendless.samples.restaurant.utility.DialogHelper;
import com.backendless.samples.restaurant.utility.LoadingCallback;

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

    Button submitOrderButton = (Button) findViewById( R.id.submit );
    submitOrderButton.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View v )
      {
        final ProgressDialog progressDialog = new ProgressDialog( OrderConfirmationActivity.this );
        progressDialog.setMessage( getString( R.string.loading_submit_order ) );
        progressDialog.show();

        Backendless.Counters.incrementAndGet( "order_number", new AsyncCallback<Integer>()
        {
          @Override
          public void handleResponse( Integer response )
          {
            progressDialog.dismiss();

            order.setNumber( response );

            // save Order on backend
            Backendless.Data.of( Order.class ).save( order, new LoadingCallback<Order>( OrderConfirmationActivity.this, getString( R.string.loading_placing_order ), true )
            {
              @Override
              public void handleResponse( Order response )
              {
                super.handleResponse( response );
                Intent orderSuccessIntent = new Intent( OrderConfirmationActivity.this, OrderSuccessActivity.class );
                orderSuccessIntent.putExtra( "restaurant", restaurant );
                orderSuccessIntent.putExtra( "location", location );
                orderSuccessIntent.putExtra( "order", order );
                startActivity( orderSuccessIntent );
                finish();
              }
            } );
          }

          @Override
          public void handleFault( BackendlessFault fault )
          {
            progressDialog.dismiss();

            DialogHelper.createErrorDialog( OrderConfirmationActivity.this, "BackendlessFault", fault.getMessage() ).show();
          }
        } );
      }
    } );
  }
}
