package com.brianwu.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brianwu.bean.HitBean;
import com.brianwu.bean.Pixabay;
import com.brianwu.fragment.adapter.ListRecyclerViewAdapter;
import com.brianwu.fragment.adapter.MainFragmentAdapter;
import com.brianwu.fragment.adapter.MyItemRecyclerViewAdapter;
import com.brianwu.phototest.R;

/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_DATA = "column-data";
    private static final String ARG_COLUMN_TYPE = "column-type";
    // TODO: Customize parameters
    private Pixabay mColumnData ;
    private OnListFragmentInteractionListener mListener = new OnListFragmentInteractionListener(){

        @Override
        public void onListFragmentInteraction(HitBean item) {
            startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(item.getPageURL())));
        }
    } ;
    private String mColumnType = "";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(Pixabay columnData , MainFragmentAdapter.ViewType type) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_COLUMN_DATA, columnData);
        args.putString(ARG_COLUMN_TYPE, type.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnData = getArguments().getParcelable(ARG_COLUMN_DATA);
            mColumnType = getArguments().getString(ARG_COLUMN_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if(mColumnType.equals(MainFragmentAdapter.ViewType.LIST.toString())){

                recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
                recyclerView.setAdapter(new ListRecyclerViewAdapter(getContext(), mColumnData.getHits() , mListener  ));
            }else {
                recyclerView.setAdapter(new MyItemRecyclerViewAdapter(getContext(), mColumnData.getHits() , mListener  ));
                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
                layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
//                GridLayoutManager manager = new GridLayoutManager(getContext(), 2);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);

            }

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(HitBean item);
    }
}
