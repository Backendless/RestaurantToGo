package com.backendless.samples.restaurant.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.backendless.samples.restaurant.R;
import com.backendless.samples.restaurant.entities.Order;
import com.backendless.samples.restaurant.entities.OrderItem;

import java.util.List;

/**
 * An adapter that fills a view having list of OrderItem objects as input data.
 */
public class OrderConfirmationAdapter extends ArrayAdapter<OrderItem>
{
  private LayoutInflater mInflater;
  private int mResource;
  private Order order;
  private TextView totalPriceView;

  /**
   * Constructor
   *
   * @param context   The current context.
   * @param resource  The resource ID for a layout file containing a TextView to use when
   *                  instantiating views.
   * @param menuItems The objects to represent in the ListView.
   */
  public OrderConfirmationAdapter( Context context, int resource, List<OrderItem> menuItems, Order order,
                                   TextView totalPriceView )
  {
    super( context, resource, menuItems );
    mResource = resource;
    mInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    this.order = order;
    this.totalPriceView = totalPriceView;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public View getView( int position, View convertView, ViewGroup parent )
  {
    final ViewHolder holder;

    final OrderItem orderItem = getItem( position );

    if( convertView == null )
    {
      /* There is no view at this position, we create a new one.
           In this case by inflating an xml layout */
      convertView = mInflater.inflate( mResource, parent, false );
      holder = new ViewHolder();
      holder.quantityEditText = (EditText) convertView.findViewById( R.id.quantity );
      holder.menuItemTextView = (TextView) convertView.findViewById( R.id.menuItem );
      holder.priceTextView = (TextView) convertView.findViewById( R.id.itemPrice );
      holder.cancelButton = (ImageButton) convertView.findViewById( R.id.cancelButton );
      holder.textWatcher = new TextWatcher()
      {
        @Override
        public void beforeTextChanged( CharSequence s, int start, int count, int after )
        {

        }

        @Override
        public void onTextChanged( CharSequence s, int start, int before, int count )
        {
          if( s.length() != 0 )
          {
            Integer newQuantity = Integer.valueOf( s.toString() );
            orderItem.setQuantity( newQuantity );
            holder.priceTextView.setText( String.valueOf( orderItem.getMenuItem().getPrice() * orderItem.getQuantity() ) );
            totalPriceView.setText( String.format( getContext().getString( R.string.order_total_text ), order.calculateTotal() ) );
          }
        }

        @Override
        public void afterTextChanged( Editable s )
        {

        }
      };

      convertView.setTag( holder );
    }
    else
    {
      /* We recycle a View that already exists */
      holder = (ViewHolder) convertView.getTag();
      holder.cancelButton.setOnClickListener( null );
      holder.quantityEditText.removeTextChangedListener( holder.textWatcher );
    }

    holder.quantityEditText.setText( String.valueOf( orderItem.getQuantity() ) );
    holder.quantityEditText.addTextChangedListener( holder.textWatcher );

    holder.menuItemTextView.setText( orderItem.getMenuItem().getName() );

    holder.cancelButton.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View v )
      {
        order.removeItem( orderItem );
        recalculateTotalPrice();
        remove( orderItem );
        notifyDataSetChanged();
      }
    } );

    holder.priceTextView.setText( String.valueOf( orderItem.getMenuItem().getPrice() * orderItem.getQuantity() ) );

    recalculateTotalPrice();

    return convertView;
  }

  private void recalculateTotalPrice()
  {
    totalPriceView.setText( String.format( getContext().getString( R.string.total_text ), order.calculateTotal() ) );
  }

  public Order getOrder()
  {
    return order;
  }

  static class ViewHolder
  {
    TextView menuItemTextView;
    EditText quantityEditText;
    TextView priceTextView;
    TextWatcher textWatcher;
    ImageButton cancelButton;
  }
}