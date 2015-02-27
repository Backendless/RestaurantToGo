package com.backendless.samples.restaurant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.backendless.samples.restaurant.R;
import com.backendless.samples.restaurant.entities.Restaurant;

import java.util.List;

/**
 * An adapter that fills a view having list of Restaurant objects as input data.
 */
public class RestaurantAdapter extends ArrayAdapter<Restaurant>
{
  private LayoutInflater mInflater;
  private int mResource;

  /**
   * Constructor
   *
   * @param context     The current context.
   * @param resource    The resource ID for a layout file containing a TextView to use when
   *                    instantiating views.
   * @param restaurants The objects to represent in the ListView.
   */
  public RestaurantAdapter( Context context, int resource, List<Restaurant> restaurants )
  {
    super( context, resource, restaurants );
    mResource = resource;
    mInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public View getView( int position, View convertView, ViewGroup parent )
  {
    View view = convertView == null ? mInflater.inflate( mResource, parent, false ) : convertView;

    TextView restaurantNameView = (TextView) view.findViewById( R.id.restaurantName );
    TextView cuisineView = (TextView) view.findViewById( R.id.restaurantCuisine );
    TextView locationsNumberView = (TextView) view.findViewById( R.id.restaurantLocations );

    Restaurant item = getItem( position );

    restaurantNameView.setText( item.getName() );
    cuisineView.setText( item.getCuisine() );
    String locationsNumberTextTemplate = getContext().getResources().getQuantityString( R.plurals.restaurant_locations, item.getLocations().size() );
    locationsNumberView.setText( String.format( locationsNumberTextTemplate, item.getLocations().size() ) );

    return view;
  }
}