package com.backendless.samples.restaurant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.backendless.samples.restaurant.R;
import com.backendless.samples.restaurant.entities.Location;

import java.util.List;

/**
 * An adapter that fills a view having list of Location objects as input data.
 */
public class LocationAdapter extends ArrayAdapter<Location>
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
  public LocationAdapter( Context context, int resource, List<Location> restaurants )
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

    TextView cityView = (TextView) view.findViewById( R.id.city );
    TextView addressView = (TextView) view.findViewById( R.id.address );

    Location item = getItem( position );

    cityView.setText( item.getCity() );
    addressView.setText( item.getStreetAddress() );

    return view;
  }
}