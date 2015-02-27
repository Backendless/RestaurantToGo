package com.backendless.samples.restaurant.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.backendless.samples.restaurant.R;
import com.backendless.samples.restaurant.entities.MenuItem;
import com.backendless.samples.restaurant.entities.Order;
import com.backendless.samples.restaurant.entities.OrderItem;

import java.util.List;

/**
 * An adapter that fills a view having list of MenuItem objects as input data.
 */
public class OrderPlacementAdapter extends ArrayAdapter<MenuItem>
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
  public OrderPlacementAdapter( Context context, int resource, List<MenuItem> menuItems, TextView totalPriceView )
  {
    super( context, resource, menuItems );
    mResource = resource;
    mInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    order = new Order();
    this.totalPriceView = totalPriceView;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public View getView( int position, View convertView, ViewGroup parent )
  {
    final ViewHolder holder;

    final MenuItem menuItem = getItem( position );

    if( convertView == null )
    {
      /* There is no view at this position, we create a new one.
           In this case by inflating an xml layout */
      convertView = mInflater.inflate( mResource, parent, false );
      holder = new ViewHolder();
      holder.quantityEditText = (EditText) convertView.findViewById( R.id.quantity );
      holder.menuItemCheckBox = (CheckBox) convertView.findViewById( R.id.menuItemCheckBox );
      holder.priceTextView = (TextView) convertView.findViewById( R.id.itemPrice );
      holder.textWatcher = new TextWatcher()
      {
        @Override
        public void beforeTextChanged( CharSequence s, int start, int count, int after )
        {

        }

        @Override
        public void onTextChanged( CharSequence s, int start, int before, int count )
        {
          if( holder.menuItemCheckBox.isChecked() && s.length() != 0 )
          {
            Integer quantity = Integer.valueOf( s.toString() );
            OrderItem orderItem = order.getOrderItem( menuItem );
            orderItem.setQuantity( quantity );
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
      holder.menuItemCheckBox.setOnCheckedChangeListener( null );
      holder.quantityEditText.removeTextChangedListener( holder.textWatcher );
    }

    holder.quantityEditText.setText( String.valueOf( order.getQuantity( menuItem ) ) );

    holder.quantityEditText.addTextChangedListener( holder.textWatcher );

    holder.menuItemCheckBox.setChecked( order.containsMenuItem( menuItem ) );
    holder.menuItemCheckBox.setText( menuItem.getName() );

    holder.menuItemCheckBox.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
    {
      @Override
      public void onCheckedChanged( CompoundButton buttonView, boolean isChecked )
      {
        if( isChecked )
        {
          OrderItem orderItem = new OrderItem();
          orderItem.setMenuItem( menuItem );
          orderItem.setQuantity( Integer.valueOf( holder.quantityEditText.getText().toString() ) );
          order.getItems().add( orderItem );
        }
        else //not checked
        {
          order.removeItem( menuItem );
        }

        totalPriceView.setText( String.format( getContext().getString( R.string.order_total_text ), order.calculateTotal() ) );
      }
    } );

    holder.priceTextView.setText( String.valueOf( menuItem.getPrice() ) );

    return convertView;
  }

  public Order getOrder()
  {
    return order;
  }

  static class ViewHolder
  {
    CheckBox menuItemCheckBox;
    EditText quantityEditText;
    TextView priceTextView;
    TextWatcher textWatcher;
  }
}