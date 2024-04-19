package com.example.noratel;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.noratel.databinding.FragmentMainBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    FragmentMainBinding binding;
    int count = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
       binding = FragmentMainBinding.inflate(inflater,container,false);
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dateAndTime();
        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsCustomDialog();
            }
        });

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCustomDialog();
            }
        });
        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCustomDialog();
            }
        });
        binding.buttonHold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobActivateCustomDialog();
            }
        });

        binding.buttonComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeCustomDialog();
            }
        });

        binding.editTextDateAndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDate();
            }
        });

        binding.buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cou = binding.editTextQuantity.getText().toString();
                if (!cou.isEmpty()){
                    count = Integer.valueOf(cou);
                    count++;
                    binding.editTextQuantity.setText("" + count);
                }else {
                    count++;
                    binding.editTextQuantity.setText("" + count);
                }
            }
        });

        binding.buttonDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cou = binding.editTextQuantity.getText().toString();
                if (!cou.isEmpty()){
                    count = Integer.valueOf(cou);
                    count--;
                    binding.editTextQuantity.setText("" + count);
                }else {
                    count--;
                    binding.editTextQuantity.setText("" + count);
                }
            }
        });

    }
    public void dateAndTime(){
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("hh-mm-ss a");
                                SimpleDateFormat sd = new SimpleDateFormat("MM-dd-yyy");
                                String dataString = sdf.format(date);
                                String d = sd.format(date);
                                binding.textViewDateAndTIme.setText(dataString);
                                binding.textView.setText(d);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        t.start();
    }
    private void saveCustomDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View alertDialog = inflater.inflate(R.layout.custom_dialog,null);
        builder.setView(alertDialog);

        AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.show();

        AppCompatButton buttonClose = alertDialog.findViewById(R.id.buttonClose);
        AppCompatButton buttonConfirm = alertDialog.findViewById(R.id.buttonSelectNext);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Save", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void clearCustomDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View alertDialog = inflater.inflate(R.layout.custom_dialog,null);
        builder.setView(alertDialog);

        AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.show();

        AppCompatButton buttonClose = alertDialog.findViewById(R.id.buttonClose);
        AppCompatButton buttonConfirm = alertDialog.findViewById(R.id.buttonSelectNext);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editTextJobCard.setText("");
                binding.editTextDateAndTime.setText("");
                binding.editTextJobNo.setText("");
                binding.editTextOperations.setText("");
                binding.editTextEmployees.setText("");
                binding.editTextLastRecorded.setText("");
                binding.editTextTarg.setText("");
                binding.editTextComp.setText("");
                binding.editTextQuantity.setText("");
                binding.editTextSerialNumber.setText("");
                count = 0;
                alert.dismiss();
            }
        });
    }
    private void jobActivateCustomDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setCancelable(false);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View alertDialog = inflater.inflate(R.layout.custom_card_hold,null);
        builder.setView(alertDialog);

        AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.show();


    }
    private void completeCustomDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View alertDialog = inflater.inflate(R.layout.custom_complete_dialog,null);
        builder.setView(alertDialog);

        AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.show();

        Button buttonClose = alertDialog.findViewById(R.id.buttonClose);
        Button buttonSelectNext = alertDialog.findViewById(R.id.buttonSelectNext);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        buttonSelectNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void settingsCustomDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setCancelable(false);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View alertDialog = inflater.inflate(R.layout.custom_setting_dialog,null);
        builder.setView(alertDialog);

        AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.show();
    }

    private void handleDate(){
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int date) {
                String dateString  = year +" "+month+" "+date;
                binding.editTextDateAndTime.setText(dateString);

            }
        },YEAR,MONTH,DATE);
        datePickerDialog.show();
    }

    MainFragmentListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (MainFragmentListener) context;
    }

    interface MainFragmentListener{
    }
}