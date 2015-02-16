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
  }

  public void loginUser( String email, String password, AsyncCallback<BackendlessUser> loginCallback )
  {
    Backendless.UserService.login( email, password, loginCallback );
  }

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

  public boolean isLoginValuesValid( CharSequence email, CharSequence password )
  {
    return Validator.isEmailValid( this, email ) && Validator.isPasswordValid( this, password );
  }

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
