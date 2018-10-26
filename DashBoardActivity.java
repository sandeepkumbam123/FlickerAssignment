package com.skumbam.flickerassignmet;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.skumbam.flickerassignmet.databinding.ActivityDashboardBinding;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by skumbam on 10/25/18.
 */

public class DashBoardActivity extends BaseActivity {
    public static final String TAG = DashBoardActivity.class.getSimpleName();
    private static final int NUMBER_OF_COLUMNS =3;
    private static final int DEFAULT_PAGE_NUMBER = 1;

    // api call prerequisites
    private static final String requestFormatType = "json";
    private static final String requestMethod = "flickr.photos.search";
    private static final boolean requestIsSafeSearch = true;
    private static final boolean isNoJsonCallBack = true;
    private static final String  requestApiKey= "3e7cc266ae2b0e0d78e279ce8e361736";

    private int mPageNumber ;
    private String userSearchText;
    private boolean isLoading;
    private int previousTotalItemCount =0;


    ActivityDashboardBinding mBinding;
    RecyclerView recyclerViewList;
    ApiInterface apiInterface;
    RecyclerAdapter mAdapter;
    ProgressDialog progressDialog;
    GridLayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        mBinding.setDashBoardData(this);
        recyclerViewList = mBinding.rvListOfPhotos;

        layoutManager = new GridLayoutManager(this ,NUMBER_OF_COLUMNS);
        recyclerViewList.setLayoutManager(layoutManager);
        apiInterface = RetrofitClient.getClient().create(ApiInterface.class);

        recyclerViewList.addOnScrollListener(scrollRecyckerViewerListener);

    }




    public void onClick() {
        hideKeyboard(this);

        // setting to default
        isLoading = false;
        previousTotalItemCount = 0;

        mPageNumber = DEFAULT_PAGE_NUMBER;
        userSearchText = mBinding.etUserSearch.getText().toString();


        if (!userSearchText.isEmpty()) {
            requestImages(userSearchText,mPageNumber);
        } else {
            mBinding.etUserSearch.setError(getString(R.string.input));
        }


        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.search_result));
        progressDialog.show();
    }


    public void requestImages(int pageNumber) {
        requestImages(userSearchText ,pageNumber);
    }


    public void requestImages(final String searchText , final int pageNumber) {
        if (apiInterface != null) {
            Call<PhotoCollectionResponse> data = apiInterface.getImages(loadFieldsforRequestData(searchText,pageNumber));

            data.enqueue(new Callback<PhotoCollectionResponse>() {
                @Override
                public void onResponse(Call<PhotoCollectionResponse> call, Response<PhotoCollectionResponse> response) {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    if (response.isSuccessful()) {
                        if (pageNumber > 1) {
                            mAdapter.addImageData(response.body());
                        } else {
                            mAdapter = new RecyclerAdapter(response.body().getmPhotoInfo().getPhotoData());
                            recyclerViewList.setAdapter(mAdapter);
                        }
                    } else {
                        Toast.makeText(DashBoardActivity.this, R.string.response_error, Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<PhotoCollectionResponse> call, Throwable t) {
                    Log.d(TAG, t.getLocalizedMessage());
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                }
            });
        }
    }


    private RecyclerView.OnScrollListener scrollRecyckerViewerListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = ((GridLayoutManager)layoutManager).findFirstVisibleItemPosition();

            if(dy > 0) {
                if (isLoading) {
                    if (mAdapter.getItemCount() > previousTotalItemCount) {
                        isLoading = false;
                        previousTotalItemCount = totalItemCount;
                    }
                }


                if (!isLoading &&
                        (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    isLoading = true;

                    requestImages(mPageNumber++);

                }
            }

        }
    };


    public HashMap<String,Object> loadFieldsforRequestData(String userSearchedText, int pageNumber) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("method",requestMethod);
        map.put("format", requestFormatType);
        map.put("safe search",requestIsSafeSearch);
        map.put("api_key", requestApiKey);
        map.put("nojsoncallback", isNoJsonCallBack);
        map.put("text", userSearchedText);
        map.put("page",pageNumber);

        return map;
    }
}