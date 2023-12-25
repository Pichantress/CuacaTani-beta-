// Generated by view binder compiler. Do not edit!
package com.capstone.cuacatani.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.capstone.cuacatani.R;
import com.capstone.cuacatani.custom.EditEmail;
import com.capstone.cuacatani.custom.EditPassword;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySignupBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView already;

  @NonNull
  public final Button btnSignup;

  @NonNull
  public final EditEmail emailEditText;

  @NonNull
  public final TextInputLayout emailEditTextLayout;

  @NonNull
  public final ImageView imgSignup;

  @NonNull
  public final TextInputEditText nameEditText;

  @NonNull
  public final TextInputLayout nameEditTextLayout;

  @NonNull
  public final EditPassword passEditText;

  @NonNull
  public final TextInputLayout passEditTextLayout;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final TextView tvEmail;

  @NonNull
  public final TextView tvLogin;

  @NonNull
  public final TextView tvName;

  @NonNull
  public final TextView tvPassword;

  private ActivitySignupBinding(@NonNull ConstraintLayout rootView, @NonNull TextView already,
      @NonNull Button btnSignup, @NonNull EditEmail emailEditText,
      @NonNull TextInputLayout emailEditTextLayout, @NonNull ImageView imgSignup,
      @NonNull TextInputEditText nameEditText, @NonNull TextInputLayout nameEditTextLayout,
      @NonNull EditPassword passEditText, @NonNull TextInputLayout passEditTextLayout,
      @NonNull ProgressBar progressBar, @NonNull TextView tvEmail, @NonNull TextView tvLogin,
      @NonNull TextView tvName, @NonNull TextView tvPassword) {
    this.rootView = rootView;
    this.already = already;
    this.btnSignup = btnSignup;
    this.emailEditText = emailEditText;
    this.emailEditTextLayout = emailEditTextLayout;
    this.imgSignup = imgSignup;
    this.nameEditText = nameEditText;
    this.nameEditTextLayout = nameEditTextLayout;
    this.passEditText = passEditText;
    this.passEditTextLayout = passEditTextLayout;
    this.progressBar = progressBar;
    this.tvEmail = tvEmail;
    this.tvLogin = tvLogin;
    this.tvName = tvName;
    this.tvPassword = tvPassword;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySignupBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySignupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_signup, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySignupBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.already;
      TextView already = ViewBindings.findChildViewById(rootView, id);
      if (already == null) {
        break missingId;
      }

      id = R.id.btn_signup;
      Button btnSignup = ViewBindings.findChildViewById(rootView, id);
      if (btnSignup == null) {
        break missingId;
      }

      id = R.id.emailEditText;
      EditEmail emailEditText = ViewBindings.findChildViewById(rootView, id);
      if (emailEditText == null) {
        break missingId;
      }

      id = R.id.emailEditTextLayout;
      TextInputLayout emailEditTextLayout = ViewBindings.findChildViewById(rootView, id);
      if (emailEditTextLayout == null) {
        break missingId;
      }

      id = R.id.img_signup;
      ImageView imgSignup = ViewBindings.findChildViewById(rootView, id);
      if (imgSignup == null) {
        break missingId;
      }

      id = R.id.nameEditText;
      TextInputEditText nameEditText = ViewBindings.findChildViewById(rootView, id);
      if (nameEditText == null) {
        break missingId;
      }

      id = R.id.nameEditTextLayout;
      TextInputLayout nameEditTextLayout = ViewBindings.findChildViewById(rootView, id);
      if (nameEditTextLayout == null) {
        break missingId;
      }

      id = R.id.passEditText;
      EditPassword passEditText = ViewBindings.findChildViewById(rootView, id);
      if (passEditText == null) {
        break missingId;
      }

      id = R.id.passEditTextLayout;
      TextInputLayout passEditTextLayout = ViewBindings.findChildViewById(rootView, id);
      if (passEditTextLayout == null) {
        break missingId;
      }

      id = R.id.progress_bar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.tv_email;
      TextView tvEmail = ViewBindings.findChildViewById(rootView, id);
      if (tvEmail == null) {
        break missingId;
      }

      id = R.id.tv_login;
      TextView tvLogin = ViewBindings.findChildViewById(rootView, id);
      if (tvLogin == null) {
        break missingId;
      }

      id = R.id.tv_name;
      TextView tvName = ViewBindings.findChildViewById(rootView, id);
      if (tvName == null) {
        break missingId;
      }

      id = R.id.tv_password;
      TextView tvPassword = ViewBindings.findChildViewById(rootView, id);
      if (tvPassword == null) {
        break missingId;
      }

      return new ActivitySignupBinding((ConstraintLayout) rootView, already, btnSignup,
          emailEditText, emailEditTextLayout, imgSignup, nameEditText, nameEditTextLayout,
          passEditText, passEditTextLayout, progressBar, tvEmail, tvLogin, tvName, tvPassword);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}