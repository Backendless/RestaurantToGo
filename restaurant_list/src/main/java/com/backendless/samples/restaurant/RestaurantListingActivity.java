package com.backendless.samples.restaurant;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import com.backendless.samples.restaurant.adapters.RestaurantAdapter;
import com.backendless.samples.restaurant.entities.Restaurant;
import com.backendless.samples.restaurant.utility.LoadingCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Displays the list of restaurants.
 */
public class RestaurantListingActivity extends ListActivity
{
  private BackendlessCollection<Restaurant> restaurants;
  private List<Restaurant> totalRestaurants = new ArrayList<>();
  private boolean isLoadingItems = false;
  private RestaurantAdapter adapter;

  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_restaurant_listing );

    adapter = new RestaurantAdapter( RestaurantListingActivity.this, R.layout.list_item_restaurant, totalRestaurants );
    setListAdapter( adapter );

    QueryOptions queryOptions = new QueryOptions();
    queryOptions.setRelated( Arrays.asList( "locations" ) );

    BackendlessDataQuery query = new BackendlessDataQuery( queryOptions );

    Backendless.Data.of( Restaurant.class ).find( query, new LoadingCallback<BackendlessCollection<Restaurant>>( this, getString( R.string.loading_restaurants ), true )
    {
      @Override
      public void handleResponse( BackendlessCollection<Restaurant> restaurantsBackendlessCollection )
      {
        restaurants = restaurantsBackendlessCollection;

        addMoreItems( restaurantsBackendlessCollection );

        super.handleResponse( restaurantsBackendlessCollection );
      }
    } );

    ListView list = (ListView) findViewById( android.R.id.list );
    list.setOnScrollListener( new AbsListView.OnScrollListener()
    {
      @Override
      public void onScrollStateChanged( AbsListView view, int scrollState )
      {

      }

      @Override
      public void onScroll( AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount )
      {
        if( needToLoadItems( firstVisibleItem, visibleItemCount, totalItemCount ) )
        {
          isLoadingItems = true;

          restaurants.nextPage( new LoadingCallback<BackendlessCollection<Restaurant>>( RestaurantListingActivity.this )
          {
            @Override
            public void handleResponse( BackendlessCollection<Restaurant> nextPage )
            {
              restaurants = nextPage;

              addMoreItems( nextPage );

              isLoadingItems = false;
            }
          } );
        }
      }
    } );
  }

  /**
   * Determines whether is it needed to load more items as user scrolls down.
   *
   * @param firstVisibleItem number of the first item visible on screen
   * @param visibleItemCount number of items visible on screen
   * @param totalItemCount   total number of items in list
   * @return true if user is about to reach the end of a list, else false
   */
  private boolean needToLoadItems( int firstVisibleItem, int visibleItemCount, int totalItemCount )
  {
    return !isLoadingItems && totalItemCount != 0 && totalItemCount - (visibleItemCount + firstVisibleItem) < visibleItemCount / 2;
  }

  /**
   * Adds more items to list and notifies Android that dataset has changed.
   *
   * @param nextPage list of new items
   */
  private void addMoreItems( BackendlessCollection<Restaurant> nextPage )
  {
    totalRestaurants.addAll( nextPage.getCurrentPage() );
    adapter.notifyDataSetChanged();
  }
}
