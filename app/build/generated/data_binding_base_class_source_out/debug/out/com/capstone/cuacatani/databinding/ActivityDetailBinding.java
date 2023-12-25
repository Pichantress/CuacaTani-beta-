// Generated by view binder compiler. Do not edit!
package com.capstone.cuacatani.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.capstone.cuacatani.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDetailBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView imgPlant;

  @NonNull
  public final RelativeLayout rlKonten;

  @NonNull
  public final TextView tvHow;

  @NonNull
  public final TextView tvHowText;

  @NonNull
  public final TextView tvPanen;

  @NonNull
  public final TextView tvReq;

  @NonNull
  public final TextView tvReqText;

  @NonNull
  public final TextView tvTitle;

  private ActivityDetailBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView imgPlant,
      @NonNull RelativeLayout rlKonten, @NonNull TextView tvHow, @NonNull TextView tvHowText,
      @NonNull TextView tvPanen, @NonNull TextView tvReq, @NonNull TextView tvReqText,
      @NonNull TextView tvTitle) {
    this.rootView = rootView;
    this.imgPlant = imgPlant;
    this.rlKonten = rlKonten;
    this.tvHow = tvHow;
    this.tvHowText = tvHowText;
    this.tvPanen = tvPanen;
    this.tvReq = tvReq;
    this.tvReqText = tvReqText;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.img_plant;
      ImageView imgPlant = ViewBindings.findChildViewById(rootView, id);
      if (imgPlant == null) {
        break missingId;
      }

      id = R.id.rl_konten;
      RelativeLayout rlKonten = ViewBindings.findChildViewById(rootView, id);
      if (rlKonten == null) {
        break missingId;
      }

      id = R.id.tv_how;
      TextView tvHow = ViewBindings.findChildViewById(rootView, id);
      if (tvHow == null) {
        break missingId;
      }

      id = R.id.tv_howText;
      TextView tvHowText = ViewBindings.findChildViewById(rootView, id);
      if (tvHowText == null) {
        break missingId;
      }

      id = R.id.tv_panen;
      TextView tvPanen = ViewBindings.findChildViewById(rootView, id);
      if (tvPanen == null) {
        break missingId;
      }

      id = R.id.tv_req;
      TextView tvReq = ViewBindings.findChildViewById(rootView, id);
      if (tvReq == null) {
        break missingId;
      }

      id = R.id.tv_reqText;
      TextView tvReqText = ViewBindings.findChildViewById(rootView, id);
      if (tvReqText == null) {
        break missingId;
      }

      id = R.id.tv_title;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new ActivityDetailBinding((ConstraintLayout) rootView, imgPlant, rlKonten, tvHow,
          tvHowText, tvPanen, tvReq, tvReqText, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
