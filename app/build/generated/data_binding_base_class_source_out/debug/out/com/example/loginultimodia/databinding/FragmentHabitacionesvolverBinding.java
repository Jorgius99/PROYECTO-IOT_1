// Generated by view binder compiler. Do not edit!
package com.example.loginultimodia.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.loginultimodia.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHabitacionesvolverBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final RecyclerView recyclerViewH;

  @NonNull
  public final TextView volverbotonhabit;

  private FragmentHabitacionesvolverBinding(@NonNull FrameLayout rootView,
      @NonNull RecyclerView recyclerViewH, @NonNull TextView volverbotonhabit) {
    this.rootView = rootView;
    this.recyclerViewH = recyclerViewH;
    this.volverbotonhabit = volverbotonhabit;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHabitacionesvolverBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHabitacionesvolverBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_habitacionesvolver, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHabitacionesvolverBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.recyclerViewH;
      RecyclerView recyclerViewH = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewH == null) {
        break missingId;
      }

      id = R.id.volverbotonhabit;
      TextView volverbotonhabit = ViewBindings.findChildViewById(rootView, id);
      if (volverbotonhabit == null) {
        break missingId;
      }

      return new FragmentHabitacionesvolverBinding((FrameLayout) rootView, recyclerViewH,
          volverbotonhabit);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
