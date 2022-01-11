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

public final class FragmentAvisosBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final RecyclerView recyclerView1;

  @NonNull
  public final TextView tabdosis;

  private FragmentAvisosBinding(@NonNull FrameLayout rootView, @NonNull RecyclerView recyclerView1,
      @NonNull TextView tabdosis) {
    this.rootView = rootView;
    this.recyclerView1 = recyclerView1;
    this.tabdosis = tabdosis;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAvisosBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAvisosBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_avisos, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAvisosBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.recyclerView1;
      RecyclerView recyclerView1 = ViewBindings.findChildViewById(rootView, id);
      if (recyclerView1 == null) {
        break missingId;
      }

      id = R.id.tabdosis;
      TextView tabdosis = ViewBindings.findChildViewById(rootView, id);
      if (tabdosis == null) {
        break missingId;
      }

      return new FragmentAvisosBinding((FrameLayout) rootView, recyclerView1, tabdosis);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
