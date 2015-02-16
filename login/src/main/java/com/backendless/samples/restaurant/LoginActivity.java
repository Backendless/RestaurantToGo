package com.backendless.samples.restaurant;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles user login:
 * - by email and password
 * - with Facebook account
 * - with Twitter account
 */
public class LoginActivity extends Activity
{
  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_login );

    Backendless.initApp( this, BackendSettings.APPLICATION_ID, BackendSettings.ANDROID_SECRET_KEY, BackendSettings.VERSION );

    Button loginButton = (Button) findViewById( R.id.loginButton );
    loginButton.setOnClickListener( createLoginButtonListener() );

    Button loginFacebookButton = (Button) findViewById( R.id.loginFacebookButton );
    loginFacebookButton.setOnClickListener( createLoginWithFacebookButtonListener() );

    Button loginTwitterButton = (Button) findViewById( R.id.loginTwitterButton );
    loginTwitterButton.setOnClickListener( createLoginWithTwitterButtonListener() );
  }

  /**
   * Sends a request to Backendless to log in user by email and password.
   *
   * @param email         user's email
   * @param password      user's password
   * @param loginCallback a callback, containing actions to be executed on request result
   */
  public void loginUser( String email, String password, AsyncCallback<BackendlessUser> loginCallback )
  {
    Backendless.UserService.login( email, password, loginCallback );
  }

  /**
   * Sends a request to Backendless to log in user with Facebook account.
   * Fetches Facebook user's name and saves it on Backendless.
   *
   * @param loginCallback a callback, containing actions to be executed on request result
   */
  public void loginFacebookUser( AsyncCallback<BackendlessUser> loginCallback )
  {
    Map<String, String> fieldsMappings = new HashMap<>();
    fieldsMappings.put( "name", "name" );
    Backendless.UserService.loginWithFacebook( this, null, fieldsMappings, Collections.<String>emptyList(), loginCallback );
  }

  /**
   * Sends a request to Backendless to log in user with Twitter account.
   * Fetches Twitter user's name and saves it on Backendless.
   *
   * @param loginCallback a callback, containing actions to be executed on request result
   */
  public void loginTwitterUser( AsyncCallback<BackendlessUser> loginCallback )
  {
    Map<String, String> fieldsMappings = new HashMap<>();
    fieldsMappings.put( "name", "name" );
    Backendless.UserService.loginWithTwitter( this, null, fieldsMappings, loginCallback );
  }

  /**
   * Creates a listener, which proceeds with login by email and password on button click.
   *
   * @return a listener, handling login button click
   */
  public View.OnClickListener createLoginButtonListener()
  {
    return new View.OnClickListener()
    {
      @Override
      public void onClick( View v )
      {
        EditText emailField = (EditText) findViewById( R.id.emailField );
        EditText passwordField = (EditText) findViewById( R.id.passwordField );

        CharSequence email = emailField.getText();
        CharSequence password = passwordField.getText();

        if( isLoginValuesValid( email, password ) )
        {
          LoadingCallback<BackendlessUser> loginCallback = createLoginCallback();

          loginCallback.showLoading();
          loginUser( email.toString(), password.toString(), loginCallback );
        }
      }
    };
  }

  /**
   * Creates a listener, which proceeds with login with Facebook on button click.
   *
   * @return a listener, handling login with Facebook button click
   */
  public View.OnClickListener createLoginWithFacebookButtonListener()
  {
    return new View.OnClickListener()
    {
      @Override
      public void onClick( View v )
      {
        LoadingCallback<BackendlessUser> loginCallback = createLoginCallback();

        loginCallback.showLoading();
        loginFacebookUser( loginCallback );
      }
    };
  }

  /**
   * Creates a listener, which proceeds with login with Twitter on button click.
   *
   * @return a listener, handling login with Facebook button click
   */
  public View.OnClickListener createLoginWithTwitterButtonListener()
  {
    return new View.OnClickListener()
    {

      @Override
      public void onClick( View v )
      {
        LoadingCallback<BackendlessUser> loginCallback = createLoginCallback();

        loginCallback.showLoading();
        loginTwitterUser( loginCallback );
      }
    };
  }

  /**
   * Validates the values, which user entered on login screen.
   * Shows Toast with a warning if something is wrong.
   *
   * @param email    user's email
   * @param password user's password
   * @return true if all values are OK, false if something is wrong
   */
  public boolean isLoginValuesValid( CharSequence email, CharSequence password )
  {
    return Validator.isEmailValid( this, email ) && Validator.isPasswordValid( this, password );
  }

  /**
   * Creates a callback, containing actions to be executed on login request result.
   * Shows a Toast with BackendlessUser's objectId on success,
   * show a dialog with an error message on failure.
   *
   * @return a callback, containing actions to be executed on login request result
   */
  public LoadingCallback<BackendlessUser> createLoginCallback()
  {
    return new LoadingCallback<BackendlessUser>( this, getString( R.string.loading_login ) )
    {
      @Override
      public void handleResponse( BackendlessUser loggedInUser )
      {
        super.handleResponse( loggedInUser );
        Toast.makeText( LoginActivity.this, String.format( getString( R.string.info_logged_in ), loggedInUser.getObjectId() ), Toast.LENGTH_LONG ).show();
      }
    };
  }
}
