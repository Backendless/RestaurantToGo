package com.backendless.samples.restaurant;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import com.robotium.solo.Solo;

import java.util.UUID;

public class RegistrationActivityTest extends ActivityInstrumentationTestCase2<RegistrationActivity>
{
  private Solo solo;

  private EditText nameField;
  private EditText emailField;
  private EditText passwordField;
  private EditText passwordConfirmField;

  private String validName = "Jack Daniels";
  private String validEmail = "jackyd@email.com";
  private String validPassword = "in#jfS3d";

  public RegistrationActivityTest()
  {
    super( RegistrationActivity.class );
  }

  public void setUp() throws Exception
  {
    solo = new Solo( getInstrumentation(), getActivity() );

    nameField = (EditText) solo.getView( R.id.nameField );
    emailField = (EditText) solo.getView( R.id.emailField );
    passwordField = (EditText) solo.getView( R.id.passwordField );
    passwordConfirmField = (EditText) solo.getView( R.id.passwordConfirmField );
  }

  public void testEmptyNameValidation()
  {
    solo.enterText( nameField, "" );
    solo.enterText( emailField, validEmail );
    solo.enterText( passwordField, validPassword );
    solo.enterText( passwordConfirmField, validPassword );

    solo.clickOnButton( solo.getString( R.string.button_register ) );

    assertTrue( "Warning in Toast is missing or invalid", solo.waitForText( solo.getString( R.string.warning_name_empty ) ) );
  }

  public void testNameFromLowercase()
  {
    solo.enterText( nameField, validName.toLowerCase() );
    solo.enterText( emailField, validEmail );
    solo.enterText( passwordField, validPassword );
    solo.enterText( passwordConfirmField, validPassword );

    solo.clickOnButton( solo.getString( R.string.button_register ) );

    assertTrue( "Warning in Toast is missing or invalid", solo.waitForText( solo.getString( R.string.warning_name_lowercase ) ) );
  }

  public void testEmptyEmail()
  {
    solo.enterText( nameField, validName );
    solo.enterText( emailField, "" );
    solo.enterText( passwordField, validPassword );
    solo.enterText( passwordConfirmField, validPassword );

    solo.clickOnButton( solo.getString( R.string.button_register ) );

    assertTrue( "Warning in Toast is missing or invalid", solo.waitForText( solo.getString( R.string.warning_email_empty ) ) );
  }

  public void testInvalidEmail()
  {
    solo.enterText( nameField, validName );
    solo.enterText( emailField, "not_email" );
    solo.enterText( passwordField, validPassword );
    solo.enterText( passwordConfirmField, validPassword );

    solo.clickOnButton( solo.getString( R.string.button_register ) );

    assertTrue( "Warning in Toast is missing or invalid", solo.waitForText( solo.getString( R.string.warning_email_invalid ) ) );
  }

  public void testEmptyPassword()
  {
    solo.enterText( nameField, validName );
    solo.enterText( emailField, validEmail );
    solo.enterText( passwordField, "" );
    solo.enterText( passwordConfirmField, validPassword );

    solo.clickOnButton( solo.getString( R.string.button_register ) );

    assertTrue( "Warning in Toast is missing or invalid", solo.waitForText( solo.getString( R.string.warning_password_empty ) ) );
  }

  public void testNotMatchingPasswords()
  {
    solo.enterText( nameField, validName );
    solo.enterText( emailField, validEmail );
    solo.enterText( passwordField, validPassword );
    solo.enterText( passwordConfirmField, UUID.randomUUID().toString() );

    solo.clickOnButton( solo.getString( R.string.button_register ) );

    assertTrue( "Warning in Toast is missing or invalid", solo.waitForText( solo.getString( R.string.warning_passwords_do_not_match ) ) );
  }

  @Override
  public void tearDown() throws Exception
  {
    solo.finishOpenedActivities();
  }
}