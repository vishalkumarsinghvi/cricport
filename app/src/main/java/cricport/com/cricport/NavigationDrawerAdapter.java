package cricport.com.cricport;

/**
 * Created by BADE on 02-02-2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;


public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavDrawerItem> listDrawerItems = Collections.emptyList();
    private LayoutInflater mInflater;
    private Context context;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> listDrawerItems) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.listDrawerItems = listDrawerItems;
    }

    public void delete(int position) {
        listDrawerItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (listDrawerItems.get(viewType).getItemType() == DrawerFragment.TYPE_ITEM) {
            view = mInflater.inflate(R.layout.nav_drawer_row, parent, false);
        } else if (listDrawerItems.get(viewType).getItemType() == DrawerFragment.TYPE_HEADER) {
            view = mInflater.inflate(R.layout.fragment_navigation_drawer_header, parent, false);
        }
        return new MyViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current = listDrawerItems.get(position);
        holder.tvItemTitle.setText(current.getTitle());
        holder.ivItemIcon.setImageResource(current.getIcon());
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return DrawerFragment.TYPE_HEADER;

        return DrawerFragment.TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return listDrawerItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemTitle, tvUserStatus;
        ImageView ivItemIcon;

        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == DrawerFragment.TYPE_ITEM) {
                tvItemTitle = (TextView) itemView.findViewById(R.id.nav_drawer_row_title);
                ivItemIcon = (ImageView) itemView.findViewById(R.id.nav_drawer_row_icon);
            }if (viewType == DrawerFragment.TYPE_HEADER) {
                tvItemTitle = (TextView) itemView.findViewById(R.id.frag_nav_drawer_header_tv_username);
                ivItemIcon = (ImageView) itemView.findViewById(R.id.view_feed_iv_UserImage);
            }
        }
    }
}