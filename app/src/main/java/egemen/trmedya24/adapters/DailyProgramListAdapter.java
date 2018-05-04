package egemen.trmedya24.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import egemen.trmedya24.R;
import egemen.trmedya24.model.ProgramsResponse;

public class DailyProgramListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ProgramsResponse> mProgramsResponseList;

    public DailyProgramListAdapter(Activity activity, List<ProgramsResponse> programsResponses) {
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mProgramsResponseList = programsResponses;
    }

    @Override
    public int getCount() {
        return mProgramsResponseList.size();
    }

    @Override
    public ProgramsResponse getItem(int position) {
        return (ProgramsResponse) mProgramsResponseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.daily_programs_list_adapter, null);
        TextView timeTxt =
                satirView.findViewById(R.id.time_textview);
        TextView headerTxt =
                satirView.findViewById(R.id.header_textview);

        TextView descriptionTxt =
                satirView.findViewById(R.id.description_textview);


        ProgramsResponse programsResponse = (ProgramsResponse) mProgramsResponseList.get(position);
        timeTxt.setText(programsResponse.startTime);
        headerTxt.setText(programsResponse.title);
        descriptionTxt.setText(programsResponse.shortContent);

        return satirView;
    }

   /* public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mProgramsResponseList.clear();
        if (charText.length() == 0) {
            mProgramsResponseList.addAll(arraylist);
        } else {
            for (ProgramsResponse wp : arraylist) {
                if (wp.day.toLowerCase(Locale.getDefault()).contains(charText)) {
                    mProgramsResponseList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
        System.out.println(mProgramsResponseList.size()+" ");
    }
*/
}