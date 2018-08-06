package com.example.etoo.guardiannews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    private TextView itemTitle;
    private TextView time;
    private TextView section;


    public NewsAdapter(Context context, List<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        News currentNews = getItem(position);

        itemTitle = listItemView.findViewById(R.id.itemTitle);
        section = listItemView.findViewById(R.id.section);
        time = listItemView.findViewById(R.id.minutes_ago);

        itemTitle.setText(currentNews.getTitle());
        section.setText(currentNews.getSectionName());
        time.setText(currentNews.getTime());

        return listItemView;
    }
}
