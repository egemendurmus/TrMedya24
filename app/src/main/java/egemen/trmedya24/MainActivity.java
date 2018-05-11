package egemen.trmedya24;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import egemen.trmedya24.adapters.TabsPagerAdapter;
import egemen.trmedya24.api.ApiClient;
import egemen.trmedya24.api.RestInterfaceController;
import egemen.trmedya24.helper.AppHelper;
import egemen.trmedya24.helper.FragmentPagerHelperInterface;
import egemen.trmedya24.model.MainResponse;
import egemen.trmedya24.model.ProgramsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button navBtn;
    RestInterfaceController restInterface;
    private final String jsonFile = "response";
    public static String responseData;
    public static int positions;
    public static List<ProgramsResponse> responseList = new ArrayList<ProgramsResponse>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (AppHelper.checkInternetConnection(MainActivity.this) == false) {
            AppHelper.connectionMessage(MainActivity.this, "İnternet Bağlantını Kontrol Et!");

        }

        getDailyProgramList();
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);
        navBtn = findViewById(R.id.nav_btn);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        navBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(tabLayout, "Bu Menü de herhangi bir işlem yok", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

    public void getDailyProgramList() {
        restInterface = ApiClient.getClient().create(RestInterfaceController.class);
        Call<MainResponse> call = restInterface.getJsonValues();
        call.enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {

                try {
                    if (response.code() == 500) {
                        //  AppHelper.connectionMessage(MainActivity.this, String.valueOf(response.body().errorMessage));
                        //todo servis çalışmadığı için ekledim silinicek
                        localfiles();
                        return;
                    }
                    if (response.body().errorCode == 0) {
                        //  AppHelper.connectionMessage(MainActivity.this, String.valueOf(response.body().errorMessage));
                        //todo servis çalışmadığı için ekledim silinicek
                        localfiles();
                        return;
                    }
                    if (response.code() == 503) {
                        // AppHelper.connectionMessage(MainActivity.this, String.valueOf(response.body().errorMessage));
                        //todo servis çalışmadığı için ekledim silinicek
                        localfiles();
                        return;
                    }

                    if (response.code() == 401 || response.code() == 403) {
                        AppHelper.connectionMessage(MainActivity.this, String.valueOf(response.body().errorMessage));
                        return;
                    }
                    setupViewPager(viewPager, response.body());
                } catch (Exception e) {
                    AppHelper.connectionMessage(MainActivity.this, String.valueOf(e));
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                Log.e("errors ", String.valueOf(t));
                localfiles();

            }
        });

    }

    //todo servis çalışmadığı için ekledim silinicek
    private void localfiles() {
        Gson gson = new Gson();
        try {
            String jsons = null;
            try {
                jsons = getAssetsJSON(jsonFile);
                MainResponse staff = gson.fromJson(jsons, MainResponse.class);
                setupViewPager(viewPager, staff);
                AppHelper.createProgressDialog(MainActivity.this).cancel();

            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAssetsJSON(String fileName) {
        String json = null;
        try {
            InputStream inputStream = this.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    private void setupViewPager(final ViewPager viewPager, final MainResponse data) {
        setDailyProgramList(0, data);
        final TabsPagerAdapter mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(4);
        getPagerInterface(mAdapter, 0, data);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                positions = position;
               // setDailyProgramList(position, data);
                getPagerInterface(mAdapter, position,data);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void getPagerInterface(TabsPagerAdapter mAdapter, int position, MainResponse data) {
        FragmentPagerHelperInterface fragment =
                (FragmentPagerHelperInterface) mAdapter.instantiateItem(viewPager, position);
        if (fragment != null) {
            responseList.clear();
            fragment.fragmentBecameVisible();
            setDailyProgramList(position, data);
        }
    }

    private void setDailyProgramList(int position, MainResponse data) {
        Gson gson = new Gson();
        responseData = gson.toJson(data);
        positions = position;
        MainResponse staff = gson.fromJson(MainActivity.responseData, MainResponse.class);
        //responseList.clear();
        for (int i = 0; i < staff.data.size(); i++) {
            if (staff.data.get(i).day.equals(String.valueOf(positions))) {
                responseList.add(staff.data.get(i));
            }
        }

    }

}