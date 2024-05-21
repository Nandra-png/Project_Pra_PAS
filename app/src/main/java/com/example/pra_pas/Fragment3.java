package com.example.pra_pas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Fragment3 extends Fragment {

    private ImageView close;
    private View profileDetails;
    private Animation rotateUpAnimation;
    private ImageView lmenu;
    private boolean isExpanded = false;
    private Animation slideInAnimation;
    private Animation scaleDownAnimation;
    private TextView bd_btn;
    private TextView br_btn;
    private Animation rotateDownAnimation;

    private static final String PREFS_NAME = "MyPrefsFile";

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);

        bd_btn = view.findViewById(R.id.bangundatar);
        br_btn = view.findViewById(R.id.bangunruang);
        close = view.findViewById(R.id.muleh);
        lmenu = view.findViewById(R.id.lmenu);
        profileDetails = view.findViewById(R.id.profile_details);
        slideInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_from_bottom);
        scaleDownAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_down);
        rotateDownAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_down);
        rotateUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_up);

        bd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment1 fragment1 = new Fragment1();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment1);
                transaction.addToBackStack(null);
                transaction.commit();
                ((MainActivity) requireActivity()).setSelectedMenuItem(R.id.menu_item1);
            }
        });

        br_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment2 fragment2 = new Fragment2();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment2);
                transaction.addToBackStack(null);
                transaction.commit();
                ((MainActivity) requireActivity()).setSelectedMenuItem(R.id.menu_item2);
            }
        });

        lmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    collapseProfileDetails();
                } else {
                    expandProfileDetails();
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_dialog, null);
                builder.setView(dialogView);

                TextView messageTextView = dialogView.findViewById(R.id.dialog_message);
                messageTextView.setText("Apakah Anda ingin keluar?");

                Button buttonYes = dialogView.findViewById(R.id.button_yes);
                Button buttonNo = dialogView.findViewById(R.id.button_no);

                AlertDialog dialog = builder.create();

                buttonYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences settings = requireActivity().getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.clear();
                        editor.apply();

                        Intent intent = new Intent(requireActivity(), login.class);
                        startActivity(intent);
                        requireActivity().finishAffinity();
                        dialog.dismiss();
                    }
                });

                buttonNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        return view;
    }

    private void expandProfileDetails() {
        lmenu.startAnimation(rotateDownAnimation);
        profileDetails.setVisibility(View.VISIBLE);
        profileDetails.startAnimation(slideInAnimation);
        updateRotation(lmenu, -180);
        isExpanded = true;
    }

    private void collapseProfileDetails() {
        lmenu.startAnimation(rotateUpAnimation);
        profileDetails.startAnimation(scaleDownAnimation);
        profileDetails.setVisibility(View.GONE);
        isExpanded = false;
        updateRotation(lmenu, 0);
    }

    private void updateRotation(ImageView imageView, float degrees) {
        imageView.setRotation(degrees);
    }
}
