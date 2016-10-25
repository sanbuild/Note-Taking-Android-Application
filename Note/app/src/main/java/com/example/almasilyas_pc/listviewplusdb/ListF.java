package com.example.almasilyas_pc.listviewplusdb;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListF.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListF#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListF extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;


    public ListF( ) {

        // Required empty public constructor
    }




    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListF.
     */
    // TODO: Rename and change types and number of parameters
    public static ListF newInstance(String param1, String param2) {
        ListF fragment = new ListF();
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


    ListView listView;
    FrameLayout frameLayout;
    custom_adapter adapter;
    AlertDialog.Builder builder;
    Db db;
    int id;
    Shopping shopping;
    boolean flag = false;


    public void setId(int i)
    {
        id=i;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        frameLayout=(FrameLayout)inflater.inflate(R.layout.fragment_list, container, false);
        listView=(ListView)frameLayout.findViewById(R.id.listView);
        db=new Db(getActivity(),"abc",null,1);
        builder = new AlertDialog.Builder(getActivity());
        final int button_id = id;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if(button_id == R.id.shopping)
                {
                    LinearLayout layouttwo = (LinearLayout) view;
                    final TextView  Tv1 = (TextView) layouttwo.findViewById(R.id.textView);
                    final TextView  Tv2 = (TextView) layouttwo.findViewById(R.id.textView2);
                    builder.setMessage("Do you want to edit or Delete ");
                    builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.Shopping_delete(position);
                            adapter = new custom_adapter(getActivity(),button_id);
                            listView.setAdapter(adapter);

                        }
                    });
                    builder.setNegativeButton("EDIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            Intent i = new Intent(getActivity(),Shopping.class);
                              i.putExtra("edit",false);// Jo bhi pass krna HOTA HY ACTIVITY KO WO INTENT KY THROUGH KRTY HAIN // agr ma yahan value false set ki hti to ???
                            i.putExtra("shopping_list",Tv1.getText());
                            i.putExtra("shopping_date",Tv2.getText());
                            i.putExtra("position",position);
                            startActivityForResult(i,1);

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
                else if(button_id == R.id.bp)
                {
                    LinearLayout layouttwo = (LinearLayout) view;
                   final TextView  Tv1 = (TextView) layouttwo.findViewById(R.id.textView);
                   final TextView  Tv2 = (TextView) layouttwo.findViewById(R.id.textView2);
                    builder.setMessage("Do you want to edit or Delete ");
                    builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            db.Bp_delete(position);
                            adapter = new custom_adapter(getActivity(),button_id);
                            listView.setAdapter(adapter);

                        }
                    });
                    builder.setNegativeButton("EDIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent i = new Intent(getActivity(),Blood_Pressure.class);
                            i.putExtra("edit",false);// Jo bhi pass krna HOTA HY ACTIVITY KO WO INTENT KY THROUGH KRTY HAIN // agr ma yahan value false set ki hti to ???
                            i.putExtra("bp", Tv1.getText());
                            i.putExtra("time",Tv2.getText());
                            i.putExtra("position",position);
                            startActivityForResult(i, 1);
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();


                }
                else if(button_id == R.id.borrow_money)
                {
                    LinearLayout layoutthree = (LinearLayout) view;
                    final TextView Tv3 = (TextView) layoutthree.findViewById(R.id.tv1_layout2);
                    final TextView Tv4 = (TextView) layoutthree.findViewById(R.id.tv2_layout2);
                    final TextView Tv5 = (TextView) layoutthree.findViewById(R.id.tv3_layout2);
                    builder.setMessage("Do you want to edit or Delete ");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.Borrow_delete(position);
                            adapter = new custom_adapter(getActivity(),button_id);
                            listView.setAdapter(adapter);

                        }
                    });
                    builder.setNegativeButton("EDIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(getActivity(),Borrow_Money.class);
                            i.putExtra("edit",false);// Jo bhi pass krna HOTA HY ACTIVITY KO WO INTENT KY THROUGH KRTY HAIN // agr ma yahan value false set ki hti to ???
                            i.putExtra("money",Tv3.getText());
                            i.putExtra("name",Tv4.getText());
                            i.putExtra("date",Tv5.getText());
                            i.putExtra("position",position);
                            startActivityForResult(i, 1);
                        }
                    });

                }
                else if(button_id == R.id.gps)
                {
                    LinearLayout layoutthree = (LinearLayout) view;
                    final TextView Tv3 = (TextView) layoutthree.findViewById(R.id.tv1_layout2);
                    final TextView Tv4 = (TextView) layoutthree.findViewById(R.id.tv2_layout2);
                    final TextView Tv5 = (TextView) layoutthree.findViewById(R.id.tv3_layout2);
                    builder.setMessage("Do you want to edit or Delete ");
                    builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.Gps_delete(position);
                            adapter = new custom_adapter(getActivity(),button_id);
                            listView.setAdapter(adapter);

                        }
                    });
                    builder.setNegativeButton("EDIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent i = new Intent(getActivity(),GPS.class);
                            i.putExtra("edit",false);// Jo bhi pass krna HOTA HY ACTIVITY KO WO INTENT KY THROUGH KRTY HAIN // agr ma yahan value false set ki hti to ???
                            i.putExtra("longitude",Tv3.getText());
                            i.putExtra("latitude",Tv4.getText());
                            i.putExtra("date",Tv5.getText());
                            i.putExtra("position",position);
                            startActivityForResult(i, 1);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }



            }
        });
        adapter=new custom_adapter(getActivity().getApplicationContext(),id);
        listView.setAdapter(adapter);
        return (View)frameLayout;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == resultCode)
        {
            final int button_id = id;
            adapter = new custom_adapter(getActivity(),button_id);
            listView.setAdapter(adapter);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
