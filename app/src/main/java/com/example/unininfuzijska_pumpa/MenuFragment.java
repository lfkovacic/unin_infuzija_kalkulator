package com.example.unininfuzijska_pumpa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MenuFragment extends DialogFragment {

    private EditText tezinaEditText;
    private EditText visinaEditText;

    private OnCloseListener onCloseListener;

    public MenuFragment() {
        // Required empty public constructor
    }

    public void setOnCloseListener(OnCloseListener onCloseListener) {
        this.onCloseListener = onCloseListener;
    }

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        // Example of accessing values from ParametriEnum
        tezinaEditText = view.findViewById(R.id.tezinaEditText);
        tezinaEditText.setHint(ParametriEnum.TEZINA.getLabel());

        visinaEditText = view.findViewById(R.id.visinaEditText);
        visinaEditText.setHint(ParametriEnum.VISINA.getLabel());

        Button closeButton = view.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClose();
            }
        });

        // Add other UI initialization code here

        return view;
    }

    public interface OnCloseListener {
        void onClose(int tezina, int visina); // Modify the parameters as needed
    }

    private void onClose() {
        if (onCloseListener != null) {

            String tezinaString = tezinaEditText.getText().toString();
            String visinaString = visinaEditText.getText().toString();

            int tezinaValue = Integer.parseInt(tezinaString.length() == 0 ? "0" : tezinaString);
            int visinaValue = Integer.parseInt(visinaString.length() == 0 ? "0" : visinaString);

            onCloseListener.onClose(tezinaValue, visinaValue);
            dismiss();
        }
    }
}
