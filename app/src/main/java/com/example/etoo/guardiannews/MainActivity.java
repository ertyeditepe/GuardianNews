package com.example.etoo.guardiannews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  LoaderManager.LoaderCallbacks<List<News>> {
    private ListView myListView;
    private TextView myEmptyTextView;
    private ProgressBar progressBar;
    private NewsAdapter mNewsAdapter;
    private static final String LOG_TAG = MainActivity.class.getName();
    private static final int NEWS_LOADER_ID = 1;
    private static final String
            GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search?format=json&order-by=newest&show-reference=author&show-tags=contributor&lang=en&page-size=50&q=&api-key=ee7fcfa8-a253-432e-9e44-80655700e71a";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        myListView = findViewById(R.id.listView);
        myEmptyTextView = findViewById(R.id.emptyView);
        progressBar = findViewById(R.id.progressBar);

            mNewsAdapter = new NewsAdapter(this, new ArrayList<News>());
            myListView.setAdapter(mNewsAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                News currentNews = mNewsAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsUri = Uri.parse(currentNews.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).

            Log.i(LOG_TAG, "initLoader() method called");

            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {

            progressBar.setVisibility(View.GONE);
            myEmptyTextView.setText(R.string.no_internet_connection);
        }

    }


    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "On Create Loder has worked");
        // Create a new loader for the given URL
        return new NewsLoader(this, GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        // Set empty state text to display "No earthquakes found."
        //  mEmptyStateTextView.setText(R.string.no_earthquakes);
        Log.i(LOG_TAG, "OnLoadFinished() called");

        // Clear the adapter of previous earthquake data
        mNewsAdapter.clear();

        progressBar.setVisibility(View.GONE);

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (news != null && !news.isEmpty()) {
            mNewsAdapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        Log.i(LOG_TAG,"OnLoaderReset() method called");

        // Loader reset, so we can clear out our existing data.
        mNewsAdapter.clear();
        Log.i(LOG_TAG,"mAdapter.clear() method called");

    }

}

