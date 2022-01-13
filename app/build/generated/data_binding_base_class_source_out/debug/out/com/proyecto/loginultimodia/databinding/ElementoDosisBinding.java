// Generated by view binder compiler. Do not edit!
package com.proyecto.loginultimodia.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proyecto.loginultimodia.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ElementoDosisBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView HoraSolo1;

  @NonNull
  public final ConstraintLayout dosis3;

  @NonNull
  public final TextView fecha1;

  @NonNull
  public final TextView pastilla;

  @NonNull
  public final TextView txtUsername1;

  private ElementoDosisBinding(@NonNull ConstraintLayout rootView, @NonNull TextView HoraSolo1,
      @NonNull ConstraintLayout dosis3, @NonNull TextView fecha1, @NonNull TextView pastilla,
      @NonNull TextView txtUsername1) {
    this.rootView = rootView;
    this.HoraSolo1 = HoraSolo1;
    this.dosis3 = dosis3;
    this.fecha1 = fecha1;
    this.pastilla = pastilla;
    this.txtUsername1 = txtUsername1;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ElementoDosisBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ElementoDosisBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.elemento_dosis, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ElementoDosisBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.HoraSolo1;
      TextView HoraSolo1 = ViewBindings.findChildViewById(rootView, id);
      if (HoraSolo1 == null) {
        break missingId;
      }

      id = R.id.dosis3;
      ConstraintLayout dosis3 = ViewBindings.findChildViewById(rootView, id);
      if (dosis3 == null) {
        break missingId;
      }

      id = R.id.fecha1;
      TextView fecha1 = ViewBindings.findChildViewById(rootView, id);
      if (fecha1 == null) {
        break missingId;
      }

      id = R.id.pastilla;
      TextView pastilla = ViewBindings.findChildViewById(rootView, id);
      if (pastilla == null) {
        break missingId;
      }

      id = R.id.txtUsername1;
      TextView txtUsername1 = ViewBindings.findChildViewById(rootView, id);
      if (txtUsername1 == null) {
        break missingId;
      }

      return new ElementoDosisBinding((ConstraintLayout) rootView, HoraSolo1, dosis3, fecha1,
          pastilla, txtUsername1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
