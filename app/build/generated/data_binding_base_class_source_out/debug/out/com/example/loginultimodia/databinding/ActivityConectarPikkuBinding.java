// Generated by view binder compiler. Do not edit!
package com.example.loginultimodia.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.loginultimodia.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityConectarPikkuBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonConnect;

  @NonNull
  public final Button buttonScan;

  @NonNull
  public final Button pararPikku;

  @NonNull
  public final Switch switchLecturaPikku;

  @NonNull
  public final TextView textConnect;

  @NonNull
  public final TextView textScan;

  private ActivityConectarPikkuBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonConnect, @NonNull Button buttonScan, @NonNull Button pararPikku,
      @NonNull Switch switchLecturaPikku, @NonNull TextView textConnect,
      @NonNull TextView textScan) {
    this.rootView = rootView;
    this.buttonConnect = buttonConnect;
    this.buttonScan = buttonScan;
    this.pararPikku = pararPikku;
    this.switchLecturaPikku = switchLecturaPikku;
    this.textConnect = textConnect;
    this.textScan = textScan;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityConectarPikkuBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityConectarPikkuBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_conectar_pikku, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityConectarPikkuBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonConnect;
      Button buttonConnect = ViewBindings.findChildViewById(rootView, id);
      if (buttonConnect == null) {
        break missingId;
      }

      id = R.id.buttonScan;
      Button buttonScan = ViewBindings.findChildViewById(rootView, id);
      if (buttonScan == null) {
        break missingId;
      }

      id = R.id.pararPikku;
      Button pararPikku = ViewBindings.findChildViewById(rootView, id);
      if (pararPikku == null) {
        break missingId;
      }

      id = R.id.switchLecturaPikku;
      Switch switchLecturaPikku = ViewBindings.findChildViewById(rootView, id);
      if (switchLecturaPikku == null) {
        break missingId;
      }

      id = R.id.textConnect;
      TextView textConnect = ViewBindings.findChildViewById(rootView, id);
      if (textConnect == null) {
        break missingId;
      }

      id = R.id.textScan;
      TextView textScan = ViewBindings.findChildViewById(rootView, id);
      if (textScan == null) {
        break missingId;
      }

      return new ActivityConectarPikkuBinding((ConstraintLayout) rootView, buttonConnect,
          buttonScan, pararPikku, switchLecturaPikku, textConnect, textScan);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
