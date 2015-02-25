package com.backendless.samples.restaurant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.backendless.samples.restaurant.R;
import com.backendless.samples.restaurant.entities.MenuItem;

import java.util.List;

/**
 * An adapter that fills a view having list of MenuItem objects as input data.
 */
public class OrderPlacementAdapter extends ArrayAdapter<MenuItem>
{
  private LayoutInflater mInflater;
  private int mResource;
  private TextView totalPriceView;
  private NumberPicker mNumberPicker;
  private CheckBox mCheckBox;

  /**
   * Constructor
   *
   * @param context     The current context.
   * @param resource    The resource ID for a layout file containing a TextView to use when
   *                    instantiating views.
   * @param restaurants The objects to represent in the ListView.
   */
  public OrderPlacementAdapter( Context context, int resource, List<MenuItem> restaurants, TextView totalPriceView )
  {
    super( context, resource, restaurants );
    mResource = resource;
    mInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    this.totalPriceView = totalPriceView;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public View getView( int position, View convertView, ViewGroup parent )
  {
    View view = convertView == null ? mInflater.inflate( mResource, parent, false ) : convertView;

    final NumberPicker quantityPicker = (NumberPicker) view.findViewById( R.id.quantity );
    quantityPicker.setMinValue( 0 );
    quantityPicker.setMaxValue( 10 );
    quantityPicker.setValue( 0 );
    quantityPicker.setDescendantFocusability( NumberPicker.FOCUS_BLOCK_DESCENDANTS );
//    quantityPicker.setWrapSelectorWheel( false );

    final TextView priceTextView = (TextView) view.findViewById( R.id.itemPrice );

    final CheckBox menuItemCheckBox = (CheckBox) view.findViewById( R.id.menuItemCheckBox );
    menuItemCheckBox.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
    {
      @Override
      public void onCheckedChanged( CompoundButton buttonView, boolean isChecked )
      {
        String totalText = totalPriceView.getText().toString();
        double currentPrice = Double.valueOf( totalText.substring( totalText.lastIndexOf( " " ) ) );
        double itemPrice = Double.valueOf( priceTextView.getText().toString() );
        double newPrice = isChecked ? currentPrice + itemPrice * quantityPicker.getValue() : currentPrice - itemPrice * quantityPicker.getValue();
        totalPriceView.setText( String.format( getContext().getString( R.string.order_total_text ), newPrice ) );
      }
    } );

    quantityPicker.setOnValueChangedListener( new NumberPicker.OnValueChangeListener()
    {
      @Override
      public void onValueChange( NumberPicker picker, int oldVal, int newVal )
      {
        if( menuItemCheckBox.isChecked() )
        {
          String totalText = totalPriceView.getText().toString();
          double currentPrice = Double.valueOf( totalText.substring( totalText.lastIndexOf( " " ) ) );
          double itemPrice = Double.valueOf( priceTextView.getText().toString() );
          double newPrice = currentPrice + itemPrice * (newVal - oldVal);
          totalPriceView.setText( String.format( getContext().getString( R.string.order_total_text ), newPrice ) );
        }
      }
    } );

    MenuItem item = getItem( position );

    menuItemCheckBox.setText( item.getName() );
    priceTextView.setText( String.valueOf( item.getPrice() ) );

    return view;
  }
}