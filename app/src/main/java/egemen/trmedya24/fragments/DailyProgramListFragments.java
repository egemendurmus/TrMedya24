package egemen.trmedya24.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import egemen.trmedya24.MainActivity;
import egemen.trmedya24.R;
import egemen.trmedya24.adapters.DailyProgramListAdapter;
import egemen.trmedya24.helper.FragmentPagerHelperInterface;


public class DailyProgramListFragments extends Fragment implements FragmentPagerHelperInterface {
    ListView listView;

    public DailyProgramListFragments() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.daily_schedule_fragment, container, false);
        listView = (root).findViewById(R.id.list);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentBecameVisible();
    }

    @Override
    public void fragmentBecameVisible() {
        DailyProgramListAdapter programListAdapter = new DailyProgramListAdapter(getActivity(), MainActivity.responseList);
        listView.setAdapter(programListAdapter);
        programListAdapter.notifyDataSetChanged();
        listView.invalidateViews();


    }
}