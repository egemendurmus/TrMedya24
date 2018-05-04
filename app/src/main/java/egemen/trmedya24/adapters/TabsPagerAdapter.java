package egemen.trmedya24.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import egemen.trmedya24.fragments.DailyProgramListFragments;

public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    private String tabTitles[] = new String[]{"PAZARTESİ", "SALI", "ÇARŞAMBA", "PERŞEMBE", "CUMA", "CUMARTESİ", "PAZAR"};

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new DailyProgramListFragments();
            case 1:
                return new DailyProgramListFragments();
            case 2:
                return new DailyProgramListFragments();
            case 3:
                return new DailyProgramListFragments();
            case 4:
                return new DailyProgramListFragments();
            case 5:
                return new DailyProgramListFragments();
            case 6:
                return new DailyProgramListFragments();
        }

        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return 7;
    }

}