package com.backendless.samples.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoginActivity extends Activity
{
  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_login );

    startActivity( new Intent( this, RegistrationActivity.class ) );
  }
}
