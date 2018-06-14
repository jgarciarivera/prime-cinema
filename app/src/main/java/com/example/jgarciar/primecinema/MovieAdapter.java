package com.example.jgarciar.primecinema;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

//public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>
//{
//
//    public MovieAdapter(Context context, List<Movie> movies)
//    {
//        // Add parameters and initialize member variables
//    }
//
//    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder
//    {
//
//        public MovieAdapterViewHolder(View view)
//        {
//            super(view);
//        }
//    }
//
//    @Override
//    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
//    {
//        Context context = parent.getContext();
//        int layoutIdForListItem = R.layout.movie_list_item;
//        LayoutInflater inflater = LayoutInflater.from(context);
//        boolean shouldAttachToParentImmediately = false;
//
//        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
//        return new MovieAdapterViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(MovieAdapterViewHolder holder, int position)
//    {
//        //
//    }
//
//    @Override
//    public int getItemCount()
//    {
//        return 0;
//    }
//
//    public void setMovieData()
//    {
//        //
//    }
//}

public class MovieAdapter extends ArrayAdapter<Movie>
{

    private Context context;

    private List<Movie> values;

    public MovieAdapter(Context context, List<Movie> values)
    {
        super(context, R.layout.list_movie_item, values);

        this.context = context;

        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        if (row == null)
        {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_movie_item, parent, false);
        }

        TextView textView = (TextView) row.findViewById(R.id.list_movie_item_text);

        Movie item = values.get(position);
        String message = item.getTitle();
        System.out.println(message);
        textView.setText(message);

        return row;
    }
}
