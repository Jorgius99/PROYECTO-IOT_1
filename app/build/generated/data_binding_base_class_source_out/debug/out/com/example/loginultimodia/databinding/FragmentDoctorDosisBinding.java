// Generated by view binder compiler. Do not edit!
package com.example.loginultimodia.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public final class FragmentDoctorDosisBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView imageView01;

  @NonNull
  public final TextView textView8;

  @NonNull
  public final TextView tusHabs;

  private FragmentDoctorDosisBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView imageView01, @NonNull TextView textView8, @NonNull TextView tusHabs) {
    this.rootView = rootView;
    this.imageView01 = imageView01;
    this.textView8 = textView8;
    this.tusHabs = tusHabs;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDoctorDosisBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDoctorDosisBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_doctor_dosis, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDoctorDosisBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imageView01;
      ImageView imageView01 = ViewBindings.findChildViewById(rootView, id);
      if (imageView01 == null) {
        break missingId;
      }

      id = R.id.textView8;
      TextView textView8 = ViewBindings.findChildViewById(rootView, id);
      if (textView8 == null) {
        break missingId;
      }

      id = R.id.tus_habs;
      TextView tusHabs = ViewBindings.findChildViewById(rootView, id);
      if (tusHabs == null) {
        break missingId;
      }

      return new FragmentDoctorDosisBinding((ConstraintLayout) rootView, imageView01, textView8,
          tusHabs);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
