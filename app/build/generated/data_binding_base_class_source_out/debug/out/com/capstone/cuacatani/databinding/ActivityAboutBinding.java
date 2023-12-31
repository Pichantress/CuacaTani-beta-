// Generated by view binder compiler. Do not edit!
package com.capstone.cuacatani.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.capstone.cuacatani.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAboutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CardView cardView;

  @NonNull
  public final TextView email;

  @NonNull
  public final Button logoutButton;

  @NonNull
  public final BottomNavigationView navView;

  @NonNull
  public final CircleImageView profileImage;

  @NonNull
  public final Chip statusUser;

  @NonNull
  public final TextView tvAbout;

  @NonNull
  public final TextView username;

  private ActivityAboutBinding(@NonNull ConstraintLayout rootView, @NonNull CardView cardView,
      @NonNull TextView email, @NonNull Button logoutButton, @NonNull BottomNavigationView navView,
      @NonNull CircleImageView profileImage, @NonNull Chip statusUser, @NonNull TextView tvAbout,
      @NonNull TextView username) {
    this.rootView = rootView;
    this.cardView = cardView;
    this.email = email;
    this.logoutButton = logoutButton;
    this.navView = navView;
    this.profileImage = profileImage;
    this.statusUser = statusUser;
    this.tvAbout = tvAbout;
    this.username = username;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAboutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAboutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_about, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAboutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardView;
      CardView cardView = ViewBindings.findChildViewById(rootView, id);
      if (cardView == null) {
        break missingId;
      }

      id = R.id.email;
      TextView email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

      id = R.id.logoutButton;
      Button logoutButton = ViewBindings.findChildViewById(rootView, id);
      if (logoutButton == null) {
        break missingId;
      }

      id = R.id.nav_view;
      BottomNavigationView navView = ViewBindings.findChildViewById(rootView, id);
      if (navView == null) {
        break missingId;
      }

      id = R.id.profileImage;
      CircleImageView profileImage = ViewBindings.findChildViewById(rootView, id);
      if (profileImage == null) {
        break missingId;
      }

      id = R.id.status_user;
      Chip statusUser = ViewBindings.findChildViewById(rootView, id);
      if (statusUser == null) {
        break missingId;
      }

      id = R.id.tv_about;
      TextView tvAbout = ViewBindings.findChildViewById(rootView, id);
      if (tvAbout == null) {
        break missingId;
      }

      id = R.id.username;
      TextView username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      return new ActivityAboutBinding((ConstraintLayout) rootView, cardView, email, logoutButton,
          navView, profileImage, statusUser, tvAbout, username);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
